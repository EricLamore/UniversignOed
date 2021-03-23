package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.universign.universigncs.oed.domain.enumeration.GroupStatus;

/**
 * A Group.
 */
@Document(collection = "group")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("universign_organization_id")
    private String universignOrganizationId;

    @Field("status")
    private GroupStatus status;

    @Field("activate_advanced")
    private Boolean activateAdvanced;

    @Field("universign_organization_profil")
    private String universignOrganizationProfil;

    @Field("created_at")
    private Instant createdAt;

    @Field("last_updated_at")
    private Instant lastUpdatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Group name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversignOrganizationId() {
        return universignOrganizationId;
    }

    public Group universignOrganizationId(String universignOrganizationId) {
        this.universignOrganizationId = universignOrganizationId;
        return this;
    }

    public void setUniversignOrganizationId(String universignOrganizationId) {
        this.universignOrganizationId = universignOrganizationId;
    }

    public GroupStatus getStatus() {
        return status;
    }

    public Group status(GroupStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(GroupStatus status) {
        this.status = status;
    }

    public Boolean isActivateAdvanced() {
        return activateAdvanced;
    }

    public Group activateAdvanced(Boolean activateAdvanced) {
        this.activateAdvanced = activateAdvanced;
        return this;
    }

    public void setActivateAdvanced(Boolean activateAdvanced) {
        this.activateAdvanced = activateAdvanced;
    }

    public String getUniversignOrganizationProfil() {
        return universignOrganizationProfil;
    }

    public Group universignOrganizationProfil(String universignOrganizationProfil) {
        this.universignOrganizationProfil = universignOrganizationProfil;
        return this;
    }

    public void setUniversignOrganizationProfil(String universignOrganizationProfil) {
        this.universignOrganizationProfil = universignOrganizationProfil;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Group createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public Group lastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }
        return id != null && id.equals(((Group) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Group{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", universignOrganizationId='" + getUniversignOrganizationId() + "'" +
            ", status='" + getStatus() + "'" +
            ", activateAdvanced='" + isActivateAdvanced() + "'" +
            ", universignOrganizationProfil='" + getUniversignOrganizationProfil() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", lastUpdatedAt='" + getLastUpdatedAt() + "'" +
            "}";
    }
}
