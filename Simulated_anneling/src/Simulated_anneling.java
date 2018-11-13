import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Simulated_anneling {
    public static double TEMPERATURE = 100;
    public static double TEMPERATURE_coefficient = 0.9999;
    public int TOTAL_COST;

    public ArrayList<City> temp_seq;

    public ArrayList<City> route;
    public ArrayList<City> new_route;

    public ArrayList<City> best;
    public int best_cost;
    //public static int last_cost;


    public void simulate(){

        startSequence();
        TOTAL_COST = calculateTotalCost(route);

        best = new ArrayList<>(route);
        best_cost = TOTAL_COST;



        int counter = 1;
        while (Math.round(TEMPERATURE*100) != 0) {

            new_route = new ArrayList<>(swapElements()); //something

            int new_cost = calculateTotalCost(new_route);
            makeDecision(new_cost);

            TEMPERATURE = TEMPERATURE * TEMPERATURE_coefficient;

            if(best_cost>TOTAL_COST){
                best_cost=TOTAL_COST;
                best = new ArrayList<>(route);
            }


            for(int i = 0; i < route.size(); i++){
                System.out.print((route.get(i).getName()+1) + " ->");
            }
            System.out.println();
            System.out.println(TEMPERATURE);
            System.out.println();
            System.out.println(TOTAL_COST);
            counter++;
        }

        System.out.println("__________________________");



        for(int i = 0; i < route.size(); i++){
            System.out.print((route.get(i).getName()+1) + " -");
        }
        System.out.println();

        System.out.println("Last cost: " + TOTAL_COST);
        System.out.println("Number of interations: " + counter);

        System.out.println("Best route: ");
        for(int i = 0; i < best.size(); i++){
            System.out.print((best.get(i).getName()+1) + " -");
        }
        System.out.println();
        System.out.println("Best cost: " + best_cost);
    }

//--------------------------------------------------------------------------------


    public  ArrayList<City> swapElements(){
        temp_seq = new ArrayList<>(route);



        int x1 = new Random().nextInt(this.route.size());
        int x2 = new Random().nextInt(this.route.size());
        if(x1==x2 || x1==0 || x1==route.size()-1
                || x2==0 || x2==route.size()-1){
            swapElements();
        }else {
            temp_seq.set(x1, route.get(x2));
            temp_seq.set(x2, route.get(x1));
        }

        return temp_seq;
    }

    public int calculateTotalCost(ArrayList<City> route){
        int total_cost = 0;
        for(int i = 0, j = 1; j < route.size() ;i++,j++){
            total_cost += route.get(i).getDistanceToCity(route.get(j));
        }
        return total_cost;
    }

    public boolean calculateProbability(int diffrente_of_cost){
        int pRandom = new Random().nextInt(100);
        double pk = 100 / Math.pow(Math.E,(diffrente_of_cost/TEMPERATURE));
        if(pRandom > pk){
            return false;
        }else {
            return true;
        }
    }

    public void makeDecision(int new_cost){   //if something wrong in solution, fix it.
        if(new_cost<TOTAL_COST){
            TOTAL_COST = new_cost;
            route = new ArrayList<>(new_route);
        }else {
            int diffrence = new_cost - TOTAL_COST;
            if(calculateProbability(diffrence)==true){
                TOTAL_COST = new_cost;
                route = new ArrayList<>(new_route);
            }else {
                TOTAL_COST = TOTAL_COST; //for me, because I can forgot
            }
        }
    }

    public void startSequence(){
        ArrayList<City> temp_seq = new ArrayList<>();
        int city = new Random().nextInt(MyReader.getArrayOfCities().size()); //start city
        temp_seq.add(MyReader.getArrayOfCities().get(city));


        city = -1;

        while(temp_seq.size()!=MyReader.getArrayOfCities().size()+1){
            city = new Random().nextInt(MyReader.getArrayOfCities().size());
            if(!temp_seq.contains(MyReader.getArrayOfCities().get(city))){
                temp_seq.add(MyReader.getArrayOfCities().get(city));
            }
            if(temp_seq.size()==(MyReader.getArrayOfCities().size())){
                city = temp_seq.get(0).getName();
                temp_seq.add(MyReader.getArrayOfCities().get(city));
            }
        }
        route = new ArrayList<>(temp_seq);
    }
}
