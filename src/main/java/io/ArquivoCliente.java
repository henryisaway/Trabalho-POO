package io;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ArquivoCliente extends Arquivo {
    public ArquivoCliente(File arquivo) {
        super(arquivo);
    }
    
    public void criaClientes() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo();
        String[] linhas = conteudoArquivo.split("\\r?\\n");
        String[] linha;
        int codigo, numEscricao;
        String nome, endereco, telefone, cpf, cnpj, tipoCliente;
        LocalDate dataCadastro;

        for(int i=1; i < linhas.length; i++) {
            linha = linhas[i].split(";");
            codigo = Integer.parseInt(linha[0]);
            nome = linha[1];
            endereco = linha[2];
            telefone = linha[3];
            dataCadastro = LocalDate.parse(linha[4]);
            tipoCliente = linha[5];
            numEscricao = Integer.parseInt(linha[7]);
            if(tipoCliente.equals("F")) {
                cpf = linha[6];
            } else {
                cnpj = linha[6];
            }
        }
    }

    public static void main(String[] args) throws ArquivoException {
        ArquivoCliente clientes = new ArquivoCliente(new File("/home/pilanza/Documents/github/Trabalho-POO/src/resources/cadastro_clientes.csv"));
        clientes.criaClientes();
    }
}
