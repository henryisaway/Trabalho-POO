package model;

public class AhPagar {
    private String nomeFornecedor;
    private String cnpj;
    private String pessoaContato;
    private String telefone;
    private double valorTotal;

    public AhPagar(String nomeFornecedor, String cnpj, String pessoaContato, String telefone, double valorTotal) {
        this.nomeFornecedor = nomeFornecedor;
        this.cnpj = cnpj;
        this.pessoaContato = pessoaContato;
        this.telefone = telefone;
        this.valorTotal = valorTotal;
    }
    public String infoAhPagar() {
        return nomeFornecedor + ";" + cnpj + ";" + pessoaContato + ";" + telefone + ";" + String.format("%.2f", valorTotal);
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getPessoaContato() {
        return pessoaContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    
}
