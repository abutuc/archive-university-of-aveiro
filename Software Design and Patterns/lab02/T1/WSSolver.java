import java.io.*;
import java.util.*;
public class WSSolver {
    public static File inputFile; // Input File
    public static FileWriter outputFile; // Output File
    public static Scanner sc; // Scanner to read lines in the input file
    public static ArrayList<ArrayList<String>> solutions = new ArrayList<>(); // Array list to save the words found in the grid
    // ---------------------------------------- //
    public static void main(String[] args) throws IOException{
        inputFile = new File(args[0]); // Get input file contaning the words to place on the grid
        sc = new Scanner(inputFile); // Initialize the scanner
        // Format the name of the output file based on the name of the input file
        String[] inputFileArray = args[0].split("[^0-9]");
        String outputIdentifier = "";
        for (String number : inputFileArray) {
            outputIdentifier += number;
        }
        File f = new File("out" + outputIdentifier + ".txt");
        f.createNewFile(); // Create the new file
        outputFile = new FileWriter(f); // Initialize the FileWriter
        ArrayList<ArrayList<String>> grid = getGrid(); // Create a new grid
        // Get a Maps of all the words in the input file, keys -> letter, values -> words starting with key letter
        HashMap<String, ArrayList<ArrayList<String>>> keyWords = getWords(); 
        for (int row = 0; row < grid.size(); row++) {                       // Our approach to solve this problem, was to check in every
            for (int column = 0; column < grid.size(); column++){           // cell of the grid and see if the letter in it was a key in the HashMap
                String letter = grid.get(row).get(column);                  // If so, the program would try to find one of the words key'd to that letter
                if(keyWords.get(letter)!= null){                            // in a brute force direction search
                    // Search every word, in every possible direction
                    search(row, column, letter, grid, keyWords); 
                }
            }
        }
        // As the name suggest, some of the solutions found may doubled and inside another solutions. Ex: In BANANA, you may find ANA.
        ArrayList<ArrayList<String>> deathRow = new ArrayList<>(); // So we, use the following condictions to remove those.
        for (ArrayList<String> solution : solutions){
            for (ArrayList<String> otherSolution : solutions){
                if((!(otherSolution.get(0).equals(solution.get(0)))) &&
                    otherSolution.get(0).contains(solution.get(0)) &&
                    (solution.get(1).equals(otherSolution.get(1)) || solution.get(2).equals(otherSolution.get(2))) &&
                    solution.get(3).equals(otherSolution.get(3)) && solution.get(4).equals(otherSolution.get(4))){
                    deathRow.add(solution);
                }
            }
        }
        solutions.removeAll(deathRow); // Remove those useless solutions
        printSolutions(); // Print the solution's table
        printGrid(grid); // Print the grid
        // Closes
        sc.close();
        outputFile.close();
    }
    // Get the grid in the first (N-size) lines of the program
    public static ArrayList<ArrayList<String>> getGrid(){
        ArrayList<ArrayList<String>> grid = new ArrayList<>();
        String[] read = sc.nextLine().split("");
        ArrayList<String> input = new ArrayList<>();
        Collections.addAll(input, read);
        grid.add(input);
        int gridSize = input.size();
        for (int i = 0; i < gridSize-1; i++){
            read = sc.nextLine().split("");
            input = new ArrayList<>();
            Collections.addAll(input, read);
            grid.add(input);
        }
        return grid;
    }
    // Scan of the remaining lines and get all words it's gonna look for
    public static HashMap<String, ArrayList<ArrayList<String>>> getWords(){
        String input;
        HashMap<String, ArrayList<ArrayList<String>>> keyWords = new HashMap<>();
        while(sc.hasNext()){
            input = sc.nextLine();
            String[] inputSplited = input.split("[^a-zA-Z]");
            for (String word : inputSplited) {
                word = word.toUpperCase();
                String[] wordSplited = word.split("");
                ArrayList<String> wordArray = new ArrayList<>();
                Collections.addAll(wordArray, wordSplited);
                keyWords.putIfAbsent(wordSplited[0], new ArrayList<>());
                keyWords.get(wordSplited[0]).add(wordArray);
            }
        }
        return keyWords;
    }
    // Search every word, in every possible direction
    public static void search(int row, int column, String letter, ArrayList<ArrayList<String>> grid, HashMap<String, ArrayList<ArrayList<String>>> keyWords){
        ArrayList<ArrayList<String>> possibleWords = keyWords.get(letter);
        for(int i = 0; i < possibleWords.size(); i++){
            // Pick a word
            ArrayList<String> word = possibleWords.get(i);
            for(int width = -1; width < 2; width++){
                // For changes in the X axis
                int x = column+width;
                if(x < 0 || x >= grid.size()){ continue; } // Check if it's valid
                for(int height = -1; height < 2; height++){
                    // For changes in the X axis
                    int y = row+height;
                    if(y < 0 || y >= grid.size()){ continue; } // Check if it's valid
                    if(grid.get(y).get(x).equals(word.get(1))){ // If the second letter of the word matches one on the circle
                        // The program searches in that direction
                        if (searchDirection(row, column, word, width, height, grid) == 1){
                            // The searchDirection returns true if the word is found
                            ArrayList<String> wordData = new ArrayList<>();
                            String wordString = "";
                            for (String l : word) {
                                wordString += l;
                            }
                            wordData.add(wordString);
                            wordData.add(Integer.toString(row));
                            wordData.add(Integer.toString(column));
                            wordData.add(Integer.toString(width));
                            wordData.add(Integer.toString(height));
                            // Had the word to the solutions array list
                            solutions.add(wordData);
                        }
                    }
                }
            }
        }
    }
    // Searches if the word is in the direction
    public static int searchDirection(int row, int column, ArrayList<String> word, int width, int height, ArrayList<ArrayList<String>> grid){
        for(int k = 0; k < word.size(); k++){
            // In a for cicle with the range of the size of the word. Searches if every cell in that direction matches the letter in the word
            int xSearch = column + k*width;
            int ySearch = row + k*height;
            if(xSearch < 0 || xSearch >= grid.size()){ return 0; }
            else if(ySearch < 0 || ySearch >= grid.size()){ return 0; }
            else if(!(grid.get(ySearch).get(xSearch).equals(word.get(k)))) { return 0; }
        }
        // If nothing triggered the returns, than it returns that that direction has the word we are looking for
        return 1;
    }
    // Get the (String) direction using the changes in axis
    public static String getDirection(ArrayList<String> printable){
        String direction = "null";
        if (Integer.parseInt(printable.get(3)) == -1){
            if (Integer.parseInt(printable.get(4)) == -1){
                direction = "UpLeft";
            } else if (Integer.parseInt(printable.get(4)) == -0){
                direction = "Left";
            } else {
                direction = "DownLeft";
            }
        }
        if (Integer.parseInt(printable.get(3)) == 0){
            if (Integer.parseInt(printable.get(4)) == -1){
                direction = "Up";
            } else {
                direction = "Down";
            }
        }
        if (Integer.parseInt(printable.get(3)) == 1){
            if (Integer.parseInt(printable.get(4)) == -1){
                direction = "UpRight";
            } else if (Integer.parseInt(printable.get(4)) == -0){
                direction = "Right";
            } else {
                direction = "DownRight";
            }
        }
        return direction;
    }
    // Print the table containing the solutions found
    public static void printSolutions() throws IOException{
        for(int i = 0; i < solutions.size(); i++){
            ArrayList<String> printable = solutions.get(i);
            String direction = getDirection(printable);
            String position = Integer.toString(Integer.parseInt(printable.get(1))+1)  + "," + Integer.toString(Integer.parseInt(printable.get(2))+1);
            outputFile.write(String.format("%-15s %-4d %-6s %s\n",printable.get(0).toString().toLowerCase(),
                                            printable.get(0).length(),
                                            position,
                                            direction)); 
        }
    }
    public static void printGrid(ArrayList<ArrayList<String>> grid) throws IOException{
        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
        for (ArrayList<String> solution : solutions){
            for(int k = 0; k < solution.get(0).length(); k++){
                int xSearch = Integer.parseInt(solution.get(2)) + k*Integer.parseInt(solution.get(3));
                int ySearch = Integer.parseInt(solution.get(1)) + k*Integer.parseInt(solution.get(4));
                ArrayList<Integer> alb = new ArrayList<>();
                alb.add(ySearch); alb.add(xSearch);
                positions.add(alb);
            }
        }
        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.size(); c++) {
                ArrayList<Integer> ariana =  new ArrayList<>();
                ariana.add(r); ariana.add(c);
                if (!(positions.contains(ariana))){
                    grid.get(r).set(c, ".");
                }
            }
        }
        outputFile.write("\n");
        for(int i = 0; i < grid.size(); i++){
            for(int j = 0; j < grid.size(); j++){
                outputFile.write(" " + grid.get(i).get(j));
            }
            outputFile.write("\n");
        }
    }
}