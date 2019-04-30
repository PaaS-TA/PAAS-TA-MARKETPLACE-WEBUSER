package org.openpaas.paasta.marketplace.web.user.service;

import org.cloudfoundry.client.v3.organizations.ListOrganizationsResponse;
import org.openpaas.paasta.marketplace.web.user.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author hrjin
 * @version 1.0
 * @since 2019-03-27
 */
@Service
public class OrgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrgService.class);

//    @Autowired
//    RestTemplateService restTemplateService;

    @Resource(name = "cfJavaClientApiRest")
    RestTemplate cfJavaClientApiRest;


    /**
     * 조직명 중복검사를 실행한다.
     *
     * @param orgName the org name
     * @return boolean
     *
     * 권한 : 사용자
     */
    public boolean isExistOrgByOrgName(String orgName) {
        //return restTemplateService.send("/orgs/" + orgName + "/exist", null, HttpMethod.GET, null, Boolean.class);
        //return marketApiRest.getForObject("/orgs/" + orgName + "/exist", Boolean.class);
        return cfJavaClientApiRest.getForObject("/v3/orgs/" + orgName + "/exist", boolean.class);
    }


    /**
     * Org 생성 V3 (-> 쿼터 지정 부분이 없음.)
     *
     * @return Org
     */
    public Org createOrgV3(String orgName){
        //return restTemplateService.send("/orgs", token, HttpMethod.POST, org, Org.class);
        //return marketApiRest.postForObject("/orgs", org, Org.class);
        LOGGER.info("허허 이거 참 !!!!!!! 허참?! " + orgName);
        return cfJavaClientApiRest.postForObject("/v3/orgs", orgName, Org.class);
    }


    /**
     * 기존 Org 생성 V2 (-> 쿼터 지정 부분이 있음.)
     *
     * @param org the org
     * @return Map
     */
    public Map createOrgV2(Org org){
        //return restTemplateService.send("/orgs", token, HttpMethod.POST, org, Org.class);
        //return marketApiRest.postForObject("/orgs", org, Org.class);
        LOGGER.info("허허 이거 참 !!!!!!! 허참?! " + org.toString());
        return cfJavaClientApiRest.postForObject("/v2/orgs", org, Map.class);
    }


    /**
     * 관리자 권한으로 Org 목록 조회
     *
     * @return ListOrganizationsResponse
     */
    public ListOrganizationsResponse getOrgsListV3() {
        return cfJavaClientApiRest.getForObject("/v3/orgs-admin", ListOrganizationsResponse.class);
    }
}
