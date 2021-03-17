/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.upskill.clubedesportivo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * @author acris
 */

public class SemiprofissionalTest {


    private static Semiprofissional sp1;
    private static Semiprofissional sp2;

    @Test
    public void testCalcularDescontoIRS() {
        //Arrange
        sp2 = new Semiprofissional("ana", 9847,
                29, Genero.FEMININO, Actividade.CICLISMO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                825.45f, 64, 10, 1000);
        float expectedResult = 1000 * 0.1f;

        //Act
        float result = sp2.calcularDescontoIRS();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalcularPagamentoComDesconto() {

        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);

        float expectedResult = (200.5f + (0.02f * 200.5f)) - (200.5f * 0.1f);

        //Act
        float result = sp1.calcularPagamentoComDesconto();

        //Assert
        assertEquals(expectedResult, result);


    }

    @Test
    public void testCalcularPagamento() {

        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);
        float expectedResult = 200.5f + (0.02f * 200.5f);

        //Act
        float result = sp1.calcularPagamento();

        //Assert
        assertEquals(expectedResult, result);
    }


}
