import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Product {
    private static int IDcounter=1;
    private int ID;
    private String description;
    private double startingPrice;
    private double price;
    private double timeOfAuction;
    private double timeLeftOfAuction;
    private long startOfAuction;
    private State state;
    private List<Observer> observers = new ArrayList<>();



    public Product(String description, double startingPrice){
        this.description = description;
        this.startingPrice = startingPrice;
        this.price = startingPrice;
        this.observers = new ArrayList<>();
        this.ID = IDcounter++;
        this.state = null;
        this.timeOfAuction = 0;
        this.timeLeftOfAuction = 0;
    }

    public void notifyObservers(Product product, int flag){
        for (Observer obs: observers){
            obs.update(this, flag);
        }
    }

    public void attach(Observer o){
        observers.add(o);
    }


    public void setState(State state){
        this.state = state;
        notifyObservers(this, 3);
    }
    public boolean setPrice(double price) {
        if (this.price >= price){
            return false;
        }
        else {
            this.price = price;
            notifyObservers(this, 1);
            return true;
        }
    }

    public void startOfAuction(double timeOfAuction) {
        this.timeOfAuction = timeOfAuction;
        this.timeLeftOfAuction = timeOfAuction;
        startOfAuction = new Date().getTime();
        notifyObservers(this, 2);
    }

    public void setTimeLeftOfAuction(double timeLeftOfAuction){
        this.timeLeftOfAuction = timeLeftOfAuction;
        notifyObservers(this, 2);
    }

    public int getID() {
        return ID;
    }
    public String getDescription() {
        return description;
    }
    public double getStartingPrice() {
        return startingPrice;
    }

    public double getTimeOfAuction() {
        return timeOfAuction;
    }
    
    public double getTimeLeftOfAuction() {
        return timeLeftOfAuction;
    }

    public double getPrice() {
        return price;
    }

    public State getState(){
        return state;
    }

    public long getStartOfAuction() {
        return startOfAuction;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public String toString() {
        return "Product " + ID + "\nDescription: " + description + "\nStarting price: " + startingPrice + "\nPrice: " + price + "\nState: " + state; 
    }
}