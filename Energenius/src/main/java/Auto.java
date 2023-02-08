import java.util.Date;

public class Auto {
    double akkuStand;
    double maxAkku;
    String id;
    StehPlan stehPlan;
    Date aktuelleZeit;
    boolean autoIstDa;
    StehPeriode aktuelleStehPeriode;


    
    public Auto(double maxAkku, String id, StehPlan stehPlan) {
        this.maxAkku = maxAkku;
        this.id = id;
        this.stehPlan = stehPlan;
        this.akkuStand = 0;
    }

    public void charge(double watt) {
        akkuStand += watt;
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
        autoIstDa = false;
    }

    void autoKommtAn(){
        autoIstDa = true;
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
