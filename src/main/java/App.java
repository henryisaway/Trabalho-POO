import utils.ClienteHandler;
import io.ArquivoException;
import utils.FornecedorHandler;

import java.util.Scanner;
import utils.ProdutoHandler;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ArquivoException {
        ClienteHandler.carregaClientes();
        FornecedorHandler.carregaFornecedores();
        ProdutoHandler.carregaProduto();
        int opcao;

        do {
            menuPrincipal();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){8
                    
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
                    edicao();
                    break;
                case 6:
                    remocao();
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
        System.out.println("[5] - Edicaoo");
        System.out.println("[6] - Remocao");
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
                    FornecedorHandler.cadastraFornecedor();
                    break;
                case 1:
                    ProdutoHandler.cadastraProduto();
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
                    FornecedorHandler.listarFornecedores();
                    break;
                case 1:
                    ProdutoHandler.listarProdutos();
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
                    System.out.println(FornecedorHandler.buscarFornecedor());
                    break;
                case 1:
                    System.out.println(ProdutoHandler.buscarProduto());
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

    public static void remocao() throws ArquivoException {
        int opcao;
        boolean flag = true;
        do{
            System.out.println("Selecione o que deseja remover: ");
            menuGeral();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 3:
                    ClienteHandler.removeCliente();
                    break;
                case 2:
                    FornecedorHandler.removeFornecedor();
                    break;
                case 1:
                    ProdutoHandler.removeProduto();
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

    public static void edicao() throws ArquivoException {
        int opcao;
        boolean flag = true;
        do{
            System.out.println("Selecione o que deseja editar: ");
            menuGeral();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 3:
                    ClienteHandler.editaCliente();
                    break;
                case 2:
                    FornecedorHandler.editaFornecedor();
                    break;
                case 1:
                    ProdutoHandler.editaProduto();
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
