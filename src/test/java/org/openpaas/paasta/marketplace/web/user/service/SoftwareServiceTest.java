package org.openpaas.paasta.marketplace.web.user.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openpaas.paasta.marketplace.api.domain.Category;
import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.openpaas.paasta.marketplace.api.domain.SoftwarePlan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({ "unchecked", "deprecation" })
public class SoftwareServiceTest extends AbstractMockTest {

    SoftwareService softwareService;

    @Mock
    ResponseEntity<CustomPage<Software>> softearePageResponse;

    @Mock
    CustomPage<Software> softwareCustomPage;

    @Mock
    Page<Software> softwarePage;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        softwareService = new SoftwareService(paasApiRest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createSoftware() {
        Category category = category(1L, "category-01");
        Software software = software(1L, "software-01", category);

        softwareService.createSoftware(software);
    }

    @Test(expected = RuntimeException.class)
    public void createSoftwareError() {
        when(paasApiRest.postForObject(any(String.class), any(), any())).thenThrow(new IllegalStateException());

        softwareService.createSoftware(null);
    }

    @Test
    public void getCategories() {
        List<Category> categoryList = new ArrayList<>();
        Category category1 = category(1L, "category-01");
        categoryList.add(category1);
        Category category2 = category(2L, "category-02");
        categoryList.add(category2);

        when(paasApiRest.getForObject(startsWith("/categories"), eq(List.class))).thenReturn(categoryList);

        List<Category> result = softwareService.getCategories();
        assertEquals(categoryList, result);
    }

    @Test(expected = RuntimeException.class)
    public void getCategoriesError() {
        when(paasApiRest.getForObject(startsWith("/categories"), eq(List.class)))
                .thenThrow(new IllegalStateException());

        softwareService.getCategories();
    }

    @Test
    public void getSoftwareList() {
        Category category = category(1L, "category-01");
        List<Software> softwareList = new ArrayList<>();
        Software software1 = software(1L, "software-01", category);
        softwareList.add(software1);
        Software software2 = software(2L, "software-02", category);
        softwareList.add(software2);

        when(paasApiRest.exchange(startsWith("/softwares/page"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(softearePageResponse);

        when(softearePageResponse.getBody()).thenReturn(softwareCustomPage);
        when(softwareCustomPage.toPage()).thenReturn(softwarePage);
        when(softwareCustomPage.getContent()).thenReturn(softwareList);

        CustomPage<Software> result = softwareService.getSoftwareList("?nameLike=software");
        assertEquals(softwareList, result.getContent());
    }

    @Test
    public void getSoftware() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);

        when(paasApiRest.getForObject(eq("/softwares/1"), eq(Software.class))).thenReturn(software1);

        Software result = softwareService.getSoftware(1L);
        assertEquals(software1, result);
    }

    @Test
    public void getSoftwarePlanList() {
        Category category = category(1L, "category-01");
        software(1L, "software-01", category);
        List<SoftwarePlan> softwarePlanList = new ArrayList<>();
        SoftwarePlan softwarePlan1 = softwarePlan(1L, 1L);
        softwarePlanList.add(softwarePlan1);
        SoftwarePlan softwarePlan2 = softwarePlan(2L, 1L);
        softwarePlanList.add(softwarePlan2);

        when(paasApiRest.getForObject(startsWith("/softwares/plan/1/list"), eq(List.class)))
                .thenReturn(softwarePlanList);

        List<SoftwarePlan> result = softwareService.getSoftwarePlanList(1L);
        assertEquals(softwarePlanList, result);
    }

}
