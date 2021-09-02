package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "flower" })
@XmlRootElement(name = "flowers")
public class Flowers {

    @XmlElement(required = true)
    private final List<Flower> flowerList;

    public Flowers() {
        flowerList = new ArrayList<>();
    }

    /**
     * Return list that consists of objects of flowers {@link Flower}.
     * @return List<Flower>
     */
    public final List<Flower> getFlowerList() {
        return flowerList;
    }

    /**
     * @param flower
     * Add object of flower {@link Flower} to list.
     */
    public final void addFlower(Flower flower) {
        flowerList.add(flower);
    }


}
