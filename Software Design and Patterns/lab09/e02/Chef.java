
public abstract class Chef {

    private Chef next;
    private String keyword;

    public Chef(String keyword) {
        this.keyword = keyword;
    }

    public Chef setNext(Chef next) {
        this.next = next;
        return this;
    }

    public void cook(String request, int timeTaken) {
        if (canCook(request, keyword)) {
            System.out.println(this.getClass().getSimpleName()+": Starting to cook "+request+". Out in "+timeTaken+" minutes!");
        } else {
            System.out.println(this.getClass().getSimpleName()+": I can't cook that.");
            if (next != null) {
                next.cook(request, timeTaken);
            } else {
                System.out.println("We're sorry but that request can't be satisfied by our service!");
            }
        }
    }

    public boolean canCook(String request, String keyword) {
        return request.toLowerCase().contains(keyword.toLowerCase());
    }
}
