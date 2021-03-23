package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.AdminPermissions;
import com.universign.universigncs.oed.repository.AdminPermissionsRepository;

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
 * Integration tests for the {@link AdminPermissionsResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdminPermissionsResourceIT {

    private static final Boolean DEFAULT_OED_WRITE = false;
    private static final Boolean UPDATED_OED_WRITE = true;

    private static final Boolean DEFAULT_GROUP_WRITE = false;
    private static final Boolean UPDATED_GROUP_WRITE = true;

    private static final Boolean DEFAULT_ADMIN_WRITE = false;
    private static final Boolean UPDATED_ADMIN_WRITE = true;

    @Autowired
    private AdminPermissionsRepository adminPermissionsRepository;

    @Autowired
    private MockMvc restAdminPermissionsMockMvc;

    private AdminPermissions adminPermissions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPermissions createEntity() {
        AdminPermissions adminPermissions = new AdminPermissions()
            .oedWrite(DEFAULT_OED_WRITE)
            .groupWrite(DEFAULT_GROUP_WRITE)
            .adminWrite(DEFAULT_ADMIN_WRITE);
        return adminPermissions;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdminPermissions createUpdatedEntity() {
        AdminPermissions adminPermissions = new AdminPermissions()
            .oedWrite(UPDATED_OED_WRITE)
            .groupWrite(UPDATED_GROUP_WRITE)
            .adminWrite(UPDATED_ADMIN_WRITE);
        return adminPermissions;
    }

    @BeforeEach
    public void initTest() {
        adminPermissionsRepository.deleteAll();
        adminPermissions = createEntity();
    }

    @Test
    public void createAdminPermissions() throws Exception {
        int databaseSizeBeforeCreate = adminPermissionsRepository.findAll().size();
        // Create the AdminPermissions
        restAdminPermissionsMockMvc.perform(post("/api/admin-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPermissions)))
            .andExpect(status().isCreated());

        // Validate the AdminPermissions in the database
        List<AdminPermissions> adminPermissionsList = adminPermissionsRepository.findAll();
        assertThat(adminPermissionsList).hasSize(databaseSizeBeforeCreate + 1);
        AdminPermissions testAdminPermissions = adminPermissionsList.get(adminPermissionsList.size() - 1);
        assertThat(testAdminPermissions.isOedWrite()).isEqualTo(DEFAULT_OED_WRITE);
        assertThat(testAdminPermissions.isGroupWrite()).isEqualTo(DEFAULT_GROUP_WRITE);
        assertThat(testAdminPermissions.isAdminWrite()).isEqualTo(DEFAULT_ADMIN_WRITE);
    }

    @Test
    public void createAdminPermissionsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adminPermissionsRepository.findAll().size();

        // Create the AdminPermissions with an existing ID
        adminPermissions.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdminPermissionsMockMvc.perform(post("/api/admin-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPermissions)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPermissions in the database
        List<AdminPermissions> adminPermissionsList = adminPermissionsRepository.findAll();
        assertThat(adminPermissionsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAdminPermissions() throws Exception {
        // Initialize the database
        adminPermissionsRepository.save(adminPermissions);

        // Get all the adminPermissionsList
        restAdminPermissionsMockMvc.perform(get("/api/admin-permissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adminPermissions.getId())))
            .andExpect(jsonPath("$.[*].oedWrite").value(hasItem(DEFAULT_OED_WRITE.booleanValue())))
            .andExpect(jsonPath("$.[*].groupWrite").value(hasItem(DEFAULT_GROUP_WRITE.booleanValue())))
            .andExpect(jsonPath("$.[*].adminWrite").value(hasItem(DEFAULT_ADMIN_WRITE.booleanValue())));
    }
    
    @Test
    public void getAdminPermissions() throws Exception {
        // Initialize the database
        adminPermissionsRepository.save(adminPermissions);

        // Get the adminPermissions
        restAdminPermissionsMockMvc.perform(get("/api/admin-permissions/{id}", adminPermissions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adminPermissions.getId()))
            .andExpect(jsonPath("$.oedWrite").value(DEFAULT_OED_WRITE.booleanValue()))
            .andExpect(jsonPath("$.groupWrite").value(DEFAULT_GROUP_WRITE.booleanValue()))
            .andExpect(jsonPath("$.adminWrite").value(DEFAULT_ADMIN_WRITE.booleanValue()));
    }
    @Test
    public void getNonExistingAdminPermissions() throws Exception {
        // Get the adminPermissions
        restAdminPermissionsMockMvc.perform(get("/api/admin-permissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAdminPermissions() throws Exception {
        // Initialize the database
        adminPermissionsRepository.save(adminPermissions);

        int databaseSizeBeforeUpdate = adminPermissionsRepository.findAll().size();

        // Update the adminPermissions
        AdminPermissions updatedAdminPermissions = adminPermissionsRepository.findById(adminPermissions.getId()).get();
        updatedAdminPermissions
            .oedWrite(UPDATED_OED_WRITE)
            .groupWrite(UPDATED_GROUP_WRITE)
            .adminWrite(UPDATED_ADMIN_WRITE);

        restAdminPermissionsMockMvc.perform(put("/api/admin-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdminPermissions)))
            .andExpect(status().isOk());

        // Validate the AdminPermissions in the database
        List<AdminPermissions> adminPermissionsList = adminPermissionsRepository.findAll();
        assertThat(adminPermissionsList).hasSize(databaseSizeBeforeUpdate);
        AdminPermissions testAdminPermissions = adminPermissionsList.get(adminPermissionsList.size() - 1);
        assertThat(testAdminPermissions.isOedWrite()).isEqualTo(UPDATED_OED_WRITE);
        assertThat(testAdminPermissions.isGroupWrite()).isEqualTo(UPDATED_GROUP_WRITE);
        assertThat(testAdminPermissions.isAdminWrite()).isEqualTo(UPDATED_ADMIN_WRITE);
    }

    @Test
    public void updateNonExistingAdminPermissions() throws Exception {
        int databaseSizeBeforeUpdate = adminPermissionsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdminPermissionsMockMvc.perform(put("/api/admin-permissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adminPermissions)))
            .andExpect(status().isBadRequest());

        // Validate the AdminPermissions in the database
        List<AdminPermissions> adminPermissionsList = adminPermissionsRepository.findAll();
        assertThat(adminPermissionsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAdminPermissions() throws Exception {
        // Initialize the database
        adminPermissionsRepository.save(adminPermissions);

        int databaseSizeBeforeDelete = adminPermissionsRepository.findAll().size();

        // Delete the adminPermissions
        restAdminPermissionsMockMvc.perform(delete("/api/admin-permissions/{id}", adminPermissions.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdminPermissions> adminPermissionsList = adminPermissionsRepository.findAll();
        assertThat(adminPermissionsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
