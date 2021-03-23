package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.universign.universigncs.oed.domain.enumeration.OpearatorStatus;

/**
 * A Operator.
 */
@Document(collection = "operator")
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("status")
    private OpearatorStatus status;

    @Field("phone_number")
    private String phoneNumber;

    @Field("invitation_date")
    private LocalDate invitationDate;

    @Field("invitation_url")
    private String invitationUrl;

    @Field("update_access_date")
    private Boolean updateAccessDate;

    @Field("access_date")
    private LocalDate accessDate;

    @Field("update_training_date")
    private Boolean updateTrainingDate;

    @Field("training_date")
    private LocalDate trainingDate;

    @Field("has_succeeded_mcq")
    private Boolean hasSucceededMCQ;

    @Field("update_qcm_date")
    private Boolean updateQCMDate;

    @Field("mcq_date")
    private LocalDate mcqDate;

    @Field("training_session_id")
    private String trainingSessionId;

    @Field("training_session_url")
    private String trainingSessionURL;

    @Field("session_id")
    private String sessionId;

    @Field("group_id")
    private String groupId;

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

    public OpearatorStatus getStatus() {
        return status;
    }

    public Operator status(OpearatorStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OpearatorStatus status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Operator phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getInvitationDate() {
        return invitationDate;
    }

    public Operator invitationDate(LocalDate invitationDate) {
        this.invitationDate = invitationDate;
        return this;
    }

    public void setInvitationDate(LocalDate invitationDate) {
        this.invitationDate = invitationDate;
    }

    public String getInvitationUrl() {
        return invitationUrl;
    }

    public Operator invitationUrl(String invitationUrl) {
        this.invitationUrl = invitationUrl;
        return this;
    }

    public void setInvitationUrl(String invitationUrl) {
        this.invitationUrl = invitationUrl;
    }

    public Boolean isUpdateAccessDate() {
        return updateAccessDate;
    }

    public Operator updateAccessDate(Boolean updateAccessDate) {
        this.updateAccessDate = updateAccessDate;
        return this;
    }

    public void setUpdateAccessDate(Boolean updateAccessDate) {
        this.updateAccessDate = updateAccessDate;
    }

    public LocalDate getAccessDate() {
        return accessDate;
    }

    public Operator accessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
        return this;
    }

    public void setAccessDate(LocalDate accessDate) {
        this.accessDate = accessDate;
    }

    public Boolean isUpdateTrainingDate() {
        return updateTrainingDate;
    }

    public Operator updateTrainingDate(Boolean updateTrainingDate) {
        this.updateTrainingDate = updateTrainingDate;
        return this;
    }

    public void setUpdateTrainingDate(Boolean updateTrainingDate) {
        this.updateTrainingDate = updateTrainingDate;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public Operator trainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
        return this;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Boolean isHasSucceededMCQ() {
        return hasSucceededMCQ;
    }

    public Operator hasSucceededMCQ(Boolean hasSucceededMCQ) {
        this.hasSucceededMCQ = hasSucceededMCQ;
        return this;
    }

    public void setHasSucceededMCQ(Boolean hasSucceededMCQ) {
        this.hasSucceededMCQ = hasSucceededMCQ;
    }

    public Boolean isUpdateQCMDate() {
        return updateQCMDate;
    }

    public Operator updateQCMDate(Boolean updateQCMDate) {
        this.updateQCMDate = updateQCMDate;
        return this;
    }

    public void setUpdateQCMDate(Boolean updateQCMDate) {
        this.updateQCMDate = updateQCMDate;
    }

    public LocalDate getMcqDate() {
        return mcqDate;
    }

    public Operator mcqDate(LocalDate mcqDate) {
        this.mcqDate = mcqDate;
        return this;
    }

    public void setMcqDate(LocalDate mcqDate) {
        this.mcqDate = mcqDate;
    }

    public String getTrainingSessionId() {
        return trainingSessionId;
    }

    public Operator trainingSessionId(String trainingSessionId) {
        this.trainingSessionId = trainingSessionId;
        return this;
    }

    public void setTrainingSessionId(String trainingSessionId) {
        this.trainingSessionId = trainingSessionId;
    }

    public String getTrainingSessionURL() {
        return trainingSessionURL;
    }

    public Operator trainingSessionURL(String trainingSessionURL) {
        this.trainingSessionURL = trainingSessionURL;
        return this;
    }

    public void setTrainingSessionURL(String trainingSessionURL) {
        this.trainingSessionURL = trainingSessionURL;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Operator sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public Operator groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public MapProperties getMetaDatas() {
        return metaDatas;
    }

    public Operator metaDatas(MapProperties mapProperties) {
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
        if (!(o instanceof Operator)) {
            return false;
        }
        return id != null && id.equals(((Operator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Operator{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", invitationDate='" + getInvitationDate() + "'" +
            ", invitationUrl='" + getInvitationUrl() + "'" +
            ", updateAccessDate='" + isUpdateAccessDate() + "'" +
            ", accessDate='" + getAccessDate() + "'" +
            ", updateTrainingDate='" + isUpdateTrainingDate() + "'" +
            ", trainingDate='" + getTrainingDate() + "'" +
            ", hasSucceededMCQ='" + isHasSucceededMCQ() + "'" +
            ", updateQCMDate='" + isUpdateQCMDate() + "'" +
            ", mcqDate='" + getMcqDate() + "'" +
            ", trainingSessionId='" + getTrainingSessionId() + "'" +
            ", trainingSessionURL='" + getTrainingSessionURL() + "'" +
            ", sessionId='" + getSessionId() + "'" +
            ", groupId='" + getGroupId() + "'" +
            "}";
    }
}
