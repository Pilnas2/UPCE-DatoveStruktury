package Semestralka;

import java.util.Iterator;

/**
 *
 * @author marti
 */
public class ProgVyrobniProces {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbstrDoubleList<Object> p = new AbstrDoubleList<>();

        p.vlozPrvni("4");
        p.vlozPrvni("3");
        p.vlozPrvni("2");
        p.vlozPrvni("1");
        p.vlozPosledni("5");

        p.zpristupniPrvni();
        p.zpristupniPosledni();

        System.out.println(p.zpristupniPrvni());
        System.out.println(p.zpristupniNaslednika());
        System.out.println(p.zpristupniNaslednika());
        System.out.println(p.zpristupniNaslednika());
        System.out.println(p.zpristupniPosledni());
        System.out.println("//");
        System.out.println(p.zpristupniPosledni());
        System.out.println(p.odeberNaslednika());
        System.out.println("//");
        System.out.println(p.zpristupniPrvni());
        System.out.println(p.zpristupniNaslednika());
        System.out.println(p.zpristupniPredchudce());
        System.out.println("----------------------------");

    }

}
