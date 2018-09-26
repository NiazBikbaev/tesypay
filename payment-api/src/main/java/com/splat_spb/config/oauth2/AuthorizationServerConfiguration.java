package com.splat_spb.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * OAuth2 server configuration.
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final OAuth2ExceptionTranslator exceptionTranslator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ClientDetailsService clientDetailsService;
    private final OAuth2AuthenticationEntryPoint authenticationEntryPoint;

    public AuthorizationServerConfiguration(OAuth2ExceptionTranslator exceptionTranslator,
                                            PasswordEncoder passwordEncoder,
                                            AuthenticationManager authenticationManager,
                                            ClientDetailsService clientDetailsService,
                                            OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint) {
        this.exceptionTranslator = exceptionTranslator;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.clientDetailsService = clientDetailsService;
        this.authenticationEntryPoint = oAuth2AuthenticationEntryPoint;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.addTokenEndpointAuthenticationFilter(new BasicAuthenticationFilter(authenticationManager,
                authenticationEntryPoint));
        security.passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.exceptionTranslator(exceptionTranslator);
        endpoints.pathMapping("/oauth/token", "/oauth2/token");
    }

}
