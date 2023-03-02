package lab03.exe01;

public class JogoGaloPlayer implements JGaloInterface {
	
	private char player1;
	private char player2;
	private char[][] grid;
	private int numMovesPlayed;
	private char currentPlayer;

	public JogoGaloPlayer(String[] args) {
		
		/* If no args are passed, by default the 1st player is 'X' 
		 * If arg[0] is a valid player ("X" or "O"), define corresponding char and start game*/
		if (args.length < 1 || args[0].equalsIgnoreCase("X")) { 
			this.player1 = 'X';
			this.player2 = 'O';
		}
		else if (args[0].equalsIgnoreCase("O")){
			this.player1 = 'O';
			this.player2 = 'X';
		}
		
		else {
			System.out.print("ERRO DE USO! \nExecute a aplicação na forma: java JGalo [X/O]");
			System.exit(1);
		}
		
		this.grid = new char[3][3];
		this.numMovesPlayed = 0;
	}

	@Override
	public char getActualPlayer() {
		if(numMovesPlayed % 2 == 0) {
			currentPlayer = player1;
			return player1; 
		}
		else {
			currentPlayer = player2;
			return player2;
		}
	}

	@Override
	public boolean setJogada(int lin, int col) {
		lin--;
		col--;
		
		/* If the position is inside the board and is still free, make the play*/
		if (lin >=  0 && lin < 3 && col >=0 && col < 3 && grid[lin][col] != 'X' && grid[lin][col] != 'O') {
            grid[lin][col] = currentPlayer;
    		numMovesPlayed++;
            return true;
        } 
		else {
            return false;
        }
	}

	@Override
	public boolean isFinished() {
		
		/* Game is finished if there is a winner or all the positions in the board are occupied */
		if((numMovesPlayed == 9 && checkResult() == ' ')  || checkResult() == 'X' || checkResult() == 'O') {
			return true;
		}
		else {
			return false;	
		}
	}

	@Override
	public char checkResult() {
		
		/* Run through grid */
		for(int i = 0; i < 3; i++) { 

			 /* Search columns */
			if(grid[i][0] == currentPlayer && grid[i][1] == currentPlayer && grid[i][2] == currentPlayer) {
				return currentPlayer;
			}

			/* Search lines */
			if(grid[0][i] == currentPlayer && grid[1][i] == currentPlayer && grid[2][i] == currentPlayer) { 
				return currentPlayer;
			}
		}
		
		/* Search DownRight and UpLeft */
		if (grid[0][0] == currentPlayer && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
			return currentPlayer;
		}
		 
		/* Search DownLeft and UpRight */
		if (grid[0][2] == currentPlayer && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
			return currentPlayer;
		}
		
		return ' '; 
	}
}
