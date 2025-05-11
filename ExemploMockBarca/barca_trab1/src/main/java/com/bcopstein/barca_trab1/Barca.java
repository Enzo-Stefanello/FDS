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

        // leitura assentos e fileiras
        if (assentoInformado == null || !assentoInformado.matches("F\\d{2}A\\d{2}")) {
            return -1.0;
        }

        int fileira = Integer.parseInt(assentoInformado.substring(1, 3)); // extrai numeros ex. F02
        int assento = Integer.parseInt(assentoInformado.substring(4, 6)); // extrai numeros ex. A02

        // erros
        if (fileira < 1 || fileira > 60 || assento < 1 || assento > 20) {
            return -1.0; // invalido
        }

        if (ocupado[fileira - 1][assento - 1]) {
            return -2.0; // ocupado

        }

        if ((total_passageiros < 100 && (fileira < 1 || fileira > 20)) ||
                (total_passageiros >= 100 && total_passageiros < 200 && (fileira < 40 || fileira > 60))) {
            return -3.0; // bloqueado
        }

        // add
        int hora = relogio.getHora();
        int minuto = relogio.getMinuto();
        double preco;

        if ((hora >= 8 && hora < 12) || (hora >= 14 && hora < 18)) {
            preco= precoBase; // Horário comercial
        } else if ((hora == 12 && minuto >= 1) || (hora == 13) ||
                (hora == 18 && minuto >= 1) || (hora == 19)) {
            preco= precoBase * 1.10; // 10% mais caro
        } else if (hora >= 20 && hora <= 23) {
            preco= precoBase * 1.20; // 20% mais caro
        } else {
            preco= precoBase * 1.50; // Madrugada
        }

        return Math.round(preco * 100.0) / 100.0; //apenas duas casas dps virgula
    }

    public void ocupacaoArbitraria(String assentoInformado) {
        if (assentoInformado == null || !assentoInformado.matches("F\\d{2}A\\d{2}"))
            return;

        int fileira = Integer.parseInt(assentoInformado.substring(1, 3));
        int assento = Integer.parseInt(assentoInformado.substring(4, 6));

        if (fileira >= 1 && fileira <= 60 && assento >= 1 && assento <= 20) {
            if (!ocupado[fileira - 1][assento - 1]) {
                ocupado[fileira - 1][assento - 1] = true;
                total_passageiros++;
            }
        }
    }
}
