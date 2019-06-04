package org.openpaas.paasta.marketplace.web.user.code;

import java.util.List;

import org.openpaas.paasta.marketplace.web.user.common.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom Code Controller
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-05-08
 */
@RestController
@Slf4j
public class CustomCodeController {

    @Autowired
    private CustomCodeService customCodeService;

    /**
     * GroupCode 로 단위코드 목록 조회
     *
     * @param groupCode the group code
     * @return CustomCodeList
     */
    @GetMapping(value = UserConstants.URI_WEB_CUSTOM_CODE_LIST)
    public CustomCodeList getUnitCodeListByGroupCode(@PathVariable String groupCode){
    	log.info("custom code");
        return customCodeService.getUnitCodeListByGroupCode(groupCode);
    }

}
