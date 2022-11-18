
abstract public class State {
    protected Livro livro;
    
    public State(Livro livro) {
        this.livro = livro;
    }
        
    abstract public boolean regista();
    abstract public boolean requisita();
    abstract public boolean devolve();
    abstract public boolean reserva();
    abstract public boolean cancela();
}
