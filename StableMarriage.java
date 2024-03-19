import java.util.Scanner;

import java.util.ArrayList;

public class StableMarriage{
    private int nPairs;
    private ArrayList<ArrayList<Integer>> preferences;

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
        preferences = new ArrayList<>();

        while(scanner.hasNext()){
            input = scanner.nextLine();
            String[] indexes = input.split(" ");
            
            ArrayList<Integer> individualList = new ArrayList<>();
            for(int j=0; j < indexes.length; j++){
                individualList.add(Integer.parseInt(indexes[j]));
            }
            preferences.add(individualList);
        }

        // Print the preferences to make sure it works
        for (ArrayList<Integer> preferenceList : preferences) {
            System.out.println(preferenceList);
        }
        scanner.close();
    }

    public static void main(String[] args){
        StableMarriage test = new StableMarriage();
        test.readFile();
    }

}