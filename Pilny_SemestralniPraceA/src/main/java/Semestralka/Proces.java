/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semestralka;

import java.util.Objects;

/**
 *
 * @author marti
 */
class Proces {
    String id;
    int pocetOsob;
    int cas;

    public Proces(String id, int pocetOsob, int cas) {
        this.id = id;
        this.pocetOsob = pocetOsob;
        this.cas = cas;
    }

    public Proces(String id, int cas) {
        this.id = id;
        this.cas = cas;
    }

    public String getId() {
        return id;
    }

    public int getPocetOsob() {
        return pocetOsob;
    }

    public int getCas() {
        return cas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPocetOsob(int pocetOsob) {
        this.pocetOsob = pocetOsob;
    }

    public void setCas(int cas) {
        this.cas = cas;
    }

    @Override
    public String toString() {
        return "Proces{" + "id=" + id + ", pocetOsob=" + pocetOsob + ", cas=" + cas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + this.pocetOsob;
        hash = 53 * hash + this.cas;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proces other = (Proces) obj;
        if (this.pocetOsob != other.pocetOsob) {
            return false;
        }
        if (this.cas != other.cas) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
    
}
