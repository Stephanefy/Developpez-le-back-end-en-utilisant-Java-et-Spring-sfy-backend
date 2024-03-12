package com.chatop.chatopapi.responses;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleJSONResponse {

    private String key;
    private String value;

    public SimpleJSONResponse(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
