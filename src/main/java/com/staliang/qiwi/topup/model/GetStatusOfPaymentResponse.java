package com.staliang.qiwi.topup.model;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "response")
public class GetStatusOfPaymentResponse extends ResponseWithResultCode {

    @XmlElement(name = "payment")
    private Payment payment;

    @XmlElementWrapper(name = "balances")
    @XmlElement(name = "balance")
    private List<Balance> balances;
}
