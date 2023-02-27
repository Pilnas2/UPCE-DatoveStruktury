/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pamatky;

import BVS.eTypProhl;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author marti
 */
public interface IPamatky extends Serializable{
    int importDatZTXT();
    int vlozZamek(Zamek zamek);
    Zamek najdiZamek(String klic) throws Exception ;
    Zamek odeberZamek(String klic) throws Exception;
    void zrus();
    void prebuduj();
    void nastavKlic(eTypKey typ);
    Zamek najdiNejbliz(String klic);
    Iterator VytvorIterator(eTypProhl typ);
    
}
