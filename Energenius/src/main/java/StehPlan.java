import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StehPlan {
    ArrayList<StehPeriode> stehPerioden;
    HashMap<Date, Double> prognoseDaten;

    public StehPlan(ArrayList<StehPeriode> stehPerioden) {
        this.stehPerioden = stehPerioden;
    }


    public StehPeriode getCurrentStehperiode(Date aktuelleZeit) {
        for (StehPeriode stehPeriode : stehPerioden) {
            if (stehPeriode.getAbfahrt().before(aktuelleZeit)) {
                if (stehPeriode.getAnkunft().after(aktuelleZeit)) {
                    return stehPeriode;
                }
            }
        }
        return null;
    };

    public int gibNötigeDurchschnittsladung (){

    }

    //Todo: Use some of these methods to update car Stehperiode
    Date getAbwesenheit() {
        return new Date();
    }

    Date getNextAnwesenheit() {
        return new Date();
    }

    Date getCurrentAnwesenheit() {
        return new Date();
    }



}
