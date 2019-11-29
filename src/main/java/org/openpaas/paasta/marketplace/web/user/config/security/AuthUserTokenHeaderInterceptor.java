package org.openpaas.paasta.marketplace.web.user.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.openpaas.paasta.marketplace.web.user.util.SecurityUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthUserTokenHeaderInterceptor implements ClientHttpRequestInterceptor {

    private String authTokenHeaderName;
    private String cfAuthBearerTokenHeaderName;

    public AuthUserTokenHeaderInterceptor(String authTokenHeaderName, String cfAuthBearerTokenHeaderName) {
        this.authTokenHeaderName = authTokenHeaderName;
        this.cfAuthBearerTokenHeaderName = cfAuthBearerTokenHeaderName;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        OAuth2User user = SecurityUtils.getUser();

        if (user != null) {
            request.getHeaders().set(authTokenHeaderName, httpServletRequest.getSession().getAttribute("yourName").toString());
            request.getHeaders().set(cfAuthBearerTokenHeaderName, httpServletRequest.getSession().getAttribute("token").toString());
        }

        return execution.execute(request, body);
    }

}
