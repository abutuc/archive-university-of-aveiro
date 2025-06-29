package ua.tqs;
import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {
    List <Stock> stocks = new ArrayList <> ();
    IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double total = 0.0;
        for (Stock s : stocks) {
            total += s.getQuantity() * stockmarket.lookUpPrice(s.getLabel());
        }
        return total;
    }
}
