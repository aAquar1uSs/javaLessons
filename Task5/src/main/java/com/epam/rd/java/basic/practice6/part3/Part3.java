package com.epam.rd.java.basic.practice6.part3;

public class Part3 {
    
    public static void main(String[] args) {
        Parking parking = new Parking(5);
        System.out.println("parking.arrive(0)," + parking.arrive(0));
        System.out.println("parking.arrive(1)," + parking.arrive(1));
        System.out.println("parking.arrive(2)," + parking.arrive(2));
        System.out.println("parking.arrive(3)," + parking.arrive(3));
        System.out.println("parking.arrive(4)," + parking.arrive(4));
        System.out.println("parking.depart(2)," + parking.depart(2));

        System.out.print("Result: ");
        parking.print();

    }

}
