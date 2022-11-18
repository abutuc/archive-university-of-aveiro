import java.util.ArrayList;
public class Data {
    // Esta classe serve como container aos dados que são fruto do parsing do conteúdo do ficheiro input:
    // - Conjunto de letras, nomeadamente a sopa de letras que é representado por uma matriz de caracteres.
    // - Conjunto de palavras a procurar
    // - Número de letras, que corresponde à dimensão da sopa de letras.
    // - Número de palavras a procurar.
    // O resto do código é baseado em construtores, getters e setters.
    
    private char[][] letters;
    private ArrayList<String> words;
    private int nLetters;
    private int nWords;
    
    public Data(char[][] letters, ArrayList<String> words, int nLetters, int nWords) {
        this.letters = letters;
        this.words = words;
        this.nLetters = nLetters;
        this.nWords = nWords;
    }

    public void setLetters(char[][] letters) {
        this.letters = letters;
    }
    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
    public void setnLetters(int nLetters) {
        this.nLetters = nLetters;
    }
    public void setnWords(int nWords) {
        this.nWords = nWords;
    }

    public char[][] getLetters() {
        return letters;
    }
    public ArrayList<String> getWords() {
        return words;
    }
    public int getnLetters() {
        return nLetters;
    }
    public int getnWords() {
        return nWords;
    }
}
