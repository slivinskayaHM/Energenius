public class Output {


    public Output() {
    }
    // Werden von Solaranalage beeinflusst
    static double genutzertStromVonAnlage = 0;
    static double genutzertStromAusLeitung = 0;
    double gesammtGenutzerStrom = 0;

    // Wird von Runner beeinflusst
    double produzierterStromVonAnlage = 0;


    // All the energie that has been used, therefore, whenever a car gets charged this has to rise by Solaranlage.LADUNGS_BIT



    double gesparrtesGeld;
    // ToDo daten anpassen;

    /**
     *  Methode die jedes mal wenn die Zeit sich ändert vom Runner aufgerufen wird und variabalen updated, die nicht durch
     *  andere Klassen beeinflusst werden z.B: produzierterStromVonAnlage (wird aus realDaten bezogen)
     *
     *
     *
     **/
    public void addProducedOutput(double produzierterStrom){
        // System.out.println("--> Produzeierter Strom: " + produzierterStromVonAnlage + " ist bereits da, hinzugefügt wird: " + produzierterStrom);
        produzierterStromVonAnlage += produzierterStrom;
    };
    public void addUsedElectrictyFromPanel(double genutzterStrom) {
        // System.out.println("--> Genutzerter Strom aus Anlage: " + genutzertStromVonAnlage + " ist bereits da, hinzugefügt wird: " + genutzterStrom);
        genutzertStromVonAnlage += genutzterStrom;
    }
    public void addUsedElectrictyFromPowrLine(double genutzterStrom) {
        // System.out.println("--> Genutzerter Strom aus Leitung: " + genutzertStromAusLeitung + " ist bereits da, hinzugefügt wird: " + genutzterStrom);
        genutzertStromAusLeitung += genutzterStrom;
    }
    private double geldausGabeOhneSolarpanele(){
        gesammtGenutzerStrom = genutzertStromVonAnlage + genutzertStromAusLeitung;

        return (gesammtGenutzerStrom / 1000) * 0.3;
    }
    private static double gesamtGenutzerStromInEuro() {
        return ((genutzertStromVonAnlage/1000) * 0.07) +((genutzertStromAusLeitung/1000) * 0.3);
    }
    /**
     *  Methode die am Ende des runners aufgerufen wird und eine Ausgabe mit allen relevanten Daten erzeugt
     **/
    public void generateOutput(){
        System.out.println("+--------------------------------------------------------+");
        System.out.println("                                                          ");
        System.out.println("          Herzlich Willkommen bei ENERGENIUS!             ");
        System.out.println(" Sie haben sich für uns entschieden um Geld zu sparen und ");
        System.out.println(" dabei noch etwas Gutes für die Umwelt zu tun. Danke!  ");
        System.out.println(" Mit der Verwendung von Energenius werden die Kosten dadurch ");
        System.out.println(" eingespart, indem eine Priorisierung der Ladestunden erstellt");
        System.out.println(" wird. Somit müssen Sie als Besitzer nur Ihre nächste Abfahrt");
        System.out.println(" angeben, und unser Algorithmus errechnet für Sie ganz von allein, wann");
        System.out.println(" die besten Stunden für eine Ladung sind! Beispielsweise nicht");
        System.out.println(" Nachts, oder wenn Sonnigere Stunden im Ladeplan erwartet werden :)");

        System.out.println(" ");
        System.out.println("               Kommen wir nun zu Ihren Statistiken: ");
        System.out.println(" Ohne ENERGENIUS hätten Sie für den Testzeitraum von 6 Tagen bis zu");
        System.out.println("                 " + Math.ceil(geldausGabeOhneSolarpanele()) +" Euro für Strom ausgegeben!");
        System.out.println(" MIt ENERGENIUS liegen ihre Kosten hingegen bei  " + Math.ceil(gesamtGenutzerStromInEuro()) + " Euro pro Tag.");
        System.out.println(" Somit hätten sie ohne ENERGENIUS --- " + Math.ceil(prozentzahl())+ "% ---  mehr gezahlt");
        System.out.println("        und sparrten " + (Math.ceil(geldausGabeOhneSolarpanele()) - Math.ceil(gesamtGenutzerStromInEuro())) + " Euro in einer einzigen Woche");
        System.out.println("+--------------------------------------------------------+");
    };

    private static double prozentzahl() {
        return (genutzertStromVonAnlage * 0.07 / gesamtGenutzerStromInEuro());
    }

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
