package model;

import java.time.LocalDate;

public class ClientePF extends Cliente{ //Pessoa Fisica
    private String cpf;

    public ClientePF(String nome, String endereco, String telefone, LocalDate dataCadastro, String cpf){
        super(nome,endereco, telefone,dataCadastro);
        this.cpf = cpf;
    }

    public ClientePF(String nome, String endereco, String telefone, LocalDate dataCadastro, String cpf, int codigo){
        super(nome,endereco, telefone,dataCadastro, codigo);
        this.cpf = cpf;
    }

    public String getCPF(){
        return cpf;
    }
    public void setCPF(String cpf){ this.cpf = cpf;}

    public String infoClientPF(){
        return "Codigo Identificador: "+super.getCodigoIndentificador()+"|Nome: "+super.getNome()+" |Endereco: "+super.getEndereco()+" |Telefone: "+super.getTelefone()+" |CPF: "+cpf;
    }
}
