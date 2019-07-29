package org.openpaas.paasta.marketplace.web.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

@Data
public class SoftwareSpecification implements Specification<Software> {

    private static final long serialVersionUID = 1L;

    private String categoryId;

    private String nameLike;

    @Override
    public Predicate toPredicate(Root<Software> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = new ArrayList<>();
        if (categoryId != null) {
            restrictions.add(builder.equal(root.get("category").get("id"), categoryId));
        }
        if (nameLike != null) {
            restrictions.add(builder.like(root.get("name"), "%" + nameLike + "%"));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}