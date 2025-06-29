package tqs.euromillions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;



class CuponEuromillionsTest {
    CuponEuromillions cupon;

    @BeforeEach
    void setUp() {
        cupon = new CuponEuromillions();
    }

    @AfterEach
    void tearDown() {
        cupon = null;
    }

    @DisplayName("Test appendDip and countDips")
    @Test
    void testAppendDip() {
        Dip dip = new Dip(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2});
        cupon.appendDip(dip);
        assertEquals(dip, cupon.getDipByIndex(0));
        assertEquals(1, cupon.countDips());
    }

    @DisplayName("Test format")
    @Test
    void testFormat() {
        Dip dip = new Dip(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2});
        cupon.appendDip(dip);
        assertEquals("Dip #1:N[  1  2  3  4  5] S[  1  2]\n", cupon.format());
    }


}