package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A AdminPermissions.
 */
@Document(collection = "admin_permissions")
public class AdminPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("oed_write")
    private Boolean oedWrite;

    @Field("group_write")
    private Boolean groupWrite;

    @Field("admin_write")
    private Boolean adminWrite;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isOedWrite() {
        return oedWrite;
    }

    public AdminPermissions oedWrite(Boolean oedWrite) {
        this.oedWrite = oedWrite;
        return this;
    }

    public void setOedWrite(Boolean oedWrite) {
        this.oedWrite = oedWrite;
    }

    public Boolean isGroupWrite() {
        return groupWrite;
    }

    public AdminPermissions groupWrite(Boolean groupWrite) {
        this.groupWrite = groupWrite;
        return this;
    }

    public void setGroupWrite(Boolean groupWrite) {
        this.groupWrite = groupWrite;
    }

    public Boolean isAdminWrite() {
        return adminWrite;
    }

    public AdminPermissions adminWrite(Boolean adminWrite) {
        this.adminWrite = adminWrite;
        return this;
    }

    public void setAdminWrite(Boolean adminWrite) {
        this.adminWrite = adminWrite;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdminPermissions)) {
            return false;
        }
        return id != null && id.equals(((AdminPermissions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminPermissions{" +
            "id=" + getId() +
            ", oedWrite='" + isOedWrite() + "'" +
            ", groupWrite='" + isGroupWrite() + "'" +
            ", adminWrite='" + isAdminWrite() + "'" +
            "}";
    }
}
