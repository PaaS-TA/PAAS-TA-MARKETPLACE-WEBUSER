package org.openpaas.paasta.marketplace.web.user.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.web.user.domain.Category;
import org.openpaas.paasta.marketplace.web.user.domain.RestPageImpl;
import org.openpaas.paasta.marketplace.web.user.domain.Software;
import org.openpaas.paasta.marketplace.web.user.domain.SoftwareSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SoftwareService {

    private final RestTemplate paasApiRest;

    @SneakyThrows
    public void createSoftware(Software software) {
        Object response = paasApiRest.postForObject("http://localhost:8000/softwares", software, Void.class);
    }

    @SneakyThrows
    public List<Category> getCategories() {
        return paasApiRest.getForObject("http://localhost:8000/categories", List.class);
    }

    public Page<Software> getSoftwares(SoftwareSpecification spec) {
        String url = UriComponentsBuilder.newInstance().path("/softwares/page")
                .queryParam("categoryId", spec.getCategoryId())
                .queryParam("nameLike", spec.getNameLike())
                .build().encode()
                .toString();
        return paasApiRest.getForObject(url, RestPageImpl.class);
    }


}
