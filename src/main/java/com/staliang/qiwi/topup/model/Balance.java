package com.staliang.qiwi.topup.model;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.math.BigDecimal;

@Getter
@ToString
public class Balance {

    @XmlAttribute(name = "code")
    private int code;

    @XmlValue
    private BigDecimal value;
}
