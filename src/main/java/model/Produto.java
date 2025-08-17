package model;

public class Produto {
    private int codigoProduto;
    private String descricao;
    private int estoqueMin;
    private int qtEstoque;
    private double valorDeCusto;
    private int percentualLucro;
    private double valorVenda;
    static int proxCod = 1;

    public Produto(String descricao, int estoqueMin, int qtEstoque, double valorDeCusto, int percentualLucro) {
        this.descricao = descricao;
        this.estoqueMin = estoqueMin;
        this.qtEstoque = qtEstoque;
        this.valorDeCusto = valorDeCusto;
        this.percentualLucro = percentualLucro;

        codigoProduto = proxCod;
        proxCod++;

        double porcentagem = (double)percentualLucro / 100;
        valorVenda = valorDeCusto + (valorDeCusto * porcentagem);
    }
    public Produto(int codigoProduto, String descricao, int estoqueMin, int qtEstoque, double valorDeCusto, int percentualLucro) {
        this.descricao = descricao;
        this.estoqueMin = estoqueMin;
        this.qtEstoque = qtEstoque;
        this.valorDeCusto = valorDeCusto;
        this.percentualLucro = percentualLucro;
        this.codigoProduto = codigoProduto;
        proxCod = codigoProduto + 1;
        double porcentagem = (double)percentualLucro / 100;
        valorVenda = valorDeCusto + (valorDeCusto *porcentagem);
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public int getQtEstoque() {
        return qtEstoque;
    }

    public double getValorDeCusto() {
        return valorDeCusto;
    }

    public int getPercentualLucro() {
        return percentualLucro;
    }

    public double getValorVenda() {
        return valorVenda;
    }
    
    public double getLucro() {
        return valorVenda - valorDeCusto;
    }

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
        if(estoqueMin > qtEstoque);
        System.out.println("Tem menos produto em estoque do que a quantia minima!");
    }

    public void setQtEstoque(int qtEstoque) {
        this.qtEstoque = qtEstoque;
        if(this.qtEstoque < this.estoqueMin)System.out.println("Tem menos produto em estoque do que a quantia minima!");
    }

    public void setValorDeCusto(double valorDeCusto) {
        this.valorDeCusto = valorDeCusto;
        double porcentagem = (double)percentualLucro / 100;
        valorVenda = valorDeCusto + (valorDeCusto *porcentagem);
    }

    public void setPercentualLucro(int percentualLucro) {
        this.percentualLucro = percentualLucro;
        double porcentagem = (double)percentualLucro / 100;
        valorVenda = valorDeCusto + (valorDeCusto *porcentagem);
    }
    
    public String infoProduto(){
        return "Codigo do Produto: "+this.codigoProduto+" |Descricao: "+this.descricao+" |Estoque minimo: "+
                this.estoqueMin+" |Quantidade atual em estoque: "+this.qtEstoque+" |Valor de Custo: "+
                this.valorDeCusto+" |Percentual de lucro: "+this.percentualLucro;
    }

    public String toString() {
        return codigoProduto + ";" + descricao + ";" + valorDeCusto + ";" + getLucro(); 
    }
}
