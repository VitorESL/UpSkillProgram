package org.upskill.clubedesportivo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ArrayList<Atleta> atletas = new ArrayList<>();
        ClubeDesportivo porto = new ClubeDesportivo("Porto", atletas, 1893, 9, 28);

        /*
        Retornar o nome do clube
         */

        System.out.println("Nome do clube desportivo: " + porto.getNome());

        /*
        Inserir um novo atleta no contentor
         */

        Semiprofissional sp1 = new Semiprofissional("hulk", 1122,
                34, Genero.MASCULINO, Actividade.CORRIDA,
                ObjectivoTreino.QUEIMA_GORDURA, 650.75f, 70,
                10, 200.5f);
        Semiprofissional sp2 = new Semiprofissional("andré", 6698,
                40, Genero.MASCULINO, Actividade.CICLISMO,
                ObjectivoTreino.QUEIMA_GORDURA, 325.1f, 68,
                5, 500);
        Semiprofissional sp3 = new Semiprofissional("ana", 9847,
                29, Genero.FEMININO, Actividade.CICLISMO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                825.45f, 64, 10, 1000);

        Amador a1 = new Amador("josé", 3355,
                20, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 3, 80, 6);
        Amador a2 = new Amador("vanessa", 3214, 18, Genero.FEMININO, Actividade.NATACAO,
                ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA, 650.25f, 63, 10);
        Amador a3 = new Amador("mário", 5412, 60, Genero.MASCULINO, Actividade.NATACAO,
                ObjectivoTreino.QUEIMA_GORDURA, 375.45f, 58, 26);


        Profissional p1 = new Profissional("joana", 6677, 40, Genero.FEMININO,
                Actividade.CICLISMO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                350.45f, 55, 300.5f);
        Profissional p2 = new Profissional("andreia", 3521, 25, Genero.FEMININO,
                Actividade.CAMINHADA, ObjectivoTreino.QUEIMA_GORDURA, 325.7f, 57, 250.45f);
        Profissional p3 = new Profissional("antonio", 6478, 41, Genero.MASCULINO,
                Actividade.NATACAO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                700.50f, 60, 150.25f);
        Profissional p4 = new Profissional("mariana", 6677, 40, Genero.FEMININO,
                Actividade.NATACAO, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                410.3f, 55, 300.5f);
        Profissional p5 = new Profissional("antunes", 6677, 40, Genero.FEMININO,
                Actividade.CAMINHADA, ObjectivoTreino.TRABALHAR_CAPACIDADE_RESPIRATORIA,
                150.8f, 55, 300.5f);

        porto.addAtleta(sp1);
        porto.addAtleta(sp2);
        porto.addAtleta(sp3);
        porto.addAtleta(a1);
        porto.addAtleta(a2);
        porto.addAtleta(a3);
        porto.addAtleta(p1);
        porto.addAtleta(p2);
        porto.addAtleta(p3);
        porto.addAtleta(p4);
        porto.addAtleta(p5);

        /*
        Retornar uma lista de atletas, ordenada alfabeticamente por nome,
        usando a interface nativa do Java Comparable;
         */

        System.out.println("\n### Ordenação Alfabética dos Atletas ###");
        Collections.sort(atletas, Atleta::compareTo);
        listarNome(atletas);


        /*
        Retornar uma lista de atletas, ordenada inversamente pelo valor dos
        prémios usando a interface nativa do Java Comparator;
         */

        System.out.println("\n### Ordenação Inversa dos Prémios dos Atletas ###");
        ComparatorPremioReverse comparatorPremioReverse = new ComparatorPremioReverse();
        Collections.sort(atletas, Collections.reverseOrder(comparatorPremioReverse));
        listarPremios(atletas);

        /*
        Retornar o valor total, para efeitos de IRS, da totalidade dos atletas;
         */

        double somaIRS = 0;
        for (Atleta atleta : atletas) {
            if (atleta instanceof Profissional || atleta instanceof Semiprofissional) {
                somaIRS += ((CalculoIRS) atleta).calcularDescontoIRS();
            }
        }
        System.out.println(String.format("O valor total para efeitos de IRS é: %n%.2f€. ", somaIRS));

        /*
        Retornar uma lista de atletas do clube,
        ordenada alfabeticamente por categoria, modalidade e nome.
         */

        System.out.println("\n### Ordenação Alfabética Categoria, Actividade e Nomes ###");
        ComparatorOrdenacaoCategoriaActividadeNome comparatorOrdenacaoCategoriaActividadeNome = new ComparatorOrdenacaoCategoriaActividadeNome();
        Collections.sort(atletas, comparatorOrdenacaoCategoriaActividadeNome);
        listarAtletas(atletas);


        /*
        Obter o nome, a FCM e as FCT de cada atleta semiprofissional e amador;
         */

        System.out.println("\n### Nome, FCM e FCT dos Atletas###");
        for (Atleta atleta : atletas) {
            if (atleta instanceof Amador || atleta instanceof Semiprofissional) {
                System.out.println(String.format("Nome do atleta: %s%n" +
                                "\tFrequência Cardíaca Máxima: %.2f%n" +
                                "\tFrequência Cardíaca de Repouso: %.2f. %n",
                        atleta.getNome(), atleta.calculoFCM(), atleta.calculoFCT()));
            }
        }

        /*
        Obter o nome e o valor a pagar de cada atleta.
         */

        System.out.println("\n### Valor a pagar a cada Atleta###");
        for (Atleta atleta : atletas) {
            System.out.println(String.format("Nome do atleta: %s%n" +
                            "\tCategoria: %s%n" +
                            "\t Valor a pagar: %.2f€ %n",
                    atleta.getNome(), atleta.getClass().getSimpleName(),
                    atleta.calcularPagamento(), atleta.calculoFCT()));
            ;
        }

        /*
        Apresentação das quantidades de instâncias de
        atletas amadores e profissionais criadas, sem percorrer o contentor;
         */

        int totalAmadores = a1.getTotalAmadores();
        int totalProfissionais = p1.getTotalProfissionais();

        System.out.println(String.format("No Clube existem %d Atletas Amadores e %d Atletas Profissionais.",
                totalAmadores, totalProfissionais));

        /*
        Calcular e apresentar o valor total a pagar a cada tipo de atleta (profissional, semiprofissional e amador),
        percorrendo apenas uma vez o contentor. Deve ser também calculado e apresentado o valor total a pagar a todos os atletas.
         */

        float somaProfissionais = 0;
        float somaSemiProfissionais = 0;
        float somaAmadores = 0;
        float somaTotal = 0;

        for (Atleta atleta : atletas) {
            if (atleta instanceof Amador) {
                somaAmadores += atleta.calcularPagamento();
            } else if (atleta instanceof Semiprofissional) {
                somaSemiProfissionais += atleta.calcularPagamento();
            } else {
                somaProfissionais += atleta.calcularPagamento();
            }
        }
        somaTotal = somaAmadores + somaSemiProfissionais + somaProfissionais;


        System.out.println(String.format("O Clube tem a pagar:" +
                        "%n\taos atletas Amadores o valor de %.2f€," +
                        "%n\taos atletas Semiprofissionais o valor de %.2f€ " +
                        "%n\te aos atletas Profissionais o valor de %.2f€, " +
                        "%n\ttotalizando um valor total de %.2f€.",
                somaAmadores, somaSemiProfissionais,
                somaProfissionais, somaTotal));

    }

    private static void listarAtletas(List<Atleta> lista) {
        for (Atleta atleta : lista) {
            System.out.println(String.format("Atleta: %s", atleta.toString()));
        }
    }

    private static void listarPremios(List<Atleta> lista) {
        for (Atleta atleta : lista) {
            System.out.println(String.format("Nome do Atleta: %s %n Prémio: %.2f €", atleta.getNome(), atleta.getValorPremio()));
        }
    }

    private static void listarNome(List<Atleta> lista) {
        for (Atleta atleta : lista) {
            System.out.println(String.format("Atleta: %s", atleta.getNome()));
        }
    }

}
