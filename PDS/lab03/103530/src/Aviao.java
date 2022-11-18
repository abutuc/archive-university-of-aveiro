import java.lang.Math;

public class Aviao {
    // Os conjunto de lugares serão matrizes de inteiros cujas linhas correspondem às filas e as colunas correspondem aos lugares.
    private int[][] lugares_executivos;
    private int exec_rows;
    private int exec_cols;

    private int[][] lugares_turisticos;
    private int tur_rows;
    private int tur_cols;

    private int lugares_executivos_disponiveis;
    private int lugares_turisticos_disponiveis;


    // Class Constructer if plane has executive seats
    public Aviao(int[][] lugares_executivos, int[][] lugares_turisticos){
        this.lugares_executivos = lugares_executivos;
        exec_rows = this.lugares_executivos.length;
        exec_cols = this.lugares_executivos[0].length;

        this.lugares_turisticos = lugares_turisticos;
        tur_rows = this.lugares_turisticos.length;
        tur_cols = this.lugares_turisticos[0].length;

        lugares_executivos_disponiveis = exec_rows * exec_cols;
        lugares_turisticos_disponiveis = tur_rows * tur_cols;
    }

    public Aviao(int[][] lugares_turisticos){
        this.lugares_executivos = null;
        exec_rows = 0;
        exec_cols = 0;

        this.lugares_turisticos = lugares_turisticos;
        tur_rows = this.lugares_turisticos.length;
        tur_cols = this.lugares_turisticos[0].length;

        lugares_executivos_disponiveis = exec_cols * exec_rows;
        lugares_turisticos_disponiveis = tur_rows * tur_cols;
    }


    // Attributes setters
    public void setLugares_executivos(int[][] lugares_executivos) {
        this.lugares_executivos = lugares_executivos;
    }
    public void setLugares_executivos_disponiveis(int lugares_executivos_disponiveis) {
        this.lugares_executivos_disponiveis = lugares_executivos_disponiveis;
    }
    public void setLugares_turisticos(int[][] lugares_turisticos) {
        this.lugares_turisticos = lugares_turisticos;
    }
    public void setLugares_turisticos_disponiveis(int lugares_turisticos_disponiveis) {
        this.lugares_turisticos_disponiveis = lugares_turisticos_disponiveis;
    }
    public void setExec_cols(int exec_cols) {
        this.exec_cols = exec_cols;
    }
    public void setExec_rows(int exec_rows) {
        this.exec_rows = exec_rows;
    }
    public void setTur_cols(int tur_cols) {
        this.tur_cols = tur_cols;
    }
    public void setTur_rows(int tur_rows) {
        this.tur_rows = tur_rows;
    }

    // Attributes getters
    public int[][] getLugares_executivos() {
        return lugares_executivos;
    }
    public int getLugares_executivos_disponiveis() {
        return lugares_executivos_disponiveis;
    }
    public int[][] getLugares_turisticos() {
        return lugares_turisticos;
    }
    public int getLugares_turisticos_disponiveis() {
        return lugares_turisticos_disponiveis;
    }
    public int getExec_cols() {
        return exec_cols;
    }
    public int getExec_rows() {
        return exec_rows;
    }
    public int getTur_cols() {
        return tur_cols;
    }
    public int getTur_rows() {
        return tur_rows;
    }


    // Method to update value the seat in either plane's section.
    // Returns true if updateSeat was sucessful, otherwise, it returns false.
    public boolean updateSeat(int row, int col, int content, String section){
        if (section.equals("Executivo")){
            lugares_executivos[row][col] = content;
            lugares_executivos_disponiveis--;
            return true;
        }
        else if (section.equals("Turístico")){
            lugares_turisticos[row][col] = content;
            lugares_turisticos_disponiveis--;
            return true;
        }
        return false;

    }

    // Method to retrieve value of seat in plane's section.
    // Return intended value if sucessful, otherwise, returns -1.
    public int getSeatValue(int row, int col, String section){
        if (section.equals("Executivo")){
            return lugares_executivos[row][col];
        }
        else if (section.equals("Turístico")){
            return lugares_turisticos[row][col];
        }
        return -1;
    }
    // Prints all the seats and its reservations.
    @Override
    public String toString() {
        char cur_char = 'A';
        String str = " ";
        for (int i = 1; i <= exec_rows + tur_rows; i++){
            if (i > 10){
                str += "  " + i;
            }
            else {
                str += "   " + i;
            }
        } 
        str += "\n";
        for (int f = 0; f < Math.max(exec_cols, tur_cols); f++){
            for (int s = 0; s <= exec_rows + tur_rows; s++){
                if (s == 0){
                    str += cur_char;
                    cur_char = (char) ((int)cur_char + 1);          // goes to next char
                }
                else {
                    if (this.lugares_executivos == null){
                        str += "    " + lugares_turisticos[s-1][f];
                    }
                    else {
                        if (s <= exec_rows){
                            if (f >= exec_cols){
                                str += "    ";
                            }
                            else {
                                str += "   " + lugares_executivos[s-1][f];
                            }
                        }
                        else {
                            if (f >= tur_cols){
                                str += "    ";
                            }
                            else {
                                str += "   " + lugares_turisticos[s-exec_rows-1][f];
                            }
                        }
                    }
                }
            }
            str += "\n";
        }

        return str;
    }
    
}
