package br.com.rbarbiero.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

    private String token;
    private String tokenType;
    private long expiresIn;
    private String scope;

    public String getToken() {
        return token;
    }

    @JsonProperty("access_token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("token_type")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
