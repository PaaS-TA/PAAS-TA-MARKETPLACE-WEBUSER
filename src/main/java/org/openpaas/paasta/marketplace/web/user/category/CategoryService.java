package org.openpaas.paasta.marketplace.web.user.category;

import java.util.List;

import org.openpaas.paasta.marketplace.web.user.common.RestTemplateService;
import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
    private RestTemplateService marketApiRest;

    /**
     * 카테고리 목록 조회
     *
     * @return CategoryList
     */
    public CategoryList getCategoryListByDeleteYn() {
    	return marketApiRest.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_CATEGORY, HttpMethod.GET, null, CategoryList.class);
    }

}
