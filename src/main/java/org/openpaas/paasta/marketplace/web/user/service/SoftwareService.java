package org.openpaas.paasta.marketplace.web.user.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openpaas.paasta.marketplace.web.user.domain.Category;
import org.openpaas.paasta.marketplace.web.user.domain.RestPageImpl;
import org.openpaas.paasta.marketplace.web.user.domain.Software;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public Page<Software> getSoftwares() {
        return paasApiRest.getForObject("http://localhost:8000/softwares/page", RestPageImpl.class);
    }


}
