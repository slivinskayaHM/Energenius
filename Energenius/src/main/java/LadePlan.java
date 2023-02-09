import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

public class LadePlan {
    Auto auto;
    int vefuegbareTicks;
    int noetigeTicks;

    TreeMap<Date, Double> prognose;

    private LadePlan(Auto auto, TreeMap<Date, Double> prognose) {
        this.auto = auto;
        this.vefuegbareTicks = berechneVerfuegbareTicks(auto.aktuelleStehPeriode);
        this.noetigeTicks = berechneNoetigeTicks(auto.getBenoetigteLadung() - auto.akkuStand);
        this.prognose = prognose;
    }

    public double getRequiredCharge() {
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





    private static int berechneVerfuegbareTicks(StehPeriode aktuelleStehPeriode) {
        double ladeZeit = berechneZeit(aktuelleStehPeriode.getAbfahrt(), aktuelleStehPeriode.getAnkunft());
        double fiveMinutesInMillis  = 5 * 60 * 1000;
        return (int) Math.floor(ladeZeit / fiveMinutesInMillis);
    }

    private static double berechneZeit(Date abfahrt, Date ankunft) {
        return abfahrt.getTime()  - ankunft.getTime();
    }

}
