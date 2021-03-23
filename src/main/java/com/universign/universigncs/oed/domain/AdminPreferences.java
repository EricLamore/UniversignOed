package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AdminPreferences.
 */
@Document(collection = "admin_preferences")
public class AdminPreferences implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("filter")
    private String filter;

    @DBRef
    @Field("columns")
    private Set<Column> columns = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilter() {
        return filter;
    }

    public AdminPreferences filter(String filter) {
        this.filter = filter;
        return this;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public AdminPreferences columns(Set<Column> columns) {
        this.columns = columns;
        return this;
    }

    public AdminPreferences addColumns(Column column) {
        this.columns.add(column);
        column.setAdminPreferences(this);
        return this;
    }

    public AdminPreferences removeColumns(Column column) {
        this.columns.remove(column);
        column.setAdminPreferences(null);
        return this;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdminPreferences)) {
            return false;
        }
        return id != null && id.equals(((AdminPreferences) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdminPreferences{" +
            "id=" + getId() +
            ", filter='" + getFilter() + "'" +
            "}";
    }
}