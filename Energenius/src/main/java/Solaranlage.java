import java.util.*;

public class Solaranlage {
    static final double LADUNGS_BIT = 4000 / 12;

    double gegenwärtigeStromzeugung;

    TreeMap<Date, Double> prognoseDaten;
    //ArrayList<Auto> autos = new ArrayList<>();
    ArrayList<LadePlan> ladePlaene = new ArrayList<LadePlan>();
    ArrayList<Auto> ladeSchlange = new ArrayList<>();

    double stromVonAnlage = 0;
    double stromAusLeitung = 0;

    public Solaranlage(TreeMap<Date, Double> prognoseDaten) {
        this.prognoseDaten = prognoseDaten;
    }

//    private TreeMap<Date, Double> rechneAufTicksRunter(TreeMap<Date, Double> prognoseDaten) {
//        for(Map.Entry<Date,Double> entry : prognoseDaten.entrySet()) {
//            Date key = entry.getKey();
//            Double value = entry.getValue();
//
//            int addMinutes = 5;
//            int addedTimeInMs = addMinutes * 60 * 1000;
//            long newTime = key.getTime() + addedTimeInMs;
//
//            for (int i = 0; i <= 11; i++) {
//                prognoseDaten.put(new Date(newTime), value);
//
//                System.out.println(key + " " + value + "added");
//            }
//        }
//        return prognoseDaten;
//    }


    public void handleAktuelleZeit(Date zeit, double gegenwaertigeStromzeugung) {

        stromVonAnlage = gegenwaertigeStromzeugung;

        for (LadePlan ladePlan : ladePlaene) {
            if (ladePlan.auto.autoIstDa && shouldCharge(zeit, gegenwaertigeStromzeugung, ladePlan)) {
                ladeSchlange.add(ladePlan.auto);
            }
        }
        handleLadeSchlange();
        ladeSchlange.clear();
    }

    private void handleLadeSchlange() {
        if (ladeSchlange.size() == 0) {
            return;
        }
//        if (ladeSchlange.size() == 2) {
//            for (Auto auto : ladeSchlange) {
//               priorisiereLadung();
//            }
//        }
        for (Auto auto : ladeSchlange) {
            stromAusLeitung = LADUNGS_BIT - stromVonAnlage;
            auto.charge(LADUNGS_BIT);

        }

    }

    boolean shouldCharge(Date uhrzeit, double gegenwaertigeStromzeugung, LadePlan ladePlan) {
        return ladePlan.shouldCharge(uhrzeit, gegenwaertigeStromzeugung);
    }

    public void makeNewLadeplan(Auto auto) {
        StehPeriode currentStehperiode = auto.aktuelleStehPeriode;
        SortedMap<Date, Double> prognoseDatenFuerStehperiode = selectPrognoseZeitraum(currentStehperiode.getAnkunft(), currentStehperiode.getAbfahrt());
        ladePlaene.add(new LadePlan(auto, prognoseDatenFuerStehperiode));
    }

    private SortedMap<Date, Double> selectPrognoseZeitraum(Date ankunft, Date abfahrt) {
        // Na ob das mal klappt :3

        return prognoseDaten.subMap(ankunft, abfahrt);
    }

    public void remomveLadePlan(Auto auto) throws Exception {
        for (LadePlan ladePlan : ladePlaene) {
            if (ladePlan.auto.id == auto.id) {
                ladePlaene.remove(ladePlan);
                System.out.println("auto with id" +  auto.id + " has been removed");
                return;
            }
        }
        throw new Exception("Auto nicht in Ladeschlange");
    }
};
