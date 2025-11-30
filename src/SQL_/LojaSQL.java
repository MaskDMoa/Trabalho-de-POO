package SQL_;

import Loja.*;
import java.sql.*;

public class LojaSQL {


    public static int buscarLojaUsuario(int idUsuario) {
        String sql = "SELECT * FROM loja WHERE idUsuario = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idLoja");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar loja do usuário: " + e.getMessage());
        }
        return 0;
    }


    public static int criarLoja(String nome, int idUsuario) {
        String sql = "INSERT INTO loja(nome, idUsuario) VALUES (?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nome);
            stmt.setInt(2, idUsuario);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet generated = stmt.getGeneratedKeys();
                if (generated.next()) {
                    return generated.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar loja: " + e.getMessage());
        }
        return -1;
    }

    public static boolean excluirLoja(int idLoja, int idVendedor) {
        String sql = "DELETE FROM loja WHERE idLoja = ? AND idVendedor = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLoja);
            stmt.setInt(2, idVendedor);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir loja: " + e.getMessage());
            return false;
        }
    }

    public static void listarLojas() {
        String sql = "SELECT * FROM loja";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Lojas Registradas ---");
            boolean achou = false;

            while (rs.next()) {
                achou = true;
                System.out.println("ID: " + rs.getInt("idLoja"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("-----------------------");
            }

            if (!achou) {
                System.out.println("Nenhuma loja encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar lojas: " + e.getMessage());
        }
    }


}
