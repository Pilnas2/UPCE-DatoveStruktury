package Semestralka;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 *
 * @author marti
 */
public class VyrobniProces implements IVyrobniProces {

    private IAbstrDoubleList<Proces> abstrDouble;

    public static VyrobniProces vyrobniProces(Supplier<IAbstrDoubleList<Proces>> creator) {
        VyrobniProces vyrobniProces = new VyrobniProces();
        vyrobniProces.abstrDoubleList(creator);
        return vyrobniProces;
    }

    private void abstrDoubleList(Supplier<IAbstrDoubleList<Proces>> supplier) {
        Objects.requireNonNull(supplier);
        abstrDouble = supplier.get();
    }

    @Override
    public int importDat(String soubor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void vlozProces(Proces proces, enumPozice pozice) {
        switch (pozice) {
            case PRVNI ->
                abstrDouble.vlozPrvni(proces);
            case NASLEDNIK ->
                abstrDouble.vlozNaslednika(proces);
            case POSLEDNI ->
                abstrDouble.vlozPosledni(proces);
            case PREDCHUDCE ->
                abstrDouble.vlozPredchudce(proces);
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public Proces zpristupniProces(enumPozice pozice) {
        Proces novy = null;
        switch (pozice) {
            case PRVNI ->
                novy = abstrDouble.zpristupniPrvni();
            case NASLEDNIK ->
                novy = abstrDouble.zpristupniNaslednika();
            case POSLEDNI ->
                novy = abstrDouble.zpristupniPosledni();
            case PREDCHUDCE ->
                novy = abstrDouble.zpristupniPredchudce();
            case AKTUALNI ->
                novy = abstrDouble.zpristupniAktualni();

            default ->
                throw new AssertionError();
        }
        return novy;
    }

    @Override
    public Proces odeberProces(enumPozice pozice) {
        Proces novy = null;
        switch (pozice) {
            case PRVNI ->
                novy = abstrDouble.odeberPrvni();
            case NASLEDNIK ->
                novy = abstrDouble.odeberNaslednika();
            case POSLEDNI ->
                novy = abstrDouble.odeberPosledni();
            case PREDCHUDCE ->
                novy = abstrDouble.odeberPredchudce();
            case AKTUALNI ->
                novy = abstrDouble.odeberAktualni();

            default ->
                throw new AssertionError();
        }
        return novy;
    }

    @Override
    public IAbstrLifo vytipujKandidatiReorg(int cas, enumReorg reorgan) {
        Proces vytipuj1 = null;
        Proces vytipuj2 = null;
        IAbstrLifo zasobnik = new AbstrLifo();
        Iterator<Proces> iterator = iterator();

        switch (reorgan) {

            case AGREGACE:
                while (iterator.hasNext()) {
                    vytipuj1 = iterator.next();
                    if (vytipuj1.getCas() == cas && iterator.hasNext()) {
                        vytipuj2 = iterator.next();
                        if (vytipuj2.getCas() == cas) {
                            zasobnik.vloz(vytipuj1);
                            zasobnik.vloz(vytipuj2);
                            break;

                        }
                    }
                }

                break;

            case DEKOMPOZICE:
                while (iterator.hasNext()) {
                    vytipuj1 = iterator.next();
                    if (vytipuj1.getCas() == cas) {
                        zasobnik.vloz(vytipuj1);
                        break;
                    }
                }
                break;

            default:
                throw new AssertionError();
        }
        reorganizace(reorgan, zasobnik);
        return zasobnik;
    }

    @Override
    public void reorganizace(enumReorg reorgan, IAbstrLifo zasobnik) {
        Proces organizace1 = null;
        Proces organizace2 = null;
        Iterator<Proces> iterator = iterator();
        boolean jePrvni = true;
        switch (reorgan) {
            case AGREGACE:
                
                organizace2 = (Proces) zasobnik.odeber();
                organizace1 = (Proces) zasobnik.odeber();

                organizace1.setCas(organizace1.getCas() * 2);
                organizace1.setPocetOsob(organizace1.getPocetOsob() + organizace2.getPocetOsob());
                
                while (iterator.hasNext()) {
                     if (jePrvni) {
                        abstrDouble.zpristupniPrvni();
                        jePrvni = false;
                    } else {
                        abstrDouble.zpristupniNaslednika();
                    }
                    if (organizace1.equals(iterator.next())) {
                        if(iterator.hasNext() && organizace2.equals(iterator.next())){
                            abstrDouble.odeberNaslednika();
                            abstrDouble.vlozNaslednika(organizace1);
                            abstrDouble.odeberAktualni();
                            break;
                        }
                    }
                }
                break;

            case DEKOMPOZICE:
                organizace1 = (Proces) zasobnik.odeber();
                organizace1.setCas(organizace1.getCas() / 2);
                organizace1.setPocetOsob(organizace1.getPocetOsob() / 2);
                organizace2 = new Proces(organizace1.getId(),
                        organizace1.getPocetOsob(), organizace1.getCas());
                while (iterator.hasNext()) {
                    if (jePrvni) {
                        abstrDouble.zpristupniPrvni();
                        jePrvni = false;
                    } else {
                        abstrDouble.zpristupniNaslednika();
                    }
                    if (organizace1.equals(iterator.next())) {
                        abstrDouble.vlozNaslednika(organizace2);
                        abstrDouble.vlozNaslednika(organizace1);
                        abstrDouble.odeberAktualni();
                        break;

                    }

                }
                break;

            default:
                throw new AssertionError();
        }
    }

    @Override
    public void zrus() {
        abstrDouble.zrus();
    }

    @Override
    public Iterator<Proces> iterator() {
        return abstrDouble.iterator();
    }

}
