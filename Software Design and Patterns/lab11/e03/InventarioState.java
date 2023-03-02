
class InventarioState extends State {

    public InventarioState(Livro livro) {
        super(livro);
    }

    @Override
    public boolean regista() {
        livro.setState(new DisponivelState(livro));
        return true;
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
        // Do nothing
        return false;
    }

    @Override
    public String toString() {
        return "Inventário";
    }
}
