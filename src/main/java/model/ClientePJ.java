package model;

import java.time.LocalDate;

public class ClientePJ extends Cliente{//Pesoa Juridica
    private String cnpj;
    private int inscricaoEstadual;

    public ClientePJ(String nome, String endereco, String telefone, String cnpj, int inscricaoEstadual, LocalDate dataCadastro){
        super(nome,endereco, telefone, dataCadastro);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public ClientePJ(String nome, String endereco, String telefone, String cnpj, int inscricaoEstadual, LocalDate dataCadastro, int codigo){
        super(nome,endereco, telefone, dataCadastro, codigo);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCNPJ(){ return cnpj; }
    public int getInscricaoEstadual(){ return inscricaoEstadual; }

    public void setCNPJ(String cnpj){ this.cnpj = cnpj;}
    public void setInscricaoEstadual(int inscricaoEstadual){this.inscricaoEstadual = inscricaoEstadual;}

    public String infoClientePJ(){
        return "Codifo Identificador: "+super.getCodigoIndentificador()+"|Nome: "+super.getNome()+" |Endereco: "+super.getEndereco()+" |Telefone: "+super.getTelefone()+" |CNPJ: "+cnpj+" |Inscricao Estadual: "+inscricaoEstadual;
    }
}
