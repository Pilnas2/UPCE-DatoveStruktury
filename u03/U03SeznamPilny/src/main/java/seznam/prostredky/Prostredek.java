package seznam.prostredky;

import java.util.Locale;
import java.util.Objects;

/**
 * TODO Doplnit alespoň jednoho dalšího potomka podle vlastního výběru.
 *
 * @author karel@simerda.cz
 */
public abstract sealed class Prostredek implements Cloneable
        permits OsobniAutomobil, NakladniAutomobil, Traktor {

    private final TypyDopravnichProstredku typ;
    private double hmotnost;
    private String spz;

    public Prostredek(TypyDopravnichProstredku typ, double hmotnost, String spz) {
        this.typ = typ;
        this.hmotnost = hmotnost;
        this.spz = spz;
    }

    public Prostredek(TypyDopravnichProstredku typ) {
        this.typ = typ;
    }

    public TypyDopravnichProstredku getTyp() {
        return typ;
    }

    public double getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(double hmotnost) {
        this.hmotnost = hmotnost;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    @Override
    public String toString() {
        return String.format("typ=%s, SPZ=%s, hmotnost=%6.2f",
                typ, spz, hmotnost);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.typ);
        hash = 83 * hash + Objects.hashCode(this.hmotnost);
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.hmotnost) ^ (Double.doubleToLongBits(this.hmotnost) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.spz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prostredek other = (Prostredek) obj;
//        if (Double.doubleToLongBits(this.hmotnost) != Double.doubleToLongBits(other.hmotnost)) {
//            return false;
//        }
        
        if (!Objects.equals(this.spz, other.spz)) {
            return false;
        }
        if (!Objects.equals(this.hmotnost, other.hmotnost)) {
            return true;
        }
        if (this.typ != other.typ) {
            return false;
        }
        if(this.hmotnost != other.hmotnost){
            return false;
        }
        return true;
    }

}
