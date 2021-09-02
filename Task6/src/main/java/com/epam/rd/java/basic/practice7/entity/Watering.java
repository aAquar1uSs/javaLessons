package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "watering")
public class Watering {
    @XmlAttribute(name = "measure")
    private String measure;

    @XmlSchemaType(name = "positiveInteger")
    private int value;

    /**
     * @param measure
     * Set the value of measure for watering.
     */
    public void setMeasureForWatering(String measure) {
        this.measure = measure;
    }

    /**
     * Get the value of measure for watering.
     * @return measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @param value
     * Set the value for watering.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the value for watering.
     * @return value
     */
    public int getValue() {
        return value;
    }
}
