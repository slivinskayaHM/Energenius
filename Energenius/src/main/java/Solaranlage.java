import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Solaranlage {
    static final double LADUNGS_BIT = 4000 / 12;

    double gegenwärtigeStromzeugung;

    HashMap<Date, Double> prognoseDaten;
    ArrayList<Auto> autos = new ArrayList<>();
    ArrayList<Auto> ladeSchlange = new ArrayList<>();

    public void handleAktuelleZeit(Date zeit, double gegenwärtigeStromzeugung) {

        for (Auto auto : autos) {
            if (auto.autoIstDa && shouldCharge(zeit, gegenwärtigeStromzeugung, auto)) {
                ladeSchlange.add(auto);
            }
        }
        handleLadeSchlange();
    }

    private void handleLadeSchlange() {
        if (ladeSchlange.size() == 0) {
            return;
        }
        if (ladeSchlange.size() == 2) {
            for (Auto auto : ladeSchlange) {
               priorisiereLadung();
            }
        }
            for (Auto auto : ladeSchlange) {
                auto.charge(LADUNGS_BIT);
            }

    }


    public Solaranlage(HashMap<Date, Double> prognoseDaten) {
        this.prognoseDaten = prognoseDaten;
    }



    boolean shouldCharge(Date uhrzeit, double gegenwärtigeStromzeugungm, Auto auto) {
        return false;
    }




};
