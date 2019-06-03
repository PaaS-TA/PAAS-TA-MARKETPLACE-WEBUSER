package org.openpaas.paasta.marketplace.web.user.category;

import java.util.List;

import org.openpaas.paasta.marketplace.web.user.common.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryList extends BaseModel {

	private List<Category> items;

}
