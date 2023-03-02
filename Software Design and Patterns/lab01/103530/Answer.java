public class Answer {
    // Esta classe serve como container aos dados da resposta, nomeadamente:
    // - palavra
    // - Coordenada no eixo dos x (ou seja o número da linha)
    // - Coordenada no eixo dos y (ou seja o número da coluna)
    // - Direção da palavra
    // O resto do código é baseado em construtores, getters e setters.
    
    private String word;
    private int xcord;
    private int ycord;
    private String direction;

    public Answer(String word, int xcord, int ycord, String direction){
        this.word = word;
        this.xcord = xcord;
        this.ycord = ycord;
        this.direction = direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setXcord(int xcord) {
        this.xcord = xcord;
    }
    public void setYcord(int ycord) {
        this.ycord = ycord;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public String getDirection() {
        return direction;
    }
    public int getXcord() {
        return xcord;
    }
    public int getYcord() {
        return ycord;
    }
    public String getWord() {
        return word;
    }
    
}
