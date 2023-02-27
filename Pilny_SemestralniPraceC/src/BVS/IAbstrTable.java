/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BVS;

import java.util.Iterator;

/**
 *
 * @author marti
 * @param <K>
 * @param <V>
 */
public interface IAbstrTable <K extends Comparable<K>, V> {
    void zrus();
    boolean jePrazdny();
    V najdi(K key) throws Exception;
    void vloz(K key, V value);
    V odeber(K key) throws Exception;
    Iterator vytvorIterator(eTypProhl typ);
    
}
