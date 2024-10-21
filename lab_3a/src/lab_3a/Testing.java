package lab_3a;


import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {
    @Test
    public void testNullStartTime() {
        try {
            new MovieTicketPriceCalculator(null, LocalTime.of(10,30,0), 5, 60);
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    public void testNullEndTime() {
        try {
            new MovieTicketPriceCalculator(LocalTime.of(10,30,0), null, 5, 60);
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    public void testInvalidStartTimes() {
        try {
            new MovieTicketPriceCalculator(LocalTime.of(10,30,0), LocalTime.of(10,20,0), 5, 60);
            fail();
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testComputeDiscount() {
        MovieTicketPriceCalculator calc = new MovieTicketPriceCalculator(LocalTime.of(0,0,0), LocalTime.of(1,0,0), 5, 60);
        assertEquals(calc.computeDiscount(61), 400);
        assertEquals(calc.computeDiscount(60), 400);
        assertEquals(calc.computeDiscount(50), 0);
        assertEquals(calc.computeDiscount(5), 300);
        assertEquals(calc.computeDiscount(4), 300);
    }

    @Test
    public void testComputePriceNoDiscount() {
        MovieTicketPriceCalculator calc = new MovieTicketPriceCalculator(LocalTime.of(0,0,0), LocalTime.of(1,0,0), 5, 60);
        assertEquals(calc.computePrice(LocalTime.of(0,0,0), 30), 2400);
        assertEquals(calc.computePrice(LocalTime.of(0,1,0), 30), 2400);
        assertEquals(calc.computePrice(LocalTime.of(1,0,0), 30), 2700);
        assertEquals(calc.computePrice(LocalTime.of(1,1,0), 30), 2700);
        assertEquals(calc.computePrice(LocalTime.of(23,59,59), 30), 2700);
    }

    @Test
    public void testComputePriceWithDiscount() {
        MovieTicketPriceCalculator calc = new MovieTicketPriceCalculator(LocalTime.of(0,0,0), LocalTime.of(1,0,0), 5, 60);
        assertEquals(calc.computePrice(LocalTime.of(0,0,0), 5), 2100);
        assertEquals(calc.computePrice(LocalTime.of(0,1,0), 5), 2100);
        assertEquals(calc.computePrice(LocalTime.of(1,0,0), 5), 2400);
        assertEquals(calc.computePrice(LocalTime.of(1,1,0), 5), 2400);
        assertEquals(calc.computePrice(LocalTime.of(23,59,59), 5), 2400);
    }
}