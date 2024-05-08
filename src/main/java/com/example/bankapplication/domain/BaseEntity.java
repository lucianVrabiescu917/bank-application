package com.example.bankapplication.domain;

import com.example.bankapplication.utils.UuidUtils;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "sequence", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence", allocationSize = 10)
    protected Long id;

    @CreatedDate
    @jakarta.persistence.Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    protected String uuid = UuidUtils.generateUuid();

    @Version
    @Access(AccessType.FIELD)
    protected Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o.getClass().isInstance(this) || this.getClass().isInstance(o))) return false;

        return uuid.equals(((BaseEntity) o).getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
