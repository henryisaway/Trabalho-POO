package io;

import model.ClientePF;
import model.ClientePJ;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Classe responsável por ler e gravar objetos de ClientePF e Cliente PJ em arquivos
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
    
    //Lê o conteudo do arquivo e preenche as listas de ClientePF e Cliente PJ com os dados lidos
    public void pegaClientes() throws ArquivoException {
        String conteudoArquivo = super.getTextoArquivo(); //Le todos conteudos do arquivo  como texto
        String[] linhas = conteudoArquivo.split("\\r?\\n");//Separa conteudo em linhas
        String[] linha;

        //Começa do indice 1 para pular o cabeçalho
        for(int i=1; i < linhas.length; i++) {
            linha = linhas[i].split(";");//Divide a linha em campos, separados por ';'
            //Extrai dados comuns tanto pra 'F' e 'J'
            codigo = Integer.parseInt(linha[0]);
            nome = linha[1];
            endereco = linha[2];
            telefone = linha[3];
            dataCadastro = LocalDate.parse(linha[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            tipoCliente = linha[5];
            
            //Pega o dado que cada tipo unico possui
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
    //Metodo que adicona um novo ClientePF ao arquivo
    public void criaCliente (ClientePF cliente) throws ArquivoException {
        //Extrai dados Cliente PF
        codigo = cliente.getCodigoIndentificador();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        dataCadastro = cliente.getData();

        tipoCliente = "F";
        cpf = cliente.getCPF();
        //Monta a linha em formato csv,  ja com \n
        String linhaCliente = ("\n"+codigo+";"+nome+";"+endereco+";"+telefone+";"+formatador.format(dataCadastro)+";"+tipoCliente+";"+cpf);
        //Adiciona a linha ao final do arquivo
        super.adicionaTextoArquivo(linhaCliente);
    }
    //Metodo que adicona um novo ClientePJ ao arquivo
    public void criaCliente (ClientePJ cliente) throws ArquivoException {
        //Extrai dados Cliente PJ
        codigo = cliente.getCodigoIndentificador();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        dataCadastro = cliente.getData();
        tipoCliente = "J";
        cnpj = cliente.getCNPJ();
        numInscricao = cliente.getInscricaoEstadual();
        //Monta a linha em formato csv,  ja com \n
        String linhaCliente = ("\n"+codigo+";"+nome+";"+endereco+";"+telefone+";"+formatador.format(dataCadastro)+";"+tipoCliente+";"+cnpj+";"+numInscricao);
        //Adiciona a linha ao final do arquivo
        super.adicionaTextoArquivo(linhaCliente);
    }

    public void editaCliente (ClientePF cliente) throws ArquivoException {
        codigo = cliente.getCodigoIndentificador();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        dataCadastro = cliente.getData();
        tipoCliente = "F";
        cpf = cliente.getCPF();
        //Monta a linha em formato csv,  ja com \n
        String linhaCliente = (codigo+";"+nome+";"+endereco+";"+telefone+";"+formatador.format(dataCadastro)+";"+tipoCliente+";"+cpf);
        super.editaItem(codigo, linhaCliente);
    }

    public void editaCliente (ClientePJ cliente) throws ArquivoException {
        codigo = cliente.getCodigoIndentificador();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
        telefone = cliente.getTelefone();
        dataCadastro = cliente.getData();
        tipoCliente = "J";
        cnpj = cliente.getCNPJ();
        numInscricao = cliente.getInscricaoEstadual();
        //Monta a linha em formato csv,  ja com \n
        String linhaCliente = (codigo+";"+nome+";"+endereco+";"+telefone+";"+formatador.format(dataCadastro)+";"+tipoCliente+";"+cnpj+";"+numInscricao);
        //Adiciona a linha ao final do arquivo
        super.editaItem(codigo, linhaCliente);
    }
 
    public List<ClientePF> getListaClientePF() { return listaClientesPF; }
    public List<ClientePJ> getListaClientePJ() { return listaClientesPJ; }
}
