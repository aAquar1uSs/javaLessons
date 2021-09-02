package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lighting")
public class Lighting {

    @XmlAttribute(name = "lighting", required = true)
    private String lightRequiring;

    /**
     * @param lighting
     * Set the value of the lighting requiring.
     */
    public void setLightRequiring(String lighting) {
        this.lightRequiring = lighting;
    }

    /**
     * Get the value of the requiring for light.
     * @return lightRequiring
     */
    public String getLightRequiring() {
        return lightRequiring;
    }


}
