package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.CheckResult;
import com.universign.universigncs.oed.repository.CheckResultRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.universign.universigncs.oed.domain.enumeration.Status;
import com.universign.universigncs.oed.domain.enumeration.CheckStatus;
/**
 * Integration tests for the {@link CheckResultResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CheckResultResourceIT {

    private static final String DEFAULT_CHECK_ERROR_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_ERROR_MESSAGE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.UPDATE;
    private static final Status UPDATED_STATUS = Status.INSERT;

    private static final CheckStatus DEFAULT_RESULT = CheckStatus.PERFECT;
    private static final CheckStatus UPDATED_RESULT = CheckStatus.EMAIL_AND_PHONE_IN_OTHER_ORGANISATION;

    @Autowired
    private CheckResultRepository checkResultRepository;

    @Autowired
    private MockMvc restCheckResultMockMvc;

    private CheckResult checkResult;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckResult createEntity() {
        CheckResult checkResult = new CheckResult()
            .checkErrorMessage(DEFAULT_CHECK_ERROR_MESSAGE)
            .status(DEFAULT_STATUS)
            .result(DEFAULT_RESULT);
        return checkResult;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CheckResult createUpdatedEntity() {
        CheckResult checkResult = new CheckResult()
            .checkErrorMessage(UPDATED_CHECK_ERROR_MESSAGE)
            .status(UPDATED_STATUS)
            .result(UPDATED_RESULT);
        return checkResult;
    }

    @BeforeEach
    public void initTest() {
        checkResultRepository.deleteAll();
        checkResult = createEntity();
    }

    @Test
    public void createCheckResult() throws Exception {
        int databaseSizeBeforeCreate = checkResultRepository.findAll().size();
        // Create the CheckResult
        restCheckResultMockMvc.perform(post("/api/check-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(checkResult)))
            .andExpect(status().isCreated());

        // Validate the CheckResult in the database
        List<CheckResult> checkResultList = checkResultRepository.findAll();
        assertThat(checkResultList).hasSize(databaseSizeBeforeCreate + 1);
        CheckResult testCheckResult = checkResultList.get(checkResultList.size() - 1);
        assertThat(testCheckResult.getCheckErrorMessage()).isEqualTo(DEFAULT_CHECK_ERROR_MESSAGE);
        assertThat(testCheckResult.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCheckResult.getResult()).isEqualTo(DEFAULT_RESULT);
    }

    @Test
    public void createCheckResultWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = checkResultRepository.findAll().size();

        // Create the CheckResult with an existing ID
        checkResult.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCheckResultMockMvc.perform(post("/api/check-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(checkResult)))
            .andExpect(status().isBadRequest());

        // Validate the CheckResult in the database
        List<CheckResult> checkResultList = checkResultRepository.findAll();
        assertThat(checkResultList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllCheckResults() throws Exception {
        // Initialize the database
        checkResultRepository.save(checkResult);

        // Get all the checkResultList
        restCheckResultMockMvc.perform(get("/api/check-results?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(checkResult.getId())))
            .andExpect(jsonPath("$.[*].checkErrorMessage").value(hasItem(DEFAULT_CHECK_ERROR_MESSAGE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT.toString())));
    }
    
    @Test
    public void getCheckResult() throws Exception {
        // Initialize the database
        checkResultRepository.save(checkResult);

        // Get the checkResult
        restCheckResultMockMvc.perform(get("/api/check-results/{id}", checkResult.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(checkResult.getId()))
            .andExpect(jsonPath("$.checkErrorMessage").value(DEFAULT_CHECK_ERROR_MESSAGE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT.toString()));
    }
    @Test
    public void getNonExistingCheckResult() throws Exception {
        // Get the checkResult
        restCheckResultMockMvc.perform(get("/api/check-results/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCheckResult() throws Exception {
        // Initialize the database
        checkResultRepository.save(checkResult);

        int databaseSizeBeforeUpdate = checkResultRepository.findAll().size();

        // Update the checkResult
        CheckResult updatedCheckResult = checkResultRepository.findById(checkResult.getId()).get();
        updatedCheckResult
            .checkErrorMessage(UPDATED_CHECK_ERROR_MESSAGE)
            .status(UPDATED_STATUS)
            .result(UPDATED_RESULT);

        restCheckResultMockMvc.perform(put("/api/check-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCheckResult)))
            .andExpect(status().isOk());

        // Validate the CheckResult in the database
        List<CheckResult> checkResultList = checkResultRepository.findAll();
        assertThat(checkResultList).hasSize(databaseSizeBeforeUpdate);
        CheckResult testCheckResult = checkResultList.get(checkResultList.size() - 1);
        assertThat(testCheckResult.getCheckErrorMessage()).isEqualTo(UPDATED_CHECK_ERROR_MESSAGE);
        assertThat(testCheckResult.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCheckResult.getResult()).isEqualTo(UPDATED_RESULT);
    }

    @Test
    public void updateNonExistingCheckResult() throws Exception {
        int databaseSizeBeforeUpdate = checkResultRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCheckResultMockMvc.perform(put("/api/check-results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(checkResult)))
            .andExpect(status().isBadRequest());

        // Validate the CheckResult in the database
        List<CheckResult> checkResultList = checkResultRepository.findAll();
        assertThat(checkResultList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCheckResult() throws Exception {
        // Initialize the database
        checkResultRepository.save(checkResult);

        int databaseSizeBeforeDelete = checkResultRepository.findAll().size();

        // Delete the checkResult
        restCheckResultMockMvc.perform(delete("/api/check-results/{id}", checkResult.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CheckResult> checkResultList = checkResultRepository.findAll();
        assertThat(checkResultList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
