package SQL_;

import java.sql.*;

public class BDSQL {
    public static void main(String[] args) {

        String sql = "SELECT * FROM usuario";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                boolean isVendedor = rs.getBoolean("isVendedor");

                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Email: " + email);
                System.out.println("Tipo: " + (isVendedor ? "Vendedor" : "Cliente"));
                System.out.println("Senha: " + senha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
