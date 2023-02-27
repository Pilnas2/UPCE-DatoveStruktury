package Semestralka;

import java.nio.file.Files;

/**
 *
 * @author marti
 * @param <T>
 */
public class AbstrLifo<T> implements IAbstrLifo<T> {


    private AbstrDoubleList<T> list = new AbstrDoubleList<>();

    @Override
    public void zrus() {
        list.zrus();
    }

    @Override
    public boolean jePrazdy() {
        return list.jePrazdny();
    }

    @Override
    public T odeber() {
        return list.odeberPosledni();
    }

    @Override
    public void vloz(T data) {
        list.vlozPosledni(data);
    }
}
