package com.staliang.qiwi.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Currency;

@XmlRootElement(name = "request")
public class CheckClientRequest extends RequestWithExtraPassword {

    @XmlElement(name = "request-type")
    private final String requestType = "check-user";

    @XmlElement(name = "terminal-id")
    private final Long terminalId;

    @XmlElement(name = "extra")
    private ExtraPhone phone;

    @XmlElement(name = "extra")
    private ExtraCurrencyCode currencyCode;

    // only for JAXB
    private CheckClientRequest() {
        this(null, null, null);
    }

    public CheckClientRequest(Long terminalId, String phone, Currency currency) {
        this.terminalId = terminalId;

        if (phone != null) {
            this.phone = new ExtraPhone(phone);
        }

        if (currency != null) {
            this.currencyCode = new ExtraCurrencyCode(currency.getCurrencyCode());
        }
    }

    private static class ExtraPhone extends Extra {
        public ExtraPhone(String value) {
            super("phone", value);
        }
    }

    private static class ExtraCurrencyCode extends Extra {
        public ExtraCurrencyCode(String value) {
            super("ccy", value);
        }
    }
}
