package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Administrator;
import com.universign.universigncs.oed.repository.AdministratorRepository;

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
 * Integration tests for the {@link AdministratorResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdministratorResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private MockMvc restAdministratorMockMvc;

    private Administrator administrator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Administrator createEntity() {
        Administrator administrator = new Administrator()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .birthDate(DEFAULT_BIRTH_DATE);
        return administrator;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Administrator createUpdatedEntity() {
        Administrator administrator = new Administrator()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .birthDate(UPDATED_BIRTH_DATE);
        return administrator;
    }

    @BeforeEach
    public void initTest() {
        administratorRepository.deleteAll();
        administrator = createEntity();
    }

    @Test
    public void createAdministrator() throws Exception {
        int databaseSizeBeforeCreate = administratorRepository.findAll().size();
        // Create the Administrator
        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isCreated());

        // Validate the Administrator in the database
        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeCreate + 1);
        Administrator testAdministrator = administratorList.get(administratorList.size() - 1);
        assertThat(testAdministrator.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAdministrator.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAdministrator.getEmailAddress()).isEqualTo(DEFAULT_EMAIL_ADDRESS);
        assertThat(testAdministrator.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
    }

    @Test
    public void createAdministratorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = administratorRepository.findAll().size();

        // Create the Administrator with an existing ID
        administrator.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isBadRequest());

        // Validate the Administrator in the database
        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = administratorRepository.findAll().size();
        // set the field null
        administrator.setFirstName(null);

        // Create the Administrator, which fails.


        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isBadRequest());

        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = administratorRepository.findAll().size();
        // set the field null
        administrator.setLastName(null);

        // Create the Administrator, which fails.


        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isBadRequest());

        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEmailAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = administratorRepository.findAll().size();
        // set the field null
        administrator.setEmailAddress(null);

        // Create the Administrator, which fails.


        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isBadRequest());

        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAdministrators() throws Exception {
        // Initialize the database
        administratorRepository.save(administrator);

        // Get all the administratorList
        restAdministratorMockMvc.perform(get("/api/administrators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(administrator.getId())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())));
    }
    
    @Test
    public void getAdministrator() throws Exception {
        // Initialize the database
        administratorRepository.save(administrator);

        // Get the administrator
        restAdministratorMockMvc.perform(get("/api/administrators/{id}", administrator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(administrator.getId()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()));
    }
    @Test
    public void getNonExistingAdministrator() throws Exception {
        // Get the administrator
        restAdministratorMockMvc.perform(get("/api/administrators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdministrator() throws Exception {
        // Initialize the database
        administratorRepository.save(administrator);

        int databaseSizeBeforeUpdate = administratorRepository.findAll().size();

        // Update the administrator
        Administrator updatedAdministrator = administratorRepository.findById(administrator.getId()).get();
        updatedAdministrator
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .birthDate(UPDATED_BIRTH_DATE);

        restAdministratorMockMvc.perform(put("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdministrator)))
            .andExpect(status().isOk());

        // Validate the Administrator in the database
        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeUpdate);
        Administrator testAdministrator = administratorList.get(administratorList.size() - 1);
        assertThat(testAdministrator.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAdministrator.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAdministrator.getEmailAddress()).isEqualTo(UPDATED_EMAIL_ADDRESS);
        assertThat(testAdministrator.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
    }

    @Test
    public void updateNonExistingAdministrator() throws Exception {
        int databaseSizeBeforeUpdate = administratorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdministratorMockMvc.perform(put("/api/administrators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isBadRequest());

        // Validate the Administrator in the database
        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAdministrator() throws Exception {
        // Initialize the database
        administratorRepository.save(administrator);

        int databaseSizeBeforeDelete = administratorRepository.findAll().size();

        // Delete the administrator
        restAdministratorMockMvc.perform(delete("/api/administrators/{id}", administrator.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
