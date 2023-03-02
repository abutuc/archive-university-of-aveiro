import java.util.*;

public class WSSolver {

	public static void main(String[] args) {

		// Sintaxe do programa
		if (args.length == 0) {
			System.out.println("Sintaxe: java WSSolver [file_path]");
			return;
		}

		Scanner in = new Scanner(System.in);
		
		// Carregar o puzzle
		WordSearch WS = new WordSearch(in, args[0]);

		// Método brute force
		if (WS.solveBruteForce() == false) {
			System.err.println("Erro: Não foi possível resolver o puzzle pelo método de força bruta.");
			System.exit(1);
		}

		// Apresentar os resultados
		System.out.print(WS.toResultsString());
	}
}
