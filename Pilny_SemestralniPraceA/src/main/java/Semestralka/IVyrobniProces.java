/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Semestralka;

import java.util.Iterator;

/**
 *
 * @author marti
 */
public interface IVyrobniProces extends Iterable<Proces>{
    
    int importDat(String soubor);
    void vlozProces(Proces proces, enumPozice pozice);
    Proces zpristupniProces(enumPozice pozice);
    Proces odeberProces(enumPozice pozice);
    Iterator iterator();
    IAbstrLifo vytipujKandidatiReorg(int cas, enumReorg reorgan);
    void reorganizace(enumReorg reorgan, IAbstrLifo zasobnik);
    void zrus();
}
