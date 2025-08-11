package model;
import java.time.LocalDate;

public class Venda {
    private int codigoCliente;
    private LocalDate dataVenda;
    private int CodProduto;
    private int quantidade;
    private double valor;
    private MetodoPagamento metodoPagamento;

    public Venda(int CodProduto, int quantidade, LocalDate dataVenda, MetodoPagamento metodoPagamento, int codigoCliente) {
        this.codigoCliente = codigoCliente;
        this.CodProduto = CodProduto;
        this.quantidade = quantidade;
        this.dataVenda = dataVenda;
        this.metodoPagamento = metodoPagamento;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }
    
    
    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public int getCodProduto() {
        return CodProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }
    
    public void setCodProduto(int CodProduto) {
        this.CodProduto = CodProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String infoVenda() {
        if(codigoCliente == 0){
            return "Codigo do cliente: ... |Data da venda:" + dataVenda + " |Codigo do produto: " + CodProduto + " |Quantidade: " + quantidade +
                " |valor: " + valor + " |Metodo de pagamento: " + metodoPagamento;
        }
        else{
            return "Codigo do cliente:" + codigoCliente + " |Data da venda:" + dataVenda + " |Codigo do produto: " + CodProduto + " |Quantidade: " + quantidade +
                " |valor: " + valor + " |Metodo de pagamento: " + metodoPagamento;
        }
    }
    
    public String infoVendaFiado() {
        return "Codigo do cliente:" + codigoCliente + " |Data da venda:" + dataVenda + " |Codigo do produto: " + CodProduto + " |Quantidade: " + quantidade +
                " |valor: " + valor + " |Metodo de pagamento: " + metodoPagamento;
    }
    

}
