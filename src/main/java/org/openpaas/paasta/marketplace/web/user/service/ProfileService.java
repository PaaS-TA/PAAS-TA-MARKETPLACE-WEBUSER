package org.openpaas.paasta.marketplace.web.user.service;

import java.util.List;

import org.openpaas.paasta.marketplace.api.domain.Category;
import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final RestTemplate paasApiRest;
    private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);

    @SneakyThrows
    public List<Category> getCategories() {
        return paasApiRest.getForObject("/categories", List.class);
    }

    @SneakyThrows
    public List<Profile> getProfile() {
        return paasApiRest.getForObject("/profiles", List.class);
    }


    //Page
    public CustomPage<Profile> getProfileList(String queryParamString) {
        ResponseEntity<CustomPage<Profile>> responseEntity = paasApiRest.exchange("/profiles/page" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<CustomPage<Profile>>() {});
        CustomPage<Profile> customPage = responseEntity.getBody();
        Page<Profile> page = customPage.toPage();
        return customPage;
    }

}
