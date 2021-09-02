package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Group", propOrder = {
        "tempreture",
        "lighting",
        "watering"
})
public class GrowingTips {

    @XmlElement(required = true)
    private Tempreture tempreture;

    @XmlElement(required = true)
    private Lighting lighting;

    @XmlElement(required = true)
    private Watering watering;

    /**
     * @param temp
     * Set the value of the tempreture properties {@link Tempreture}.
     */
    public void setTempreture(Tempreture temp) {
        this.tempreture = temp;
    }

    /**
     * Get the value of the temprature properties.
     * @return possible object temprature {@link Tempreture}.
     */
    public Tempreture getTempreture() {
        return tempreture;
    }

    /**
     * @param light
     * Set the value of the lighting properties {@link Lighting}.
     */
    public void setLighting(Lighting light) {
        this.lighting = light;
    }

    /**
     * Get the value of the lighting properties {@link Lighting}.
     * @return lighting
     */
    public Lighting  getLighting() {
        return lighting;
    }

    /**
     * @param watering
     * Set the value of the watering properties {@link Watering}.
     */
    public void setWatering (Watering watering) {
        this.watering = watering;
    }

    /**
     * Get the value of the watering properties {@link Watering}.
     * @return watering
     */
    public Watering getWatering() {
        return watering;
    }
}
