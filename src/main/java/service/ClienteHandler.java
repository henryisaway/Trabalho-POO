package service;

import io.ArquivoCliente;
import io.ArquivoException;
import model.Cliente;
import model.ClientePF;
import model.ClientePJ;

import java.io.File;
import java.time.LocalDate;
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
        String entrada;
        
        while (true) {
            System.out.print("Digite [1] PF ou [2] PJ: ");
            entrada = sc.nextLine(); // lê a linha inteira

            if (entrada.trim().equals("1") || entrada.trim().equals("2")) {
                opcao = Integer.parseInt(entrada.trim());
                break;
            } else {
                System.out.println("Entrada invalida. Tente novamente!");
            }
        }
        nome = Leitor.lerString("Digite o nome: ");
        endereco = Leitor.lerString("Digite o endereco: ");
        telefone = Leitor.lerString("Digite o numero de telefone: ");
        dataCadastro = LocalDate.now();//Tem que ser a data de hoje...
        if (opcao == 1) {
            cpf = Leitor.lerString("Digite o cpf: ");
            arquivoCliente.criaCliente(new ClientePF(nome, endereco, telefone, dataCadastro, cpf));
        } 
        else{
            cnpj =Leitor.lerString("Digite o cnpj: ");
                
            numInscricao = Leitor.lerInteiro("Digite o numero de inscricao estadual: ");
            arquivoCliente.criaCliente(new ClientePJ(nome, endereco, telefone, cnpj, numInscricao, dataCadastro));
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

    public static Cliente buscarCliente(int id) throws ArquivoException{

        clientesPF = arquivoCliente.getListaClientePF();
        clientesPJ = arquivoCliente.getListaClientePJ();

        for (ClientePF cliente:clientesPF) {
            if(cliente.getCodigoIndentificador() == id) { return cliente; }
        }

        for (ClientePJ cliente:clientesPJ) {
            if(cliente.getCodigoIndentificador() == id) { return cliente; }
        }

        return null;
    }

    public static void removeCliente() throws ArquivoException{
        int id;
        clientesPF = arquivoCliente.getListaClientePF();
        clientesPJ = arquivoCliente.getListaClientePJ();
        System.out.print("Digite o id do cliente que deseja remover: ");
        id = sc.nextInt();
        sc.nextLine();

        clientesPF.removeIf(cliente -> cliente.getCodigoIndentificador() == id);
        clientesPJ.removeIf(cliente -> cliente.getCodigoIndentificador() == id);

        arquivoCliente.removeItem(id);
        System.out.println("Cliente deletado com sucesso!");
    }

    public static void editaCliente() throws ArquivoException {
        int id;
        clientesPF = arquivoCliente.getListaClientePF();
        clientesPJ = arquivoCliente.getListaClientePJ();
        System.out.print("Digite o id do cliente que deseja editar: ");
        id = sc.nextInt();
        sc.nextLine();
        for (ClientePF cliente: clientesPF) {
            if (cliente.getCodigoIndentificador() == id) {
                edicaoCliente(cliente);
                return;
            }
        }
        for (ClientePJ cliente: clientesPJ) {
            if (cliente.getCodigoIndentificador() == id) {
                edicaoCliente(cliente);
                return;
            }
        }
        System.out.println("Cliente nao existe!");
    }

    public static void edicaoCliente(ClientePF cliente) throws ArquivoException {
        String entrada;
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Nome ["+cliente.getNome()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setNome(entrada);
        System.out.print("Endereco ["+cliente.getEndereco()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setEndereco(entrada);
        System.out.print("Numero ["+cliente.getTelefone()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setTelefone(entrada);
        System.out.print("CPF ["+cliente.getCPF()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setCPF(entrada);
        arquivoCliente.editaCliente(cliente);
        System.out.println("Cliente editado com sucesso!");
    }

    public static void edicaoCliente(ClientePJ cliente) throws ArquivoException {
        String entrada;
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Nome ["+cliente.getNome()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setNome(entrada);
        System.out.print("Endereco ["+cliente.getEndereco()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setEndereco(entrada);
        System.out.print("Numero ["+cliente.getTelefone()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setTelefone(entrada);
        System.out.print("CNPJ ["+cliente.getCNPJ()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) cliente.setCNPJ(entrada);
    
        while (true) {
            System.out.print("Inscricao Estadual [" + cliente.getInscricaoEstadual() + "]: ");
            entrada = sc.nextLine();
            if(!entrada.trim().isEmpty()){
                try {
                    int inscricao = Integer.parseInt(entrada);
                    cliente.setInscricaoEstadual(inscricao);
                    break; // Entrada válida, sai do loop
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido! Digite um numero inteiro ou pressione Enter para manter o valor atual.");
                }
            }
            else break;
        }
        arquivoCliente.editaCliente(cliente);
        System.out.println("Cliente editado com sucesso!");
    }
}
