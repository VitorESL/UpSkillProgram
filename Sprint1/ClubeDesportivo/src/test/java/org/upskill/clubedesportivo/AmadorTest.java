/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.upskill.clubedesportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author acris
 */
public class AmadorTest {

    @Test
    public void testDeterminarValorPagamento() {

        //Arrange
        Amador a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO,
                Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                3, 80, 6);

        float expectedResult = (3 * 0.02f) + (3 * 0.07f);

        //Act
        float result = a1.determinarValorPagamento();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalcularPagamento() {

        //Arrange
        int valorMinimo = 5;
        Amador a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO,
                Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                3, 80, 6);
        float expectedResult = 5;

        //Act
        float result = a1.calcularPagamento();

        //Assert
        assertEquals(expectedResult, result);

    }

}
