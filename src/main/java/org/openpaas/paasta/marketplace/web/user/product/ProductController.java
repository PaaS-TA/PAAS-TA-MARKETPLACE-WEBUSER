package org.openpaas.paasta.marketplace.web.user.product;

import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.openpaas.paasta.marketplace.web.user.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 상품 Controller
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-04
 */
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CommonService commonService;

    /**
     * 상품 페이지로 이동
     * @return ModelAndView(Spring 클래스)
     **/
    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserInfoMain() {
        return ProductService.getUserInfoMain();
    }

    /**
     * 상품 목록 조회 페이지 이동
     *
     * @param httpServletRequest the http servlet request
     * @return ModelAndView(Spring 클래스)
     */
    @GetMapping(value = UserConstants.URI_WEB_PRODUCT_LIST)
    @ResponseBody
    public ModelAndView getProductListPage(HttpServletRequest httpServletRequest,
                                           @RequestParam(value = "categoryId", required = false) Long categoryId,
                                           @RequestParam(value = "productName", required = false) String productName){
        return commonService.setPathVariables(httpServletRequest, UserConstants.URI_VIEW_PRODUCT + "/list", new ModelAndView());
    }

    /**
     * 상품 목록 조회
     *
     * @return ProductList
     */
    @GetMapping(value = UserConstants.URI_DB_PRODUCT_LIST) /*"/db/category/list";*/
    @ResponseBody
    public ProductList getProductList(HttpServletRequest httpServletRequest){
        return productService.getProductList(commonService.setParameters(httpServletRequest));
    }

    /**
     * 상품 상세 페이지 이동
     *
     * @param httpServletRequest the http servlet request
     * @param id the id
     * @return ModelAndView
     */
    @GetMapping(value = UserConstants.URI_WEB_PRODUCT_DETAIL)
    public ModelAndView getProductPage(HttpServletRequest httpServletRequest, @PathVariable(value = "id") Long id){
        return commonService.setPathVariables(httpServletRequest, UserConstants.URI_VIEW_PRODUCT + "/detail", new ModelAndView());
    }

    /**
     * 상품 상세 조회
     *
     * @param id the id
     * @return Product
     */
    @GetMapping(value = UserConstants.URI_DB_PRODUCT_DETAIL)
    public Product getProduct(@PathVariable(value = "id") Long id){
        return productService.getProduct(id);
    }

    /**
     * 아이콘 조회
     *
     * @param filePath the file path
     * @param fileName the file name
     * @return ResponseEntity
     * @throws Exception
     */
    @GetMapping(value = "/icon")
    public ResponseEntity<byte[]> displayIconFile(@RequestParam(value = "filePath") String filePath, @RequestParam("iconFileName") String fileName) throws Exception {
        return FileUtils.displayImageFile(filePath, fileName);
    }

    /**
     * 스크린샷 조회
     *
     * @param filePath the file path
     * @param fileName the file name
     * @return ResponseEntity
     * @throws Exception
     */
    @GetMapping(value = "/screenshots")
    public ResponseEntity<byte[]> displayScreenShotFile(@RequestParam(value = "filePath") String filePath, @RequestParam("screenshotFileName") String fileName) throws Exception {
        return FileUtils.displayImageFile(filePath, fileName);
    }

}
