import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WSSolver {
    public static void main(String[] args) throws Exception {
        Data estrutura = parseInfo("sdl_04.txt");   // Chama a função de parsing e devolve um objeto da classe "Data"
        ArrayList<String> words = estrutura.getWords(); // Armazena as palavras a procurar numa ArrayList de Strings.
        ArrayList<Answer> answers = new ArrayList<Answer>();    // Criação da ArrayList de objetos "Answer" para guardar as respostas corretas
        FileWriter writer = new FileWriter("out4.txt"); // Inicialização do objeto FileWriter para escrever no ficheiro o conteúdo da ArrayList answers.

        // Loop que percorre as palavras a encontrar, chamando em cada iteração a função findAnswer que encontra a resposta do puzzle relativamente à palavra em questão.
        // Na mesma iteração é escrita a resposta no formato indicado no enunciado, utilizando para tal o método "format" da classe String.

        for (int i = 0; i<words.size(); i++){
            Answer answer = findAnswer(estrutura.getLetters(), estrutura.getnLetters(), words.get(i).length(), words.get(i).toUpperCase());
            answers.add(answer);
            writer.write(String.format("%-15s\t%10d\t%10d,%-5d%20s\n", words.get(i), words.get(i).length(), answer.getXcord(), answer.getYcord(),  answer.getDirection()));
        }
        writer.write("\n");;
        printTable(estrutura.getnLetters(), answers, writer);   // Chamada da função que trata da escrita da tabela com as soluções da sopa, deixando as palavras encontradas e colocando tudo o resto com pontos.
        writer.close();
    }

    
    // Esta função tem como output o objeto Data que advém do parsing do ficheiro input fornecido, nomeadamente fname.
    private static Data parseInfo(String fname) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(fname));

        // INICIALIZAÇÃO DAS VARIÁVEIS
        String line; // contém uma linha do ficheiro
        String lineUp;  // versão Upper Case da line.
        String[] letters = {"temp"};    // array usado para guardar o produto de line.split, caso estejamos na secção da sopa de letras
        String[] words = {"temp"};      // array usado para guardar o produto de line.split, caso estejamos na secção das palavras.
        // 

        ArrayList<String> words_data = new ArrayList<>(); // array list que guardará as palavras.
        char[][] matrix = new char[40][40];         // matriz de caracteres que contererá o mapeamento da sopa de letras.
        int count_line = 0; // contador usado para fazer tracking das linhas já lidas e que podem ser escritas na matrix
        int count = 0;  // contador usado para assegurar que a matriz é quadrada.

        // Este while loop percorre o ficheiro input verificando que caso o Uppercase da linha é igual ao seu estado inicial, estamos perante a sopa de letras, caso contrário estamos perante as palavras.
        // Para dividr as palavras usamos a regex que cobre todos os delimiters indicados no enunciado.

        while(scanner.hasNext()){
            line = scanner.nextLine();
            lineUp = line.toUpperCase();
            if (line.equals(lineUp)){
                letters = line.split("");
                for (int i = 0; i < letters.length; i++){
                    matrix[count_line][i] = letters[i].charAt(0);
                }
                count++;
            }
            else {
                words = line.split("[ ;,]");
                for (int f = 0; f < words.length; f++){
                    words_data.add(words[f]);
                }
            }
            count_line++;
        }

        // VERIFICA SE A MATRIX É QUADRADA.

        int nLetters = letters.length;
        int nWords = words_data.size();
        if (nLetters != count){
            System.err.println("O puzzle não é quadrado.");
            System.exit(1);
        }
        return new Data(matrix, words_data, nLetters, nWords);  // retorna objeto Data com o produto de todo o parsing anterior.

    }


    private static Answer findAnswer(char[][] matrix, int msize, int length_word, String word){
        int count_char = 0;
        ArrayList<String> directions = new ArrayList<String>(); // Array List que contém as direção candidatas a serem solução.

        // Este for-loop percorre cada célula da matrix da sopa de letras procurando direções candidatas. Para cada célula são avaliadas as 8 direções possíveis, nomeadamente as 8 células vizinhas,
        // A célula da matrix que corresponde ao primeiro caracter da palavra então é avaliado o segundo caracter, caso este também o seja, então a direção passa a ser uma direção candidata à solução e é adicionada
        // à lista das directions.

        // É verificado se sempre que percorremos uma direção não transbordámos os limites da tabela. Caso o tenhamos feito, então não é avaliada essa célula e a direção é reconhecida como não candidata.
        for (int i=0; i<msize;  i++){
            for (int j=0; j<msize; j++){
                if (word.charAt(count_char) == matrix[i][j]){
                    count_char++;
                    if ( i-1 >= 0  && i-1 < msize && j-1 < msize && j-1 >= 0){
                        if (matrix[i-1][j-1] == word.charAt(count_char)){
                            directions.add("UpLeft");
                        }
                    }
                    if ( i-1 >= 0  && i-1 < msize){
                        if (matrix[i-1][j] == word.charAt(count_char)){
                            directions.add("Up");
                        }
                    }
                    if ( i-1 >= 0  && i-1 < msize && j+1 < msize && j+1 >= 0){
                        if (matrix[i-1][j+1] == word.charAt(count_char)){
                            directions.add("UpRight");
                        }
                    }
                    if (j-1 < msize && j-1 >= 0){
                        if (matrix[i][j-1] == word.charAt(count_char)){
                            directions.add("Left");
                        }
                    }
                    if (j+1 < msize && j+1 >= 0){
                        if (matrix[i][j+1] == word.charAt(count_char)){
                            directions.add("Right");
                        }
                    }
                    if ( i+1 >= 0  && i+1 < msize && j-1 < msize && j-1 >= 0){
                        if (matrix[i+1][j-1] == word.charAt(count_char)){
                            directions.add("DownLeft");
                        }
                    }
                    if ( i+1 >= 0  && i+1 < msize){
                        if (matrix[i+1][j] == word.charAt(count_char)){
                            directions.add("Down");
                        }
                    }
                    if ( i+1 >= 0  && i+1 < msize && j+1 < msize && j+1 >= 0){
                        if (matrix[i+1][j+1] == word.charAt(count_char)){
                            directions.add("DownRight");
                        }
                    }
                }

                // Caso não exista nenhuma direção candidata na célula a avaliar, então o loop continua para as próximas células.
                if (directions.size() == 0){
                    count_char = 0;
                    continue;
                }
                // CASO CONTRÁRIO, para cada direção candidata, vamos percorrer todos os caracteres que faltam avaliar da palavra em questão, caso todos os caracteres na sopa de letras
                // corresponderem a todos os caracteres da palavra, então teremos encontrado a nossa solução da palavra, sendo retornado um objeto do tipo "Answer" com os dados da resposta correta.
                else {
                    int temp_x = i;
                    int temp_y = j;
                    count_char++;
                    int temp_count_char = count_char;
                    int n_directions = directions.size();
                    for (int f = 0; f<n_directions; f++){
                        switch(directions.get(f)){
                            case "UpLeft":
                                temp_x = i - 1;
                                temp_y = j - 1;
                                for (int l=2; l < length_word; l++){
                                    temp_x--;
                                    temp_y--;
                                    if (temp_x >= 0  && temp_x < msize && temp_y < msize && temp_y >= 0 && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "UpLeft");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }

                                }
                                break;
                            
                            case "Up":
                                temp_x = i - 1;
                                for (int l=2; l < length_word; l++){
                                    temp_x--;
                                    if (temp_x >= 0  && temp_x < msize && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "Up");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;

                            case "UpRight":
                                temp_x = i - 1;
                                temp_y = j + 1;
                                for (int l=2; l < length_word; l++){
                                    temp_x--;
                                    temp_y++;
                                    if (temp_x >= 0  && temp_x < msize && temp_y < msize && temp_y >= 0 && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "UpRight");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;
                            
                            case "Left":
                                temp_y = j - 1;
                                for (int l=2; l < length_word; l++){
                                    temp_y--;
                                    if (temp_y < msize && temp_y >= 0 && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "Left");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;
                            
                            case "Right":
                                temp_y = j + 1;
                                for (int l=2; l < length_word; l++){
                                    temp_y++;
                                    if (temp_y < msize && temp_y >= 0 && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "Right");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;
                            
                            case "DownLeft":
                                temp_x = i + 1;
                                temp_y = j - 1;
                                for (int l=2; l < length_word; l++){
                                    temp_x++;
                                    temp_y--;
                                    if (temp_x >= 0  && temp_x < msize && temp_y < msize && temp_y >= 0 && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "DownLeft");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;
                            
                            case "Down":
                                temp_x = i + 1;
                                for (int l=2; l < length_word; l++){
                                    temp_x++;
                                    if (temp_x >= 0  && temp_x < msize && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "Down");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;
                            
                            case "DownRight":
                                temp_x = i + 1;
                                temp_y = j + 1;
                                for (int l=2; l < length_word; l++){
                                    temp_x++;
                                    temp_y++;
                                    if (temp_x >= 0  && temp_x < msize && temp_y < msize && temp_y >= 0 && matrix[temp_x][temp_y] == word.charAt(temp_count_char)){
                                        temp_count_char++;
                                        if (l == length_word-1){
                                            return new Answer(word, i+1, j+1, "DownRight");
                                        }
                                        continue;
                                    }
                                    else{
                                        temp_x = i;
                                        temp_y = j;
                                        temp_count_char = count_char;
                                        break;
                                    }
                                }
                                break;
                            
                            default:
                                System.out.println("Not there yet.");
                        }
                    }
                    count_char = 0;
                    directions.clear();
                    continue;
                }
            }
        }
        System.err.println("Error: A Word was not found.");
        System.exit(1);
        return new Answer("not found", 0, 0, "None");
    }


    // função que trata da escrita da tabela com as soluções da sopa, deixando as palavras encontradas e colocando tudo o resto com pontos.
    private static void printTable(int table_size, ArrayList<Answer> answers, FileWriter writer) throws IOException{
        char[][] table = new char[table_size][table_size];
        // coloquei tudo inicialmente com pontos
        for (int s = 0; s < table_size; s++){
            for (int p = 0; p < table_size; p++){
                table[s][p] = '.';
            }
        }
        // percorrer as respostas e mapear os caracteres correspondentes nas coordenadas correspondentes.
        for (int i = 0; i < answers.size(); i++){
            Answer answer = answers.get(i);
            answer.setXcord(answer.getXcord()-1);
            answer.setYcord(answer.getYcord()-1);
            for (int f = 0; f < answer.getWord().length(); f++){
                switch (answer.getDirection()){
                    case "UpLeft":
                        table[answer.getXcord()-f][answer.getYcord()-f] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;

                    case "Up":
                        table[answer.getXcord()-f][answer.getYcord()] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;

                    case "UpRight":
                        table[answer.getXcord()-f][answer.getYcord()+f] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;

                    case "Left":
                        table[answer.getXcord()][answer.getYcord()-f] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;
                    
                    case "Right":
                        table[answer.getXcord()][answer.getYcord()+f] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;
                        
                    case "DownLeft":
                        table[answer.getXcord()+f][answer.getYcord()-f] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;

                    case "Down":
                        table[answer.getXcord()+f][answer.getYcord()] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;

                    case "DownRight":
                        table[answer.getXcord()+f][answer.getYcord()+f] = Character.toUpperCase(answer.getWord().charAt(f));
                        break;
                        
                    default:
                        System.out.println("Something happened here.");
                }
            }
            answer.setXcord(answer.getXcord()+1);
            answer.setYcord(answer.getYcord()+1);
        }
        
        for (int s = 0; s < table_size; s++){
            for (int p = 0; p < table_size; p++){
                writer.write(String.format("%s ", table[s][p]));
            }
            writer.write("\n");
        }

    }
}