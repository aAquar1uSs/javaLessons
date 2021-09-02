package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aveLenFlower")
public class AveLenFlower {

    @XmlAttribute(name = "measure",required = true)
    private String measure;

    @XmlSchemaType(name = "positiveInteger")
    private int value;

    /**
     *  @param measure
     * Set the measure for aveLenFlower.
     */
    public void setMeasureForLenFlower(String measure) {
        this.measure = measure;
    }

    /**
     * Get measure for AveLenFlower.
     * @return measure
     */
    public String getMeasureForLenFlower() {
        return measure;
    }

    /**
     * @param value
     * Set the value of measure for AveLenFlower.
     */
    public void setValueForLenFlower(int value) {
        this.value = value;
    }

    /**
     * Get the value of measure.
     * @return value
     */
    public int getValueForLenFlower() {
        return value;
    }


}
