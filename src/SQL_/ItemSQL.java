package SQL_;

import Loja.Item;
import java.sql.*;

public class ItemSQL {

    public static boolean adicionarItem(Item item){
        String sql = "INSERT INTO itens(nome, descricao, quantidade, preco, idLoja) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getDescricao());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPreco());
            stmt.setInt(5, item.getIdLoja());

            stmt.executeUpdate();
            System.out.println("Item cadastrado com sucesso!");
            return true;


        }catch (SQLException e) {
            System.out.println("Erro ao cadastrar item: " +  e.getMessage());
        }

        return false;
    }

    public static void mostrarItem(int idLoja){

        String sql = "SELECT * FROM itens WHERE idLoja = ?";

        try(Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, idLoja);

            ResultSet rs = stmt.executeQuery();


            System.out.println("--Itens da Loja--");

            boolean achou = false;

            while(rs.next()){
                achou = true;
                int id = rs.getInt("idItem");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");

                System.out.println("--- Item ---");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Descrição: " + descricao);
                System.out.println("Quantidade: " + quantidade);
                System.out.println("Preço: R$" + preco);
            }

            if(!achou){
                System.out.println("Nenhum item encontrado");
            }
        }catch (SQLException e){
            System.out.println("Erro ao listar itens: " +  e.getMessage());
        }

    }

    public static boolean atualizarItem(int idItem, String nome, String descricao, int quantidade, double preco) {
        String sql = "UPDATE itens SET nome = ?, descricao = ?, quantidade = ?, preco = ? WHERE idItem = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, preco);
            stmt.setInt(5, idItem);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar item: " + e.getMessage());
            return false;
        }
    }

    public static boolean deletarItem(int idItem) {
        String sql = "DELETE FROM itens WHERE idItem = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idItem);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar item: " + e.getMessage());
            return false;
        }
    }


}
