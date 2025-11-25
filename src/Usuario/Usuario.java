package Usuario;

import Interfaces.InterUsuario;

public abstract class Usuario implements InterUsuario {
    protected int id;
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public abstract boolean isVendedor();

}
