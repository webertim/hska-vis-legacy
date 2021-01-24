package hska.iwi.eShopMaster.clients;

import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RoleClient {
    private static final String ZUUL_ENDPOINT = "http://localhost:9081/role-api/";
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

    public Role getRoleByName(String name) {
        Role role = restTemplate().getForObject(ZUUL_ENDPOINT + String.format("roles?name=%s", name), Role.class);
        return role;
    }
}