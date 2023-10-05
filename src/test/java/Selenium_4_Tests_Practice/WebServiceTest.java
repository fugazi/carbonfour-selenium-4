package Selenium_4_Tests_Practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class WebServiceTest {

    private HttpClient httpClient;
    private String secureToken;

    private static final String EMAIL = "durrea02@rei.com";
    private static final String PASSWORD = "test1234";
    private static final String DOMAIN_BY_DEFAULT = "https://webservice.com";
    private static final String ENDPOINT = "/rest/user/login";

    @BeforeEach
    public void setUp() {
        httpClient = HttpClients.createDefault();
        login();
    }

    private void login() {

        HttpPost authRequest = new HttpPost(DOMAIN_BY_DEFAULT + ENDPOINT);
        String authData = "email=" + EMAIL + "&password=" + PASSWORD;
        authRequest.setEntity(new StringEntity(authData, ContentType.APPLICATION_FORM_URLENCODED));

        try {
            HttpResponse response = httpClient.execute(authRequest);
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
        request.setHeader("Identity-Token", secureToken);

        try {
            HttpResponse response = (HttpResponse) httpClient.execute(request);
            assertEquals(201, response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
