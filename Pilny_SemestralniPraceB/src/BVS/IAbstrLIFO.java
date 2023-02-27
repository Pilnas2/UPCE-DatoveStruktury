/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BVS;

import java.util.Iterator;

/**
 *
 * @author marti
 */
public interface IAbstrLIFO<T> extends Iterable<T>{
    void zrus();
    boolean jePrazdny();
    void vloz(T data);
    T odeber();
    Iterator vytvorIterator();
}
