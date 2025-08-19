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
            throw  new ArquivoException("Arquivo nao encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }

        return texto.toString(); // Retorna todos os conteudos do arquivo como uma String
    }
    // Método que adiciona texto ao final do arquivo (modo append = true)
    public void adicionaTextoArquivo(String texto, boolean sobrescreva) throws ArquivoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, sobrescreva))) {
            bw.write(texto); // Escreve o texto no final do arquivo
            bw.flush();
            
        } catch (FileNotFoundException e) {
            throw new ArquivoException("Arquivo nao encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }


    public void reescreveArquivo(String texto) throws ArquivoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            bw.write(texto);
        } catch (FileNotFoundException e) {
            throw  new ArquivoException("Arquivo nao encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }

    public void removeItem(int id) throws ArquivoException {
        StringBuilder texto = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String[] linhaConteudo;
            int codigo;

            // Lê e preserva o cabeçalho
            linha = br.readLine();
            if (linha != null) {
                texto.append(linha).append("\n");
            }

            // Lê e filtra as demais linhas
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                linhaConteudo = linha.split(";");
                codigo = Integer.parseInt(linhaConteudo[0]);
                if (codigo != id) {
                    texto.append(linha).append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            throw new ArquivoException("Arquivo nao encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }

        // Só reescreve o arquivo se tudo deu certo
        reescreveArquivo(texto.toString());
    }


    public void editaItem(int id, String item) throws ArquivoException{
        StringBuilder texto = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String[] linhaConteudo;
            int codigo;

            linha = br.readLine();
            texto.append(linha).append("\n");

            while((linha = br.readLine()) != null) {
                linhaConteudo = linha.split(";");
                codigo = Integer.parseInt(linhaConteudo[0]);
                if (codigo == id) {
                    texto.append(item).append("\n");
                } else {
                    texto.append(linha).append("\n");
                }
            }

            reescreveArquivo(texto.toString());
        } catch (FileNotFoundException e) {
            throw  new ArquivoException("Arquivo nao encontrado!");
        } catch (IOException e) {
            throw new ArquivoException("Erro de I/O.");
        }
    }
    
    public boolean terminaComQuebra(File arquivo) throws IOException {
    // Abre o arquivo para leitura usando RandomAccessFile
    try (RandomAccessFile raf = new RandomAccessFile(arquivo, "r")) {

        // Obtém o tamanho total do arquivo em bytes
        long tamanho = raf.length();

        // Se o arquivo estiver vazio, retorna false (não há quebra de linha)
        if (tamanho == 0) return false;

        // Move o ponteiro para o último byte do arquivo
        raf.seek(tamanho - 1);

        // Lê o último byte do arquivo
        byte ultimoByte = raf.readByte();

        // Retorna true se o último byte for uma quebra de linha ('\n'), senão false
        return ultimoByte == '\n';
    }
}


}
