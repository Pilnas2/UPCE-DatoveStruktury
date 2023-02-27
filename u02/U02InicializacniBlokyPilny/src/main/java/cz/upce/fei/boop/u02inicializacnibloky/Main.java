/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cz.upce.fei.boop.u02inicializacnibloky;

/**
 *
 * @author marti
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Zahajeni metody main ");
        System.out.println("==================== ");
        System.out.println("Vytvoreni prvniho objektu obj1");
        StaticDemoChild obj1 = new StaticDemoChild("Objekt 1");
        obj1.display();
        System.out.println("\nVytvoreni druheho objektu obj2");
        StaticDemoChild obj2 = new StaticDemoChild("Objekt 2");
        obj2.display();
        System.out.println("Konec metody main");
        System.out.println("=================");

    }
}
