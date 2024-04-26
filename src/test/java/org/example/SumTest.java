package org.example;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SumTest {

    @Test
    public void sumPositiveNumberTest() {
        Sum sum = new Sum();
        assertEquals(sum.sum(1,2), 3);
    }
}
