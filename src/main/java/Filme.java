import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Filme {

    private Integer rank;
    private String titulo;
    private List<String> generos;
    private String descricao;
    private String diretor;
    private List<String> atores;
    private Integer ano;
    private Integer duracao;
    private Double avaliacao;
    private Integer votos;
    private BigDecimal receita;
    private Integer pontuacao;

    public Filme(Integer rank, String titulo, List<String> generos, String descricao, String diretor, List<String> atores, Integer ano, Integer duracao, Double avaliacao, Integer votos, BigDecimal receita, Integer pontuacao) {
        this.rank = rank;
        this.titulo = titulo;
        this.generos = generos;
        this.descricao = descricao;
        this.diretor = diretor;
        this.atores = atores;
        this.ano = ano;
        this.duracao = duracao;
        this.avaliacao = avaliacao;
        this.votos = votos;
        this.receita = receita;
        this.pontuacao = pontuacao;
    }

    public Filme() {

    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public BigDecimal getReceita() {
        return receita;
    }

    public void setReceita(BigDecimal receita) {
        this.receita = receita;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filme filme = (Filme) o;
        return Objects.equals(rank, filme.rank) && Objects.equals(titulo, filme.titulo) && Objects.equals(generos, filme.generos) && Objects.equals(descricao, filme.descricao) && Objects.equals(diretor, filme.diretor) && Objects.equals(atores, filme.atores) && Objects.equals(ano, filme.ano) && Objects.equals(duracao, filme.duracao) && Objects.equals(avaliacao, filme.avaliacao) && Objects.equals(votos, filme.votos) && Objects.equals(receita, filme.receita) && Objects.equals(pontuacao, filme.pontuacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, titulo, generos, descricao, diretor, atores, ano, duracao, avaliacao, votos, receita, pontuacao);
    }

    @Override
    public String toString() {
        return "Filme{" +
                "rank=" + rank +
                ", titulo='" + titulo + '\'' +
                ", generos=" + generos +
                ", descricao='" + descricao + '\'' +
                ", diretor='" + diretor + '\'' +
                ", atores=" + atores +
                ", ano=" + ano +
                ", duracao=" + duracao +
                ", avaliacao=" + avaliacao +
                ", votos=" + votos +
                ", receita=" + receita +
                ", pontuacao=" + pontuacao +
                '}';
    }
}
