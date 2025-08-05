package io;

import java.io.*;
import java.util.*;

public class ArquivoCliente extends Arquivo {
    public ArquivoCliente(File arquivo) {
        super(arquivo);
    }
    
    public void criaClientes() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo();
        String[] linhas = conteudoArquivo.split("\\r?\\n");
        for(String linha:linhas) {
            System.out.println(linha);
        }
    }

    public static void main(String[] args) throws ArquivoException {
        ArquivoCliente clientes = new ArquivoCliente(new File("/home/pietro/Documents/github/Trabalho-POO/src/resources/cadastro_clientes.csv"));
        clientes.criaClientes();
    }
}
