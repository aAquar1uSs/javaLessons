package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.XmlElement;

public class VisualParameters {

    @XmlElement(name = "temColour")
    private String stemColour;

    @XmlElement(name = "leafColour")
    private String leafColour;

    @XmlElement(name = "aveLenFlower")
    private AveLenFlower aveLenFlower;

    /**
     * @param stemColour
     * Set the value of stem colour.
     */
    public void setStemColour(String stemColour) {
        this.stemColour = stemColour;
    }

    /**
     * Get the value of stem colour.
     * @return stemColour
     */
    public String getStemColour() {
        return stemColour;
    }

    /**
     * @param leafColour
     * Set the value of leaf colour.
     */
    public void setLeafColour(String leafColour) {
        this.leafColour = leafColour;
    }

    /**
     * Get the value of leaf colour.
     * @return leaf colour
     */
    public String getLeafColour() {
        return leafColour;
    }

    /**
     * @param aveLenFlower
     * Set the value of length flower {@link AveLenFlower}.
     */
    public void setAveLenFlower(AveLenFlower aveLenFlower) {
        this.aveLenFlower = aveLenFlower;
    }

    /**
     *  Get the value of length flower {@link AveLenFlower}.
     * @return aveLenFlower
     */
    public AveLenFlower getAveLenFlower() {
        return aveLenFlower;
    }
}
