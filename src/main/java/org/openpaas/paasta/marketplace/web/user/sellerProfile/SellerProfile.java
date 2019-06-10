package org.openpaas.paasta.marketplace.web.user.sellerProfile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openpaas.paasta.marketplace.web.user.common.BaseModel;

/**
 * 판매자 프로필 도메인
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SellerProfile extends BaseModel {
    // ID
    private Long id;

    // 판매자 로그인ID
    private String sellerId;

    // 판매자명
    private String sellerName;

    // 업체유형
    private String businessType;

    // 관리자명
    private String managerName;

    // 이메일주소
    private String email;

    // 홈페이지주소
    private String homepageUrl;

    private String deleteYn;

}
