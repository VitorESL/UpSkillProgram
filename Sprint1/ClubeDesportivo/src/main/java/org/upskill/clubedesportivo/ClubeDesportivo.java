package org.upskill.clubedesportivo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.upskill.clubedesportivo.Atleta;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class ClubeDesportivo {

    private String nome;
    private ArrayList<Atleta> atletas;
    private static int totalAtletas = 0;
    private int anoFundacao;
    private int mesFundacao;
    private int diaFundacao;

    private final String NOME_POR_OMISSAO = "sem nome";
    private final int ANO_FUNDACAO_POR_OMISSAO = 1900;
    private final int MES_FUNDACAO_POR_OMISSAO = 1;
    private final int DIA_FUNDACAO_POR_OMISSAO = 1;


    public ClubeDesportivo(String nome, ArrayList atletas, int anoFundacao, int mesFundacao, int diaFundacao) {
        this.nome = nome;
        this.atletas = atletas;
        this.anoFundacao = anoFundacao;
        this.mesFundacao = mesFundacao;
        this.diaFundacao = diaFundacao;
    }

    public ClubeDesportivo() {
        this.nome = NOME_POR_OMISSAO;
        this.atletas = getAtletas();
    }

    public void addAtleta(Atleta novoAtleta) {
        atletas.add(novoAtleta);
        totalAtletas++;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the atletas
     */
    public ArrayList<Atleta> getAtletas() {
        return atletas;
    }

    /**
     * @param atletas the atletas to set
     */
    public void setAtletas(ArrayList<Atleta> atletas) {
        this.atletas = atletas;
    }

    public String dataFundacao(int ano, int mes, int dia) {
        return String.format("%d/%d/%d", dia, mes, ano);
    }


    /**
     * @return the totalAtletas
     */
    public int getTotalAtletas() {
        return totalAtletas;
    }


    @Override
    public String toString() {
        return String.format("NOME: %s;%n " +
                        "DATA DE FUNDACAO: %s;%n" +
                        "TOTAL ATLETAS: %d,%n", getNome(),
                dataFundacao(diaFundacao, mesFundacao, anoFundacao),
                getTotalAtletas());

    }
}

