package com.cyber.db.entity;

public class Role {
    private final String nameId;
    private final String description;

    public Role(String nameId) {
        this(nameId, "");
    }

    public Role(String nameId, String description) {
        this.nameId = nameId;
        this.description = description;
    }

    public String getNameId() {
        return nameId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Role{" + nameId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return nameId.equals(role.nameId);
    }

    @Override
    public int hashCode() {
        return nameId.hashCode();
    }
}
