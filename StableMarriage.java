import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class StableMarriage{
    private int nPairs;
    private Map<Integer,ArrayList<Integer>> menPreferences;
    private Map<Integer,ArrayList<Integer>> womenPreferences;

    /**
     * Method readFile() takes in-files in the format where first line contains a single integer N (1 <= N <= 3000), the number
     * of students and companies. The each followig line after of the in-file contains the individuals preference lists.
     * I will add some example data in-files in the repository. 
     * 
     * @param output Decideds if extra information should be printed out or not in the terminal.
     * 
     */
    private void readFile(boolean output){
        Scanner scanner = new Scanner(System.in);
        nPairs = scanner.nextInt();
        String input;
        menPreferences = new HashMap<>();
        womenPreferences = new HashMap<>();

        for(int i=0; i < nPairs*2; i++){
            int indexOfPerson = scanner.nextInt();

            ArrayList<Integer> individualList = new ArrayList<>();
            for(int j=0; j < nPairs; j++){
                int index = scanner.nextInt();
                if(!individualList.contains(index)){
                    individualList.add(index);
                }
            }
            if(!womenPreferences.containsKey(indexOfPerson)){
                womenPreferences.put(indexOfPerson,individualList);
            }else if (!menPreferences.containsKey(indexOfPerson)) {
                menPreferences.put(indexOfPerson,individualList);
            }else{
                break;
            }
        } 

        // Print the preferences to make sure it works, call it by sending "output" in the argument when running from terminal.
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
     * @param output Decideds if extra information should be printed out or not in the terminal.
     * 
     */
    private void algorithmGS(boolean output){
        ArrayList<Integer> p = new ArrayList<>(menPreferences.keySet());
        Map<Integer,Integer> pairs = new HashMap<>();

        
        //Time compexity n-men in p
        while(!p.isEmpty()){
                    int man = p.remove(0);
                    ArrayList<Integer> womansPrefered = menPreferences.get(man);

                    //Time compexity n-women in preferences => in total n^2.
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

        //prints out extra information if "output" was sent in the arg when program runned.
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