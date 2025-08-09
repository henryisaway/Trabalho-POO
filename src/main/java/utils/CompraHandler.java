package utils;

import io.ArquivoException;
import io.ArquivoCompra;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import model.Compra;
import model.Produto;
import java.time.LocalDate;

public class CompraHandler {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArquivoCompra arquivoCompra = new ArquivoCompra(new File("src/main/java/resources/registro_compras.csv"));
    private static List<Compra> compras;

    public static void carregaProduto() throws ArquivoException {
        arquivoCompra.pegaCompras();
    }

    public static void cadastraCompra(Produto produto) throws ArquivoException {
        int numeroNotaFiscal, codigoFornecedor, codigoProduto, quantidade;
        LocalDate dataCompra;
        
        System.out.print("Digite o numero da nota fiscal: ");
        numeroNotaFiscal = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o codigo do Fornecedor: ");//Fazer verificação se fornecedor esta listado
        codigoFornecedor = sc.nextInt();
        sc.nextLine();
        
        dataCompra = LocalDate.now();
        codigoProduto = produto.getCodigoProduto();
        quantidade = produto.getQtEstoque();
        
        arquivoCompra.criaCompra(new Compra(numeroNotaFiscal, codigoFornecedor,codigoProduto, quantidade, dataCompra));
    }

    public static void listarCompras() throws ArquivoException{
        compras = arquivoCompra.getListaCompras();

        System.out.println("Compras: ");
        for (Compra compra:compras) {
            System.out.println(compra.infoCompra());
        }
    }

    public static void editaProduto() throws ArquivoException {
        //Fazer editar Compra
    }
}