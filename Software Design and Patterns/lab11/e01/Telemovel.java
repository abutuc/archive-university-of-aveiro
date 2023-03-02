
public class Telemovel {
    private final String processador;
    private final double preco;
    private final int memoria;
    private final int camera;

    public Telemovel(String processador, double preco, int memoria, int camera) {
        this.processador = processador;
        this.preco = preco;
        this.memoria = memoria;
        this.camera = camera;
    }

    public String getProcessador() {
        return processador;
    }

    public double getPreco() {
        return preco;
    }

    public int getMemoria() {
        return memoria;
    }

    public int getCamera() {
        return camera;
    }

    @Override
    public String toString() {
        return "Telemovel [processador="+processador+", preco="+preco+", memoria="+memoria+", camera="+camera+"]";
    }
}
