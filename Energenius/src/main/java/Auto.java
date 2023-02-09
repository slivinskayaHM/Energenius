import java.util.Date;

public class Auto {
    double akkuStand;
    double maxAkku;
    String id;
    StehPlan stehPlan;
    Date aktuelleZeit;
    boolean autoIstDa;
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
        System.out.println("Auto wurde geladen --> neuer Akkustand: " + akkuStand);
    }


    public void handleAktuelleZeit(Date aktuelleZeit) throws Exception {
        this.aktuelleZeit = aktuelleZeit;
        StehPeriode aktuelleStehPeriode = stehPlan.getCurrentStehperiode(aktuelleZeit);
        if (aktuelleStehPeriode == null && autoIstDa) {
           autoFaehrtAb();
        } else {
            if (!autoIstDa){
                autoKommtAn();
            }
        }

    }

    void autoFaehrtAb() throws Exception {
        if (akkuStand < aktuelleStehPeriode.getBenoetigteLadung()) {
            throw new Exception ("Akkustand im Auto zu niedrig für geplante fahrt!");
        }
        home.remomveLadePlan(this);
        autoIstDa = false;
        System.out.println("Auto ist abgefahren");
    }

    void autoKommtAn(){
        autoIstDa = true;
        home.makeNewLadeplan(this);
        System.out.println("Auto ist angekommen");
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
