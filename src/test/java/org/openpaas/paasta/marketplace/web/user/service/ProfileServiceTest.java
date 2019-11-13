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
import org.openpaas.paasta.marketplace.api.domain.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SuppressWarnings({ "unchecked", "deprecation" })
public class ProfileServiceTest extends AbstractMockTest {

    ProfileService profileService;

    @Mock
    ResponseEntity<CustomPage<Profile>> profilePageResponse;

    @Mock
    CustomPage<Profile> profileCustomPage;

    @Mock
    Page<Profile> profilePage;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        profileService = new ProfileService(paasApiRest);
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

        List<Category> result = profileService.getCategories();
        assertEquals(categoryList, result);
    }

    @Test(expected = RuntimeException.class)
    public void getCategoriesError() {
        when(paasApiRest.getForObject(startsWith("/categories"), eq(List.class)))
                .thenThrow(new IllegalStateException());

        profileService.getCategories();
    }

    @Test
    public void getProfile() {
        List<Profile> profileList = new ArrayList<>();
        Profile profile1 = profile("user-01");
        profileList.add(profile1);
        Profile profile2 = profile("user-02");
        profileList.add(profile2);

        when(paasApiRest.getForObject(startsWith("/profiles"), eq(List.class))).thenReturn(profileList);

        List<Profile> result = profileService.getProfile();
        assertEquals(profileList, result);
    }

    @Test(expected = RuntimeException.class)
    public void getProfileError() {
        when(paasApiRest.getForObject(startsWith("/profiles"), eq(List.class))).thenThrow(new IllegalStateException());

        profileService.getProfile();
    }

    @Test
    public void getProfileList() {
        List<Profile> profileList = new ArrayList<>();
        Profile profile1 = profile("user-01");
        profileList.add(profile1);
        Profile profile2 = profile("user-02");
        profileList.add(profile2);

        when(paasApiRest.exchange(startsWith("/profiles/page"), eq(HttpMethod.GET), eq(null),
                any(ParameterizedTypeReference.class))).thenReturn(profilePageResponse);

        when(profilePageResponse.getBody()).thenReturn(profileCustomPage);
        when(profileCustomPage.toPage()).thenReturn(profilePage);
        when(profileCustomPage.getContent()).thenReturn(profileList);

        CustomPage<Profile> result = profileService.getProfileList("?nameLike=user");
        assertEquals(profileList, result.getContent());
    }

}
