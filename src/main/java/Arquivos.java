import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Arquivos {
    private final Path[] arquivos;

    public Arquivos(Path... arquivos) {
        this.arquivos = arquivos;
    }

    public Set<Filme> lerArquivo() {
        return Arrays.stream(arquivos)
                .parallel()
                .map(path -> {
                    try {
                        return Files.lines(path)
                                .parallel()
                                .skip(1)
                                .map(linha -> {
                                    String[] split = linha.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                                    return parseFilme(split);
                                })
                                .collect(Collectors.toList());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                //Stream<List<Carro>>
                .flatMap(lista -> lista.stream())
                .collect(Collectors.toSet());
    }

    public Filme parseFilme(String[] split) {
        Filme filme = new Filme();

        filme.setRank(Integer.valueOf(split[0]));
        filme.setTitulo(split[1]);
        filme.setGeneros(splitDoSplit(split[2]));
        filme.setDescricao(split[3]);
        filme.setDiretor(split[4]);
        filme.setAtores(splitDoSplit(split[5]));
        filme.setAno(Integer.valueOf(split[6]));
        filme.setDuracao(Integer.valueOf(split[7]));
        filme.setAvaliacao(Double.valueOf(split[8]));
        filme.setVotos(split[9].isEmpty() ? 0 : Integer.parseInt(split[9]));
        if (split.length > 10) filme.setReceita(split[10].isEmpty() ? new BigDecimal("0.00") : new BigDecimal(split[10]));
        if (split.length > 11) filme.setPontuacao(split[11].isEmpty() ? 0 : Integer.parseInt(split[11]));

        return filme;
    }

    public List<String> splitDoSplit(String split) {
        split.replace("\"", "");
        return Stream.of(split.split(","))
                .map(String::new)
                .collect(Collectors.toList());
    }


    public void escreverArquivoGeneroHorror(Set<Filme> filmes) {
        System.out.println("criando arquivo do top 20 filmes de Horror");

        final Path destino = Path.of("src/main/resources/Horror.csv");
        Pattern horror = Pattern.compile(".*horror.*", Pattern.CASE_INSENSITIVE);

        try {
            var linhasFile = filmes.stream()
                    .parallel()
                    .filter(l -> horror.matcher(l.getGeneros().toString()).matches())
                    .sorted(Comparator.comparing(Filme::getAvaliacao).reversed())
                    .limit(20)
                    .map(Filme::toString)
                    .collect(Collectors.toList());

            Files.write(destino, linhasFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void escreverArquivoAno(Set<Filme> filmes) {
        System.out.println("criando arquivos de Melhores filmes por ano");

        Set<Integer> anos = new HashSet();

        anos = filmes.stream()
                .map(Filme::getAno)
                .collect(Collectors.toSet());
        for (Integer ano : anos) {
            try {
                String paths = "src/main/resources/MelhoresFilmesAno" + ano + ".csv";
                final Path destino = Path.of(paths);
                var linhasFile = filmes.stream()
                        .parallel()
                        .filter(l -> l.getAno().equals(ano))
                        .sorted(Comparator.comparing(Filme::getAvaliacao).reversed())
                        .limit(50)
                        .map(Filme::toString)
                        .collect(Collectors.toList());

                Files.write(destino, linhasFile);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void escreverArquivoTempoProcessamento(LocalDateTime start, LocalDateTime end) {
        System.out.println("criando arquivo Tempo Processamento");

        final Path destino = Path.of("src/main/resources/tempoProcessamento.csv");
        List<String> dados = new ArrayList<>();
        long segundos = ChronoUnit.SECONDS.between(start, end);
        long mili = ChronoUnit.MILLIS.between(start, end);

        dados.add("Incio processamento: " + start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SS")));
        dados.add("Fim processamento: " + end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SS")));
        dados.add("Tempo em milisegundos: " + mili + " milisegundos");
        dados.add("Tempo em segundos: " + segundos +" segundos");


        try {
            Files.write(destino, dados);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
