package com.staliang.qiwi.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "request")
public class GetStatusOfPaymentRequest extends RequestWithExtraPassword {

    @XmlElement(name = "request-type")
    private final String requestType = "pay";

    @XmlElement(name = "terminal-id")
    private final Long terminalId;

    @XmlElementWrapper(name = "status")
    @XmlElement(name = "payment")
    private List<Payment> payments;

    // only for JAXB
    private GetStatusOfPaymentRequest() {
        this(null, null, null);
    }

    public GetStatusOfPaymentRequest(Long terminalId, Long transactionNumber, String accountNumber) {
        this.terminalId = terminalId;

        if (transactionNumber != null) {
            this.payments = Collections.singletonList(new Payment(transactionNumber, accountNumber));
        }
    }

    private static class Payment {

        @XmlElement(name = "transaction-number")
        private final Long transactionNumber;

        @XmlElement(name = "to")
        private Destination destination;

        private Payment(Long transactionNumber, String accountNumber) {
            this.transactionNumber = transactionNumber;
            this.destination = new Destination(accountNumber);
        }

        private static class Destination {

            @XmlElement(name = "account-number")
            private final String accountNumber;

            private Destination(String accountNumber) {
                this.accountNumber = accountNumber;
            }
        }
    }
}
