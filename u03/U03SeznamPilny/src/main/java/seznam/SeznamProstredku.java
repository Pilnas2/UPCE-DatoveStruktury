/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seznam;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import seznam.prostredky.Prostredek;

/**
 *
 * @author marti
 */
public class SeznamProstredku implements Seznam {
    
    private Prostredek[] prostredek;
    private int pocet;
    private int kapacita;

    public SeznamProstredku(int rozmer) {
        if(rozmer >= 0){
            this.prostredek = new Prostredek[rozmer];
            pocet = 0;
        } else {
            throw new ArrayIndexOutOfBoundsException("Mimo rozsah.");
        }
    }
    
    private boolean kontrola(int pozice){
        if (pocet == 0 ){
            throw new IllegalAccessError("Nepovolená operace.");
        }else if (pozice - 1 < 0 || pozice > pocet){
            throw new IndexOutOfBoundsException("Špatná rozsah");
        }
        return true;
    }

    @Override
    public Prostredek dej(int pozice)
            throws IndexOutOfBoundsException, IllegalAccessError {
        kontrola(pozice);
        return prostredek[pozice - 1];
    }


    @Override
    public Prostredek[] dejKopieDopravniProstredku() throws CloneNotSupportedException {
        Prostredek[] kopie = new Prostredek[prostredek.length];
        System.arraycopy(prostredek, 0, kopie, 0, pocet);
        return kopie;
    }

    @Override
    public Prostredek odeber(final int pozice)
            throws IndexOutOfBoundsException, IllegalAccessError {
        kontrola(pozice);
        Prostredek odebrany = prostredek[pozice - 1];
        System.arraycopy(prostredek, pocet, this, pozice, pocet);
        return odebrany;
    }

    @Override
    public void vloz(Prostredek prostredek) throws IllegalArgumentException {
        atributNeniNull(prostredek);
        zmenaRozmeruPole();
        this.prostredek[pocet++] = prostredek;
        
        
    }
    
    private void zmenaRozmeruPole() {
        if( pocet == kapacita){
            kapacita *= 2;
            Prostredek[] seznam = new Prostredek[kapacita];
            System.arraycopy(prostredek, 0, seznam, 0, pocet);
            prostredek = seznam;
        }
    }
    private void atributNeniNull(Prostredek prostredek) throws IllegalArgumentException{
        if(prostredek == null){
            throw new IllegalArgumentException("Nemůže být null");
    }
    }

    @Override
    public int pocet() {
        return pocet;
    }

    @Override
    public int kapacita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void zrus() {
        pocet = 0;
    }

    @Override
    public Iterator<Prostredek> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public Prostredek[] dejDopravniProstredky() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
