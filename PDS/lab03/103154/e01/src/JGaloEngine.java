
public class JGaloEngine implements JGaloInterface {

	private char[][] squares;
	private char previousPlayer;
	private char actualPlayer;
	private char result;
	private int numPlays;

	public JGaloEngine(String[] args) {

		this.squares = new char[3][3];
		this.previousPlayer = '\0';
		this.actualPlayer = (args.length > 0 && args[0].toUpperCase().equals("O")) ? 'O' : 'X';
		this.result = '\0';
		this.numPlays = 0;

	}

	@Override
	public char getActualPlayer() {
		return this.actualPlayer;
	}

	@Override
	public boolean setJogada(int lin, int col) {
		this.squares[lin - 1][col - 1] = this.previousPlayer = this.actualPlayer;
		this.actualPlayer = (this.previousPlayer == 'X') ? 'O' : 'X';
		this.numPlays++;
		return true;
	}

	@Override
	public boolean isFinished() {

		// Verificar se houve algum vencedor
		boolean winner = false;
		// Horizontal
		for (int i = 0; i < 3; i++)
			winner |= this.squares[i][0] != '\0' && this.squares[i][0] == this.squares[i][1] && this.squares[i][1] == this.squares[i][2];
		// Vertical
		for (int j = 0; j < 3; j++)
			winner |= this.squares[0][j] != '\0' && this.squares[0][j] == this.squares[1][j] && this.squares[1][j] == this.squares[2][j];
		// Diagonal
		winner |= this.squares[0][0] != '\0' && this.squares[0][0] == this.squares[1][1] && this.squares[1][1] == this.squares[2][2];
		winner |= this.squares[2][0] != '\0' && this.squares[2][0] == this.squares[1][1] && this.squares[1][1] == this.squares[0][2];
		
		// Verificar se houve empate
		boolean tie = (winner) ? false : this.numPlays == 9;
		
		this.result = (winner) ? this.previousPlayer : (tie) ? ' ' : '\0'; 
		
		return winner || tie;
	}

	@Override
	public char checkResult() {
		return this.result;
	}

}
