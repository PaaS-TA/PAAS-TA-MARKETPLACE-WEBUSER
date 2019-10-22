package org.openpaas.paasta.marketplace.web.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

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
    public Map<Long, Integer> getDayOfUseInstsPeriod(List<Long> idIn) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().path("/stats/instances/my/usePeriod/days");
        for (Long id : idIn) {
            builder.queryParam("idIn", id);
        }
        String url = builder.buildAndExpand().toUriString();

        return paasApiRest.getForObject(url, Map.class);
    }
}
