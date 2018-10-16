package com.staliang.qiwi.model;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

@Getter
@ToString
public abstract class ResponseWithResultCode {

    @XmlElement(name = "result-code")
    private ResultCode resultCode;

    @Getter
    @ToString
    private static class ResultCode {

        @XmlAttribute(name = "fatal")
        private boolean fatal;

        @XmlAttribute(name = "message")
        private String message;

        @XmlValue
        private int value;
    }
}
