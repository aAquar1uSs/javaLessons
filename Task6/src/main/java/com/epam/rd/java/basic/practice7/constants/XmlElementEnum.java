package com.epam.rd.java.basic.practice7.constants;

/**
 * Enum class contains xml element and attributes
 */
public enum XmlElementEnum {
    /**
     * Elements
     */
    FLOWERS("flowers"),
    FLOWER("flower"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    VISUALPARAMETERS("visualParameters"),
    STEMCOLOUR("stemColour"),
    LEAFCOLOUR("leafColour"),
    AVELENFLOWER("aveLenFlower"),
    GROWINGTIPS("growingTips"),
    TEMPRETURE("tempreture"),
    LIGHTING("lighting"),
    WATERING("watering"),
    MULTIPLYING("multiplying"),

    /**
     * Attribute
     */
    MEASURE("measure"),
    /**
     * Attribute
     */
    LIGHTREQUIRING("lightRequiring");


    private final String value;

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    XmlElementEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
