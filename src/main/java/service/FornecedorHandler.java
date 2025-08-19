package service;

import io.ArquivoException;
import io.ArquivoFornecedor;
import model.Fornecedor;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public final class FornecedorHandler {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArquivoFornecedor arquivoFornecedor = new ArquivoFornecedor(new File("src/main/java/resources/cadastro_fornecedores.csv"));
    private static List<Fornecedor> fornecedores;

    public static void carregaFornecedores() throws ArquivoException {
        arquivoFornecedor.pegaFornecedores();
    }

    public static void cadastraFornecedor() throws ArquivoException {
        String nome, endereco, telefone, cnpj, pessoaContato;

        nome = Leitor.lerString("Digite o nome: ");
        endereco = Leitor.lerString("Digite o endereco: ");
        telefone = Leitor.lerString("Digite o numero de telefone: ");
        cnpj = Leitor.lerString("Digite o cnpj: ");
        pessoaContato = Leitor.lerString("Digite a pessoa de contato: ");

        arquivoFornecedor.criaFornecedor(new Fornecedor(nome, endereco, telefone, cnpj, pessoaContato));
    }

    public static void listarFornecedores() throws ArquivoException{
        fornecedores = arquivoFornecedor.getListaFornecedor();

        System.out.println("Fornecedores: ");
        for (Fornecedor fornecedor:fornecedores) {
            System.out.println(fornecedor.infoFornecedor());
        }
    }

    public static Fornecedor buscarFornecedor(int id) throws ArquivoException{
        fornecedores = arquivoFornecedor.getListaFornecedor();

        for (Fornecedor fornecedor:fornecedores) {
            if(fornecedor.getCodigoIndentificador() == id) { return fornecedor; }
        }

        return null;
    }

    public static void removeFornecedor() throws ArquivoException{
        int id;
        fornecedores = arquivoFornecedor.getListaFornecedor();
        System.out.print("Digite o id do fornecedor que deseja remover: ");
        id = sc.nextInt();
        sc.nextLine();

        fornecedores.removeIf(fornecedor -> fornecedor.getCodigoIndentificador() == id);

        arquivoFornecedor.removeItem(id);
        System.out.println("Fornecedor deletado com sucesso!");
    }

    public static void editaFornecedor() throws ArquivoException {
        int id;
        fornecedores = arquivoFornecedor.getListaFornecedor();
        System.out.print("Digite o id do fornecedor que deseja editar: ");
        id = sc.nextInt();
        sc.nextLine();
        for (Fornecedor fornecedor: fornecedores) {
            if (fornecedor.getCodigoIndentificador() == id) {
                edicaoFornecedor(fornecedor);
                return;
            }
        }
        System.out.println("Fornecedor nao encontrado");
    }

    public static void edicaoFornecedor(Fornecedor fornecedor) throws ArquivoException {
        String entrada;
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Nome ["+fornecedor.getNomeEmpresa()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) fornecedor.setNomeEmpresa(entrada);
        System.out.print("Endereço ["+fornecedor.getEndereco()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) fornecedor.setEndereco(entrada);
        System.out.print("Número ["+fornecedor.getTelefone()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) fornecedor.setTelefone(entrada);
        System.out.print("CNPJ ["+fornecedor.getCNPJ()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) fornecedor.setCNPJ(entrada);
        System.out.print("Pessoa de contato ["+fornecedor.getPessoaContato()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) fornecedor.setPessoaContato(entrada);
        arquivoFornecedor.editaFornecedor(fornecedor);
        System.out.println("Fornecedor editado com sucesso!");
    }
}
