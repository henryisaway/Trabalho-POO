package model;

public class Fornecedor {
    private int codigoIndentificador;
    private String nomeEmpresa;
    private String endereco;
    private String telefone;
    private String cnpj;
    private String pessoaContato;
    private static int codigoAtual = 0;

    public Fornecedor(String nomeEmpresa, String endereco, String telefone, String cnpj, String pessoaContato){
        this.nomeEmpresa = nomeEmpresa;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.pessoaContato = pessoaContato;
        codigoAtual += 1;
        this.codigoIndentificador = codigoAtual;
    }

    public Fornecedor(String nomeEmpresa, String endereco, String telefone, String cnpj, String pessoaContato, int codigoIndentificador){
        this.nomeEmpresa = nomeEmpresa;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.pessoaContato = pessoaContato;
        codigoAtual = codigoIndentificador;
        this.codigoIndentificador = codigoIndentificador;
    }

    public int getCodigoIndentificador(){return codigoIndentificador;}
    public String getTelefone() {return telefone;}
    public String getEndereco() {return endereco;}
    public String getCNPJ() {return cnpj;}
    public String getNomeEmpresa() {return nomeEmpresa;}
    public String getPessoaContato() {return pessoaContato;}
    public String infoFornecedor(){
        return "Codigo Identificador: "+codigoIndentificador+"|Nome: "+nomeEmpresa+" |Endereco: "+endereco+" |Telefone: "+telefone+" |CNPJ: "+cnpj+" |Pessoa de Contato:" +pessoaContato;
    }

    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setCNPJ(String cnpj) {this.cnpj = cnpj;}
    public void setPessoaContato(String pessoaContato) {this.pessoaContato = pessoaContato;}


}
