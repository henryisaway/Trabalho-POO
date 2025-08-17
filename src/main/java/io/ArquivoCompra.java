package io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Compra;
import java.time.LocalDate;

public class ArquivoCompra extends Arquivo{ 
    private final List<Compra> listaCompras = new ArrayList<Compra>();

    int numeroNotaFiscal, quantidade, codigoFornecedor, codigoProduto;
    LocalDate dataCompra;
    
    public ArquivoCompra(File arquivo) {
        super(arquivo);
    }
    
    public void pegaCompras() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo();   //Lê todos conteudos do arquivo como texto
        String[] linhas = conteudoArquivo.split("\\r?\\n"); //Separa conteudo em linhas
        String[] linha;

        //Começa do indice 1 para pular o cabeçalho
        for(int i=1; i < linhas.length; i++) {
            if (linhas[i].trim().isEmpty()) {
                continue;
            }
            linha = linhas[i].split(";");
            numeroNotaFiscal = Integer.parseInt(linha[0]);
            codigoFornecedor = Integer.parseInt(linha[1]);
            dataCompra = LocalDate.parse(linha[2]);
            codigoProduto = Integer.parseInt(linha[3]);
            quantidade =Integer.parseInt(linha[4]);

            listaCompras.add(new Compra(numeroNotaFiscal, codigoFornecedor,codigoProduto, quantidade, dataCompra));
        }
    }

    public void criaCompra (Compra compra) throws ArquivoException {
        numeroNotaFiscal = compra.getNumeroNotaFiscal();
        codigoFornecedor = compra.getCodigoFornecedor();
        dataCompra = compra.getDataCompra();
        codigoProduto = compra.getCodigoProduto();
        quantidade = compra.getQuantidade();

        //Monta a linha em formato csv
        String linhaCompra = (numeroNotaFiscal+";"+codigoFornecedor+";"+dataCompra+";"+codigoProduto+";"+quantidade);
        //Adiciona a linha ao final do arquivo
        super.adicionaTextoArquivo(linhaCompra);
    }
    
    public List<Compra> getListaCompras() throws ArquivoException{ 
        pegaCompras();
        return listaCompras;
    }

    public void clearListaCompras(){
        listaCompras.clear();
    } 
}

