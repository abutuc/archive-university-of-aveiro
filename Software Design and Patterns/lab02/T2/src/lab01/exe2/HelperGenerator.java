package lab01.exe2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class HelperGenerator {
	
	private String wordFileName;
    private int puzzleSize;
    private String outputFileName;
    private ArrayList<String> listWordsToAdd;

    public HelperGenerator(String wordFileName, int puzzleSize, String outputFileName) {
		this.wordFileName = wordFileName;
		
		verifySize(puzzleSize);
		
		this.puzzleSize = puzzleSize;
		this.outputFileName = outputFileName;
		this.listWordsToAdd = new ArrayList<String>();
	}
    
    public void generateGame() {
    	
		try {
			BufferedReader readFile = new BufferedReader(new FileReader(wordFileName));
			String line;
			
			try {
				while((line = readFile.readLine()) != null){
					
					/* Check if line is blank */
					verifyBlank(line);
					
					/* Get words to add to puzzle*/
					getWordsToAdd(line);
				}
				readFile.close();
				
				/* Write solution file*/
				writeSolutionFile();

			} catch (IOException e) {
				System.out.println(e);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
    
    /* Write solution file and return success message, or error message if one occurs */
	private void writeSolutionFile() {
		PrintWriter writer;
		
		try {
			writer = new PrintWriter(outputFileName, "UTF-8");
			
			/* Write game puzzle in file*/
			for(Character[] chr : createGamePuzzle()) {
				writer.printf((Arrays.toString(chr).replace(",", "").replace("[", "").replace("]", "\n")).replace(" ", ""));
			}
			
			/* Write words to find in file, separated by commas */
			for(String wrd : listWordsToAdd) {
				if (wrd.contentEquals(listWordsToAdd.get(listWordsToAdd.size() - 1))){
					writer.printf(wrd);
				}
				else {
					writer.printf(wrd + ",");
				}
			}
			
			writer.close();
			listWordsToAdd.clear();

		} catch (FileNotFoundException e) {
			System.out.println(e);
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
		System.out.println("Program ended --> Game Puzzle has been succesfully written in the file: " + outputFileName + 
				"! \n-----------------------------------------------------------------------------------------------");
	}

	/* Create final game puzzle*/
	public Character[][] createGamePuzzle(){
		 
		/*Check if all words fit the puzzle*/
		wordsFitPuzzle();
		
		/* Generate puzzle with random characters */
		Character[][] soup = new Character[puzzleSize][puzzleSize];
		
        for(int i = 0 ; i < puzzleSize ; i++)
            for(int j = 0 ; j < puzzleSize ; j++)
            	soup[i][j] = generateRandomChar();

        ArrayList<Integer[]> occupiedPos = new ArrayList<>();
       
        
        /* Insert each character of each word in the puzzle*/
        try{
            for(String word : listWordsToAdd){
                ArrayList<Integer[]> temp = insertWordInPuzzle(soup, word, occupiedPos);

                for(int i = 0; i < temp.size(); i++) {
                    soup[temp.get(i)[0]][temp.get(i)[1]] = word.toUpperCase().charAt(i);
                }
                occupiedPos.addAll(temp);
            }
        }
        catch(NullPointerException e ){
        	System.out.println("ERROR: Too many tries, word list may be too extensive for this puzzle!");
        	System.exit(1);
        }
        
        return soup;
    }
	
	/* Get insert positions of each character of the word. Make size*size attempts at placing word in puzzle,
	 * from a random direction and random starting position */
    private  ArrayList<Integer[]> insertWordInPuzzle(Character[][]puzzle, String word, ArrayList<Integer[]> occupiedPositions){
        int numTries=0;
        Random randomStartPos = new Random();

        while(numTries < (puzzleSize*puzzleSize)){
        	Direction currentDirection = Direction.getRandomDirection();
            boolean failedAttempt = false;
         
            int x = randomStartPos.nextInt(puzzleSize);
            int y = randomStartPos.nextInt(puzzleSize);

            ArrayList<Integer[]> tempOccupiedPos = new ArrayList<>();
             
            for(int i = 0 ; i < word.length() ; i++){
                Integer[] currentPos = {y, x};

                char currentChar = word.charAt(i);
                if(currentPos[0] >= puzzleSize || currentPos[1]>=puzzleSize || currentPos[0] < 0 || currentPos[1] < 0) {
                	failedAttempt = true;
                    break;
                }

                for(Integer[] pos : occupiedPositions)
                    if(pos[0]==currentPos[0] && pos[1]==currentPos[1])
                        if(currentChar!=puzzle[currentPos[0]][currentPos[1]]){
                        	failedAttempt = true;
                            break;
                        }

                tempOccupiedPos.add(currentPos);
                
                if(currentDirection==Direction.UP) {
                	y--;
                }
                if(currentDirection==Direction.DOWN) {
                	y++;
                }
                if(currentDirection==Direction.RIGHT) {
                	x++;
                }
                if(currentDirection==Direction.LEFT) {
                	x--;
                }
                if(currentDirection==Direction.DOWNRIGHT) {
                	x++;
	                y++;
                }
                if(currentDirection==Direction.DOWNLEFT) {
                	x--;
                    y++;
                }
                if(currentDirection==Direction.UPLEFT) {
                	x--;
                    y--;
                }
                if(currentDirection==Direction.UPRIGHT) {
                	x++;
                    y--;
                }
            }
            if(failedAttempt){
            	numTries++;
                continue;
            }
            return tempOccupiedPos;
        }
        return null;
    }	
	
	/* Check if all words can fit the puzzle with the given size*/
	private void wordsFitPuzzle() {
		String largestWord = listWordsToAdd.get(0);

        for(int i = 1 ; i < listWordsToAdd.size() ; i++){
            if(largestWord.length() < listWordsToAdd.get(i).length()) {
                largestWord = listWordsToAdd.get(i);
            }
        }
        if (largestWord.length() > puzzleSize){
        	System.out.println("ERROR: File contains words too big to fit a puzzle with the given size!");
			System.exit(1);
        }
	}
	    
	/* Get list of words to add to the puzzle*/
	private void getWordsToAdd(String line){
		ArrayList<String> keyWords = new ArrayList<String>();

		String[] lineWords = line.split(";|,|\\s+"); // Words can be separated by ";" "," or space
		for (String c : lineWords) {
			if(!isAllUpper(c) && isAllAlphabetic(c)) { // Words are made of alphabetic characters that are all lowercase or mixed
				keyWords.add(c);
			}
		}
		listWordsToAdd.addAll(manageSmallerWords(keyWords));
	}
	
	/* Check if there are repeated words or smaller words inside bigger words. If there are, 
	 * remove repeated words, ignore the smaller words and return the new list of unique non redundant words to search */
	private ArrayList<String> manageSmallerWords(ArrayList<String> wordList) {

		/* Convert to set to remove repeated words and then convert again to arraylist */
		Set<String> removeRepeated = new LinkedHashSet<String>(wordList);
		ArrayList<String> removeRedundant = new ArrayList<String>(removeRepeated);
		
		
		ArrayList<String> filteredWords = new ArrayList<String>();
		for(String str : removeRedundant){
			filteredWords.add(str);
		}
		
		/* Remove any smaller word inside bigger word, converted to lower case in order to be case insensitive */
		for (int i = 0; i < filteredWords.size(); i++) {
           for (int j = i + 1; j < removeRedundant.size(); j++) {
               if (removeRedundant.get(i).toLowerCase().contains(removeRedundant.get(j).toLowerCase())){
            	   filteredWords.remove(j);
               }
               if (removeRedundant.get(j).toLowerCase().contains(removeRedundant.get(i).toLowerCase())) {
            	   filteredWords.remove(i);
               }
           }
       }
		return filteredWords;
	}
	
	/* Check if puzzle size is valid */
    private void verifySize(int size) {
		if(size > 40) {
			System.out.println("ERROR: Size too large, puzzle cannot be bigger than 40x40!");
			System.exit(1);
		}
	}
	
	/* Check if line is blank */
	private void verifyBlank(String str) {
		if (str.trim().isEmpty()) {
			System.out.println("ERROR: File contains a blank line!");
			System.exit(1);
		}
		return;
	}
	
	/* Check if all string characters are uppercase */
	private boolean isAllUpper(String str){
	    for (int i = 0; i < str.length(); i++){
	        if (Character.isLowerCase(str.charAt(i))){
	            return false;
	        }
	    }
	    return true;
	}

	/* Check if all string characters are alphabetic */
	private boolean isAllAlphabetic(String str) {
		if (!str.matches("^[a-zA-Z]*$") || str.matches(".*([ \t]).*")) {
			return false;
		}
		return true;
	}
	
	/* Generate random character*/
	private char generateRandomChar() {
		Random rnd = new Random();
		char chr = (char)(rnd.nextInt(26) + 'a');
		return Character.toUpperCase(chr);
	}
}