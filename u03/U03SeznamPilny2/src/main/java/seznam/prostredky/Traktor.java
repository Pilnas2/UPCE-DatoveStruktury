/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seznam.prostredky;

/**
 *
 * @author marti
 */
public final class Traktor extends Prostredek {

    private int tah;

    public Traktor(double hmotnost, String spz) {
        super(TypyDopravnichProstredku.TRAKTOR, hmotnost, spz);
    }

    public Traktor(final String spz, final double hmotnost, final int tah) throws IllegalArgumentException {
            this(hmotnost, spz);
            this.tah = tah;
            if(tah == 0){
            throw new IllegalArgumentException("špatný argument.");
            }
    }

    public static boolean check(int kontrola) {
        return (kontrola > 0);
    }

    public double getTah() {
        return tah;
    }

    public void setTah(int tah) {
        this.tah = tah;
    }

    @Override
    public String toString() {
        return super.toString() + " tah=" + tah;
    }

    public Traktor(TypyDopravnichProstredku typ) {
        super(typ);
    }

}
