package org.openpaas.paasta.marketplace.web.user.service;


import org.openpaas.paasta.marketplace.web.user.model.Quota;
import org.openpaas.paasta.marketplace.web.user.model.QuotaList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Quota Service
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-03-29
 */
@Service
public class QuotaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrgService.class);

//    @Autowired
//    RestTemplateService restTemplateService;

    @Resource(name = "cfJavaClientApiRest")
    RestTemplate cfJavaClientApiRest;

    /**
     * Org Quota 생성
     *
     * @param quota the quota
     * @return Quota
     */
    public Quota createOrgQuota(Quota quota){
        //return restTemplateService.send("/orgs/quotas", null, HttpMethod.POST, quota, Quota.class);
        return cfJavaClientApiRest.postForObject("/v3/orgs/quota-definitions", quota, Quota.class);
    }

    /**
     * Org Quota 목록 조회
     *
     * @return QuotaList
     */
    public QuotaList getOrgQuotas() {
        //return restTemplateService.send("/orgs/quotas", null, HttpMethod.GET, null, QuotaList.class);
        //return marketApiRest.getForObject("/orgs/quotas", QuotaList.class);
        return cfJavaClientApiRest.getForObject("/v3/orgs/quota-definitions", QuotaList.class);
    }
}
