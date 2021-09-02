package com.epam.rd.java.basic.practice6.part3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingTest {
    private Parking parking;
    private StringBuilder actual;

    @Before
    public void setUp() {
        parking = new Parking(5);
        actual = new StringBuilder();
    }

    @Test
    public void shouldFillParkingPlace() {
        for(int i = 0;i < parking.getArray().length;i++) {
            actual.append(parking.getArray()[i]);
        }
        Assert.assertEquals("00000",actual.toString());
    }

    @Test
    public void shouldCreateParkingPlaces() {
        int actual = parking.getArray().length;
        int expected = 5;

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldDepartCar() {
        parking.arrive(0);
        parking.arrive(1);
        parking.arrive(2);
        parking.arrive(3);
        parking.arrive(4);
        parking.depart(2);

        for(int i = 0;i < parking.getArray().length;i++) {
            actual.append(parking.getArray()[i]);
        }
        Assert.assertEquals("11011",actual.toString());
    }

    @Test
    public void shouldArriveCar() {

        parking.arrive(0);
        parking.arrive(3);

        for(int i = 0;i < parking.getArray().length;i++) {
            actual.append(parking.getArray()[i]);
        }
        Assert.assertEquals("10010",actual.toString());
    }


}