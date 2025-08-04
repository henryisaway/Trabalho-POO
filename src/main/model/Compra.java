package main.model;
import java.util.Scanner;

public class Compra {
    private int NumeroNotaFiscal;
    private int codigoFornecedor;
    private Data dataCompra;
    private int codigoProduto;
    private int quantidade;

    public Compra(int NumeroNotaFiscal, int codigoFornecedor, int codigoProduto, int quantidade){
        Scanner scan = new Scanner(System.in);
        this.NumeroNotaFiscal = NumeroNotaFiscal;
        this.codigoFornecedor = codigoFornecedor;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        System.out.println("Digite a data da compra:");
        System.out.println("Dia: ");
        int dia = scan.nextInt();
        scan.nextLine();
        System.out.println("Mes: ");
        int mes= scan.nextInt();
        scan.nextLine();
        System.out.println("Ano: ");
        int ano = scan.nextInt();
        scan.nextLine();
        dataCompra = new Data(dia, mes, ano);
    }

    public int getNumeroNotaFiscal() { return NumeroNotaFiscal;}
    public int getCodigoFornecedor() {return codigoFornecedor;}
    public int getCodigoProduto() {return codigoProduto;}
    public int getQuantidade() {return quantidade;}

}
