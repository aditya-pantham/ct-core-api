package com.commercetools.ct_core_api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryAPIResponse {
    private String id;
    private int version;
    private Date versionModifiedAt;
    private int lastMessageSequenceNumber;
    private Date createdAt;
    private Date lastModifiedAt;
    private String key;
    private Name name;
    private Description description;
    private String orderHint;
}

    @Getter
    @Setter
    class Name {
        @JsonProperty("de-De")
        private String deDE;
        @JsonProperty("it-IT")
        private String itIT;
        @JsonProperty("nl-NL")
        private String nlNL;
        @JsonProperty("fr-FR")
        private String frFR;
        @JsonProperty("en-AU")
        private String enAU;
        @JsonProperty("es-ES")
        private String esES;
        @JsonProperty("en-GB")
        private String enGB;
        @JsonProperty("en-NZ")
        private String enNZ;
        @JsonProperty("pt-PT")
        private String ptPT;
        @JsonProperty("en-US")
        private String enUS;
    }

    @Getter
    @Setter
    class Description {
        @JsonProperty("de-De")
        private String deDE;
        @JsonProperty("it-IT")
        private String itIT;
        @JsonProperty("nl-NL")
        private String nlNL;
        @JsonProperty("fr-FR")
        private String frFR;
        @JsonProperty("en-AU")
        private String enAU;
        @JsonProperty("es-ES")
        private String esES;
        @JsonProperty("en-GB")
        private String enGB;
        @JsonProperty("en-NZ")
        private String enNZ;
        @JsonProperty("pt-PT")
        private String ptPT;
        @JsonProperty("en-US")
        private String enUS;
}