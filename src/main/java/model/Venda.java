package model;

public class Venda {
    private Data dataVenda;
    private int CodProduto;
    private int quantidade;
    private double valor;
    private MetodoPagamento metodoPagamento;

    public Venda(int CodProduto, int quantidade, Data dataVenda, MetodoPagamento metodoPagamento) {
        this.CodProduto = CodProduto;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
        this.metodoPagamento = metodoPagamento;
    }
    public int getCodProduto() {return CodProduto;}
    public int getQuantidade() {return quantidade;}

}
