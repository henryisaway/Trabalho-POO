package service;

import java.io.*;
import java.nio.file.*;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.*;
import io.*;

public class RelatoriosHandler {
    public void GerarTotalPagarFornecedor() throws ArquivoException{
        //Criando primeira Linha do csv
        try {
        // Data atual formatada como "2025_08"
        String nomePasta = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM"));

        // Caminho completo: report/2025_08
        Path pastaRelatorio = Paths.get("report", nomePasta);
        Files.createDirectories(pastaRelatorio);

        // Caminho do arquivo dentro da pasta
        Path caminhoArquivo = pastaRelatorio.resolve("apagar.csv");

        // Escreve o cabeçalho
        
        } catch (IOException e) {
        System.err.println("Erro ao criar relatório: " + e.getMessage());
        }
        
        ArquivoCompra arquivoCompra = new ArquivoCompra(new File("registro_compras.csv"));
        
        //Pega uma Lista de dados
        arquivoCompra.pegaCompras();
        List<Compra> listaCompras = arquivoCompra.getListaCompras();  
        List<AhPagar> listaDevendoFornecedores = new ArrayList<>();
        
        for(int i = 0; i<listaCompras.size(); i++){
            int codigoFornecedor = listaCompras.get(i).getCodigoFornecedor();
            //Terminar... 
        }
    }
    
}
