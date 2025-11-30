package Usuario;

import Interfaces.InterUsuario;
import Loja.Carrinho;
import SQL_.LojaSQL;
import SQL_.ItemSQL;
import java.util.Scanner;

public class Cliente extends Usuario implements InterUsuario {

    Scanner input = new Scanner(System.in);

    private double credito = 0.0;

    public void adicionarCredito(double valor) {
        this.credito += valor;
        System.out.println("Crédito adicionado! Saldo atual: R$" + credito);
    }

    public Cliente(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }

    @Override
    public boolean isVendedor() {
        return false;
    }

    @Override
    public void mostrarMenu() {
        while (true) {
            System.out.println("--- Menu Cliente ---");
            System.out.println("1 - Listar Lojas");
            System.out.println("2 - Ver Itens de uma Loja");
            System.out.println("3 - Ver Carrinho");
            System.out.println("4 - Adicionar crédito");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    LojaSQL.listarLojas();
                    break;

                case 2:
                    System.out.print("Digite o ID da loja: ");
                    ItemSQL.mostrarItem(input.nextInt());
                    break;

                case 3:
                    gerenciar();
                    break;

                case 4:
                    System.out.print("Quanto deseja adicionar? R$");
                    adicionarCredito(input.nextDouble());
                    break;
                case 0:
                    Carrinho.limpar();
                    Thread sairThread = new Thread(() -> {
                        try {
                            System.out.print("Saindo da Conta");
                            for(int i = 0; i < 3; i++){
                                Thread.sleep(500);
                                System.out.print(".");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                    sairThread.start();
                    try {
                        sairThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("");
                    return;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    @Override
    public void gerenciar() {

        while (true) {
            System.out.println("--- Carrinho ---");
            System.out.println("1 - Adicionar item");
            System.out.println("2 - Remover item");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Finalizar compra");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("ID do item: ");
                    Carrinho.adicionar(input.nextInt());
                    break;
                case 2:
                    System.out.print("ID do item: ");
                    Carrinho.remover(input.nextInt());
                    break;
                case 3:
                    Carrinho.listar();
                    break;
                case 4:
                    double total = Carrinho.getTotal();

                    if (total > credito) {
                        System.out.println("Saldo insuficiente! Total: R$" + total + " | Crédito: R$" + credito);
                    } else {
                        credito -= total;
                        Carrinho.finalizarCompra();
                        System.out.println("Compra paga com sucesso!");
                        System.out.println("Crédito restante: R$" + credito);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

}
