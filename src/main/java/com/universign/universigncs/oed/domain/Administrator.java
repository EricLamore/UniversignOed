package com.universign.universigncs.oed.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.universign.universigncs.oed.domain.enumeration.Role;

import com.universign.universigncs.oed.domain.enumeration.AdministratorStatus;

/**
 * A Administrator.
 */
@Document(collection = "administrator")
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("first_name")
    private String firstName;

    @NotNull
    @Field("last_name")
    private String lastName;

    @NotNull
    @Field("email_address")
    private String emailAddress;

    @Field("birth_date")
    private LocalDate birthDate;

    @Field("role")
    private Role role;

    @NotNull
    @Field("password")
    private String password;

    @Field("administrator_status")
    private AdministratorStatus administratorStatus;

    @Field("admin_fonction")
    private String adminFonction;

    @Field("phone")
    private String phone;

    @Field("mobile_phone")
    private String mobilePhone;

    @Field("description")
    private String description;

    @Field("universign_admin")
    private Boolean universignAdmin;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Administrator firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Administrator lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Administrator emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Administrator birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public Administrator role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public Administrator password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdministratorStatus getAdministratorStatus() {
        return administratorStatus;
    }

    public Administrator administratorStatus(AdministratorStatus administratorStatus) {
        this.administratorStatus = administratorStatus;
        return this;
    }

    public void setAdministratorStatus(AdministratorStatus administratorStatus) {
        this.administratorStatus = administratorStatus;
    }

    public String getAdminFonction() {
        return adminFonction;
    }

    public Administrator adminFonction(String adminFonction) {
        this.adminFonction = adminFonction;
        return this;
    }

    public void setAdminFonction(String adminFonction) {
        this.adminFonction = adminFonction;
    }

    public String getPhone() {
        return phone;
    }

    public Administrator phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public Administrator mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDescription() {
        return description;
    }

    public Administrator description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isUniversignAdmin() {
        return universignAdmin;
    }

    public Administrator universignAdmin(Boolean universignAdmin) {
        this.universignAdmin = universignAdmin;
        return this;
    }

    public void setUniversignAdmin(Boolean universignAdmin) {
        this.universignAdmin = universignAdmin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Administrator)) {
            return false;
        }
        return id != null && id.equals(((Administrator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Administrator{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", role='" + getRole() + "'" +
            ", password='" + getPassword() + "'" +
            ", administratorStatus='" + getAdministratorStatus() + "'" +
            ", adminFonction='" + getAdminFonction() + "'" +
            ", phone='" + getPhone() + "'" +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", description='" + getDescription() + "'" +
            ", universignAdmin='" + isUniversignAdmin() + "'" +
            "}";
    }
}
