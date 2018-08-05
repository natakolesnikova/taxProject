package com.tax.entity;

import java.io.Serializable;
import java.util.Objects;

public class User  implements Serializable {
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private int userRoleId;

    public User() {}

    public static class Builder {
        private int id;
        private String firstName;
        private String secondName;
        private String email;
        private String password;
        private int userRoleId;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUserRoleId(int userRoleId) {
            this.userRoleId = userRoleId;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = id;
            user.firstName = firstName;
            user.secondName = secondName;
            user.email = email;
            user.password = password;
            user.userRoleId = userRoleId;
            return user;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getUserRoleId() == user.getUserRoleId() &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getSecondName(), user.getSecondName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getFirstName(), getSecondName(), getEmail(), getPassword(), getUserRoleId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRoleId=" + userRoleId +
                '}';
    }
}

