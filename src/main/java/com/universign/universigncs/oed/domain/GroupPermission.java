package com.universign.universigncs.oed.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A GroupPermission.
 */
@Document(collection = "group_permission")
public class GroupPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("move_operators_to_universign_organization")
    private Boolean moveOperatorsToUniversignOrganization;

    @Field("allow_customization")
    private Boolean allowCustomization;

    @Field("allow_affiliated_group")
    private Boolean allowAffiliatedGroup;

    @Field("allow_affiliated_customization")
    private Boolean allowAffiliatedCustomization;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isMoveOperatorsToUniversignOrganization() {
        return moveOperatorsToUniversignOrganization;
    }

    public GroupPermission moveOperatorsToUniversignOrganization(Boolean moveOperatorsToUniversignOrganization) {
        this.moveOperatorsToUniversignOrganization = moveOperatorsToUniversignOrganization;
        return this;
    }

    public void setMoveOperatorsToUniversignOrganization(Boolean moveOperatorsToUniversignOrganization) {
        this.moveOperatorsToUniversignOrganization = moveOperatorsToUniversignOrganization;
    }

    public Boolean isAllowCustomization() {
        return allowCustomization;
    }

    public GroupPermission allowCustomization(Boolean allowCustomization) {
        this.allowCustomization = allowCustomization;
        return this;
    }

    public void setAllowCustomization(Boolean allowCustomization) {
        this.allowCustomization = allowCustomization;
    }

    public Boolean isAllowAffiliatedGroup() {
        return allowAffiliatedGroup;
    }

    public GroupPermission allowAffiliatedGroup(Boolean allowAffiliatedGroup) {
        this.allowAffiliatedGroup = allowAffiliatedGroup;
        return this;
    }

    public void setAllowAffiliatedGroup(Boolean allowAffiliatedGroup) {
        this.allowAffiliatedGroup = allowAffiliatedGroup;
    }

    public Boolean isAllowAffiliatedCustomization() {
        return allowAffiliatedCustomization;
    }

    public GroupPermission allowAffiliatedCustomization(Boolean allowAffiliatedCustomization) {
        this.allowAffiliatedCustomization = allowAffiliatedCustomization;
        return this;
    }

    public void setAllowAffiliatedCustomization(Boolean allowAffiliatedCustomization) {
        this.allowAffiliatedCustomization = allowAffiliatedCustomization;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupPermission)) {
            return false;
        }
        return id != null && id.equals(((GroupPermission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GroupPermission{" +
            "id=" + getId() +
            ", moveOperatorsToUniversignOrganization='" + isMoveOperatorsToUniversignOrganization() + "'" +
            ", allowCustomization='" + isAllowCustomization() + "'" +
            ", allowAffiliatedGroup='" + isAllowAffiliatedGroup() + "'" +
            ", allowAffiliatedCustomization='" + isAllowAffiliatedCustomization() + "'" +
            "}";
    }
}
