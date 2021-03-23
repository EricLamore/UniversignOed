package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.CertifiedUser;
import com.universign.universigncs.oed.repository.CertifiedUserRepository;

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

/**
 * Integration tests for the {@link CertifiedUserResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
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
    private MockMvc restCertifiedUserMockMvc;

    private CertifiedUser certifiedUser;

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
            .contentType(MediaType.APPLICATION_JSON)
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
            .contentType(MediaType.APPLICATION_JSON)
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
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certifiedUser.getId())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)));
    }
    
    @Test
    public void getCertifiedUser() throws Exception {
        // Initialize the database
        certifiedUserRepository.save(certifiedUser);

        // Get the certifiedUser
        restCertifiedUserMockMvc.perform(get("/api/certified-users/{id}", certifiedUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(certifiedUser.getId()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER));
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
            .contentType(MediaType.APPLICATION_JSON)
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

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCertifiedUserMockMvc.perform(put("/api/certified-users")
            .contentType(MediaType.APPLICATION_JSON)
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
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CertifiedUser> certifiedUserList = certifiedUserRepository.findAll();
        assertThat(certifiedUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
