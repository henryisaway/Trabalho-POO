package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;

import model.*;
import io.*;
import java.util.Collections;
import java.util.Comparator;


public class RelatoriosHandler {
    
    public static void GerarTotalPagarFornecedor() throws ArquivoException {
        List<Compra> listaCompras = CompraHandler.getCompras();
        List<AhPagar> listaDevendoFornecedores = new ArrayList<>();

        for (Compra compra : listaCompras) {
            Fornecedor fornecedor = FornecedorHandler.buscarFornecedor(compra.getCodigoFornecedor());
            Produto produto = ProdutoHandler.buscarProduto(compra.getCodigoProduto());

            if (produto == null) {
                System.out.println("[AVISO] Compra registrada para produto inexistente (código: " + compra.getCodigoProduto() + "). Ignorando.");
                continue;
            }
            if (fornecedor == null) {
                System.out.println("[AVISO] Compra registrada para fornecedor inexistente (código: " + compra.getCodigoFornecedor() + "). Ignorando.");
                continue;
            }

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
    
    public static void GerarTotalReceberCliente() throws ArquivoException {
        List<Venda> listaVendas = VendaHandler.getVendasFiado();
        ClientePF cf = null;
        ClientePJ cj = null;
        AhReceber ahReceber;
        String tipoCliente;
        List<AhReceber> listaClientesDevedores = new ArrayList<>();

        for (Venda V : listaVendas) {
            Cliente cliente = ClienteHandler.buscarCliente(V.getCodigoCliente());
            Produto produto = ProdutoHandler.buscarProduto(V.getCodProduto());

            if (produto == null) {
                System.out.println("[AVISO] Venda registrada para produto inexistente (código: " + V.getCodProduto() + "). Ignorando.");
                continue;
            }
            if (cliente == null) {
                System.out.println("[AVISO] Venda registrada para cliente inexistente (código: " + V.getCodigoCliente() + "). Ignorando.");
                continue;
            }

            if(cliente instanceof ClientePF){
                cf = (ClientePF) cliente;
                tipoCliente = "F";
            }
            else{
                cj = (ClientePJ) cliente;
                tipoCliente = "J";
            }
            
            
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

        listaClientesDevedores.sort(Comparator.comparing(AhReceber::getNomeDoCliente, String.CASE_INSENSITIVE_ORDER));

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

    public static void GerarRelatorioProdutos() throws ArquivoException {
        List<Produto> produtos = ProdutoHandler.getProdutos();
        List<Venda> vendas = VendaHandler.getVendas();
        java.util.Map<Integer, Integer> vendasPorProduto = new java.util.HashMap<>();
        for (Venda venda : vendas) {
            vendasPorProduto.put(venda.getCodProduto(), vendasPorProduto.getOrDefault(venda.getCodProduto(), 0) + venda.getQuantidade());
        }

        List<Produto> relatorioProdutos = new java.util.ArrayList<>();
        for (Produto produto : produtos) {
            int quantidadeVendida = vendasPorProduto.getOrDefault(produto.getCodigoProduto(), 0);
            if (quantidadeVendida > 0) {
                final double custoTotal = produto.getValorDeCusto() * quantidadeVendida;
                final double lucroTotal = (produto.getValorVenda() - produto.getValorDeCusto()) * quantidadeVendida;

                Produto relatorioProduto = new Produto(produto.getCodigoProduto(), produto.getDescricao(), 0, 0, 0, 0) {
                    @Override
                    public String toString() {
                        return getCodigoProduto() + ";" + getDescricao() + ";" + String.format("%.2f", custoTotal) + ";" + String.format("%.2f", lucroTotal);
                    }
                    @Override
                    public double getLucro() {
                        return lucroTotal;
                    }
                };
                relatorioProdutos.add(relatorioProduto);
            }
        }

        relatorioProdutos.sort(java.util.Comparator.comparingDouble(Produto::getLucro).reversed());

        ArquivoRelatorios arquivoRelatorio = new ArquivoRelatorios();
        arquivoRelatorio.CriaRelatorioProdutos(relatorioProdutos);
    }

    public static void GerarRelatorioVendasPorMetodoPagamento() throws ArquivoException {
        List<Venda> vendas = VendaHandler.getVendas();
        java.util.Map<MetodoPagamento, double[]> stats = new java.util.EnumMap<>(MetodoPagamento.class);

        for (Venda venda : vendas) {
            Produto produto = ProdutoHandler.buscarProduto(venda.getCodProduto());
            if (produto == null) {
                continue;
            }

            double rawIncome = produto.getValorVenda() * venda.getQuantidade();
            double profit = (produto.getValorVenda() - produto.getValorDeCusto()) * venda.getQuantidade();

            stats.computeIfAbsent(venda.getMetodoPagamento(), k -> new double[2]);
            stats.get(venda.getMetodoPagamento())[0] += rawIncome;
            stats.get(venda.getMetodoPagamento())[1] += profit;
        }

        java.util.List<java.util.Map.Entry<MetodoPagamento, double[]>> sortedStats = new java.util.ArrayList<>(stats.entrySet());

        sortedStats.sort((e1, e2) -> {
            int profitComparison = Double.compare(e2.getValue()[1], e1.getValue()[1]);
            if (profitComparison != 0) {
                return profitComparison;
            }
            return e1.getKey().getCodigo().compareTo(e2.getKey().getCodigo());
        });

        java.util.List<String> reportLines = new java.util.ArrayList<>();
        for (java.util.Map.Entry<MetodoPagamento, double[]> entry : sortedStats) {
            reportLines.add(entry.getKey().name() + ";" + String.format("%.2f", entry.getValue()[0]) + ";" + String.format("%.2f", entry.getValue()[1]));
        }

        ArquivoRelatorios arquivoRelatorio = new ArquivoRelatorios();
        arquivoRelatorio.CriaRelatorioVendasPorMetodoPagamento(reportLines);
    }

    public static void GerarRelatorioEstoque() throws ArquivoException {
        List<Produto> produtos = ProdutoHandler.getProdutos();

        produtos.sort(Comparator.comparing(Produto::getDescricao, String.CASE_INSENSITIVE_ORDER));

        java.util.List<String> reportLines = new java.util.ArrayList<>();
        for (Produto produto : produtos) {
            String observacoes = "";
            if (produto.getQtEstoque() < produto.getEstoqueMin()) {
                observacoes = "COMPRAR MAIS";
            }
            reportLines.add(produto.getCodigoProduto() + ";" + produto.getDescricao() + ";" + produto.getQtEstoque() + ";" + observacoes);
        }

        ArquivoRelatorios arquivoRelatorio = new ArquivoRelatorios();
        arquivoRelatorio.CriaRelatorioEstoque(reportLines);
    }
    
}