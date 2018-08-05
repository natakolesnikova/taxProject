package com.tax.entity;

import java.io.Serializable;
import java.util.Objects;

public class Inspector implements Serializable {
    private int id;
    private int workNumber;
    private int workStatus;
    private int userId;

    public Inspector() {
    }

    public static class Builder {
        private int id;
        private int workNumber;
        private int workStatus;
        private int userId;

        public Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setWorkNumber(int workNumber) {
            this.workNumber = workNumber;
            return this;
        }

        public Builder setWorkStatus(int workStatus) {
            this.workStatus = workStatus;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Inspector build() {
            Inspector inspector = new Inspector();
            inspector.id = id;
            inspector.workNumber = workNumber;
            inspector.workStatus = workStatus;
            inspector.userId = userId;
            return inspector;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inspector)) return false;
        Inspector inspector = (Inspector) o;
        return getId() == inspector.getId() &&
                getWorkNumber() == inspector.getWorkNumber() &&
                getWorkStatus() == inspector.getWorkStatus() &&
                getUserId() == inspector.getUserId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getWorkNumber(), getWorkStatus(), getUserId());
    }
}
