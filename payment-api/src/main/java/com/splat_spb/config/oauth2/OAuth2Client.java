package com.splat_spb.config.oauth2;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

/**
 * Default comment.
 **/
public class OAuth2Client {
    private static final String[] EMPTY_ARRAY = {};
    @NotNull
    private String clientId;
    @NotNull
    private String clientSecret;
    @NotNull
    private String[] scopes;
    private String[] redirectUris;
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;
    @NotNull
    private String[] authorizedGrantTypes;
    private String[] authorities;
    private String[] resourceIds;
    private boolean autoApprove;
    private Map<String, String> additionalInformation;

    /**
     * Getter for resourceIds.
     *
     * @return java.lang.String[]
     */
    public String[] getResourceIds() {
        return resourceIds == null ? EMPTY_ARRAY : resourceIds;
    }

    /**
     * Setter for resourceIds.
     *
     * @param resourceIds value
     */
    public void setResourceIds(String[] resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * Getter for authorities.
     *
     * @return java.lang.String[]
     */
    public String[] getAuthorities() {
        return authorities == null ? EMPTY_ARRAY : authorities;
    }

    /**
     * Setter for authorities.
     *
     * @param authorities value
     */
    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    /**
     * Getter for clientId.
     *
     * @return java.lang.String
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Setter for clientId.
     *
     * @param clientId value
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Getter for clientSecret.
     *
     * @return java.lang.String
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Setter for clientSecret.
     *
     * @param clientSecret value
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Getter for scopes.
     *
     * @return java.lang.String[]
     */
    public String[] getScopes() {
        return scopes;
    }

    /**
     * Setter for scopes.
     *
     * @param scopes value
     */
    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    /**
     * Getter for redirectUris.
     *
     * @return java.lang.String[]
     */
    public String[] getRedirectUris() {
        return redirectUris == null ? EMPTY_ARRAY : redirectUris;
    }

    /**
     * Setter for redirectUris.
     *
     * @param redirectUris value
     */
    public void setRedirectUris(String[] redirectUris) {
        this.redirectUris = redirectUris;
    }

    /**
     * Getter for accessTokenValiditySeconds.
     *
     * @return int
     */
    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    /**
     * Setter for accessTokenValiditySeconds.
     *
     * @param accessTokenValiditySeconds value
     */
    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    /**
     * Getter for refreshTokenValiditySeconds.
     *
     * @return int
     */
    public int getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    /**
     * Setter for refreshTokenValiditySeconds.
     *
     * @param refreshTokenValiditySeconds value
     */
    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    /**
     * Getter for authorizedGrantTypes.
     *
     * @return java.lang.String[]
     */
    public String[] getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * Setter for authorizedGrantTypes.
     *
     * @param authorizedGrantTypes value
     */
    public void setAuthorizedGrantTypes(String[] authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * Getter for autoApprove.
     *
     * @return boolean
     */
    public boolean isAutoApprove() {
        return autoApprove;
    }

    /**
     * Setter for autoApprove.
     *
     * @param autoApprove value
     */
    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    /**
     * Getter for additionalInformation.
     *
     * @return additional information map
     */
    public Map<String, String> getAdditionalInformation() {
        return additionalInformation == null ? Collections.emptyMap() : additionalInformation;
    }

    /**
     * Setter for additionalInformation.
     *
     * @param additionalInformation value
     */
    public void setAdditionalInformation(Map<String, String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
