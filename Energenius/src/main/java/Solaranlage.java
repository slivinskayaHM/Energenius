import java.util.*;

public class Solaranlage {
    static final double LADUNGS_BIT = 4000 / 12;

    TreeMap<Date, Double> prognoseDaten;
    //ArrayList<Auto> autos = new ArrayList<>();
    ArrayList<LadePlan> ladePlaene = new ArrayList<LadePlan>();
    ArrayList<LadePlan> ladeSchlange = new ArrayList<>();


    double stromVonAnlage = 0;
    double stromAusLeitung = 0;
    Date currentDate;
    Output report;

    boolean didNotShowCurrentDate = true;

    public Solaranlage(TreeMap<Date, Double> prognoseDaten, Output report) {
        this.prognoseDaten = prognoseDaten;
        this.report = report;
    }



    public void handleAktuelleZeit(Date zeit, double gegenwaertigeStromzeugung) {
        stromVonAnlage = gegenwaertigeStromzeugung;
        report.addProducedOutput(gegenwaertigeStromzeugung / 12);
        currentDate = zeit;


        if (didNotShowCurrentDate) {
            System.out.println("----------------------------  " + currentDate + "  ----------------------------");
            didNotShowCurrentDate = false;
        }

        for (LadePlan ladePlan : ladePlaene) {
            if (ladePlan.auto.autoIstDa && shouldCharge(currentDate, gegenwaertigeStromzeugung, ladePlan)) {
                ladeSchlange.add(ladePlan);
            }
        }
        if (ladeSchlange.size() > 0) {
            handleLadeSchlange();
        }
        beendeZeitEinheit();
    }

    private void beendeZeitEinheit() {
        ladeSchlange.clear();
        didNotShowCurrentDate = true;
        stromVonAnlage = 0;
        stromAusLeitung = 0;
    }

    private void handleLadeSchlange() {
        Collections.sort(ladeSchlange);
        charge(ladeSchlange.get(0).auto);
        ladeSchlange.remove(0);

        if (ladeSchlange.size() != 0) {
            System.out.println(" Auto mit höchster Priorität wurde geladen. Suche Autos mit Priorität 'forceCharge'");
            for (LadePlan ladePlan : ladeSchlange) {
                if (ladePlan.forceCharge) {
                    charge(ladePlan.auto);
                }
            }
        }
    }

    private void charge(Auto auto) {
        auto.charge(LADUNGS_BIT);
        stromAusLeitung += LADUNGS_BIT - stromVonAnlage / 12;
        if (LADUNGS_BIT >= stromVonAnlage / 12) {
            report.addUsedElectrictyFromPanel(stromVonAnlage / 12);
            stromVonAnlage = 0;
        } else {
            report.addUsedElectrictyFromPanel(LADUNGS_BIT);
            stromVonAnlage -= LADUNGS_BIT;
        }
        report.addUsedElectrictyFromPowrLine(stromAusLeitung);
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
