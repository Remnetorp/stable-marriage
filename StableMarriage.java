import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class StableMarriage {
    private int nPairs;
    private Map<Integer, LinkedList<Integer>> menPreferences;
    private Map<Integer, LinkedList<Integer>> womenPreferences;

    /**
     * Method readFile() takes in-files in the format where first line contains a
     * single integer N (1 <= N <= 3000), the number
     * of students and companies. The each followig line after of the in-file
     * contains the individuals preference lists. The first occurence
     * of an index will always be the woman and the second occurence will be the
     * man. There are some example data in-files in the repository.
     * 
     */
    private void readFile() {
        Scanner scanner = new Scanner(System.in);
        nPairs = scanner.nextInt();
        menPreferences = new HashMap<>();
        womenPreferences = new HashMap<>();

        for (int i = 0; i < nPairs * 2; i++) {
            int indexOfPerson = scanner.nextInt();
            LinkedList<Integer> individualList = new LinkedList<>();
            for (int j = 0; j < nPairs; j++) {
                int index = scanner.nextInt();
                individualList.add(index);
            }
            if (!womenPreferences.containsKey(indexOfPerson)) {
                womenPreferences.put(indexOfPerson, individualList);
            } else if (!menPreferences.containsKey(indexOfPerson)) {
                menPreferences.put(indexOfPerson, individualList);
            } else {
                break;
            }
        }
        scanner.close();
    }

    /**
     * AlgorithmGS is a method performing the Gale-shapley algorithm, it finds a
     * stable pairing of the men and women provided
     * from the input data file. It takes the preference lists into consideration
     * and find best matching.
     *
     * @return A map containing the stable pairs of the women and the men, the keys
     *         are the women and the values are the men.
     * 
     */
    private Map<Integer, Integer> algorithmGS() {
        LinkedList<Integer> p = new LinkedList<>(menPreferences.keySet());
        Map<Integer, Integer> pairs = new HashMap<>();

        while (!p.isEmpty()) {
            int man = p.poll();
            int woman = menPreferences.get(man).poll();
            if (!pairs.containsKey(woman)) {
                pairs.put(woman, man);
            } else {
                int currentMan = pairs.get(woman);
                LinkedList<Integer> herPreference = womenPreferences.get(woman);
                int indexMan = herPreference.indexOf(man);
                int indexCurrentMan = herPreference.indexOf(currentMan);
                if (indexMan < indexCurrentMan) {
                    pairs.put(woman, man);
                    womenPreferences.get(woman).remove(indexCurrentMan);
                    p.push(currentMan);
                } else {
                    p.push(man);
                    womenPreferences.get(woman).remove(indexMan);
                }
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        StableMarriage test = new StableMarriage();

        long time0 = System.currentTimeMillis();
        test.readFile();
        long time1 = System.currentTimeMillis();
        Map<Integer, Integer> pairs = test.algorithmGS();
        long time2 = System.currentTimeMillis();

        pairs.forEach((key, value) -> {
            System.out.println(value);
        });

        long readTime = (time1 - time0);
        long algorithmTime = (time2 - time1);

        boolean output = false;
        for (String arg : args) {
            if (arg.equals("output")) {
                output = true;
            }
        }
        if (output) {
            test.printInformation(readTime, algorithmTime, pairs);
        }
    }

    /**
     * printInformation method sole reason is to provide extra information in the
     * output to understand the result.
     *
     * @param readTime      The time in milliseconds it took to perform the read
     *                      method.
     * @param algorithmTime The time in milliseconds it took to perform the
     *                      algorithm method.
     * @param pairs         A map containing the stable pairs.
     * 
     */
    public void printInformation(long readTime, long algorithmTime, Map<Integer, Integer> pairs) {
        System.out.println("Amount of pairs with men and women: " + nPairs);
        for (int i = 1; i < (1 + nPairs / 2); i++) {
            System.out.println("Man " + i + "has preferences: " + menPreferences.get(i));
            System.out.println("Woman " + i + "has preferences: " + womenPreferences.get(i));
        }
        pairs.forEach((key, value) -> {
            System.out.println("Woman " + key + " is paired with man " + value);
        });
        System.out.println("Milliseconds reading input: " + readTime);
        System.out.println("Milliseconds performing algorithm: " + algorithmTime);
        System.out.println("Total time in milliseconds: " + (readTime + algorithmTime));
    }
}