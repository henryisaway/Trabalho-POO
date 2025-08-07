import utils.ClienteHandler;
import io.ArquivoException;

import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ArquivoException {
        ClienteHandler.carregaClientes();
        int opcao;

        do {
            menuPrincipal();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 0:
                    System.out.println("Desligando...");
                    break;
                case 1:
                    gerarRelatorioMensal();
                    break;
                case 2:
                    controleContas();
                    break;
                case 3:
                    registroVendas();
                    break;
                case 4:
                    cadastro();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    busca();
                    break;
                case 8:
                    listagem();
                    break;
                default:
                    System.out.println("Opção invalida! digite novamente");
                    break;
            }
        } while (opcao != 0);
    }

    public static void menuPrincipal(){
        System.out.println("[0] - Sair ");
        System.out.println("[1] - Geracao de relatorios mensais");
        System.out.println("[2] - Controle de contas");
        System.out.println("[3] - Registro de vendas");
        System.out.println("[4] - Cadastro");
        System.out.println("[5] - Edição");
        System.out.println("[6] - Remoção");
        System.out.println("[7] - Busca");
        System.out.println("[8] - Listagem");
        System.out.print("Digite uma opcao: ");
    }

    public static void menuGeral(){
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.println("[1] - Produto");
        System.out.println("[2] - Fornecedor");
        System.out.println("[3] - Cliente");
        System.out.print("Digite uma opcao: ");
    }

    public static void menuRegistroVendas(){
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.println("[1] - Listar Todas as Vendas");
        System.out.println("[2] - Registrar Nova Venda");
        System.out.print("Digite uma opcao: ");
    }

    public static void menuControleContas(){
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.println("[1] - Visualizar Contas a Receber (Clientes)");
        System.out.println("[2] - Visualizar Contas a Pagar (Fornecedores)");
        System.out.print("Digite uma opcao: ");
    }

    public static void gerarRelatorioMensal(){
        
    }

    public static void controleContas(){
        int opcao;
        boolean flag = true;
        do{
            menuControleContas();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 2:
                    break;
                case 1:
                    break;
                case 0:
                    System.out.println("retornando ao menu principal");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção invalida! digite novamente");
                    break;
            }
            
            
        }while(flag);
        
    }

    public static void registroVendas(){
        int opcao;
        menuRegistroVendas();
    }
    
    public static void cadastro() throws ArquivoException {
        int opcao;
        boolean flag = true;
        do{
           System.out.println("Selecione o que deseja cadastrar: ");
           menuGeral();
           opcao = sc.nextInt();
           sc.nextLine();
           switch(opcao){
                case 3:
                    ClienteHandler.cadastraCliente();
                    break;
                case 2:

                    break;
                case 1:

                    break;
                case 0:
                    System.out.println("retornando ao menu principal");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção invalida! digite novamente");
                    break;
            }

       }while(flag);
    }

    public static void listagem() throws ArquivoException {
        int opcao;
        boolean flag = true;
        do{
            System.out.println("Selecione o que deseja listar: ");
            menuGeral();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 3:
                    ClienteHandler.listarClientes();
                    break;
                case 2:

                    break;
                case 1:

                    break;
                case 0:
                    System.out.println("retornando ao menu principal");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção invalida! digite novamente");
                    break;
            }

        }while(flag);
    }

    public static void busca() throws ArquivoException {
        int opcao;
        boolean flag = true;
        do{
            System.out.println("Selecione o que deseja buscar: ");
            menuGeral();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 3:
                    System.out.println(ClienteHandler.buscarCliente());
                    break;
                case 2:

                    break;
                case 1:

                    break;
                case 0:
                    System.out.println("retornando ao menu principal");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção invalida! digite novamente");
                    break;
            }

        }while(flag);
    }
}
