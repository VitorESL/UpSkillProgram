/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.upskill.clubedesportivo;

public class Semiprofissional extends NaoProfissional implements CalculoIRS {

    private float parcelaFixa;
    private int antiguidade;

    private static final float txIRS = 0.1f;


    public Semiprofissional(String nome, int idCivil,
                            int idade, String genero, String actividade,
                            String objectivoTreino, float valorPremio,
                            int fcr, int antiguidade, float parcelaFixa) {
        super(nome, idCivil, idade, genero, actividade, objectivoTreino,
                valorPremio, fcr, antiguidade);
        this.parcelaFixa = parcelaFixa;

    }

    public float calcularDescontoIRS() {
        return parcelaFixa * txIRS;
    }


    public float calcularPagamentoComDesconto() {
        return calcularPagamento() - calcularDescontoIRS();
    }

    @Override
    public float calcularPagamento() {
        return parcelaFixa + (determinarPercentagemVariavel() * parcelaFixa);
    }

    @Override
    public String toString() {
        return String.format("Semi-profissional %n" + super.toString() +
                "\tPARCELA FIXA: %.1f€;%n", parcelaFixa);
    }
}
