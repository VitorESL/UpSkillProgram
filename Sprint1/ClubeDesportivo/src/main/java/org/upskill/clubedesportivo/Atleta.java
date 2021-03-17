/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.upskill.clubedesportivo;

public abstract class Atleta implements Actividade, Genero, ObjectivoTreino {

    /**
     * O nome do Atleta
     */
    private String nome;

    /**
     * A identificação civil do Atleta
     */
    private int idCivil;

    /**
     * A idade do Atleta
     */
    private int idade;

    /**
     * O género do Atleta
     */
    private String genero;

    /**
     * A actividade praticada pelo atleta
     */
    private String actividade;

    /**
     * O objectivo de treino do Atleta
     */
    private String objectivoTreino;

    /**
     * O valor total dos prémios arrecadados pelo atleta no mês
     */
    public float valorPremio;

    /**
     * A frequência cardíaca de repuso do atleta
     */
    private int fcr;


    /**
     * O nome do Atleta por omissão
     */
    private static final String NOME_POR_OMISSAO = "sem nome";

    /**
     * A identificação civil do Atleta por omissão
     */
    private static final int IDCIVIL_POR_OMISSAO = 0;

    /**
     * A idade do Atleta por omissão
     */
    private static final int IDADE_POR_OMISSAO = 18;

    /**
     * O género do Atleta por omissão
     */
    private static final String GENERO_POR_OMISSAO = "indefinido";

    /**
     * A actividade do Atleta por omissão
     */
    private static final String ACTIVIDADE_POR_OMISSAO = "Sem actividade associada";

    /**
     * O objectivo de treino do Atleta por omissão
     */
    private static final String OBJECTIVOTREINO_POR_OMISSAO = "Sem objectivo definido";

    /**
     * O valor arrecadado em prémios pelo Atleta por omissão
     */
    private static final float VALORPREMIO_POR_OMISSAO = 0;

    /**
     * A frequência cardíaca em repouso do Atleta por omissão
     */
    private static final int FCR_POR_OMISSAO = 0;

    /**
     * Inicializa um novo atleta com atributos por omissão
     */
    public Atleta() {
        nome = NOME_POR_OMISSAO;
        idCivil = IDCIVIL_POR_OMISSAO;
        idade = IDADE_POR_OMISSAO;
        genero = GENERO_POR_OMISSAO;
        actividade = ACTIVIDADE_POR_OMISSAO;
        objectivoTreino = OBJECTIVOTREINO_POR_OMISSAO;
        valorPremio = VALORPREMIO_POR_OMISSAO;
        fcr = FCR_POR_OMISSAO;
    }

    /**
     * Inicializa um novo Atleta com os atributos nome, idCivil, idade,
     * género, actividade, objectivo de treino, valor dos prémios e frequência
     * cardíaca em repouso
     *
     * @param nome
     * @param idCivil
     * @param idade
     * @param genero
     * @param actividade
     * @param objectivoTreino
     * @param valorPremio
     * @param fcr
     */
    public Atleta(String nome, int idCivil, int idade, String genero,
                  String actividade, String objectivoTreino, float valorPremio, int fcr) {
        this.nome = nome;
        this.idCivil = idCivil;
        this.idade = idade;
        this.genero = genero;
        this.actividade = actividade;
        this.objectivoTreino = objectivoTreino;
        this.valorPremio = valorPremio;
        this.fcr = fcr;

    }

    /**
     * Permite calcular a primeira variável necessária para o cálculo da FCM
     *
     * @return primeiraVariavel
     */
    private float getPrimeiraVariavel() {
        if (getActividade().equals(Actividade.CAMINHADA) || getActividade().equals(Actividade.CORRIDA)) {
            return 208.75f;
        } else if (getActividade().equals(Actividade.CICLISMO)) {
            if (getGenero().equals(Genero.FEMININO)) {
                return 189;
            } else {
                return 202;
            }
        } else {
            return 204;
        }
    }

    /**
     * Permite calcular a segunda variável necessária para o cálculo da FCM
     *
     * @return segundaVariavel
     */
    private float getSegundaVariavel() {
        if (getActividade().equals(Actividade.CAMINHADA) || getActividade().equals(Actividade.CORRIDA)) {
            return 0.73f;
        } else if (getActividade().equals(Actividade.CICLISMO)) {
            if (getGenero().equals(Genero.FEMININO)) {
                return 0.56f;
            } else {
                return 0.72f;
            }
        } else {
            return 1.7f;
        }
    }

    /**
     * Permite calcular a frequência cardíaca máxima do Atleta
     *
     * @return fcm
     */
    public float calculoFCM() {
        return getPrimeiraVariavel() - (getSegundaVariavel() * getIdade());
    }

    /**
     * Permite determinar a intensidade de treino do atleta
     *
     * @return it
     */
    public float determinarIT() {
        if (objectivoTreino.equals(ObjectivoTreino.QUEIMA_GORDURA)) {
            return 0.6f;
        } else {
            return 0.75f;
        }

    }

    /**
     * Permite calcular a frequência cardíaca de trabalho do Atleta
     *
     * @return fct
     */
    public float calculoFCT() {
        return calculoFCM() + (determinarIT() * (calculoFCM() - getFcr()));
    }

    /**
     * Permite calcular o pagamento a efetuar ao atleta
     *
     * @return Pagamento ao Atleta
     */
    public abstract float calcularPagamento();

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
     * @return the idCivil
     */
    public int getIdCivil() {
        return idCivil;
    }

    /**
     * @param idCivil the idCivil to set
     */
    public void setIdCivil(int idCivil) {
        this.idCivil = idCivil;
    }

    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the actividade
     */
    public String getActividade() {
        return actividade;
    }

    /**
     * @param actividade the actividade to set
     */
    public void setActividade(String actividade) {
        this.actividade = actividade;
    }

    /**
     * @return the valorPremio
     */
    public float getValorPremio() {
        return valorPremio;
    }

    /**
     * @param valorPremio the valorPremio to set
     */
    public void setValorPremio(float valorPremio) {
        this.valorPremio = valorPremio;
    }

    /**
     * @return the fcr
     */
    public int getFcr() {
        return fcr;
    }

    /**
     * @param fcr the fcr to set
     */
    public void setFcr(int fcr) {
        this.fcr = fcr;
    }

    public int compareTo(Atleta outroAtleta) {
        return this.getNome().compareTo(outroAtleta.getNome());
    }

    /**
     * Devolve os atributos do atleta
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("NOME: %s;%n" +
                        "\tID CIVIL: %d;%n" +
                        "\tGENERO: %s;%n" +
                        "\tIDADE: %d;%n" +
                        "\tACTIVIDADE: %s;%n" +
                        "\tOBJECTIVO DO TREINO: %s;%n" +
                        "\tVALOR DO PRÉMIO: %.2f€;%n" +
                        "\tFREQUÊNCIA CARDIACA DE REPOUSO: %d.",
                nome, idCivil,
                genero, idade, actividade,
                objectivoTreino, valorPremio, fcr);
    }

}
