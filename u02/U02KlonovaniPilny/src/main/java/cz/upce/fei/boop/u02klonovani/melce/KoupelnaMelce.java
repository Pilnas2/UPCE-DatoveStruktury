package cz.upce.fei.boop.u02klonovani.melce;

import cz.upce.fei.boop.u02klonovani.MojeException;
import cz.upce.fei.boop.u02klonovani.Rozmer;
import cz.upce.fei.boop.u02klonovani.Vana;
import java.util.Objects;

/**
 * TODO Upravte třídu KoupelnaMelce tak vyhověla testu a byla mělce klonovatelná
 * TODO Při upravách třídy dodržujte strukturu třídy podle editor-fold
 */
public class KoupelnaMelce implements Cloneable {

//<editor-fold defaultstate="collapsed" desc="instanční proměnný/atributy">
    private String nazev;
    private Rozmer rozmer;
    private Vana vana;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="konstruktory">
    public KoupelnaMelce(String nazev, Rozmer rozmer, Vana vana) {
        Objects.requireNonNull(nazev);
        Objects.requireNonNull(rozmer);
        Objects.requireNonNull(vana);
        if (nazev.isEmpty()) {
            throw new MojeException("Not supported yet.");
        }

        this.nazev = nazev;
        this.rozmer = rozmer;
        this.vana = vana;
    }

    public KoupelnaMelce(String nazev, double delka, double sirka, double vyska, Vana vana) throws MojeException {
        this(nazev, new Rozmer(delka, sirka, vyska), vana);
    }

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="klonování">
    @Override
    protected KoupelnaMelce clone() throws CloneNotSupportedException {
        return (KoupelnaMelce) super.clone();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="metoda toString">
    @Override
    public String toString() {
        return "KoupelnaMelce{" + "nazev=" + nazev + ", rozmer=" + rozmer + ", vana=" + vana + '}';
    }

//</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="Metody get/set ">
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Rozmer getRozmer() {
        return rozmer;
    }

    public void setRozmer(Rozmer rozmer) {
        this.rozmer = rozmer;
    }

    public Vana getVana() {
        return vana;
    }

    public void setVana(Vana vana) {
        this.vana = vana;
    }
//</editor-fold>

}
