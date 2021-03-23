package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.GroupConfiguration;
import com.universign.universigncs.oed.repository.GroupConfigurationRepository;

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
 * Integration tests for the {@link GroupConfigurationResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GroupConfigurationResourceIT {

    @Autowired
    private GroupConfigurationRepository groupConfigurationRepository;

    @Autowired
    private MockMvc restGroupConfigurationMockMvc;

    private GroupConfiguration groupConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupConfiguration createEntity() {
        GroupConfiguration groupConfiguration = new GroupConfiguration();
        return groupConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupConfiguration createUpdatedEntity() {
        GroupConfiguration groupConfiguration = new GroupConfiguration();
        return groupConfiguration;
    }

    @BeforeEach
    public void initTest() {
        groupConfigurationRepository.deleteAll();
        groupConfiguration = createEntity();
    }

    @Test
    public void createGroupConfiguration() throws Exception {
        int databaseSizeBeforeCreate = groupConfigurationRepository.findAll().size();
        // Create the GroupConfiguration
        restGroupConfigurationMockMvc.perform(post("/api/group-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupConfiguration)))
            .andExpect(status().isCreated());

        // Validate the GroupConfiguration in the database
        List<GroupConfiguration> groupConfigurationList = groupConfigurationRepository.findAll();
        assertThat(groupConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        GroupConfiguration testGroupConfiguration = groupConfigurationList.get(groupConfigurationList.size() - 1);
    }

    @Test
    public void createGroupConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupConfigurationRepository.findAll().size();

        // Create the GroupConfiguration with an existing ID
        groupConfiguration.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupConfigurationMockMvc.perform(post("/api/group-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the GroupConfiguration in the database
        List<GroupConfiguration> groupConfigurationList = groupConfigurationRepository.findAll();
        assertThat(groupConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllGroupConfigurations() throws Exception {
        // Initialize the database
        groupConfigurationRepository.save(groupConfiguration);

        // Get all the groupConfigurationList
        restGroupConfigurationMockMvc.perform(get("/api/group-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupConfiguration.getId())));
    }
    
    @Test
    public void getGroupConfiguration() throws Exception {
        // Initialize the database
        groupConfigurationRepository.save(groupConfiguration);

        // Get the groupConfiguration
        restGroupConfigurationMockMvc.perform(get("/api/group-configurations/{id}", groupConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupConfiguration.getId()));
    }
    @Test
    public void getNonExistingGroupConfiguration() throws Exception {
        // Get the groupConfiguration
        restGroupConfigurationMockMvc.perform(get("/api/group-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroupConfiguration() throws Exception {
        // Initialize the database
        groupConfigurationRepository.save(groupConfiguration);

        int databaseSizeBeforeUpdate = groupConfigurationRepository.findAll().size();

        // Update the groupConfiguration
        GroupConfiguration updatedGroupConfiguration = groupConfigurationRepository.findById(groupConfiguration.getId()).get();

        restGroupConfigurationMockMvc.perform(put("/api/group-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupConfiguration)))
            .andExpect(status().isOk());

        // Validate the GroupConfiguration in the database
        List<GroupConfiguration> groupConfigurationList = groupConfigurationRepository.findAll();
        assertThat(groupConfigurationList).hasSize(databaseSizeBeforeUpdate);
        GroupConfiguration testGroupConfiguration = groupConfigurationList.get(groupConfigurationList.size() - 1);
    }

    @Test
    public void updateNonExistingGroupConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = groupConfigurationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupConfigurationMockMvc.perform(put("/api/group-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(groupConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the GroupConfiguration in the database
        List<GroupConfiguration> groupConfigurationList = groupConfigurationRepository.findAll();
        assertThat(groupConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroupConfiguration() throws Exception {
        // Initialize the database
        groupConfigurationRepository.save(groupConfiguration);

        int databaseSizeBeforeDelete = groupConfigurationRepository.findAll().size();

        // Delete the groupConfiguration
        restGroupConfigurationMockMvc.perform(delete("/api/group-configurations/{id}", groupConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupConfiguration> groupConfigurationList = groupConfigurationRepository.findAll();
        assertThat(groupConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
