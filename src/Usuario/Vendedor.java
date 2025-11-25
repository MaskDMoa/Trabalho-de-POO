package Usuario;

import Interfaces.*;
import Loja.Item;
import SQL_.*;
import java.util.Scanner;

public class Vendedor extends Usuario implements InterUsuario {

    private int idLoja;

    public Vendedor(int id, String nome, String email, String senha, int idLoja) {
        super(id, nome, email, senha);
        this.idLoja = idLoja;
    }

    @Override
    public boolean isVendedor() {
        return true;
    }


    public void mostrarMenu(){
        Scanner input = new Scanner(System.in);

        if (idLoja == 0) {
            System.out.println("Você ainda não possui uma loja!");
            System.out.print("Deseja criar agora? (S/N): ");
            String resp = input.nextLine().toUpperCase();

            if (resp.equals("S")) {
                System.out.print("Nome da loja: ");
                String nomeLoja = input.nextLine();

                int novaLoja = LojaSQL.criarLoja(nomeLoja, getId());
                if (novaLoja != -1) {
                    idLoja = novaLoja;
                    System.out.println("Loja criada com sucesso!");
                } else {
                    System.out.println("Erro ao criar loja.");
                    return;
                }
            } else {
                return;
            }
        }

        while(true) {
            System.out.println("Menu Vendedor:");
            System.out.println("1 - Gerenciar Itens");
            System.out.println("2 - Excluir Loja");
            System.out.println("0 - Sair");
            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    gerenciar();
                    break;
                case 2:
                    System.out.print("Tem certeza que deseja excluir a loja? (S/N): ");
                    String confirma = input.nextLine();

                    if (confirma.equalsIgnoreCase("S")) {
                        if (LojaSQL.excluirLoja(idLoja, getId())) {
                            System.out.println("Loja excluída com sucesso!");
                        } else {
                            System.out.println("Erro: Loja não existe ou não pertence a você.");
                        }
                    } else {
                        System.out.println("Exclusão cancelada!");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    @Override
    public void gerenciar() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("--- Gerenciar Itens ---");
            System.out.println("1 - Listar itens");
            System.out.println("2 - Adicionar item");
            System.out.println("3 - Editar item");
            System.out.println("4 - Remover item");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    ItemSQL.mostrarItem(idLoja);
                    break;

                case 2:
                    System.out.print("Nome do item: ");
                    String nome = input.nextLine();
                    System.out.print("Descrição: ");
                    String desc = input.nextLine();
                    System.out.print("Quantidade: ");
                    int qtd = input.nextInt();
                    System.out.print("Preço: ");
                    double preco = input.nextDouble();
                    input.nextLine();

                    Item item = new Item(nome, desc, qtd, preco, idLoja);

                    ItemSQL.adicionarItem(item);
                    break;

                case 3:
                    ItemSQL.mostrarItem(idLoja);
                    System.out.print("ID do item para editar: ");
                    int idAlt = input.nextInt();
                    input.nextLine();

                    System.out.print("Novo nome: ");
                    String nNome = input.nextLine();
                    System.out.print("Nova descrição: ");
                    String nDesc = input.nextLine();
                    System.out.print("Nova quantidade: ");
                    int nQtd = input.nextInt();
                    System.out.print("Novo preço: ");
                    double nPreco = input.nextDouble();
                    input.nextLine();

                    ItemSQL.atualizarItem(idAlt, nNome, nDesc, nQtd, nPreco);
                    break;

                case 4:
                    ItemSQL.mostrarItem(idLoja);
                    System.out.print("ID do item para remover: ");
                    int idDel = input.nextInt();
                    input.nextLine();

                    ItemSQL.deletarItem(idDel);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
