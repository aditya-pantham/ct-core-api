package com.commercetools.ct_core_api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties
public class CategoryAPIResponse {
    private String id;
    private int version;
    private String key;
    private Name name;
    private Description description;
    private String orderHint;
}

    @Getter
    @Setter
    class Name {
        @JsonProperty("en-US")
        private String enUS;
    }

    @Getter
    @Setter
    class Description {
        @JsonProperty("en-US")
        private String enUS;

}