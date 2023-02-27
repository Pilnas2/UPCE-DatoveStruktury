package cz.upce.fei.boop.u02inicializacnibloky;

public class StaticDemo {

    private static String n;

    static {
        System.out.println("1. Zahajeni statickeho inicializacniho bloku predka");
        System.out.println("    a vlastně zahajeni statickeho konstruktoru");
        System.out.println("   Hodnota staticke promenne pred: " + n);
        n = "Hello";
        System.out.println("    a po definici: " + n);
        System.out.println("2. Konec statickeho bloku predka\n "
                + "   a vlastne ukonceni statickeho konstruktoru.");
    }

    public StaticDemo() {
        System.out.println("2. Konstruktor predka");
    }

    {
        System.out.println("1. Instancni inicializacni blok predka");
    }
}
