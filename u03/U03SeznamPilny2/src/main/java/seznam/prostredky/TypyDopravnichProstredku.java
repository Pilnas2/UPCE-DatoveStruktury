/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seznam.prostredky;

/**
 *
 * @author marti
 */
public enum TypyDopravnichProstredku {
    NAKLADNI_AUTOMOBIL("truck"),OSOBNI_AUTOMOBIL("osobní auto"),TRAKTOR("traktor");
    
    
    private String nazev;
    
    public String nazev(){
        return nazev;
    }

    private TypyDopravnichProstredku(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }

    
    

    
    
}
