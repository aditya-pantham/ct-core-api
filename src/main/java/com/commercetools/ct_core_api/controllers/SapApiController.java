package com.commercetools.ct_core_api.controllers;

import com.commercetools.ct_core_api.vo.AccessTokenResponse;
import com.commercetools.ct_core_api.vo.UserProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@Log4j2
@RestController
@RequestMapping("/sap/api")
public class SapApiController {

    private final RestClient authClient;
    private final RestClient apiClient;
    public static final String AUTHORIZATION = "Authorization";

    public SapApiController(RestClient.Builder authClientBuilder, RestClient.Builder apiClientBuilder) {
        this.authClient = authClientBuilder.baseUrl("https://api.canv1f-techaspec2-d1-public.model-t.cc.commerce.ondemand.com").build();
        this.apiClient = apiClientBuilder.baseUrl("https://api.canv1f-techaspec2-d1-public.model-t.cc.commerce.ondemand.com/occ/v2/electronics").build();
    }

    @PostMapping("/token")
    public AccessTokenResponse getAccessToken(HttpServletRequest request) {
        return authClient
                .post()
                .uri("/authorizationserver/oauth/token?grant_type=client_credentials")
                .header(AUTHORIZATION, request.getHeader(AUTHORIZATION))
                .contentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .retrieve()
                .body(AccessTokenResponse.class);
    }

    @GetMapping("/categories/{categoryId}/products")
    public String getCatalogs(@PathVariable String categoryId, HttpServletRequest request){
        String accessToken = "Bearer " + getAccessToken(request).getAccessToken();
        return apiClient.get()
                .uri("/categories/{categoryId}/products",categoryId)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, accessToken)
                .retrieve()
                .body(String.class);

    }

    @PostMapping("/user")
    public String createUser(HttpServletRequest request, @RequestBody UserProperties prop){
        String accessToken = "Bearer " + getAccessToken(request).getAccessToken();
        return apiClient.post()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, accessToken)
                .body(prop)
                .retrieve()
                .body(String.class);
    }
}
