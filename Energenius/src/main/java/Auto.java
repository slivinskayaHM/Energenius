import java.util.Date;

public class Auto {
    double akkuStand;
    double maxAkku;
    String id;
    StehPlan stehPlan;
    Date aktuelleZeit;
    boolean autoIstDa = false;
    StehPeriode aktuelleStehPeriode;
    Solaranlage home;


    
    public Auto(double maxAkku, String id, StehPlan stehPlan, Solaranlage home) {
        this.maxAkku = maxAkku;
        this.id = id;
        this.stehPlan = stehPlan;
        this.akkuStand = 0;
        this.home = home;
    }

    public void charge(double watt) {
        akkuStand += watt;
        System.out.println( this.id + " wurde geladen --> neuer Akkustand: " + akkuStand + "; benötigt für nächste Fahrt: " + aktuelleStehPeriode.getBenoetigteLadung());
    }


    public void handleAktuelleZeit(Date aktuelleZeit) throws Exception {
        this.aktuelleZeit = aktuelleZeit;
        // System.out.println("" + id + "handles time: " + aktuelleZeit);
        StehPeriode aktuelleStehPeriode = stehPlan.getCurrentStehperiode(aktuelleZeit);
        if (aktuelleStehPeriode == null) {
            if (autoIstDa) {
                System.out.println(this.id + " fährt ab!");
                autoFaehrtAb();
                autoIstDa = false;
            }
        } else {
            if (this.aktuelleStehPeriode == null) {
                this.aktuelleStehPeriode = aktuelleStehPeriode;
            }
            if (!autoIstDa){
                autoKommtAn();
                System.out.println(this.id + " ist angekommen. Verbliebene Ladung: " + akkuStand);
            }
            autoIstDa = true;
        }

    }

    void autoFaehrtAb() throws Exception {
        if (akkuStand < aktuelleStehPeriode.getBenoetigteLadung()) {
            throw new Exception ("Akkustand im Auto zu niedrig für geplante fahrt! Akkustand:" + akkuStand + " benötigte ladung: " + aktuelleStehPeriode.getBenoetigteLadung());
        }
        home.remomveLadePlan(this);
        autoIstDa = false;
        akkuStand = akkuStand - aktuelleStehPeriode.benoetigteLadung;
        aktuelleStehPeriode = null;

    }

    void autoKommtAn(){
        autoIstDa = true;
        home.makeNewLadeplan(this);
    }

    // -------------------- setter + getter -------------------------------------


    public double getBenoetigteLadung() {

       return aktuelleStehPeriode.getBenoetigteLadung() - akkuStand;
    }


    public double getAkkuStand() {
        return akkuStand;
    }

    public double getMaxAkku() {
        return maxAkku;
    }

    public String getId() {
        return id;
    }


}
