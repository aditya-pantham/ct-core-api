package com.commercetools.ct_core_api.controllers;

import com.commercetools.ct_core_api.vo.AccessTokenResponse;
import com.commercetools.ct_core_api.vo.CategoryAPIResponse;
import com.commercetools.ct_core_api.vo.OrderApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@Log4j2
@RestController
@RequestMapping("/ct/api/")
public class CommerceToolsApiController {

    private final RestClient authClient;
    private final RestClient apiClient;
    public static final String AUTHORIZATION = "Authorization";
    public static final String CATEGORIES_BASE_URL = "/categories";
    public static final String ORDERS_BASE_URL = "/orders";

    public CommerceToolsApiController(RestClient.Builder authClientBuilder, RestClient.Builder apiClientBuilder) {
        this.authClient = authClientBuilder.baseUrl("https://auth.europe-west1.gcp.commercetools.com").build();
        this.apiClient = apiClientBuilder.baseUrl("https://api.europe-west1.gcp.commercetools.com/credera-2test-b2b").build();
    }

    @PostMapping("/getToken")
    public AccessTokenResponse getAccessToken(HttpServletRequest request) {
        return authClient
                .post()
                .uri("/oauth/token?grant_type=client_credentials")
                .header(AUTHORIZATION, request.getHeader(AUTHORIZATION))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(AccessTokenResponse.class);
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryAPIResponse getCategoryById(@PathVariable String categoryId, HttpServletRequest request) {
        String accessToken = "Bearer " + getAccessToken(request).getAccessToken();
        return apiClient.get()
                .uri(CATEGORIES_BASE_URL + "/{categoryId}", categoryId)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, accessToken)
                .retrieve()
                .body(CategoryAPIResponse.class);
    }

    @GetMapping("/orders/{orderId}")
    public OrderApiResponse getOrderById(@PathVariable String orderId, HttpServletRequest request) {
        String accessToken = "Bearer " + getAccessToken(request).getAccessToken();
        return apiClient.get()
                .uri(ORDERS_BASE_URL + "/{orderId}", orderId)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, accessToken)
                .retrieve()
                .body(new ParameterizedTypeReference<OrderApiResponse>() {});
    }
}