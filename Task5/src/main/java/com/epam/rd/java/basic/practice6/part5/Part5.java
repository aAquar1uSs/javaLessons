package com.epam.rd.java.basic.practice6.part5;

public class Part5 {

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(8);
        tree.add(6);
        tree.add(2);
        tree.add(5);
        tree.remove(6);
        tree.print();
    }

}
