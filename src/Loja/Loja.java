package Loja;

public class Loja {
    private int idLoja;
    private String nome;
    private int idUsuario;

    public Loja(int idLoja, String nome, int idUsuario) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public String getNome() {
        return nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
