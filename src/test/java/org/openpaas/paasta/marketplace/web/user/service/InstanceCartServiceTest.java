package org.openpaas.paasta.marketplace.web.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openpaas.paasta.marketplace.api.domain.Category;
import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.openpaas.paasta.marketplace.api.domain.InstanceCartSpecification;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({ "unchecked" })
public class InstanceCartServiceTest extends AbstractMockTest {

    InstanceCartService instanceCartService;

    @Mock
    ResponseEntity<List<InstanceCart>> instanceCartListResponse;

    @Mock
    ResponseEntity<Integer> intResponse;

    @Mock
    ResponseEntity<Long> longResponse;

    @Mock
    CustomPage<Instance> instanceCustomPage;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        instanceCartService = new InstanceCartService(paasApiRest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createInstanceCart() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        InstanceCart instanceCart1 = instanceCart(1L, software1);

        when(paasApiRest.postForObject(eq("/instances/cart"), any(InstanceCart.class), eq(InstanceCart.class)))
                .thenReturn(instanceCart1);

        InstanceCart result = instanceCartService.createInstanceCart(instanceCart1);
        assertEquals(instanceCart1, result);
    }

    @Test
    public void getAllList() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        List<InstanceCart> instanceCartList = new ArrayList<>();
        InstanceCart instanceCart1 = instanceCart(1L, software1);
        instanceCartList.add(instanceCart1);
        InstanceCart instanceCart2 = instanceCart(2L, software1);
        instanceCartList.add(instanceCart2);

        when(paasApiRest.exchange(startsWith("/instances/cart/allList"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instanceCartListResponse);
        when(instanceCartListResponse.getBody()).thenReturn(instanceCartList);

        List<InstanceCart> result = instanceCartService.getAllList("?softwareNameLike=software");
        assertEquals(instanceCartList, result);
    }

    @Test
    public void getAllListNull() {
        when(paasApiRest.exchange(startsWith("/instances/cart/allList"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instanceCartListResponse);
        when(instanceCartListResponse.getBody()).thenReturn(null);

        List<InstanceCart> result = instanceCartService.getAllList("?softwareNameLike=software");
        assertNull(result);
    }

    @Test
    public void getUserAllCartList() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        List<InstanceCart> instanceCartList = new ArrayList<>();
        InstanceCart instanceCart1 = instanceCart(1L, software1);
        instanceCartList.add(instanceCart1);
        InstanceCart instanceCart2 = instanceCart(2L, software1);
        instanceCartList.add(instanceCart2);

        when(paasApiRest.exchange(startsWith("/instances/cart/userAllCartList"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instanceCartListResponse);
        when(instanceCartListResponse.getBody()).thenReturn(instanceCartList);

        List<InstanceCart> result = instanceCartService.getUserAllCartList("?softwareNameLike=software");
        assertEquals(instanceCartList, result);
    }

    @Test
    public void getUserAllCartListNull() {
        when(paasApiRest.exchange(startsWith("/instances/cart/userAllCartList"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instanceCartListResponse);
        when(instanceCartListResponse.getBody()).thenReturn(null);

        List<InstanceCart> result = instanceCartService.getUserAllCartList("?softwareNameLike=software");
        assertNull(result);
    }

    @Test
    public void allDelete() {
        when(paasApiRest.exchange(startsWith("/instances/cart/allDelete"), eq(HttpMethod.DELETE), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(intResponse);
        when(intResponse.getBody()).thenReturn(2);

        int result = instanceCartService.allDelete("?softwareId=1&softwareId=2");
        assertEquals(2, result);
    }

    @Test
    public void delete() {
        InstanceCartSpecification spec = new InstanceCartSpecification();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        spec.setInInstanceCartId(ids);

        doNothing().when(paasApiRest).delete(startsWith("/instances/cart/delete"));
        int result = instanceCartService.delete(spec);
        assertEquals(2, result);
    }

    @Test
    public void deleteNull() {
        InstanceCartSpecification spec = new InstanceCartSpecification();

        doNothing().when(paasApiRest).delete(startsWith("/instances/cart/delete"));
        int result = instanceCartService.delete(spec);
        assertEquals(0, result);
    }

    @Test
    public void deleteEmpty() {
        InstanceCartSpecification spec = new InstanceCartSpecification();
        List<Long> ids = new ArrayList<>();
        spec.setInInstanceCartId(ids);

        doNothing().when(paasApiRest).delete(startsWith("/instances/cart/delete"));
        int result = instanceCartService.delete(spec);
        assertEquals(0, result);
    }

    @Test
    public void getPageList() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        List<InstanceCart> instanceCartList = new ArrayList<>();
        InstanceCart instanceCart1 = instanceCart(1L, software1);
        instanceCartList.add(instanceCart1);
        InstanceCart instanceCart2 = instanceCart(2L, software1);
        instanceCartList.add(instanceCart2);

        when(paasApiRest.exchange(startsWith("/instances/cart/page/list"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(instanceCartListResponse);
        when(instanceCartListResponse.getBody()).thenReturn(instanceCartList);

        List<InstanceCart> result = instanceCartService.getPageList("?softwareNameLike=software");
        assertEquals(instanceCartList, result);
    }

    @Test
    public void purchaseCart() {
        Category category = category(1L, "category-01");
        Software software1 = software(1L, "software-01", category);
        InstanceCart instanceCart1 = instanceCart(1L, software1);

        when(paasApiRest.postForObject(eq("/instances/cart/purchaseCart"), any(InstanceCart.class),
                eq(InstanceCart.class))).thenReturn(instanceCart1);

        InstanceCart result = instanceCartService.purchaseCart(instanceCart1);
        assertEquals(instanceCart1, result);
    }

}
