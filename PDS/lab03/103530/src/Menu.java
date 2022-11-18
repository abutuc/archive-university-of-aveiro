import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        // Variable declaration and initialization
        boolean quit = false;       // flag used to terminate program when Q option is entered.
        String[] answer = new String[2];            // will contain raw splitted content of user input.
        String opcao = "";               // will contain the first word from answer
        String flight_code;
        String filename;
        String[] flight_info = new String[1];       // initialized just to avoid unnecessary error flags.
        String[] reservation_info = new String[2];  // initialized just to avoid unnecessary error flags.
        String reserve_string = "";
        String[] reservation_code;
        int sequencial_number;
        ArrayList<String> file_content;
        HashMap<String, Flight> flights = new HashMap<String, Flight>();            // HashMap que associa o código do voo à instancia do Flight correspondente 
        HashMap<String, HashMap<Integer, ArrayList<String>>> reservations = new HashMap<String, HashMap<Integer, ArrayList<String>>>(); // HashMap que associa o código do voo a um HashMap que contém as diversas reservas, sendo que cada reserva tem um sequencial_code que está associado a uma ArrayList que contém os lugares da reserva
        ArrayList<String> lista_comandos = new ArrayList<String>();

        Scanner sc = new Scanner(System.in);

        // Menu Startup
        while (!quit){
            // Não é passado nenhum ficheiro na linha de comando.
            if (args.length == 0){
                System.out.println("\n\nEscolha uma opção: (H para ajuda)");
                answer = sc.nextLine().split(" ");
                opcao = answer[0].toUpperCase();
                // System.out.println("Entrou aqui");
                // System.exit(1);
            }
            // Permite que seja passado um ficheiro com comandos na linha de comando.
            else if (args.length == 1) {
                // System.out.println("Entrou aqui");
                // System.exit(1);
                filename = args[0];
                System.out.println(filename);
                if(!readCommandListFile(filename, lista_comandos)){
                    args = new String[0];
                    continue;
                }
                else {
                    for (int j = 0; j<lista_comandos.size(); j++){
                        answer = lista_comandos.get(j).split(" ");
                        opcao = answer[0].toUpperCase();
                        switch(opcao){
                            case "H":
                                printMenu();
                                break;
                            
                            case "I":
                                filename = answer[1];
                                file_content = readFile(new File(filename));
                                if (file_content==null){
                                    break;
                                }
                                flight_code = createFlight(flights, file_content.get(0).split(" "), reservations);
                                System.out.println(flights.get(flight_code));
                                for (int s = 1; s < file_content.size(); s++){
                                    if(!makeReservation(flights, flight_code, file_content.get(s).split(" "), s, reservations)){
                                        continue;
                                    }
                                }
                                break;
            
                            case "M":
                                flight_code = answer[1];
                                System.out.println(flights.get(flight_code).getPlane());
                                break;
            
                            case "F":
                                if (answer.length == 4){
                                    flight_info = new String[3];
                                    for (int i = 1; i < answer.length; i++){
                                        flight_info[i-1] = answer[i];
                                    }
                                    
                                }
                                else if (answer.length == 3){
                                    flight_info = new String[2];
                                    for (int i = 1; i < answer.length; i++){
                                        flight_info[i-1] = answer[i];
                                    }
                                }
                                flight_code = createFlight(flights, flight_info, reservations);
                                System.out.println(flights.get(flight_code));
                                break;
                            
                            case "R":
                                flight_code = answer[1];
                                for (int i = 2; i < answer.length; i++){
                                    reservation_info[i-2] = answer[i];
                                }
                                if(!makeReservation(flights, flight_code, reservation_info, reservations.get(flight_code).size()+1, reservations)){
                                    break;
                                }
                                for (int i = 0; i < reservations.get(flight_code).get(reservations.get(flight_code).size()).size(); i++){
                                    reserve_string += reservations.get(flight_code).get(reservations.get(flight_code).size()).get(i) + " | ";
                                }
                                System.out.println(flight_code +  ":" + reservations.get(flight_code).size() + " = " + reserve_string);
                                reserve_string = "";
                                break;
                            
                            case "C":
                                reservation_code = answer[1].split(":");
                                flight_code = reservation_code[0];
                                sequencial_number = Integer.parseInt(reservation_code[1]);
                                cancelReservation(flights, flight_code, sequencial_number, reservations);
                                System.out.println("Reserva número " + sequencial_number + " do voo " + flight_code + " foi cancelada com sucesso.");
                                break;
            
                            case "Q":
                                quit = true;
                                break;
                            default:
                                System.out.println("Introduza uma operação válida.");
                                break;
                        }
                    }
                }
            }
            switch(opcao){
                case "H":
                    printMenu();
                    break;
                
                case "I":
                    filename = answer[1];
                    file_content = readFile(new File(filename));
                    if (file_content==null){
                        break;
                    }
                    flight_code = createFlight(flights, file_content.get(0).split(" "), reservations);
                    System.out.println(flights.get(flight_code));
                    for (int s = 1; s < file_content.size(); s++){
                        if(!makeReservation(flights, flight_code, file_content.get(s).split(" "), s, reservations)){
                            continue;
                        }
                    }
                    break;

                case "M":
                    flight_code = answer[1];
                    System.out.println(flights.get(flight_code).getPlane());
                    break;

                case "F":
                    if (answer.length == 4){
                        flight_info = new String[3];
                        for (int i = 1; i < answer.length; i++){
                            flight_info[i-1] = answer[i];
                        }
                        
                    }
                    else if (answer.length == 3){
                        flight_info = new String[2];
                        for (int i = 1; i < answer.length; i++){
                            flight_info[i-1] = answer[i];
                        }
                    }
                    flight_code = createFlight(flights, flight_info, reservations);
                    System.out.println(flights.get(flight_code));
                    break;
                
                case "R":
                    flight_code = answer[1];
                    for (int i = 2; i < answer.length; i++){
                        reservation_info[i-2] = answer[i];
                    }
                    if(!makeReservation(flights, flight_code, reservation_info, reservations.get(flight_code).size()+1, reservations)){
                        break;
                    }
                    for (int i = 0; i < reservations.get(flight_code).get(reservations.get(flight_code).size()).size(); i++){
                        reserve_string += reservations.get(flight_code).get(reservations.get(flight_code).size()).get(i) + " | ";
                    }
                    System.out.println(flight_code +  ":" + reservations.get(flight_code).size() + " = " + reserve_string);
                    reserve_string = "";
                    break;
                
                case "C":
                    reservation_code = answer[1].split(":");
                    flight_code = reservation_code[0];
                    sequencial_number = Integer.parseInt(reservation_code[1]);
                    cancelReservation(flights, flight_code, sequencial_number, reservations);
                    System.out.println("Reserva número " + sequencial_number + " do voo " + flight_code + " foi cancelada com sucesso.");
                    break;

                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Introduza uma operação válida.");
            }
        }
        sc.close();
    }

    // Method that prints Menu with the different possible options.
    public static void printMenu(){
        System.out.println("-------- Menu ---------\n");
        System.out.println("Opção H: Apresenta as opções do menu;\n");
        System.out.println("Opção I (filename): Lê um ficheiro de texto contendo informação sobre um voo;\n");
        System.out.println("Opção M (flight_code): Exibe o mapa das reservas do voo com o 'flight_code' passado;\n");
        System.out.println("Opção F (flight_code) (num_seats_executive) (num_seats_tourist): Acrescenta um novo voo, com código, lugares em executiva (p.ex. 4x3, representando 4 filas com 3 lugares por fila), e lugares em turística. Os lugares em classe executiva são opcionais podendo existir apenas lugares em turística;\n" );
        System.out.println("Opção R (flight_code) (class) (number_seats): Acrescenta uma nova reserva a um voo, com indicação do código do voo, da classe (T/E), e do número de lugares;\n");
        System.out.println("Opção C (reservation_code): Cancela uma reserva. O código de reserva tem o formato (flight_code):(sequential_reservation_number);\n");
        System.out.println("Opção Q: Termina o programa.\n");
        System.out.println("-----------------------");
    }

    // Method that reads the content of a file and returns its lines in an ArrayList of Strings.
    public static ArrayList<String> readFile(File file){
        int count = 0;
        ArrayList<String> flines =  new ArrayList<String>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                if (count == 0){
                    flines.add(sc.nextLine().substring(1)); // removes '>' char and returns code of flight
                    count++;
                }
                else {
                    flines.add(sc.nextLine());
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e){
            System.err.println("File was not found. Check name of file. If error persists, use the relative path of the file.");
            return null;
        }
        return flines;
    }

    // Método que cria um Avião e um Voo
    public static String createFlight(HashMap<String, Flight> flights, String[] flight_info, HashMap<String, HashMap<Integer, ArrayList<String>>> reservations){
        String flight_code = flight_info[0];
        String[] exec_configuration;
        String[] tur_configuration;
        Aviao aviao = new Aviao(new int[1][1], new int[1][1]);      // Initialize plane with random constructor parameter to avoid unnecessary error flags.
        if (flight_info.length == 2){                               // if plane doesn't have executive class
            tur_configuration = flight_info[1].split("x");
            aviao = new Aviao(new int[Integer.parseInt(tur_configuration[0])][Integer.parseInt(tur_configuration[1])]);          // creates plane with due configuration
        }
        else if (flight_info.length == 3){                          // if plane has executive case
            exec_configuration = flight_info[1].split("x");
            tur_configuration = flight_info[2].split("x");
            aviao = new Aviao(new int[Integer.parseInt(exec_configuration[0])][Integer.parseInt(exec_configuration[1])], new int[Integer.parseInt(tur_configuration[0])][Integer.parseInt(tur_configuration[1])]);          // creates plane with due configuration
        }
        else {
            System.err.println("An error has occured while trying to register flight. Input data is incorrect.");
            System.exit(1);
        }
        Flight voo = new Flight(flight_code, aviao);
        flights.put(flight_code, voo);
        reservations.put(flight_code, new HashMap<Integer, ArrayList<String>>());
        return flight_code;
    }

    // Método que faz uma reserva
    public static boolean makeReservation(HashMap<String, Flight> flights, String flight_code, String[] reservation, int sequencial_number, HashMap<String, HashMap<Integer, ArrayList<String>>> reservations){
        reservations.get(flight_code).put(sequencial_number, new ArrayList<String>());
        int nSeats_toReserve = Integer.parseInt(reservation[1]);
        int nSeats_toReserve_l = nSeats_toReserve;
        boolean empty_found = false;
        boolean found;
        Flight voo = flights.get(flight_code);
        if ((reservation[0].equals("T") && nSeats_toReserve > voo.getPlane().getLugares_turisticos_disponiveis()) || (reservation[1].equals("E") && nSeats_toReserve > voo.getPlane().getLugares_executivos_disponiveis())){
            System.out.println("Não foi possível obter lugares para a reserva: " + reservation[0] + " " + reservation[1]);
            return false;
        }

        else if (reservation[0].equals("T")){
            while (nSeats_toReserve != 0 && voo.getPlane().getLugares_turisticos_disponiveis() != 0){
                found = false;
                for (int i = 0; i < voo.getPlane().getTur_rows() && !empty_found; i++){
                    if (voo.getPlane().getSeatValue(i, 0, "Turístico") == 0){
                        //System.out.println("Entered empty Turistic row");
                        for (int f = 0; f < nSeats_toReserve_l; f++){
                            if (f > voo.getPlane().getTur_cols()-1){
                                //System.out.println("I need to switch Turistic row");
                                nSeats_toReserve_l = nSeats_toReserve;
                                //System.out.println(nSeats_toReserve_l + " left to sit on Turistic Section.");
                                empty_found = false;
                                break;
                            }
                            else {
                                //System.out.println("One person sat in Turistic Section.");
                                voo.getPlane().updateSeat(i, f, sequencial_number, "Turístico");
                                reservations.get(flight_code).get(sequencial_number).add(String.valueOf(i+1+voo.getPlane().getExec_rows()) + String.valueOf(((char)((int)'A' + f))));
                                nSeats_toReserve--;
                                empty_found = true;
                            }
                        }
                    }
                } 
                if (!empty_found && nSeats_toReserve != 0){
                    //System.out.println("Needs to be sorted out of available.");
                    for (int i = 0; i < voo.getPlane().getTur_rows(); i++){
                        for (int f = 0; f < voo.getPlane().getTur_cols(); f++){
                            if (voo.getPlane().getSeatValue(i, f, "Turístico") == 0){
                                //System.out.println("He sat.");
                                voo.getPlane().updateSeat(i, f, sequencial_number, "Turístico");
                                reservations.get(flight_code).get(sequencial_number).add(String.valueOf(i+1+voo.getPlane().getExec_rows()) + String.valueOf(((char)((int)'A' + f))));
                                nSeats_toReserve--;
                                found = true;
                                break;
                            }
                        }
                        if (found){
                            break;
                        }
                    }
                }
            }
        }
        else if (reservation[0].equals("E")){
            if (voo.getPlane().getLugares_executivos() == null){
                System.out.println("Classe executiva não disponível neste voo.");
                System.out.println("Não foi possível obter lugares para a reserva: " + reservation[0] + " " + reservation[1]);
                return false;
            }
            while (nSeats_toReserve != 0 && voo.getPlane().getLugares_executivos_disponiveis() != 0){
                found = false;
                for (int i = 0; i < voo.getPlane().getExec_rows() && !empty_found; i++){
                    if (voo.getPlane().getSeatValue(i, 0, "Executivo") == 0){
                        //System.out.println("Entered empty Executive row");
                        for (int f = 0; f < nSeats_toReserve_l; f++){
                            if (f > voo.getPlane().getExec_cols()-1){
                                //System.out.println("I need to switch Executive row");
                                nSeats_toReserve_l = nSeats_toReserve;
                                //System.out.println(nSeats_toReserve_l + " left to sit in Executive Section.");
                                empty_found = false;
                                break;
                            }
                            else {
                                //System.out.println("One person sat in Executive Section.");
                                voo.getPlane().updateSeat(i, f, sequencial_number, "Executivo");
                                reservations.get(flight_code).get(sequencial_number).add(String.valueOf(i+1) + String.valueOf(((char)((int)'A' + f))));
                                nSeats_toReserve--;
                                empty_found = true;
                            }
                        }
                    }
                }
                if (!empty_found && nSeats_toReserve != 0){
                    //System.out.println("Needs to be sorted out of available.");
                    for (int i = 0; i < voo.getPlane().getExec_rows(); i++){
                        for (int f = 0; f < voo.getPlane().getExec_cols(); f++){
                            if (voo.getPlane().getSeatValue(i, f, "Executivo") == 0){
                                //System.out.println("He sat.");
                                voo.getPlane().updateSeat(i, f, sequencial_number, "Executivo");
                                reservations.get(flight_code).get(sequencial_number).add(String.valueOf(i+1) + String.valueOf(((char)((int)'A' + f))));
                                nSeats_toReserve--;
                                found = true;
                                break;
                            }
                        }
                        if (found){
                            break;
                        }
                    }
                }
            }
        }
        if (nSeats_toReserve != 0){
            System.out.println("Não foi possível obter lugares para a reserva: " + reservation[0] + " " + reservation[1]);
            return false;
        }

        return true;
    }


    // Método que cancela uma reserva
    public static boolean cancelReservation(HashMap<String, Flight> flights, String flight_code, int sequencial_number, HashMap<String, HashMap<Integer, ArrayList<String>>> reservations){
        Flight voo = flights.get(flight_code);
        for (int i = 0; i < voo.getPlane().getTur_rows(); i++){
            for (int f = 0; f < voo.getPlane().getTur_cols(); f++){
                if (voo.getPlane().getSeatValue(i, f, "Turístico") == sequencial_number){
                    voo.getPlane().updateSeat(i, f, 0, "Turístico");
                }
            }
        }

        for (int i = 0; i < voo.getPlane().getExec_rows(); i++){
            for (int f = 0; f < voo.getPlane().getExec_cols(); f++){
                if (voo.getPlane().getSeatValue(i, f, "Executivo") == sequencial_number){
                    voo.getPlane().updateSeat(i, f, 0, "Executivo");
                }
            }
        }
        reservations.get(flight_code).remove(sequencial_number);
        return true;
    }


    // Método que lê lista de comandos
    public static boolean readCommandListFile(String filename, ArrayList<String> command_list){
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()){
                command_list.add(sc.nextLine());
            }
            sc.close();
            return true;
            }
        catch (FileNotFoundException e){
            System.err.println("File entered doesn't exit. If error persists, use relative path.");
            return false;
        }
    }


}
