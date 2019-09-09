package com.universign.universigncs.oed.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A HeaderConfiguration.
 */
@Document(collection = "header_configuration")
public class HeaderConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("logo")
    private String logo;

    @Field("width")
    private Integer width;

    @Field("href")
    private String href;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public HeaderConfiguration logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getWidth() {
        return width;
    }

    public HeaderConfiguration width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getHref() {
        return href;
    }

    public HeaderConfiguration href(String href) {
        this.href = href;
        return this;
    }

    public void setHref(String href) {
        this.href = href;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HeaderConfiguration)) {
            return false;
        }
        return id != null && id.equals(((HeaderConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HeaderConfiguration{" +
            "id=" + getId() +
            ", logo='" + getLogo() + "'" +
            ", width=" + getWidth() +
            ", href='" + getHref() + "'" +
            "}";
    }
}
