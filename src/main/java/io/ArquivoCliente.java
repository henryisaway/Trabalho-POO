package io;

import model.ClientePF;
import model.ClientePJ;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArquivoCliente extends Arquivo {
    private final List<ClientePF> listaClientesPF = new ArrayList<ClientePF>();
    private final List<ClientePJ> listaClientesPJ = new ArrayList<ClientePJ>();

    int codigo, numInscricao;
    String nome, endereco, telefone, cpf, cnpj, tipoCliente;
    LocalDate dataCadastro;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ArquivoCliente(File arquivo) {
        super(arquivo);
    }
    
    public void pegaClientes() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo();
        String[] linhas = conteudoArquivo.split("\\r?\\n");
        String[] linha;

        for(int i=1; i < linhas.length; i++) {
            linha = linhas[i].split(";");
            codigo = Integer.parseInt(linha[0]);
            nome = linha[1];
            endereco = linha[2];
            telefone = linha[3];
            dataCadastro = LocalDate.parse(linha[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            tipoCliente = linha[5];
            if(tipoCliente.equals("F")) {
                cpf = linha[6];
                listaClientesPF.add(new ClientePF(nome, endereco, telefone, dataCadastro, cpf, codigo));
            } else {
                cnpj = linha[6];
                numInscricao = Integer.parseInt(linha[7]);
                listaClientesPJ.add(new ClientePJ(nome, endereco, telefone, cnpj, numInscricao, dataCadastro, codigo));
            }
        }
    }

    public void criaCliente (ClientePF cliente) throws ArquivoException {
        codigo = cliente.getCodigoIndentificador();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        dataCadastro = cliente.getData();

        tipoCliente = "F";
        cpf = cliente.getCPF();
        String linhaCliente = ("\n"+codigo+";"+nome+";"+endereco+";"+telefone+";"+formatador.format(dataCadastro)+";"+tipoCliente+";"+cpf);
        super.adicionaTextoArquivo(linhaCliente);
    }

    public void criaCliente (ClientePJ cliente) throws ArquivoException {
        codigo = cliente.getCodigoIndentificador();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        dataCadastro = cliente.getData();
        tipoCliente = "F";
        cnpj = cliente.getCNPJ();
        numInscricao = cliente.getInscricaoEstadual();
        String linhaCliente = (codigo+";"+nome+";"+endereco+";"+telefone+";"+formatador.format(dataCadastro)+";"+tipoCliente+";"+cnpj+";"+numInscricao);
        super.adicionaTextoArquivo(linhaCliente);
    }

    public List<ClientePF> getListaClientePF() { return listaClientesPF; }
    public List<ClientePJ> getListaClientePJ() { return listaClientesPJ; }
}
