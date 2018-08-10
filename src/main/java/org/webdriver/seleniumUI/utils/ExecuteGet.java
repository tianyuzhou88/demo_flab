package org.webdriver.seleniumUI.utils;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;

public class ExecuteGet {

    private final String url;
    private final String usr;
    private final String pwd;
    private String responseBody;

    public String getResponseBody() {
        return responseBody;
    }

    public ExecuteGet(String url, String usr, String pwd) {
        this.url = url;
        this.usr = usr;
        this.pwd = pwd;
    }


    public void execute() throws IOException {
        ClientSetup clientSetup = new ClientSetup(usr, pwd);
        clientSetup.createClient();
        ResponseHandler<String> handler = new BasicResponseHandler();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(HttpHeaders.AUTHORIZATION, clientSetup.authHeader());
//        httpGet.addHeader("X-Requested-With", "HttpClients");
        try {
            CloseableHttpResponse response = clientSetup.getHttpClient().execute(httpGet, clientSetup.getContext());
            int responseCode = response.getStatusLine().getStatusCode();
            responseBody = handler.handleResponse(response);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
