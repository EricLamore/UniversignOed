package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Administrator;
import com.universign.universigncs.oed.repository.AdministratorRepository;
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

import com.universign.universigncs.oed.domain.enumeration.Role;
import com.universign.universigncs.oed.domain.enumeration.AdministratorStatus;
/**
 * Integration tests for the {@link AdministratorResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class AdministratorResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_BIRTH_DATE = LocalDate.ofEpochDay(-1L);

    private static final Role DEFAULT_ROLE = Role.SUPER_ADMIN;
    private static final Role UPDATED_ROLE = Role.GROUP_ADMIN;

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final AdministratorStatus DEFAULT_ADMINISTRATOR_STATUS = AdministratorStatus.REGISTERING;
    private static final AdministratorStatus UPDATED_ADMINISTRATOR_STATUS = AdministratorStatus.ACTIVE;

    private static final String DEFAULT_ADMIN_FONCTION = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_FONCTION = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_UNIVERSIGN_ADMIN = false;
    private static final Boolean UPDATED_UNIVERSIGN_ADMIN = true;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAdministratorMockMvc;

    private Administrator administrator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdministratorResource administratorResource = new AdministratorResource(administratorRepository);
        this.restAdministratorMockMvc = MockMvcBuilders.standaloneSetup(administratorResource)
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
    public static Administrator createEntity() {
        Administrator administrator = new Administrator()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .birthDate(DEFAULT_BIRTH_DATE)
            .role(DEFAULT_ROLE)
            .password(DEFAULT_PASSWORD)
            .administratorStatus(DEFAULT_ADMINISTRATOR_STATUS)
            .adminFonction(DEFAULT_ADMIN_FONCTION)
            .phone(DEFAULT_PHONE)
            .mobilePhone(DEFAULT_MOBILE_PHONE)
            .description(DEFAULT_DESCRIPTION)
            .universignAdmin(DEFAULT_UNIVERSIGN_ADMIN);
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
            .birthDate(UPDATED_BIRTH_DATE)
            .role(UPDATED_ROLE)
            .password(UPDATED_PASSWORD)
            .administratorStatus(UPDATED_ADMINISTRATOR_STATUS)
            .adminFonction(UPDATED_ADMIN_FONCTION)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .description(UPDATED_DESCRIPTION)
            .universignAdmin(UPDATED_UNIVERSIGN_ADMIN);
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
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
        assertThat(testAdministrator.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testAdministrator.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testAdministrator.getAdministratorStatus()).isEqualTo(DEFAULT_ADMINISTRATOR_STATUS);
        assertThat(testAdministrator.getAdminFonction()).isEqualTo(DEFAULT_ADMIN_FONCTION);
        assertThat(testAdministrator.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testAdministrator.getMobilePhone()).isEqualTo(DEFAULT_MOBILE_PHONE);
        assertThat(testAdministrator.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAdministrator.isUniversignAdmin()).isEqualTo(DEFAULT_UNIVERSIGN_ADMIN);
    }

    @Test
    public void createAdministratorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = administratorRepository.findAll().size();

        // Create the Administrator with an existing ID
        administrator.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrator)))
            .andExpect(status().isBadRequest());

        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = administratorRepository.findAll().size();
        // set the field null
        administrator.setPassword(null);

        // Create the Administrator, which fails.

        restAdministratorMockMvc.perform(post("/api/administrators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(administrator.getId())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].administratorStatus").value(hasItem(DEFAULT_ADMINISTRATOR_STATUS.toString())))
            .andExpect(jsonPath("$.[*].adminFonction").value(hasItem(DEFAULT_ADMIN_FONCTION.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].mobilePhone").value(hasItem(DEFAULT_MOBILE_PHONE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].universignAdmin").value(hasItem(DEFAULT_UNIVERSIGN_ADMIN.booleanValue())));
    }
    
    @Test
    public void getAdministrator() throws Exception {
        // Initialize the database
        administratorRepository.save(administrator);

        // Get the administrator
        restAdministratorMockMvc.perform(get("/api/administrators/{id}", administrator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(administrator.getId()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS.toString()))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.administratorStatus").value(DEFAULT_ADMINISTRATOR_STATUS.toString()))
            .andExpect(jsonPath("$.adminFonction").value(DEFAULT_ADMIN_FONCTION.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.mobilePhone").value(DEFAULT_MOBILE_PHONE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.universignAdmin").value(DEFAULT_UNIVERSIGN_ADMIN.booleanValue()));
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
            .birthDate(UPDATED_BIRTH_DATE)
            .role(UPDATED_ROLE)
            .password(UPDATED_PASSWORD)
            .administratorStatus(UPDATED_ADMINISTRATOR_STATUS)
            .adminFonction(UPDATED_ADMIN_FONCTION)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .description(UPDATED_DESCRIPTION)
            .universignAdmin(UPDATED_UNIVERSIGN_ADMIN);

        restAdministratorMockMvc.perform(put("/api/administrators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
        assertThat(testAdministrator.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testAdministrator.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testAdministrator.getAdministratorStatus()).isEqualTo(UPDATED_ADMINISTRATOR_STATUS);
        assertThat(testAdministrator.getAdminFonction()).isEqualTo(UPDATED_ADMIN_FONCTION);
        assertThat(testAdministrator.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAdministrator.getMobilePhone()).isEqualTo(UPDATED_MOBILE_PHONE);
        assertThat(testAdministrator.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAdministrator.isUniversignAdmin()).isEqualTo(UPDATED_UNIVERSIGN_ADMIN);
    }

    @Test
    public void updateNonExistingAdministrator() throws Exception {
        int databaseSizeBeforeUpdate = administratorRepository.findAll().size();

        // Create the Administrator

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdministratorMockMvc.perform(put("/api/administrators")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
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
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Administrator> administratorList = administratorRepository.findAll();
        assertThat(administratorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Administrator.class);
        Administrator administrator1 = new Administrator();
        administrator1.setId("id1");
        Administrator administrator2 = new Administrator();
        administrator2.setId(administrator1.getId());
        assertThat(administrator1).isEqualTo(administrator2);
        administrator2.setId("id2");
        assertThat(administrator1).isNotEqualTo(administrator2);
        administrator1.setId(null);
        assertThat(administrator1).isNotEqualTo(administrator2);
    }
}
