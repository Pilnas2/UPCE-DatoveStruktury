/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Semestralka;

/**
 *
 * @author marti
 */
public enum enumReorg {
    AGREGACE("agregace"), DEKOMPOZICE("dekompozice");
    
    private final String string;

    private enumReorg(String string) {
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
