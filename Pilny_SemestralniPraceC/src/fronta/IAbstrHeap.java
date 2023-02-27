/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fronta;

import BVS.eTypProhl;
import java.util.Iterator;
import seznam.IAbstrDoubleList;

/**
 *
 * @author marti
 */
public interface IAbstrHeap<V> {
    void vybuduj(V[] valueArray);
    void prebuduj();
    void zrus();
    boolean jePrazdny();
    void vloz(V value);
    V odeberMax();
    V zpristupniMax();
    Iterator<V> iterator(eTypProhl prohlidka);
}
