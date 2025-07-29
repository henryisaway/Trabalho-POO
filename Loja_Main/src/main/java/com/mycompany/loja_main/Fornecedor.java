package com.mycompany.loja_main;

public class Fornecedor {
    private int codigoIndentificador;
    private String nomeEmpresa;
    private String endereco;
    private String telefone;
    private String cnpj;
    private String pessoaContato;
    static int proxCodigo = 1;
    
    public Fornecedor(String nomeEmpresa, String endereco, String telefone, String cnpj, String pessoaContato){
        this.nomeEmpresa = nomeEmpresa;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.pessoaContato = pessoaContato;
    }
    
    public int getCodigoIndentificador(){return codigoIndentificador;}

    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}
    public void setPessoaContato(String pessoaContato) {this.pessoaContato = pessoaContato;}
    
    
}
