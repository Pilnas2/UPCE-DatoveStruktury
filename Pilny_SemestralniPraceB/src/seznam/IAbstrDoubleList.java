package seznam;

import java.util.Iterator;

public interface IAbstrDoubleList <T> extends Iterable<T>{
    
    /**
     * Zrušení celého seznamu
     */
    void zrus();
    
    /**
     *  Test naplněnosti seznamu
     * @return vrací true pokud je seznam prázdný, false pokud je v něm alespoň
     * jeden prvek
     */
    boolean jePrazdny();
    
    void vlozPrvni(T data);
    void vlozPosledni(T data);
    void vlozNaslednika(T data);
    void vlozPredchudce(T data);
    
    T zpristupniAktualni();
    T zpristupniPrvni();
    T zpristupniPosledni();
    T zpristupniNaslednika();
    T zpristupniPredchudce();
    
    T odeberAktualni();
    T odeberPrvni();
    T odeberPosledni();
    T odeberNaslednika();
    T odeberPredchudce();
    
    Iterator<T> iterator();
    
}
