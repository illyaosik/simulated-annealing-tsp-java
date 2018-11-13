import java.util.ArrayList;

public class City {
    private int name;
    public ArrayList<Integer> distances;

    public City(int name, ArrayList<Integer> distances){
        this.name = name;
        this.distances = distances;
    }

    public int getName(){
        return this.name;
    }

    public int getDistanceToCity(City city){
        return distances.get(city.name);
    }

    public ArrayList<Integer> getAllDistances(){
        return distances;
    }
}
