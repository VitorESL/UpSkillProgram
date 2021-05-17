package org.upskill.clubedesportivo;

public class Profissional extends Atleta implements CalculoIRS {
    private float percentagemParcelaVariavel = 0.20f;
    private float parcelaFixa;
    private static final float txIRS = 0.1f;
    private static int totalProfissionais = 0;

    public Profissional() {
        super();
        totalProfissionais++;
    }

    public Profissional(String nome, int idCivil, int idade, String genero,
                        String actividade, String objectivoTreino,
                        float valorPremio, int fcr, float parcelaFixa) {
        super(nome, idCivil, idade,
                genero, actividade, objectivoTreino, valorPremio, fcr);
        this.parcelaFixa = parcelaFixa;
        totalProfissionais++;
    }

    public int getTotalProfissionais() {
        return totalProfissionais;
    }

    public float determinarParcelaVariavel() {
        return getValorPremio() * percentagemParcelaVariavel;
    }

    @Override
    public float calcularDescontoIRS() {
        return parcelaFixa * txIRS;
    }

    @Override
    public float calcularPagamento() {

        return (float) (parcelaFixa + determinarParcelaVariavel());
    }


    public float calcularPagamentoComDesconto() {
        return calcularPagamento() - calcularDescontoIRS();
    }

    public String toString() {
        return String.format("Profissional %n" + super.toString()
                        + "%n\tPERCENTAGEM SOBRE A PARCELA VARIÁVEL: %n",
                percentagemParcelaVariavel);
    }
}
