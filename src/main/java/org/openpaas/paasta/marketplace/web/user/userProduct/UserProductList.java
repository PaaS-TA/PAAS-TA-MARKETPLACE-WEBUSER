package org.openpaas.paasta.marketplace.web.user.userProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openpaas.paasta.marketplace.web.user.common.BaseModel;

import java.util.List;

/**
 * @author hrjin
 * @version 1.0
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserProductList extends BaseModel {
    List<UserProduct> items;

    int total;
    int start;
    int display;
    int page;
    int size;
    int totalPages;
    long totalElements;
    boolean isLast;
}
