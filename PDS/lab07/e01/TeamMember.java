public class TeamMember extends EmployeeDecorator {
    
    public TeamMember(EmployeeInterface e) {
        super(e);
    }

    @Override
    public void work() {
        e.work();
        System.out.print(" as Team Member");
    }

    public void colaborate() {
        System.out.print(" -- colaborating");
    }
}
