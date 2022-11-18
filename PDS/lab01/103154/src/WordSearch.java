import java.io.*;
import java.util.*;

public class WordSearch {

	// Obtidos do ficheiro
	private int size;
	private char[][] letters;
	private ArrayList<String> words;
	// Obtidos de métodos
	private HashMap<String, WordInfo> wordsInfo; // Associa a palavra às suas informações no puzzle
	private ArrayList<ArrayList<ArrayList<String>>> wordsInLetters; // Palavras mapeadas em cada letra do puzzle
	private boolean[][] markedLetters; // Letras do puzzle pelas quais passa pelo menos uma palavra
	private boolean solved; // Indica se o puzzle está resolvido

	/**
	 * Carrega o puzzle de um ficheiro.
	 * 
	 * @param in     - Scanner para ler input do terminal.
	 * @param wsPath - Ficheiro com o puzzle e as palavras.
	 */
	public WordSearch(Scanner in, String wsPath) {

		File file;
		Scanner sc;

		// Certificar que o path corresponde a um ficheiro não vazio
		while (true) {
			try {
				file = new File(wsPath);
				sc = new Scanner(file);

				// Ficheiro vazio
				if (!sc.hasNextLine()) {
					System.out.printf("Ficheiro \"%s\" está vazio. Insira caminho de um ficheiro com puzzle e lista de palavras: ", wsPath);
					wsPath = in.next();
				} else {
					break;
				}
			}
			// Ficheiro não encontrado
			catch (FileNotFoundException fnfe) {
				System.out.printf("Ficheiro \"%s\" não foi encontrado. Insira caminho de um ficheiro com puzzle e lista de palavras: ", wsPath);
				wsPath = in.next();
			}
		}

		// Tamanho do puzzle
		int W = 0;
		int H = 0;

		this.words = new ArrayList<>();

		// Enquanto houver linhas no ficheiro
		while (sc.hasNextLine()) {

			// Ler linha do ficheiro
			String line = sc.nextLine();
			if (line.isEmpty()) {
				System.err.println("Erro: Ficheiro não pode ter linhas vazias.");
				System.exit(1);
			}
			// Número de caracteres na linha
			int nChars = line.length();
			
			// Primeira linha do puzzle
			if (W == 0) {
				this.size = W = nChars;
				this.letters = new char[W][W];
			}
			
			// Linhas do puzzle ainda por ler
			if (H < W) {
				// Puzzle não é quadrado
				if (nChars != W) {
					System.err.println("Erro: Puzzle deve ser quadrado.");
					System.exit(1);
				}
				// Tamanho do puzzle excede os limites
				if (nChars > 40) {
					System.err.println("Erro: Puzzle excede o tamanho máximo de 40x40.");
					System.exit(1);
				}
				// Linha lida não pertence ao puzzle
				if (!line.equals(line.toUpperCase())) {
					System.err.println("Erro: As letras do puzzle devem ser apenas maiúsculas.");
					System.exit(1);
				}
				// Guardar caracteres da linha do puzzle
				letters[H++] = line.toCharArray();
			}
			// Linha de palavras
			else {
				// Para cada palavra na linha
				for (String word : line.split("[, ;]")) {
					// Palavra não existe
					if (word.length() == 0)
						continue;

					// Palavra está em maiúsculas
					if (word.equals(word.toUpperCase())) {
						System.err.printf("Erro: Palavra inválida \"%s\". Palavras devem estar só em minúsculas ou misturadas.\n", word);
						System.exit(1);
					}

					// Palavra composta por caracteres não alfabéticos
					if (!word.matches("[a-zA-Z]+")) {
						System.err.printf("Erro: Palavra inválida \"%s\". Palavras devem ser compostas apenas por caracteres alfabéticos.\n", word);
						System.exit(1);
					}
					// Guardar palavra
					this.words.add(word);
				}
			}
		}
	}

	/**
	 * Gera o puzzle a partir de uma lista de palavras e do tamanho do puzzle.
	 * 
	 * @param in     - Scanner para ler input do terminal.
	 * @param wlPath - Ficheiro com a lista de palavras.
	 * @param size   - Tamanho do puzzle.
	 */
	public WordSearch(Scanner in, String wlPath, int size) {

		// Tamanho do puzzle excede os limites
		if (size > 40) {
			System.err.println("Erro: Puzzle excede o tamanho máximo de 40x40.");
			System.exit(1);
		}
		this.size = size;

		File file;
		Scanner sc;

		// Certificar que o path corresponde a um ficheiro não vazio
		while (true) {
			try {
				file = new File(wlPath);
				sc = new Scanner(file);

				// Ficheiro vazio
				if (!sc.hasNextLine()) {
					System.out.printf("Ficheiro \"%s\" está vazio. Insira caminho de um ficheiro com lista de palavras: ", wlPath);
					wlPath = in.next();
				} else {
					break;
				}
			}
			// Ficheiro não encontrado
			catch (FileNotFoundException fnfe) {
				System.out.printf("Ficheiro \"%s\" não foi encontrado. Insira caminho de um ficheiro com lista de palavras: ", wlPath);
				wlPath = in.next();
			}
		}

		this.words = new ArrayList<>();

		// Enquanto houver linhas no ficheiro
		while (sc.hasNextLine()) {

			// Ler linha do ficheiro
			String line = sc.nextLine();
			if (line.isEmpty()) {
				System.err.println("Erro: Ficheiro não pode ter linhas vazias.");
				System.exit(1);
			}

			// Para cada palavra na linha
			for (String word : line.split("[, ;]")) {
				// Palavra não existe
				if (word.length() == 0)
					continue;

				// Palavra composta por caracteres não alfabéticos
				if (!word.matches("[a-zA-Z]+")) {
					System.err.printf("Erro: Palavra inválida \"%s\". Palavras devem ser compostas apenas por caracteres alfabéticos.\n", word);
					System.exit(1);
				}
				// Guardar palavra
				this.words.add(word);
			}
		}

		// Ordenar palavras por ordem decrescente de comprimento
		ArrayList<String> wordsDescending = new ArrayList<>();
		wordsDescending.addAll(this.words);
		Collections.sort(wordsDescending, Comparator.comparingInt(String::length).reversed());

		// Maior palavra a inserir no puzzle
		String longestWord = wordsDescending.get(0);
		if (longestWord.length() > size) {
			System.err.printf("Erro: Palavra inválida \"%s\" (%d). Palavra excede os limites do puzzle (%d).\n", longestWord, longestWord.length(), size);
			System.exit(1);
		}

		// Gerar letras até encontrar um puzzle válido (1000 tentativas)
		for (int k = 0; k < 1000; k++) {
			// Inserir as palavras no puzzle
			this.letters = this.generateLetters(new char[size][size], wordsDescending, 0);
			// Adicionar letras aleatórias nos espaços vazios
			Random rand = new Random();
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					if (this.letters[i][j] == '\0')
						this.letters[i][j] = (char) ((int) 'A' + rand.nextInt(26));
			// Desabilitar temporariamente stderr para suprimir as mensagens de System.err dentro do método solveBruteForce
			PrintStream err = System.err;
			System.setErr(new PrintStream(new OutputStream() {
				public void write(int b) {
				}
			}));
			// Resolver o puzzle para certificar de que pode ser resolvido
			boolean solvable = this.solveBruteForce();
			// Reabilitar stdout
			System.setErr(err);
			// Puzzle tem solução
			if (solvable)
				return;
			// Limpar as letras
			this.letters = new char[size][size];
		}

		// Não foi encontrado um puzzle válido
		System.err.println("Erro: Não foi possível gerar um puzzle válido.");

	}

	/**
	 * Resolve o puzzle por um método de força bruta.
	 * 
	 * @return boolean - true se teve sucesso, false caso contrário.
	 */
	public boolean solveBruteForce() {

		this.wordsInfo = new HashMap<>();
		this.wordsInLetters = new ArrayList<>();
		for (int x = 0; x < this.size; x++) {
			this.wordsInLetters.add(new ArrayList<>());
			for (int y = 0; y < this.size; y++)
				this.wordsInLetters.get(x).add(new ArrayList<String>());
		}
		this.markedLetters = new boolean[this.size][this.size];

		// Associa comprimento da palavra a todas as palavras candidadas no puzzle
		HashMap<Integer, ArrayList<WordInfo>> allWords = new HashMap<>();

		// Ordenar palavras por ordem decrescente de comprimento
		ArrayList<String> wordsDescending = new ArrayList<>();
		wordsDescending.addAll(this.words);
		Collections.sort(wordsDescending, Comparator.comparingInt(String::length).reversed());

		// Para cada palavra
		for (String word : wordsDescending) {
			int len = word.length(); // Comprimento da palavra
			int nMatches = 0; // Número de correspondências

			// Obter palavras candidatas
			if (!allWords.containsKey(len)) {
				allWords.put(len, this.getAllWords(len));
			}
			ArrayList<WordInfo> candidates = allWords.get(len);

			// Para cada palavra candidata
			for (WordInfo candidate : candidates) {
				// Corresponde à palavra a procurar
				if (candidate.getWord().equals(word.toLowerCase())) {
					// Se for possível marcar as letras da palavra no puzzle
					if (this.markLetters(candidate)) {
						nMatches++;
						// Associar à palavra encontrada as suas informações
						this.wordsInfo.put(word, candidate);
					}
					// Mais do que uma correpondência encontrada
					if (nMatches > 1) {
						System.err.printf("Erro: Palavra \"%s\" foi encontrada mais do que 1 vez.\n", word);
						return false;
					}
				}

			}
			// Palavra não foi encontrada
			if (nMatches == 0) {
				System.err.printf("Erro: Palavra \"%s\" não foi encontrada.\n", word);
				return false;
			}
		}

		// Puzzle está resolvido
		return this.solved = true;
	}

	/**
	 * Retorna a resolução do puzzle.
	 * 
	 * @return String
	 */
	public String toResultsString() {

		// Puzzle não foi resolvido
		if (!this.solved)
			return "O puzzle não foi resolvido!\n";

		StringBuilder sb = new StringBuilder();

		// Tabela
		for (String word : this.words) {
			int len = this.wordsInfo.get(word).getLength();
			int x = this.wordsInfo.get(word).getX();
			int y = this.wordsInfo.get(word).getY();
			String ori = this.wordsInfo.get(word).getOrientation().getLabel();

			sb.append(String.format("%-15s\t%d\t%-5s\t%-10s\n", word, len, (x + 1) + "," + (y + 1), ori));
		}
		sb.append("\n");

		// Puzzle
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				sb.append((markedLetters[i][j]) ? letters[i][j] + " " : ". ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * Retorna a estrutura para ficheiro do puzzle.
	 * 
	 * @return String
	 */
	public String toFileString() {

		// Puzzle não foi resolvido
		if (!this.solved)
			return "O puzzle não foi resolvido!\n";

		StringBuilder sb = new StringBuilder();

		// Puzzle
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++)
				sb.append(this.letters[i][j]);
			sb.append("\n");
		}

		// Lista de palavras
		sb.append(String.join(";", this.words));

		return sb.toString();
	}

	/* --------------------------------------------- Getters ---------------------------------------------- */

	public int getSize() {
		return this.size;
	}

	public char[][] getLetters() {
		return this.letters;
	}

	public ArrayList<String> getWords() {
		return this.words;
	}

	public HashMap<String, WordInfo> getWordsInfo() {
		return this.wordsInfo;
	}

	public ArrayList<ArrayList<ArrayList<String>>> getWordsInLetters() {
		return this.wordsInLetters;
	}

	public boolean[][] getMarkedLetters() {
		return this.markedLetters;
	}

	/* --------------------------------------------- Setters ---------------------------------------------- */

	public void setSize(int size) {
		this.size = size;
	}

	public void setLetters(char[][] letters) {
		this.letters = letters;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	public void setWordsInLetters(ArrayList<ArrayList<ArrayList<String>>> wordsInLetters) {
		this.wordsInLetters = wordsInLetters;
	}

	public void setMarkedLetters(boolean[][] markedLetters) {
		this.markedLetters = markedLetters;
	}

	/* ----------------------------------- Métodos privados de suporte ------------------------------------ */

	/**
	 * Retorna uma lista com todas as palavras de comprimento len no puzzle.
	 * 
	 * @param len - Comprimento das palavras.
	 * @return ArrayList<WordInfo>
	 */
	private ArrayList<WordInfo> getAllWords(int len) {

		ArrayList<WordInfo> wordsInfo = new ArrayList<>();

		wordsInfo.addAll(this.getAllWordsHorizontal(len));
		wordsInfo.addAll(this.getAllWordsVertical(len));
		wordsInfo.addAll(this.getAllWordsDiagonal(len));

		return wordsInfo;
	}

	/**
	 * Retorna uma lista com todas as palavras de comprimento len no puzzle na horizontal.
	 * 
	 * @param len - Comprimento das palavras.
	 * @return ArrayList<WordInfo>
	 */
	private ArrayList<WordInfo> getAllWordsHorizontal(int len) {

		ArrayList<WordInfo> wordsInfo = new ArrayList<>();
		StringBuilder word = new StringBuilder();

		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size - len + 1; j++) {
				word.setLength(0);
				for (int k = 0; k < len; k++)
					word.append(Character.toLowerCase(this.letters[i][j + k]));
				// Adicionar palavra e respetiva inversa
				wordsInfo.add(new WordInfo(word.toString(), i, j, Orientation.R));
				wordsInfo.add(new WordInfo(word.reverse().toString(), i, j + len - 1, Orientation.L));
			}

		return wordsInfo;
	}

	/**
	 * Retorna uma lista com todas as palavras de comprimento len no puzzle na vertical.
	 * 
	 * @param len - Comprimento das palavras.
	 * @return ArrayList<WordInfo>
	 */
	private ArrayList<WordInfo> getAllWordsVertical(int len) {

		ArrayList<WordInfo> wordsInfo = new ArrayList<>();
		StringBuilder word = new StringBuilder();

		for (int i = 0; i < this.size - len + 1; i++)
			for (int j = 0; j < this.size; j++) {
				word.setLength(0);
				for (int k = 0; k < len; k++)
					word.append(Character.toLowerCase(this.letters[i + k][j]));
				// Adicionar palavra e respetiva inversa
				wordsInfo.add(new WordInfo(word.toString(), i, j, Orientation.D));
				wordsInfo.add(new WordInfo(word.reverse().toString(), i + len - 1, j, Orientation.U));
			}

		return wordsInfo;
	}

	/**
	 * Retorna uma lista com todas as palavras de comprimento len no puzzle na diagonal.
	 * 
	 * @param len - Comprimento das palavras.
	 * @return ArrayList<WordInfo>
	 */
	private ArrayList<WordInfo> getAllWordsDiagonal(int len) {

		ArrayList<WordInfo> wordsInfo = new ArrayList<>();
		StringBuilder word = new StringBuilder();

		/* Diagonais CimaEsquerda <-> BaixoDireta */

		// Metade inferior (inclui a grande diagonal)
		for (int i = 0; i < this.size - len + 1; i++)
			for (int j = 0; j < i + 1; j++) {
				word.setLength(0);
				for (int k = 0; k < len; k++)
					word.append(Character.toLowerCase(this.letters[i + k][j + k]));
				// Adicionar palavra e respetiva inversa
				wordsInfo.add(new WordInfo(word.toString(), i, j, Orientation.DR));
				wordsInfo.add(new WordInfo(word.reverse().toString(), i + len - 1, j + len - 1, Orientation.UL));
			}

		// Metade superior
		for (int i = 0; i < this.size - len; i++)
			for (int j = i + 1; j < this.size - len + 1; j++) {
				word.setLength(0);
				for (int k = 0; k < len; k++)
					word.append(Character.toLowerCase(this.letters[i + k][j + k]));
				// Adicionar palavra e respetiva inversa
				wordsInfo.add(new WordInfo(word.toString(), i, j, Orientation.DR));
				wordsInfo.add(new WordInfo(word.reverse().toString(), i + len - 1, j + len - 1, Orientation.UL));
			}

		/* Diagonais BaixoEsquerda <-> CimaDireita */

		// Metade superior (inclui a grande diagonal)
		for (int i = len - 1; i < this.size; i++)
			for (int j = 0; j < this.size - i; j++) {
				word.setLength(0);
				for (int k = 0; k < len; k++)
					word.append(Character.toLowerCase(this.letters[i - k][j + k]));
				// Adicionar palavra e respetiva inversa
				wordsInfo.add(new WordInfo(word.toString(), i, j, Orientation.UR));
				wordsInfo.add(new WordInfo(word.reverse().toString(), i - len + 1, j + len - 1, Orientation.DL));
			}

		// Metade inferior
		for (int i = len; i < this.size; i++)
			for (int j = this.size - i; j < this.size - len + 1; j++) {
				word.setLength(0);
				for (int k = 0; k < len; k++)
					word.append(Character.toLowerCase(this.letters[i - k][j + k]));
				// Adicionar palavra e respetiva inversa
				wordsInfo.add(new WordInfo(word.toString(), i, j, Orientation.UR));
				wordsInfo.add(new WordInfo(word.reverse().toString(), i - len + 1, j + len - 1, Orientation.DL));
			}

		return wordsInfo;
	}

	/**
	 * Marca as letras onde a palavra fornecida é mapeada e verifica se essa está contida noutra.
	 * 
	 * @param wordInfo
	 * @return boolean - true se teve sucesso, false caso contrário.
	 */
	private boolean markLetters(WordInfo wordInfo) {

		String word = wordInfo.getWord();
		int x = wordInfo.getX();
		int y = wordInfo.getY();
		int len = wordInfo.getLength();
		Orientation ori = wordInfo.getOrientation();

		// Valores temporários que apenas sobrescrevem os valores originais se for possível mapear a palavra sem esta estar contida noutra
		ArrayList<ArrayList<ArrayList<String>>> tempWordsInLetters = new ArrayList<>();
		for (int i = 0; i < this.size; i++) {
			tempWordsInLetters.add(new ArrayList<>());
			for (int j = 0; j < this.size; j++)
				tempWordsInLetters.get(i).add(new ArrayList<String>());
		}
		Collections.copy(tempWordsInLetters, this.wordsInLetters);
		boolean[][] tempMarkedLetters = this.markedLetters.clone();

		ArrayList<String> mappedWords; // Palavras mapeadas numa letra do puzzle
		HashSet<String> wordIsSubstringCurrentLetter; // Conjunto de palavras onde word está contida, na letra considerada
		HashSet<String> wordIsSubstring = new HashSet<>(); // Conjunto de palavras onde word está contida, em todas as letras que a compoem

		// Inicializar valores para a primeira letra
		wordIsSubstring.addAll(tempWordsInLetters.get(x).get(y));

		switch (ori) {
		case D:
			for (int i = x; i < x + len; i++) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(i).get(y);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(i).get(y).add(word);
				// Marcar letra
				tempMarkedLetters[i][y] = true;
			}
			break;
		case DL:
			for (int i = x, j = y; i < x + len; i++, j--) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(i).get(j);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(i).get(j).add(word);
				// Marcar letra
				tempMarkedLetters[i][j] = true;
			}
			break;
		case DR:
			for (int i = x, j = y; i < x + len; i++, j++) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(i).get(j);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(i).get(j).add(word);
				// Marcar letra
				tempMarkedLetters[i][j] = true;
			}
			break;
		case L:
			for (int j = y; j > y - len; j--) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(x).get(j);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(x).get(j).add(word);
				// Marcar letra
				tempMarkedLetters[x][j] = true;
			}
			break;
		case R:
			for (int j = y; j < y + len; j++) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(x).get(j);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(x).get(j).add(word);
				// Marcar letra
				tempMarkedLetters[x][j] = true;
			}
			break;
		case U:
			for (int i = x; i > x - len; i--) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(i).get(y);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(i).get(y).add(word);
				// Marcar letra
				tempMarkedLetters[i][y] = true;
			}
			break;
		case UL:
			for (int i = x, j = y; i > x - len; i--, j--) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(i).get(j);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(i).get(j).add(word);
				// Marcar letra
				tempMarkedLetters[i][j] = true;
			}
			break;
		case UR:
			for (int i = x, j = y; i > x - len; i--, j++) {
				// Palavras mapeadas nesta letra do puzzle
				mappedWords = tempWordsInLetters.get(i).get(j);
				// Conjunto de palavras onde word está contida, na letra considerada
				wordIsSubstringCurrentLetter = new HashSet<>();
				for (String W : mappedWords)
					if (W.toLowerCase().contains(word.toLowerCase()))
						wordIsSubstringCurrentLetter.add(W);
				// Conjunto de palavras onde word está contida, em todas as letras que a compoem
				wordIsSubstring.retainAll(wordIsSubstringCurrentLetter);
				// Adicionar palavra ao conjunto
				tempWordsInLetters.get(i).get(j).add(word);
				// Marcar letra
				tempMarkedLetters[i][j] = true;
			}
			break;
		}

		// Todas as letras da palavra estão contidas noutra maior
		if (wordIsSubstring.size() > 0)
			return false;

		// Valores temporários sobrescrevem os valores originais
		this.markedLetters = tempMarkedLetters;
		Collections.copy(this.wordsInLetters, tempWordsInLetters);

		return true;
	}

	/**
	 * Gera recursivamente as letras do puzzle a partir de uma lista de palavras e do tamanho do puzzle.
	 * 
	 * @param letters - Letras do puzzle atuais.
	 * @param words   - Palavras do puzzle por ordem decrescente de comprimento.
	 * @param wIndex  - índice da palavra atual.
	 * @return char[][] - Se existir solução, retorna letters modificada. Caso contrário, retorna letters não modificada.
	 */
	private char[][] generateLetters(char[][] letters, ArrayList<String> words, int wIndex) {

		// Baralhar orientações
		List<Orientation> randOrientations = Arrays.asList(Orientation.values());
		Collections.shuffle(randOrientations);

		// Para todas as orientações possíveis
		for (Orientation ori : randOrientations) {
			// Tentar inserir a palavra atual
			char[][] tryLetters = this.insertWord(letters, words.get(wIndex), ori);
			// Verificar se foi possível inserir a palavra no puzzle
			boolean successTry = false;
			outTry: for (int i = 0; i < this.size; i++)
				for (int j = 0; j < this.size; j++)
					if (tryLetters[i][j] != letters[i][j]) {
						successTry = true;
						break outTry;
					}
			// Foi possível inserir palavra no puzzle
			if (successTry) {
				// Não há mais palavras para inserir
				if (wIndex == words.size() - 1)
					return tryLetters;
				// Letras do puzzle obtidas recursivamente
				char[][] retLetters = this.generateLetters(tryLetters, words, wIndex + 1);
				// Verificar se existe solução
				boolean successRet = false;
				outRet: for (int i = 0; i < this.size; i++)
					for (int j = 0; j < this.size; j++)
						if (retLetters[i][j] != letters[i][j]) {
							successRet = true;
							break outRet;
						}
				// Existe solução com a palavra atual
				if (successRet)
					return retLetters;
			}
		}

		return letters;
	}

	/**
	 * Tenta inserir a palavra no puzzle.
	 * 
	 * @param letters - Letras do puzzle.
	 * @param word    - Palavra.
	 * @param ori     - Orientação da palavra.
	 * @return char[][] - Se for possível inserir, retorna letters modificada. Caso contrário, retorna letters não modificada.
	 */
	private char[][] insertWord(char[][] letters, String word, Orientation ori) {

		int N = this.size;
		int len = word.length();

		// Letras do puzzle temporárias
		char[][] tempLetters;
		// Número de letras da palavra iguais às já existentes no puzzle
		int nMatches;
		// Pares de coordenadas
		ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

		switch (ori) {
		case D:
			// Baralhar posições
			for (int i = 0; i < N - len + 1; i++)
				for (int j = 0; j < N; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x + k][y] == '\0')
						tempLetters[x + k][y] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x + k][y] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case DL:
			// Baralhar posições
			for (int i = 0; i < N - len + 1; i++)
				for (int j = len - 1; j < N; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x + k][y - k] == '\0')
						tempLetters[x + k][y - k] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x + k][y - k] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case DR:
			// Baralhar posições
			for (int i = 0; i < N - len + 1; i++)
				for (int j = 0; j < N - len + 1; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x + k][y + k] == '\0')
						tempLetters[x + k][y + k] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x + k][y + k] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case L:
			// Baralhar posições
			for (int i = 0; i < N; i++)
				for (int j = len - 1; j < N; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x][y - k] == '\0')
						tempLetters[x][y - k] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x][y - k] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case R:
			// Baralhar posições
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N - len + 1; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x][y + k] == '\0')
						tempLetters[x][y + k] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x][y + k] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case U:
			// Baralhar posições
			for (int i = len - 1; i < N; i++)
				for (int j = 0; j < N; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x - k][y] == '\0')
						tempLetters[x - k][y] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x - k][y] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case UL:
			// Baralhar posições
			for (int i = len - 1; i < N; i++)
				for (int j = len - 1; j < N; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x - k][y - k] == '\0')
						tempLetters[x - k][y - k] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x - k][y - k] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		case UR:
			// Baralhar posições
			for (int i = len - 1; i < N; i++)
				for (int j = 0; j < N - len + 1; j++)
					pairs.add(new ArrayList<>(Arrays.asList(i, j)));
			Collections.shuffle(pairs);
			// Para cada posição
			for (ArrayList<Integer> pair : pairs) {
				int x = pair.get(0);
				int y = pair.get(1);
				// Copiar letras do puzzle
				tempLetters = Arrays.stream(letters).map(char[]::clone).toArray(char[][]::new);
				nMatches = 0;
				for (int k = 0; k < len; k++) {
					// Letra da palavra nesta posição
					char letter = Character.toUpperCase(word.charAt(k));
					// Posição livre
					if (tempLetters[x - k][y + k] == '\0')
						tempLetters[x - k][y + k] = letter;
					// Posição com a mesma letra
					else if (tempLetters[x - k][y + k] == letter)
						nMatches++;
					// Posição com letra diferente
					else
						break;
					// Palavra inserida com sucesso: não está contida noutra palavra e todas as letras foram inseridas.
					if (nMatches < len && k == len - 1)
						return tempLetters;
				}
			}
			break;
		}

		// Não foi possível inserir a palavra no puzzle
		return letters;
	}

}
