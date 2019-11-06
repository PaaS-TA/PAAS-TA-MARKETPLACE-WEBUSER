package org.openpaas.paasta.marketplace.web.user.service;

import java.util.List;
import java.util.Map;

import org.openpaas.paasta.marketplace.api.domain.Category;
import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
    public CustomPage<Instance> getMyTotalList(String queryParamString) {
        ResponseEntity<CustomPage<Instance>> responseEntity = paasApiRest.exchange("/instances/my/totalPage" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<CustomPage<Instance>>() {});
        CustomPage<Instance> customPage = responseEntity.getBody();

        System.out.println("getContent ::: " + customPage.getContent());
        System.out.println("getTotalElements ::: " + customPage.getTotalElements());
        return customPage;
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

    public void updateToDeleted(Long id) {
        String url = UriComponentsBuilder.newInstance().path("/instances/{id}")
                .build()
                .expand(id)
                .toString();

        paasApiRest.delete(url);
    }

    public Software provision(Software software) {
        return paasApiRest.postForObject("/apps", software, Software.class);
    }

    public Instance createInstance(Instance instance) {
        return paasApiRest.postForObject("/instances", instance, Instance.class);
    }
    
    /**
     * 사용자 총 사용요금 계산 (기간한정)
     * @param queryParamString
     * @return
     */
    public Long getUsagePriceTotal(String usageStartDate, String usageEndDate) {
    	String queryParamString = String.format("?usageStartDate=%s&usageEndDate=%s", usageStartDate, usageEndDate);
        ResponseEntity<Long> responseEntity = paasApiRest.exchange("/instances/usagePriceTotal"+ queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<Long>() {});
        return responseEntity.getBody();
    }
    
    /**
     * 상품별 사용요금 계산 (기간한정)
     * @param inInstanceId
     * @param usageStartDate
     * @param usageEndDate
     * @return
     */
    public Map<String, String> getPricePerInstanceList(List<Long> inInstanceId, String usageStartDate, String usageEndDate) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().path("/instances/pricePerInstanceList");
        for (Long id : inInstanceId) {
            builder.queryParam("inInstanceId", id);
        }
        builder.queryParam("usageStartDate", usageStartDate);
        builder.queryParam("usageEndDate", usageEndDate);
        String url = builder.buildAndExpand().toUriString();

        return paasApiRest.getForObject(url, Map.class);
    }
}
