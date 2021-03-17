package org.upskill.clubedesportivo;

public class Amador extends NaoProfissional {

    private static int totalAmadores = 0;

    public Amador(String nome, int idCivil, int idade, String genero,
                  String actividade, String objectivoTreino,
                  float valorPremio, int fcr, int antiguidade) {
        super(nome, idCivil, idade, genero, actividade, objectivoTreino,
                valorPremio, fcr, antiguidade);
        totalAmadores++;
    }

    public Amador() {
        super();
        totalAmadores++;
    }

    public int getTotalAmadores() {
        return totalAmadores;
    }

    private static final int valorMinimo = 5;
    private static final float percentagemSegundaParcela = 0.07f;

    public float determinarValorPagamento() {
        return (valorPremio * determinarPercentagemVariavel()) + (
                valorPremio * percentagemSegundaParcela);
    }

    @Override
    public float calcularPagamento() {
        if (determinarValorPagamento() < valorMinimo) {
            return valorMinimo;
        } else {
            return determinarValorPagamento();
        }
    }

    @Override
    public String toString() {
        return String.format("Amador%n" + super.toString() + " %n");
    }
}
