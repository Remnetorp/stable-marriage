import java.util.Scanner;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;


public class StableMarriage{
    private int nPairs;
    private ArrayList<ArrayList<Integer>> menPreferences;
    private ArrayList<ArrayList<Integer>> womenPreferences;

    /* 
     * Method readFile() takes in-files in the format where first line contains a single integer N (1 <= N <= 3000), the number
     * of students and companies. The each followig line after of the in-file contains the individuals preference lists.
     * I will add some example data in-files in the repository. 
     */
    private void readFile(){
        Scanner scanner = new Scanner(System.in);
        nPairs = scanner.nextInt();
        
        scanner.nextLine();
        String input;
        Map<Integer,ArrayList<Integer>> menPreferences = new HashMap<>();
        Map<Integer,ArrayList<Integer>> womenPreferences = new HashMap<>();

        while(scanner.hasNext()){
            input = scanner.nextLine();
            String[] indexes = input.split(" ");

            int indexOfPerson = Integer.parseInt(indexes[0]);

            ArrayList<Integer> individualList = new ArrayList<>();
            for(int j=1; j < indexes.length; j++){
                individualList.add(Integer.parseInt(indexes[j]));
            }
            if(!womenPreferences.containsKey(indexOfPerson)){
                womenPreferences.put(indexOfPerson,individualList);
            }else{
                menPreferences.put(indexOfPerson,individualList);
            }
        }

        // Print the preferences to make sure it works
        System.out.println("\nWomen:");
        menPreferences.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("\nMen: ");
        womenPreferences.forEach((key, value) -> System.out.println(key + ": " + value));
        scanner.close();
    }


     /**
     * Started implementing the stable pairing by trying to follow the Gale-Shapley algorithm. 
     * Currently it gives a result, but seems to be incorrect.
     *
     */
    private void algorithmGS(){
        Map<Integer,Integer> pairs = new HashMap<>();
        Map<Integer, ArrayList<Integer>> women = new HashMap<>(); 
        Map<Integer, ArrayList<Integer>> men = new HashMap<>(); 
        ArrayList<Integer> p = new ArrayList<>();

        for (int i = 1; i<menPreferences.size()+1; i++){
            men.put(i, menPreferences.get(i-1));
            women.put(i, womenPreferences.get(i-1));
            p.add(i);
        }
    

        while(!p.isEmpty()){
            int man = p.remove(0);
            ArrayList<Integer> womansPrefered = men.get(man);
            int firstWoman = womansPrefered.remove(0);
            if (pairs.containsKey(firstWoman)){
                int currentMan = pairs.get(firstWoman);
                ArrayList<Integer> herPreference = women.get(firstWoman);
                if(herPreference.indexOf(man) < currentMan){
                    pairs.put(firstWoman, man);
                    p.add(currentMan);
                }else{
                    men.put(man, womansPrefered);
                    p.add(man);
                }
            }else{
                pairs.put(firstWoman, man);
                men.put(man, womansPrefered);
            }
        }
        pairs.forEach((key,value) -> {
            System.out.println("Man: " + value + ", Woman: " + key);
        });
    }


    public static void main(String[] args){
        StableMarriage test = new StableMarriage();
        test.readFile();
        //test.algorithmGS();
    }

}