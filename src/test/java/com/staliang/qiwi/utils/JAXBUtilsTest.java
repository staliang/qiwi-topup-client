package com.staliang.qiwi.utils;

import com.staliang.qiwi.model.TransferRequest;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JAXBUtilsTest {

    @Test
    public void toXML() throws JAXBException, IOException {
        TransferRequest request = new TransferRequest(1L, 2L, Currency.getInstance("RUB"),
                BigDecimal.TEN, Currency.getInstance("RUB"), "accountNumber");

        String result = JAXBUtils.toXML(request);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><request><request-type>pay</request-type><terminal-id>1</terminal-id><auth><payment><transaction-number>2</transaction-number><from><ccy>RUB</ccy></from><to><amount>10</amount><ccy>RUB</ccy><service-id>99</service-id><account-number>accountNumber</account-number></to></payment></auth></request>", result);
    }

    @Test
    public void fromXML() throws JAXBException {
        TransferRequest result = JAXBUtils.fromXML("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><request><request-type>pay</request-type></request>", TransferRequest.class);
        assertNotNull(result);
    }
}
