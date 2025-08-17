package io;

import java.io.*;
import java.nio.file.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

import model.*;

/*
Objetivo:
Todos os relatórios serão criados aqui e organizados em pastas,
já que a professora ordenou que guardemos os relatórios dos outros meses.

Funcionamento:
- Ao chamar essa classe, ela cria uma pasta com o nome do mês atual (formato: yyyy-MM).
- Dentro dessa pasta, serão salvos os arquivos de relatório.
- Se a pasta já existir, os arquivos serão sobrescritos com os dados mais recentes.
- Os relatórios antigos ficam guardados nas pastas dos meses anteriores.

Relatórios gerados:
- estoque.csv
- vendasprod.csv
- vendaspagto.csv
- areceber.csv
- apagar.csv

OBS: Tudo isso ficara dentro da pasta Relatorios...
*/

//Não vai ser possivel herdar a classe Arquivo, pois ela e feita para arquivos Fixos...
public class ArquivoRelatorios{

    public void CriaRelatorioAhPagar(List<AhPagar> listaDividas, boolean trocaDeMes) throws ArquivoException {
        // Define a data do relatório: mês anterior se trocaDeMes for true, senão mês atual
        LocalDate dataRelatorio = trocaDeMes ? LocalDate.now().minusMonths(1) : LocalDate.now();

        // Formata a pasta com base na data escolhida
        String nomePasta = dataRelatorio.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("1-apagar.csv");

        if (!Files.exists(pastaRelatorio)) {
            try {
                Files.createDirectories(pastaRelatorio);
            } catch (IOException e) {
                throw new ArquivoException("Erro de I/O.");
            }
        }

        OpenOption[] configAbrirArquivo = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, configAbrirArquivo)) {

            // Escrever cabeçalho do csv
            writer.write("nome do fornecedor;cnpj do fornecedor;pessoa de contato;telefone;valor total a pagar\n");

            // Escrever dívidas
            for (AhPagar divida : listaDividas) {
                writer.write(divida.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }

    public void CriaRelatorioAhReceber(List<AhReceber> listaDevedores, boolean trocaDeMes) throws ArquivoException {
        // Define a data do relatório: mês anterior se trocaDeMes for true, senão mês atual
        LocalDate dataRelatorio = trocaDeMes ? LocalDate.now().minusMonths(1) : LocalDate.now();

        // Formata a pasta com base na data escolhida
        String nomePasta = dataRelatorio.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("2-areceber.csv");

        if (!Files.exists(pastaRelatorio)) {
            try {
                Files.createDirectories(pastaRelatorio);
            } catch (IOException e) {
                throw new ArquivoException("Erro de I/O.");
            }
        }

        OpenOption[] configAbrirArquivo = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, configAbrirArquivo)) {

            // Escrever cabeçalho do csv
            writer.write("nome do cliente;tipo de cliente;cpf/cnpj do cliente;telefone;data de cadastro;valor total a receber\n");

            // Escrever devedores
            for (AhReceber devedor : listaDevedores) {
                writer.write(devedor.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }

    public void CriaRelatorioProdutos(List<Produto> produtos,boolean trocaDeMes) throws ArquivoException {
        // Define a data do relatório: mês anterior se trocaDeMes for true, senão mês atual
        LocalDate dataRelatorio = trocaDeMes ? LocalDate.now().minusMonths(1) : LocalDate.now();

        // Formata a pasta com base na data escolhida
        String nomePasta = dataRelatorio.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("3-vendasprod.csv");

        if (!Files.exists(pastaRelatorio)) {
            try {
                Files.createDirectories(pastaRelatorio);
            } catch (IOException e) {
                throw new ArquivoException("Erro de I/O.");
            }
        }

        OpenOption[] configAbrirArquivo = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, configAbrirArquivo)) {
            // Define o cabeçalho do CSV.
            writer.write("codigo do produto;descricao;receita bruta;lucro total\n");

            // Escreve cada produto da lista em uma nova linha do CSV.
            for (Produto produto : produtos) {
                writer.write(produto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }

    public void CriaRelatorioVendasPorMetodoPagamento(java.util.List<String> reportLines,boolean trocaDeMes) throws ArquivoException {
        // Define a data do relatório: mês anterior se trocaDeMes for true, senão mês atual
        LocalDate dataRelatorio = trocaDeMes ? LocalDate.now().minusMonths(1) : LocalDate.now();

        // Formata a pasta com base na data escolhida
        String nomePasta = dataRelatorio.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("4-vendaspgto.csv");

        if (!Files.exists(pastaRelatorio)) {
            try {
                Files.createDirectories(pastaRelatorio);
            } catch (IOException e) {
                throw new ArquivoException("Erro de I/O.");
            }
        }

        OpenOption[] configAbrirArquivo = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, configAbrirArquivo)) {
            writer.write("metodo de pagamento;receita bruta;lucro total\n");

            for (String line : reportLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }

    public void CriaRelatorioEstoque(java.util.List<String> reportLines,boolean trocaDeMes) throws ArquivoException {
        // Define a data do relatório: mês anterior se trocaDeMes for true, senão mês atual
        LocalDate dataRelatorio = trocaDeMes ? LocalDate.now().minusMonths(1) : LocalDate.now();

        // Formata a pasta com base na data escolhida
        String nomePasta = dataRelatorio.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("5-estoque.csv");

        if (!Files.exists(pastaRelatorio)) {
            try {
                Files.createDirectories(pastaRelatorio);
            } catch (IOException e) {
                throw new ArquivoException("Erro de I/O.");
            }
        }

        OpenOption[] configAbrirArquivo = new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, configAbrirArquivo)) {
            writer.write("código do produto;descrição do produto;quantidade em estoque;observações\n");

            for (String line : reportLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }
}