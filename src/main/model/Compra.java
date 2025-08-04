package main.model;

public class Compra {
    private int NumeroNotaFiscal;
    private int codigoFornecedor;
    private Data dataCompra;
    private int codigoProduto;
    private int quantidade;

    public Compra(int NumeroNotaFiscal, int codigoFornecedor, int codigoProduto, int quantidade, Data dataCompra){
        this.NumeroNotaFiscal = NumeroNotaFiscal;
        this.codigoFornecedor = codigoFornecedor;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
    }

    public int getNumeroNotaFiscal() { return NumeroNotaFiscal;}
    public int getCodigoFornecedor() {return codigoFornecedor;}
    public int getCodigoProduto() {return codigoProduto;}
    public int getQuantidade() {return quantidade;}

}
