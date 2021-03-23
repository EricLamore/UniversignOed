package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Operator;
import com.universign.universigncs.oed.repository.OperatorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universign.universigncs.oed.domain.enumeration.OpearatorStatus;
import com.universign.universigncs.oed.domain.enumeration.SignatureStatus;
import com.universign.universigncs.oed.domain.enumeration.UniversignStatus;
import com.universign.universigncs.oed.domain.enumeration.SignatureStatus;
/**
 * Integration tests for the {@link OperatorResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OperatorResourceIT {

    private static final OpearatorStatus DEFAULT_STATUS = OpearatorStatus.CREATED;
    private static final OpearatorStatus UPDATED_STATUS = OpearatorStatus.VALIDATED;

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INVITATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVITATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INVITATION_URL = "AAAAAAAAAA";
    private static final String UPDATED_INVITATION_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UPDATE_ACCESS_DATE = false;
    private static final Boolean UPDATED_UPDATE_ACCESS_DATE = true;

    private static final LocalDate DEFAULT_ACCESS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACCESS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_UPDATE_TRAINING_DATE = false;
    private static final Boolean UPDATED_UPDATE_TRAINING_DATE = true;

    private static final LocalDate DEFAULT_TRAINING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRAINING_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_HAS_SUCCEEDED_MCQ = false;
    private static final Boolean UPDATED_HAS_SUCCEEDED_MCQ = true;

    private static final Boolean DEFAULT_UPDATE_QCM_DATE = false;
    private static final Boolean UPDATED_UPDATE_QCM_DATE = true;

    private static final LocalDate DEFAULT_MCQ_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MCQ_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TRAINING_SESSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRAINING_SESSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRAINING_SESSION_URL = "AAAAAAAAAA";
    private static final String UPDATED_TRAINING_SESSION_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    private static final SignatureStatus DEFAULT_SIGNATURE_STATUS = SignatureStatus.READY;
    private static final SignatureStatus UPDATED_SIGNATURE_STATUS = SignatureStatus.EXPIRED;

    private static final Boolean DEFAULT_IS_OED = false;
    private static final Boolean UPDATED_IS_OED = true;

    private static final String DEFAULT_SIGNATURE_SESSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_SESSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE_SESSION_URL = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE_SESSION_URL = "BBBBBBBBBB";

    private static final UniversignStatus DEFAULT_UNIVERSIGN_STATUS = UniversignStatus.STATUS_INACTIVE;
    private static final UniversignStatus UPDATED_UNIVERSIGN_STATUS = UniversignStatus.STATUS_ACTIVE;

    private static final Boolean DEFAULT_CERTIFIED = false;
    private static final Boolean UPDATED_CERTIFIED = true;

    private static final Boolean DEFAULT_QUALIFIED = false;
    private static final Boolean UPDATED_QUALIFIED = true;

    private static final SignatureStatus DEFAULT_PREVIOUS_FINAL_UNIVERSIGN_STATUS = SignatureStatus.READY;
    private static final SignatureStatus UPDATED_PREVIOUS_FINAL_UNIVERSIGN_STATUS = SignatureStatus.EXPIRED;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private MockMvc restOperatorMockMvc;

    private Operator operator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Operator createEntity() {
        Operator operator = new Operator()
            .status(DEFAULT_STATUS)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .invitationDate(DEFAULT_INVITATION_DATE)
            .invitationUrl(DEFAULT_INVITATION_URL)
            .updateAccessDate(DEFAULT_UPDATE_ACCESS_DATE)
            .accessDate(DEFAULT_ACCESS_DATE)
            .updateTrainingDate(DEFAULT_UPDATE_TRAINING_DATE)
            .trainingDate(DEFAULT_TRAINING_DATE)
            .hasSucceededMCQ(DEFAULT_HAS_SUCCEEDED_MCQ)
            .updateQCMDate(DEFAULT_UPDATE_QCM_DATE)
            .mcqDate(DEFAULT_MCQ_DATE)
            .trainingSessionId(DEFAULT_TRAINING_SESSION_ID)
            .trainingSessionURL(DEFAULT_TRAINING_SESSION_URL)
            .sessionId(DEFAULT_SESSION_ID)
            .language(DEFAULT_LANGUAGE)
            .groupId(DEFAULT_GROUP_ID)
            .groupName(DEFAULT_GROUP_NAME)
            .signatureStatus(DEFAULT_SIGNATURE_STATUS)
            .isOED(DEFAULT_IS_OED)
            .signatureSessionId(DEFAULT_SIGNATURE_SESSION_ID)
            .signatureSessionURL(DEFAULT_SIGNATURE_SESSION_URL)
            .universignStatus(DEFAULT_UNIVERSIGN_STATUS)
            .certified(DEFAULT_CERTIFIED)
            .qualified(DEFAULT_QUALIFIED)
            .previousFinalUniversignStatus(DEFAULT_PREVIOUS_FINAL_UNIVERSIGN_STATUS)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .birthDate(DEFAULT_BIRTH_DATE);
        return operator;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Operator createUpdatedEntity() {
        Operator operator = new Operator()
            .status(UPDATED_STATUS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .invitationDate(UPDATED_INVITATION_DATE)
            .invitationUrl(UPDATED_INVITATION_URL)
            .updateAccessDate(UPDATED_UPDATE_ACCESS_DATE)
            .accessDate(UPDATED_ACCESS_DATE)
            .updateTrainingDate(UPDATED_UPDATE_TRAINING_DATE)
            .trainingDate(UPDATED_TRAINING_DATE)
            .hasSucceededMCQ(UPDATED_HAS_SUCCEEDED_MCQ)
            .updateQCMDate(UPDATED_UPDATE_QCM_DATE)
            .mcqDate(UPDATED_MCQ_DATE)
            .trainingSessionId(UPDATED_TRAINING_SESSION_ID)
            .trainingSessionURL(UPDATED_TRAINING_SESSION_URL)
            .sessionId(UPDATED_SESSION_ID)
            .language(UPDATED_LANGUAGE)
            .groupId(UPDATED_GROUP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .signatureStatus(UPDATED_SIGNATURE_STATUS)
            .isOED(UPDATED_IS_OED)
            .signatureSessionId(UPDATED_SIGNATURE_SESSION_ID)
            .signatureSessionURL(UPDATED_SIGNATURE_SESSION_URL)
            .universignStatus(UPDATED_UNIVERSIGN_STATUS)
            .certified(UPDATED_CERTIFIED)
            .qualified(UPDATED_QUALIFIED)
            .previousFinalUniversignStatus(UPDATED_PREVIOUS_FINAL_UNIVERSIGN_STATUS)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .birthDate(UPDATED_BIRTH_DATE);
        return operator;
    }

    @BeforeEach
    public void initTest() {
        operatorRepository.deleteAll();
        operator = createEntity();
    }

    @Test
    public void createOperator() throws Exception {
        int databaseSizeBeforeCreate = operatorRepository.findAll().size();
        // Create the Operator
        restOperatorMockMvc.perform(post("/api/operators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operator)))
            .andExpect(status().isCreated());

        // Validate the Operator in the database
        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeCreate + 1);
        Operator testOperator = operatorList.get(operatorList.size() - 1);
        assertThat(testOperator.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOperator.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testOperator.getInvitationDate()).isEqualTo(DEFAULT_INVITATION_DATE);
        assertThat(testOperator.getInvitationUrl()).isEqualTo(DEFAULT_INVITATION_URL);
        assertThat(testOperator.isUpdateAccessDate()).isEqualTo(DEFAULT_UPDATE_ACCESS_DATE);
        assertThat(testOperator.getAccessDate()).isEqualTo(DEFAULT_ACCESS_DATE);
        assertThat(testOperator.isUpdateTrainingDate()).isEqualTo(DEFAULT_UPDATE_TRAINING_DATE);
        assertThat(testOperator.getTrainingDate()).isEqualTo(DEFAULT_TRAINING_DATE);
        assertThat(testOperator.isHasSucceededMCQ()).isEqualTo(DEFAULT_HAS_SUCCEEDED_MCQ);
        assertThat(testOperator.isUpdateQCMDate()).isEqualTo(DEFAULT_UPDATE_QCM_DATE);
        assertThat(testOperator.getMcqDate()).isEqualTo(DEFAULT_MCQ_DATE);
        assertThat(testOperator.getTrainingSessionId()).isEqualTo(DEFAULT_TRAINING_SESSION_ID);
        assertThat(testOperator.getTrainingSessionURL()).isEqualTo(DEFAULT_TRAINING_SESSION_URL);
        assertThat(testOperator.getSessionId()).isEqualTo(DEFAULT_SESSION_ID);
        assertThat(testOperator.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testOperator.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
        assertThat(testOperator.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testOperator.getSignatureStatus()).isEqualTo(DEFAULT_SIGNATURE_STATUS);
        assertThat(testOperator.isIsOED()).isEqualTo(DEFAULT_IS_OED);
        assertThat(testOperator.getSignatureSessionId()).isEqualTo(DEFAULT_SIGNATURE_SESSION_ID);
        assertThat(testOperator.getSignatureSessionURL()).isEqualTo(DEFAULT_SIGNATURE_SESSION_URL);
        assertThat(testOperator.getUniversignStatus()).isEqualTo(DEFAULT_UNIVERSIGN_STATUS);
        assertThat(testOperator.isCertified()).isEqualTo(DEFAULT_CERTIFIED);
        assertThat(testOperator.isQualified()).isEqualTo(DEFAULT_QUALIFIED);
        assertThat(testOperator.getPreviousFinalUniversignStatus()).isEqualTo(DEFAULT_PREVIOUS_FINAL_UNIVERSIGN_STATUS);
        assertThat(testOperator.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testOperator.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testOperator.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testOperator.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
    }

    @Test
    public void createOperatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operatorRepository.findAll().size();

        // Create the Operator with an existing ID
        operator.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperatorMockMvc.perform(post("/api/operators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operator)))
            .andExpect(status().isBadRequest());

        // Validate the Operator in the database
        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = operatorRepository.findAll().size();
        // set the field null
        operator.setStatus(null);

        // Create the Operator, which fails.


        restOperatorMockMvc.perform(post("/api/operators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operator)))
            .andExpect(status().isBadRequest());

        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllOperators() throws Exception {
        // Initialize the database
        operatorRepository.save(operator);

        // Get all the operatorList
        restOperatorMockMvc.perform(get("/api/operators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operator.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].invitationDate").value(hasItem(DEFAULT_INVITATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].invitationUrl").value(hasItem(DEFAULT_INVITATION_URL)))
            .andExpect(jsonPath("$.[*].updateAccessDate").value(hasItem(DEFAULT_UPDATE_ACCESS_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].accessDate").value(hasItem(DEFAULT_ACCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateTrainingDate").value(hasItem(DEFAULT_UPDATE_TRAINING_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].trainingDate").value(hasItem(DEFAULT_TRAINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].hasSucceededMCQ").value(hasItem(DEFAULT_HAS_SUCCEEDED_MCQ.booleanValue())))
            .andExpect(jsonPath("$.[*].updateQCMDate").value(hasItem(DEFAULT_UPDATE_QCM_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].mcqDate").value(hasItem(DEFAULT_MCQ_DATE.toString())))
            .andExpect(jsonPath("$.[*].trainingSessionId").value(hasItem(DEFAULT_TRAINING_SESSION_ID)))
            .andExpect(jsonPath("$.[*].trainingSessionURL").value(hasItem(DEFAULT_TRAINING_SESSION_URL)))
            .andExpect(jsonPath("$.[*].sessionId").value(hasItem(DEFAULT_SESSION_ID)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].signatureStatus").value(hasItem(DEFAULT_SIGNATURE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].isOED").value(hasItem(DEFAULT_IS_OED.booleanValue())))
            .andExpect(jsonPath("$.[*].signatureSessionId").value(hasItem(DEFAULT_SIGNATURE_SESSION_ID)))
            .andExpect(jsonPath("$.[*].signatureSessionURL").value(hasItem(DEFAULT_SIGNATURE_SESSION_URL)))
            .andExpect(jsonPath("$.[*].universignStatus").value(hasItem(DEFAULT_UNIVERSIGN_STATUS.toString())))
            .andExpect(jsonPath("$.[*].certified").value(hasItem(DEFAULT_CERTIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].qualified").value(hasItem(DEFAULT_QUALIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].previousFinalUniversignStatus").value(hasItem(DEFAULT_PREVIOUS_FINAL_UNIVERSIGN_STATUS.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())));
    }
    
    @Test
    public void getOperator() throws Exception {
        // Initialize the database
        operatorRepository.save(operator);

        // Get the operator
        restOperatorMockMvc.perform(get("/api/operators/{id}", operator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operator.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.invitationDate").value(DEFAULT_INVITATION_DATE.toString()))
            .andExpect(jsonPath("$.invitationUrl").value(DEFAULT_INVITATION_URL))
            .andExpect(jsonPath("$.updateAccessDate").value(DEFAULT_UPDATE_ACCESS_DATE.booleanValue()))
            .andExpect(jsonPath("$.accessDate").value(DEFAULT_ACCESS_DATE.toString()))
            .andExpect(jsonPath("$.updateTrainingDate").value(DEFAULT_UPDATE_TRAINING_DATE.booleanValue()))
            .andExpect(jsonPath("$.trainingDate").value(DEFAULT_TRAINING_DATE.toString()))
            .andExpect(jsonPath("$.hasSucceededMCQ").value(DEFAULT_HAS_SUCCEEDED_MCQ.booleanValue()))
            .andExpect(jsonPath("$.updateQCMDate").value(DEFAULT_UPDATE_QCM_DATE.booleanValue()))
            .andExpect(jsonPath("$.mcqDate").value(DEFAULT_MCQ_DATE.toString()))
            .andExpect(jsonPath("$.trainingSessionId").value(DEFAULT_TRAINING_SESSION_ID))
            .andExpect(jsonPath("$.trainingSessionURL").value(DEFAULT_TRAINING_SESSION_URL))
            .andExpect(jsonPath("$.sessionId").value(DEFAULT_SESSION_ID))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
            .andExpect(jsonPath("$.signatureStatus").value(DEFAULT_SIGNATURE_STATUS.toString()))
            .andExpect(jsonPath("$.isOED").value(DEFAULT_IS_OED.booleanValue()))
            .andExpect(jsonPath("$.signatureSessionId").value(DEFAULT_SIGNATURE_SESSION_ID))
            .andExpect(jsonPath("$.signatureSessionURL").value(DEFAULT_SIGNATURE_SESSION_URL))
            .andExpect(jsonPath("$.universignStatus").value(DEFAULT_UNIVERSIGN_STATUS.toString()))
            .andExpect(jsonPath("$.certified").value(DEFAULT_CERTIFIED.booleanValue()))
            .andExpect(jsonPath("$.qualified").value(DEFAULT_QUALIFIED.booleanValue()))
            .andExpect(jsonPath("$.previousFinalUniversignStatus").value(DEFAULT_PREVIOUS_FINAL_UNIVERSIGN_STATUS.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()));
    }
    @Test
    public void getNonExistingOperator() throws Exception {
        // Get the operator
        restOperatorMockMvc.perform(get("/api/operators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOperator() throws Exception {
        // Initialize the database
        operatorRepository.save(operator);

        int databaseSizeBeforeUpdate = operatorRepository.findAll().size();

        // Update the operator
        Operator updatedOperator = operatorRepository.findById(operator.getId()).get();
        updatedOperator
            .status(UPDATED_STATUS)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .invitationDate(UPDATED_INVITATION_DATE)
            .invitationUrl(UPDATED_INVITATION_URL)
            .updateAccessDate(UPDATED_UPDATE_ACCESS_DATE)
            .accessDate(UPDATED_ACCESS_DATE)
            .updateTrainingDate(UPDATED_UPDATE_TRAINING_DATE)
            .trainingDate(UPDATED_TRAINING_DATE)
            .hasSucceededMCQ(UPDATED_HAS_SUCCEEDED_MCQ)
            .updateQCMDate(UPDATED_UPDATE_QCM_DATE)
            .mcqDate(UPDATED_MCQ_DATE)
            .trainingSessionId(UPDATED_TRAINING_SESSION_ID)
            .trainingSessionURL(UPDATED_TRAINING_SESSION_URL)
            .sessionId(UPDATED_SESSION_ID)
            .language(UPDATED_LANGUAGE)
            .groupId(UPDATED_GROUP_ID)
            .groupName(UPDATED_GROUP_NAME)
            .signatureStatus(UPDATED_SIGNATURE_STATUS)
            .isOED(UPDATED_IS_OED)
            .signatureSessionId(UPDATED_SIGNATURE_SESSION_ID)
            .signatureSessionURL(UPDATED_SIGNATURE_SESSION_URL)
            .universignStatus(UPDATED_UNIVERSIGN_STATUS)
            .certified(UPDATED_CERTIFIED)
            .qualified(UPDATED_QUALIFIED)
            .previousFinalUniversignStatus(UPDATED_PREVIOUS_FINAL_UNIVERSIGN_STATUS)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .birthDate(UPDATED_BIRTH_DATE);

        restOperatorMockMvc.perform(put("/api/operators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOperator)))
            .andExpect(status().isOk());

        // Validate the Operator in the database
        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeUpdate);
        Operator testOperator = operatorList.get(operatorList.size() - 1);
        assertThat(testOperator.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOperator.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testOperator.getInvitationDate()).isEqualTo(UPDATED_INVITATION_DATE);
        assertThat(testOperator.getInvitationUrl()).isEqualTo(UPDATED_INVITATION_URL);
        assertThat(testOperator.isUpdateAccessDate()).isEqualTo(UPDATED_UPDATE_ACCESS_DATE);
        assertThat(testOperator.getAccessDate()).isEqualTo(UPDATED_ACCESS_DATE);
        assertThat(testOperator.isUpdateTrainingDate()).isEqualTo(UPDATED_UPDATE_TRAINING_DATE);
        assertThat(testOperator.getTrainingDate()).isEqualTo(UPDATED_TRAINING_DATE);
        assertThat(testOperator.isHasSucceededMCQ()).isEqualTo(UPDATED_HAS_SUCCEEDED_MCQ);
        assertThat(testOperator.isUpdateQCMDate()).isEqualTo(UPDATED_UPDATE_QCM_DATE);
        assertThat(testOperator.getMcqDate()).isEqualTo(UPDATED_MCQ_DATE);
        assertThat(testOperator.getTrainingSessionId()).isEqualTo(UPDATED_TRAINING_SESSION_ID);
        assertThat(testOperator.getTrainingSessionURL()).isEqualTo(UPDATED_TRAINING_SESSION_URL);
        assertThat(testOperator.getSessionId()).isEqualTo(UPDATED_SESSION_ID);
        assertThat(testOperator.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testOperator.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
        assertThat(testOperator.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testOperator.getSignatureStatus()).isEqualTo(UPDATED_SIGNATURE_STATUS);
        assertThat(testOperator.isIsOED()).isEqualTo(UPDATED_IS_OED);
        assertThat(testOperator.getSignatureSessionId()).isEqualTo(UPDATED_SIGNATURE_SESSION_ID);
        assertThat(testOperator.getSignatureSessionURL()).isEqualTo(UPDATED_SIGNATURE_SESSION_URL);
        assertThat(testOperator.getUniversignStatus()).isEqualTo(UPDATED_UNIVERSIGN_STATUS);
        assertThat(testOperator.isCertified()).isEqualTo(UPDATED_CERTIFIED);
        assertThat(testOperator.isQualified()).isEqualTo(UPDATED_QUALIFIED);
        assertThat(testOperator.getPreviousFinalUniversignStatus()).isEqualTo(UPDATED_PREVIOUS_FINAL_UNIVERSIGN_STATUS);
        assertThat(testOperator.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testOperator.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testOperator.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testOperator.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
    }

    @Test
    public void updateNonExistingOperator() throws Exception {
        int databaseSizeBeforeUpdate = operatorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperatorMockMvc.perform(put("/api/operators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operator)))
            .andExpect(status().isBadRequest());

        // Validate the Operator in the database
        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOperator() throws Exception {
        // Initialize the database
        operatorRepository.save(operator);

        int databaseSizeBeforeDelete = operatorRepository.findAll().size();

        // Delete the operator
        restOperatorMockMvc.perform(delete("/api/operators/{id}", operator.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
