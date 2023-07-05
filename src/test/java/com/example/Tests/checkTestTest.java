package com.example.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class checkTestTest {

    private checkTest checkTest;
    @BeforeEach
    void setUp() {
        checkTest = new checkTest();
    }

    @Test
    void getLastName() {
        checkTest.setFirstName("gerber");
        assertEquals("gerber",checkTest.getFirstName());
    }

    @Test
    void getFirstName() {
        checkTest.setFirstName("yuval");
        assertEquals("yuval",checkTest.getFirstName());
    }

    @Test
    void getFullName() {
        checkTest.setFirstName("bart");
        checkTest.setLastName("simpson");
        assertEquals("BART SIMPSON",checkTest.getFullName());
    }
}