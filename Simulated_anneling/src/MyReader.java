import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyReader {
    public static ArrayList<City> cities = new ArrayList<>();

    public static void read(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));
        int name = 0;

        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            ArrayList<Integer> distances = new ArrayList<>();
            for(String s : line){
                distances.add(Integer.parseInt(s));
            }

            cities.add(new City(name,distances));
            name++;
        }
    }
    public static ArrayList<City> getArrayOfCities(){
        return cities;
    }
}
