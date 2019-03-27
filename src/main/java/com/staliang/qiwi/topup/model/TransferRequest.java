package com.staliang.qiwi.topup.model;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Currency;

@ToString
@XmlRootElement(name = "request")
public class TransferRequest extends RequestWithExtraPassword {

    @XmlElement(name = "request-type")
    private String requestType = "pay";

    @XmlElement(name = "terminal-id")
    private final Long terminalId;

    @XmlElement(name = "auth")
    private Auth auth;

    // only for JAXB
    private TransferRequest() {
        this(null, null, null, null, null, null, null);
    }

    public TransferRequest(Long terminalId, Long transactionNumber, Currency sourceCurrency, BigDecimal amount,
                           Currency destinationCurrency, String accountNumber, String comment) {
        this.terminalId = terminalId;

        if (transactionNumber != null) {
            Auth.Payment.Source source = new Auth.Payment.Source(sourceCurrency);
            Auth.Payment.Destination destination = new Auth.Payment.Destination(
                    amount, destinationCurrency, accountNumber, comment);

            this.auth = new Auth(new Auth.Payment(transactionNumber, source, destination));
        }
    }

    private static class Auth {

        @XmlElement(name = "payment")
        private final Payment payment;

        private Auth(Payment payment) {
            this.payment = payment;
        }

        private static class Payment {

            @XmlElement(name = "transaction-number")
            private final Long transactionNumber;

            @XmlElement(name = "from")
            private final Source source;

            @XmlElement(name = "to")
            private final Destination destination;

            private Payment(Long transactionNumber, Source source, Destination destination) {
                this.transactionNumber = transactionNumber;
                this.source = source;
                this.destination = destination;
            }

            private static class Source {

                @XmlElement(name = "ccy")
                private final String currencyCode;

                private Source(Currency currency) {
                    this.currencyCode = currency != null ? currency.getCurrencyCode() : null;
                }
            }

            private static class Destination {

                private static final long CARD_SERVICE_ID = 34020L;
                private static final long WALLET_SERVICE_ID = 99L;

                @XmlElement(name = "amount")
                private final BigDecimal amount;

                @XmlElement(name = "ccy")
                private final String currencyCode;

                @XmlElement(name = "service-id")
                private final Long serviceId;

                @XmlElement(name = "account-number")
                private final String accountNumber;

                @XmlElement(name = "extra")
                private ExtraComment extra;

                private Destination(BigDecimal amount, Currency currency, String accountNumber, String comment) {
                    this.amount = amount;
                    this.currencyCode = currency != null ? currency.getCurrencyCode() : null;
                    this.accountNumber = accountNumber;
                    if (isCardNumber(accountNumber)) {
                        serviceId = CARD_SERVICE_ID;
                    } else {
                        serviceId = WALLET_SERVICE_ID;
                    }
                    this.extra = new ExtraComment(comment);
                }

                private boolean isCardNumber(String accountNumber) {
                    return accountNumber.trim().length() > 12;
                }

                private static class ExtraComment extends Extra {
                    public ExtraComment(String value) {
                        super("comment", value);
                    }
                }
            }
        }
    }
}
