import java.util.Date;

public class StehPeriode {
    Date Ankunft;
    Date Abfahrt;
    double benoetigteLadung;

    public StehPeriode(Date ankunft, Date abfahrt, double benoetigteLadung) {
        Ankunft = ankunft;
        Abfahrt = abfahrt;
        this.benoetigteLadung = benoetigteLadung;
    }

    public Date getAnkunft() {
        return Ankunft;
    }

    public Date getAbfahrt() {
        return Abfahrt;
    }

    public double getBenoetigteLadung() {
        return benoetigteLadung;
    }
}
