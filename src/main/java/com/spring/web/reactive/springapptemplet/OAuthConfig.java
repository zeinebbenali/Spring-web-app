package com.spring.web.reactive.springapptemplet;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.authorization.ReactiveOAuth2AuthorizationFailureHandler;
import org.springframework.security.oauth2.client.authorization.ReactiveOAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.ReactiveOAuth2AuthorizedClientRepository;

@Configurationpublic
class OAuthConfig {
    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(ReactiveClientRegistrationRepository clientRegistrationRepository, ReactiveOAuth2AuthorizedClientRepository authorizedClientRepository) {
        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder().authorizationCode().refreshToken().build();
        DefaultReactiveOAuth2AuthorizedClientManager authorizedClientManager = new DefaultReactiveOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        authorizedClientManager.setAuthorizationSuccessHandler(authorizationSuccessHandler());
        authorizedClientManager.setAuthorizationFailureHandler(authorizationFailureHandler());
        return authorizedClientManager;
    }

    @Bean
    public ReactiveOAuth2AuthorizationSuccessHandler authorizationSuccessHandler() {
        return (authorizedClient, principal, attributes) -> {         // Custom success handling logic if needed      };   }
            @Bean public ReactiveOAuth2AuthorizationFailureHandler authorizationFailureHandler () {
                return (authorizedClient, principal, attributes) -> {         // Custom failure handling logic if needed      };   }}