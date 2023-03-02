import java.util.ArrayList;

public abstract class Observer {
    static  ArrayList<Product> productsInAuction = new ArrayList<>();
    public abstract void update(Product p, int flag);
}
