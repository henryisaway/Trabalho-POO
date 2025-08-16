package service;

import java.util.ArrayList;
import java.util.List;

import model.*;
import io.*;
import java.util.Collections;
import java.util.Comparator;


public class RelatoriosHandler {
    
    public static void GerarTotalPagarFornecedor(List<Compra> listaCompras) throws ArquivoException {
        
        List<AhPagar> listaDevendoFornecedores = new ArrayList<>();

        for (Compra compra : listaCompras) {
            Fornecedor fornecedor = FornecedorHandler.buscarFornecedor(compra.getCodigoFornecedor());
            Produto produto = ProdutoHandler.buscarProduto(compra.getCodigoProduto());

            int quantidade = compra.getQuantidade();
            double valorTotalAhPagar = quantidade * produto.getValorDeCusto();

            AhPagar ahPagar = new AhPagar(
                fornecedor.getNomeEmpresa(),
                fornecedor.getCNPJ(),
                fornecedor.getPessoaContato(),
                fornecedor.getTelefone(),
                valorTotalAhPagar
            );
            listaDevendoFornecedores.add(ahPagar);
        }

        Collections.sort(listaDevendoFornecedores, Comparator.comparing(AhPagar::getNomeFornecedor));

        List<AhPagar> listaFinal = new ArrayList<>();

        if (!listaDevendoFornecedores.isEmpty()) {
            AhPagar acumulador = listaDevendoFornecedores.get(0);

            for (int i = 1; i < listaDevendoFornecedores.size(); i++) {
                AhPagar atual = listaDevendoFornecedores.get(i);

                if (acumulador.getNomeFornecedor().equals(atual.getNomeFornecedor())) {
                    acumulador.somarValor(atual.getValorTotal());
                } else {
                    listaFinal.add(acumulador);
                    acumulador = atual;
                }
            }
            listaFinal.add(acumulador);
        }

        // Escreve no arquivo CSV
        ArquivoRelatorios arquivoRelatorio = new ArquivoRelatorios();
        arquivoRelatorio.CriaRelatorioAhPagar(null, true);//Cria a primeira Linha do csv...
        for (AhPagar item : listaFinal) {
            arquivoRelatorio.CriaRelatorioAhPagar(item, false);
        }
    }
    public static void GerarTotalReceberCliente(List<Venda> listaVendas) throws ArquivoException {
        
        List<AhReceber> listaClientesDevedores = new ArrayList<>();

        for (Venda V : listaVendas) {
            Cliente cliente = ClienteHandler.buscarCliente(V.getCodigoCliente());
            Produto produto = ProdutoHandler.buscarProduto(V.getCodProduto());

            int quantidade = compra.getQuantidade();
            double valorTotalAhPagar = quantidade * produto.getValorDeCusto();

            AhPagar ahPagar = new AhPagar(
                fornecedor.getNomeEmpresa(),
                fornecedor.getCNPJ(),
                fornecedor.getPessoaContato(),
                fornecedor.getTelefone(),
                valorTotalAhPagar
            );
            listaDevendoFornecedores.add(ahPagar);
        }

        Collections.sort(listaDevendoFornecedores, Comparator.comparing(AhPagar::getNomeFornecedor));

        List<AhPagar> listaFinal = new ArrayList<>();

        if (!listaDevendoFornecedores.isEmpty()) {
            AhPagar acumulador = listaDevendoFornecedores.get(0);

            for (int i = 1; i < listaDevendoFornecedores.size(); i++) {
                AhPagar atual = listaDevendoFornecedores.get(i);

                if (acumulador.getNomeFornecedor().equals(atual.getNomeFornecedor())) {
                    acumulador.somarValor(atual.getValorTotal());
                } else {
                    listaFinal.add(acumulador);
                    acumulador = atual;
                }
            }
            listaFinal.add(acumulador);
        }

        // Escreve no arquivo CSV
        ArquivoRelatorios arquivoRelatorio = new ArquivoRelatorios();
        arquivoRelatorio.CriaRelatorioAhPagar(null, true);//Cria a primeira Linha do csv...
        for (AhPagar item : listaFinal) {
            arquivoRelatorio.CriaRelatorioAhPagar(item, false);
        }
    }
    
}
