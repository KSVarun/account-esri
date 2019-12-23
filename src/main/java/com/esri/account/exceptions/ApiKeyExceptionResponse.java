package com.esri.account.exceptions;

public class ApiKeyExceptionResponse {
    private String apiKey;

    public ApiKeyExceptionResponse(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
