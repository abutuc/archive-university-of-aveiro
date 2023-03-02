import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Client> clients = new ArrayList<>();
        for (int i=1; i<=3; i++){
            products.add(new Product("Product " + i, i*5));
        }

        
        for (int i=1; i<=3; i++){
            clients.add(new Client("Client " + i));
        }

        Manager manager = new Manager("Manager");
        
        // Check if everything is fine (and testing toStrings):
        /*
        for (Product product: products){
            System.out.println("--------------------------");
            System.out.println(product);
            System.out.println("--------------------------");
        }

        for (Client client: clients){
            System.out.println("--------------------------");
            System.out.println(client);
            System.out.println("--------------------------");
        }

        System.out.println("--------------------------");
        System.out.println(manager);
        System.out.println("--------------------------");
        */ // It worked but it's to spammy.

        for (Product product: products){
            manager.registerProduct(product);
        }


        // Normal Flow

        System.out.println("--------------------------");
        System.out.println("Test Normal Flow");
        System.out.println("--------------------------");
        manager.setProductInAuction(products.get(0), 10);
        manager.auctionSilence(1, products.get(0));
        clients.get(0).bidOnProduct(products.get(0), 10);
        manager.auctionSilence(2, products.get(0));
        clients.get(1).bidOnProduct(products.get(0), 15);
        manager.auctionSilence(3, products.get(0));
        clients.get(2).bidOnProduct(products.get(0), 20);
        manager.auctionSilence(5, products.get(0));
        clients.get(0).bidOnProduct(products.get(0), 25);


        // Lower bid does nothing
        System.out.println("--------------------------");
        System.out.println("Test Lower Bids");
        System.out.println("--------------------------");
        manager.setProductInAuction(products.get(1), 5);
        clients.get(1).bidOnProduct(products.get(1),  15);
        clients.get(2).bidOnProduct(products.get(1), 10);
        manager.auctionSilence(5, products.get(1));


        // Test Enormous Auction 3 Products, 3 Clients, 1 Manager
        System.out.println("--------------------------");
        System.out.println("Test Enormous Auction");
        System.out.println("--------------------------");
        manager.setProductInAuction(products.get(0), 10);
        manager.auctionSilence(2, products.get(0));
        clients.get(0).bidOnProduct(products.get(0), 20);
        manager.auctionSilence(1, products.get(0));
        clients.get(2).bidOnProduct(products.get(0), 30);
        clients.get(1).bidOnProduct(products.get(0), 31);
        manager.auctionSilence(3, products.get(0));
        clients.get(0).bidOnProduct(products.get(0), 100);
        manager.auctionSilence(4, products.get(0));
        manager.setProductInAuction(products.get(1), 5);
        clients.get(2).bidOnProduct(products.get(1), 12);
        manager.auctionSilence(1, products.get(1));
        clients.get(0).bidOnProduct(products.get(1), 15);
        manager.auctionSilence(4, products.get(1));
        manager.setProductInAuction(products.get(2), 5);
        manager.auctionSilence(5, products.get(2));


    }
}
