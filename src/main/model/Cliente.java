package main.model;

public class Cliente {
    private int codigoIdentificador;
    private String nome;
    private String endereco;
    private String telefone;
    private Data dataCadastro;
    static int proxCodigo = 1;

    public Cliente(String nome,String endereco, String telefone, Data dataCadastro, boolean Criando){
        if(Criando){
        codigoIdentificador = proxCodigo;
        proxCodigo ++;
        }
        else{
            //Significa que so esta copiando de arquivo, nao e um proximo cliente
            this.codigoIdentificador= 0;
        }
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    public int getCodigoIndentificador(){return codigoIdentificador;}
    public String getNome() {return nome;}
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
    public Data getData() {return dataCadastro;}

    public void setNome(String nome){ this.nome = nome;}
    public void setEndereco(String endereco){ this.endereco = endereco;}

    public void setCodigoIdentificador(int codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }
    

}
