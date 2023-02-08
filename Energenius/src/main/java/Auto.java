import java.util.Date;

public class Auto {
    double akkuStand;
    double maxAkku;
    String id;
    Fahrplan fahrplan;
    Date aktuelleZeit;
    boolean autoIstDa;
    StehPeriode aktuelleStehPeriode;


    
    public Auto(double maxAkku, String id, Fahrplan fahrplan) {
        this.maxAkku = maxAkku;
        this.id = id;
        this.fahrplan = fahrplan;
        this.akkuStand = 0;
    }

    public void charge(double watt) {
        akkuStand += watt;
    }


    public void handleAktuelleZeit(Date aktuelleZeit) {
        this.aktuelleZeit = aktuelleZeit;
        StehPeriode aktuelleStehPeriode = fahrplan.getCurrentStehperiode(aktuelleZeit);
        if (aktuelleStehPeriode == null && autoIstDa) {
           autoFaehrtAb();
        } else {
            if (!autoIstDa){
                autoKommtAn();
            }
        }
    }

    void autoFaehrtAb(){
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
