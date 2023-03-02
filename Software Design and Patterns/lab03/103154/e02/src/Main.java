import java.io.*;
import java.util.*;

public class Main {

	private static HashMap<String, Flight> flights = new HashMap<>();

	public static void main(String[] args) {

		//
		// Origem dos comandos
		//
		Scanner sc = null;
		if (args.length > 0) {
			try {
				// Ficheiro
				sc = new Scanner(new File(args[0]));
			} catch (FileNotFoundException e) {
				System.err.println("Erro: Ficheiro de comandos não foi encontrado");
				System.exit(1);
			}
		} else {
			// Terminal
			sc = new Scanner(System.in);
		}

		//
		// Leitura dos comandos
		//
		while (true) {

			System.out.println("Escolha uma opção: (H para ajuda)");
			String line;
			try {
				 line = sc.nextLine();
			} catch (NoSuchElementException e) {
				break;
			}

			//
			// Processar input
			//
			// tokens[0] - Comando
			// tokens[1..end] - Argumentos
			//
			String[] tokens = line.trim().split("\\s+");

			//
			// Executar comando
			//
			switch (tokens[0]) {
			case "H":
				printHelp();
				break;
			case "I":
				if (tokens.length == 2)
					readFlightFromFile(tokens[1]);
				else
					System.err.println("Erro: Número de argumentos inválido");
				break;
			case "M":
				if (tokens.length == 2)
					printReservationMap(tokens[1]);
				else
					System.err.println("Erro: Número de argumentos inválido");
				break;
			case "F":
				if (tokens.length == 3)
					createFlight(tokens[1], "0x0", tokens[2]);
				else if (tokens.length == 4)
					createFlight(tokens[1], tokens[2], tokens[3]);
				else
					System.err.println("Erro: Número de argumentos inválido");
				break;
			case "R":
				if (tokens.length == 4)
					createReservation(tokens[1], tokens[2], tokens[3]);
				else
					System.err.println("Erro: Número de argumentos inválido");
				break;
			case "C":
				if (tokens.length == 2)
					cancelReservation(tokens[1]);
				else
					System.err.println("Erro: Número de argumentos inválido");
				break;
			case "Q":
				System.exit(0);
				break;
			case "":
				// Ignorar
				break;
			default:
				System.err.println("Erro: Comando inválido");
			}
		}
	}

	/**
	 * Comando H: Mostra ajuda
	 */
	private static void printHelp() {
		System.out.println("H                                                   - Mostra ajuda                           ");
		System.out.println("I filename                                          - Lê ficheiro com informação sobre um voo");
		System.out.println("M flight_code                                       - Mostra reservas de um voo              ");
		System.out.println("F flight_code num_seats_executive num_seats_tourist - Cria um novo voo                       ");
		System.out.println("      num_seats = [num_rows]x[num_seats_per_row]                                             ");
		System.out.println("R flight_code class number_seats                    - Cria uma reserva num voo               ");
		System.out.println("C flight_code:sequential_reservation_number         - Cancela uma reserva de um voo          ");
		System.out.println("Q                                                   - Termina o programa                     ");
		System.out.println();
	}

	/**
	 * Comando I: Lê ficheiro com informação sobre um voo
	 * 
	 * @param filePath
	 */
	private static void readFlightFromFile(String filePath) {
		// Tentar abrir ficheiro e scanner respetivo
		try (Scanner sc = new Scanner(new File(filePath))) {

			Flight flight; // Voo
			String flightCode; // Código do voo
			String[] tokens; // De cada linha processada

			//
			// Processar informação do voo
			//
			String flightInfo = sc.nextLine();

			// Ficheiro não começa por '>'
			if (flightInfo.charAt(0) != '>') {
				System.err.println("Erro: Ficheiro não começa por \'>\'");
				return;
			}

			tokens = flightInfo.substring(1) // Ignorar '>'
					.trim().split("\\s+");
			if (tokens.length == 2) {
				//
				// tokens[0] - Código de voo
				// tokens[1] - NFxNL em classe turística
				//
				flightCode = tokens[0];
				String[] dimT = tokens[1].split("x");
				flight = new Flight(flightCode, 0, 0, Integer.parseInt(dimT[0]), Integer.parseInt(dimT[1]));
			} else if (tokens.length == 3) {
				//
				// tokens[0] - Código de voo
				// tokens[1] - NFxNL em classe executiva
				// tokens[2] - NFxNL em classe turística
				//
				flightCode = tokens[0];
				String[] dimE = tokens[1].split("x");
				String[] dimT = tokens[2].split("x");
				flight = new Flight(flightCode, Integer.parseInt(dimE[0]), Integer.parseInt(dimE[1]), Integer.parseInt(dimT[0]), Integer.parseInt(dimT[1]));
			} else {
				System.err.println("Erro: Ficheiro tem informações do voo num formato inválido");
				return;
			}

			// Imprimir voo
			System.out.println(flight);

			//
			// Processar reservas efetuadas
			//
			while (sc.hasNextLine()) {

				String reservationInfo = sc.nextLine();

				tokens = reservationInfo.trim().split("\\s+");
				if (tokens.length == 0) {
					// Linha vazia
					continue;
				}
				if (tokens.length == 2) {
					//
					// tokens[0] - Classe da reserva
					// tokens[1] - Número de lugares
					//
					flight.createReservation(ClassType.valueOf(tokens[0]), Integer.parseInt(tokens[1]));
				} else {
					System.err.println("Erro: Ficheiro tem reservas do voo num formato inválido");
					return;
				}
			}

			// Inserir voo no dicionário
			flights.put(flightCode, flight);

		} catch (FileNotFoundException e) {
			System.err.println("Erro: Ficheiro não foi encontrado");
			return;
		}
	}

	/**
	 * Comando M: Mostra reservas de um voo
	 * 
	 * @param flightCode
	 */
	private static void printReservationMap(String flightCode) {
		flights.get(flightCode).printReservationMap();
	}

	/**
	 * Comando F: Cria um novo voo
	 * 
	 * @param flightCode
	 * @param nSeatsE
	 * @param nSeatsT
	 */
	private static void createFlight(String flightCode, String nSeatsE, String nSeatsT) {
		String[] dimE = nSeatsE.split("x");
		String[] dimT = nSeatsT.split("x");
		Flight flight = new Flight(flightCode, Integer.parseInt(dimE[0]), Integer.parseInt(dimE[1]), Integer.parseInt(dimT[0]), Integer.parseInt(dimT[1]));
		// Imprimir voo
		System.out.println(flight);
		// Inserir voo no dicionário
		flights.put(flightCode, flight);
	}

	/**
	 * Comando R: Cria uma reserva num voo
	 * 
	 * @param flightCode
	 * @param classType
	 * @param nSeats
	 */
	private static void createReservation(String flightCode, String classType, String nSeats) {
		// Voo não existe
		if (!flights.containsKey(flightCode)) {
			System.err.println("Erro: Voo " + flightCode + " não existe");
			return;
		}
		Flight flight = flights.get(flightCode);
		ArrayList<String> reservedRowSeatIds = flight.createReservation(ClassType.valueOf(classType), Integer.parseInt(nSeats));
		// Reserva foi efetuada com sucesso
		if (reservedRowSeatIds != null)
			System.out.println(flightCode + ":" + flight.getLastReservationNumber() + " = " + String.join(" | ", reservedRowSeatIds));
	}

	/**
	 * Comando C: Cancela uma reserva de um voo
	 * 
	 * @param reservationCode
	 */
	private static void cancelReservation(String reservationCode) {
		String[] tokens = reservationCode.split(":");
		String flightCode = tokens[0];
		int reservationId = Integer.parseInt(tokens[1]);
		// Voo não existe
		if (!flights.containsKey(flightCode)) {
			System.err.println("Erro: Voo " + flightCode + " não existe");
			return;
		}
		flights.get(flightCode).cancelReservation(reservationId);
		// Reserva foi cancelada com sucesso
		System.out.println("Reserva " + flightCode + ":" + reservationId + " foi cancelada com sucesso");
	}

}
