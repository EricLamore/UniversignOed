package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @DBRef
    @Field("groupConfiguration")
    private GroupConfiguration groupConfiguration;

    @DBRef
    @Field("parent")
    private Group parent;

    @DBRef
    @Field("permissions")
    private GroupPermission permissions;

    @DBRef
    @Field("administrators")
    private Set<Link> administrators = new HashSet<>();

    @DBRef
    @Field("operators")
    private Set<Link> operators = new HashSet<>();

    @DBRef
    @Field("affiliatedGroups")
    private Set<Link> affiliatedGroups = new HashSet<>();

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

    public GroupConfiguration getGroupConfiguration() {
        return groupConfiguration;
    }

    public Group groupConfiguration(GroupConfiguration groupConfiguration) {
        this.groupConfiguration = groupConfiguration;
        return this;
    }

    public void setGroupConfiguration(GroupConfiguration groupConfiguration) {
        this.groupConfiguration = groupConfiguration;
    }

    public Group getParent() {
        return parent;
    }

    public Group parent(Group group) {
        this.parent = group;
        return this;
    }

    public void setParent(Group group) {
        this.parent = group;
    }

    public GroupPermission getPermissions() {
        return permissions;
    }

    public Group permissions(GroupPermission groupPermission) {
        this.permissions = groupPermission;
        return this;
    }

    public void setPermissions(GroupPermission groupPermission) {
        this.permissions = groupPermission;
    }

    public Set<Link> getAdministrators() {
        return administrators;
    }

    public Group administrators(Set<Link> links) {
        this.administrators = links;
        return this;
    }

    public Group addAdministrators(Link link) {
        this.administrators.add(link);
        link.setGroup(this);
        return this;
    }

    public Group removeAdministrators(Link link) {
        this.administrators.remove(link);
        link.setGroup(null);
        return this;
    }

    public void setAdministrators(Set<Link> links) {
        this.administrators = links;
    }

    public Set<Link> getOperators() {
        return operators;
    }

    public Group operators(Set<Link> links) {
        this.operators = links;
        return this;
    }

    public Group addOperators(Link link) {
        this.operators.add(link);
        link.setGroup(this);
        return this;
    }

    public Group removeOperators(Link link) {
        this.operators.remove(link);
        link.setGroup(null);
        return this;
    }

    public void setOperators(Set<Link> links) {
        this.operators = links;
    }

    public Set<Link> getAffiliatedGroups() {
        return affiliatedGroups;
    }

    public Group affiliatedGroups(Set<Link> links) {
        this.affiliatedGroups = links;
        return this;
    }

    public Group addAffiliatedGroups(Link link) {
        this.affiliatedGroups.add(link);
        link.setGroup(this);
        return this;
    }

    public Group removeAffiliatedGroups(Link link) {
        this.affiliatedGroups.remove(link);
        link.setGroup(null);
        return this;
    }

    public void setAffiliatedGroups(Set<Link> links) {
        this.affiliatedGroups = links;
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
            "}";
    }
}
