import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int[] bloom;
    static List<HashFunction> functions = new ArrayList<>();
    static int m;

    public static void main(String[] args) throws IOException {
        double p = Double.parseDouble(args[0]);


        Scanner words = new Scanner(new File("words.txt"));
        List<String> bloomWords = new ArrayList<>();
        System.out.println("Die Wörter aus words.txt für den BloomFilter einlesen.");
        while(words.hasNext()) {
            String word = words.next();
            bloomWords.add(word);
        }

        int n = bloomWords.size();
        m = (int) -((n * Math.log(p)) / (Math.log(2) * Math.log(2)));

        //create Hashfunctions
        System.out.println("Hashfunktionen generieren");
        double k = Math.round(Math.log(2.0) * m / n);
        for (int i=0; i<k; i++) {
            functions.add(Hashing.murmur3_128(i));
        }

        bloom = new int[m];

        //add words to bloomfilter
        for(String word : bloomWords) {
            addToBloomFilter(word);
        }

        //test other words with testwords.txt
        System.out.println("Testen der Wörter einige aus words.txt und ein paar selber geschrieben");
        Scanner test = new Scanner(new File("testwords.txt"));

        int countFalsePositives = 0;
        int countWords = 0;
        while(test.hasNext()) {
            String next = test.next();
            if(isInBloomFilter(next)) {
                countFalsePositives++;
            }
            countWords++;
        }

        System.out.println("Erwartet p=" + p);
        System.out.println("Gemessen   p=" + MessageFormat.format("{0,number,#.###}", (double)countFalsePositives / (double)countWords));

    }

    private static void addToBloomFilter(String word) {
        for(HashFunction hf : functions) {
            HashCode code = hf.hashString(word, Charset.defaultCharset());
            int index = Math.abs(code.asInt()) % m;
            bloom[index] = 1;
        }
    }

    private static boolean isInBloomFilter(String word) {
        for(HashFunction hf: functions) {
            HashCode code = hf.hashString(word, Charset.defaultCharset());
            int index = Math.abs(code.asInt()) % m;
            if(bloom[index] == 0) {
                return false;
            }
        }
        return true;
    }
}
