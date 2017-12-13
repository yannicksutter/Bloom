import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    int erwarteteAnzahl;
    int filtergrösse;
    int hashfunktionen;
    static int[] seeds;

    public static void main(String[] args) throws IOException {
        double p = Double.parseDouble(args[0]);

        FileReader fr = new FileReader("words.txt");
        BufferedReader br = new BufferedReader(fr);

        List<String> words = new ArrayList<String>();

        String line;
        while ((line = br.readLine()) != null) {
            words.add(line);
            System.out.println(line);
        }
        br.close();

        double m = Math.ceil((words.size() * Math.log(p)) / Math.log(1.0 / (Math.pow(2.0, Math.log(2.0)))));
        double k = Math.round(Math.log(2.0) * m / words.size());
        seeds = createSeeds(k);
        for(String word : words) {
            addToBloomFilter(word);
        }



    }

    private static void addToBloomFilter(String word) {

    }

    private static boolean isInBloomFilter() {
        return false;
    }



    private static int[] createSeeds(double k) {
        int[] seeds = new int[(int) k];
        for(int i = 0; i < k; i++) {
            seeds[i] = (int) Math.random() * 100000;
        }
    }
}
