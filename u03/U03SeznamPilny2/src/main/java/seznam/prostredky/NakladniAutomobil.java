/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seznam.prostredky;

/**
 *
 * @author marti
 */
public final class NakladniAutomobil extends Prostredek{
    
    private int pocetNaprav;

    public NakladniAutomobil(String spz, double hmotnost) {
        super(TypyDopravnichProstredku.NAKLADNI_AUTOMOBIL, hmotnost, spz);
    }

    public NakladniAutomobil(String spz, int pocetNaprav, double hmotnost) {
        this(spz, hmotnost);
        this.pocetNaprav = pocetNaprav;
        if(spz == ""){
            throw new IllegalArgumentException("nesprávný argument");
        } else if(pocetNaprav < 0){
            throw new IllegalArgumentException("nesprávný argument");
        } else if(pocetNaprav == 0){
            throw new IllegalArgumentException("pocetNaprav == 0");
        }
    }

    public int getPocetNaprav() {
        return pocetNaprav;
    }

    public void setPocetNaprav(int pocetNaprav) {
        this.pocetNaprav = pocetNaprav;
    }

    @Override
    public String toString() {
        return super.toString() + ", početNáprav=" + pocetNaprav;
    }
    
}
