package com.splat_spb.config.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Default comment.
 **/
@Configuration
@ConfigurationProperties("oauth2")
@Validated
public class OAuth2ClientsProperties {
    private List<OAuth2Client> clients;

    /**
     * Getter for clients.
     *
     * @return java.util.List<com.test.testpay.config.OAuth2Client>
     */
    public List<OAuth2Client> getClients() {
        return clients;
    }

    /**
     * Setter for clients.
     *
     * @param clients value
     */
    public void setClients(List<OAuth2Client> clients) {
        this.clients = clients;
    }
}
