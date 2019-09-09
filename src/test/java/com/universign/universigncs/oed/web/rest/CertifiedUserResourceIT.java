package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.CertifiedUser;
import com.universign.universigncs.oed.repository.CertifiedUserRepository;
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


import java.util.List;

import static com.universign.universigncs.oed.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CertifiedUserResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class CertifiedUserResourceIT {

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    @Autowired
    private CertifiedUserRepository certifiedUserRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restCertifiedUserMockMvc;

    private CertifiedUser certifiedUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CertifiedUserResource certifiedUserResource = new CertifiedUserResource(certifiedUserRepository);
        this.restCertifiedUserMockMvc = MockMvcBuilders.standaloneSetup(certifiedUserResource)
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
    public static CertifiedUser createEntity() {
        CertifiedUser certifiedUser = new CertifiedUser()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return certifiedUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CertifiedUser createUpdatedEntity() {
        CertifiedUser certifiedUser = new CertifiedUser()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return certifiedUser;
    }

    @BeforeEach
    public void initTest() {
        certifiedUserRepository.deleteAll();
        certifiedUser = createEntity();
    }

    @Test
    public void createCertifiedUser() throws Exception {
        int databaseSizeBeforeCreate = certifiedUserRepository.findAll().size();

        // Create the CertifiedUser
        restCertifiedUserMockMvc.perform(post("/api/certified-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifiedUser)))
            .andExpect(status().isCreated());

        // Validate the CertifiedUser in the database
        List<CertifiedUser> certifiedUserList = certifiedUserRepository.findAll();
        assertThat(certifiedUserList).hasSize(databaseSizeBeforeCreate + 1);
        CertifiedUser testCertifiedUser = certifiedUserList.get(certifiedUserList.size() - 1);
        assertThat(testCertifiedUser.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testCertifiedUser.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testCertifiedUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCertifiedUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    public void createCertifiedUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certifiedUserRepository.findAll().size();

        // Create the CertifiedUser with an existing ID
        certifiedUser.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertifiedUserMockMvc.perform(post("/api/certified-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifiedUser)))
            .andExpect(status().isBadRequest());

        // Validate the CertifiedUser in the database
        List<CertifiedUser> certifiedUserList = certifiedUserRepository.findAll();
        assertThat(certifiedUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllCertifiedUsers() throws Exception {
        // Initialize the database
        certifiedUserRepository.save(certifiedUser);

        // Get all the certifiedUserList
        restCertifiedUserMockMvc.perform(get("/api/certified-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certifiedUser.getId())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())));
    }
    
    @Test
    public void getCertifiedUser() throws Exception {
        // Initialize the database
        certifiedUserRepository.save(certifiedUser);

        // Get the certifiedUser
        restCertifiedUserMockMvc.perform(get("/api/certified-users/{id}", certifiedUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certifiedUser.getId()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()));
    }

    @Test
    public void getNonExistingCertifiedUser() throws Exception {
        // Get the certifiedUser
        restCertifiedUserMockMvc.perform(get("/api/certified-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCertifiedUser() throws Exception {
        // Initialize the database
        certifiedUserRepository.save(certifiedUser);

        int databaseSizeBeforeUpdate = certifiedUserRepository.findAll().size();

        // Update the certifiedUser
        CertifiedUser updatedCertifiedUser = certifiedUserRepository.findById(certifiedUser.getId()).get();
        updatedCertifiedUser
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER);

        restCertifiedUserMockMvc.perform(put("/api/certified-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCertifiedUser)))
            .andExpect(status().isOk());

        // Validate the CertifiedUser in the database
        List<CertifiedUser> certifiedUserList = certifiedUserRepository.findAll();
        assertThat(certifiedUserList).hasSize(databaseSizeBeforeUpdate);
        CertifiedUser testCertifiedUser = certifiedUserList.get(certifiedUserList.size() - 1);
        assertThat(testCertifiedUser.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testCertifiedUser.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testCertifiedUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCertifiedUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    public void updateNonExistingCertifiedUser() throws Exception {
        int databaseSizeBeforeUpdate = certifiedUserRepository.findAll().size();

        // Create the CertifiedUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertifiedUserMockMvc.perform(put("/api/certified-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certifiedUser)))
            .andExpect(status().isBadRequest());

        // Validate the CertifiedUser in the database
        List<CertifiedUser> certifiedUserList = certifiedUserRepository.findAll();
        assertThat(certifiedUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteCertifiedUser() throws Exception {
        // Initialize the database
        certifiedUserRepository.save(certifiedUser);

        int databaseSizeBeforeDelete = certifiedUserRepository.findAll().size();

        // Delete the certifiedUser
        restCertifiedUserMockMvc.perform(delete("/api/certified-users/{id}", certifiedUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CertifiedUser> certifiedUserList = certifiedUserRepository.findAll();
        assertThat(certifiedUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertifiedUser.class);
        CertifiedUser certifiedUser1 = new CertifiedUser();
        certifiedUser1.setId("id1");
        CertifiedUser certifiedUser2 = new CertifiedUser();
        certifiedUser2.setId(certifiedUser1.getId());
        assertThat(certifiedUser1).isEqualTo(certifiedUser2);
        certifiedUser2.setId("id2");
        assertThat(certifiedUser1).isNotEqualTo(certifiedUser2);
        certifiedUser1.setId(null);
        assertThat(certifiedUser1).isNotEqualTo(certifiedUser2);
    }
}
