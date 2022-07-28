import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        String movies1 = "src/main/resources/movies1.csv";
        String movies2 = "src/main/resources/movies2.csv";
        String movies3 = "src/main/resources/movies3.csv";
        Arquivos arquivos = new Arquivos(Path.of(movies1), Path.of(movies2), Path.of(movies3));

        LocalDateTime horaInicio = LocalDateTime.now();
        Set<Filme> filmes = arquivos.lerArquivo();
        arquivos.escreverArquivoGeneroHorror(filmes);
        arquivos.escreverArquivoAno(filmes);
        LocalDateTime horaFim = LocalDateTime.now();
        arquivos.escreverArquivoTempoProcessamento(horaInicio, horaFim);

        System.out.println("Dez Melhores do Genero Ação");
        filmes.stream().parallel()
                .filter(l -> l.getGeneros().contains("Action"))
                .sorted(Comparator.comparing(Filme::getAvaliacao).reversed())
                .limit(10)
                .map(Filme::toString).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("Os melhores do diretor James Gunn");
        filmes.stream().parallel()
                .filter(l -> l.getDiretor().equals("James Gunn"))
                .sorted(Comparator.comparing(Filme::getAvaliacao).reversed())
                .limit(10)
                .map(Filme::toString).collect(Collectors.toList()).forEach(System.out::println);

    }
}
