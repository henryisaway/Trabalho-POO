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

OBS: Tudo isso ficara dentro da pasta report...
*/


//Não vai ser possivel herdar a classe Arquivo, pois ela e feita pra arquivos Fixos...
public class ArquivoRelatorios{

    public void CriaRelatorioAhPagar(List<AhPagar> listaDividas) throws ArquivoException {
        String nomePasta = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("apagar.csv");

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

    public void CriaRelatorioAhReceber(List<AhReceber> listaDevedores) throws ArquivoException {
        String nomePasta = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve("areceber.csv");

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
}
