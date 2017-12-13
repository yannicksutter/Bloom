import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {


    int erwarteteAnzahl;
    int filtergrösse;
    int hashfunktionen;

    public static void main(String[] args) throws IOException {
        double fehlerwahrscheinlichkeit = Double.parseDouble(args[0]);

        FileReader fr = new FileReader("words.txt");
        BufferedReader br = new BufferedReader(fr);

        int counter = 0;
        String line;
        while ((line = br.readLine()) != null) {
            counter++;
            System.out.println(line);
        }
        //doppelt so gross wie anzahl elemente
        int filtergrösse = 2*counter;
        int [] array = new int[filtergrösse];
        System.out.println(array.length);
        br.close();




    }
}
