package SQL_;

import java.sql.*;

public class Input_SQL {
        public static void criarUsuario(String nome, String email, String senha, boolean isVendedor) {
            String sql = "INSERT INTO usuario(nome, email, senha, isVendedor) VALUES (?, ?, ?, ?)";

            try (Connection conn = Conexao.getConexao();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, senha);
                stmt.setBoolean(4, isVendedor);

                stmt.executeUpdate();
                System.out.println("Conta criada com sucesso!");

            } catch (SQLException e) {
                System.out.println("Erro ao criar usuário: " + e.getMessage());
            }
        }

}
