package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Venda;
import model.MetodoPagamento;
import java.time.LocalDate;

public class ArquivoVenda extends Arquivo{
    
    private final List<Venda> listaVendas = new ArrayList<Venda>();
    private final List<Venda> listaVendasFiado = new ArrayList<Venda>();
    
    int codigoCliente, codigoProduto, quantidade;
    MetodoPagamento modoPagamento;
    LocalDate dataVenda;
    
    public ArquivoVenda(File arquivo) {
        super(arquivo);
    }
    
    public void pegaVendas() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo();   //Le todos conteudos do arquivo como texto
        String[] linhas = conteudoArquivo.split("\\r?\\n"); //Separa conteudo em linhas
        String[] linha;

        //Começa do indice 1 para pular o cabeçalho
        for(int i=1; i < linhas.length; i++) {

            // Henrique: Não sei o porquê desse println, alguém deve ter colocado como debug
            // System.out.println("1");

            linha = linhas[i].split(";");    // Divide a linha em campos, separados por ';'
            
            if(linha[0].equals(" ")) codigoCliente = 0;  // Caso nao seja fiado...
            else codigoCliente = Integer.parseInt(linha[0]);
            
            dataVenda = LocalDate.parse(linha[1]);
            codigoProduto =Integer.parseInt(linha[2]);
            quantidade = Integer.parseInt(linha[3]);
            
            // Henrique: Não sei o porquê desse println, alguém deve ter colocado como debug
            // System.out.println(linha[4]);

            modoPagamento = MetodoPagamento.fromCodigo(linha[4]);

            listaVendas.add(new Venda(codigoProduto,quantidade, dataVenda, modoPagamento, codigoCliente));
        }
    }
    public  void pegaVendasFiado() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo(); // Lê todos conteudos do arquivo  como texto
        String[] linhas = conteudoArquivo.split("\\r?\\n");//Separa conteudo em linhas
        String[] linha;

        //Começa do indice 1 para pular o cabeçalho
        for(int i=1; i < linhas.length; i++) {
            if (linhas[i].trim().isEmpty()) {
                continue;
            }
            linha = linhas[i].split(";");//Divide a linha em campos, separados por ';'
            
            if(!linha[0].equals(" ")){
                codigoCliente = Integer.parseInt(linha[0]);
                dataVenda = LocalDate.parse(linha[1]);
                codigoProduto =Integer.parseInt(linha[2]);
                quantidade = Integer.parseInt(linha[3]);
                modoPagamento = MetodoPagamento.fromCodigo(linha[4]);
                listaVendasFiado.add(new Venda(codigoProduto,quantidade, dataVenda, modoPagamento, codigoCliente));
            }//Caso seja fiado...
        }
    }
    

    public void criaVenda (Venda venda) throws ArquivoException {
        
        codigoCliente = venda.getCodigoCliente();
        dataVenda = venda.getDataVenda();
        codigoProduto = venda.getCodProduto();
        quantidade = venda.getQuantidade();
        modoPagamento = venda.getMetodoPagamento();

        //Monta a linha em formato csv
        String linhaVenda;
        if(codigoCliente == 0){ 
            linhaVenda = (" ;"+dataVenda+";"+codigoProduto+";"+quantidade+";"+modoPagamento.getCodigo());
        }
        else linhaVenda = (codigoCliente+";"+dataVenda+";"+codigoProduto+";"+quantidade+";"+modoPagamento.getCodigo());
        //Adiciona a linha ao final do arquivo//Adiciona a linha ao final do arquivo
        try {
            File arquivo = new File("src/main/java/resources/registro_vendas.csv");

            // Verifica se precisa adicionar quebra de linha antes
            if (!super.terminaComQuebra(arquivo)) {
                linhaVenda = "\n" + linhaVenda;
            }

            // Adiciona a linha ao final do arquivo
            super.adicionaTextoArquivo(linhaVenda, true);

        } catch (IOException e) {
            throw new ArquivoException("Erro ao verificar quebra de linha no arquivo", e);
        }

    }
    
    public void reniciaVendasMes () throws ArquivoException {

        String linhaVenda = ("Codigo do Cliente;Data de venda;Codigo do produto;Quantidade;Modo de pagamento");
        //Sobrescreve o arquivo...
        super.adicionaTextoArquivo(linhaVenda,false);
    }
    
    public List<Venda> getListaVendas() throws ArquivoException { 
        pegaVendas();
        return listaVendas; }
    public List<Venda> getListaVendasFiado() throws ArquivoException{ 
        pegaVendasFiado();
        return listaVendasFiado; }
    
    public void clearListaVendas(){
        listaVendas.clear();
        listaVendasFiado.clear();
    }
}
