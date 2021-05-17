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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * @author acris
 */

public class ClubeDesportivoTest {

    private static ClubeDesportivo porto;
    private static Amador a1;
    private static Semiprofissional sp1;


    @Test
    public void testAddAtleta() {
        //Arrange

        ArrayList<Atleta> atletas = new ArrayList<>();
        porto = new ClubeDesportivo("Porto", atletas, 1893, 9, 28);
        a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);

        //Act
        porto.addAtleta(a1);

        //Assert
        //assertEquals(porto.getAtletas().size(),1);
        assertEquals(porto.getAtletas().get(0), a1);
    }

}
