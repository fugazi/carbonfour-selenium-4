package Selenium_4_Tests_Practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class WebServiceTest {

    private HttpClient httpClient;
    private String secureToken;

    @BeforeEach
    public void setUp() {
        httpClient = HttpClients.createDefault();
        login();
    }

    private void login() {
        String email = "login@email.com";
        String password = "password";

        HttpPost authRequest = new HttpPost("https://webservice.com/rest/user/login");
        String authData = "email=" + email + "&password=" + password;
        authRequest.setEntity(new StringEntity(authData, ContentType.APPLICATION_FORM_URLENCODED));

        try {
            HttpResponse response = (HttpResponse) httpClient.execute(authRequest);
            assertEquals(200, response.getStatusLine().getStatusCode());
            secureToken = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * WebService SignalsWebService
     */
    @Tag("Regression")
    @Test
    void verifySignOut() {
        assertNotNull(secureToken);

        HttpPost request = new HttpPost("https://webservice.com/rest/user/logout");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json;q=0.9;v=1");
        request.setHeader("Sec-GPC", "1");
        request.setHeader("User-Agent", "future");
        request.setHeader("Identity-Token", secureToken);

        try {
            HttpResponse response = (HttpResponse) httpClient.execute(request);
            assertEquals(201, response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
