package org.openpaas.paasta.marketplace.web.user.common;

import java.util.HashMap;
import java.util.Map;

import org.openpaas.paasta.marketplace.web.user.config.security.userdetail.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * Properties
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-03-14
 */
@Slf4j
public class Common {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);
//
//    @Value("${market.place.api.uri}")
//    public String marketApiUri;
//
//    @Value("${cf.java.client.api.uri}")
//    public String cfJavaClientApiUri;
//
//    @Value("${cf.java.client.api.authorization.username}")
//    public String cfJavaClientApiUsername;
//
//    @Value("${cf.java.client.api.authorization.password}")
//    public String cfJavaClientApiPassword;
//
//    @Value("${cf.uaa.oauth.info.uri}")
//    public String uaaUserInfo;
//
//    @Value("${cf.uaa.oauth.token.check.uri}")
//    public String checkTokenUri;
//
//    @Value("${cf.uaa.oauth.token.access.uri}")
//    public String accessUri;
//
//    @Value("${cf.uaa.oauth.logout.uri}")
//    public String logoutUri;
//
//    @Value("${cf.uaa.oauth.authorization.uri}")
//    public String authorizationUri;
//
//    @Value("${cf.uaa.oauth.client.id}")
//    public String clientId;
//
//    @Value("${cf.uaa.oauth.client.secret}")
//    public String clientSecret;
//
//    @Value("${cf.api.url}")
//    public String apiUrl;
//
//    @Resource(name = "cfJavaClientApiRest")
//    RestTemplate cfJavaClientApiRest;

//	@Autowired
//	private PropertyService property;
//
//    public String getToken() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if ( "".equals( auth.getCredentials() ) )
//            return ( (String) auth.getPrincipal().toString() );
//
//        final Object authPrincipal = auth.getPrincipal();
//        if ( authPrincipal instanceof String )
//            return ( (String) authPrincipal );
//
//        User user = (User) authPrincipal;
//
//        //token 만료 시간 비교
//        if (user.getExpireDate() <= System.currentTimeMillis()) {
//
//            try {
//                Map<String, Object> resBody = new HashMap();
//                resBody.put("id", user.getUsername());
//                resBody.put("password", user.getPassword());
//
//                Map result;
//
//                result = cfJavaClientApiRest.postForObject("/login", resBody, Map.class);
//
//                user.setToken((String) result.get("token"));
//                user.setExpireDate((Long) result.get("expireDate"));
//
//                // session에 적용
//                Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                log.info("new token : " + user.getToken());
//
//            } catch (Exception e) {
//                throw new BadCredentialsException(e.getMessage());
//            }
//        }
//
//        log.info("############################# Expires In : " + (user.getExpireDate() - System.currentTimeMillis()) / 1000 + " sec");
//
//        String token = user.getToken();
//
//        return token;
//    }

}
