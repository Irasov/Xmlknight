package com.epam.irasov.xmlknight.entity;

import java.util.Comparator;
import java.util.UUID;

public abstract class NamedEntity extends BaseEntity {

    public static final Comparator<NamedEntity> NAME_ORDER = new NameComparator();

    public NamedEntity() {

    }

    public NamedEntity(Long id, UUID uuid, String name) {
        super(id, uuid);
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class NameComparator implements Comparator<NamedEntity> {
        public int compare(NamedEntity o1, NamedEntity o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedEntity)) return false;
        if (!super.equals(o)) return false;
        NamedEntity that = (NamedEntity) o;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString()+ "name: " + name+" |" ;
    }
}
