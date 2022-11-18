
class DisponivelState extends State {

    public DisponivelState(Livro livro) {
        super(livro);
    }

    @Override
    public boolean regista() {
        // Do nothing
        return false;
    }

    @Override
    public boolean requisita() {
        livro.setState(new EmprestadoState(livro));
        return true;
    }

    @Override
    public boolean devolve() {
        // Do nothing
        return false;
    }

    @Override
    public boolean reserva() {
        livro.setState(new ReservadoState(livro));
        return true;
    }

    @Override
    public boolean cancela() {
        // Do nothing
        return false;
    }

    @Override
    public String toString() {
        return "Disponível";
    }
}
