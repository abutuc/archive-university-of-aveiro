import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class WSGenerator {
    public static void main(String[] args) throws FileNotFoundException {
            String fin = "";
            Integer size = 0;
            String fout = "";
		    boolean[] flags = new boolean[3]; // [ i_flag, s_flag, o_flag ]

            // Código que processa os dados da linha de comandos
		    // i - indexa opção; i+1 - indexa argumento
            for (int i = 0; i < args.length - 1; i = i + 2) {
                switch (args[i]) {
                case "-i":
                    flags[0] = true;
                    fin = args[i + 1];
                    break;
                case "-s":
                    flags[1] = true;
                    size = Integer.parseInt(args[i + 1]);
                    // Tamanho inválido
                    if (size <= 0)
                    {
                        System.err.printf("Erro: Tamanho inválido \"%s\" deve ser inteiro positivo.", size);
                        System.exit(1);
                    }
                    break;
                case "-o":
                    flags[2] = true;
                    fout = args[i + 1];
                    break;
                default:
                    System.err.printf("Erro: Opção inválida \"%s\".\n", args[i]);
                    System.exit(1);
                }
            }

            if (!flags[0] || !flags[1])
		    {
                System.err.println("Erro: Obrigatório fornecer as opções \"-i [wordlist_path]\" e \"-s [size]\".");
                System.exit(1);
		    
            }

            String[] directions = {"UpLeft", "UpRight", "Up", "Left", "Right", "DownLeft", "DownRight", "Down"};
            ArrayList<String> words = parseInfo(fin);
            ArrayList<String> sorted_words = new ArrayList<String>();
            sorted_words.addAll(words);
            Collections.sort(sorted_words, Comparator.comparingInt(String::length).reversed());
            Random rand = new Random();
            String direction;
            char[][] matrix = new char[size][size];
            String word = " ";
            int random_direction_i;
            int word_len_left;
            int temp_x;
            int temp_y;
            char[] memory_seq;
            int count_mem = 0;
            boolean found = false;
            boolean done = false;
            int words_checked = 0;

            // Loop que corre até encontrar uma sopa de palavras que respeite as condições enunciadas
            // Para cada iteração é gerada aleatoriamente uma direção e é percorrida a sopa de palavras (vazia) até encontrar onde a palavra
            // encaixa, quando encontrar coloca-a. A forma de avaliar se a mesma é possível de colocar, é observar se ao colocar a palavra esta não transborda dos limites.
            // Caso a colocação da palavra já foi sendo feita e avaliou-se que a mesma não podia estar lá, através do array memory_seq conseguimos fazer traceback do estado inicial antes da colocação da palavra em questão.
            // Caso todas a palavras encaixem de forma a não desobedecer às normas, então encontramos uma solução da sopa de palavras.
            while(!done){
            try { 
                for (int f = 0; f<sorted_words.size(); f++){
                    random_direction_i = rand.nextInt(directions.length);
                    direction = directions[random_direction_i];
                    word = sorted_words.get(f);
                    word_len_left = word.length()-1;
                    for (int i = 0; i < size; i++){
                        for (int j = 0; j < size; j++){
                            temp_x = i;
                            temp_y = j;
                            switch(direction){
                                case "UpLeft":
                                    if (i-word_len_left >= 0  && i-word_len_left < size && j-word_len_left >= 0 && j-word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_x--;
                                                temp_y--;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_x++;
                                                    temp_y++;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                
                                case "UpRight":
                                    if (i-word_len_left >= 0  && i-word_len_left < size && j+word_len_left >= 0 && j+word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_x--;
                                                temp_y++;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_x++;
                                                    temp_y--;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
    
                                case "Up":
                                    if (i-word_len_left >= 0  && i-word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_x--;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_x++;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                
                                case "Left":
                                    if ( j-word_len_left >= 0 && j-word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_y--;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_y++;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
    
                                case "Right":
                                    if (j+word_len_left >= 0 && j+word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_y++;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_y--;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                
                                case "DownLeft":
                                    if (i+word_len_left >= 0  && i+word_len_left < size && j-word_len_left >= 0 && j-word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_x++;
                                                temp_y--;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_x--;
                                                    temp_y++;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                
                                case "DownRight":
                                    if (i+word_len_left >= 0  && i+word_len_left < size && j+word_len_left >= 0 && j+word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_x++;
                                                temp_y++;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_x--;
                                                    temp_y--;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                
                                case "Down":
                                    if (i+word_len_left >= 0  && i+word_len_left < size){
                                        memory_seq = new char[word_len_left+1];
                                        for (int l=0; l <= word_len_left; l++){
                                            if (matrix[temp_x][temp_y] == '\0' || matrix[temp_x][temp_y] == word.charAt(l)){
                                                memory_seq[count_mem] = matrix[temp_x][temp_y];
                                                matrix[temp_x][temp_y] = word.charAt(l);
                                                count_mem++;
                                                temp_x++;
                                                found = true;
                                            }
                                            else {
                                                for (int s = l; s > 0; s--){
                                                    found = false;
                                                    temp_x--;
                                                    count_mem--;
                                                    matrix[temp_x][temp_y] = memory_seq[count_mem];
                                                }
                                                temp_x = i;
                                                temp_y = j;
                                                count_mem = 0;
                                                break;
                                            }
                                        }
                                    }
                                    break;
    
                                default:
                                    System.out.println("Something went wrong.");
                                    
                            }
                            if (found) break;
                        }
                        if (found) {
                            found = false;
                            words_checked++;
                            break;
                        }         
                    } 
            }
            if (words_checked == words.size()){
                done = true;
            }
    
    
    
            for (int i = 0; i<size; i++){
                for (int f = 0; f<size; f++){
                    if (matrix[i][f] == '\0'){
                        matrix[i][f] = (char) ((int) 'A' + rand.nextInt(26));       // ATRIBUIÇÃO ALEATÓRIA DOS ESPAÇOS QUE SE ENCONTRAM VAZIOS A CARACTERES.
                    }
                }
            }
            printTable(matrix, size, fout, words);
            done = true;

            }
            catch (Exception e){
                count_mem = 0;
                found = false;
                done = false;
                words_checked = 0;
                matrix = new char[size][size];
            }
        }
    }


    private static ArrayList<String> parseInfo(String fname) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(fname));
        ArrayList<String> words = new ArrayList<String>();
        while(scanner.hasNext()){
            words.add(scanner.next().toUpperCase());
        }
        return words;
    }

    private static void printTable(char[][] matrix, int size, String fout, ArrayList<String> words) throws IOException{
        if (fout == ""){
            for (int i = 0; i<size; i++){
                for (int f = 0; f<size; f++){
                    System.out.print(String.format("%s", matrix[i][f]));
                }
                System.out.println();
            }
            for (int s = 0; s < words.size(); s++){
                System.out.print(String.format("%s", words.get(s).toLowerCase()));
            }
            System.out.println();
        }
        else {
            FileWriter writer = new FileWriter(fout);
            for (int i = 0; i<size; i++){
                for (int f = 0; f<size; f++){
                        writer.write(String.format("%s ", matrix[i][f]));
                }
                writer.write("\n");
            }
            for (int s = 0; s<words.size(); s++){
                writer.write(String.format("%s ", words.get(s).toLowerCase()));
            }
            writer.write("\n");

            writer.close();
        }
    }
}
