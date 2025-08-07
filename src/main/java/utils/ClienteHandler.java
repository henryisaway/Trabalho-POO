package utils;

import io.ArquivoCliente;
import io.ArquivoException;
import model.ClientePF;
import model.ClientePJ;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public final class ClienteHandler {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArquivoCliente arquivoCliente = new ArquivoCliente(new File("src/main/java/resources/cadastro_clientes.csv"));
    private static List<ClientePF> clientesPF;
    private static List<ClientePJ> clientesPJ;

    public static void carregaClientes() throws ArquivoException {
        arquivoCliente.pegaClientes();
    }

    public static void cadastraCliente() throws ArquivoException {
        int numInscricao, opcao;
        String nome, endereco, telefone, cpf, cnpj;
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

    public static void listarClientes() throws ArquivoException{
        clientesPF = arquivoCliente.getListaClientePF();
        clientesPJ = arquivoCliente.getListaClientePJ();

        System.out.println("Clientes PF: ");
        for (ClientePF cliente:clientesPF) {
            System.out.println(cliente.infoClientPF());
        }
        System.out.println("Clientes PJ: ");
        for (ClientePJ cliente:clientesPJ) {
            System.out.println(cliente.infoClientePJ());
        }
    }

    public static String buscarCliente() throws ArquivoException{
        int id;

        System.out.print("Digite o id do cliente: ");
        id = sc.nextInt();
        sc.nextLine();

        clientesPF = arquivoCliente.getListaClientePF();
        clientesPJ = arquivoCliente.getListaClientePJ();

        for (ClientePF cliente:clientesPF) {
            if(cliente.getCodigoIndentificador() == id) { return cliente.infoClientPF(); }
        }

        for (ClientePJ cliente:clientesPJ) {
            if(cliente.getCodigoIndentificador() == id) { return cliente.infoClientePJ(); }
        }

        return "Usuário não existe";
    }

    public static void removeCliente() throws ArquivoException{
        int id;
        System.out.print("Digite o id do cliente que deseja remover: ");
        id = sc.nextInt();
        sc.nextLine();
    }
}
