package io;

import model.Fornecedor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoFornecedor extends Arquivo {
    private final List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

    int codigo;
    String nome, endereco, telefone, cnpj, pessoaContato;

    public ArquivoFornecedor(File arquivo) {
        super(arquivo);
    }

    public void pegaFornecedores() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo(); //Le todos conteudos do arquivo  como texto
        String[] linhas = conteudoArquivo.split("\\r?\\n");//Separa conteudo em linhas
        String[] linha;

        //Começa do indice 1 para pular o cabeçalho
        for(int i=1; i < linhas.length; i++) {
            if (linhas[i].trim().isEmpty()) {
                continue;
            }
            linha = linhas[i].split(";");//Divide a linha em campos, separados por ';'
            //Extrai dados comuns tanto pra 'F' e 'J'
            codigo = Integer.parseInt(linha[0]);
            nome = linha[1];
            endereco = linha[2];
            telefone = linha[3];
            cnpj = linha[4];
            pessoaContato = linha[5];

            listaFornecedor.add(new Fornecedor(nome, endereco, telefone, cnpj, pessoaContato, codigo));
        }
    }

    public void criaFornecedor (Fornecedor fornecedor) throws ArquivoException {
        codigo = fornecedor.getCodigoIndentificador();
        nome = fornecedor.getNomeEmpresa();
        endereco = fornecedor.getEndereco();
        telefone = fornecedor.getTelefone();
        cnpj = fornecedor.getCNPJ();
        pessoaContato = fornecedor.getPessoaContato();

        //Monta a linha em formato csv
        String linhaFornecedor = (codigo+";"+nome+";"+endereco+";"+telefone+";"+cnpj+";"+pessoaContato);
        
        try {
            File arquivo = new File("src/main/java/resources/cadastro_fornecedores.csv");

            // Verifica se precisa adicionar quebra de linha antes
            if (!super.terminaComQuebra(arquivo)) {
                linhaFornecedor = "\n" + linhaFornecedor;
            }

            // Adiciona a linha ao final do arquivo
            super.adicionaTextoArquivo(linhaFornecedor, true);

        } catch (IOException e) {
            throw new ArquivoException("Erro ao verificar quebra de linha no arquivo", e);
        }

        // Atualiza a lista de fornecedores
        listaFornecedor.add(new Fornecedor(nome,endereco,telefone,cnpj,pessoaContato,codigo));
    }

    public void editaFornecedor (Fornecedor fornecedor) throws ArquivoException {
        codigo = fornecedor.getCodigoIndentificador();
        nome = fornecedor.getNomeEmpresa();
        endereco = fornecedor.getEndereco();
        telefone = fornecedor.getTelefone();
        cnpj = fornecedor.getCNPJ();
        pessoaContato = fornecedor.getPessoaContato();

        //Monta a linha em formato csv,  ja com \n
        String linhaFornecedor = (codigo+";"+nome+";"+endereco+";"+telefone+";"+cnpj+";"+pessoaContato);
        super.editaItem(codigo, linhaFornecedor);
        
        for(int i = 0; i<listaFornecedor.size(); i++){
            if(listaFornecedor.get(i).getCodigoIndentificador() == codigo){
                listaFornecedor.set(i, new Fornecedor(nome,endereco,telefone,cnpj,pessoaContato,codigo));
            }
        }
    }

    public List<Fornecedor> getListaFornecedor() { return listaFornecedor; }
}
