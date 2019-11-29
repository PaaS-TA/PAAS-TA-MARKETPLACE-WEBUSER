package org.openpaas.paasta.marketplace.web.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final RestTemplate paasApiRest;

    /**
     * 구매 상품 사용한 일(Day) 수
     *
     * @param idIn
     * @return
     */
    public Map<Long, Integer> getDayOfUseInstsPeriod(List<Long> idIn, String usageStartDate, String usageEndDate) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().path("/stats/instances/my/usePeriod/days");
        for (Long id : idIn) {
            builder.queryParam("idIn", id);
        }
        builder.queryParam("usageStartDate", usageStartDate);
        builder.queryParam("usageEndDate", usageEndDate);
        String url = builder.buildAndExpand().toUriString();

        return paasApiRest.getForObject(url, Map.class);
    }
    
    /**
     * 요금 통계 (월별 사용 일수, 사용 요금)
     *
     * @param usageStart, usageEnd
     * @return
     */
    public Map<Long, Object> purchaseAmount(String queryParamString) {
    	ResponseEntity<Map<Long, Object>> responseEntity = paasApiRest.exchange("/stats/instances/my/price/total" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<Map<Long, Object>>() {});
    	Map<Long, Object> customMap = responseEntity.getBody();
        return customMap;
    }
}
