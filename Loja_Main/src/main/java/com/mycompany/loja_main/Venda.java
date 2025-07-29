package com.mycompany.loja_main;
import java.time.LocalDate;

public class Venda {
    private int codigoCliente;
    private LocalDate data;
    private int CodProduto;
    private int quantidade;
    //fazer classe meio de pagamento!!

    public Venda(int codigoCliente, int CodProduto, int quantidade) {
        this.codigoCliente = codigoCliente;
        this.CodProduto = CodProduto;
        this.quantidade = quantidade;
        data = LocalDate.now();
    }

    public int getCodigoCliente() {return codigoCliente;}
    public int getCodProduto() {return CodProduto;}
    public int getQuantidade() {return quantidade;}
    
}
