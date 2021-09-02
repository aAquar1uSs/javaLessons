package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tempreture")
public class Tempreture {

    @XmlAttribute(name = "measure")
    private String measure;

    @XmlSchemaType(name = "positiveInteger")
    private int value;

    /**
     * @param measure
     * Set the measure for temperature.
     */
    public void setMeasureForTemperature(String measure) {
        this.measure = measure;
    }

    /**
     * Get the measure for temperature.
     * @return measure
     */
    public String getMeasureForTemperature() {
        return measure;
    }

    /**
     * @param value
     * Set the value of temperature.
     */
    public void setAverageTemperature(int value) {
        this.value = value;
    }

    /**
     * Get the value of temperature.
     * @return value
     */
    public int getAverageTemperature() {
        return value;
    }

}
