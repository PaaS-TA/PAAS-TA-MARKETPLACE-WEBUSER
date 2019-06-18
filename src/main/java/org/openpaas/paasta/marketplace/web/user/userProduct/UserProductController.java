package org.openpaas.paasta.marketplace.web.user.userProduct;

import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private CommonService commonService;


    /**
     * 사용자 구매 상품 목록 조회 페이지 이동
     *
     * @param httpServletRequest the http servlet request
     * @return ModelAndView
     */
    @GetMapping(value = UserConstants.URI_WEB_USER_PRODUCT_LIST)
    public ModelAndView getUserProductListPage(HttpServletRequest httpServletRequest,
                                           @RequestParam(value = "userId", defaultValue = "") String userId,
                                           @RequestParam(value = "categoryId", required = false) Long categoryId,
                                           @RequestParam(value = "productName", required = false) String productName) {
        return commonService.setPathVariables(httpServletRequest, UserConstants.URI_VIEW_USER_PRODUCT + "/list", new ModelAndView());
    }


    /**
     * 사용자 구매 상품 목록 조회
     *
     * @param httpServletRequest the http servlet request
     * @return UserProductList
     */
    @GetMapping(value = UserConstants.URI_DB_USER_PRODUCT_LIST)
    public UserProductList getUserProductList(HttpServletRequest httpServletRequest){
        return userProductService.getUserProductList(commonService.setParameters(httpServletRequest));
    }


    /**
     * 사용자 구매 상품 상세 조회 페이지 이동
     *
     * @param httpServletRequest the http servlet request
     * @param id the id
     * @return ModelAndView
     */
    @GetMapping(value = UserConstants.URI_WEB_USER_PRODUCT_DETAIL)
    public ModelAndView getUserProduct(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id){
        return commonService.setPathVariables(httpServletRequest, UserConstants.URI_VIEW_USER_PRODUCT + "/detail", new ModelAndView());
    }


    /**
     * 사용자 구매 상품 상세 조회
     *
     * @param id the id
     * @return UserProduct
     */
    @GetMapping(value = UserConstants.URI_DB_USER_PRODUCT_DETAIL)
    public UserProduct getUserProduct(@PathVariable(value = "id") Long id){
        return userProductService.getUserProduct(id);
    }


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
