package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Operator;
import com.universign.universigncs.oed.repository.OperatorRepository;
import com.universign.universigncs.oed.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.universign.universigncs.oed.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universign.universigncs.oed.domain.enumeration.OpearatorStatus;
/**
 * Integration tests for the {@link OperatorResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class OperatorResourceIT {

    private static final OpearatorStatus DEFAULT_STATUS = OpearatorStatus.CREATED;
    private static final OpearatorStatus UPDATED_STATUS = OpearatorStatus.VALIDATED;

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INVITATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVITATION_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_INVITATION_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_INVITATION_URL = "AAAAAAAAAA";
    private static final String UPDATED_INVITATION_URL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UPDATE_ACCESS_DATE = false;
    private static final Boolean UPDATED_UPDATE_ACCESS_DATE = true;

    private static final LocalDate DEFAULT_ACCESS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACCESS_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ACCESS_DATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_UPDATE_TRAINING_DATE = false;
    private static final Boolean UPDATED_UPDATE_TRAINING_DATE = true;

    private static final LocalDate DEFAULT_TRAINING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRAINING_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_TRAINING_DATE = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_HAS_SUCCEEDED_MCQ = false;
    private static final Boolean UPDATED_HAS_SUCCEEDED_MCQ = true;

    private static final Boolean DEFAULT_UPDATE_QCM_DATE = false;
    private static final Boolean UPDATED_UPDATE_QCM_DATE = true;

    private static final LocalDate DEFAULT_MCQ_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MCQ_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_MCQ_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_TRAINING_SESSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRAINING_SESSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRAINING_SESSION_URL = "AAAAAAAAAA";
    private static final String UPDATED_TRAINING_SESSION_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_ID = "BBBBBBBBBB";

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOperatorMockMvc;

    private Operator operator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OperatorResource operatorResource = new OperatorResource(operatorRepository);
        this.restOperatorMockMvc = MockMvcBuilders.standaloneSetup(operatorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

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
            .groupId(DEFAULT_GROUP_ID);
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
            .groupId(UPDATED_GROUP_ID);
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
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
        assertThat(testOperator.getGroupId()).isEqualTo(DEFAULT_GROUP_ID);
    }

    @Test
    public void createOperatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operatorRepository.findAll().size();

        // Create the Operator with an existing ID
        operator.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperatorMockMvc.perform(post("/api/operators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operator.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].invitationDate").value(hasItem(DEFAULT_INVITATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].invitationUrl").value(hasItem(DEFAULT_INVITATION_URL.toString())))
            .andExpect(jsonPath("$.[*].updateAccessDate").value(hasItem(DEFAULT_UPDATE_ACCESS_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].accessDate").value(hasItem(DEFAULT_ACCESS_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateTrainingDate").value(hasItem(DEFAULT_UPDATE_TRAINING_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].trainingDate").value(hasItem(DEFAULT_TRAINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].hasSucceededMCQ").value(hasItem(DEFAULT_HAS_SUCCEEDED_MCQ.booleanValue())))
            .andExpect(jsonPath("$.[*].updateQCMDate").value(hasItem(DEFAULT_UPDATE_QCM_DATE.booleanValue())))
            .andExpect(jsonPath("$.[*].mcqDate").value(hasItem(DEFAULT_MCQ_DATE.toString())))
            .andExpect(jsonPath("$.[*].trainingSessionId").value(hasItem(DEFAULT_TRAINING_SESSION_ID.toString())))
            .andExpect(jsonPath("$.[*].trainingSessionURL").value(hasItem(DEFAULT_TRAINING_SESSION_URL.toString())))
            .andExpect(jsonPath("$.[*].sessionId").value(hasItem(DEFAULT_SESSION_ID.toString())))
            .andExpect(jsonPath("$.[*].groupId").value(hasItem(DEFAULT_GROUP_ID.toString())));
    }
    
    @Test
    public void getOperator() throws Exception {
        // Initialize the database
        operatorRepository.save(operator);

        // Get the operator
        restOperatorMockMvc.perform(get("/api/operators/{id}", operator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(operator.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.invitationDate").value(DEFAULT_INVITATION_DATE.toString()))
            .andExpect(jsonPath("$.invitationUrl").value(DEFAULT_INVITATION_URL.toString()))
            .andExpect(jsonPath("$.updateAccessDate").value(DEFAULT_UPDATE_ACCESS_DATE.booleanValue()))
            .andExpect(jsonPath("$.accessDate").value(DEFAULT_ACCESS_DATE.toString()))
            .andExpect(jsonPath("$.updateTrainingDate").value(DEFAULT_UPDATE_TRAINING_DATE.booleanValue()))
            .andExpect(jsonPath("$.trainingDate").value(DEFAULT_TRAINING_DATE.toString()))
            .andExpect(jsonPath("$.hasSucceededMCQ").value(DEFAULT_HAS_SUCCEEDED_MCQ.booleanValue()))
            .andExpect(jsonPath("$.updateQCMDate").value(DEFAULT_UPDATE_QCM_DATE.booleanValue()))
            .andExpect(jsonPath("$.mcqDate").value(DEFAULT_MCQ_DATE.toString()))
            .andExpect(jsonPath("$.trainingSessionId").value(DEFAULT_TRAINING_SESSION_ID.toString()))
            .andExpect(jsonPath("$.trainingSessionURL").value(DEFAULT_TRAINING_SESSION_URL.toString()))
            .andExpect(jsonPath("$.sessionId").value(DEFAULT_SESSION_ID.toString()))
            .andExpect(jsonPath("$.groupId").value(DEFAULT_GROUP_ID.toString()));
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
            .groupId(UPDATED_GROUP_ID);

        restOperatorMockMvc.perform(put("/api/operators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
        assertThat(testOperator.getGroupId()).isEqualTo(UPDATED_GROUP_ID);
    }

    @Test
    public void updateNonExistingOperator() throws Exception {
        int databaseSizeBeforeUpdate = operatorRepository.findAll().size();

        // Create the Operator

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperatorMockMvc.perform(put("/api/operators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Operator> operatorList = operatorRepository.findAll();
        assertThat(operatorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Operator.class);
        Operator operator1 = new Operator();
        operator1.setId("id1");
        Operator operator2 = new Operator();
        operator2.setId(operator1.getId());
        assertThat(operator1).isEqualTo(operator2);
        operator2.setId("id2");
        assertThat(operator1).isNotEqualTo(operator2);
        operator1.setId(null);
        assertThat(operator1).isNotEqualTo(operator2);
    }
}
