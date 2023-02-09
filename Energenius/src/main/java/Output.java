public class Output {


    // Werden von Solaranalage beeinflusst
    double genutzertStromVonAnlage;
    double genutzertStromAusLeitung;
    double gesammtGenutzerStrom;

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
    public void handleTime(double produzierterStrom){
        produzierterStromVonAnlage += produzierterStrom;
    };
    public void usedElectrictyFromPanel(double genutzterStrom) {
        genutzertStromVonAnlage += genutzterStrom;
    }
    public void usedElectrictyFromPowrLine(double genutzterStrom) {
        genutzertStromAusLeitung += genutzterStrom;
    }
    private double geldausGabeOhneSolarpanele(){
        return (gesammtGenutzerStrom / 1000) * 0.3;
    }
    private double gesamtGenutzerStromInEuro() {
        return ((genutzertStromVonAnlage/1000) * 0.07) +((genutzertStromAusLeitung/1000) * 0.3);
    }
    /**
     *  Methode die am Ende des runners aufgerufen wird und eine Ausgabe mit allen relevanten Daten erzeugt
     **/
    public void generateOutput(){
        System.out.println("+--------------------------------------------------------+");
        System.out.println("                                                          ");
        System.out.println("          Herzlich Willkommen bei Energenius!             ");
        System.out.println(" Sie haben sich für uns entschieden um Geld zu sparen und ");
        System.out.println(" dabei noch etwas Gutes für die Umwelt zu tun. Danke!  ");
        System.out.println(" ");
        System.out.println(" Kommen wir nun zu Ihren Statistiken: ");
        System.out.println(" Ohne Solarpanele hätten Sie für den Testzeitraum von 6 Tagen");
        System.out.println(geldausGabeOhneSolarpanele() / 6 +" Euro pro Tag für Strom ausgegeben!");
        System.out.println("");
        System.out.println(" Wenn Sie also nach einem langen oder auch kurzen Tag nach ");
        System.out.println(" Hause kommen und Ihr Auto an die Ladesäule stecken und nur");
        System.out.println(" eine weitere Person aus Ihrem Hausstand es Ihnen gleich tut");
        System.out.println(" mit ihrem Auto, geben Sie am Tag bei einer gesamten benötigten");
        System.out.println(" Ladung von 38kW (Was unter dem Durchschnitt liegt) 9,92 Euro ");
        System.out.println(" aus!");
        System.out.println(" Mit der Verwendung von Energenius werden die Kosten dadurch ");
        System.out.println(" eingespart, indem eine Priorisierung der Ladestunden erstellt");
        System.out.println(" wird. Somit müssen Sie als Besitzer nur Ihre nächste Abfahrt");
        System.out.println(" angeben, und somit errechnet unser Algorithmus für Sie, wann");
        System.out.println(" die besten Stunden für eine Ladung sind! Beispielsweise nicht");
        System.out.println(" Nachts. :)");
        System.out.println(" Dadurch liegen Ihre Kosten durchschnittlich bei " + gesamtGenutzerStromInEuro() / 6 + " Euro");
        System.out.println(" pro Tag.");
        System.out.println("+--------------------------------------------------------+");
    };

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
