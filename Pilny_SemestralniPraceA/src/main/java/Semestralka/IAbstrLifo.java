package Semestralka;

/**
 *
 * @author marti
 * @param <T>
 */
public interface IAbstrLifo <T> {
    
    void zrus ();
    boolean jePrazdy();
    
    void vloz(T data);
    T odeber();
    
}
