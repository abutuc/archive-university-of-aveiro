package ua.tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue() {

        when(market.lookUpPrice("Google")).thenReturn(100.0);
        when(market.lookUpPrice("Facebook")).thenReturn(200.0);

        portfolio.addStock(new Stock("Google", 10));
        portfolio.addStock(new Stock("Facebook", 20));

        Double total = portfolio.getTotalValue();
        assertEquals(5000.0, total);
        assertThat(total, equalTo(5000.0));

        verify(market, times(2)).lookUpPrice(anyString());

    }
}