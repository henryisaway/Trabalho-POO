package com.mycompany.loja_main;
import java.util.Scanner;

public class MetodoPagamento {
    private int codigoPagamento;
    private String descricao;

    // Variáveis específicas por tipo de pagamento
    
    //Para quem pagou em chegue:
    private String numeroCheque;
    private String nomeBanco;
    private boolean aVista = true;
    private Data dataEmissao;
    
    
    //Para quem pagou em cartao :
    private String bandeira;
    private int ultimos4Digitos;
    private boolean cartaoDebito = true;
    private int qtParcelas;
    
    
    //Para quem pagou com ticket
    private String nomeTicket;
    private int ultimos4Ticket;
    
    //Para quem mandou anotar
    private int codCliente;

    public MetodoPagamento(int codigoPagamento) {
        Scanner scan = new Scanner (System.in);
        this.codigoPagamento = codigoPagamento;
        switch (this.codigoPagamento) {
            case 1:
                descricao = "Dinheiro";
                break;
            case 2:
                descricao = "Cheque";
                System.out.println("Qual o nome do Banco: ");
                this.nomeBanco = scan.nextLine();
                System.out.println("Qual o numero do cheque: ");
                this.numeroCheque = scan.nextLine();
                System.out.println("Foi pago a vista?(S ou N)");
                char resposta = scan.next().charAt(0);
                if(resposta != 'S' && resposta != 's')this.aVista = false;
                System.out.println("Digite a data da emissao:");
                System.out.println("Dia: ");
                int dia = scan.nextInt();
                scan.nextLine();
                System.out.println("Mes: ");
                int mes= scan.nextInt();
                scan.nextLine();
                System.out.println("Ano: ");
                int ano = scan.nextInt();
                scan.nextLine();
                this.dataEmissao = new Data(dia, mes, ano);
                break;
            case 3:
                descricao = "Cartão de Débito";
                System.out.println("Qual a bandeira do cartao: ");
                this.bandeira = scan.nextLine();
                System.out.println("Ultimos 4 digitos: ");
                this.ultimos4Digitos = scan.nextInt();
                scan.nextLine();
                break;
            case 4:
                descricao = "Cartão de Crédito";
                System.out.println("Qual a bandeira do cartao: ");
                this.bandeira = scan.nextLine();
                System.out.println("Ultimos 4 digitos: ");
                this.ultimos4Digitos = scan.nextInt();
                scan.nextLine();
                this.cartaoDebito = false;
                System.out.println("Em quantas vezes: ");
                this.qtParcelas = scan.nextInt();
                scan.nextLine();
                
                break;
            case 5:
                descricao = "Ticket Alimentação";
                System.out.println("Digite o nome do ticket: ");
                this.nomeTicket = scan.nextLine();
                break;
            case 6:
                descricao = "Fiado";
                System.out.println("Digite o codigo do Cliente: ");
                this.codCliente = scan.nextInt();
                scan.nextLine();
                break;
            default:
                System.out.println("Erro! Forma de pagamento Indevida...");
        }
    }
    
    
    
    //GET
    public int getCodigoPagamento() {
        return codigoPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public boolean isaVista() {
        return aVista;
    }

    public String getBandeira() {
        return bandeira;
    }

    public int getUltimos4Digitos() {
        return ultimos4Digitos;
    }

    public boolean isCartaoDebito() {
        return cartaoDebito;
    }

    public int getQtParcelas() {
        return qtParcelas;
    }

    public String getNomeTicket() {
        return nomeTicket;
    }

    public int getUltimos4Ticket() {
        return ultimos4Ticket;
    }

    public int getCodCliente() {
        return codCliente;
    }
    
    //SET

    public void setCodigoPagamento(int codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public void setaVista(boolean aVista) {
        this.aVista = aVista;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public void setUltimos4Digitos(int ultimos4Digitos) {
        this.ultimos4Digitos = ultimos4Digitos;
    }

    public void setCartaoDebito(boolean cartaoDebito) {
        this.cartaoDebito = cartaoDebito;
    }

    public void setQtParcelas(int qtParcelas) {
        this.qtParcelas = qtParcelas;
    }

    public void setNomeTicket(String nomeTicket) {
        this.nomeTicket = nomeTicket;
    }

    public void setUltimos4Ticket(int ultimos4Ticket) {
        this.ultimos4Ticket = ultimos4Ticket;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    public void informaMetodoPagamento() {
        System.out.println("=== Informacoes do Metodo de Pagamento ===");
        System.out.println("Codigo: " + codigoPagamento);
        System.out.println("Descricao: " + descricao);

        switch (codigoPagamento) {
            case 1: // Dinheiro
                System.out.println("Pagamento realizado em dinheiro.");
                break;

            case 2: // Cheque
                System.out.println("Banco: " + nomeBanco);
                System.out.println("Numero do cheque: " + numeroCheque);
                System.out.println("Pagamento a vista: " + (aVista ? "Sim" : "Nao"));
                break;

            case 3: // Cartao de Debito
                System.out.println("Tipo: Cartao de Debito");
                System.out.println("Bandeira: " + bandeira);
                System.out.println("Ultimos 4 digitos: " + ultimos4Digitos);
                break;

            case 4: // Cartao de Credito
                System.out.println("Tipo: Cartao de Credito");
                System.out.println("Bandeira: " + bandeira);
                System.out.println("Ultimos 4 digitos: " + ultimos4Digitos);
                System.out.println("Parcelas: " + qtParcelas);
                break;

            case 5: // Ticket Alimentacao
                System.out.println("Ticket: " + nomeTicket);
                break;

            case 6: // Fiado
                System.out.println("Codigo do cliente: " + codCliente);
                break;

            default:
                System.out.println("Forma de pagamento nao reconhecida.");
        }
    }
}