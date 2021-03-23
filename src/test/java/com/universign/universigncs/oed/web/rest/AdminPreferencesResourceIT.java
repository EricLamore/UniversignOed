package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.AdminPreferences;
import com.universign.universigncs.oed.repository.AdminPreferencesRepository;

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
 * Integration tests for the {@link AdminPreferencesResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdminPreferencesResourceIT {

    private static final String DEFAULT_FILTER = "AAAAAAAAAA";
    private static final String UPDATED_FILTER = "BBBBBBBBBB";

    @Autowired
    private AdminPreferencesRepository adminPreferencesRepository;

    @Autowired
    private MockMvc restAdminPreferencesMockMvc;

    private AdminPreferences adminPreferences;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPreferences createEntity() {
        AdminPreferences adminPreferences = new AdminPreferences()
            .filter(DEFAULT_FILTER);
        return adminPreferences;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPreferences createUpdatedEntity() {
        AdminPreferences adminPreferences = new AdminPreferences()
            .filter(UPDATED_FILTER);
        return adminPreferences;
    }

    @BeforeEach
    public void initTest() {
        adminPreferencesRepository.deleteAll();
        adminPreferences = createEntity();
    }

    @Test
    public void createAdminPreferences() throws Exception {
        int databaseSizeBeforeCreate = adminPreferencesRepository.findAll().size();
        // Create the AdminPreferences
        restAdminPreferencesMockMvc.perform(post("/api/admin-preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPreferences)))
            .andExpect(status().isCreated());

        // Validate the AdminPreferences in the database
        List<AdminPreferences> adminPreferencesList = adminPreferencesRepository.findAll();
        assertThat(adminPreferencesList).hasSize(databaseSizeBeforeCreate + 1);
        AdminPreferences testAdminPreferences = adminPreferencesList.get(adminPreferencesList.size() - 1);
        assertThat(testAdminPreferences.getFilter()).isEqualTo(DEFAULT_FILTER);
    }

    @Test
    public void createAdminPreferencesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminPreferencesRepository.findAll().size();

        // Create the AdminPreferences with an existing ID
        adminPreferences.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminPreferencesMockMvc.perform(post("/api/admin-preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPreferences)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPreferences in the database
        List<AdminPreferences> adminPreferencesList = adminPreferencesRepository.findAll();
        assertThat(adminPreferencesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAdminPreferences() throws Exception {
        // Initialize the database
        adminPreferencesRepository.save(adminPreferences);

        // Get all the adminPreferencesList
        restAdminPreferencesMockMvc.perform(get("/api/admin-preferences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminPreferences.getId())))
            .andExpect(jsonPath("$.[*].filter").value(hasItem(DEFAULT_FILTER)));
    }
    
    @Test
    public void getAdminPreferences() throws Exception {
        // Initialize the database
        adminPreferencesRepository.save(adminPreferences);

        // Get the adminPreferences
        restAdminPreferencesMockMvc.perform(get("/api/admin-preferences/{id}", adminPreferences.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adminPreferences.getId()))
            .andExpect(jsonPath("$.filter").value(DEFAULT_FILTER));
    }
    @Test
    public void getNonExistingAdminPreferences() throws Exception {
        // Get the adminPreferences
        restAdminPreferencesMockMvc.perform(get("/api/admin-preferences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdminPreferences() throws Exception {
        // Initialize the database
        adminPreferencesRepository.save(adminPreferences);

        int databaseSizeBeforeUpdate = adminPreferencesRepository.findAll().size();

        // Update the adminPreferences
        AdminPreferences updatedAdminPreferences = adminPreferencesRepository.findById(adminPreferences.getId()).get();
        updatedAdminPreferences
            .filter(UPDATED_FILTER);

        restAdminPreferencesMockMvc.perform(put("/api/admin-preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdminPreferences)))
            .andExpect(status().isOk());

        // Validate the AdminPreferences in the database
        List<AdminPreferences> adminPreferencesList = adminPreferencesRepository.findAll();
        assertThat(adminPreferencesList).hasSize(databaseSizeBeforeUpdate);
        AdminPreferences testAdminPreferences = adminPreferencesList.get(adminPreferencesList.size() - 1);
        assertThat(testAdminPreferences.getFilter()).isEqualTo(UPDATED_FILTER);
    }

    @Test
    public void updateNonExistingAdminPreferences() throws Exception {
        int databaseSizeBeforeUpdate = adminPreferencesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminPreferencesMockMvc.perform(put("/api/admin-preferences")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPreferences)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPreferences in the database
        List<AdminPreferences> adminPreferencesList = adminPreferencesRepository.findAll();
        assertThat(adminPreferencesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAdminPreferences() throws Exception {
        // Initialize the database
        adminPreferencesRepository.save(adminPreferences);

        int databaseSizeBeforeDelete = adminPreferencesRepository.findAll().size();

        // Delete the adminPreferences
        restAdminPreferencesMockMvc.perform(delete("/api/admin-preferences/{id}", adminPreferences.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminPreferences> adminPreferencesList = adminPreferencesRepository.findAll();
        assertThat(adminPreferencesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
