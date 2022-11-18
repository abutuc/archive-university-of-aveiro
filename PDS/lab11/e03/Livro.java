
public class Livro {
    private String titulo;
    private String isbn;
    private int ano;
    private String autor;
    private State state;

    public Livro(String titulo, String isbn, int ano, String autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.ano = ano;
        this.autor = autor;
        this.state = new InventarioState(this);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAno() {
        return ano;
    }

    public String getAutor() {
        return autor;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean regista() {
        return state.regista();
    }

    public boolean requisita() {
        return state.requisita();
    }

    public boolean devolve() {
        return state.devolve();
    }

    public boolean reserva() {
        return state.reserva();
    }

    public boolean cancela() {
        return state.cancela();
    }
}
