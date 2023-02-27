package seznam;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {
private Prvek<T> prvni;
    private Prvek<T> posledni;
    private Prvek<T> aktualni;
    private int pocet;

    private static class Prvek<T> {

        private T data;
        private Prvek<T> dalsi;
        private Prvek<T> predchozi;

        public Prvek(T data, Prvek<T> dalsi, Prvek<T> predchozi) {
            this.data = data;
            this.dalsi = dalsi;
            this.predchozi = predchozi;
        }

    }

    @Override
    public void zrus() {
        while (prvni != null) {
            if (prvni == posledni) {
                posledni.dalsi = null;
            }
            Prvek<T> novyPrvek = prvni.dalsi;
            prvni = null;
            prvni = novyPrvek;

        }
        aktualni = null;
        pocet = 0;
    }

    @Override
    public boolean jePrazdny() {
        return pocet == 0;
    }

    @Override
    public void vlozPrvni(T data) {
        vkladaniNull(data);
        Prvek<T> novyPrvek = new Prvek<>(data, prvni, posledni);

        if (!jePrazdny()) {
            prvni.predchozi = novyPrvek;
            posledni.dalsi = novyPrvek;
        } else {
            novyPrvek.dalsi = novyPrvek;
            novyPrvek.predchozi = novyPrvek;
            posledni = novyPrvek;
        }
        prvni = novyPrvek;

        pocet++;

    }

    @Override
    public void vlozPosledni(T data) {
        vkladaniNull(data);
        Prvek<T> novyPrvek = new Prvek<>(data, prvni, posledni);
        if (jePrazdny()) {
            novyPrvek.dalsi = novyPrvek;
            novyPrvek.predchozi = novyPrvek;
            prvni = novyPrvek;
        } else {
            posledni.dalsi = novyPrvek;
            prvni.predchozi = novyPrvek;
        }
        posledni = novyPrvek;
        pocet++;
    }

    @Override
    public void vlozNaslednika(T data) {
        vkladaniNull(data);
        if (jePrazdny() || aktualni == null) {
        }
        Prvek<T> novyPrvek = new Prvek<>(data, aktualni.dalsi, aktualni);
        aktualni.dalsi.predchozi = novyPrvek;
        aktualni.dalsi = novyPrvek;
        if (aktualni == posledni) {
            posledni = novyPrvek;
        }
        pocet++;
    }

    @Override
    public void vlozPredchudce(T data){
        vkladaniNull(data);
        if (jePrazdny() || aktualni == null) {
        }
        Prvek<T> novyPrvek = new Prvek<>(data, aktualni, aktualni.predchozi);
        aktualni.predchozi.dalsi = novyPrvek;
        aktualni.predchozi = novyPrvek;
        if (aktualni == prvni) {
            prvni = novyPrvek;
        }
        pocet++;
    }

    @Override
    public T zpristupniAktualni() {
        if (jePrazdny() || aktualni == null) {
        }
        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni()  {
        if (jePrazdny()) {
        }
        aktualni = prvni;
        return aktualni.data;

    }

    @Override
    public T zpristupniPosledni() {
        if (jePrazdny()) {
        }
        aktualni = posledni;
        return aktualni.data;
    }

    @Override
    public T zpristupniNaslednika()  {
        if (jePrazdny() || aktualni == null) {
        }
        aktualni = aktualni.dalsi;
        return aktualni.data;
    }

    @Override
    public T zpristupniPredchudce()  {
        if (jePrazdny() || aktualni == null) {
        }
        aktualni = aktualni.predchozi;
        return aktualni.data;
    }

    @Override
    public T odeberAktualni()  {
        if (jePrazdny() || aktualni == null) {
        }
        T mazanyAktualni = aktualni.data;
        if (pocet > 1) {
            if (aktualni == prvni) {
                odeberPrvni();
            } else if (aktualni == posledni) {
                odeberPosledni();
            } else {
                aktualni.predchozi.dalsi = aktualni.dalsi;
                aktualni.dalsi.predchozi = aktualni.predchozi;
            }
        } else {
            prvni = null;
            posledni = null;
        }
        aktualni = prvni;
        pocet--;

        return mazanyAktualni;
    }

    @Override
    public T odeberPrvni() {
        if (jePrazdny()) {
        }
        T mazanyPrvni = prvni.data;
        if (pocet > 1) {
            if (aktualni != prvni) {
                prvni = prvni.dalsi;
                prvni.predchozi = posledni;
                posledni.dalsi = prvni;

            } else {
                prvni = prvni.dalsi;
                prvni.predchozi = posledni;
                posledni.dalsi = prvni;
                aktualni = prvni;
            }
        } else {
            prvni = null;
            posledni = null;
            aktualni = null;
        }

        pocet--;

        return mazanyPrvni;
    }

    @Override
    public T odeberPosledni() {
        if (jePrazdny()) {
           return null;
        }
        T mazanyPosledni = posledni.data;
        if (pocet > 1) {
            if (aktualni != posledni) {
                posledni = posledni.predchozi;
                posledni.dalsi = prvni;
                prvni.predchozi = posledni;
            } else {
                posledni = posledni.predchozi;
                posledni.dalsi = prvni;
                prvni.predchozi = posledni;
                aktualni = posledni;
            }
        } else {
            prvni = null;
            posledni = null;
            aktualni = null;
        }

        pocet--;

        return mazanyPosledni;
    }

    @Override
    public T odeberNaslednika() {
        if (jePrazdny() || aktualni == null) {
        }
        T mazanyNaslednik = aktualni.dalsi.data;
        if (pocet > 1) {
            if (aktualni.dalsi == posledni) {
                odeberPosledni();
            } else if (aktualni.dalsi == prvni) {
                odeberPrvni();
            } else {
                aktualni.dalsi.dalsi.predchozi = aktualni;
                aktualni.dalsi = aktualni.dalsi.dalsi;
            }
        } else {
            odeberPosledni();
            aktualni = null;
        }

        pocet--;

        return mazanyNaslednik;
    }

    @Override
    public T odeberPredchudce() {
        if (jePrazdny() || aktualni == null) {
        }
        T mazanyPredchudce = aktualni.predchozi.data;

        if (pocet > 1) {
            if (aktualni.predchozi == posledni) {
                odeberPosledni();
            } else if (aktualni.predchozi == prvni) {
                odeberPrvni();
            } else {
                aktualni.predchozi.predchozi.dalsi = aktualni;
                aktualni.predchozi = aktualni.predchozi.predchozi;
            }
        } else {
            odeberPosledni();
            aktualni = null;
        }

        pocet--;

        return mazanyPredchudce;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Prvek<T> soucasny = prvni;

            @Override
            public boolean hasNext() {
                return soucasny != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = soucasny.data;
                    if (soucasny != posledni) {
                        soucasny = soucasny.dalsi;
                    } else {
                        soucasny = null;
                    }
                    return data;
                }
                throw new NoSuchElementException();
            }

        };

    }
    
    public int getPocetPrvku(){
        return pocet;
    }

    private void vkladaniNull(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException("Nelze vkladat prazdna data");
        }
    }

}