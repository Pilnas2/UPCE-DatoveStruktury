package cz.upce.fei.boop.u02klonovani;

import java.util.Objects;

/*
 * TODO Upravte třídu Vana tak vyhověla testu a byla klonovatelná
 * TODO Při upravách třídy dodržujte strukturu třídy podle editor-fold
 */
public class Vana implements Cloneable {

//<editor-fold defaultstate="collapsed" desc="Instanční proměnný/atributy">
    private final String nazevVany;
    private final Rozmer rozmer;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Konstruktory">
    public Vana(String nazevVany, Rozmer rozmer) {
        if (nazevVany == null || rozmer == null) {
            throw new NullPointerException("nula");
        }
        this.nazevVany = nazevVany;
        this.rozmer = rozmer;

    }

    public Vana(String nazevVany, double delka, double sirka, double vyska) {
        this(nazevVany, new Rozmer(delka, sirka, vyska));
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return "Vana{" + "nazev=" + nazevVany + ", rozmer=" + rozmer + '}';
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Metody get">
    public String getNazevVany() {
        return nazevVany;
    }

    public Rozmer getRozmer() {
        return rozmer;
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Metody equals a hashCode ">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.nazevVany);
        hash = 73 * hash + Objects.hashCode(this.rozmer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vana other = (Vana) obj;
        if (!Objects.equals(this.nazevVany, other.nazevVany)) {
            return false;
        }
        return Objects.equals(this.rozmer, other.rozmer);
    }
//</editor-fold>

    @Override
    public Vana clone() throws CloneNotSupportedException {
        return (Vana) super.clone();
    }

}
