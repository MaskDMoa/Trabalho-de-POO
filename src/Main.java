import java.util.*;

import SQL_.*;
import Usuario.*;
import Exception.InputException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        do{
            try {
                System.out.println("1 - Entrar");
                System.out.println("2 - Criar Conta");
                System.out.println("0 - Sair");
                int opcao = input.nextInt();

                if (opcao != 1 && opcao != 2 && opcao != 0) {
                    throw new InputException("Opção inválida!");
                }

                switch (opcao) {
                    case 1:
                        input.nextLine(); // limpar variavel

                        System.out.print("Email: ");
                        String emailLogin = input.nextLine();

                        System.out.print("Senha: ");
                        String senhaLogin = input.nextLine();

                        Usuario usuario = UsuarioSQL.logar(emailLogin, senhaLogin);

                        if (usuario != null) {
                            System.out.println("Bem-vindo, " + usuario.getNome());
                            System.out.println("Login realizado com sucesso!");

                            //Poliformismo, quando o usuario é cliente ele mostra menu cliente, quando é vendedor mostra menu vendedor
                            usuario.mostrarMenu();

                        } else {
                            System.out.println("Email ou senha incorretos");
                        }
                        break;
                    case 2:
                        input.nextLine(); // limpar variavel

                        System.out.print("Digite seu nome: ");
                        String nome = input.nextLine();

                        System.out.print("Digite seu email: ");
                        String email = input.nextLine();

                        System.out.print("Digite sua senha: ");
                        String senha = input.nextLine();

                        System.out.print("Você é vendedor? (S/N): ");
                        String resp = input.nextLine().trim().toUpperCase();
                        boolean isVendedor = resp.equals("S");

                        if (UsuarioSQL.criarUsuario(nome, email, senha, isVendedor)) {
                            System.out.println("Usuário criado com sucesso");
                        } else {
                            System.out.println("Erro ao criar usuário");
                        }
                        break;
                    default:
                        input.close();

                        Thread sairThread = new Thread(() -> {
                            try {
                                System.out.print("Saindo");
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
                        return;
                }
            }catch (InputMismatchException e) {
                System.out.println("Erro: Digite uma tecla válida");
                input.nextLine();
            }catch (InputException e) {
                System.out.println(e.getMessage());
            }

        }while(true);


    }
}