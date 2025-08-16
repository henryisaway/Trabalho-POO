package model;
import java.time.LocalDate;

public class AhReceber {
    private String nomeDoCliente;
    private String tipoCliente;
    private String CPF_CNPJ;
    private String telefone;
    private LocalDate dataCadastro;
    private double valorAhReceber;
    
    public AhReceber(String nomeCliente, String tipoCliente,String CPF_CNPJ,String telefone,LocalDate dataCadastro, double valorAhReceber){
        this.nomeDoCliente = nomeCliente;
        this.tipoCliente = tipoCliente;
        this.CPF_CNPJ = CPF_CNPJ;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.valorAhReceber = valorAhReceber;
    }
    
    public void somarValor(double valor) {
        this.valorAhReceber += valor;
    }
    
    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getCPF_CNPJ() {
        return CPF_CNPJ;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public double getValorAhReceber() {
        return valorAhReceber;
    }
    
    public String infoAhReceber(){
        return nomeDoCliente+";"+tipoCliente+";"+CPF_CNPJ+";"+telefone+";"+dataCadastro+";"+valorAhReceber;
    }
    
}
