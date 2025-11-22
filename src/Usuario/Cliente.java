package Usuario;

public class Cliente{
    private String nome;
    private String email;
    private String senha;

    public Cliente(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void Comprar(){
        /* Aqui entra o itens que a pessoa selecionou */
    }

    public void Carrinho(){
        /* Aqui vai mostrar os itens que ela seleciounou para comprar */
    }

}
