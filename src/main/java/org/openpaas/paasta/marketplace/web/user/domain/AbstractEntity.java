package org.openpaas.paasta.marketplace.web.user.domain;

import lombok.Data;

import java.time.LocalDateTime;

//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractEntity {

    protected Yn inUse = Yn.Y;

    protected String createdBy;

    protected LocalDateTime createdDate;

    protected String lastModifiedBy;

    protected LocalDateTime lastModifiedDate;

    public void use() {
        this.inUse = Yn.Y;
    }

    public void unuse() {
        this.inUse = Yn.N;
    }

}
