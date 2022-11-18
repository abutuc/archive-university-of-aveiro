
class EmprestadoState extends State {

    public EmprestadoState(Livro livro) {
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
        livro.setState(new DisponivelState(livro));
        return true;
    }

    @Override
    public boolean reserva() {
        // Do nothing
        return false;
    }

    @Override
    public boolean cancela() {
        // Do nothing
        return false;
    }

    @Override
    public String toString() {
        return "Emprestado";
    }
}
