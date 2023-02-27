/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fronta;

import BVS.AbstrFIFO;
import BVS.AbstrLIFO;
import BVS.eTypProhl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import pamatky.Pamatky;
import pamatky.Zamek;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;

/**
 *
 * @author marti
 */
public class AbstrHeap<V> implements IAbstrHeap<V> {

    public int pocetPrvku = 0;
    private V[] pole = (V[]) new Object[10];
    private final Comparator<V> comparator;

    public AbstrHeap(Comparator<V> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void vybuduj(V[] hodnoty) {
        zrus();
        pole = Arrays.copyOf(hodnoty, hodnoty.length);
        pocetPrvku = hodnoty.length;
        prebuduj();
    }

    @Override
    public void prebuduj() {
        for (int i = pocetPrvku / 2 - 1; i >= 0; i--) {
            heapify(pole, pocetPrvku, i);
        }

        for (int i = pocetPrvku - 1; i >= 0; i--) {
            V temp = pole[0];
            pole[0] = pole[i];
            pole[i] = temp;

            heapify(pole, i, 0);
        }
    }

    void heapify(V[] arr, int n, int i) {
        int largest = i;  
        int l = 2 * i + 1;  
        int r = 2 * i + 2;  

       
        if (l < n && (comparator.compare(arr[l], arr[largest])) > 0) {
            largest = l;
        }

        
        if (r < n && (comparator.compare(arr[r], arr[largest])) > 0) {
            largest = r;
        }

        
        if (largest != i) {
            V swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            
            heapify(arr, n, largest);
        }
    }

    @Override
    public void zrus() {
        pole = (V[]) new Object[10];
        pocetPrvku = 0;
    }

    @Override
    public boolean jePrazdny() {
        return pocetPrvku == 0;
    }

    @Override
    public void vloz(V value) {
        if ((pocetPrvku == pole.length) && pole.length != 0) {
            pole = Arrays.copyOf(pole, (pole.length) * 2);
            pole[pocetPrvku] = value;
        } else if (pole.length == 0) {
            pole = (V[]) new Object[10];
            pole[0] = value;
        } else {
            pole[pocetPrvku] = value;
        }
        pocetPrvku++;
        System.out.println(pole.length);
        prebuduj();
    }

    @Override
    public V odeberMax() {
        V odebranyPrvek = pole[0];
        pole[0] = null;
        pocetPrvku--;
        System.arraycopy(pole, 1, pole, 0, pocetPrvku);
        if ((pole.length / 2) >= pocetPrvku) {
            pole = Arrays.copyOf(pole, (pole.length) / 2);
        }
        prebuduj();
        return odebranyPrvek;
    }

    @Override
    public V zpristupniMax() {
        prebuduj();
        return pole[0];
    }

    @Override
    public Iterator<V> iterator(eTypProhl prohlidka) {
        switch (prohlidka) {
            case SIRKA -> {
                return new iteratorDoSirky();
            }
            case HLOUBKA -> {
                return new iteratorDoHloubky();
            }
        }
        return null;
    }

    private class iteratorDoSirky implements Iterator<V> {

        private final AbstrFIFO<V> fifo = new AbstrFIFO<>();

        public iteratorDoSirky() {
            for (int i = 0; i < pocetPrvku; i++) {
                fifo.vloz(pole[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !fifo.jePrazdny() && !jePrazdny();
        }

        @Override
        public V next() {
            V odebrany = fifo.odeber();
            return odebrany;
        }
    }

    private class iteratorDoHloubky implements Iterator<V> {

        private AbstrLIFO<Integer> fifo = new AbstrLIFO<>();
        private int soucasny;

        public iteratorDoHloubky() {
            soucasny = 0;
            while (soucasny <= pocetPrvku) {
                fifo.vloz(soucasny);
                soucasny = soucasny * 2 + 1;
            }
        }

        @Override
        public boolean hasNext() {
            return !fifo.jePrazdny();
        }

        @Override
        public V next() {
            int odebrany = fifo.odeber();
            soucasny = odebrany * 2 + 2;

            while (soucasny < pocetPrvku) {
                fifo.vloz(soucasny);
                soucasny = soucasny * 2 + 1;
            }
            return pole[odebrany];
        }

    }

}
