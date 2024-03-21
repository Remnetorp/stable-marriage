import java.util.Scanner;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;


public class StableMarriage{
    private int nPairs;
    private Map<Integer,ArrayList<Integer>> menPreferences;
    private Map<Integer,ArrayList<Integer>> womenPreferences;

    /* 
     * Method readFile() takes in-files in the format where first line contains a single integer N (1 <= N <= 3000), the number
     * of students and companies. The each followig line after of the in-file contains the individuals preference lists.
     * I will add some example data in-files in the repository. 
     */
    private void readFile(boolean output){
        Scanner scanner = new Scanner(System.in);
        nPairs = scanner.nextInt();
        
        scanner.nextLine();
        String input;
        menPreferences = new HashMap<>();
        womenPreferences = new HashMap<>();

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
            }else if (!menPreferences.containsKey(indexOfPerson)) {
                menPreferences.put(indexOfPerson,individualList);
            }else{
                break;
            }
        }

        // Print the preferences to make sure it works
        if(output){
            System.out.println("\nMen:");
            menPreferences.forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println("\nWomen: ");
            womenPreferences.forEach((key, value) -> System.out.println(key + ": " + value));
        }
        scanner.close();
    }


         /**
     * Started implementing the stable pairing by trying to follow the Gale-Shapley algorithm. 
     * Currently it gives a result, but seems to be incorrect.
     *
     */
    private void algorithmGS(boolean output){
        ArrayList<Integer> p = new ArrayList<>(menPreferences.keySet());
        Map<Integer,Integer> pairs = new HashMap<>();

 
        while(!p.isEmpty()){
                    int man = p.remove(0);
                    ArrayList<Integer> womansPrefered = menPreferences.get(man);
                    
                    //int firstWoman = womansPrefered.remove(0);

                    for(int firstWoman : womansPrefered){
                        if (!pairs.containsKey(firstWoman)){
                            pairs.put(firstWoman, man);
                            break;
                        }else{
                            int currentMan = pairs.get(firstWoman);
                            ArrayList<Integer> herPreference = womenPreferences.get(firstWoman);
                            if(herPreference.indexOf(man) < herPreference.indexOf(currentMan)){
                                pairs.put(firstWoman, man);
                                p.add(currentMan);
                                break;
                            }
                        }
                    }
        }
        if (output){
            pairs.forEach((key,value) -> {
                System.out.println("Woman: " + key + ", Man: " + value);
            }); 
        }

        pairs.forEach((key,value) -> {
            System.out.println(value);
        });
    } 


    public static void main(String[] args){
        StableMarriage test = new StableMarriage();
        boolean output = false;
        for(String arg : args){
            if (arg.equals("output")){
                output = true;
            }
        }
        test.readFile(output);
        test.algorithmGS(output);
    }

}