package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.Data;

@Data
public class Screenshot  extends AbstractEntity {

    private Long id;

    private String fileName;

    private Long seq;
}

