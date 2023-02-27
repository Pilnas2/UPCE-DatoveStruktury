package cz.upce.fei.boop.nemennatrida;

import java.util.Locale;

public record Rozmer(double delka, double sirka, double vyska) {

    public static double DIMENZE_MIN = 0.1;
    public static double DIMENZE_MAX = 100.0;

    public static boolean kontrolaDimenze(double dimenze) {
        return (DIMENZE_MIN <= dimenze && dimenze <= DIMENZE_MAX);
    }

    private static boolean check(double dimenze) {
        return (dimenze >= DIMENZE_MIN && dimenze <= DIMENZE_MAX);

    }
}
