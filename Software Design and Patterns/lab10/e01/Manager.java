import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Manager extends Observer{
    private String name;
    private ArrayList<Product> productsInStock;
    private ArrayList<Product> productsSold;

    public Manager(String name){
        this.name = name;
        productsInStock = new ArrayList<>();
        productsSold = new ArrayList<>();
    }

    public void registerProduct(Product product){
        productsInStock.add(product);
        product.setState(State.STOCK);
        product.attach(this);
    }

    public void setProductInAuction(Product product, double timeOfAuction){
        System.out.println(name + ": Product " + product.getID() + " is in auction.");
        product.setState(State.AUCTION);
        product.startOfAuction(timeOfAuction);
        productsInStock.remove(product);
        productsInAuction.add(product);
    }

    public void setProductInStock(Product product){
        productsInAuction.remove(product);
        productsInStock.add(product);
        product.setState(State.STOCK);
    }

    public void setProductInSales(Product product){
        productsInAuction.remove(product);
        productsSold.add(product);
        product.setState(State.SOLD);
    }

    public void auctionSilence(long timeout, Product product) throws InterruptedException{
        TimeUnit.SECONDS.sleep(timeout);
        long timeStamp = new Date().getTime();
        long auctionTimeLeft = (long)product.getTimeOfAuction() - ((timeStamp - product.getStartOfAuction())/1000);
        product.setTimeLeftOfAuction(auctionTimeLeft);
    }

    public void update(Product product, int flag){
        switch(flag){
            case 1:
                System.out.println(name + ": Product " + product.getID() + " has a new bid of " + product.getPrice());
                break;
            
            case 2:
                double timeLeft = product.getTimeLeftOfAuction();
                if (timeLeft > 0){
                    System.out.println(this.name + ": Product " + product.getID() + " auction has " + timeLeft + " seconds left!");
                }
                else {
                    System.out.println(this.name + ": Product" + product.getID() + " auction has ended." );
                    if (product.getStartingPrice() == product.getPrice()){
                        System.out.println(this.name + ": Product " + product.getID() + " is back to Stock.");
                        setProductInStock(product);
                    }
                    else {
                        System.out.println(name + ": Product " + product.getID() + " has been sold!");
                        setProductInSales(product);
                    }
                }
                break;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
