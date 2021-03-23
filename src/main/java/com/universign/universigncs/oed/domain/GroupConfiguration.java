package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

/**
 * A GroupConfiguration.
 */
@Document(collection = "group_configuration")
public class GroupConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("header")
    private HeaderConfiguration header;

    @DBRef
    @Field("i18n")
    private MapProperties i18n;

    @DBRef
    @Field("metaDatas")
    private MapProperties metaDatas;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HeaderConfiguration getHeader() {
        return header;
    }

    public GroupConfiguration header(HeaderConfiguration headerConfiguration) {
        this.header = headerConfiguration;
        return this;
    }

    public void setHeader(HeaderConfiguration headerConfiguration) {
        this.header = headerConfiguration;
    }

    public MapProperties getI18n() {
        return i18n;
    }

    public GroupConfiguration i18n(MapProperties mapProperties) {
        this.i18n = mapProperties;
        return this;
    }

    public void setI18n(MapProperties mapProperties) {
        this.i18n = mapProperties;
    }

    public MapProperties getMetaDatas() {
        return metaDatas;
    }

    public GroupConfiguration metaDatas(MapProperties mapProperties) {
        this.metaDatas = mapProperties;
        return this;
    }

    public void setMetaDatas(MapProperties mapProperties) {
        this.metaDatas = mapProperties;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupConfiguration)) {
            return false;
        }
        return id != null && id.equals(((GroupConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupConfiguration{" +
            "id=" + getId() +
            "}";
    }
}
