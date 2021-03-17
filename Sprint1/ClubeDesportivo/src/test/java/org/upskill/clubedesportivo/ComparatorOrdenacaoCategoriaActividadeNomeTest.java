package org.upskill.clubedesportivo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author acris
 */

public class ComparatorOrdenacaoCategoriaActividadeNomeTest {

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
        ComparatorOrdenacaoCategoriaActividadeNome comparator = new ComparatorOrdenacaoCategoriaActividadeNome();

        //Act
        int result = comparator.compare(a1, sp1);

        //Assert
        assertTrue(result < 0);

    }

    public void testCompareMaior() {
        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);
        a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);
        ComparatorOrdenacaoCategoriaActividadeNome comparator = new ComparatorOrdenacaoCategoriaActividadeNome();

        //Act
        int result = comparator.compare(sp1, a1);

        //Assert
        assertTrue(result > 0);

    }

    public void testCompareIgual() {
        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);

        ComparatorOrdenacaoCategoriaActividadeNome comparator = new ComparatorOrdenacaoCategoriaActividadeNome();

        //Act
        int result = comparator.compare(sp1, sp1);

        //Assert
        assertTrue(result == 0);

    }

    @Test
    public void testComparatorTodos() {
        //Arrange
        sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);
        sp2 = new Semiprofissional("andré", 6698,
                40, Genero.MASCULINO, Actividade.CICLISMO,
                ObjectivoTreino.QUEIMA_GORDURA, 325.1f, 68,
                5, 500);
        sp3 = new Semiprofissional("ana", 9847,
                29, Genero.FEMININO, Actividade.CICLISMO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                825.45f, 64, 10, 1000);

        a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);
        a2 = new Amador("vanessa", 3214, 18, Genero.FEMININO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 650.25f, 63, 10);
        a3 = new Amador("mário", 5412, 60, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.QUEIMA_GORDURA, 375.45f, 58, 26);

        p1 = new Profissional("joana", 6677, 40, Genero.FEMININO,
                Actividade.CICLISMO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                350.45f, 55, 300.5f);
        p2 = new Profissional("andreia", 3521, 25, Genero.FEMININO,
                Actividade.CAMINHADA, ObjectivoTreino.QUEIMA_GORDURA, 325.7f, 57, 250.45f);
        p3 = new Profissional("antonio", 6478, 41, Genero.MASCULINO,
                Actividade.NATACAO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                700.50f, 60, 150.25f);
        p4 = new Profissional("mariana", 6677, 40, Genero.FEMININO,
                Actividade.NATACAO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                410.3f, 55, 300.5f);
        p5 = new Profissional("antunes", 6677, 40, Genero.FEMININO,
                Actividade.CAMINHADA, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                150.8f, 55, 300.5f);

        ArrayList<Atleta> lista = new ArrayList<>();
        lista.add(sp1);
        lista.add(p1);
        lista.add(a1);
        lista.add(sp2);
        lista.add(p2);
        lista.add(a2);
        lista.add(sp3);
        lista.add(p3);
        lista.add(a3);
        lista.add(p4);
        lista.add(p5);
        ComparatorOrdenacaoCategoriaActividadeNome comparator = new ComparatorOrdenacaoCategoriaActividadeNome();

        //Act
        Collections.sort(lista, comparator);

        //Assert
        assertEquals(lista.get(0), a1);
        assertEquals(lista.get(1), a3);
        assertEquals(lista.get(2), a2);
        assertEquals(lista.get(3), p2);
        assertEquals(lista.get(4), p5);
        assertEquals(lista.get(5), p1);
        assertEquals(lista.get(6), p3);
        assertEquals(lista.get(7), p4);
        assertEquals(lista.get(8), sp3);
        assertEquals(lista.get(9), sp2);
        assertEquals(lista.get(10), sp1);
    }

}