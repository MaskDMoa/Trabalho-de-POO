package Loja;

public class Item {
        private int idItem;
        private String nome;
        private String descricao;
        private int quantidade;
        private double preco;
        private int idLoja;

        public Item(String nome, String descricao, int quantidade, double preco, int idLoja) {
            this.nome = nome;
            this.descricao = descricao;
            this.quantidade = quantidade;
            this.preco = preco;
            this.idLoja = idLoja;
        }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public int getIdLoja() {
        return idLoja;
    }
}
