package main.io;

import main.model.*;
import java.io.*;
import java.util.*;

public class ArquivoCliente extends Arquivo {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    
    public ArquivoCliente(File arquivo) {
        super(arquivo);
    }
    
    public ArrayList<Cliente> getClientes(){return clientes;}
    public void processarConteudo() throws ArquivoException {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = br.readLine(); // Ignora cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length < 7) continue;

                int codigo = Integer.parseInt(campos[0].trim());
                String nome = campos[1].trim();
                String endereco = campos[2].trim();
                String telefone = campos[3].trim();
                String dataStr = campos[4].trim(); // formato esperado: dd/mm/yyyy
                String tipo = campos[5].trim();
                String doc = campos[6].trim();

                Data dataCadastro = parseData(dataStr);

                Cliente cliente;
                if (tipo.equalsIgnoreCase("F")) {
                    cliente = new ClientePF(nome, endereco, telefone, doc);
                } else if (tipo.equalsIgnoreCase("J")) {
                    String inscricaoEstadual = campos.length > 7 ? campos[7].trim() : "";
                    cliente = new ClientePJ(nome, endereco, telefone, doc, inscricaoEstadual);
                } else {
                    continue; // tipo inválido
                }

                // Ajusta o código, já que o construtor atual gera automaticamente
                //Coocar endereco correto
                
                clientes.add(cliente);
            }
        } catch (IOException e) {
            System.out.println("Erro de I/O.");
            System.exit(1);
        }
    }

    private Data parseData(String dataStr) {
        String[] partes = dataStr.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return new Data(dia, mes, ano);
    }
}
