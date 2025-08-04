package main.model;
import main.service.MetodoPagamento;

import java.util.Scanner;

public class Venda {
    private Data dataVenda;
    private int CodProduto;
    private int quantidade;
    private double valor;
    private MetodoPagamento metodoPagamento;

    public Venda(int CodProduto, int quantidade) {
        Scanner scan = new Scanner(System.in);
        this.CodProduto = CodProduto;
        this.quantidade = quantidade;
        System.out.println("Digite a data da venda:");
        System.out.println("Dia: ");
        int dia = scan.nextInt();
        scan.nextLine();
        System.out.println("Mes: ");
        int mes= scan.nextInt();
        scan.nextLine();
        System.out.println("Ano: ");
        int ano = scan.nextInt();
        scan.nextLine();
        dataVenda = new Data(dia, mes, ano);
    }
    public int getCodProduto() {return CodProduto;}
    public int getQuantidade() {return quantidade;}

}
