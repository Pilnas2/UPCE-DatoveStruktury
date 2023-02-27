package seznam.prostredky;

import util.Barva;

/**
 *
 * @author marti
 */
public final class OsobniAutomobil extends Prostredek {

    private Barva barva;
    private int pocetSedadel;

    public OsobniAutomobil(double hmotnost, String spz) {
        super(TypyDopravnichProstredku.OSOBNI_AUTOMOBIL, hmotnost, spz);
    }

    public OsobniAutomobil(final String spz, final Barva barva,
            final int pocetSedadel, final double hmotnost) {
        this(hmotnost, spz);
        this.barva = barva;
        this.pocetSedadel = pocetSedadel;
        if (pocetSedadel == 0) {
            throw new IllegalArgumentException("nesprávný argument");
        } else if (barva == null) {
            throw new NullPointerException("nula");

        } else if (spz == null) {
            throw new NullPointerException("nula");
        } else if (spz.isBlank()) {
            throw new IllegalArgumentException("nesprávný argument");

        } else if (hmotnost == 0) {
            throw new IllegalArgumentException("nesprávný argument");

        }

    }

    public OsobniAutomobil(String spz, Barva barva, double hmotnost) {
        this(hmotnost, spz);
        this.barva = barva;

    }

    public static boolean check(int kontrola) {
        return (kontrola > 0);
    }

    public Barva getBarva() {
        return barva;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public int getPocetSedadel() {
        return pocetSedadel;
    }

    public void setPocetSedadel(int pocetSedadel) {
        this.pocetSedadel = pocetSedadel;
    }

    @Override
    public String toString() {
        return super.toString() + ",  barva=" + barva + ", početSedadel=" + pocetSedadel;
    }
    

}
