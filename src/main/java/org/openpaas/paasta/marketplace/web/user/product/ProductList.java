package org.openpaas.paasta.marketplace.web.user.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openpaas.paasta.marketplace.web.user.common.BaseModel;

import java.util.List;

/**
 * 상품 목록 도메인
 *
 * @author hrjin
 * @version 1.0
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ProductList extends BaseModel {
    List<Product> items;

    int total;
    int start;
    int display;
    int page;
    int size;
    int totalPages;
    long totalElements;
    boolean isLast;
}
