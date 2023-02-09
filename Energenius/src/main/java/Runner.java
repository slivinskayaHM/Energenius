import java.util.*;

public class Runner {
    ArrayList<StehPeriode> fahrten;
    static TreeMap<Date, Double> prognoseDaten = rechneAufTicksRunter(InitData.makePrognoseDaten());
    static TreeMap<Date, Double> realDaten = InitData.makeRealDaten();

    static Solaranlage home;
    static ArrayList<Auto> autos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        //Todo Output initialisieren
        home = new Solaranlage(prognoseDaten);

        autos.add(InitData.makeAuto1(home));
        autos.add(InitData.makeAuto2(home));

        for (Map.Entry<Date, Double> entry :  realDaten.entrySet()) {
            Date aktuelleZeit = entry.getKey();
            double aktuelleStromerzeugung = entry.getValue();

            home.handleAktuelleZeit(aktuelleZeit, aktuelleStromerzeugung);
            for (Auto auto : autos) {
                auto.handleAktuelleZeit(aktuelleZeit);
            }
        }

        System.out.println("------------------------- fertig ------------------------");
        //Todo output.Data ausgeben
    }



    static StehPlan makeStehPlan(){
        ArrayList<StehPeriode> fahrten = new ArrayList<>();
        fahrten.add(
                new StehPeriode(
                new Date(2023, 1, 13, 14, 40),
                new Date(2023, 1,14,6,0),
                25000
                )
        );
        fahrten.add(
                new StehPeriode(
                        new Date(2023, 1, 14, 12, 45),
                        new Date(2023, 1,15,6,10),
                        21000
                )
        );
        return new StehPlan(fahrten);
    }

    private static TreeMap<Date, Double> rechneAufTicksRunter(TreeMap<Date, Double> stuendlicheDaten) {
        TreeMap<Date, Double> returnMap = new TreeMap<>();
        for(Map.Entry<Date, Double> entry : stuendlicheDaten.entrySet()) {
            Date key = entry.getKey();
            Double value = entry.getValue();

            int addMinutes = 5;
            int addedTimeInMs = addMinutes * 60 * 1000;
            long newTime = key.getTime();

            for (int i = 0; i <= 10; i++) {
                newTime += addedTimeInMs;
                Date newDate = new Date(newTime);
                returnMap.put(newDate, value);
                System.out.println( newDate + " / " + value + " added");
            }
        }
        return returnMap;
    }

}
