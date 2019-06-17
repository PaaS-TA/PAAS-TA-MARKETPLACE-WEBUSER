package org.openpaas.paasta.marketplace.web.user.userProduct;

import lombok.Data;
import org.openpaas.paasta.marketplace.web.user.product.Product;

import java.time.LocalDateTime;

@Data
public class UserProduct {

    private Long id;

	// 사용자ID
	private String userId;

	// 상품 ID
    private Long productId;

    // 구매한 상품 정보
    private Product product;

    // 사용자명
    private String userName;
    
    // 상품명
    private String productName;
    
    // 미터링 유형
    private String meteringType;

    // 요금
    private int unitPrice;

    // 구매상태
    private String provisionStatus;
    
    // 사용시작일자
    private LocalDateTime useStartdate;
    
    // 사용종료일자
    private LocalDateTime useEnddate;
    
    // 접속URL
    private String accessUrl;

    private String uuid;

    private Integer provisionTryCount = 0;

    private Integer deprovisionTryCount = 0;

}
