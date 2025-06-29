/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;

    private BoundedSetOfNaturals setD;
    private BoundedSetOfNaturals setE;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});

        setD = new BoundedSetOfNaturals(3);
        setE = new BoundedSetOfNaturals(3);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = setE = null;
    }

    @DisplayName("add element to set")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        assertThrows(IllegalArgumentException.class, () -> setB.add(11));

        assertThrows(IllegalArgumentException.class, () -> setB.add(10));

        assertThrows(IllegalArgumentException.class, () -> setB.add(-1));
    }

    @DisplayName("add from bad array")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{-10};
        int[] elems2 = new int[]{10, 10};
        int[] elems3 = new int[]{10, 20, 30, 40};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));

        assertThrows(IllegalArgumentException.class, () -> setD.add(elems2));

        assertThrows(IllegalArgumentException.class, () -> setE.add(elems3));
    }

    @DisplayName("test intersects")
    @Test
    public void testIntersects(){
        assertTrue(setB.intersects(setC));
        assertTrue(setC.intersects(setB));
        assertFalse(setA.intersects(setC));
    }

    @DisplayName("test intersection")
    @Test
    public void testIntersection(){
        BoundedSetOfNaturals result = setB.intersection(setC);
        assertEquals(2, result.size());
        assertTrue(result.contains(50));
        assertTrue(result.contains(60));
    }

}
