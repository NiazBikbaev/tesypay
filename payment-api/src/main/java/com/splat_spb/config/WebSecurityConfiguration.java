package com.splat_spb.config;

import com.splat_spb.config.oauth2.OAuth2ClientsProperties;
import com.splat_spb.config.oauth2.OAuth2ExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.error.DefaultOAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.web.client.RestTemplate;

/**
 * Web security configuration.
 **/
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final OAuth2ClientsProperties clientsProperties;
    private final OAuth2ExceptionTranslator exceptionTranslator;

    public WebSecurityConfiguration(OAuth2ClientsProperties clientsProperties, OAuth2ExceptionTranslator exceptionTranslator) {
        this.clientsProperties = clientsProperties;
        this.exceptionTranslator = exceptionTranslator;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new ClientDetailsUserDetailsService(clientDetailsService()));
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(exceptionTranslator);
        DefaultOAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();
        exceptionRenderer.setMessageConverters(new RestTemplate().getMessageConverters());
        authenticationEntryPoint.setExceptionRenderer(exceptionRenderer);
        return authenticationEntryPoint;
    }

    @Bean
    public ClientDetailsService clientDetailsService() throws Exception {
        InMemoryClientDetailsServiceBuilder builder =
                new InMemoryClientDetailsServiceBuilder();
        clientsProperties.getClients()
                         .forEach(client -> builder.withClient(client.getClientId())
                                                   .secret(passwordEncoder().encode(client.getClientSecret()))
                                                   .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                                                   .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                                                   .authorizedGrantTypes(client.getAuthorizedGrantTypes())
                                                   .authorities(client.getAuthorities())
                                                   .resourceIds(client.getResourceIds())
                                                   .additionalInformation(client.getAdditionalInformation())
                                                   .scopes(client.getScopes()));
        return builder.build();
    }

}
