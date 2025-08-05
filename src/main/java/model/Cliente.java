package model;

import java.time.LocalDate;

public class Cliente {
    private int codigo;
    private String nome, endereco, telefone;
    private LocalDate dataCadastro;
    private static int codigoAtual = 0;

    public Cliente(String nome,String endereco, String telefone, LocalDate dataCadastro){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.codigo = codigoAtual + 1;
    }

    public Cliente(String nome,String endereco, String telefone, LocalDate dataCadastro, int codigo){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.codigo = codigo;
    }

    public int getCodigoIndentificador(){return codigo;}
    public String getNome() {return nome;}
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
    public LocalDate getData() {return dataCadastro;}

    public void setNome(String nome){ this.nome = nome;}
    public void setEndereco(String endereco){ this.endereco = endereco;}
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
}
