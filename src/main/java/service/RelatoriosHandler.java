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

        // Verifica se o fornecedor já está na lista; Neste caso a nova dívida é somada ao invés de sobreescrita
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
        arquivoRelatorio.CriaRelatorioAhPagar(listaFinal);    
    }
    
    public static void GerarTotalReceberCliente(List<Venda> listaVendas) throws ArquivoException {
        ClientePF cf = null;
        ClientePJ cj = null;
        AhReceber ahReceber;
        String tipoCliente;
        List<AhReceber> listaClientesDevedores = new ArrayList<>();

        for (Venda V : listaVendas) {
            Cliente cliente = ClienteHandler.buscarCliente(V.getCodigoCliente());
            if(cliente instanceof ClientePF){
                cf = (ClientePF) cliente;
                tipoCliente = "F";
            }
            else{
                cj = (ClientePJ) cliente;
                tipoCliente = "J";
            }
            
            
            Produto produto = ProdutoHandler.buscarProduto(V.getCodProduto());

            int quantidade = V.getQuantidade();
            double valorDevendo = quantidade * produto.getValorVenda();

            if(tipoCliente.equals("F")){
                ahReceber  = new AhReceber(
                    cf.getNome(),
                    tipoCliente,
                    cf.getCPF(),
                    cf.getTelefone(),
                    cf.getData(),
                    valorDevendo  
                );
                listaClientesDevedores.add(ahReceber);
            }
            else{
                ahReceber  = new AhReceber(
                    cj.getNome(),
                    tipoCliente,
                    cj.getCNPJ(),
                    cj.getTelefone(),
                    cj.getData(),
                    valorDevendo  
                );
                listaClientesDevedores.add(ahReceber);
            }
        }

        Collections.sort(listaClientesDevedores, Comparator.comparing(AhReceber::getNomeDoCliente));

        List<AhReceber> listaFinal = new ArrayList<>();

        if (!listaClientesDevedores.isEmpty()) {
            AhReceber acumulador = listaClientesDevedores.get(0);

            for (int i = 1; i < listaClientesDevedores.size(); i++) {
                AhReceber atual = listaClientesDevedores.get(i);

                if (acumulador.getNomeDoCliente().equals(atual.getNomeDoCliente())) {
                    acumulador.somarValor(atual.getValorAhReceber());
                } else {
                    listaFinal.add(acumulador);
                    acumulador = atual;
                }
            }
            listaFinal.add(acumulador);
        }

        // Escreve no arquivo CSV
        ArquivoRelatorios arquivoRelatorio = new ArquivoRelatorios();
        arquivoRelatorio.CriaRelatorioAhReceber(listaFinal);
    }
    
}
