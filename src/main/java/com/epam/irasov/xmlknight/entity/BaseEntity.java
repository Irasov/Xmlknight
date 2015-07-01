package com.epam.irasov.xmlknight.entity;

import java.util.UUID;

public abstract class BaseEntity {
    private Long id;
    private UUID uuid;

    public BaseEntity() {

    }

    public BaseEntity(Long id, UUID uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(uuid != null ? !uuid.equals(that.uuid) : that.uuid != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", uuid=" + uuid +
                '}';
    }
}
