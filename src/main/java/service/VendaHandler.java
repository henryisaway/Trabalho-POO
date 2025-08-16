package service;

import io.ArquivoException;
import io.ArquivoVenda;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.MetodoPagamento;
import model.Venda;
import model.Produto;
import model.Cliente;

public class VendaHandler {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArquivoVenda arquivoVenda = new ArquivoVenda(new File("src/main/java/resources/registro_vendas.csv"));
    private static List<Venda> vendas;

    public static void carregaVendas() throws ArquivoException {
        arquivoVenda.pegaVendas();
    }

    public static void cadastraVenda() throws ArquivoException {
        int codigoCliente, codigoProduto, quantidade;
        MetodoPagamento modoPagamento;
        LocalDate dataVenda;

        dataVenda = LocalDate.now();
        System.out.print("Digite o codigo do produto vendido: ");
        codigoProduto = sc.nextInt();
        sc.nextLine();
        Produto buscaProduto = ProdutoHandler.buscarProduto(codigoProduto);
        if(buscaProduto == null){
            System.out.println("Codigo de produto nao existente no estoque!");
            return;
        }
        System.out.print("Digite a quantidade de produto: ");
        quantidade = sc.nextInt();
        sc.nextLine();
        quantidade = ProdutoHandler.vendeuProduto(codigoProduto, quantidade);
        if(quantidade == 0){
            System.out.println("Voltando ao menu principal...");
            return;
        }
        while(true){
            MetodoPagamento.listarMetodos();
            System.out.print("Digite o metodo de pagamento: ");
            modoPagamento = MetodoPagamento.fromCodigo(sc.nextLine());
            if(modoPagamento != null)break;
            else{
                System.out.println("Metodo de pagamento nao disponivel!");
                System.out.print("Deseja escolher outro metodo:\n "
                            + "1 - Sim\n"
                            + "2 - Nao\n"
                            + "Digite sua opcao: ");
                int opcao = sc.nextInt();
                sc.nextLine();
                if(opcao != 1){
                    System.out.println("Retornando ao menu principal!");
                    return;
                }
            }
        }
        if(modoPagamento == MetodoPagamento.FIADO){
            System.out.print("Digite o Codigo do Cliente: ");
            codigoCliente = sc.nextInt();
            sc.nextLine();
            Cliente buscaCliente = ClienteHandler.buscarCliente(codigoCliente);
            if(buscaCliente == null){
                System.out.println("Cadastre o cliente antes de vender fiado!");
                return;
            }
            arquivoVenda.criaVenda(new Venda(codigoProduto,quantidade, dataVenda, modoPagamento, codigoCliente));
        }
        else arquivoVenda.criaVenda(new Venda(codigoProduto,quantidade, dataVenda, modoPagamento, 0)); //CodigoCliente = 0, pois não foi fiado!!
    }

    public static void listarVendas() throws ArquivoException{
        vendas = arquivoVenda.getListaVendas();

        System.out.println("Vendas: ");
        for (Venda venda:vendas) {
            System.out.println(venda.infoVenda());
        }
        vendas.clear();
        arquivoVenda.clearListaVendas();
    }
    
    public static void listarVendasFiado() throws ArquivoException{
        vendas = arquivoVenda.getListaVendasFiado();

        System.out.println("Vendas Fiado: ");
        for (Venda venda:vendas) {
            System.out.println(venda.infoVenda());
        }
        vendas.clear();
        arquivoVenda.clearListaVendas();
        
    }
    
    public static List<Venda> getVendas() throws ArquivoException{
        return arquivoVenda.getListaVendas();
    }
    
    public static List<Venda> getVendasFiado()throws ArquivoException{
        return arquivoVenda.getListaVendasFiado();
    }
}

