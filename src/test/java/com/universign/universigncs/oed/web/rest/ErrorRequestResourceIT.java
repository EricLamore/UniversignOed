package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.ErrorRequest;
import com.universign.universigncs.oed.repository.ErrorRequestRepository;

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

/**
 * Integration tests for the {@link ErrorRequestResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ErrorRequestResourceIT {

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private ErrorRequestRepository errorRequestRepository;

    @Autowired
    private MockMvc restErrorRequestMockMvc;

    private ErrorRequest errorRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErrorRequest createEntity() {
        ErrorRequest errorRequest = new ErrorRequest()
            .comment(DEFAULT_COMMENT)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .birthDate(DEFAULT_BIRTH_DATE);
        return errorRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ErrorRequest createUpdatedEntity() {
        ErrorRequest errorRequest = new ErrorRequest()
            .comment(UPDATED_COMMENT)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .birthDate(UPDATED_BIRTH_DATE);
        return errorRequest;
    }

    @BeforeEach
    public void initTest() {
        errorRequestRepository.deleteAll();
        errorRequest = createEntity();
    }

    @Test
    public void createErrorRequest() throws Exception {
        int databaseSizeBeforeCreate = errorRequestRepository.findAll().size();
        // Create the ErrorRequest
        restErrorRequestMockMvc.perform(post("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRequest)))
            .andExpect(status().isCreated());

        // Validate the ErrorRequest in the database
        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeCreate + 1);
        ErrorRequest testErrorRequest = errorRequestList.get(errorRequestList.size() - 1);
        assertThat(testErrorRequest.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testErrorRequest.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testErrorRequest.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testErrorRequest.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testErrorRequest.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
    }

    @Test
    public void createErrorRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = errorRequestRepository.findAll().size();

        // Create the ErrorRequest with an existing ID
        errorRequest.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restErrorRequestMockMvc.perform(post("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRequest)))
            .andExpect(status().isBadRequest());

        // Validate the ErrorRequest in the database
        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorRequestRepository.findAll().size();
        // set the field null
        errorRequest.setFirstName(null);

        // Create the ErrorRequest, which fails.


        restErrorRequestMockMvc.perform(post("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRequest)))
            .andExpect(status().isBadRequest());

        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorRequestRepository.findAll().size();
        // set the field null
        errorRequest.setLastName(null);

        // Create the ErrorRequest, which fails.


        restErrorRequestMockMvc.perform(post("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRequest)))
            .andExpect(status().isBadRequest());

        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = errorRequestRepository.findAll().size();
        // set the field null
        errorRequest.setEmailAddress(null);

        // Create the ErrorRequest, which fails.


        restErrorRequestMockMvc.perform(post("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRequest)))
            .andExpect(status().isBadRequest());

        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllErrorRequests() throws Exception {
        // Initialize the database
        errorRequestRepository.save(errorRequest);

        // Get all the errorRequestList
        restErrorRequestMockMvc.perform(get("/api/error-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(errorRequest.getId())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())));
    }
    
    @Test
    public void getErrorRequest() throws Exception {
        // Initialize the database
        errorRequestRepository.save(errorRequest);

        // Get the errorRequest
        restErrorRequestMockMvc.perform(get("/api/error-requests/{id}", errorRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(errorRequest.getId()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()));
    }
    @Test
    public void getNonExistingErrorRequest() throws Exception {
        // Get the errorRequest
        restErrorRequestMockMvc.perform(get("/api/error-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateErrorRequest() throws Exception {
        // Initialize the database
        errorRequestRepository.save(errorRequest);

        int databaseSizeBeforeUpdate = errorRequestRepository.findAll().size();

        // Update the errorRequest
        ErrorRequest updatedErrorRequest = errorRequestRepository.findById(errorRequest.getId()).get();
        updatedErrorRequest
            .comment(UPDATED_COMMENT)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .birthDate(UPDATED_BIRTH_DATE);

        restErrorRequestMockMvc.perform(put("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedErrorRequest)))
            .andExpect(status().isOk());

        // Validate the ErrorRequest in the database
        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeUpdate);
        ErrorRequest testErrorRequest = errorRequestList.get(errorRequestList.size() - 1);
        assertThat(testErrorRequest.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testErrorRequest.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testErrorRequest.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testErrorRequest.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testErrorRequest.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
    }

    @Test
    public void updateNonExistingErrorRequest() throws Exception {
        int databaseSizeBeforeUpdate = errorRequestRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restErrorRequestMockMvc.perform(put("/api/error-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(errorRequest)))
            .andExpect(status().isBadRequest());

        // Validate the ErrorRequest in the database
        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteErrorRequest() throws Exception {
        // Initialize the database
        errorRequestRepository.save(errorRequest);

        int databaseSizeBeforeDelete = errorRequestRepository.findAll().size();

        // Delete the errorRequest
        restErrorRequestMockMvc.perform(delete("/api/error-requests/{id}", errorRequest.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ErrorRequest> errorRequestList = errorRequestRepository.findAll();
        assertThat(errorRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
