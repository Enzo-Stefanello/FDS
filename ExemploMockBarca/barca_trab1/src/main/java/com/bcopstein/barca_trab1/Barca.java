package com.bcopstein.barca_trab1;

public class Barca {
    private Relogio relogio;
    private double precoBase;
    private boolean ocupado[][] = new boolean[60][20]; // Lembre-se que o array começa em 0
    private int total_passageiros = 0;

    // Construtor que recebe a instância de Relogio e o preço base
    public Barca(Relogio relogio, double precoBase) {
        this.relogio = relogio;
        this.precoBase = precoBase;
    }

    // Método que define o assento e retorna o preço
    public double defineAssento(String assentoInformado) {
        // Valida se o assento informado é válido
        if (assentoInformado == null || !assentoInformado.matches("F\\d{2}A\\d{2}")) {
            return -1.0;  // Assento inválido
        }
        // Extrai o número da fileira e o assento
        int fileira = Integer.parseInt(assentoInformado.substring(1, 3)); // Ex. F02 -> 2
        int assento = Integer.parseInt(assentoInformado.substring(4, 6)); // Ex. A12 -> 12

        // Verifica se a fileira e o assento são válidos
        if (fileira < 1 || fileira > 60 || assento < 1 || assento > 20) {
            return -1.0;  // Assento inválido
        }
        // Verifica se o assento está ocupado
        if (ocupado[fileira - 1][assento - 1]) {
            return -2.0;  // Assento ocupado
        }
        // Verifica as regras de distribuição de peso
        if (total_passageiros < 100 && (fileira < 1 || fileira > 20)) {
            return -3.0;  // Para os primeiros 100 passageiros, só podem ocupar fileiras 1 a 20
        }
        if (total_passageiros >= 100 && total_passageiros < 200 && (fileira < 40 || fileira > 60)) {
            return -3.0;  // Para os próximos 100 passageiros, só podem ocupar fileiras 40 a 60
        }
        // Marca o assento como ocupado
        ocupado[fileira - 1][assento - 1] = true;
        total_passageiros++;
        // Calcula o preço da passagem baseado no horário
        return calcularPrecoPorHorario();
    }

    // Método para calcular o preço com base no horário
    private double calcularPrecoPorHorario() {
        int hora = relogio.getHora();
        int minuto = relogio.getMinuto();
        double precoFinal;
        // Horário comercial padrão (08:00 - 12:00 e 14:00 - 18:00)
        if ((hora >= 8 && minuto >= 0 && hora <= 12 && minuto == 00) || (hora >= 14 && minuto >= 0 && hora <= 18 && minuto == 0)) {
            precoFinal = precoBase;
        }
        // Faixa de 12:01 às 13:59 (10% a mais)
        else if ((hora == 12 && minuto >= 1) || (hora == 13) || (hora == 14 && minuto == 0)) {
            precoFinal = precoBase * 1.1;
        }
        // Faixa de 18:01 às 19:59 (10% a mais)
        else if ((hora == 18 && minuto >= 1) || (hora == 19 && hora <= 59)) {
            precoFinal = precoBase * 1.1;
        }
        // Faixa de 20:00 às 23:59 (20% a mais)
        else if (hora >= 20 && hora <= 23) {
            precoFinal = precoBase * 1.2;
        }
        // Faixa da madrugada (00:00 às 07:59) (50% a mais)
        else if (hora >= 0 && hora <= 7) {
            precoFinal = precoBase * 1.5;
        }
        // Caso padrão (deve retornar o preço base)
        else {
            precoFinal = precoBase;
        }
        return Double.parseDouble(String.format("%.2f", precoFinal));
    }

    // Método para marcar um assento arbitrariamente como ocupado (para testes)
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
