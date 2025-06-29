package ua.tqs;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
    TqsStack stack;

    @BeforeEach
    void setUp() {
        stack = new TqsStack();
    }

    @DisplayName("a) A stack is empty on construction.")
    @Test
    void testEmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @DisplayName("b) A stack has size 0 on construction.")
    @Test
    void testEmptyStackSize() {
        assertEquals(0, stack.size());
    }

    @DisplayName("c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void testPush() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertFalse(stack.isEmpty());
        assertEquals(3, stack.size());
    }

    @DisplayName("d) If one pushes x then pops, the value popped is x.")
    @Test
    void testPushPop() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @DisplayName("e) If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void testPushPeek() {
        stack.push(1);
        assertEquals(1, stack.peek());
        assertEquals(1, stack.size());
    }

    @DisplayName("f) If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void testPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @DisplayName("g) Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void testPopEmptyStack() {
        assertThrows(java.util.NoSuchElementException.class, stack::pop);
    }

    @DisplayName("h) Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void testPeekEmptyStack() {
        assertThrows(java.util.NoSuchElementException.class, stack::peek);
    }











}