package cz.upce.fei.boop.u02klonovani.hluboce;

import cz.upce.fei.boop.u02klonovani.MojeException;
import cz.upce.fei.boop.u02klonovani.Rozmer;
import cz.upce.fei.boop.u02klonovani.Vana;
import java.util.Objects;

/**
 * TODO Upravte třídu KoupelnaHlubuce tak vyhověla testu a byla hluboce
 * klonovatelná TODO Při upravách třídy dodržujte strukturu třídy podle
 * editor-fold
 */
public class KoupelnaHluboce implements Cloneable {

//<editor-fold defaultstate="collapsed" desc="instanční proměnný/atributy">
    private String nazev;
    private Rozmer rozmer;
    private Vana vana;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="konstruktory">
    public KoupelnaHluboce(String nazev, Rozmer rozmer, Vana vana) {
        Objects.requireNonNull(nazev);
        Objects.requireNonNull(rozmer);
        Objects.requireNonNull(vana);
        if (nazev.isEmpty()) {
            throw new MojeException("Not supported yet!");
        }

        this.nazev = nazev;
        this.rozmer = rozmer;
        this.vana = vana;
    }

    public KoupelnaHluboce(String nazev, double delka, double sirka, double vyska, Vana vana) throws MojeException {
        this(nazev, new Rozmer(delka, sirka, vyska), vana);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="metoda klonovani">

    public KoupelnaHluboce clone() throws CloneNotSupportedException {
        final KoupelnaHluboce k = (KoupelnaHluboce) super.clone();
        k.vana = vana.clone();
        return k;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="¨metoda toString"

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="metody get">
//</editor-fold>
    public String getNazev() {
        return nazev;
    }

    public Rozmer getRozmer() {
        return rozmer;
    }

    public Vana getVana() {
        return vana;
    }

}
