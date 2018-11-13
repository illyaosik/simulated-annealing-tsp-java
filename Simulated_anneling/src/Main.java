import java.io.IOException;

public class Main {
    public static String data1 = "/Users/illiaosiyuk/Simulated_anneling/src/tsp_data.txt";
    public static String data2 = "/Users/illiaosiyuk/Simulated_anneling/src/tsp_data2.txt";
    public static void main(String[] args) throws IOException {

        //MyReader.getArrayOfCities().toString();

        MyReader.read(data2);

        //MyReader.getArrayOfCities().toString();

        Simulated_anneling a = new Simulated_anneling();
        a.simulate();
        //Simulated_anneling.simulate();
    }
}
