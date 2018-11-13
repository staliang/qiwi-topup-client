package com.staliang.qiwi.topup;

import com.staliang.qiwi.topup.auth.AuthEngine;
import com.staliang.qiwi.topup.model.CheckClientResponse;
import com.staliang.qiwi.topup.model.GetBalanceResponse;
import com.staliang.qiwi.topup.model.GetStatusOfPaymentResponse;
import com.staliang.qiwi.topup.model.TransferResponse;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QiwiTopupClientTest {

    private static final String PASSWORD = System.getProperty("password");
    private static final long TERMINAL_ID = Long.valueOf(System.getProperty("terminalId"));
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("RUB");
    private static final String ACCOUNT_NUMBER = System.getProperty("accountNumber");

    private final QiwiTopupClient qiwiTopupClient = new QiwiTopupClient(AuthEngine.authByPassword(PASSWORD));

    @Test
    public void transfer() throws IOException, JAXBException {
        TransferResponse result = qiwiTopupClient.transfer(TERMINAL_ID, System.currentTimeMillis(), DEFAULT_CURRENCY,
                BigDecimal.valueOf(0.1), DEFAULT_CURRENCY, ACCOUNT_NUMBER, "comment");

        assertNotNull(result);
    }

    @Test
    public void getStatusOfPayment() throws IOException, JAXBException {
        GetStatusOfPaymentResponse result = qiwiTopupClient.getStatusOfPayment(TERMINAL_ID, 1539956828560L, ACCOUNT_NUMBER);

        assertNotNull(result);
    }

    @Test
    public void checkClient() throws IOException, JAXBException {
        CheckClientResponse result = qiwiTopupClient.checkClient(TERMINAL_ID, ACCOUNT_NUMBER, DEFAULT_CURRENCY);

        assertNotNull(result);
        assertTrue(result.isExist());
    }

    @Test
    public void getBalance() throws IOException, JAXBException {
        GetBalanceResponse result = qiwiTopupClient.getBalance(TERMINAL_ID);

        assertNotNull(result);
        assertEquals(1, result.getBalances().size());
        assertNotNull(result.getBalances().get(0).getValue());
    }
}
