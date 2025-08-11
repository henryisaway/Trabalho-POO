package model;
import java.util.Scanner;

public enum MetodoPagamento {
    DINHEIRO("$"),
    CHEQUE("X"),
    DEBITO("D"),
    CREDITO("C"),
    TICKET("T"),
    FIADO("F");

    private String codigo;
    private static Scanner sc = new Scanner(System.in);
    MetodoPagamento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static MetodoPagamento fromCodigo(String codigo) {
        for (MetodoPagamento mp : values()) {
            if (mp.codigo.equals(codigo)) {
                return mp;
            }
        }
        System.out.println("Metodo de pagamento nao aceito!");
        return null;
    }
    public static void listarMetodos() {
        System.out.println("Metodos de pagamento disponiveis:");
        for (MetodoPagamento mp : MetodoPagamento.values()) {
            System.out.println("- " + mp.name() + " (" + mp.getCodigo() + ")");
        }
    }
}

