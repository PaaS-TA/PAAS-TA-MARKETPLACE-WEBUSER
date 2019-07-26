package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.Data;

@Data
public class Sample {

    private String value;

    public Sample(String value) {
        this.value = value;
    }
}
