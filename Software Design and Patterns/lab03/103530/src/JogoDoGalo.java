public class JogoDoGalo implements JGaloInterface{
    private char player;
    private char last_player;
    private int num_plays;
    private char result;
    private char[][] tabuleiro;

    public JogoDoGalo(String[] tipo){
        this.player = (tipo.length > 0 && tipo[0].toUpperCase().equals("O")) ? 'O' : 'X';
        this.last_player = '\0';
        this.result = '\0';
        this.tabuleiro = new char[3][3];
        this.num_plays = 0;
        
    }

    public char getActualPlayer(){
        return this.player;                                     // Devolve o tipo do utilizador
    }

    public boolean setJogada(int lin, int col){
        tabuleiro[lin-1][col-1] = this.player;                      // Coloca o caracter do jogador no espaço correto.
        this.last_player = this.player;
        this.player = (this.last_player == 'O') ? 'X' : 'O';            // Troca os jogadores.
        this.num_plays++;                                            // incrementa o número de jogadas
        return true;                                            // Tudo correu bem
    }

    public boolean isFinished(){
        // Percorre inicialmente as linhas
        for (int i = 0; i < 3; i++){
            if (this.tabuleiro[i][0] != '\0' && this.tabuleiro[i][0] == this.tabuleiro[i][1] && this.tabuleiro[i][1] == this.tabuleiro[i][2]){
                this.result = this.last_player;
            }
        }

        // Percorre de seguida as colunas
        for (int i = 0; i < 3; i++){
            if (this.tabuleiro[0][i] != '\0' && this.tabuleiro[0][i] == this.tabuleiro[1][i] && this.tabuleiro[1][i] == this.tabuleiro[2][i]){
                this.result = this.last_player;
            }
        }

        if (this.tabuleiro[0][0] != '\0' && this.tabuleiro[0][0] == this.tabuleiro[1][1] && this.tabuleiro[1][1] == this.tabuleiro[2][2]){
            this.result = this.last_player;
        }
        if (this.tabuleiro[0][2] != '\0' && this.tabuleiro[0][2] == this.tabuleiro[1][1] && this.tabuleiro[1][1] == this.tabuleiro[2][0]){
            this.result = this.last_player;
        }

        if (this.num_plays == 9){
            this.result = ' ';
        }

        return this.result == '\0' ? false : true;

        

    }

    public char checkResult(){
        return this.result;
    }


}
