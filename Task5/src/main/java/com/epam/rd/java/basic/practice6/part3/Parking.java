package com.epam.rd.java.basic.practice6.part3;

import java.util.Arrays;

public class Parking {

    private final int[] parkingPlaces;

    public Parking(int capacity) {
        this.parkingPlaces = new int[capacity];
        fillArrayParkingPlace();
    }

    public int[] getArray() {
        return parkingPlaces;
    }


    public void fillArrayParkingPlace() {
        Arrays.fill(parkingPlaces, 0);
    }

    public boolean arrive(int k) {
        if (k > parkingPlaces.length - 1) {
            throw new IllegalArgumentException();
        }

        if (parkingPlaces[k] == 0) {
            parkingPlaces[k] = 1;
            return true;
        }

        for (int i = k; i < parkingPlaces.length; i++) {
            if (parkingPlaces[i] == 0) {
                parkingPlaces[i] = 1;
                return true;
            }
        }

        for (int i = 0; i < k; i++) {
            if (parkingPlaces[i] == 0) {
                parkingPlaces[i] = 1;
                return true;
            }
        }

        return false;
    }

    public boolean depart(int k) {
        if (k > parkingPlaces.length - 1) {
            throw new IllegalArgumentException();
        }

        if (parkingPlaces[k] == 1) {
            parkingPlaces[k] = 0;
            return true;
        }

        return false;
    }

    public void print() {
        for (int parkingPlace : parkingPlaces) {
            System.out.print(parkingPlace);
        }
    }
}
