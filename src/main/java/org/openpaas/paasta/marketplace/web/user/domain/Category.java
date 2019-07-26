package org.openpaas.paasta.marketplace.web.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Category extends AbstractEntity {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    private Long seq;

    @JsonIgnore
    private List<Software> softwareList;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
