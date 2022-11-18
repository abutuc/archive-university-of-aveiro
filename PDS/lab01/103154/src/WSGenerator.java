import java.io.*;
import java.util.*;

public class WSGenerator {

	public static void main(String[] args) {

		// Sintaxe do programa
		if (args.length < 4) {
			System.err.println("Sintaxe: java WSGenerator -i [wordlist_path] -s [size] [options...]");
			System.err.println("options: -o [output_file_path]");
			return;
		}

		// Validar argumentos de entrada
		String wlPath = null, outPath = null;
		int size = 0;
		boolean[] flags = new boolean[3]; // [ i_flag, s_flag, o_flag ]
		// i - indexa opção; i+1 - indexa argumento
		for (int i = 0; i < args.length - 1; i = i + 2) {
			switch (args[i]) {
			case "-i":
				flags[0] = true;
				wlPath = args[i + 1];
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
				outPath = args[i + 1];
				break;
			default:
				System.err.printf("Erro: Opção inválida \"%s\".\n", args[i]);
				System.exit(1);
			}
		}
		// Não foram fornecidas as opçãoes obrigatórias: -i, -s
		if (!flags[0] || !flags[1])
		{
			System.err.println("Erro: Obrigatório fornecer as opções \"-i [wordlist_path]\" e \"-s [size]\".");
			System.exit(1);
		}

		Scanner in = new Scanner(System.in);

		// Gerar o puzzle
		WordSearch WS = new WordSearch(in, wlPath, size);

		// Obter o puzzle resultante
		String res = WS.toFileString();

		// Resultado na consola
		if (outPath == null) {
			System.out.print(res);
		}
		// Resultado no ficheiro
		else {
			while (true) {
				try {
					PrintWriter out = new PrintWriter(new FileWriter(outPath));
					out.print(res);
					out.close();
					break;
				} catch (IOException ioe) {
					System.out.printf("Não foi possível guardar output em \"%s\". Insira caminho de um ficheiro válido: ", outPath);
					outPath = in.next();
				}
			}
		}
	}
}
