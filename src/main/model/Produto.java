package main.model;

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
        valorVenda = valorDeCusto + (valorDeCusto *porcentagem);
    }

    public int getCodigoProduto() {return codigoProduto;}


    public void setDescricao(String descricao) {this.descricao = descricao;}

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
        if(estoqueMin > qtEstoque);
        System.out.println("Tem menos produto em estoque do que a quantia minima!");
    }

    public void setQtEstoque(int qtEstoque) {
        this.qtEstoque = qtEstoque;
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
}
