/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BVS;

import java.util.Iterator;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;

/**
 *
 * @author marti
 */
public class AbstrLIFO<T> implements IAbstrLIFO<T> {

    private IAbstrDoubleList<T> zasobnik;

    public AbstrLIFO() {
        this.zasobnik = new AbstrDoubleList<>();
    }

    @Override
    public void zrus() {
        zasobnik.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return zasobnik.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        zasobnik.vlozPosledni(data);
    }

    @Override
    public T odeber() {
        try {
            return zasobnik.odeberPosledni();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Iterator vytvorIterator() {
        return iterator();
    }

    @Override
    public Iterator<T> iterator() {
        return zasobnik.iterator();
    }

}
