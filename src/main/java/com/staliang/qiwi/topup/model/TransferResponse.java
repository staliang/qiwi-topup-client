package com.staliang.qiwi.topup.model;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "response")
public class TransferResponse extends ResponseWithResultCode {

    @XmlElement(name = "payment")
    private Payment payment;

    @Getter
    @ToString
    private static class Payment {

        @XmlElement(name = "from")
        private From from;

        @XmlElement(name = "to")
        private To to;

        @XmlElementWrapper(name = "balances")
        @XmlElement(name = "balance")
        private List<Balance> balances;

        @Getter
        @ToString
        private static class From {

            @XmlElement(name = "amount")
            private BigDecimal amount;

            @XmlElement(name = "ccy")
            private String currencyCode;
        }

        @Getter
        @ToString
        private static class To {

            @XmlElement(name = "service-id")
            private Long serviceId;

            @XmlElement(name = "amount")
            private BigDecimal amount;

            @XmlElement(name = "ccy")
            private String currencyCode;

            @XmlElement(name = "accountNumber")
            private String accountNumber;
        }
    }
}

