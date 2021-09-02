package com.epam.rd.java.basic.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

    private final int numberOfPeople;
    private final int k;

    public Part2(int n, int k) {
        this.numberOfPeople = n;
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void fillListOfPeople(final List<Integer> list) {
        for (int i = 0; i < getNumberOfPeople(); i++) {
            list.add(i);
        }
    }

    public String outputList(final List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(n->sb.append(n)
                .append(System.lineSeparator()));

        return sb.toString();
    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long startTime = System.currentTimeMillis();
        int removeIndex = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.size() == 1) {
                break;
            }
            removeIndex += (k - 1);
            while (removeIndex >= list.size()) {
                removeIndex = removeIndex - list.size();
            }
            list.remove(removeIndex);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long startTime = System.currentTimeMillis();
        int countIndex = 0;
        Iterator<Integer> iterator = list.iterator();
        while (list.size() > 1) {
            if (iterator.hasNext()) {
                iterator.next();
                countIndex++;
                if (countIndex == k) {
                    iterator.remove();
                    countIndex = 0;
                }
            } else {
                iterator = list.iterator();
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2(10000, 4);
        List<Integer> listOfPeople = new LinkedList<>();
        List<Integer> arrayOfPeople = new ArrayList<>();

        part2.fillListOfPeople(listOfPeople);
        part2.fillListOfPeople(arrayOfPeople);

        System.out.println("ArrayList#Index: " + removeByIndex(arrayOfPeople, part2.getK()) + "ms");
        System.out.println("LinkedList#Index: " + removeByIndex(listOfPeople, part2.getK()) + "ms");
        System.out.println("ArrayList#Iterator: " + removeByIterator(listOfPeople, part2.getK()) + "ms");
        System.out.println("LinkedList#Iterator: " + removeByIterator(listOfPeople, part2.getK()) + "ms");

    }
}
