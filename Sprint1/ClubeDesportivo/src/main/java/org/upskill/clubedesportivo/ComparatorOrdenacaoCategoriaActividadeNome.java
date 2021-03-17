package org.upskill.clubedesportivo;

import java.util.Comparator;

public class ComparatorOrdenacaoCategoriaActividadeNome implements Comparator<Atleta> {


    @Override
    public int compare(Atleta a1, Atleta a2) {
        String categoria1 = a1.getClass().getSimpleName();
        String categoria2 = a2.getClass().getSimpleName();

        String actividade1 = a1.getActividade();
        String actividade2 = a2.getActividade();

        String nome1 = a1.getNome();
        String nome2 = a2.getNome();

        int result1 = categoria1.compareTo(categoria2);
        int result2 = result1 == 0 ?
                actividade1.compareTo(actividade2):
                result1;

        return (result2 == 0) ? nome1.compareTo(nome2) : result2;

    }
}
