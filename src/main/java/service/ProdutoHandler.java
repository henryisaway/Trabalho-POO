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

    public static List<Produto> getProdutos() {
        
        return arquivoProduto.getListaProdutos();
    }

    public static void carregaProduto() throws ArquivoException {
        arquivoProduto.pegaProdutos();
    }

    public static void cadastraProduto() throws ArquivoException {
        String descricao;
        int estoqueMin, qtProdutos, percentualLucro;
        double valorCusto;
        String entrada;
        
        descricao = Leitor.lerString("Digite a descricao do produto: ");
        estoqueMin = Leitor.lerInteiro("Digite o estoque minimo para este produto: ");
        qtProdutos = Leitor.lerInteiro("Digite a quantidade do produto em estoque: ");
        percentualLucro = Leitor.lerInteiro("Digite o percentualLucro: ");
        valorCusto = Leitor.lerDouble("Digite o valor pago pela empresa para a compra do produto: ");
        
        Produto novoProduto = new Produto(descricao, estoqueMin, qtProdutos, valorCusto, percentualLucro);
        boolean cadastrou = CompraHandler.cadastraCompra(novoProduto.getCodigoProduto(), qtProdutos);
        if(cadastrou  == false){
            return;
        }
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
        
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        System.out.print("Descricao -> ["+produto.getDescricao()+"] ");
        entrada = sc.nextLine();
        if(!entrada.trim().isEmpty()) produto.setDescricao(entrada);
        
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        while (true) {
            System.out.print("Estoque minimo -> ["+produto.getEstoqueMin()+"]: ");
            entrada = sc.nextLine();
            if(!entrada.trim().isEmpty()){
                try {
                    estoqueMin = Integer.parseInt(entrada);
                    produto.setEstoqueMin(estoqueMin);
                    break; // Entrada válida, sai do loop
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido! Digite um numero inteiro ou pressione Enter para manter o valor atual.");
                }
            }
            else break;
        }
        
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        while (true) {
            System.out.print("Quantida de Produtos em estoque -> ["+produto.getQtEstoque()+"]: ");
            entrada = sc.nextLine();
            if(!entrada.trim().isEmpty()){
                try {
                    qtProdutos = Integer.parseInt(entrada);
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
                    break; // Entrada válida, sai do loop
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido! Digite um numero inteiro ou pressione Enter para manter o valor atual.");
                }
            }
            else break;
        }
        
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        while (true) {
            System.out.print("Percentual de lucro ->["+produto.getPercentualLucro()+"]: ");
            entrada = sc.nextLine();
            if(!entrada.trim().isEmpty()){
                try {
                    percentualLucro = Integer.parseInt(entrada);
                    produto.setPercentualLucro(percentualLucro);
                    break; // Entrada válida, sai do loop
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido! Digite um numero inteiro ou pressione Enter para manter o valor atual.");
                }
            }
            else break;
        }
        System.out.println("Caso nao deseje editar o campo abaixo, apenas pressione enter");
        while (true) {
            System.out.print("Valor pago pela empresa no produto -> ["+produto.getValorDeCusto()+"]: ");
            entrada = sc.nextLine();
            if(!entrada.trim().isEmpty()){
                try {
                    valorCusto = Double.parseDouble(entrada);
                    produto.setValorDeCusto(valorCusto);
                    break; // Entrada válida, sai do loop
                } catch (NumberFormatException e) {
                    System.out.println("Valor invalido! Digite um numero inteiro ou pressione Enter para manter o valor atual.");
                }
            }
            else break;
        }
        arquivoProduto.editaProduto(produto);
        System.out.println("Produto editado com sucesso!");
    }
    
    public static int vendeuProduto(int codigoProduto,int quantidade)throws ArquivoException{
        produtos = arquivoProduto.getListaProdutos();
        for(Produto produto: produtos){

            if(produto.getCodigoProduto() == codigoProduto){
                if(produto.getQtEstoque() == 0){
                    System.out.println("Nao temos esse produto em estoque!");
                    return 0;
                }
                else if(produto.getQtEstoque() <quantidade && produto.getQtEstoque() > 0){
                    do{
                        System.out.print("Temos apenas "+produto.getQtEstoque()+ " deste produto em estoque!\n"
                                + "Deseja comprar uma quantia menor...\n"
                                + "1 - Sim\n"
                                + "2 - Nao\n"
                                );
                        int opcao = Leitor.lerInteiro("Digite sua opcao: ");
                        if(opcao == 1){
                            quantidade = Leitor.lerInteiro("Digite a nova quantia: ");
                            if(quantidade < produto.getQtEstoque()){
                                produto.setQtEstoque(produto.getQtEstoque() - quantidade);
                                arquivoProduto.editaProduto(produto);
                                break;
                            }
                        }
                        else if(opcao <1 || opcao > 2){
                            System.out.println("Digite 1 ou 2...");
                        }
                        else{
                            return 0;
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