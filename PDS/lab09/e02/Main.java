
public class Main {

    public static void main(String[] args) {

        String[] requests = {
                "veggie burger",
                "Pasta Carbonara",
                "PLAIN pizza, no toppings!",
                "sushi nigiri and sashimi",
                "salad with tuna",
                "strawberry ice cream and waffles dessert" };
        int[] timeTaken = { 19, 14, 7, 14, 10, 17 };

        Chef cookers = new SushiChef().setNext(
                               new PastaChef().setNext(
                                       new BurgerChef().setNext(
                                               new PizzaChef().setNext(
                                                       new DessertChef()))));
        
        for (int i = 0; i < requests.length; i++) {
            System.out.println("Can I please get a " + requests[i] + "?");
            cookers.cook(requests[i], timeTaken[i]);
            System.out.println();
        }
    }
}
