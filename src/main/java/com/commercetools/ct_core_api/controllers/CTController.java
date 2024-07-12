package com.commercetools.ct_core_api.controllers;

import com.commercetools.ct_core_api.vo.AccessTokenResponse;
import com.commercetools.ct_core_api.vo.CategoryAPIResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@Log4j2
@RestController
@RequestMapping("/ct/api/")
public class CTController {

    @Value("${commercetools.commerce.api.ctp_access_token}")
    private String ctp_access_token;

    private final RestClient restClient;
    private final RestClient authClient;

    public CTController(RestClient.Builder restClientBuilder, RestClient.Builder authClientBuilder) {
        this.authClient = authClientBuilder
                .baseUrl("https://auth.europe-west1.gcp.commercetools.com/")
                .build();

        this.restClient = restClientBuilder
                .baseUrl("https://api.europe-west1.gcp.commercetools.com/credera-2test-b2b/")
                .build();
    }

    @PostMapping("/getToken")
    public AccessTokenResponse getAccessToken(HttpServletRequest request) {
        log.info("Authorization Header: " + request.getHeader("Authorization"));
        String accessToken = "";
        return authClient
                .post()
                .uri("/oauth/token?grant_type=client_credentials")
                .header("Authorization", request.getHeader("Authorization"))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(AccessTokenResponse.class);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryAPIResponse> getCategoryById(@PathVariable String categoryId, HttpServletRequest request) {
        log.info("Inside getCategoryById \n");
        log.info("CategoryId : " + categoryId);
        return restClient
                .get()
                .uri("categories/{category-id}", categoryId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + ctp_access_token)
                //.header("Authorization", request.getHeader("Authorization"))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((req, res) -> {
                    log.error("Category not found : " + categoryId + "\n");
                    throw new CategoryNotFoundException(categoryId);//
                }))
                .toEntity(new ParameterizedTypeReference<>() {
                });
    }
}


class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryId) {
        super("Category not found with ID : " + categoryId);
    }
}