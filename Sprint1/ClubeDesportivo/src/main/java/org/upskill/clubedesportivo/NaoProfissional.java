package org.upskill.clubedesportivo;

public abstract class NaoProfissional extends Atleta {

    private int antiguidade;
    private static int limMinAntiguidade = 5;
    private static int limRecente = 10;
    private static int limAntigo = 20;


    private static int ANTIGUIDADE_POR_OMISSAO = 5;

    public NaoProfissional(String nome, int idCivil, int idade, String genero,
                           String actividade, String objectivoTreino,
                           float valorPremio, int fcr, int antiguidade) {
        super(nome, idCivil, idade, genero, actividade, objectivoTreino,
                valorPremio, fcr);
        this.antiguidade = antiguidade;
    }

    public NaoProfissional() {
        super();
        this.antiguidade = ANTIGUIDADE_POR_OMISSAO;
    }

    public float determinarPercentagemVariavel() {
        if (antiguidade >= limMinAntiguidade && antiguidade <= limRecente) {
            return 0.02f;
        } else {
            if (antiguidade > limRecente && antiguidade <= limAntigo) {
                return 0.08f;
            } else {
                return 0.2f;
            }
        }
    }

    @Override
    public String toString() {
        return String.format(super.toString() +
                "%n\tAntiguidade: %d%n", antiguidade);
    }
}
