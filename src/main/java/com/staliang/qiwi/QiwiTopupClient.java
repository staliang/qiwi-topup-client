package com.staliang.qiwi;

import com.staliang.qiwi.auth.AuthEngine;
import com.staliang.qiwi.model.CheckClientRequest;
import com.staliang.qiwi.model.CheckClientResponse;
import com.staliang.qiwi.model.GetBalanceRequest;
import com.staliang.qiwi.model.GetBalanceResponse;
import com.staliang.qiwi.model.GetStatusOfPaymentRequest;
import com.staliang.qiwi.model.GetStatusOfPaymentResponse;
import com.staliang.qiwi.model.RequestWithExtraPassword;
import com.staliang.qiwi.model.TransferRequest;
import com.staliang.qiwi.model.TransferResponse;
import com.staliang.qiwi.utils.JAXBUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public class QiwiTopupClient {

    private static final String DEFAULT_URL = "https://api.qiwi.com/xml/topup.jsp";

    private final AuthEngine authEngine;

    public QiwiTopupClient(AuthEngine authEngine) {
        this.authEngine = authEngine;
    }

    private <T> T sendRequest(RequestWithExtraPassword request, Class<T> clazz) throws IOException, JAXBException {
        if (authEngine.isAuthByRequest()) {
            authEngine.addAuthInfoToRequest(request);
        }

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(DEFAULT_URL);
            httpPost.setEntity(new StringEntity(JAXBUtils.toXML(request)));

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
                return JAXBUtils.fromXML(EntityUtils.toString(httpResponse.getEntity()), clazz);
            }
        }
    }

    public TransferResponse transfer(Long terminalId, Long transactionNumber, Currency sourceCurrency, BigDecimal amount,
                                     Currency destinationCurrency, String accountNumber) throws IOException, JAXBException {
        TransferRequest request = new TransferRequest(terminalId, transactionNumber, sourceCurrency,
                amount, destinationCurrency, accountNumber);
        return sendRequest(request, TransferResponse.class);
    }

    public GetStatusOfPaymentResponse getStatusOfPayment(Long terminalId, Long transactionNumber, String accountNumber) throws IOException, JAXBException {
        GetStatusOfPaymentRequest request = new GetStatusOfPaymentRequest(terminalId, transactionNumber, accountNumber);
        return sendRequest(request, GetStatusOfPaymentResponse.class);
    }

    public CheckClientResponse checkClient(Long terminalId, String phone, Currency currency) throws IOException, JAXBException {
        CheckClientRequest request = new CheckClientRequest(terminalId, phone, currency);
        return sendRequest(request, CheckClientResponse.class);
    }

    public GetBalanceResponse getBalance(Long terminalId) throws IOException, JAXBException {
        GetBalanceRequest request = new GetBalanceRequest(terminalId);
        return sendRequest(request, GetBalanceResponse.class);
    }
}
