/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import BVS.eTypProhl;
import java.util.Iterator;
import pamatky.Pamatky;
import pamatky.Zamek;
import pamatky.eTypKey;

/**
 *
 * @author marti
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Pamatky pam = new Pamatky();
        pam.nastavKlic(eTypKey.GPS);

        Zamek z1 = new Zamek("C", "Slatinb", "N10 55.0717 E015 48.6536");
        Zamek z2 = new Zamek("X", "Slatina", "N56 04.8127 E014 15.3027");
        Zamek z3 = new Zamek("t", "123", "N49 26.4373 E013 22.4042");
        Zamek z4 = new Zamek("a", "1234", "N49 55.0717 E015 48.65369");
        Zamek z5 = new Zamek("s", "12345", "N49 55.0717 E015 48.65420");
        Zamek z6 = new Zamek("w", "123456", "N49 55.0717 E015 48.69420");
        Zamek z7 = new Zamek("b", "1234567", "N49 55.0717 E015 48.42690");
        Zamek z8 = new Zamek("p", "12345678", "N49 55.0717 E015 48.4269");

        pam.vlozZamek(z1);
        pam.vlozZamek(z2);
        pam.vlozZamek(z3);
        pam.vlozZamek(z4);
        pam.vlozZamek(z5);
        pam.vlozZamek(z6);
        pam.vlozZamek(z7);
        pam.vlozZamek(z8);
        System.out.println("/n");
//        pam.importDatZTXT();
//        Iterator it = pam.VytvorIterator(eTypProhl.SIRKA);
        // pam.prebuduj();
        Iterator it = pam.VytvorIterator(eTypProhl.SIRKA);
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(pam.najdiZamek("Slatina"));
        System.out.println("/n");
        System.out.println(pam.najdiNejbliz("Slatina"));

        //System.out.println(z1.getGPS().compareTo(z2.getGPS()));
//        System.out.println("\n");
//        System.out.println(pam.najdiNejbliz("Slatinany"));
    }

}
