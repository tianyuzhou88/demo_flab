package org.webdriver.seleniumUI.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.nio.charset.Charset;
import java.util.Properties;

public class ClientSetup {

    private  static Properties props = new TestAutomationProperties();

    private CloseableHttpClient httpClient;
    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public HttpClientContext getContext() {
        return context;
    }
    private HttpClientContext context;

    private String username;
    private String password;


    public ClientSetup(String usr, String pwd) {
        this.username = usr;
        this.password = pwd;
    }

    public void createClient() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(AuthScope.ANY),
                new UsernamePasswordCredentials(username, password));
        httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
    }

    String authHeader() {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
        return "Basic " + new String(encodedAuth);
    }

}
