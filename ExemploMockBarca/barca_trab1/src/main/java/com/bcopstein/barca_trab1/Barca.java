package com.bcopstein.barca_trab1;

public class Barca {
    private Relogio relogio;
    private double precoBase;
    private boolean ocupado[][] = new boolean[60][20]; // lembrar que o array começa em 0
    private int total_passageiros = 0;

    public Barca(Relogio relogio, double precoBase) {
        this.relogio = relogio;
        this.precoBase = precoBase;
    }

    public double defineAssento(String assentoInformado) {

        if (assentoInformado == null || !assentoInformado.matches("F\\d{2}A\\d{2}")) {
            return -1.0;
        }

        int fileira = Integer.parseInt(assentoInformado.substring(1, 3)); // extrai numeros ex. F02
        int assento = Integer.parseInt(assentoInformado.substring(4, 6)); // extrai numeros ex. A02

        if (fileira < 1 || fileira > 60 || assento < 1 || assento > 20) {
            return -1.0;
        }

        if (ocupado[fileira - 1][assento - 1]) {
            return -2.0;

        }

        if (total_passageiros < 100 && (fileira < 1 || fileira > 20) ||
                totalPassageiros >= 100 && totalPassageiros < 200 && (fileira < 40 || fileira > 60)) {
            return -3.0;
        }

        ocupados[fileira - 1][assento - 1] = true;
        total_passageiros++;

        if (relogio.getHora() >= 8 && relogio.getHora() <= 12 || relogio.getHora() >= 14 && relogio.getHora() <= 18) {
            return precoBase;
        }
        if (relogio.getHora() >= 12 && relogio.getMinuto() >= 1 || relogio.getHora() >= 13 && relogio.getMinuto() <= 59) {
            return precoBase * 1.1;
        }
        if (relogio.getHora() >= 18 && relogio.getMinuto() >= 1 || relogio.getHora() >= 19 && relogio.getMinuto() <= 59) {
            return precoBase * 1.1;
        }
        if(relogio.getHora() >= 20 && relogio.getHora() <= 23 && relogio.getMinuto() <=59) {
            return precoBase * 1.2;
        }
        if(relogio.getHora() >= 00 && relogio.getHora() <= 7 && relogio.getMinuto() <=59) {
            return precoBase * 1.5;
        }
    }

    public void ocupacaoArbitraria(String assentoInformado) {
        if (assentoInformado == null || !assentoInformado.matches("F\\d{2}A\\d{2}"))
            return;

        int fileira = Integer.parseInt(assentoInformado.substring(1, 3));
        int assento = Integer.parseInt(assentoInformado.substring(4, 6));

        if (fileira >= 1 && fileira <= 60 && assento >= 1 && assento <= 20) {
            if (!ocupados[fileira - 1][assento - 1]) {
                ocupados[fileira - 1][assento - 1] = true;
                total_passageiros++;
            }
        }
    }
}
