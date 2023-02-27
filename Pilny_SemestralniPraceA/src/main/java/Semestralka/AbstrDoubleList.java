package Semestralka;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {

    private Prvek<T> prvni;
    private Prvek<T> posledni;
    private Prvek<T> aktualni;

    private static class Prvek<T> {

        private final T data;
        private Prvek<T> predchozi;
        private Prvek<T> dalsi;

        public Prvek(T data, Prvek<T> predchozi, Prvek<T> dalsi) {
            this.data = data;
            this.predchozi = predchozi;
            this.dalsi = dalsi;
        }
    }

    @Override
    public void zrus() {
        prvni = posledni = aktualni = null;
    }

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public void vlozPrvni(T data) {
        if (data == null) {
            throw new NullPointerException("Neni nastaven prvek");
        }
        Prvek<T> novy = new Prvek(data, posledni, prvni);
        
        if (jePrazdny()) {
            novy.dalsi = novy;
            novy.predchozi = novy;
            prvni = novy;
            posledni = novy;
        } else {
            posledni.dalsi = novy;
            prvni.predchozi = novy;
            prvni = novy;
        }
    }

    @Override
    public void vlozPosledni(T data) {

        if (jePrazdny()) {
            vlozPrvni(data);
        } else {
            Prvek novy = new Prvek(data, posledni, prvni);
            posledni.dalsi = novy;
            posledni = novy;

        }
    }

    @Override
    public void vlozNaslednika(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            vlozPrvni(data);
        } else {
            Prvek novy = new Prvek(data, aktualni, aktualni.dalsi);
            aktualni.dalsi.predchozi = novy;
            aktualni.dalsi = novy;

            if (aktualni == posledni) {
                posledni = novy;
            }
        }
    }

    @Override
    public void vlozPredchudce(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            vlozPrvni(data);
        } else {
            Prvek novy = new Prvek(data, aktualni, aktualni.dalsi);
            aktualni.dalsi.predchozi = novy;
            aktualni.dalsi = novy;
        }
    }

    @Override
    public T zpristupniAktualni() {
        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni() {
        if (jePrazdny()) {
            return null;
        } else {
            aktualni = prvni;
            return prvni.data;
        }
    }

    @Override
    public T zpristupniPosledni() {
        if (jePrazdny()) {
            return null;
        } else {
            aktualni = posledni;
            return posledni.data;
        }
    }

    @Override
    public T zpristupniNaslednika() {
        if (jePrazdny() || aktualni == null) {
            throw new NullPointerException("Není nastaven aktualni prvek. ");
        } else {
            aktualni = aktualni.dalsi;
            return aktualni.data;
        }
    }

    @Override
    public T zpristupniPredchudce() {
        if (jePrazdny() || aktualni == prvni) {
            return null;
        } else {
            aktualni = aktualni.predchozi;
            return aktualni.data;
        }
    }

    @Override
    public T odeberAktualni() {
        if (jePrazdny()) {
            throw new NullPointerException("Seznam je prázdný.");
        }

        T data;
        if (aktualni == prvni) {
            data = odeberPrvni();
        } else if (aktualni == posledni) {
            data = odeberPosledni();
        } else {
            data = aktualni.data;
            aktualni.predchozi.dalsi = aktualni.dalsi;
            aktualni.dalsi.predchozi = aktualni.predchozi;
            aktualni = prvni;

        }
        return data;

    }

    @Override
    public T odeberPrvni() {
        T data = prvni.data;
        prvni = prvni.dalsi;
        prvni.predchozi = posledni;
        return data;
    }

    @Override
    public T odeberPosledni() {
        T data = posledni.data;
        posledni = posledni.predchozi;
        posledni.dalsi = prvni;
        return data;
    }

    @Override
    public T odeberNaslednika() {
        T data = aktualni.dalsi.data;
        aktualni.dalsi = aktualni.dalsi.dalsi;
        aktualni.predchozi = aktualni;
        return data;
    }

    @Override
    public T odeberPredchudce() {
        T data = aktualni.predchozi.data;
        aktualni.predchozi = aktualni;
        aktualni.predchozi.predchozi.dalsi = aktualni.predchozi;
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {

            Prvek<T> prvek = prvni;

            @Override
            public boolean hasNext() {
                return prvek != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = prvek.data;
                    if (prvek != posledni) {
                        prvek = prvek.dalsi;
                    } else {
                        prvek = null;
                    }
                    return data;
                }
                throw new NoSuchElementException();
            }

        };
        return iterator;
    }

}
