package main.model;

public class ClientePF extends Cliente{ //Pessoa Fisica
    private String cpf;

    public ClientePF(String nome,String endereco, String telefone,Data dataCadastro, boolean criando, String cpf){
        super(nome,endereco, telefone,dataCadastro,criando);
        this.cpf = cpf;
    }
    public String getCPF(){
        return cpf;
    }
    public void setCPF(String cpf){ this.cpf = cpf;}

    public String infoClienetPF(){
        return "Codigo Identificador: "+super.getCodigoIndentificador()+"|Nome: "+super.getNome()+" |Endereco: "+super.getEndereco()+" |Telefone: "+super.getTelefone()+" |CPF: "+cpf;
    }
}
