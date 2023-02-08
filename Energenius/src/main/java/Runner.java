import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Runner {
    ArrayList<StehPeriode> fahrten;
    HashMap<Date, Double> Prognosedaten;
    HashMap<Date, Double> Realdaten;
    public static void main(String[] args) {

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
