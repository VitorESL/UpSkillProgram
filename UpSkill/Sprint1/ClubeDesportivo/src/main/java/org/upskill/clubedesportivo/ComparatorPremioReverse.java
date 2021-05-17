package org.upskill.clubedesportivo;

import java.util.Comparator;

public class ComparatorPremioReverse implements Comparator<Atleta> {

    @Override
    public int compare(Atleta a1, Atleta a2) {
        double premioAtleta1 = a1.getValorPremio();
        double premioAtleta2 = a2.getValorPremio();

        return (int) (premioAtleta1 - premioAtleta2);
    }
}
