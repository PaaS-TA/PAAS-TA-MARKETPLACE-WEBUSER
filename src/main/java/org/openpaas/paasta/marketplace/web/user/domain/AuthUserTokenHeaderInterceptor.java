package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class AuthUserTokenHeaderInterceptor implements ClientHttpRequestInterceptor {

    private String authTokenHeaderName;

    public AuthUserTokenHeaderInterceptor(String authTokenHeaderName) {
        log.info("AuthUserTokenHeaderInterceptor: init");

        this.authTokenHeaderName = authTokenHeaderName;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        User user = SecurityUtils.getUser();

        log.info("user: {}", user);

        if (user != null) {
            request.getHeaders().set(authTokenHeaderName, user.getToken());
        }

        request.getHeaders().set(authTokenHeaderName, "hyerin");

        return execution.execute(request, body);
    }

}
