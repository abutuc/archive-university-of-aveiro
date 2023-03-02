public abstract class EmployeeDecorator implements EmployeeInterface {
    protected EmployeeInterface e;
    
    public EmployeeDecorator(EmployeeInterface e) {
        this.e = e;
    }

    @Override
    public void start(Date date) {
        e.start(date);
    }

    @Override
    public void terminate(Date date) {
        e.terminate(date);
    }

    @Override
    public void work() {
        e.work();
    }
}
