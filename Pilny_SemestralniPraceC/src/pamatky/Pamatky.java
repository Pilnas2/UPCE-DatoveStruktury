
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pamatky;

import BVS.AbstrTable;
import BVS.IAbstrTable;
import BVS.eTypProhl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marti
 */
public class Pamatky implements IPamatky {
    
    private IAbstrTable<String, Zamek> seznam = new AbstrTable();
    private int pocetZamku = 0;
    private eTypKey KEY = null;
    
    @Override
    public int importDatZTXT() {
        int pocetZaznamu = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line = "";
            String id;
            String gps;
            String nazev;
            br.readLine();
            while ((line = br.readLine()) != null) {
                id = line.substring(3, 9);
                gps = line.substring(19, 43);
                nazev = line.substring(69, 89).trim();
                Zamek novyZamek = new Zamek(id, nazev, gps);
                vlozZamek(novyZamek);
                pocetZaznamu++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Pamatky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(e);
        }
        return pocetZaznamu;
    }

    @Override
    public int vlozZamek(Zamek zamek) {
        seznam.vloz(zamek.getNazevPamatky(), zamek);
        pocetZamku++;
        return 0;
    }

    @Override
    public Zamek najdiZamek(String klic) throws Exception {
        Zamek zamek = (Zamek) seznam.najdi(klic);
        return zamek;
    }

    @Override
    public Zamek odeberZamek(String klic) throws Exception {
        Zamek zamek = (Zamek) seznam.odeber(klic);
        pocetZamku--;
        return zamek;
    }

    @Override
    public void zrus() {
        seznam.zrus();
        pocetZamku = 0;
    }

    @Override
    public void prebuduj() {
        Iterator it = VytvorIterator(eTypProhl.HLOUBKA);
        Zamek[] pole = new Zamek[pocetZamku];
        int i = 0;
        while(it.hasNext()) {
            pole[i] = (Zamek) it.next();
            i++;
        }
        try {
            sortedArrayToBST(pole, 0, pole.length - 1);
        } catch (Exception ex) {
            Logger.getLogger(Pamatky.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void sortedArrayToBST(Zamek arr[], int start, int end) throws Exception {
        if(start <= end) {
            int mid = (start + end) / 2;
            vlozZamek(arr[mid]);
            sortedArrayToBST(arr, start, mid - 1);
            sortedArrayToBST(arr, mid + 1, end);
        }
    }

    @Override
    public void nastavKlic(eTypKey typ) {
        KEY = typ;
        prebuduj();
    }

    @Override
    public Zamek najdiNejbliz(String klic) {
        if (KEY == eTypKey.GPS) {
            Zamek nejkatsiZamek = null;
            Zamek aktualniZamek = null;
            int nejkratsiVzdalenost = 0;
            int aktualniVzdalenost;

            Iterator it = VytvorIterator(eTypProhl.HLOUBKA);
            if (it.hasNext()) {
                aktualniZamek = (Zamek) it.next();
                nejkratsiVzdalenost = Math.abs(klic.compareTo(aktualniZamek.getGps()));
                nejkatsiZamek = aktualniZamek;
            }
            while (it.hasNext()) {
                aktualniZamek = (Zamek) it.next();
                aktualniVzdalenost = Math.abs(klic.compareTo(aktualniZamek.getGps()));
                if (aktualniVzdalenost < nejkratsiVzdalenost) {
                    nejkratsiVzdalenost = aktualniVzdalenost;
                    nejkatsiZamek = aktualniZamek;
                }
            }
            return nejkatsiZamek;
        }
        return null;
    }

    @Override
    public Iterator VytvorIterator(eTypProhl typ) {
        return seznam.vytvorIterator(typ);
    }

}
