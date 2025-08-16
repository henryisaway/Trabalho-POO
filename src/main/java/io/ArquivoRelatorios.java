package io;

import java.io.*;
import java.nio.file.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


//Não vai ser possivel e herdar a classe Arquivo, pois ela e feita pra arquiovos Fixos...
public class ArquivoRelatorios{

    public void CriaRelatorioAhPagar(AhPagar ahPagar, boolean primeiraLinha) throws ArquivoException{
        if(primeiraLinha){
            escreverLinhaRelatorio("apagar.csv","nome do fornecedor;cnpj do fornecedor;pessoa de contato;telefone;valor total a pagar",true); 
        }
        else{
            String nomeFornecedor = ahPagar.getNomeFornecedor();
            String cnpj = ahPagar.getCnpj();
            String pessoaDeContato = ahPagar.getPessoaContato();
            String telefone = ahPagar.getTelefone();
            double valorTotalPagar = ahPagar.getValorTotal();
            
            //Monta a linha em formato csv,  ja com \n
            String linhaAhPagar = (nomeFornecedor+";"+cnpj+";"+pessoaDeContato+";"+telefone+";"+valorTotalPagar);
            //Adiciona a linha ao final do arquivo
            escreverLinhaRelatorio("apagar.csv",linhaAhPagar, false);
        }
    }
    
    public void CriaRelatorioAhReceber(AhReceber ahReceber, boolean primeiraLinha) throws ArquivoException{
        if(primeiraLinha){
            escreverLinhaRelatorio("areceber.csv","nome do cliente;tipo de cliente;cpf/cnpj do cliente;telefone;data de cadastro;valor toal a receber",true); 
        }
        else{
            String nomeDoCliente = ahReceber.getNomeDoCliente();
            String tipoCliente = ahReceber.getTipoCliente();
            String CPF_CNPJ = ahReceber.getCPF_CNPJ();
            String telefone = ahReceber.getTelefone();
            LocalDate dataCadastro = ahReceber.getDataCadastro();
            double valorAhReceber = ahReceber.getValorAhReceber();
            
            //Monta a linha em formato csv
            String linhaAhPagar = (nomeDoCliente+";"+tipoCliente+";"+CPF_CNPJ+";"+telefone+";"+dataCadastro+";"+valorAhReceber);
            //Adiciona a linha ao final do arquivo
            escreverLinhaRelatorio("areceber.csv",linhaAhPagar, false);
        }
    }
    
    private void escreverLinhaRelatorio(String nomeArquivo, String linha, boolean sobrescrever) throws ArquivoException {
        String nomePasta = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM"));
        Path pastaRelatorio = Paths.get("Relatorios da Padaria", nomePasta);
        Path caminhoArquivo = pastaRelatorio.resolve(nomeArquivo);

        if (!Files.exists(pastaRelatorio)) {
            try {
                Files.createDirectories(pastaRelatorio);
            } catch (IOException e) {
                throw new ArquivoException("Erro ao criar pasta de relatório.");
            }
        }

        OpenOption[] opcoes = sobrescrever
            ? new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING}
            : new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND};

        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, opcoes)) {
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            throw new ArquivoException("Erro ao escrever no relatório.");
        }
    }
}
