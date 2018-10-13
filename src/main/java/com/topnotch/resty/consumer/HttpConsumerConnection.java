package com.topnotch.resty.consumer;


import com.topnotch.resty.consumer.exception.ClientException;
import com.topnotch.resty.log.Logger;
import com.topnotch.resty.util.stream.DefaultFileModifier;
import com.topnotch.resty.util.stream.FileModifier;

import javax.net.ssl.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;

/**
 * @author funmiayinde on 09/10/2018
 */
public class HttpConsumerConnection {

    private final static Logger logger = Logger.getLogger(HttpConsumerConnection.class.getSimpleName());
    private final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
    private final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new TrustAnyHostnameVerifier();
    protected HttpConsumerRequest loginRequest;
    protected ThreadLocal<HttpConsumerRequest> consumerRequestThreadLocal = new ThreadLocal<>();
    private CookieManager cookieManager = new CookieManager();
    private String apiUrl;
    protected FileModifier fileModifier = new DefaultFileModifier();

    protected HttpConsumerConnection(String apiUrl) {
        this(apiUrl, null);
    }

    protected HttpConsumerConnection(String apiUrl, HttpConsumerRequest loginRequest) {
        this(apiUrl, loginRequest, null);
    }

    protected HttpConsumerConnection(String apiUrl, HttpConsumerRequest loginRequest, HttpConsumerRequest httpConsumerRequest) {
        this.apiUrl = apiUrl;
        this.loginRequest = loginRequest;
        if (httpConsumerRequest != null) {
            this.consumerRequestThreadLocal.set(httpConsumerRequest);
        } else {
            this.consumerRequestThreadLocal.set(new HttpConsumerRequest());
        }
        // add cookiesmanager
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }

    /**
     * Generate a random string
     *
     * @param length the length indicates the length of the generated string
     */
    public static String generateRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    /**
     * File modify tool
     *
     * @param fileModifier modify tool
     */
    public void setModifier(FileModifier fileModifier) {
        if (fileModifier == null) {
            throw new ClientException("FileModifier cannot be null");
        }
        this.fileModifier = fileModifier;
    }

    public SSLSocketFactory initSSLSocketFactory() {
        try {
            TrustManager[] trustManager = {new TrustAnyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("TLS", "SunJEE");
            sslContext.init(null, trustManager, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private class TrustAnyHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    private class TrustAnyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
