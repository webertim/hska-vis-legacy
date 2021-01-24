package hska.iwi.eShopMaster.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AuthClient {
    private static final String AUTH_ENDPOINT = "http://localhost:9079/oauth/token";

    protected RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    protected HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("client_password", "");
        return headers;
    }

    public String login(String username, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("scope", "any");
        params.put("username", username);
        params.put("password", password);
        Map response = restTemplate().postForObject(AUTH_ENDPOINT, new HttpEntity<>(params, headers()), Map.class);
        System.out.println(response.get("access_token"));
        return (String) response.get("access_token");
    }
}
