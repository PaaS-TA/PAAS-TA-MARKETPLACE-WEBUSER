package org.openpaas.paasta.marketplace.web.user.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Common Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.02
 */
@Slf4j
@Service
public class CommonService {

    @Value("${cf.admin.name}")
    private String cfAdminName;

    @Value("${cf.admin.password}")
    private String cfAdminPassword;

    @Autowired
    private RestTemplateService cfApiRest;

    /**
     * CF admin token 받아오는 로직
     *
     * @return String
     */
    public String getAdminToken(){
        Map<String, Object> cfAdminInfo = new HashMap<>();
        cfAdminInfo.put("id", cfAdminName);
        cfAdminInfo.put("password", cfAdminPassword);

        Map result = cfApiRest.send(UserConstants.TARGET_API_CF, "/login", null, HttpMethod.POST, cfAdminInfo, Map.class);

        log.info("admin token ::: {}", result.get("token"));

        return (String) result.get("token");
    }


    /**
     * Sets parameters.
     *
     * @param httpServletRequest the http servlet request
     * @return the parameters
     */
    public String setParameters(HttpServletRequest httpServletRequest) {
        Map<String, String[]> parametersMap = httpServletRequest.getParameterMap();
        String[] parametersObject;
        String parametersKey;
        String resultString = "";

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < ParametersList.values().length; i++) {
            parametersKey = ParametersList.values()[i].actualValue;
            parametersObject = parametersMap.get(parametersKey);

            if (parametersObject != null && !"".equals(parametersObject[0])) {
                stringBuilder.append("&").append(parametersKey).append("=").append(parametersObject[0]);
            }
        }

        if (stringBuilder.length() > 0) {
            resultString = "?" + stringBuilder.substring(1);
        }

        return resultString;
    }



    /**
     * Sets path variables.
     *
     * @param httpServletRequest the http servlet request
     * @param viewName           the view name
     * @param mv                 the mv
     * @return the path variables
     */
    public ModelAndView setPathVariables(HttpServletRequest httpServletRequest, String viewName, ModelAndView mv) {
        Map pathVariablesMap = (Map) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Object pathVariablesObject;
        String pathVariablesKey;

        Map<String, String[]> parametersMap = httpServletRequest.getParameterMap();
        String[] parametersObject;
        String parametersKey;

        for (int i = 0; i < PathVariablesList.values().length; i++) {
            pathVariablesKey = PathVariablesList.values()[i].actualValue;
            pathVariablesObject = pathVariablesMap.get(pathVariablesKey);

            if (pathVariablesObject != null) {
                mv.addObject(pathVariablesKey, String.valueOf(pathVariablesObject));
            }
        }

        for (int i = 0; i < ParametersList.values().length; i++) {
            parametersKey = ParametersList.values()[i].actualValue;
            parametersObject = parametersMap.get(parametersKey);

            if (parametersObject != null && !"".equals(parametersObject[0])) {
                mv.addObject(parametersKey, parametersObject[0]);
            }
        }

        mv.setViewName(viewName);

        return mv;
    }


    /**
     * The enum Path variables list.
     */
    enum PathVariablesList {
        /**
         * Path service name path variables list.
         */
        PATH_SERVICE_NAME("serviceName"),
        /**
         * Path variables id path variables list.
         */
        PATH_VARIABLES_ID("id");

        private String actualValue;

        PathVariablesList(String actualValue) {
            this.actualValue = actualValue;
        }
    }


    /**
     * The enum Parameters list.
     */
    enum ParametersList {

        PARAMETERS_USER_ID("userId"),

        PARAMETERS_CATEGORY_ID("categoryId"),

        PARAMETERS_PRODUCT_NAME("productName"),

        /**
         * Parameters id parameters list.
         */
        PARAMETERS_ID("id"),
        /**
         * Parameters name parameters list.
         */
        PARAMETERS_NAME("name"),
        /**
         * Parameters page parameters list.
         */
        PARAMETERS_PAGE("page"),
        /**
         * Parameters size parameters list.
         */
        PARAMETERS_SIZE("size"),
        /**
         * Parameters sort parameters list.
         */
        PARAMETERS_SORT("sort");

        private String actualValue;

        ParametersList(String actualValue) {
            this.actualValue = actualValue;
        }
    }

}
