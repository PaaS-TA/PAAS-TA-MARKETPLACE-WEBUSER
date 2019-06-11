package org.openpaas.paasta.marketplace.web.user.product;

import org.openpaas.paasta.marketplace.web.user.common.RestTemplateService;
import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * 상품 Service
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-04
 */
@Service
public class ProductService {

    @Autowired
    private RestTemplateService marketApiRest;

    /**
     * 상품 목록 조회
     *
     * @param paramString the param
     * @return ProductList
     */
    ProductList getProductList(String paramString) {
        return marketApiRest.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_PRODUCT + paramString, null, HttpMethod.GET, null, ProductList.class);
    }


    /**
     * 상품 상세 조회
     *
     * @param id the id
     * @return Product
     */
    Product getProduct(Long id) {
        return marketApiRest.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_PRODUCT + "/" + id, null, HttpMethod.GET, null, Product.class);
    }
}
