package io;

import java.io.*;

abstract class Arquivo {
    protected File arquivo;
    protected String conteudoArquivo;

    public Arquivo (File arquivo) {
        this.arquivo = arquivo;
    }

    public File getArquivo() { return arquivo; }
    public void setArquivo(File arquivo) { this.arquivo = arquivo; }

    public String getConteudoArquivo() { return conteudoArquivo;}

    public String getTextoArquivo() throws ArquivoException {
        StringBuilder texto = new StringBuilder();//StringBuilder mais usado por ser efeciente com concatenação

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while((linha = br.readLine()) != null) {
                texto.append(linha).append("\n");//Adiciona a linha lida e uma quebra de linha
            }
        } catch (FileNotFoundException e) {
            throw  new ArquivoException("Arquivo não encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro ao lidar com o arquivo!");
        }

        return texto.toString();//Retorna todos os conteudos do arquivo como uma String
    }
    //Métod que adiciona texto ao final do arquivo (modo append = true)
    public void adicionaTextoArquivo(String texto) throws ArquivoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(texto);//Escreve o texto no final do arquivo
        } catch (FileNotFoundException e) {
            throw  new ArquivoException("Arquivo não encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro ao lidar com o arquivo!");
        }
    }
}
