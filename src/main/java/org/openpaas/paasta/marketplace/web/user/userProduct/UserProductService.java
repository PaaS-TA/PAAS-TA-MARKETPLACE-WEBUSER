//package org.openpaas.paasta.marketplace.web.user.userProduct;
//
//import org.openpaas.paasta.marketplace.web.user.common.RestTemplateService;
//import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
//import org.openpaas.paasta.marketplace.web.user.util.DateUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * 사용자가 구매한 상품 Service
// *
// * @author hrjin
// * @version 1.0
// * @since 2019-06-17
// */
//@Service
//public class UserProductService {
//
//    @Autowired
//    RestTemplateService restTemplateService;
//
//    /**
//     * 사용자 구매 상품 목록 조회
//     *
//     * @param paramString the param string
//     * @return UserProductList
//     */
//    UserProductList getUserProductList(String paramString) {
//        UserProductList userProductList = restTemplateService.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_USER_PRODUCT + paramString, null, HttpMethod.GET, null, UserProductList.class);
//        List<UserProduct> userProducts = userProductList.getItems();
//
//        for(UserProduct userProduct : userProducts) {
//            userProduct.setStrCreateDate(DateUtils.getConvertDate(userProduct.getCreateDate(), DateUtils.FORMAT_1));
//            userProduct.setStrUpdateDate(DateUtils.getConvertDate(userProduct.getUpdateDate(), DateUtils.FORMAT_1));
//            userProduct.setStrUseStartDate(DateUtils.getConvertDate(userProduct.getUseStartdate(), DateUtils.FORMAT_1));
//            userProduct.setStrUseEndDate(DateUtils.getConvertDate(userProduct.getUseEnddate(), DateUtils.FORMAT_1));
//        }
//
//        userProductList.setItems(userProducts);
//        return userProductList;
//    }
//
//
//    /**
//     * 사용자 구매 상품 상세 조회
//     *
//     * @param id the id
//     * @return UserProduct
//     */
//    public UserProduct getUserProduct(Long id) {
//        return restTemplateService.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_USER_PRODUCT + "/" + id, null, HttpMethod.GET, null, UserProduct.class);
//    }
//
//
//    /**
//     * 사용자 구매상품 등록
//     *
//     * @param userProduct the user product
//     * @return UserProduct
//     */
//    UserProduct createUserProduct(UserProduct userProduct) {
//        return restTemplateService.send(UserConstants.TARGET_API_MARKET, UserConstants.URI_API_USER_PRODUCT, null, HttpMethod.POST, userProduct, UserProduct.class);
//    }
//}
