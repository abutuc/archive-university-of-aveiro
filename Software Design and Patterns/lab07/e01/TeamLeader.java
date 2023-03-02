public class TeamLeader extends EmployeeDecorator {

    public TeamLeader(EmployeeInterface e) {
        super(e);
    }

    @Override
    public void work() {
        e.work();
        System.out.print(" as Team Leader");
    }

    public void planning() {
        System.out.print(" -- planning");
    }
}
