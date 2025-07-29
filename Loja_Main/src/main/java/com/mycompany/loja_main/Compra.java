package com.mycompany.loja_main;
import java.time.LocalDate;

public class Compra {
    private int NumeroNotaFiscal;
    private int codigoFornecedor;
    private LocalDate data;
    private int codigoProduto;
    private int quantidade;
    //Fazer classe Meio pagamento!!
    
    public Compra(int NumeroNotaFiscal, int codigoFornecedor, int codigoProduto, int quantidade){
        this.NumeroNotaFiscal = NumeroNotaFiscal;
        this.codigoFornecedor = codigoFornecedor;
        data = LocalDate.now();
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public int getNumeroNotaFiscal() { return NumeroNotaFiscal;}
    public int getCodigoFornecedor() {return codigoFornecedor;}
    public int getCodigoProduto() {return codigoProduto;}
    public int getQuantidade() {return quantidade;}
    
}
