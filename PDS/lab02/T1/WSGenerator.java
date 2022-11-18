import java.io.*;
import java.util.*;
public class WSGenerator {
    public static File inputFile; // Input File
    public static FileWriter outputFile; // Output File
    public static Scanner sc; // Scanner to read lines in the input file
    // Alphabet
    public static String [] abc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","w","y","z"};
    public static int outputctr; // Variable responsable for controlling if a output file was passed
    public ArrayList<ArrayList<String>> grid; // NxN grid that will be generated
    public ArrayList<String> words; // Array list of words that the grid will contain
    public ArrayList<ArrayList<String>> wordsPlaced = new ArrayList<>(); // ArrayList listing all words that are already placed
    public Random rand = new Random(); // Variable class Random to get random values used in the program
    public int size; // Size of the grid
    // ---------------------------------------- //
    public static void main(String[] args) {
        // Variable to save size of grid value
        int size;
        try {
            // Get input file contaning the words to place on the grid
            String inputFileName = args[Arrays.asList(args).indexOf("-i")+1];
            inputFile = new File(inputFileName);
            sc = new Scanner(inputFile);
            // Get size of the grid the program will generate
            size = Integer.parseInt(args[Arrays.asList(args).indexOf("-s")+1]);
            // Get outfile if option -o was given in program start
            if (args.length > 4) {
                outputctr = 1;
                File f = new File(args[Arrays.asList(args).indexOf("-o")+1]);
                f.createNewFile();
                outputFile = new FileWriter(f); // open file writer
            }
            // Get a ArrayList<String> of all the words in the input file
            ArrayList<String> keyWords = getkeyWords();
            // Generate the grid with the pretended size contaning the words listed in the input file
            new WSGenerator(size, keyWords);
            // Closing actions
            sc.close();
            outputFile.close(); // This one may do a exception if -o was not given in program start
        } catch (Exception e) {
            // Check if this was a bad call error or the output file closing
            if(args.length > 4) {
                System.out.println("Usage: java WSGenerator -i wordlist_1.txt -s 12 '-o sdl_01.txt'");
            }
            // Shutdown the system
            System.exit(0);
        }
    }
    // Function containing all the actions which will result on the pretended grid generation
    public WSGenerator(int size, ArrayList<String> words){
        this.size = size; // Set size
        this.cleangrid(); // Generate a new grid
        this.words = words; // Set words
        this.setGame(); // Put the words on the generated grid
        this.getABC(); // Fill the gaps with new random letters (Comment this line to see the words being placed)
        this.printGrid(); // Print the pretended grid
    }
    // Fill the gaps with new random letters
    public void getABC(){
        for(int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++) {
                if (this.grid.get(i).get(j).equals("-")) {
                    int ind = rand.nextInt(abc.length);
                    this.grid.get(i).set(j, abc[ind].toUpperCase());
                }
            }
        }
    }
    // Print the pretended grid
    public void printGrid(){
        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                // outputctr is the variable responsable for controlling if a output file was passed
                // If it was not passed (0), the results are pretended in the terminal
                // If it was (1), then we save the results in a file
                if(outputctr == 0) {
                    System.out.print(" " + this.grid.get(i).get(j));
                } else{
                    // try_catch, because to the compiler, file may or not be initialized
                    try {
                        outputFile.write(grid.get(i).get(j) + " ");
                    } catch (IOException e) {}
                }
            }
            if(outputctr == 0){
                System.out.println("");
            } else {
                try {
                    outputFile.write("\n");
                } catch (IOException e) {}
            } 
        }
    }
    // Generate a new grid
    public void cleangrid(){
        this.grid = new ArrayList<>(); // Create/clean new grid
        // Fill all entries of the new grid with "-", so it's easier to locate them
        for(int i = 0; i < this.size; i++){
            ArrayList<String> line = new ArrayList<>();
            for(int j = 0; j < this.size; j++){
                line.add("-");
            }
            this.grid.add(line);
        }
    }
    public void setGame(){
        // Pick a word in the words list
        int currentWordIndex = 0; // Index to determine which word the porgram is working with
        while(wordsPlaced.size() < words.size()){ 
            String currentWord = words.get(currentWordIndex); // Pick a word in the words list
            if (currentWord.length() > this.size){ // Check if the word's size is greater than grid's size
                System.out.println("The grid size can't fit the words in the list. GRID TOO SMALL!");
                System.exit(1);
            }
            // Get all directions in a ArrayList
            ArrayList<ArrayList<Integer>> directions = new ArrayList<>();
            for (int x = -1; x < 2; x++){
                for (int y = -1; y < 2; y++){
                    ArrayList<Integer> d = new ArrayList<>();
                    d.add(x); d.add(y);
                    directions.add(d);
                }
            }
            directions.remove(4); // Remove direction [0,0]
            while (directions.size()>0){
                int directionIndex = rand.nextInt(directions.size()); // Pick a random direction
                // Try to place the word in the grid using the picked direction
                if (placeWord(currentWord.split(""), directions.get(directionIndex).get(0), directions.get(directionIndex).get(1))) {
                    currentWordIndex += 1;
                    break;
                } else {
                    directions.remove(directionIndex);
                }
            }
            if(directions.size() == 0){
                currentWordIndex = 0;
                this.cleangrid();
                this.wordsPlaced.removeAll(wordsPlaced);
            }
        }
    }
    // Try to place the word in the grid using the picked direction
    public boolean placeWord(String[] currentWord, int xmove, int ymove){
        // Get all positions in the NxN grid 
        ArrayList<ArrayList<Integer>> availablePositions = getAllPositions();
        // While there are available positions remaining
        while(availablePositions.size()>0){
            // Get a random position on the grid
            int tupleIndex = rand.nextInt(availablePositions.size());
            ArrayList<Integer> currentTuple = availablePositions.get(tupleIndex);
            // Make of currentWord string (String)
            String currentWordString = "";
            for (String alb : currentWord) {
                currentWordString += alb;
            }
            // Check if the random position is valid to insert the word
            if (checkPlacement(currentWord, currentWordString, currentTuple, xmove, ymove)){ 
                // Write the work on the grid
                insertNewWord(currentWord, currentTuple.get(0), currentTuple.get(1), xmove, ymove);
                // Create the array list with the informations need to descrive every word's placing
                // And add that array on the list containing the words placed
                ArrayList<String> FoundWord = new ArrayList<>();
                FoundWord.add(currentWordString);
                FoundWord.add(Integer.toString(currentTuple.get(0)));
                FoundWord.add(Integer.toString(currentTuple.get(1)));
                FoundWord.add(Integer.toString(xmove));
                FoundWord.add(Integer.toString(ymove));
                this.wordsPlaced.add(FoundWord);
                return true;
            }
            availablePositions.remove(tupleIndex); // If that position is invalid, then remove it from the available positions
        }
        return false; // Every position using that direction is invalid
    }
    // Check if the random position is valid to insert the word
    public boolean checkPlacement(String[] currentWord, String currentWordString, ArrayList<Integer> currentTuple, int xmove, int ymove){
        for (ArrayList<String> wordPlaced : wordsPlaced){
            // Condictions to check if a word is located inside another word
            if( wordPlaced.get(0).contains(currentWordString) &&
                (wordPlaced.get(1).equals(Integer.toString(currentTuple.get(0))) || wordPlaced.get(2).equals(Integer.toString(currentTuple.get(1)))) &&
                wordPlaced.get(3).equals(Integer.toString(xmove)) && wordPlaced.get(4).equals(Integer.toString(ymove))){
                return false;
            }
        }
        // Check if that position is valid
        for(int k = 0; k < currentWord.length; k++){
            int xSearch = currentTuple.get(1) + k*xmove;
            int ySearch = currentTuple.get(0) + k*ymove;
            if(xSearch < 0 || xSearch >= grid.size()){ return false; } // Check if every x is in bounds
            else if(ySearch < 0 || ySearch >= grid.size()){ return false; } // Check if every y is in bounds
            else if(!( // Check every xy is available for placing
                    (grid.get(ySearch).get(xSearch).equals("-")) ||
                    (grid.get(ySearch).get(xSearch).equals(currentWord[k]))
                    )) { return false; }
        }
        return true; // If any of "return false"s was detected
    }
    // Write the work on the grid
    public void insertNewWord(String[] currentWord, int xi, int yi, int xmove, int ymove) {
        for(int k = 0; k < currentWord.length; k++){
            int xNew = yi + k*xmove;
            int yNew = xi + k*ymove;
            this.grid.get(yNew).set(xNew, currentWord[k]);
        }
    }
    // Get all positions in the NxN grid on a N^N sized array 
    public ArrayList<ArrayList<Integer>> getAllPositions(){
        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
        for(int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){
                ArrayList<Integer> tuple = new ArrayList<>();
                tuple.add(i); tuple.add(j);
                positions.add(tuple);
            }
        }
        return positions;
    }
    // Get a ArrayList<String> of all the words in the input file
    public static ArrayList<String> getkeyWords(){
        ArrayList<String> keyWords = new ArrayList<>(); // New array of strings
        while(sc.hasNext()){
            String[] inputSplited = sc.nextLine().split("[^a-zA-Z]"); // Create an array o strings from each line, which
            for (String word : inputSplited) {                        // are separated by non-alphabetic characters
                word = word.toUpperCase(); 
                keyWords.add(word);
            }
        }
        return keyWords;
    }
}