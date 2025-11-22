package Usuario;

    public class Usuario {
        private int id;
        private String nome;
        private String email;
        private String senha;
        private boolean isVendedor;

        public Usuario(int id, String nome, String email, String senha, boolean isVendedor) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.isVendedor = isVendedor;
        }

        public boolean isVendedor() {
            return isVendedor;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }
    }


