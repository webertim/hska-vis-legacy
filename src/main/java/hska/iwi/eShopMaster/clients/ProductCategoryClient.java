package hska.iwi.eShopMaster.clients;

import com.opensymphony.xwork2.ActionContext;
import com.sun.net.httpserver.HttpContext;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductCategoryClient {
    private static final String ZUUL_ENDPOINT = "http://localhost:9081/product-category-api/";

    protected RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    protected HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> session = ActionContext.getContext().getSession();
        String token = (String) session.get("access_token");
        headers.setBearerAuth(token);
        return headers;
    }

    public Product getProductById(int id) {
        ResponseEntity<Product> products = restTemplate().exchange(ZUUL_ENDPOINT + "products/" + id, HttpMethod.GET, new HttpEntity<>("", headers()), Product.class);
        return products.getBody();
    }

    public List<Product> getProducts() {
        ResponseEntity<Product[]> products = restTemplate().exchange(ZUUL_ENDPOINT + "products", HttpMethod.GET, new HttpEntity<>("", headers()), Product[].class);
        return Arrays.asList(products.getBody());
    }

    public List<Product> getProductsForSearchValues(String searchDescription,
                                                    Double searchMinPrice, Double searchMaxPrice) {
        ResponseEntity<Product[]> products = restTemplate().exchange(ZUUL_ENDPOINT + String.format("products?searchstring=%s&minPrice=%s&maxPrice=%s", searchDescription, searchMinPrice, searchMaxPrice), HttpMethod.GET, new HttpEntity<>("", headers()), Product[].class);
        return Arrays.asList(products.getBody());
    }
}
