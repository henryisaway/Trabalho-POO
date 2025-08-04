package main.model;

public class ClientePF extends Cliente{ //Pessoa Fisica
    private String cpf;

    public ClientePF(String nome,String endereco, String telefone, String cpf){
        super(nome,endereco, telefone);
        this.cpf = cpf;
    }
    public String getCPF(){
        return cpf;
    }
    public void setCPF(String cpf){ this.cpf = cpf;}

    public String infoClienetPF(){
        return "Codifo Identificador: "+super.getCodigoIndentificador()+"|Nome: "+super.getNome()+" |Endereco: "+super.getEndereco()+" |Telefone: "+super.getTelefone()+" |CPF: "+cpf;
    }
}
