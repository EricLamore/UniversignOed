package com.universign.universigncs.oed.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A MapProperties.
 */
@Document(collection = "map_properties")
public class MapProperties implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dummy")
    private String dummy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDummy() {
        return dummy;
    }

    public MapProperties dummy(String dummy) {
        this.dummy = dummy;
        return this;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MapProperties)) {
            return false;
        }
        return id != null && id.equals(((MapProperties) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MapProperties{" +
            "id=" + getId() +
            ", dummy='" + getDummy() + "'" +
            "}";
    }
}
