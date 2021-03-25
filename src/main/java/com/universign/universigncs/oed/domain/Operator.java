package com.universign.universigncs.oed.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.universign.universigncs.oed.domain.enumeration.OpearatorStatus;

import com.universign.universigncs.oed.domain.enumeration.SignatureStatus;

import com.universign.universigncs.oed.domain.enumeration.UniversignStatus;

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

    @Field("language")
    private String language;

    @Field("group_id")
    private String groupId;

    @Field("group_name")
    private String groupName;

    @Field("signature_status")
    private SignatureStatus signatureStatus;

    @Field("is_oed")
    private Boolean isOED;

    @Field("signature_session_id")
    private String signatureSessionId;

    @Field("signature_session_url")
    private String signatureSessionURL;

    @Field("universign_status")
    private UniversignStatus universignStatus;

    @Field("certified")
    private Boolean certified;

    @Field("qualified")
    private Boolean qualified;

    @Field("previous_final_universign_status")
    private SignatureStatus previousFinalUniversignStatus;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email_address")
    private String emailAddress;

    @Field("birth_date")
    private LocalDate birthDate;

    @DBRef
    @Field("certifiedUser")
    private CertifiedUser certifiedUser;

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

    public String getLanguage() {
        return language;
    }

    public Operator language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getGroupName() {
        return groupName;
    }

    public Operator groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public SignatureStatus getSignatureStatus() {
        return signatureStatus;
    }

    public Operator signatureStatus(SignatureStatus signatureStatus) {
        this.signatureStatus = signatureStatus;
        return this;
    }

    public void setSignatureStatus(SignatureStatus signatureStatus) {
        this.signatureStatus = signatureStatus;
    }

    public Boolean isIsOED() {
        return isOED;
    }

    public Operator isOED(Boolean isOED) {
        this.isOED = isOED;
        return this;
    }

    public void setIsOED(Boolean isOED) {
        this.isOED = isOED;
    }

    public String getSignatureSessionId() {
        return signatureSessionId;
    }

    public Operator signatureSessionId(String signatureSessionId) {
        this.signatureSessionId = signatureSessionId;
        return this;
    }

    public void setSignatureSessionId(String signatureSessionId) {
        this.signatureSessionId = signatureSessionId;
    }

    public String getSignatureSessionURL() {
        return signatureSessionURL;
    }

    public Operator signatureSessionURL(String signatureSessionURL) {
        this.signatureSessionURL = signatureSessionURL;
        return this;
    }

    public void setSignatureSessionURL(String signatureSessionURL) {
        this.signatureSessionURL = signatureSessionURL;
    }

    public UniversignStatus getUniversignStatus() {
        return universignStatus;
    }

    public Operator universignStatus(UniversignStatus universignStatus) {
        this.universignStatus = universignStatus;
        return this;
    }

    public void setUniversignStatus(UniversignStatus universignStatus) {
        this.universignStatus = universignStatus;
    }

    public Boolean isCertified() {
        return certified;
    }

    public Operator certified(Boolean certified) {
        this.certified = certified;
        return this;
    }

    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    public Boolean isQualified() {
        return qualified;
    }

    public Operator qualified(Boolean qualified) {
        this.qualified = qualified;
        return this;
    }

    public void setQualified(Boolean qualified) {
        this.qualified = qualified;
    }

    public SignatureStatus getPreviousFinalUniversignStatus() {
        return previousFinalUniversignStatus;
    }

    public Operator previousFinalUniversignStatus(SignatureStatus previousFinalUniversignStatus) {
        this.previousFinalUniversignStatus = previousFinalUniversignStatus;
        return this;
    }

    public void setPreviousFinalUniversignStatus(SignatureStatus previousFinalUniversignStatus) {
        this.previousFinalUniversignStatus = previousFinalUniversignStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public Operator firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Operator lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Operator emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Operator birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CertifiedUser getCertifiedUser() {
        return certifiedUser;
    }

    public Operator certifiedUser(CertifiedUser certifiedUser) {
        this.certifiedUser = certifiedUser;
        return this;
    }

    public void setCertifiedUser(CertifiedUser certifiedUser) {
        this.certifiedUser = certifiedUser;
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
            ", language='" + getLanguage() + "'" +
            ", groupId='" + getGroupId() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", signatureStatus='" + getSignatureStatus() + "'" +
            ", isOED='" + isIsOED() + "'" +
            ", signatureSessionId='" + getSignatureSessionId() + "'" +
            ", signatureSessionURL='" + getSignatureSessionURL() + "'" +
            ", universignStatus='" + getUniversignStatus() + "'" +
            ", certified='" + isCertified() + "'" +
            ", qualified='" + isQualified() + "'" +
            ", previousFinalUniversignStatus='" + getPreviousFinalUniversignStatus() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            "}";
    }
}
