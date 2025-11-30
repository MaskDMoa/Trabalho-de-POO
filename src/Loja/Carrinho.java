package Loja;

import SQL_.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static List<Item> itensCarrinho = new ArrayList<>();


    // Adicionar item ao carrinho pelo ID
    public static void adicionar(int idItem) {
        Item item = buscarItemNoBanco(idItem);

        if (item != null) {
            itensCarrinho.add(item);
            System.out.println("Item adicionado ao carrinho");
        } else {
            System.out.println("Item não encontrado");
        }
    }

    // Remover item do carrinho pelo ID
    public static void remover(int idItem) {
        for (Item item : itensCarrinho) {
            if (item.getIdItem() == idItem) {
                itensCarrinho.remove(item);
                System.out.println("Item removido do carrinho!");
                return;
            }
        }
        System.out.println("Item não encontrado no carrinho!");
    }

    // Listar itens do carrinho
    public static void listar() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }

        System.out.println("--- Itens no Carrinho ---");
        double total = 0.0;

        for (Item item : itensCarrinho) {
            System.out.println("ID: " + item.getIdItem());
            System.out.println("Nome: " + item.getNome());
            System.out.println("Preço: R$" + item.getPreco());
            System.out.println("------------------------");
            total += item.getPreco();
        }

        System.out.println("Total da compra: R$" + total);
    }

    // Busca um item no banco pelo ID
    private static Item buscarItemNoBanco(int idItem) {
        String sql = "SELECT * FROM itens WHERE idItem = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idItem);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Item(
                        rs.getInt("idItem"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"),
                        rs.getInt("idLoja")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar item: " + e.getMessage());
        }

        return null;
    }

    public static void finalizarCompra() {
        if (itensCarrinho.isEmpty()) {
            System.out.println("Carrinho vazio! Não é possível finalizar a compra.");
            return;
        }

        System.out.println("Finalizando compra...");

        for (Item item : itensCarrinho) {
            if (!ItemSQL.reduzirQuantidade(item.getIdItem())) {
                System.out.println("Erro ao atualizar estoque do item: " + item.getNome());
            }
        }

        itensCarrinho.clear();
        System.out.println("Compra realizada com sucesso!");
    }

    public static double getTotal() {
        double total = 0.0;
        for (Item item : itensCarrinho) {
            total += item.getPreco();
        }
        return total;
    }
}
