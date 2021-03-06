package com.staliang.qiwi.topup.model;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Setter
public abstract class RequestWithExtraPassword {

    @XmlElement(name = "extra")
    private Extra.ExtraPassword extra;
}
