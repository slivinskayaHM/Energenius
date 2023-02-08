import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Solaranlage {
    static final double CHARGEPERTICK = 4000 / 12;

    double gegenwärtigeStromzeugung;

    HashMap<Date, Double> Prognosedaten;
    ArrayList<Auto> autos = new ArrayList<>();

    public void handleAktuelleZeit() {

        for (Auto auto : autos) {
            if (auto.autoIstDa && shouldCharge()) {
                auto.charge(CHARGEPERTICK);
            }
        }
    }



    public Solaranlage(HashMap<Date, Double> Prognosedaten) {
        this.Prognosedaten = Prognosedaten;
    }



    boolean shouldCharge(Realdatum Uhrzeit, double gegenwärtigeStromzeugung) {
        return false;
    }




}
