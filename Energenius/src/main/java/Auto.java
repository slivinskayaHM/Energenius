public class Auto {
    double Akkustand;
    double maxAkku;
    String id;
    Fahrplan fahrplan;
    public Auto(double maxAkku, String id, Fahrplan fahrplan) {
        this.maxAkku = maxAkku;
        this.id = id;
        this.fahrplan = fahrplan;
        this.Akkustand = 0;
    }
}
