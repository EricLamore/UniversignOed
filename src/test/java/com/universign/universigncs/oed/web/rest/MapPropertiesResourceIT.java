package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.MapProperties;
import com.universign.universigncs.oed.repository.MapPropertiesRepository;

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
 * Integration tests for the {@link MapPropertiesResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MapPropertiesResourceIT {

    private static final String DEFAULT_DUMMY = "AAAAAAAAAA";
    private static final String UPDATED_DUMMY = "BBBBBBBBBB";

    @Autowired
    private MapPropertiesRepository mapPropertiesRepository;

    @Autowired
    private MockMvc restMapPropertiesMockMvc;

    private MapProperties mapProperties;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MapProperties createEntity() {
        MapProperties mapProperties = new MapProperties()
            .dummy(DEFAULT_DUMMY);
        return mapProperties;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MapProperties createUpdatedEntity() {
        MapProperties mapProperties = new MapProperties()
            .dummy(UPDATED_DUMMY);
        return mapProperties;
    }

    @BeforeEach
    public void initTest() {
        mapPropertiesRepository.deleteAll();
        mapProperties = createEntity();
    }

    @Test
    public void createMapProperties() throws Exception {
        int databaseSizeBeforeCreate = mapPropertiesRepository.findAll().size();
        // Create the MapProperties
        restMapPropertiesMockMvc.perform(post("/api/map-properties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mapProperties)))
            .andExpect(status().isCreated());

        // Validate the MapProperties in the database
        List<MapProperties> mapPropertiesList = mapPropertiesRepository.findAll();
        assertThat(mapPropertiesList).hasSize(databaseSizeBeforeCreate + 1);
        MapProperties testMapProperties = mapPropertiesList.get(mapPropertiesList.size() - 1);
        assertThat(testMapProperties.getDummy()).isEqualTo(DEFAULT_DUMMY);
    }

    @Test
    public void createMapPropertiesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapPropertiesRepository.findAll().size();

        // Create the MapProperties with an existing ID
        mapProperties.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapPropertiesMockMvc.perform(post("/api/map-properties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mapProperties)))
            .andExpect(status().isBadRequest());

        // Validate the MapProperties in the database
        List<MapProperties> mapPropertiesList = mapPropertiesRepository.findAll();
        assertThat(mapPropertiesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllMapProperties() throws Exception {
        // Initialize the database
        mapPropertiesRepository.save(mapProperties);

        // Get all the mapPropertiesList
        restMapPropertiesMockMvc.perform(get("/api/map-properties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapProperties.getId())))
            .andExpect(jsonPath("$.[*].dummy").value(hasItem(DEFAULT_DUMMY)));
    }
    
    @Test
    public void getMapProperties() throws Exception {
        // Initialize the database
        mapPropertiesRepository.save(mapProperties);

        // Get the mapProperties
        restMapPropertiesMockMvc.perform(get("/api/map-properties/{id}", mapProperties.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mapProperties.getId()))
            .andExpect(jsonPath("$.dummy").value(DEFAULT_DUMMY));
    }
    @Test
    public void getNonExistingMapProperties() throws Exception {
        // Get the mapProperties
        restMapPropertiesMockMvc.perform(get("/api/map-properties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMapProperties() throws Exception {
        // Initialize the database
        mapPropertiesRepository.save(mapProperties);

        int databaseSizeBeforeUpdate = mapPropertiesRepository.findAll().size();

        // Update the mapProperties
        MapProperties updatedMapProperties = mapPropertiesRepository.findById(mapProperties.getId()).get();
        updatedMapProperties
            .dummy(UPDATED_DUMMY);

        restMapPropertiesMockMvc.perform(put("/api/map-properties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMapProperties)))
            .andExpect(status().isOk());

        // Validate the MapProperties in the database
        List<MapProperties> mapPropertiesList = mapPropertiesRepository.findAll();
        assertThat(mapPropertiesList).hasSize(databaseSizeBeforeUpdate);
        MapProperties testMapProperties = mapPropertiesList.get(mapPropertiesList.size() - 1);
        assertThat(testMapProperties.getDummy()).isEqualTo(UPDATED_DUMMY);
    }

    @Test
    public void updateNonExistingMapProperties() throws Exception {
        int databaseSizeBeforeUpdate = mapPropertiesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMapPropertiesMockMvc.perform(put("/api/map-properties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mapProperties)))
            .andExpect(status().isBadRequest());

        // Validate the MapProperties in the database
        List<MapProperties> mapPropertiesList = mapPropertiesRepository.findAll();
        assertThat(mapPropertiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMapProperties() throws Exception {
        // Initialize the database
        mapPropertiesRepository.save(mapProperties);

        int databaseSizeBeforeDelete = mapPropertiesRepository.findAll().size();

        // Delete the mapProperties
        restMapPropertiesMockMvc.perform(delete("/api/map-properties/{id}", mapProperties.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MapProperties> mapPropertiesList = mapPropertiesRepository.findAll();
        assertThat(mapPropertiesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
