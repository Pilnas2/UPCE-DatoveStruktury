/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pamatky;

import java.io.Serializable;

/**
 *
 * @author marti
 */
public class Zamek implements Serializable, Comparable<Zamek>{
    private String id;
    private String nazevPamatky;
    private String gps;

    public Zamek(String id, String nazevPamatky, String gps) {
        this.id = id;
        this.nazevPamatky = nazevPamatky;
        this.gps = gps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazevPamatky() {
        return nazevPamatky;
    }

    public void setNazevPamatky(String nazevPamatky) {
        this.nazevPamatky = nazevPamatky;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    
    @Override
    public int compareTo(Zamek dalsi){
        return this.getNazevPamatky().compareTo(dalsi.getNazevPamatky());
    }

    @Override
    public String toString() {
        return "Zamek{" + "id=" + id + ", nazevPamatky=" + nazevPamatky + ", GPS=" + gps + '}';
    }
}
