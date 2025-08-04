package main.model;
import java.time.LocalDate;

public class Cliente {
    private int codigoIdentificador;
    private String nome;
    private String endereco;
    private String telefone;
    private LocalDate data;
    static int proxCodigo = 1;

    public Cliente(String nome,String endereco, String telefone){
        codigoIdentificador = proxCodigo;
        proxCodigo ++;

        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        data = LocalDate.now();
    }

    public int getCodigoIndentificador(){return codigoIdentificador;}
    public String getNome() {return nome;}
    public String getEndereco() {return endereco;}
    public String getTelefone() {return telefone;}
    public LocalDate getData() {return data;}

    public void setNome(String nome){ this.nome = nome;}
    public void setEndereco(String endereco){ this.endereco = endereco;}
    public void setTelefone(String telefone){ this.telefone = telefone;}

}
