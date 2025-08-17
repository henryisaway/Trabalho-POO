package io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ArquivoProduto extends Arquivo{
    
    private final List<Produto> listaProdutos = new ArrayList<Produto>();

    int codigo,estoqueMin, qtProduto, percentualLucro;
    String descricao;
    double valorCusto;
    
    public ArquivoProduto(File arquivo) {
        super(arquivo);
    }
    
    public void pegaProdutos() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo(); //Le todos conteudos do arquivo  como texto
        String[] linhas = conteudoArquivo.split("\\r?\\n");//Separa conteudo em linhas
        String[] linha;

        //Começa do indice 1 para pular o cabeçalho
        for(int i=1; i < linhas.length; i++) {
            if (linhas[i].trim().isEmpty()) {
                continue;
            }
            linha = linhas[i].split(";");//Divide a linha em campos, separados por ";"
            codigo = Integer.parseInt(linha[0]);
            descricao = linha[1];
            estoqueMin =Integer.parseInt(linha[2]);
            qtProduto = Integer.parseInt(linha[3]);
            valorCusto =Double.parseDouble(linha[4]);
            percentualLucro =Integer.parseInt(linha[5]); 

            listaProdutos.add(new Produto(codigo, descricao, estoqueMin, qtProduto,valorCusto,percentualLucro));
        }
    }

    public void criaProduto (Produto produto) throws ArquivoException {
        codigo = produto.getCodigoProduto();
        descricao = produto.getDescricao();
        estoqueMin = produto.getEstoqueMin();
        qtProduto = produto.getQtEstoque();
        percentualLucro = produto.getPercentualLucro();
        valorCusto = produto.getValorDeCusto();

        //Monta a linha em formato csv
        String linhaProduto = (codigo+";"+descricao+";"+estoqueMin+";"+qtProduto+";"+valorCusto+";"+percentualLucro);
        //Adiciona a linha ao final do arquivo
        super.adicionaTextoArquivo(linhaProduto);   
        listaProdutos.add(new Produto(codigo, descricao, estoqueMin, qtProduto,valorCusto, percentualLucro));
    }

    public void editaProduto (Produto produto) throws ArquivoException {
        codigo = produto.getCodigoProduto();
        descricao = produto.getDescricao();
        estoqueMin = produto.getEstoqueMin();
        qtProduto = produto.getQtEstoque();
        percentualLucro = produto.getPercentualLucro();
        valorCusto = produto.getValorDeCusto();

        //Monta a linha em formato csv,  ja com \n
        System.out.println("qt - "+qtProduto);
        String linhaProduto = (codigo+";"+descricao+";"+estoqueMin+";"+qtProduto+";"+valorCusto+";"+percentualLucro);
        super.editaItem(codigo, linhaProduto);
        
        for(int i = 0; i<listaProdutos.size(); i++){
            if(listaProdutos.get(i).getCodigoProduto() == codigo){
                listaProdutos.set(i, new Produto(codigo, descricao, estoqueMin, qtProduto,valorCusto, percentualLucro));
            }
        }
    }

    public List<Produto> getListaProdutos() { return listaProdutos; }

}
