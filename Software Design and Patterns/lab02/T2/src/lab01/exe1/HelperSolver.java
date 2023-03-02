package lab01.exe1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HelperSolver {
	
	private ArrayList<int[]> occupiedPos;
	private int puzzleSize;
	
	public HelperSolver() {
		this.occupiedPos = new ArrayList<int[]>();
		this.puzzleSize = 0;
	}
	
	public void solveGame(String dataFile, String outputFileName) {
		ArrayList<String> listWordsToFind = new ArrayList<String>();
		ArrayList<String> puzzle = new ArrayList<String>();
		ArrayList<Integer> puzzleWidths = new ArrayList<Integer>();
		int puzzleHeight = 0;
		
		try {
			BufferedReader readFile = new BufferedReader(new FileReader(dataFile));
			String line;
			
			try {
				while((line = readFile.readLine()) != null){
					
					/* Check if line is blank */
					verifyBlank(line);
					
					/* If line is a puzzle line, increment height and save width of each line in arraylist, 
					 * so we can later verify if puzzle is a square */
					if(isPuzzleLine(line)) {
						puzzleHeight++;
						puzzleWidths.add(countWidth(line));
						puzzle.add(line);
					}
					
					/* Else check if line is word list, see if it only has valid words and add them to arraylist of words to find */
					else {
						listWordsToFind.addAll(isWordList(line));
					}
					
				}
				readFile.close();
				
				/* Check if game puzzle is valid */
				verifyPuzzle(puzzleHeight, puzzleWidths);
				
				/* Write solution file*/
				writeSolutionFile(puzzle,listWordsToFind, outputFileName);
				
			} catch (IOException e) {
				System.out.println(e);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	
	/* Write solution file and return success message, or error message if one occurs */
	private void writeSolutionFile(ArrayList<String> puzzle, ArrayList<String> listWordsToFind, String outputFileName) {
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(outputFileName, "UTF-8");
			
			/* Get game puzzle in the form of a 2D Array of Characters */
			Character[][] soup = getSoup(puzzle);
			
			/* Filter word list and solve the game*/
			for (String word : manageSmallerWords(listWordsToFind)) {
				String[] solution = findWord(soup, word);
				
				/* Write solution for each word in the file */
	            writer.printf("%-20s %-5d %-8s %s \n", word, word.length(), solution[0], solution[1]);
	        }
			
			writer.printf("\n");
			
			/* Write solved game puzzle in the file */
			for(Character[] chr : getSolvedSoup(soup)) {
				writer.printf(Arrays.toString(chr).replace(",", "").replace("[", "").replace("]", "\n"));
			}
			writer.close();
			occupiedPos.clear();
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
			
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		}
		System.out.println("Program ended --> Solution has been succesfully written in the file: " + outputFileName + 
				"! \n-----------------------------------------------------------------------------------------------");
	}

	/* Search word in puzzle and if it exists, return its starting coordinates and direction*/
	private String[] findWord(Character[][] soup, String wordToFind){
        String dir = "NotFound";
        
        for(int i = 0 ; i < puzzleSize ; i++){
        	
            for(int j = 0 ; j < puzzleSize ; j++){
            	
                if (soup[i][j] == wordToFind.toUpperCase().charAt(0)) {
                	
                    dir = searchPuzzle(soup, wordToFind.toUpperCase(), i, j);
                    
                    if(dir != "NotFound") {
                        return new String[]{++i + "," + ++j, dir};
                    }
                }
            }
        }
        System.out.printf("ERROR: " +  wordToFind + " was not found!");
        System.exit(1);
        return null;
    }
	
	/* Search word in game puzzle and return the direction where it begins*/
	private String searchPuzzle(Character[][] puzzle, String wordToFind, int startY, int startX) {
		
		/* Create Map with every direction and array with values (y,x) that correspond to said direction movement*/
		Map<String, Integer[]> possibleDirs = new HashMap<String, Integer[]>();
		possibleDirs.put("Up", new Integer[] {-1, 0});
		possibleDirs.put("Down", new Integer[] {1, 0});
		possibleDirs.put("Right", new Integer[] {0, 1});
		possibleDirs.put("Left", new Integer[] {0, -1});
		possibleDirs.put("DownRight", new Integer[] {1, 1});
		possibleDirs.put("DownLeft", new Integer[] {1, -1});
		possibleDirs.put("UpLeft", new Integer[] {-1, -1});
		possibleDirs.put("UpRight", new Integer[] {-1, 1});
		
		ArrayList<int[]> temp = new ArrayList<int[]>();
		
		/* Starting from the coordinates where the first character of the word was found, try every direction until all the
		 * characters of the word are found. Save coordinates of each character found in temp array as the search continues, 
		 * and if all characters are found, add temp array elements into class atribute occupiedPos. 
		 * If failed search attempt, clear the temp array*/
		for (Entry<String, Integer[]> entry : possibleDirs.entrySet()) {
		    String dir = entry.getKey();
		    Integer[] values = entry.getValue();
		    
		    int cnt;
		    int nextX = startX + values[1];
			int nextY = startY + values[0];
			
			temp.clear();
			
			int[]initPos = {startY, startX};
			temp.add(initPos);
			
			for (cnt = 1; cnt < wordToFind.length(); cnt++){
				 if (nextX >= puzzle.length || nextX < 0 || nextY >= puzzle.length || nextY < 0) {
					 break;
	             }
	
				 else if (puzzle[nextY][nextX] != wordToFind.charAt(cnt)) {
	            	 break;
	             } 
				 
				 else {
		             int[]notFree = {nextY, nextX};
		             temp.add(notFree);
		             
		             nextY += values[0];
		             nextX += values[1];
				 }
			}
			
            if (cnt == wordToFind.length()) {
            	occupiedPos.addAll(temp);
            	return dir;
            }
		}
		return "NotFound";
	}
	

	/* Return solved game puzzle */
	private Character[][] getSolvedSoup(Character[][] initSoup){
		
		/* Create 2D Character array filled with '.' */
		Character[][] returnPuzzle = new Character[initSoup.length][initSoup.length];
		Arrays.stream(returnPuzzle).forEach(a -> Arrays.fill(a, '.'));

		/* Iterate over newly created array, the positions of this array that 
		 match the positions in the arraylist of occuppied positions will be filled with the corresponding character 
		 that was in that position in the initial game puzzle*/
		for(int i = 0; i < returnPuzzle.length; i++) {
			for(int j = 0; j < returnPuzzle.length; j++) {
				for(int[] occupiedPos : occupiedPos) {
					int[] currentPos = {i,j};
					
					if(Arrays.equals(occupiedPos, currentPos)) {
						returnPuzzle[i][j] = initSoup[i][j];
					}
				}
	        }
		}
		return returnPuzzle;
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
	
	/* Receive arraylist with puzzle lines and return initial game puzzle in the form of 2D Character array*/
	private Character[][] getSoup(ArrayList<String> arrList){
		 Character[][] soup = new Character[puzzleSize][puzzleSize];
		 
		 for(int i = 0; i < puzzleSize; i++) {
			 for(int j = 0; j < puzzleSize; j++) {
				 soup[i][j] = arrList.get(i).charAt(j);
			 }
		 }
		 return soup;
	}
	
	/* Check if line is blank */
	private void verifyBlank(String str) {
		if (str.trim().isEmpty()) {
			System.out.println("ERROR: File contains a blank line!");
			System.exit(1);
		}
		return;
	}
	
	/* Check if line is puzzle line (all upper and all alphabetic characters) */
	private boolean isPuzzleLine(String str) {
		if(isAllUpper(str) && isAllAlphabetic(str)) {
			return true;
		}
		return false;
	}
	
	/* Check if puzzle is valid (square and max size 40x40) */
	private void verifyPuzzle(int puzzleHeight, ArrayList<Integer> puzzleWidths) {
		
		/* Check if all collected puzzle lines have the same width*/
		boolean allEqualWidth = new HashSet<Integer>(puzzleWidths).size() <= 1;
		
		if(!allEqualWidth) {
			System.out.println("ERROR: Puzzle is not a square!");
			System.exit(1);
		}
		
		else if(puzzleHeight > 40 || puzzleWidths.get(0) > 40) {
			System.out.println("ERROR: Puzzle is not a valid size! Max size is 40x40.");
			System.exit(1);
		}
		else if(puzzleHeight != puzzleWidths.get(0)){
			System.out.println("ERROR: Puzzle is not a square!");
			System.exit(1);
		}
		else {
			puzzleSize = puzzleHeight;
			return;
		}
	}
	
	/* Check if line only has valid words and if yes, return list containing those words */
	private ArrayList<String> isWordList(String line){
		ArrayList<String> keyWords = new ArrayList<String>();

		String[] lineWords = line.split(";|,|\\s+"); // Words can be separated by ";" "," or space
		for (String c : lineWords) {
			if(!isAllUpper(c) && isAllAlphabetic(c)) { // Words are made of alphabetic characters that are all lowercase or mixed
				keyWords.add(c);
			}
			else {
				System.out.println("ERROR: Check for invalid words, non-alphabetic characters or invalid spaces!");
				System.exit(1);
			}
		}
		return keyWords;
	}
	
	/* Return width of a line */
	private int countWidth(String str) {
		int count = 0;
		for(int i = 0; i < str.length(); i++) {    
            if(str.charAt(i) != ' ')    
                count++;    
        }  
		return count;
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
}


