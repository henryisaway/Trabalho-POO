package model;
import java.time.LocalDate;

public class Compra {
    private int NumeroNotaFiscal;
    private int codigoFornecedor;
    private  LocalDate dataCompra;
    private int codigoProduto;
    private int quantidade;

    public Compra(int NumeroNotaFiscal, int codigoFornecedor, int codigoProduto, int quantidade, LocalDate dataCompra){
        this.NumeroNotaFiscal = NumeroNotaFiscal;
        this.codigoFornecedor = codigoFornecedor;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
    }

    public String infoCompra() {
        return "Numero da nota fiscal: " + NumeroNotaFiscal + " |Codigo do fornecedor: " + codigoFornecedor + " |Data da compra: " + dataCompra + " |Codigo de produto: " + codigoProduto + " |Quantidade: " + quantidade;
    }
    
    

    public int getNumeroNotaFiscal() { return NumeroNotaFiscal;}
    public int getCodigoFornecedor() {return codigoFornecedor;}
    public int getCodigoProduto() {return codigoProduto;}
    public int getQuantidade() {return quantidade;}

    
    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setNumeroNotaFiscal(int NumeroNotaFiscal) {
        this.NumeroNotaFiscal = NumeroNotaFiscal;
    }

    public void setCodigoFornecedor(int codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    

}
