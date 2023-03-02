package lab3;

import java.util.*;
import java.io.*;

public class lab03_2 {
    static ArrayList<String> info = new ArrayList<>();
    public static void main(String[] args){
        String codigo_voo = "";

        /*print das opcoes*/
        System.out.println("Escolha uma opção: ");
        System.out.println("Para ajuda use a opção -H");
        Scanner sc = new Scanner(System.in);
        String out = sc.nextLine();
        String[] output = out.split("[ ]");
        while(!output[0].equals("Q")) {
            if (output[0].equals("H")) {
                H();
                System.out.println("Escolha uma opçao: (H para ajuda) ");
                out = sc.nextLine();
                output = out.split("[ ]");
            } else if (output[0].equals("I")) {
                I(output[1], info, codigo_voo);
                System.out.println("Escolha uma opçao: (H para ajuda) ");
                out = sc.nextLine();
                output = out.split("[ ]");

            } else if (output[0].equals("M")) {
                M(info);
                System.out.println("Escolha uma opçao: (H para ajuda) ");
                out = sc.nextLine();
                output = out.split("[ ]");

            } else if (output[0].equals("F")) {
                if (output.length == 4){
                    F(output[1], output[2], output[3]);//caso no voo exista a classe Executiva
                }
                else if (output.length == 3){//sem classe executiva
                    F(output[1], null, output[2]);
                }
                System.out.println("Escolha uma opçao: (H para ajuda) ");
                out = sc.nextLine();
                output = out.split("[ ]");

            } else {
                System.out.println("Comando inserido nao é opção válida: (H para ajuda) ");
                out = sc.nextLine();
                output = out.split("[ ]");

            }
        }
    }

    /*opcao de ajuda*/
    private static void H() {
        System.out.println();
        System.out.println("H - Lista das opções");
        System.out.println("I - Informação sobre um voo");
        System.out.println("M - Mapa das reservas");
        System.out.println("F - Acrescenta um novo voo");
        System.out.println("R - Acrescenta uma nova reserva a um voo");
        System.out.println("C - Cancela uma reserva");
        System.out.println("Q - Terminar o Programa");
        System.out.println();
    }

    /*opcao I*/
    //le o ficheiro de texto
    private static void I(String nome_fic, ArrayList<String> info, String codigo_voo) {
        int cont_exec = 0;
        int cont_tur = 0;
        int xT;
        int xE = 0;

        Voo voo;

        ArrayList<String> info_reservas = new ArrayList<>();
        ArrayList<Voo> info_voos = new ArrayList<>();

        Reserva reserva;
        try {
            File fic = new File(nome_fic);
            Scanner sc = new Scanner(fic);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                info.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Não foi possível encontrar o ficheiro ou ele não existe!");
            e.printStackTrace();
        }

        String[] info2 = info.get(0).split("[ ]");
        codigo_voo = info2[0].substring(1, info2[0].length()-1);
        //para apenas classe turistica
        if(info2.length == 2){
            voo = new Voo(codigo_voo, info2[1]);
            info_voos.add(voo);
        } else { //para turistico e executivo
            voo = new Voo(codigo_voo, info2[1], info2[2]);
            info_voos.add(voo);
        }

        System.out.println("Código de voo: " + voo.getCodigo_voo()); //TPXXXX
        if(info2.length == 2){
            String[] t = voo.getClasse_turistica().split("x");
            xT = Integer.parseInt(t[0]) * Integer.parseInt(t[1]);
            System.out.println("Lugares disponíveis: " + xT + "lugares de classe turística.");
        } else {
            String[] t = voo.getClasse_turistica().split("x");
            xT = Integer.parseInt(t[0]) * Integer.parseInt(t[1]);
            System.out.print("Lugares disponíveis: " + xT + "lugares de classe turística e ");
            String[] e = voo.getClasse_executiva().split("x");
            xE = Integer.parseInt(t[0]) * Integer.parseInt(t[1]);
            System.out.println(xE + " lugares de classe executiva.");
        }

        for(int i = 1; i < info.size(); i++){
            info_reservas.add(info.get(i));
        }

        for (int i = 0; i < info_reservas.size(); i++){
            String[] s = info_reservas.get(i).split("[ ]");
            if(s[0].equals("T")){
                cont_tur += Integer.parseInt(s[1]);
            }
            else if (s[0].equals("E")){
                cont_exec += Integer.parseInt(s[1]);
            }
            else{
                System.out.print("Argumentos inválidos");
            }
        }
        if (info2.length == 2){
            reserva = new Reserva("T", cont_tur);
        }
        else{
            reserva = new Reserva("T", "E", cont_tur, cont_exec);
        }

        //por completar...

    }

    /*opcao M*/
    private static void M(ArrayList<String> info) {
        System.out.println(info);
    }

    /*opcao F*/
    private static void F(String s, String s1, String s2) {
        Voo vooNovoCriado = new Voo();
        vooNovoCriado.setCodigo_voo(s);
        vooNovoCriado.setClasse_executiva(s1);
        vooNovoCriado.setClasse_turistica(s2);

        System.out.println(vooNovoCriado);
    }
}
