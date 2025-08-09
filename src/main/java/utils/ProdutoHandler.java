package utils;

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
        System.out.print("Digite o valor pago pelo produto pela empresa: ");
        valorCusto = sc.nextDouble();
        sc.nextLine();
        
        
        arquivoProduto.criaProduto(new Produto(descricao, estoqueMin, qtProdutos,valorCusto, percentualLucro));
    }

    public static void listarProdutos() throws ArquivoException{
        produtos = arquivoProduto.getListaProdutos();

        System.out.println("Produtos: ");
        for (Produto produto:produtos) {
            System.out.println(produto.infoProduto());
        }
    }

    public static String buscarProduto() throws ArquivoException{
        int codigo;

        System.out.print("Digite o codigo do produto: ");
        codigo = sc.nextInt();
        sc.nextLine();

        produtos = arquivoProduto.getListaProdutos();

        for (Produto produto:produtos) {
            if(produto.getCodigoProduto() == codigo) { return produto.infoProduto(); }
        }

        return "Produto não existe";
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
            qtProdutos = Integer.parseInt(entrada);
            produto.setQtEstoque(qtProdutos);
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
}