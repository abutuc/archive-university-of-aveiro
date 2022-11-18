public class Manager extends EmployeeDecorator {

    public Manager(EmployeeInterface e) {
        super(e);
    }

    @Override
    public void work() {
        e.work();
        System.out.print(" as Manager");
    }

    public void managing() {
        System.out.print(" -- managing");
    }
}
