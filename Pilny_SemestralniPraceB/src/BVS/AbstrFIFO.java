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
public class AbstrFIFO<T> implements IAbstrFIFO<T>{

    private IAbstrDoubleList<T> fronta;

    public AbstrFIFO() {
        this.fronta = new AbstrDoubleList<T>();
    }

    @Override
    public void zrus() {
        fronta.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return fronta.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        fronta.vlozPrvni(data);
    }

    @Override
    public T odeber() {
        try {
            return fronta.odeberPosledni();
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
        return fronta.iterator();
    }

    
}
