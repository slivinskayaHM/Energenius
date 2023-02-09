import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class StehPlan {
    ArrayList<StehPeriode> stehPerioden ;

    public StehPlan(ArrayList<StehPeriode> stehPerioden) {
        this.stehPerioden = stehPerioden;
    }


    public StehPeriode getCurrentStehperiode(Date aktuelleZeit) {
        // System.out.println("Checking if Auto is in Stehperiode at " + aktuelleZeit);

        for (StehPeriode stehPeriode : stehPerioden) {

            if (stehPeriode.getAbfahrt().after(aktuelleZeit)) {

                if (stehPeriode.getAnkunft().before(aktuelleZeit)) {

                    return stehPeriode;
                }
            }
        }
        return null;
    };

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
