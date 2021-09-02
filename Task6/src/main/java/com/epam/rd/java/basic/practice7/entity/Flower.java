package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flower", propOrder = { "name", "soil", "origin", "visualParameters","growingTips",
        "multiplying" })
public class Flower {
    /**
     * Name of a flower.
     */
    @XmlElement(name = "name", required = true)
    private String name;

    /**
     * Name of the soil in which the flower is grown.
     */
    @XmlElement(name = "soil", required = true)
    private String soil;

    /**
     * The origin of the flower.
     */
    @XmlElement(name = "origin",required = true)
    private String origin;

    @XmlElement(name = "visualParameters",required = true)
    private VisualParameters visualParameters;

    @XmlElement(name = "growingTips",required = true)
    private GrowingTips growingTips;

    @XmlElement(name = "multiplying",required = true)
    private String multiplying;

    /**
     * Return the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * Set the value of the name properties.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of the soil properties.
     * @return soil
     */
    public String getSoil() {
        return soil;
    }

    /**
     * @param soil
     *  Set the value of the soil
     */
    public void setSoil(String soil) {
        this.soil = soil;
    }

    /**
     * Get the value of origin properties.
     * @return origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin
     * Set the value of origin properties.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Get the value of the visualParametrs properties.
     * @return possible object is {@link VisualParameters }
     *
     */
    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    /**
     * @param parameters allowed object is {@link VisualParameters}
     *  Set the value of the visualParametrs property.
     */
    public void setVisualParameters(VisualParameters parameters) {
        this.visualParameters = parameters;
    }

    /**
     * @param gTips allowed object is {@link GrowingTips}
     *  Set the value of the visualParametrs property.
     */
    public void setGrowingTips(GrowingTips gTips) {
        this.growingTips = gTips;
    }

    /**
     * Get the value of the growingTips property.
     *
     * @return possible object is {@link GrowingTips }
     *
     */
    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    /**
     * Get the value of the multiplying property.
     * @return multiplying
     */
    public String getMultiplying() {
        return multiplying;
    }

    /**
     * @param multiplying
     * Set the value of multiplying property.
     */
    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

}
