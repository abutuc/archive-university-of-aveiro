package lab3;

import java.util.*;

public class JGaloGame implements JGaloInterface{
    private char[][] matriz;
    private char jogador;
    private char vencedor;
    private int nJogadas;

    public JGaloGame(){
        this.matriz = new char[3][3];
        this.jogador = 'X';
        this.vencedor = ' ';
        this.nJogadas = 0;
    }

    @Override
    public char getActualPlayer(){
        return jogador;
    }

    @Override
    public boolean setJogada(int lin, int col){
        matriz[lin-1][col-1] = jogador;
        if(jogador == 'X'){
            jogador = 'O';
        } else{
            jogador = 'X';
        }
        nJogadas++;
        return true;
    }

    private ArrayList<String> getLinhas(){
        ArrayList<String> linhas = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            linhas.add(String.valueOf(matriz[i]));
        }
        for (int lin = 0; lin < matriz.length; lin++) {
            String f = "";
            for (int col = 0; col < matriz.length; col++) {
                f = f + matriz[col][lin];
            }
            linhas.add(f);
        }
        linhas.add(""+ matriz[0][0]+ matriz[1][1]+ matriz[2][2]);
        linhas.add(""+ matriz[0][2]+ matriz[1][1]+ matriz[2][0]);
        return linhas;
    }

    @Override
    public boolean isFinished(){
        ArrayList<String> linhas = getLinhas();
        for(String l : linhas){
            if(l.equals("XXX")){
                vencedor = 'X';
                return true;
            }
            if(l.equals("OOO")){
                vencedor = 'O';
                return true;
            }
        }
        //em caso de empate, o vencedor fica == ' '

        if(nJogadas == 9){
            return true;
        }
        return false;
    }

    @Override
    public char checkResult(){
        return vencedor;
    }
}
