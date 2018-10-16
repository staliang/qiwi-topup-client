package com.staliang.qiwi.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class GetBalanceRequest extends RequestWithExtraPassword {

    @XmlElement(name = "request-type")
    private final String requestType = "ping";

    @XmlElement(name = "terminal-id")
    private final Long terminalId;

    // only for JAXB
    private GetBalanceRequest() {
        this(null);
    }

    public GetBalanceRequest(Long terminalId) {
        this.terminalId = terminalId;
    }
}
