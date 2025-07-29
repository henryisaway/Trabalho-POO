package com.mycompany.loja_main;

public class ClientePJ extends Cliente{//Pesoa Juridica
    private String cnpj;
    private String inscricaoEstadual;
    
    public ClientePJ(String nome,String endereco, String telefone, String cnpj, String inscricaoEstadual){
        super(nome,endereco, telefone);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }
    
    public void getCNPJ(String cnpj){ this.cnpj = cnpj;}
    public void getInscricaoEstadual(String inscricaoEstadual){this.inscricaoEstadual = inscricaoEstadual;}
    
    public String infoClientePJ(){
        return "Codifo Identificador: "+super.getCodigoIndentificador()+"|Nome: "+super.getNome()+" |Endereco: "+super.getEndereco()+" |Telefone: "+super.getTelefone()+" |CNPJ: "+cnpj+" |Inscricao Estadual: "+inscricaoEstadual;
    }
}
