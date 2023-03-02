public class Client extends Observer{
    private String name;

    public Client(String name){
        this.name = name;
    }

    public void bidOnProduct(Product product, double bidValue){
        if (!product.getObservers().contains(this)){
            product.attach(this);
        }

        if (product.getTimeLeftOfAuction() > 0 && productsInAuction.contains(product)){
            if(!product.setPrice(bidValue)){
                System.out.println(name + ": Auction of Product " + product.getID() + " refused my bid :(");
            }
        }
        else {
            System.out.println(this.name + ": Product " + product.getID() + " is not available for auction bids.");
        }
    }

    public void update(Product product, int flag){
        int productID = product.getID();
        switch(flag){
            case 1:
                System.out.println(this.name + ": Product " + productID + " has a new bid set at " + product.getPrice() + "!");
                break;
  
            case 2:
                double timeLeft = product.getTimeLeftOfAuction();
                if (timeLeft > 0){
                    System.out.println(this.name + ": Product " + productID + " auction has " + timeLeft + " seconds left!");
                }
                else {
                    System.out.println(this.name + ": Product" + productID + " auction has ended." );
                }
                break;
            
            case 3:
                State productState = product.getState();
                if (productState.equals(State.AUCTION)){
                    System.out.println(this.name + ": Product " + productID + " is now in auction.");
                }
                else if (productState.equals(State.SOLD)){
                    System.out.println(this.name + ": Product " + productID + " is now in sales.");
                }
                else if (productState.equals(State.STOCK)){
                    System.out.println(this.name + ": Product " + productID + " is now in stock.");
                }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
