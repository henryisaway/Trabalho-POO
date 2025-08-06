import io.ArquivoCliente;
import io.ArquivoException;
import model.ClientePF;
import model.ClientePJ;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ArquivoCliente arquivoCliente = new ArquivoCliente(new File("src/main/java/resources/cadastro_clientes.csv"));

    public static void main(String[] args) throws ArquivoException {
        arquivoCliente.pegaClientes();
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
                default:
                    System.out.println("Opção invalida! digite novamente");
                    break;
            }
        } while (opcao != 0);
    }

    public static void menuPrincipal(){
        System.out.println("[4] - Cadastro (Clientes, Fornecedores, Produtos)");
        System.out.println("[3] - Registro de vendas");
        System.out.println("[2] - Controle de contas");
        System.out.println("[1] - Geração de relatórios mensais");
        System.out.println("[0] - Sair ");
        System.out.print("Digite uma opcão: ");
    }

    public static void menuCadastro(){
        System.out.println("[3] - Cadastrar Novo Cliente");
        System.out.println("[2] - Cadastrar Novo Fornecedor");
        System.out.println("[1] - Cadastrar Novo Produto");
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.print("Digite uma opcão: ");
    }

    public static void menuRegistroVendas(){
        System.out.println("[2] - Registrar Nova Venda");
        System.out.println("[1] - Listar Todas as Vendas");
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.print("Digite uma opcão: ");
    }

    public static void menuControleContas(){
        System.out.println("[2] - Visualizar Contas a Pagar (Fornecedores)");
        System.out.println("[1] - Visualizar Contas a Receber (Clientes)");
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.print("Digite uma opcão: ");
    }

    public static void gerarRelatorioMensal(){
        
    }

    public static void controleContas(){
        int opcao;
        menuControleContas();
        
    }

    public static void registroVendas(){
        int opcao;
        menuRegistroVendas();
    }

    public static void cadastro() throws ArquivoException {
       int opcao;
       menuCadastro();
       opcao = sc.nextInt();
       sc.nextLine();
       switch (opcao) {
           case 3:
               cadastraCliente();
               break;
           default:
               System.out.println("Opção invalida! digite novamente");
               break;
       }
    }

    public static void cadastraCliente() throws ArquivoException {
        int numInscricao, opcao;
        String nome, endereco, telefone, cpf, cnpj, tipoCliente, caminhoArquivo;
        LocalDate dataCadastro;

        System.out.print("O cliente é: \n[1] PF (Pessoa Física) \n[2] PJ (Pessoa Jurídica) \nDigite uma opção: ");
        opcao = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o nome: ");
        nome = sc.nextLine();
        System.out.print("Digite o endereço: ");
        endereco = sc.nextLine();
        System.out.print("Digite o número de telefone: ");
        telefone = sc.nextLine();
        System.out.print("Digite a data do cadastro (dd/mm/yyyy): ");
        dataCadastro = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (opcao == 1) {
            System.out.print("Digite o cpf: ");
            cpf = sc.nextLine();

            arquivoCliente.criaCliente(new ClientePF(nome, endereco, telefone, dataCadastro, cpf));
        } else if (opcao == 2) {
            System.out.print("Digite o cnpj: ");
            cnpj = sc.nextLine();
            System.out.print("Digite o número de inscrição estadual: ");
            numInscricao = sc.nextInt();
            sc.nextLine();

            arquivoCliente.criaCliente(new ClientePJ(nome, endereco, telefone, cnpj, numInscricao, dataCadastro));
        } else {
            System.out.println("Digite uma opção válida!");
        }
    }
}
