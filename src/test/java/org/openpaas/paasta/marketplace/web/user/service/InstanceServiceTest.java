package org.openpaas.paasta.marketplace.web.user.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openpaas.paasta.marketplace.api.domain.Category;
import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({ "unchecked" })
public class InstanceServiceTest extends AbstractMockTest {

    InstanceService instanceService;

    @Mock
    ResponseEntity<CustomPage<Instance>> instancePageResponse;

    @Mock
    ResponseEntity<Long> longResponse;

    @Mock
    CustomPage<Instance> instanceCustomPage;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        instanceService = new InstanceService(paasApiRest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCategories() {
        List<Category> categoryList = new ArrayList<>();
        Category category1 = category(1L, "category-01");
        categoryList.add(category1);
        Category category2 = category(2L, "category-02");
        categoryList.add(category2);

        when(paasApiRest.getForObject(startsWith("/categories"), eq(List.class))).thenReturn(categoryList);

        List<Category> result = instanceService.getCategories();
        assertEquals(categoryList, result);
    }

    @Test(expected = RuntimeException.class)
    public void getCategoriesError() {
        when(paasApiRest.getForObject(startsWith("/categories"), eq(List.class)))
                .thenThrow(new IllegalStateException());

        instanceService.getCategories();
    }

    @Test
    public void getinstances() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        List<Instance> instanceList = new ArrayList<>();
        Instance instance1 = instance(1L, software1);
        instanceList.add(instance1);
        Instance instance2 = instance(2L, software1);
        instanceList.add(instance2);

        when(paasApiRest.getForObject(eq("/instances"), eq(List.class))).thenReturn(instanceList);

        List<Instance> result = instanceService.getinstances();
        assertEquals(instanceList, result);
    }

    @Test(expected = RuntimeException.class)
    public void getinstancesError() {
        when(paasApiRest.getForObject(eq("/instances"), eq(List.class))).thenThrow(new IllegalStateException());

        instanceService.getinstances();
    }

    @Test
    public void getMyTotalList() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        List<Instance> instanceList = new ArrayList<>();
        Instance instance1 = instance(1L, software1);
        instanceList.add(instance1);
        Instance instance2 = instance(2L, software1);
        instanceList.add(instance2);

        when(paasApiRest.exchange(startsWith("/instances/my/totalPage"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instancePageResponse);

        when(instancePageResponse.getBody()).thenReturn(instanceCustomPage);
        when(instanceCustomPage.getContent()).thenReturn(instanceList);

        CustomPage<Instance> result = instanceService.getMyTotalList("?nameLike=instance");
        assertEquals(instanceList, result.getContent());
    }

    @Test
    public void getSoftwareList() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        List<Instance> instanceList = new ArrayList<>();
        Instance instance1 = instance(1L, software1);
        instanceList.add(instance1);
        Instance instance2 = instance(2L, software1);
        instanceList.add(instance2);

        when(paasApiRest.exchange(startsWith("/instances/my/page"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instancePageResponse);

        when(instancePageResponse.getBody()).thenReturn(instanceCustomPage);
        when(instanceCustomPage.getContent()).thenReturn(instanceList);

        CustomPage<Instance> result = instanceService.getSoftwareList("?nameLike=instance");
        assertEquals(instanceList, result.getContent());
    }

    @Test
    public void getInstance() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        Instance instance1 = instance(1L, software1);

        when(paasApiRest.getForObject(eq("/instances/1"), eq(Instance.class))).thenReturn(instance1);

        Instance result = instanceService.getInstance(1L);
        assertEquals(instance1, result);
    }

    @Test
    public void updateToDeleted() {
        doNothing().when(paasApiRest).delete(eq("/instances/1"));

        instanceService.updateToDeleted(1L);
    }

    @Test
    public void provision() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);

        when(paasApiRest.postForObject(eq("/apps"), any(Software.class), eq(Software.class))).thenReturn(software1);

        Software result = instanceService.provision(software1);
        assertEquals(software1, result);
    }

    @Test
    public void createInstance() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        Instance instance1 = instance(1L, software1);

        when(paasApiRest.postForObject(eq("/instances"), any(Instance.class), eq(Instance.class))).thenReturn(instance1);

        Instance result = instanceService.createInstance(instance1);
        assertEquals(instance1, result);
    }

    @Test
    public void getUsagePriceTotal() {
        when(paasApiRest.exchange(startsWith("/instances/usagePriceTotal"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(longResponse);
        when(longResponse.getBody()).thenReturn(25_000L);

        Long result = instanceService.getUsagePriceTotal("20191113", "20200501");
        assertEquals(Long.valueOf(25_000L), result);
    }

    @Test
    public void getPricePerInstanceList() {
        Map<String, String> map = new TreeMap<>();
        map.put("1", "25000");
        when(paasApiRest.getForObject(startsWith("/instances/pricePerInstanceList"), eq(Map.class))).thenReturn(map);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        Map<String, String> result = instanceService.getPricePerInstanceList(ids, "20191113", "20200501");
        assertEquals(map, result);
    }

}
