package com.bcopstein.barca_trab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BarcaTest {

    @Mock
    private Relogio relogio;

    private Barca barca;

    @BeforeEach
    void setup() {
        // Configura horário padrão (9:00) usando mock leniente
        lenient().when(relogio.getHora()).thenReturn(9);
        lenient().when(relogio.getMinuto()).thenReturn(0);
        barca = new Barca(relogio, 100.0);
    }

    // Testa assentos com identificadores inválidos
    @ParameterizedTest
    @CsvSource({
        "'F1A01',   -1.0",  // fileira com 1 dígito
        "'F01A1',   -1.0",  // assento com 1 dígito
        "'F00A01',  -1.0",  // fileira fora do intervalo (mínimo)
        "'F61A01',  -1.0",  // fileira fora do intervalo (máximo)
        "'F01A00',  -1.0",  // assento fora do intervalo (mínimo)
        "'F01A21',  -1.0"   // assento fora do intervalo (máximo)
    })
    void testAssentoInvalido(String assento, double esperado) {
        double resultado = barca.defineAssento(assento == null ? null : assento);
        assertEquals(esperado, resultado);
    }
    
    // Testa tentativa de ocupar um assento já ocupado
    @Test
    void testAssentoOcupado() {
        barca.ocupacaoArbitraria("F01A01");
        assertEquals(-2.0, barca.defineAssento("F01A01"));
    }

    // Testa distribuição de peso para os primeiros 100 passageiros (fileiras 1 a 20)
    @Test
    void testDistribuicaoPesoAntesDos100() {
        assertEquals(-3.0, barca.defineAssento("F21A01")); // fora da faixa permitida
        assertEquals(100.0, barca.defineAssento("F01A02")); // dentro da faixa
    }

    // Testa distribuição de peso entre 100 e 199 passageiros (fileiras 40 a 60)
    @Test
    void testDistribuicaoPesoDe100a199() {
        for (int i = 0; i < 100; i++) {
            String assento = String.format("F%02dA%02d", 1 + (i / 20), 1 + (i % 20));
            barca.ocupacaoArbitraria(assento);
        }

        assertEquals(-3.0, barca.defineAssento("F30A01")); // fora da faixa permitida
        assertEquals(100.0, barca.defineAssento("F45A01")); // dentro da faixa
    }

    // Testa distribuição de peso após 200 passageiros (qualquer assento permitido)
    @Test
    void testDistribuicaoPesoApos200() {
        for (int i = 0; i < 200; i++) {
            String assento = String.format("F%02dA%02d", 1 + (i / 20), 1 + (i % 20));
            barca.ocupacaoArbitraria(assento);
        }

        assertEquals(100.0, barca.defineAssento("F33A01")); // qualquer lugar permitido
    }
    @Test
    void testAssentoNasBordasPermitidas() {
        // Passageiro 0 a 99 – fileiras 1 a 20
        assertEquals(100.0, barca.defineAssento("F01A01")); // mínimo
        assertEquals(100.0, barca.defineAssento("F20A01")); // máximo
    }
    @Test
    void testAssentoNulo() {
        assertEquals(-1.0, barca.defineAssento(null));
    }

    // Testa cálculo de preço da passagem com base no horário
    @ParameterizedTest
    @CsvSource({
        "12,01,110.0", // 10% a mais
        "13,59,110.0",
        "14,00,100.0", // valor base
        "18,01,110.0",
        "19,59,110.0",
        "20,00,120.0", // 20% a mais
        "23,59,120.0",
        "00,00,150.0", // 50% a mais (madrugada)
        "07,59,150.0"
    })
    void testPrecoPorHorario(int hora, int minuto, double esperado) {
        // Configura mock para horário específico
        when(relogio.getHora()).thenReturn(hora);
        when(relogio.getMinuto()).thenReturn(minuto);

        Barca b = new Barca(relogio, 100.0);

        // Usa assento válido
        String assento = "F01A10";
        double resultado = b.defineAssento(assento);

        assertEquals(esperado, resultado);
    }
}
