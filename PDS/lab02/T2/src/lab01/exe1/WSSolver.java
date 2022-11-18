package lab01.exe1;

public class WSSolver{
	
	/* Program accepts 1 argument which is the name of the file containing the game data */
	public static void main(String args[]) {
		
		HelperSolver solver = new HelperSolver();
		
		String outputFileName = "solvedWordPuzzle.txt"; 
		
		solver.solveGame(args[0], outputFileName); 
		
	}
}


