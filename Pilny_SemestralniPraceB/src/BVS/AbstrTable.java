/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BVS;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;


public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V> {

    private Prvek<K, V  > root = null;

    private class Prvek<K, V> {

        K key;
        V value;
        Prvek<K, V> parent;
        Prvek<K, V> left;
        Prvek<K, V> right;

        public Prvek(K key, V value, Prvek<K, V> parent, Prvek<K, V> left, Prvek<K, V> right) throws NullPointerException {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public void zrus() {
        root = null;
    }

    @Override
    public boolean jePrazdny() {
        return root == null;
    }

    @Override
    public V najdi(K key) throws Exception {
        return najdiPrvek(key).value;
    }
     private Prvek<K, V> najdiPrvek(K key) {
        Prvek<K, V> aktualni = root;
        int porovnani = aktualni.key.compareTo(key);
        while (porovnani != 0) {
            if (porovnani < 0) {
                if (Objects.isNull(aktualni.right)) {
                    throw new NullPointerException("Hledaný prvek neexistuje.");
                } else {
                    aktualni = aktualni.right;
                    porovnani = aktualni.key.compareTo(key);
                }
            } else if (porovnani > 0) {
                if (Objects.isNull(aktualni.left)) {
                    throw new NullPointerException("Hledaný prvek neexistuje.");
                } else {
                    aktualni = aktualni.left;
                    porovnani = aktualni.key.compareTo(key);
                }
            }
        }
        return aktualni;
    }

    @Override
    public void vloz(K key, V value) {
        if (root == null) {
            root = new Prvek(key, value, null, null, null);
            return;
        }
        Prvek<K, V> predchozi = null;
        Prvek<K, V> temp = root;
       while(temp != null){
           if(key.compareTo(temp.key) < 0){
               predchozi = temp;
               temp = temp.left;
           } else if (key.compareTo(temp.key) >= 0){
               
               predchozi = temp;
               temp = temp.right;
           }
        }

        Prvek prvek = new Prvek(key, value, predchozi, null, null);

        if (key.compareTo((K) predchozi.key) < 0) {
            predchozi.left = prvek;
        } else {
            predchozi.right = prvek;
        }
    }

    @Override
    public V odeber(K key) throws Exception {
        if (!jePrazdny()) {
            Prvek<K, V> aktualni = root;
            if (aktualni.left == null && aktualni.right == null) {
                if (key.compareTo(aktualni.key) == 0) {
                    zrus();
                    return aktualni.value;
                }
            } else {
                while (aktualni != null) {
                    if (key.compareTo(aktualni.key) < 0) {
                        if (!Objects.isNull(aktualni.left)) {
                            aktualni = aktualni.left;
                        } else {
                            throw new NullPointerException("Takový prvek neexistuje!");
                        }
                    } else if (key.compareTo(aktualni.key) > 0) {
                        if (!Objects.isNull(aktualni.right)) {
                            aktualni = aktualni.right;
                        } else {
                            throw new NullPointerException("Takový prvek neexistuje!");
                        }
                    } else {
                        Prvek<K, V> leva_strana = aktualni.left;
                        while (leva_strana.right != null) {
                            leva_strana = leva_strana.right;
                        }

                        Prvek<K, V> prava_strana = aktualni.right;
                        while (prava_strana.left != null) {
                            prava_strana = prava_strana.left;
                        }

                        if (Objects.isNull(leva_strana)) {
                            prava_strana.parent.left = null;
                            prava_strana.parent = aktualni.parent;
                            if (!(aktualni == root)) {
                                if (aktualni.parent.left.key.compareTo(aktualni.key) == 0) {
                                    aktualni.parent.left = prava_strana;
                                } else {
                                    aktualni.parent.right = prava_strana;
                                }
                                prava_strana.left = aktualni.left;
                                prava_strana.right = aktualni.right;
                                return aktualni.value;
                            } else {
                                prava_strana.left = aktualni.left;
                                prava_strana.right = aktualni.right;
                                root = prava_strana;
                                return aktualni.value;
                            }
                        } else if (Objects.isNull(prava_strana)) {
                            leva_strana.parent.right = null;
                            leva_strana.parent = aktualni.parent;
                            if (!(aktualni == root)) {
                                if (aktualni.parent.left.key.compareTo(aktualni.key) == 0) {
                                    aktualni.parent.left = leva_strana;
                                } else {
                                    aktualni.parent.right = leva_strana;
                                }
                                leva_strana.left = aktualni.left;
                                leva_strana.right = aktualni.right;
                                return aktualni.value;
                            } else {
                                leva_strana.left = aktualni.left;
                                leva_strana.right = aktualni.right;
                                root = leva_strana;
                                return aktualni.value;
                            }
                        } else {
                            if (leva_strana.key.compareTo(aktualni.key) <= prava_strana.key.compareTo(aktualni.key)) {
                                leva_strana.parent.right = null;
                                leva_strana.parent = aktualni.parent;
                                if (!(aktualni == root)) {
                                    if (aktualni.parent.left.key.compareTo(aktualni.key) == 0) {
                                        aktualni.parent.left = leva_strana;
                                    } else {
                                        aktualni.parent.right = leva_strana;
                                    }
                                    leva_strana.left = aktualni.left;
                                    leva_strana.right = aktualni.right;
                                    return aktualni.value;
                                } else {
                                    leva_strana.left = aktualni.left;
                                    leva_strana.right = aktualni.right;
                                    root = leva_strana;
                                    return aktualni.value;
                                }
                            } else {
                                prava_strana.parent.left = null;
                                prava_strana.parent = aktualni.right;
                                if (!(aktualni == root)) {
                                    if (aktualni.parent.left.key.compareTo(aktualni.key) == 0) {
                                        aktualni.parent.left = prava_strana;
                                    } else {
                                        aktualni.parent.right = prava_strana;
                                    }
                                    prava_strana.left = aktualni.left;
                                    prava_strana.right = aktualni.right;
                                    return aktualni.value;
                                } else {
                                    prava_strana.left = aktualni.left;
                                    prava_strana.right = aktualni.right;
                                    root = prava_strana;
                                    return aktualni.value;
                                }
                            }
                        }
                    }
                }
            }
            return null;
        } else {
            throw new NullPointerException("Nelze smazat: strom je prázdný");
        }
    }


    @Override
    public Iterator vytvorIterator(eTypProhl typ) {
        switch (typ) {
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

        private final AbstrFIFO<Prvek<K, V>> fronta = new AbstrFIFO<>();

        public iteratorDoSirky() {
            fronta.vloz(root);
        }

        @Override
        public boolean hasNext() {
            return !fronta.jePrazdny() && !jePrazdny();
        }

        @Override
        public V next() {
            Prvek<K, V> odebrany = fronta.odeber();

            if (odebrany.left != null) {
                fronta.vloz(odebrany.left);
            }

            if (odebrany.right != null) {
                fronta.vloz(odebrany.right);
            }

            return odebrany.value;
        }
    }

    private class iteratorDoHloubky implements Iterator<V> {

        private final AbstrLIFO<Prvek<K, V>> zasobnik = new AbstrLIFO<>();
        private Prvek<K, V> soucasny;

        public iteratorDoHloubky() {
            soucasny = root;
            while (soucasny != null) {
                zasobnik.vloz(soucasny);
                soucasny = soucasny.left;
            }
        }

        @Override
        public boolean hasNext() {
            return soucasny == null && !zasobnik.jePrazdny();
        }

        @Override
        public V next() {
            Prvek<K, V> odebrany = zasobnik.odeber();
            soucasny = odebrany.right;

            while (soucasny != null) {
                zasobnik.vloz(soucasny);
                soucasny = soucasny.left;
            }

            return odebrany.value;
        }
    }
}
