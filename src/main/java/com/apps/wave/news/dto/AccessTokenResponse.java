package com.apps.wave.news.dto;

public class AccessTokenResponse {
	private String accessToken;

    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
