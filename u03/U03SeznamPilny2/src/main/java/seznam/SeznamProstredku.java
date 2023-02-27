/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seznam;

import java.util.Iterator;
import java.util.NoSuchElementException;
import seznam.prostredky.Prostredek;

/**
 *
 * @author marti
 */
public class SeznamProstredku implements Seznam {
    
    private Prostredek[] prostredek;
    private int pocet;

    public SeznamProstredku(int rozmer) throws IllegalArgumentException{
        if(rozmer <= 0){
            throw new IllegalArgumentException();
        } 
            prostredek = new Prostredek[rozmer];
        
    }
    

    @Override
    public Prostredek dej(int pozice) throws 
            IndexOutOfBoundsException, IllegalAccessError{
            int index = pozice - 1;
        if (pocet == 0) {
            throw new IllegalAccessError();
        }
        if (!kontrolaIndexu(index) || prostredek[index] == null) {
            throw new IndexOutOfBoundsException();
        }
        return prostredek[index];
    }

    @Override
    public Prostredek[] dejDopravniProstredky(){
        Prostredek[] pole = new Prostredek[pocet];
        System.arraycopy(prostredek, 0, pole, 0, pocet);
        return pole;
    }
    
    @Override
    public Prostredek[] dejKopieDopravniProstredku() throws CloneNotSupportedException{
        Prostredek[] copy = new Prostredek[pocet];
        for (int i = 0; i < pocet; i++) {
            copy[i] = prostredek[i].clone();
        }
        return copy;
    }

    @Override
    public Prostredek odeber(final int pozice)
            throws IndexOutOfBoundsException, IllegalAccessError {
        int index = pozice - 1;
        if(pocet == 0){
            throw new IllegalAccessError();
        }
        kontrola(index);
        Prostredek returnProstredek = prostredek[index];
        Prostredek[] pole = new Prostredek[prostredek.length];
        System.arraycopy(prostredek, index + 1, pole, index, prostredek.length - index - 1);
        prostredek = pole;
        pocet--;
        if (pocet < prostredek.length / 4) {
            zkratit();
        }
        
        return returnProstredek;
    }

    @Override
    public void vloz(Prostredek prostredek) throws IllegalArgumentException {
         if (prostredek == null) {
            throw new IllegalArgumentException("Parametr prostredek je null");
        }
        if (pocet >= this.prostredek.length) {
            rozsirit();
        }
        this.prostredek[pocet] = prostredek;
        pocet++;
    }
    
    

    @Override
    public int pocet() {
        return pocet;
    }

    @Override
    public int kapacita() {
        return prostredek.length;
    }

    @Override
    public void zrus() {
        prostredek = new Prostredek[1];
        pocet = 0;
    }
    
    
    @Override
    public Iterator<Prostredek> iterator() {
        return new Iterator() {
            int index = 0;
            
            @Override
            public boolean hasNext() {
                return (index < pocet);
            }

            @Override
            public Object next() {
                Prostredek p = prostredek[index];
                if (hasNext()) {
                    index++;
                } else {
                    throw new NoSuchElementException();
                }
                return p;
            }
            
        };
    }
    
    private void rozsirit() {
        Prostredek[] nove = new Prostredek[2 * prostredek.length];
        System.arraycopy(prostredek, 0, nove, 0, prostredek.length);
        prostredek = nove;
    }

    private void zkratit() {
        Prostredek[] nove = new Prostredek[prostredek.length / 2];
        System.arraycopy(prostredek, 0, nove, 0, nove.length);
        prostredek = nove;
    }



    private boolean kontrolaIndexu(int index) {
        return (index >= 0 && pocet() >= index);
    }
    
    private void kontrola(int index) throws IndexOutOfBoundsException {
        if (!kontrolaIndexu(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

}
