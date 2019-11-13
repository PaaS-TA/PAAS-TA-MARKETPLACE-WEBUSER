package org.openpaas.paasta.marketplace.web.user.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({ "unchecked" })
public class PriceServiceTest extends AbstractMockTest {

    PriceService priceService;

    @Mock
    ResponseEntity<Map<Long, Object>> longObjectMapResponse;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        priceService = new PriceService(paasApiRest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDayOfUseInstsPeriod() {
        Map<Long, Integer> map = new TreeMap<>();
        map.put(1L, 30);
        map.put(2L, 15);
        when(paasApiRest.getForObject(startsWith("/stats/instances/my/usePeriod/days"), eq(Map.class))).thenReturn(map);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        Map<Long, Integer> result = priceService.getDayOfUseInstsPeriod(ids, "20191113", "20200501");
        assertEquals(map, result);
    }

    @Test
    public void purchaseAmount() {
        Map<Long, Object> map = new TreeMap<>();
        map.put(0L, Arrays.asList("201911", 2, 3, 30));
        map.put(0L, Arrays.asList("201912", 3, 4, 30));

        when(paasApiRest.exchange(startsWith("/stats/instances/my/price/total"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(longObjectMapResponse);
        when(longObjectMapResponse.getBody()).thenReturn(map);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        Map<Long, Object> result = priceService.purchaseAmount("?usageStartDate=20191113&usageEndDate=20200101");
        assertEquals(map, result);
    }

}
