package com.splat_spb.oauth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Default comment.
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorizationServerTest {
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String AUTHENTICATION_FAILURE = "AUTHENTICATION_FAILURE";
    public static final String TOKEN_ENDPOINT = "/oauth2/token";

    @Autowired
    private WebApplicationContext context;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void shouldReturnAccessToken() throws Exception {
        mockMvc.perform(post(TOKEN_ENDPOINT)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .param("grant_type", "client_credentials")
                .param("scope", "payments"))
               .andExpect(jsonPath("$.access_token", anything()))
               .andExpect(status().is(200))
               .andExpect(jsonPath("$.expires_in").isNumber())
               .andExpect(jsonPath("$.scope", equalTo("payments")))
               .andExpect(jsonPath("$.token_type", equalToIgnoringCase("bearer")));
    }

    @Test
    public void shouldReturnAuthenticationError() throws Exception {
        mockMvc.perform(post(TOKEN_ENDPOINT)
                .with(httpBasic(CLIENT_ID, "invalid_secret"))
                .param("grant_type", "client_credentials")
                .param("scope", "payments"))
               .andExpect(status().is(401))
               .andExpect(jsonPath("$.error", equalTo(AUTHENTICATION_FAILURE)))
               .andExpect(jsonPath("$.error_description", anything()));
    }

    @Test
    public void shouldReturnAuthenticationErrorWithoutGrantType() throws Exception {
        mockMvc.perform(post(TOKEN_ENDPOINT)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .param("scope", "payments"))
               .andExpect(status().is(401))
               .andExpect(jsonPath("$.error", equalTo(AUTHENTICATION_FAILURE)))
               .andExpect(jsonPath("$.error_description", anything()));
    }


}
