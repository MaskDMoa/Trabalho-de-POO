package SQL_;

import Usuario.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioSQL {

    public static boolean criarUsuario(String nome, String email, String senha, boolean isVendedor) {

        String sql = "INSERT INTO usuario (nome, email, senha, isVendedor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setBoolean(4, isVendedor);

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Erro: Este email já está cadastrado! Tente outro.");
            } else {
                System.out.println("Erro ao criar usuário: " + e.getMessage());
            }

            return false;
        }
    }

    public static Usuario logar(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                boolean isVendedor = rs.getBoolean("isVendedor");


                if (isVendedor) {
                    int idLoja = LojaSQL.buscarLojaUsuario(id);
                    return new Vendedor(id, nome, email, senha, idLoja);
                } else {
                    return new Cliente(id, nome, email, senha);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
