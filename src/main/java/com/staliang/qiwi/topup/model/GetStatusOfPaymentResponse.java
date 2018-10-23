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

    @Getter
    @ToString
    private static class Payment {

        @XmlAttribute(name = "status")
        private int status;

        @XmlAttribute(name = "txn_id")
        private Long transactionId;

        @XmlAttribute(name = "txn-date")
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date transactionDate;

        @XmlAttribute(name = "transaction-number")
        private String transactionNumber;

        @XmlAttribute(name = "result-code")
        private int resultCode;

        @XmlAttribute(name = "final-status")
        private boolean finalStatus;

        @XmlAttribute(name = "fatal-error")
        private boolean fatalError;

        private static class DateAdapter extends XmlAdapter<String, Date> {

            private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            @Override
            public String marshal(Date v) throws Exception {
                synchronized (dateFormat) {
                    return dateFormat.format(v);
                }
            }

            @Override
            public Date unmarshal(String v) throws Exception {
                synchronized (dateFormat) {
                    return dateFormat.parse(v);
                }
            }
        }
    }
}
