import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class LadePlan implements Comparable<LadePlan> {
    Auto auto;
    int noetigeTicks;
    int verfuegbareTicks;
    int verbliebeneAuswahl;
    boolean forceCharge = false;

    SortedMap<Date, Double> prognose;

    public LadePlan(Auto auto, SortedMap<Date, Double> prognose) {
        this.auto = auto;
        this.prognose = prognose;

        // System.out.println("Prognosedaten: " + prognose);
    }

    public double getRequestedCharge() {
        ArrayList<Double> values = new ArrayList<>(prognose.values());
        return ermittleDurchschnittsWert(values);
    }

    private double ermittleDurchschnittsWert(ArrayList<Double> values) {
        double kumulierteWerte = 0;
        for (double wert : values) {
            kumulierteWerte += wert;
        }
        return kumulierteWerte / values.size();
    }


    private int berechneNoetigeTicks(double noetigeLadung) {
        return (int) Math.ceil(noetigeLadung/ Solaranlage.LADUNGS_BIT);
    }





    private int berechneVerfuegbareTicks(StehPeriode aktuelleStehPeriode, Date aktuelleZeit) {
        double ladeZeit = berechneZeit(aktuelleStehPeriode.getAbfahrt(), aktuelleZeit);

        // System.out.println("Aktuelle Zeit ist: " + aktuelleZeit + " nächste abfahrt ist " + aktuelleStehPeriode.getAbfahrt() + " \nVerfügbare Minuten sind " + Math.floor(ladeZeit / (60 * 1000)));

        double fiveMinutesInMillis  = 5 * 60 * 1000;
        return (int) Math.floor(ladeZeit / fiveMinutesInMillis);
    }

    private static double berechneZeit(Date abfahrt, Date aktuelleZeit) {
        return abfahrt.getTime() - aktuelleZeit.getTime();
    }

    public boolean shouldCharge(Date uhrzeit, double gegenwaertigeStromzeugung) {
        noetigeTicks = berechneNoetigeTicks(auto.getBenoetigteLadung());
        verfuegbareTicks = berechneVerfuegbareTicks(auto.aktuelleStehPeriode, uhrzeit);
        verbliebeneAuswahl = verfuegbareTicks - noetigeTicks;
        //System.out.println("---Verfügbare Ticks sind " + berechneVerfuegbareTicks(auto.aktuelleStehPeriode, uhrzeit) + " nötige ticks sind " + noetigeTicks);

        if (noetigeTicks < 0 ) {
            return false;
        }
        if (verfuegbareTicks <= noetigeTicks + 5) {
            System.out.println( auto.id + ": Auto wird geladen um genügend strom zur verfügung zu haben");
            forceCharge = true;
            return true;
        }
        double gewuenschteLadung = getRequestedCharge();

        // System.out.println("Gewünschte ladeung ist " + gewuenschteLadung + " gegenwärtige stromproduktion ist " + gegenwaertigeStromzeugung);

        if (gegenwaertigeStromzeugung >= gewuenschteLadung) {
            System.out.println(auto.id + ": Gegenwärtige Stromproduktion (" + gegenwaertigeStromzeugung + "): entspricht gewünschtem Output (" + gewuenschteLadung + ")");
            return true;
        }
        return false;

    }

    public double getAusweichMoeglichkeiten() {
        return verbliebeneAuswahl;
    }

    @Override
    public int compareTo(LadePlan other) {
        if (this.verbliebeneAuswahl < other.verbliebeneAuswahl) {
            return -1;
        } else if (this.verbliebeneAuswahl > other.verbliebeneAuswahl) {
            return 1;
        } else {
            return 0;
        }
    }
}
