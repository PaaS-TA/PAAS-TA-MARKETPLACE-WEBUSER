package org.openpaas.paasta.marketplace.web.user.common;

/**
 * Constants 클래스
 *
 * @author peter
 * @version 1.0
 * @since 2019.06.03
 */
public class UserConstants {

    // common
    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    public static final String RESULT_STATUS_FAIL = "FAIL";

    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String CF_AUTHORIZATION_HEADER_KEY = "cf-Authorization";
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String TARGET_API_CF = "cfApi";
    public static final String TARGET_API_MARKET = "marketApi";

//    public static final String MARKET_BASE_URL = "/";
    public static final String MARKET_INIT_URL = "/market";

    // general data
    public static final String GROUP_CODE_BUSINESS_TYPE = "BUSINESS_TYPE";

    public static final String URI_DB_CUSTOM_CODE_LIST = "/db/customCode/{groupCode}";
    public static final String URI_DB_CUSTOM_CODE_DETAIL_BY_UNIT_CODE = "/db/customCode/unitCode/{unitCode}";
    public static final String URI_DB_CATEGORY_LIST = "/db/category/list";
    public static final String URI_DB_CATEGORY_DETAIL = "/db/category/{id}/detail";
    public static final String URI_DB_PRODUCT_LIST = "/db/product/list";
    public static final String URI_DB_PRODUCT_DETAIL = "/db/product/{id}/detail";

    // cf api uri
    public static final String MARKET_USER_URL = "/user";

    // market api uri
    public static final String URI_API_BASE = "/api";
    public static final String URI_API_CUSTOM_CODE = "/api/customCode";
    public static final String URI_API_CATEGORY = "/api/category";
    public static final String URI_API_SELLER_PROFILE = "/api/seller/profile";
    public static final String URI_API_PRODUCT = "/api/seller/product";

    // market web user page uri
    public static final String URI_WEB_USER_PRODUCT_LIST = "/market/product";
    public static final String URI_WEB_USER_PRODUCT_DETAIL = "/market/product/{id}/detail";


    // market web user view file uri
    public static final String URI_VIEW_PRODUCT = "/product";


    private UserConstants() {
        throw new IllegalStateException();
    }

}
