package org.openpaas.paasta.marketplace.web.user.userProduct;

import org.openpaas.paasta.marketplace.web.user.common.RestTemplateService;
import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * 사용자가 구매한 상품 Service
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-17
 */
@Service
public class UserProductService {

    @Autowired
    RestTemplateService restTemplateService;

    /**
     * 사용자 구매상품 등록
     *
     * @param userProduct the user product
     * @return UserProduct
     */
    UserProduct createUserProduct(UserProduct userProduct) {
        return restTemplateService.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_USER_PRODUCT, null, HttpMethod.POST, userProduct, UserProduct.class);
    }
}
