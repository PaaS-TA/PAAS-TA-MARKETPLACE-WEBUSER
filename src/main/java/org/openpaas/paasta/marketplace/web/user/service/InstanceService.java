package org.openpaas.paasta.marketplace.web.user.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.api.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstanceService {

    private final RestTemplate paasApiRest;
    private static final Logger logger = LoggerFactory.getLogger(InstanceService.class);

    @SneakyThrows
    public List<Category> getCategories() {
        return paasApiRest.getForObject("/categories", List.class);
    }

    @SneakyThrows
    public List<Instance> getinstances() {
        return paasApiRest.getForObject("/instances", List.class);
    }


    //Page::Instance
    public CustomPage<Instance> getSoftwareList(String queryParamString) {
        ResponseEntity<CustomPage<Instance>> responseEntity = paasApiRest.exchange("/instances/my/page" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<CustomPage<Instance>>() {});
        CustomPage<Instance> customPage = responseEntity.getBody();

        System.out.println("getContent ::: " + customPage.getContent());
        System.out.println("getTotalElements ::: " + customPage.getTotalElements());
        return customPage;
    }

    public Instance getInstance(Long id) {
        String url = UriComponentsBuilder.newInstance().path("/instances/{id}")
                .build()
                .expand(id)
                .toString();

        return paasApiRest.getForObject(url, Instance.class);
    }

    public void updateToDeleted(Software software) {
        String url = UriComponentsBuilder.newInstance().path("/softwares/{id}")
                .build()
                .expand(software.getId())
                .toString();

        paasApiRest.put(url, software);
    }

    public Software provision(Software software) {
        return paasApiRest.postForObject("/apps", software, Software.class);
    }

    public Instance createInstance(Instance instance) {
        return paasApiRest.postForObject("/instances", instance, Instance.class);
    }
}
