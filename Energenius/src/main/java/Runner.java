import java.util.*;

public class Runner {
    ArrayList<StehPeriode> fahrten;
    TreeMap<Date, Double> prognoseDaten;
    static TreeMap<Date, Double> realDaten = InitData.realDaten;
    public static void main(String[] args) {
        //Todo Auto, Output und Solaranalage initialisieren
        for (Map.Entry<Date, Double> entry :  realDaten.entrySet()) {
            //Todo handleTime von Auto, Output und Solaranale auslößen
            // handleTime();
        }

        //Todo output.Data ausgeben
    }




    Auto makeCar1() {
       return new Auto(60000, "Auto1" , makeFahrplan());
    }



    StehPlan makeFahrplan(){
        ArrayList<StehPeriode> fahrten = new ArrayList<>();

        fahrten.add(
                new StehPeriode(
                new Date(2023, 2, 13, 14, 40),
                new Date(2023, 2,14,6,0),
                25000
                )
        );
        fahrten.add(
                new StehPeriode(
                        new Date(2023, 2, 14, 12, 45),
                        new Date(2023, 2,15,6,10),
                        21000
                )
        );

        return new StehPlan(fahrten);
    }

}
