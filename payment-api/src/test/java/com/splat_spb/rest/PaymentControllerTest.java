package com.splat_spb.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.splat_spb.rest.OAuth2RequestPostProcessor.withClient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Default comment.
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentControllerTest {
    private static final String CLIENT_ID = "client_id";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    private static final String INVALID_REQUEST = "INVALID_REQUEST";
    private static final String UNSUPPORTED_MEDIA_TYPE = "UNSUPPORTED_MEDIA_TYPE";
    private static final String AUTHENTICATION_FAILURE = "AUTHENTICATION_FAILURE";

    private static final String CREATED = "CREATED";
    private static final String PAYMENTS_ENDPOINT = "/payments/payment";
    private static final Set<String> SUFFICIENT_SCOPES = new HashSet<>(Collections.singletonList("payments"));
    private static final Set<String> INSUFFICIENT_SCOPES = new HashSet<>(Collections.singletonList("scope"));


    @Autowired
    private WebApplicationContext context;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;
    @Autowired
    private AuthorizationServerTokenServices tokenService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void shouldCreatePayment() throws Exception {
        String data = "{  \n" +
                "   \"intent\":\"order\",\n" +
                "   \"notification_url\": \"http://localhost:8081/callback\",\n" +
                "   \"payer\":{  \n" +
                "      \"email\": \"test@test.com\"\n" +
                "   },\n" +
                "   \"transaction\":{  \n" +
                "      \"external_id\":\"123456789\",\n" +
                "      \"amount\":{  \n" +
                "         \"value\": \"111.47\",\n" +
                "         \"currency\":\"USD\"\n" +
                "      },\n" +
                "      \"description\":\"The payment transaction description\"\n" +
                "   }\n" +
                "}";
        mockMvc.perform(post(PAYMENTS_ENDPOINT)
                .with(withClient(tokenService, CLIENT_ID, SUFFICIENT_SCOPES))
                .header(CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE)
                .content(data))
               .andExpect(status().is(200))
               .andExpect(jsonPath("$.state").value(CREATED.toLowerCase()));
    }

    @Test
    public void shouldReturnAuthenticationError() throws Exception {
        String data = "{  \n" +
                "   \"intent\":\"order\",\n" +
                "   \"notification_url\": \"http://localhost:8081/callback\",\n" +
                "   \"payer\":{  \n" +
                "      \"email\": \"test@test.com\"\n" +
                "   },\n" +
                "   \"transaction\":{  \n" +
                "      \"external_id\":\"123456789\",\n" +
                "      \"amount\":{  \n" +
                "         \"value\": \"111.47\",\n" +
                "         \"currency\":\"USD\"\n" +
                "      },\n" +
                "      \"description\":\"The payment transaction description\"\n" +
                "   }\n" +
                "}";
        mockMvc.perform(post(PAYMENTS_ENDPOINT)
                .with(withClient(tokenService, CLIENT_ID, INSUFFICIENT_SCOPES))
                .header(CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE)
                .content(data))
               .andExpect(status().is(401))
               .andExpect(jsonPath("$.error").value(AUTHENTICATION_FAILURE));
    }

    @Test
    public void shouldReturnMethodNotAllowed() throws Exception {
        mockMvc.perform(get(PAYMENTS_ENDPOINT)
                .with(withClient(tokenService, CLIENT_ID, SUFFICIENT_SCOPES)))
               .andExpect(status().is(400))
               .andExpect(jsonPath("$.error").value(INVALID_REQUEST));
    }

    @Test
    public void shouldReturnMediaTypeNotSupported() throws Exception {
        mockMvc.perform(post(PAYMENTS_ENDPOINT)
                .with(withClient(tokenService, CLIENT_ID, SUFFICIENT_SCOPES))
                .header(CONTENT_TYPE_HEADER, MediaType.APPLICATION_ATOM_XML_VALUE))
               .andExpect(status().is(415))
               .andExpect(jsonPath("$.error").value(UNSUPPORTED_MEDIA_TYPE));
        mockMvc.perform(post(PAYMENTS_ENDPOINT)
                .with(withClient(tokenService, CLIENT_ID, SUFFICIENT_SCOPES)))
               .andExpect(status().is(415))
               .andExpect(jsonPath("$.error").value(UNSUPPORTED_MEDIA_TYPE));
    }


    @Test
    public void shouldFailAuthentication() throws Exception {
        mockMvc.perform(post(PAYMENTS_ENDPOINT)
                .header(CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().is(401))
               .andExpect(jsonPath("$.error").value(AUTHENTICATION_FAILURE));
    }

}
