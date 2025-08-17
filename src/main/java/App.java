import io.ArquivoException;

import java.util.Scanner;
import java.util.List;

import service.*;

import model.*;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ArquivoException {
        
        //Para melhor Funcionamento do codigo, estas tres listas sao carregadas em inicio de codigo!!
        ClienteHandler.carregaClientes();
        FornecedorHandler.carregaFornecedores();
        ProdutoHandler.carregaProduto();
        
        //Ja essas Duas, serão carregadas sob demanda...
        /*
        CompraHandler.carregaCompra();
        VendaHandler.carregaVendas();
        */
        
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
        System.out.println("[5] - Edicao");
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

    public static void gerarRelatorioMensal()throws ArquivoException{
        
        List<Compra> compras = CompraHandler.getCompras();
        RelatoriosHandler.GerarTotalPagarFornecedor(compras);
        
        List<Venda> vendas = VendaHandler.getVendasFiado();
        RelatoriosHandler.GerarTotalReceberCliente(vendas);

        List<Produto> produtos = ProdutoHandler.getProdutos();
        RelatoriosHandler.GerarRelatorioProdutos(produtos);
        
        System.out.println("\n\n-----------------------------------------------");
        System.out.println("| Armazenado na pasta *Relatorios*            |");
        System.out.println("-----------------------------------------------\n\n");
        
    }

    public static void controleContas() throws ArquivoException{
        int opcao;
        boolean flag = true;
        do{
            menuControleContas();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 2:
                    CompraHandler.listarContasPagar();
                    break;
                case 1:
                    VendaHandler.listarVendasFiado();
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

    public static void registroVendas()throws ArquivoException{
        int opcao;
        boolean flag = true;
        do{
            menuRegistroVendas();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao){
                case 2:
                    VendaHandler.cadastraVenda();
                    break;
                case 1:
                    VendaHandler.listarVendas();
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
                    System.out.print("Digite o id do cliente: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Cliente buscaCliente = ClienteHandler.buscarCliente(id);
                    if(buscaCliente == null){
                        System.out.println("Cleinte nao cadastrado!");
                    }
                    else System.out.println(buscaCliente.infoCliente());
                    break;
                case 2:
                    System.out.print("Digite o id do fornecedor que deseja Buscar: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Fornecedor buscaFornecedor = FornecedorHandler.buscarFornecedor(id);
                    if(buscaFornecedor == null){
                        System.out.println("Fornecedor nao cadastrado!");
                    }
                    else System.out.println(buscaFornecedor.infoFornecedor());
                    break;
                case 1:
                    System.out.print("Digite o codigo do produto que deseja Buscar: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    Produto buscaProduto = ProdutoHandler.buscarProduto(id);
                    if(buscaProduto == null){
                        System.out.println("Produto nao esta em estoque!");
                    }
                    else System.out.println(buscaProduto.infoProduto());
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
