public class Output {


    // Werden von Solaranalage beeinflusst
    double genutzertStromVonAnlage;
    double genutzertStromAusLeitung;
    double gesammtGenutzerStrom;

    // Wird von Runner beeinflusst
    double produzierterStromVonAnlage;


    // All the energie that has been used, therefore, whenever a car gets charged this has to rise by Solaranlage.LADUNGS_BIT



    double gesparrtesGeld;
    // ToDo daten anpassen;

    /**
     *  Methode die jedes mal wenn die Zeit sich ändert vom Runner aufgerufen wird und varibalen updated, die nicht durch
     *  andere Klassen beeinflusst werden z.B: produzierterStromVonAnlage (wird aus realDaten bezogen)
     **/
    public void handleTime();

    /**
     *  Methode die am Ende des runners aufgerufen wird und eine Ausgabe mit allen relevanten Daten erzeugt
     **/
    public void generateOutput();

    public void setGenutzertStromVonAnlage(double genutzertStromVonAnlage) {
        this.genutzertStromVonAnlage = genutzertStromVonAnlage;
    }

    public void setGenutzertStromAusLeitung(double genutzertStromAusLeitung) {
        this.genutzertStromAusLeitung = genutzertStromAusLeitung;
    }

    public void setGesammtGenutzerStrom(double gesammtGenutzerStrom) {
        this.gesammtGenutzerStrom = gesammtGenutzerStrom;
    }
}
