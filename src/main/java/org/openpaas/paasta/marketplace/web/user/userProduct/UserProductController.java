package org.openpaas.paasta.marketplace.web.user.userProduct;

import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자가 구매한 상품 Controller
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-17
 */
@Slf4j
@RestController
public class UserProductController {

    @Autowired
    private UserProductService userProductService;


    /**
     * 사용자 구매상품 등록
     *
     * @param userProduct the user product
     * @return UserProduct
     */
    @PostMapping(value = UserConstants.URI_DB_USER_PRODUCT_CREATE)
    public UserProduct createUserProduct(@RequestBody UserProduct userProduct) {
        log.info("createUserProduct: user's product={}", userProduct);

        return userProductService.createUserProduct(userProduct);
    }
}
