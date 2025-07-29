package com.mycompany.loja_main;

import java.util.Scanner;

public class Loja_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        menuPrincipal();
        opcao = sc.nextInt();
        switch(opcao){
            case 0:
                System.out.println("Desligando...");
                break;
            case 1:
                gerarRelatorioMensal();
                break;
            case 2:
                controleContas(sc);
                break;
            case 3:
                registroVendas(sc);
                break;
            case 4:
                cadastro(sc);
                break;
            default:
                System.out.println("Opção invalida! digite novamente");
                break;
        }
        
    }
    public static void menuPrincipal(){
        System.out.println("[4] - Cadastro (Clientes, Fornecedores, Produtos");
        System.out.println("[3] - Registro de vendas");
        System.out.println("[2] - Controle de contas");
        System.out.println("[1] - Geração de relatórios mensais");
        System.out.println("[0] - Sair ");
        System.out.print("Digite uma opcão: ");
    }
    public static void menuCadastro(){
        System.out.println("[3] - Cadastrar Novo Cliente");
        System.out.println("[2] - Cadastrar Novo Fornecedor");
        System.out.println("[1] - Cadastrar Novo Produto");
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.print("Digite uma opcão: ");
    }
    public static void menuRegistroVendas(){
        System.out.println("[2] - Registrar Nova Venda");
        System.out.println("[1] - Listar Todas as Vendas");
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.print("Digite uma opcão: ");
    }
    public static void menuControleContas(){
        System.out.println("[2] - Visualizar Contas a Pagar (Fornecedores)");
        System.out.println("[1] - Visualizar Contas a Receber (Clientes)");
        System.out.println("[0] - Voltar ao Menu Principal");
        System.out.print("Digite uma opcão: ");
    }
    public static void gerarRelatorioMensal(){
        
    }
    public static void controleContas(Scanner sc){
        int opcao;
        menuControleContas();
        
    }
    public static void registroVendas(Scanner sc){
        int opcao;
        menuRegistroVendas();
    }
    public static void cadastro(Scanner sc){
       int opcao;
       menuCadastro();
    }
}
