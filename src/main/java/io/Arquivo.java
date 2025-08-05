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
        StringBuilder texto = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while((linha = br.readLine()) != null) {
                texto.append(linha).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw  new ArquivoException("Arquivo não encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro ao lidar com o arquivo!");
        }

        return texto.toString();
    }
    
    
}
