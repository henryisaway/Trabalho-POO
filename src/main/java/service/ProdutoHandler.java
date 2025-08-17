package service;

import io.ArquivoException;
import io.ArquivoProduto;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import model.Produto;

public class ProdutoHandler {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArquivoProduto arquivoProduto = new ArquivoProduto(new File("src/main/java/resources/cadastro_produtos.csv"));
    private static List<Produto> produtos;

    public static void carregaProduto() throws ArquivoException {
        arquivoProduto.pegaProdutos();
    }

    public static void cadastraProduto() throws ArquivoException {
        String descricao;
        int estoqueMin, qtProdutos, percentualLucro;
        double valorCusto;

        System.out.print("Digite a descricao do produto: ");
        descricao = sc.nextLine();
        System.out.print("Digite o estoque minimo para este produto: ");
        estoqueMin = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite a quantidade do produto em estoque: ");
        qtProdutos = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o percentualLucro: ");
        percentualLucro = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o valor pago pela empresa para a compra do produto: ");
        valorCusto = sc.nextDouble();
        sc.nextLine();
        
        Produto novoProduto = new Produto(descricao, estoqueMin, qtProdutos, valorCusto, percentualLucro);
        boolean cadastrou = CompraHandler.cadastraCompra(novoProduto.getCodigoProduto(), qtProdutos);
        if(cadastrou  == false)return;
        arquivoProduto.criaProduto(novoProduto);
    }

    public static void listarProdutos() throws ArquivoException{
        produtos = arquivoProduto.getListaProdutos();

        System.out.println("Produtos: ");
        for (Produto produto:produtos) {
            System.out.println(produto.infoProduto());
        }
    }

    public static Produto buscarProduto(int codigo) throws ArquivoException{
        produtos = arquivoProduto.getListaProdutos();

        for (Produto produto:produtos) {
            if(produto.getCodigoProduto() == codigo) { return produto;}
        }

        return null;
    }

    public static void removeProduto() throws ArquivoException{
        int codigo;
        produtos = arquivoProduto.getListaProdutos();
        System.out.print("Digite o codigo do produto que deseja remover: ");
        codigo = sc.nextInt();
        sc.nextLine();

        produtos.removeIf(produto -> produto.getCodigoProduto() == codigo);

        arquivoProduto.removeItem(codigo);
        System.out.println("Produto deletado com sucesso!");
    }

    public static void editaProduto() throws ArquivoException {
        int codigo;
        produtos = arquivoProduto.getListaProdutos();
        System.out.print("Digite o codigo do produto que deseja editar: ");
        codigo = sc.nextInt();
        sc.nextLine();
        for (Produto produto: produtos) {
            if (produto.getCodigoProduto() == codigo) {
                edicaoProduto(produto);
                return;
            }
        }
        System.out.println("Produto não encontrado");
    }

    public static void edicaoProduto(Produto produto) throws ArquivoException {
        String entrada;
        int estoqueMin, qtProdutos, percentualLucro;
        double valorCusto;
        
        System.out.println("Caso não deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Descricao -> ["+produto.getDescricao()+"] ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) produto.setDescricao(entrada);
        
        System.out.println("Caso não deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Estoque minimo -> ["+produto.getEstoqueMin()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()){
            estoqueMin = Integer.parseInt(entrada);
            produto.setEstoqueMin(estoqueMin);
        }
        
        System.out.println("Caso não deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Quantida de Produtos em estoque -> ["+produto.getQtEstoque()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()){
            qtProdutos = Integer.parseInt(entrada);//qtprodutos = quantiaAntiga + novaFrota
            if(qtProdutos > produto.getQtEstoque()){
                boolean editado = CompraHandler.cadastraCompra(produto.getCodigoProduto(),qtProdutos - produto.getQtEstoque());//(qtProdutos - produto.getQtEstoque) -> Vai nos dizer a nova quantia comprada...
                if(editado == false){
                    System.out.println("Retornando ao menu principal!");
                    return;
                }
                produto.setQtEstoque(qtProdutos);
                System.out.println("Compra de novos produtos registrada...");
            }
            else{
                produto.setQtEstoque(qtProdutos);
                System.out.println("Produtos retirados do estoque com sucesso!");
            }
        }
        
        System.out.println("Caso não deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Percentual de lucro ->["+produto.getPercentualLucro()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()){
            percentualLucro = Integer.parseInt(entrada);
            produto.setPercentualLucro(percentualLucro);
        }
        
        System.out.println("Caso não deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Valor pago pela empresa no produto -> ["+produto.getValorDeCusto()+"]: ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()){
            valorCusto = Double.parseDouble(entrada);
            produto.setValorDeCusto(valorCusto);
        }

        arquivoProduto.editaProduto(produto);
        System.out.println("Produto editado com sucesso!");
    }
    
    public static int vendeuProduto(int codigoProduto,int quantidade)throws ArquivoException{
        produtos = arquivoProduto.getListaProdutos();
        for(Produto produto: produtos){

            if(produto.getCodigoProduto() == codigoProduto){
                if(produto.getQtEstoque() == 0){
                    System.out.println("Não temos esse produto em estoque!");
                    return 0;
                }
                else if(produto.getQtEstoque() <quantidade && produto.getQtEstoque() > 0){
                    do{
                    System.out.print("Temos apenas "+produto.getQtEstoque()+ " deste produto em estoque!\n"
                            + "Deseja comprar uma quantia menor...\n"
                            + "1 - Sim\n"
                            + "2 - Nao\n"
                            + "Digite sua opcao: ");
                    int opcao = sc.nextInt();
                    sc.nextLine();
                    if(opcao == 1){
                        System.out.print("Digite a nova quantia: ");
                        quantidade = sc.nextInt();
                        if(quantidade < produto.getQtEstoque()){
                            produto.setQtEstoque(produto.getQtEstoque() - quantidade);
                            arquivoProduto.editaProduto(produto);
                            break;
                        }
                    }
                    }while(true);
                }
                else{
                    produto.setQtEstoque(produto.getQtEstoque() - quantidade);
                    arquivoProduto.editaProduto(produto);
                    break;
                }
            }
        }
        return quantidade;
    }
}