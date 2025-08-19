package service;

import java.util.Scanner;

public class Leitor {
    private static final Scanner sc = new Scanner(System.in);

    public static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite apenas numeros inteiros.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                return Double.parseDouble(entrada.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite um numero decimal valido.");
            }
        }
    }

    public static String lerString(String mensagem) {
    while (true) {
        System.out.print(mensagem);
        String entrada = sc.nextLine().trim();
        if (!entrada.isEmpty()) {
            return entrada;
        } else {
            System.out.println("Entrada invalida! O campo nao pode ficar vazio.");
        }
    }
}

}

