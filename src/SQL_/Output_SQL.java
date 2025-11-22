package SQL_;

import Usuario.Usuario;
import java.sql.*;

public class Output_SQL {
    public static Usuario fazerLogin(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("isVendedor")
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário");
            e.printStackTrace();
        }

        return null; // login falhou
    }
}
