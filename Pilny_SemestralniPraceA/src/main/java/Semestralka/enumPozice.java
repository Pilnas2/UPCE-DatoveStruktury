/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Semestralka;

/**
 *
 * @author marti
 */
public enum enumPozice {
    PRVNI("první"),
    POSLEDNI("poslední"),
    PREDCHUDCE("předchůdce"),
    NASLEDNIK("následník"),
    AKTUALNI("aktuální");
    
    private String string;

    private enumPozice(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }

}
