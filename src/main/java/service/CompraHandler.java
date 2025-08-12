package service;

import io.ArquivoException;
import io.ArquivoCompra;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import model.Compra;
import java.time.LocalDate;

public class CompraHandler {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArquivoCompra arquivoCompra = new ArquivoCompra(new File("src/main/java/resources/registro_compras.csv"));
    private static List<Compra> compras;

    public static void carregaCompra() throws ArquivoException {
        arquivoCompra.pegaCompras();
    }

    public static boolean cadastraCompra(int codigoProdutoP,int quantidadeP) throws ArquivoException {
        int numeroNotaFiscal, codigoFornecedor;
        LocalDate dataCompra;
        
        System.out.print("Digite o numero da nota fiscal: ");
        numeroNotaFiscal = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o codigo do Fornecedor: ");
        codigoFornecedor = sc.nextInt();
        sc.nextLine();
        String buscaFornecedor = FornecedorHandler.buscarFornecedor(codigoFornecedor);
        if(buscaFornecedor.equals("Fornecedor não cadastrado!")){
            System.out.println("Cadastre este fornecedor primeiro!");
            return false;
        }
        
        dataCompra = LocalDate.now();
        
        arquivoCompra.criaCompra(new Compra(numeroNotaFiscal, codigoFornecedor,codigoProdutoP, quantidadeP, dataCompra));
        return true;
    }

    public static void listarContasPagar() throws ArquivoException{
        
        compras = arquivoCompra.getListaCompras();

        System.out.println("Compras: ");
        for (Compra compra:compras) {
            System.out.println(compra.infoCompra());
        }
        compras.clear();
        arquivoCompra.clearListaCompras();
    }
}