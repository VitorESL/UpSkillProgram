/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.upskill.clubedesportivo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

/**
 * @author acris
 */

public class ProfissionalTest {

    @Test
    public void testDeterminarParcelaVariavel() {
        //Arrange
        Profissional p1 = new Profissional("joana", 6677, 40, Genero.FEMININO,
                Actividade.CICLISMO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                350.45f, 55, 300.5f);
        float expectedResult = 70.090004f;

        //Act
        float result = p1.determinarParcelaVariavel();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalcularDescontoIRS() {
        //Arrange
        Profissional p1 = new Profissional("joana", 6677, 40, Genero.FEMININO,
                Actividade.CICLISMO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                350.45f, 55, 300.5f);
        float expectedResult = 300.5f * 0.1f;

        //Act
        float result = p1.calcularDescontoIRS();

        //Arrange
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalcularPagamento() {
        //Arrange
        Profissional p1 = new Profissional("joana", 6677, 40, Genero.FEMININO,
                Actividade.CICLISMO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                350.45f, 55, 300.5f);
        float expectedResult = (float) (300.5f + (0.2 * 350.45f));

        //Act
        float result = p1.calcularPagamento();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalcularPagamentoComDesconto() {
        //Arrange
        Profissional p1 = new Profissional("joana", 6677, 40, Genero.FEMININO,
                Actividade.CICLISMO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                350.45f, 55, 300.5f);
        float expectedResult = p1.calcularPagamento() - p1.calcularDescontoIRS();

        //Act
        float result = p1.calcularPagamentoComDesconto();

        //Assert
        assertEquals(expectedResult, result);
    }

}
