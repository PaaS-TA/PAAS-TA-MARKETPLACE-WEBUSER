package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Software extends AbstractEntity {

    private Long id;

    @NotNull
//    @Size(min=8, max = 100)
    private String name;

    private Category category;

    private String categoryId;

    private List<Screenshot> screenshotList;

    private String description;

    private String summary;

    // 아이콘 파일 이름
    private String icon;

    // SW파일 파일 이름
    private String app;

    private String manifest;

    //    @NotNull
    private Type type;

    private String version;

    private Integer pricePerDay;

}