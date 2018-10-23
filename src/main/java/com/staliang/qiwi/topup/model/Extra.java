package com.staliang.qiwi.topup.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public abstract class Extra {

    @XmlAttribute(name = "name")
    private final String name;

    @XmlValue
    private final String value;

    public Extra(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static class ExtraPassword extends Extra {
        public ExtraPassword(String value) {
            super("password", value);
        }
    }
}
