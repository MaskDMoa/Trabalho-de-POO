import Exceptions.InputException;
import java.util.*;
import SQL_.*;
import Usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao = -1;

        do {
            try {
                System.out.println("1-Entrar");
                System.out.println("2-Criar Nova Conta");
                System.out.println("0-Sair");
                opcao = input.nextInt();

                if (opcao != 1 && opcao != 2 && opcao != 0) {
                    throw new InputException("Opção inválida!");
                }

                switch (opcao) {
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    case 1:
                        input.nextLine();
                        System.out.println("Email:");
                        String eemail = input.nextLine();

                        System.out.println("Senha:");
                        String esenha = input.nextLine();

                        Usuario usuario = Output_SQL.fazerLogin(eemail, esenha);

                        if (usuario != null) {
                            System.out.println("Bem vindo, " + usuario.getNome());
                            if(usuario.isVendedor())
                            {
                                System.out.println("Você é um vendedor");
                            }
                        }else{
                            System.out.println("Email ou senha estão errados");
                        }
                        /*- Buscar o usuário pelo e-mail cadastrado
                        - Solicitar a senha do usuário
                        - Verificar se a senha está correta
                             -> Se sim: permitir o acesso
                             -> Se não: exibir uma mensagem de erro e não entrar
                        */
                        break;
                    case 2:
                        System.out.print("Nome: ");
                        input.nextLine(); // limpar buffer
                        String nome = input.nextLine();

                        System.out.print("Email: ");
                        String email = input.nextLine();

                        System.out.print("Senha: ");
                        String senha = input.nextLine();

                        boolean isVendedor = false;
                        System.out.print("É vendedor? (1=Sim, 0=Não): ");
                        int ven = input.nextInt();

                        if(ven == 1)
                        {
                            isVendedor = true;
                        }

                        Input_SQL.criarUsuario(nome, email, senha, isVendedor);
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite uma tecla válida");
                input.nextLine();
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }while(opcao != 0);

        input.close();
    }
}