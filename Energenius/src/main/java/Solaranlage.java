import java.util.Date;
import java.util.HashMap;

public class Solaranlage {
    double gegenwärtigeStromzeugung;
    HashMap<Date, Double> Prognosedaten;

    public Solaranlage(HashMap<Date, Double> Prognosedaten) {
        this.Prognosedaten = Prognosedaten;
    }
    boolean shouldCharge(Realdatum Uhrzeit, double gegenwärtigeStromzeugung) {
        return false;
    }
}
