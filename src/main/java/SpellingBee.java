import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SpellingBee {

    public static char hiveCenter;
    public static int centerBit;
    public static int maxScore;

    public static Scanner scan = new Scanner(System.in);

    private static  ArrayList<String> dictionary = new ArrayList<String>();        

    private static final double[] weights = {
        8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2,
        0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1,
        2.8, 1.0, 2.4, 0.2, 2.0, 0.1
    };

    private static final double[] cumulative = buildCumulative(weights);
    public static void main(String[] args) {
        ArrayList<String> hive = createHive();
        ArrayList<String> dictionary = createDictionary(hive);
        Set<String> found = new HashSet<>();
        Set<String> dictSet = new HashSet<>(dictionary);
        int hiveMask = getMaskFromArray(hive);
        for (int i = 0; i < dictionary.size(); i++){
            String word = dictionary.get(i);
            if(word.length() == 4){
                maxScore++;
            }
            else if(word.length() > 4) {
                if((getMaskFromWord(word) & hiveMask) == hiveMask){
                    maxScore += (word.length() + 7);
                } else {
                    maxScore += word.length();
                }
            }
            else{
                continue;
            }
        }
        System.out.println(dictionary.size());
        System.out.print(hive);
        System.out.println(" Center letter is: " + hiveCenter + "\n Maximum possible score is: " + maxScore);
        boolean workFlag = true;
        
        
        
        int score = 0;
        while(workFlag){
            String guess = input("Score: " + score +  "\nGuess a word").toUpperCase();
            int wordMask = getMaskFromWord(guess);
            if(guess.length() < 4){
                System.out.println("Guesses must be 4 or more letters.");
                continue;
            }
            else if((wordMask & ~hiveMask) != 0){
                System.out.println("Illegal letter.");
                continue;
            }
            else if((wordMask & centerBit) == 0){
                System.out.println("Missing center letter.");
                continue;
            }
            else if(found.contains(guess)){
                System.out.println("Word already found");
                continue;
            }
            else if(!dictSet.contains(guess)){
                System.out.println("Guess not in word list.");
                continue;
            }
            else {
                int points = (guess.length()==4 ? 1 : guess.length());
                if ((wordMask & hiveMask) == hiveMask)
                {
                    points = points + 7;
                }
                score += points;
                found.add(guess);
                System.out.println("Correct! Score + " + points);
                continue;
            }
            
        }

    }

    private static String input(String s){
        System.out.println(s);
        String input = scan.nextLine();
        return input;
    }

    private static ArrayList<String> createHive(){
        ArrayList<String> hive = new ArrayList<String>();
        
        do{
            hive.clear();
            for (int i = 0; i < 7; i++){
                String letter = getWeightedLetter();
                if(hive.contains(letter)){
                    i--;
                }
                else{
                    hive.add(letter);
                }

                
        }

        } while(!containsVowel(hive));

        
        return hive;


    }

    private static boolean containsVowel(ArrayList<String> hive) {
        for (String s : hive) {
            char c = s.charAt(0);
            if ("AEIOU".indexOf(c) >= 0) return true;
        }
        return false;
    }
    private static String getWeightedLetter(){
        double r = Math.random() * cumulative[cumulative.length - 1];
        for (int i = 0; i < cumulative.length; i++) {
            if (r < cumulative[i]) {
                return String.valueOf((char) ('A' + i));
            }
        }
        return "Z";
    }
    private static double[] buildCumulative(double[] weights) {
        double[] cumulative = new double[weights.length];
        double sum = 0;
        for (int i = 0; i < weights.length; i++){
            sum += weights[i];
            cumulative[i] = sum;
        }
        return cumulative;
    }
    private static ArrayList<String> createDictionary(ArrayList <String> hive){
        dictionary.clear();
        int hiveMask = getMaskFromArray(hive);
        int centerRandom = (int) (Math.random() * 7);
        char center = hive.get(centerRandom).toUpperCase().charAt(0);
        hiveCenter = center;
        int centerIdx = center - 'A';
        if (centerIdx < 0 || centerIdx >= 26) {
            throw new IllegalStateException("Center must be A - Z");
        }
        centerBit = 1 << (center - 'A');
        try (InputStream in = SpellingBee.class.getResourceAsStream("/enable1.txt")) {
            if (in == null) {
                throw new IOException("Resource '/enable1.txt' not found on classpath. " + "Make sure it's in src/main/resources/");
            }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // (Optional) normalize once
                if (line.length() >= 4) {
                    int wordMask = getMaskFromWord(line);
                    if ((wordMask & ~hiveMask) == 0 && (wordMask & centerBit) != 0) {
                        dictionary.add(line.toUpperCase());
                    }
            }
        }
    }
} catch (IOException e) {
    System.out.println("Error reading resource enable1.txt: " + e.getMessage());
}
    
    return dictionary;
    }

    private static int getMaskFromWord(String word){
        int mask = 0;
        String ucWord = word.toUpperCase();
        for (int i = 0; i < ucWord.length(); i++ ){
            int index = ucWord.charAt(i) - 'A';
            int bit = 1 << index;
            mask = mask | bit;
        }
        //System.out.println(Integer.toBinaryString(mask));
        return mask;
    }

    private static int getMaskFromArray(ArrayList<String> array){
        
        int mask = 0;
        for (int i = 0; i < array.size(); i++){
            String word = array.get(i);
            int index = word.charAt(0) - 'A';
            int bit = 1 << index;
            mask = mask | bit;
        }
        //System.out.println(Integer.toBinaryString(mask));
        return mask;
    }

}

