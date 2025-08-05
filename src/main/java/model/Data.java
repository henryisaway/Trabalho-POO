package model;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        if (!dataValida(dia, mes, ano)) {
            throw new IllegalArgumentException("Data invalida: " + dia + "/" + mes + "/" + ano);
        }
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    private boolean dataValida(int dia, int mes, int ano) {
        if (mes < 1 || mes > 12 || dia < 1) return false;

        int[] diasPorMes = {
            31, // Janeiro
            isAnoBissexto(ano) ? 29 : 28, // Fevereiro
            31, // Março
            30, // Abril
            31, // Maio
            30, // Junho
            31, // Julho
            31, // Agosto
            30, // Setembro
            31, // Outubro
            30, // Novembro
            31  // Dezembro
        };

        return dia <= diasPorMes[mes - 1];
    }

    private boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    public void informeData(){
    System.out.printf("%02d/%02d/%04d%n", dia, mes, ano);
    }

    //GET

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    //SET
    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
