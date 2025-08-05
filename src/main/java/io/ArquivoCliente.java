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

    public ArquivoCliente(File arquivo) {
        super(arquivo);
    }
    
    public void criaClientes() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo();
        String[] linhas = conteudoArquivo.split("\\r?\\n");
        String[] linha;
        int codigo, numInscricao;
        String nome, endereco, telefone, cpf, cnpj, tipoCliente;
        LocalDate dataCadastro;

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

    public List<ClientePF> getListaClientePF() { return listaClientesPF; }
    public List<ClientePJ> getListaClientePJ() { return listaClientesPJ; }
}
