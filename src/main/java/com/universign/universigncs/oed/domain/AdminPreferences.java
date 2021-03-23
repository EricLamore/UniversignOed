package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

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
