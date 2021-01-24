package hska.iwi.eShopMaster.clients;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserRoleClient {
    private static final String ZUUL_ENDPOINT = "http://localhost:9081/user-role-api/";
    private static final String AUTH_ENDPOINT = "http://localhost:9079/oauth/token";

    protected RestTemplate restTemplate() {
        System.out.println(oauthDetails().getAccessTokenUri());
        System.out.println(oauthDetails().getTokenName());
        RestTemplate restTemplate = new OAuth2RestTemplate(oauthDetails());
        return restTemplate;
    }

    protected ClientCredentialsResourceDetails oauthDetails() {
        ClientCredentialsResourceDetails clientCredentials = new ClientCredentialsResourceDetails();
        clientCredentials.setClientSecret("secret");
        clientCredentials.setClientId("clientId");
        clientCredentials.setAccessTokenUri(AUTH_ENDPOINT);
        List<String> scopes = new ArrayList<>();
        scopes.add("any");
        clientCredentials.setScope(scopes);
        return clientCredentials;
    }

    public User registerUser(User user) {
        User registeredUser = restTemplate().postForObject(ZUUL_ENDPOINT + "register", user, User.class);
        return registeredUser;
    }

    public User getUserByUsername(String username) {
        User user = restTemplate().getForObject(ZUUL_ENDPOINT + String.format("user?username=%s", username), User.class);
        return user;
    }
}