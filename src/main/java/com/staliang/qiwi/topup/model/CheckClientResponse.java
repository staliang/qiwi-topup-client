package com.staliang.qiwi.topup.model;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "response")
public class CheckClientResponse extends ResponseWithResultCode {

    @XmlElement(name = "exist")
    private int exist;

    public boolean isExist() {
        return 1 == exist;
    }
}
