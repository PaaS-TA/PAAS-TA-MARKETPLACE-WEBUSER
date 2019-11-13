package org.openpaas.paasta.marketplace.web.user.service;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.openpaas.paasta.marketplace.api.domain.Category;
import org.openpaas.paasta.marketplace.api.domain.Profile;
import org.openpaas.paasta.marketplace.api.domain.Yn;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractMockTest {

    @Mock
    RestTemplate paasApiRest;

    protected String userId;

    protected String adminId;

    protected LocalDateTime current;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userId = "foo";
        adminId = "admin";
        current = LocalDateTime.now();
    }

    protected Category category(Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName("category-01");
        category.setDescription("descritpion");
        category.setCreatedBy("admin");
        category.setCreatedDate(current);
        category.setLastModifiedBy("admin");
        category.setLastModifiedDate(current);
        category.setSeq(id);

        return category;
    }

    protected Profile profile(String id) {
        Profile profile = new Profile();
        profile.setId(id);
        profile.setName("name-" + id);
        profile.setType(Profile.Type.Company);
        profile.setCreatedBy(id);
        profile.setCreatedDate(current);
        profile.setLastModifiedBy(id);
        profile.setLastModifiedDate(current);
        profile.setInUse(Yn.Y);

        return profile;
    }

}
