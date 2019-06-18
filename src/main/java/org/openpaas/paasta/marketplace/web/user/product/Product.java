package org.openpaas.paasta.marketplace.web.user.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openpaas.paasta.marketplace.web.user.category.Category;
import org.openpaas.paasta.marketplace.web.user.common.BaseModel;
import org.openpaas.paasta.marketplace.web.user.sellerProfile.SellerProfile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 상품 도메인
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseModel {
    private Long id;

    private Category category;

    private Long categoryId;

    private SellerProfile seller;

    private String sellerId;

    private List<Screenshot> screenshots;

    // 상품명
    private String productName;

    // 버전정보
    private String versionInfo;

    // 상품개요
    private String simpleDescription;

    // 상품상세
    private String detailDescription;

    // 상품유형
    private SwType productType;

    // 파일경로
    private String filePath;

//    @Transient: 데이터를 디스크에 저장하거나 디비에 저장하거나 http request를 통해 보내거나 할때 민감한 데이터(개인정보등)은 제외하고 싶을 때 사용
//    private File appIcon;

    // 아이콘 파일 이름
    private String iconFileName;

    // 상품 파일 이름
    private String productFileName;

    // 환경 파일 이름
    private String envFileName;

    // 미터링 유형
    private String meteringType;

    // 미터링 금액
    private int unitPrice;

    // 전시여부
    private String displayYn;

    // 승인상태
    private String approvalStatus;

    // 반려 사유
    private String rejectReason;

    // 승인 일자
    private LocalDateTime approvalDate;

    private String deleteYn;


    public enum SwType {
        WEB, API,
    };
}
