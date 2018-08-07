package com.tax.entity;

import java.io.Serializable;
import java.util.Objects;

public class TaxPayer extends User implements Serializable {
    private int id;
    private String passwordSerialNumber;
    private int passportNumber;
    private int identificationCode;
    private int userId;
    private int inspectorId;

    public static class Builder {
        private int id;
        private String passwordSerialNumber;
        private int passportNumber;
        private int identificationCode;
        private int userId;
        private int inspectorId;

        public Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPasswordSerialNumber(String passwordSerialNumber) {
            this.passwordSerialNumber = passwordSerialNumber;
            return this;
        }

        public Builder setPassportNumber(int passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        public Builder setIdentificationCode(int identificationCode) {
            this.identificationCode = identificationCode;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setInspectorId(int inspectorId) {
            this.inspectorId = inspectorId;
            return this;
        }

        public TaxPayer build() {
            TaxPayer taxPayer = new TaxPayer();
            taxPayer.id = id;
            taxPayer.passwordSerialNumber = passwordSerialNumber;
            taxPayer.passportNumber = passportNumber;
            taxPayer.identificationCode = identificationCode;
            taxPayer.userId = userId;
            taxPayer.inspectorId = inspectorId;
            return taxPayer;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswordSerialNumber() {
        return passwordSerialNumber;
    }

    public void setPasswordSerialNumber(String passwordSerialNumber) {
        this.passwordSerialNumber = passwordSerialNumber;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(int identificationCode) {
        this.identificationCode = identificationCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxPayer)) return false;
        TaxPayer taxPayer = (TaxPayer) o;
        return id == taxPayer.id &&
                getPassportNumber() == taxPayer.getPassportNumber() &&
                getIdentificationCode() == taxPayer.getIdentificationCode() &&
                getUserId() == taxPayer.getUserId() &&
                getInspectorId() == taxPayer.getInspectorId() &&
                Objects.equals(getPasswordSerialNumber(), taxPayer.getPasswordSerialNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, getPasswordSerialNumber(), getPassportNumber(), getIdentificationCode(), getUserId(), getInspectorId());
    }

    @Override
    public String toString() {
        return "TaxPayer{" +
                "id=" + id +
                ", passwordSerialNumber='" + passwordSerialNumber + '\'' +
                ", passportNumber=" + passportNumber +
                ", identificationCode=" + identificationCode +
                ", userId=" + userId +
                ", inspectorId=" + inspectorId +
                '}';
    }
}
