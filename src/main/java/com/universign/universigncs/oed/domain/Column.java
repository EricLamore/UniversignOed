package com.universign.universigncs.oed.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

/**
 * A Column.
 */
@Document(collection = "column")
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("show")
    private Boolean show;

    @Field("name")
    private String name;

    @Field("type")
    private String type;

    @DBRef
    @Field("adminPreferences")
    @JsonIgnoreProperties(value = "columns", allowSetters = true)
    private AdminPreferences adminPreferences;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isShow() {
        return show;
    }

    public Column show(Boolean show) {
        this.show = show;
        return this;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getName() {
        return name;
    }

    public Column name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Column type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AdminPreferences getAdminPreferences() {
        return adminPreferences;
    }

    public Column adminPreferences(AdminPreferences adminPreferences) {
        this.adminPreferences = adminPreferences;
        return this;
    }

    public void setAdminPreferences(AdminPreferences adminPreferences) {
        this.adminPreferences = adminPreferences;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Column)) {
            return false;
        }
        return id != null && id.equals(((Column) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Column{" +
            "id=" + getId() +
            ", show='" + isShow() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
