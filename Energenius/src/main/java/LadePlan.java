import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class LadePlan {
    public static boolean forceCharge;
    Auto auto;
    int vefuegbareTicks;
    int noetigeTicks;

    TreeMap<Date, Double> prognose;

    public LadePlan(Auto auto, TreeMap<Date, Double> prognose) {
        this.auto = auto;
        this.noetigeTicks = berechneNoetigeTicks(auto.getBenoetigteLadung() - auto.akkuStand);
        this.prognose = prognose;
    }

    public double getRequestedCharge() {
        ArrayList<Double> values = new ArrayList<>(prognose.values());
        return ermittleDurchschnittsWert(values);
    }

    private double ermittleDurchschnittsWert(ArrayList<Double> values) {
        int kumulierteWerte = 0;
        for (double wert : values) {
            kumulierteWerte += wert;
        }
        return (double) kumulierteWerte / values.size();
    }


    private int berechneNoetigeTicks(double noetigeLadung) {
        return (int) Math.ceil(noetigeLadung/ Solaranlage.LADUNGS_BIT);
    }





    private int berechneVerfuegbareTicks(StehPeriode aktuelleStehPeriode, Date aktuelleZeit) {
        double ladeZeit = berechneZeit(aktuelleStehPeriode.getAbfahrt(), aktuelleZeit);
        double fiveMinutesInMillis  = 5 * 60 * 1000;
        return (int) Math.floor(ladeZeit / fiveMinutesInMillis);
    }

    private static double berechneZeit(Date abfahrt, Date aktuelleZeit) {
        return abfahrt.getTime()  - aktuelleZeit.getTime();
    }

    public boolean shouldCharge(Date uhrzeit, double gegenwaertigeStromzeugung) {
        if (berechneVerfuegbareTicks(auto.aktuelleStehPeriode, uhrzeit) <= noetigeTicks + 5) {
            return true;
        }
        return gegenwaertigeStromzeugung <= getRequestedCharge() - 100;
    }
}
