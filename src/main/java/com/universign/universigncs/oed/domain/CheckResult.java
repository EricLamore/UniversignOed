package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

import com.universign.universigncs.oed.domain.enumeration.Status;

import com.universign.universigncs.oed.domain.enumeration.CheckStatus;

/**
 * A CheckResult.
 */
@Document(collection = "check_result")
public class CheckResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("check_error_message")
    private String checkErrorMessage;

    @Field("status")
    private Status status;

    @Field("result")
    private CheckStatus result;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckErrorMessage() {
        return checkErrorMessage;
    }

    public CheckResult checkErrorMessage(String checkErrorMessage) {
        this.checkErrorMessage = checkErrorMessage;
        return this;
    }

    public void setCheckErrorMessage(String checkErrorMessage) {
        this.checkErrorMessage = checkErrorMessage;
    }

    public Status getStatus() {
        return status;
    }

    public CheckResult status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CheckStatus getResult() {
        return result;
    }

    public CheckResult result(CheckStatus result) {
        this.result = result;
        return this;
    }

    public void setResult(CheckStatus result) {
        this.result = result;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckResult)) {
            return false;
        }
        return id != null && id.equals(((CheckResult) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckResult{" +
            "id=" + getId() +
            ", checkErrorMessage='" + getCheckErrorMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", result='" + getResult() + "'" +
            "}";
    }
}
