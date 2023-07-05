package com.example.Tests;


import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkTest {

    @Test
    public void testAddition() {
        int result = 2 + 2;
        assertEquals(4, result);
    }
}