package org.upskill.clubedesportivo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author acris
 */

public class ComparatorPremioReverseTest {

    private static Amador a1;
    private static Amador a2;
    private static Amador a3;
    private static Semiprofissional sp1;
    private static Semiprofissional sp2;
    private static Semiprofissional sp3;
    private static Profissional p1;
    private static Profissional p2;
    private static Profissional p3;
    private static Profissional p4;
    private static Profissional p5;

    @Test

    public void testCompareMenor() {
        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);
        a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);
        ComparatorPremioReverse comparator = new ComparatorPremioReverse();

        //Act
        int result = comparator.compare(sp1, a1);

        //Assert
        assertTrue(result > 0);
    }

    @Test
    public void testCompareMaior() {
        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);
        a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);
        ComparatorPremioReverse comparator = new ComparatorPremioReverse();

        //Act
        int result = comparator.compare(a1, sp1);

        //Assert
        assertTrue(result < 0);
    }

    @Test
    public void testCompareIgual() {
        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 3, 70,
                10, 200.5f);
        a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);
        ComparatorPremioReverse comparator = new ComparatorPremioReverse();

        //Act
        int result = comparator.compare(a1, sp1);

        //Assert
        assertTrue(result == 0);
    }

}