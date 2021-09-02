package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.entity.Flower;
import com.epam.rd.java.basic.practice7.entity.Flowers;

import java.util.Comparator;

public class Sorter {

    private Sorter() {

    }

    public static final Comparator<Flower> SORT_FLOWERS_BY_NAME = (o1, o2) -> {
        String flower1 = o1.getName();
        String flower2 = o2.getName();
        return flower1.compareTo(flower2);
    };

    public static final Comparator<Flower> SORT_FLOWERS_BY_ORIGIN = (o1, o2) -> {
        String flower1 = o1.getOrigin();
        String flower2 = o2.getOrigin();
        return flower1.compareTo(flower2);
    };

    public static final Comparator<Flower> SORT_FLOWERS_BY_FLOWERS_LEN_REVERSE_ORDER = (o1, o2) -> {
        int lenFlower1 = o1.getVisualParameters().getAveLenFlower().getValueForLenFlower();
        int lenFlower2 = o2.getVisualParameters().getAveLenFlower().getValueForLenFlower();
        if(lenFlower1 > lenFlower2){
            return -1;
        }
        else if(lenFlower1 < lenFlower2){
            return 1;
        }
        return 0;
    };

    /**
     * @param flowers
     * Method sort of list by name.
     */
    public static void sortByName(Flowers flowers) {
        flowers.getFlowerList().sort(SORT_FLOWERS_BY_NAME);
    }

    /**
     * @param flowers
     * Method sort of list by origin.
     */
    public static void sortByOrigin(Flowers flowers) {
        flowers.getFlowerList().sort(SORT_FLOWERS_BY_ORIGIN);
    }

    /**
     * @param flowers
     * Method sort of list by length flower.
     */
    public static void sortFlowersByFlowersLen(Flowers flowers) {
        flowers.getFlowerList().sort(SORT_FLOWERS_BY_FLOWERS_LEN_REVERSE_ORDER);
    }
}
