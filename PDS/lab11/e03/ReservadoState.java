
class ReservadoState extends State {

    public ReservadoState(Livro livro) {
        super(livro);
    }

    @Override
    public boolean regista() {
        // Do nothing
        return false;
    }

    @Override
    public boolean requisita() {
        // Do nothing
        return false;
    }

    @Override
    public boolean devolve() {
        // Do nothing
        return false;
    }

    @Override
    public boolean reserva() {
        // Do nothing
        return false;
    }

    @Override
    public boolean cancela() {
        livro.setState(new DisponivelState(livro));
        return true;
    }    

    @Override
    public String toString() {
        return "Reservado";
    }
}
