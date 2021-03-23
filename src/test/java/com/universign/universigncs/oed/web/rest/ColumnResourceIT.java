package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Column;
import com.universign.universigncs.oed.repository.ColumnRepository;

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
 * Integration tests for the {@link ColumnResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ColumnResourceIT {

    private static final Boolean DEFAULT_SHOW = false;
    private static final Boolean UPDATED_SHOW = true;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private MockMvc restColumnMockMvc;

    private Column column;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Column createEntity() {
        Column column = new Column()
            .show(DEFAULT_SHOW)
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE);
        return column;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Column createUpdatedEntity() {
        Column column = new Column()
            .show(UPDATED_SHOW)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE);
        return column;
    }

    @BeforeEach
    public void initTest() {
        columnRepository.deleteAll();
        column = createEntity();
    }

    @Test
    public void createColumn() throws Exception {
        int databaseSizeBeforeCreate = columnRepository.findAll().size();
        // Create the Column
        restColumnMockMvc.perform(post("/api/columns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(column)))
            .andExpect(status().isCreated());

        // Validate the Column in the database
        List<Column> columnList = columnRepository.findAll();
        assertThat(columnList).hasSize(databaseSizeBeforeCreate + 1);
        Column testColumn = columnList.get(columnList.size() - 1);
        assertThat(testColumn.isShow()).isEqualTo(DEFAULT_SHOW);
        assertThat(testColumn.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testColumn.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    public void createColumnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = columnRepository.findAll().size();

        // Create the Column with an existing ID
        column.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restColumnMockMvc.perform(post("/api/columns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(column)))
            .andExpect(status().isBadRequest());

        // Validate the Column in the database
        List<Column> columnList = columnRepository.findAll();
        assertThat(columnList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllColumns() throws Exception {
        // Initialize the database
        columnRepository.save(column);

        // Get all the columnList
        restColumnMockMvc.perform(get("/api/columns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(column.getId())))
            .andExpect(jsonPath("$.[*].show").value(hasItem(DEFAULT_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    public void getColumn() throws Exception {
        // Initialize the database
        columnRepository.save(column);

        // Get the column
        restColumnMockMvc.perform(get("/api/columns/{id}", column.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(column.getId()))
            .andExpect(jsonPath("$.show").value(DEFAULT_SHOW.booleanValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }
    @Test
    public void getNonExistingColumn() throws Exception {
        // Get the column
        restColumnMockMvc.perform(get("/api/columns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateColumn() throws Exception {
        // Initialize the database
        columnRepository.save(column);

        int databaseSizeBeforeUpdate = columnRepository.findAll().size();

        // Update the column
        Column updatedColumn = columnRepository.findById(column.getId()).get();
        updatedColumn
            .show(UPDATED_SHOW)
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE);

        restColumnMockMvc.perform(put("/api/columns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedColumn)))
            .andExpect(status().isOk());

        // Validate the Column in the database
        List<Column> columnList = columnRepository.findAll();
        assertThat(columnList).hasSize(databaseSizeBeforeUpdate);
        Column testColumn = columnList.get(columnList.size() - 1);
        assertThat(testColumn.isShow()).isEqualTo(UPDATED_SHOW);
        assertThat(testColumn.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testColumn.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    public void updateNonExistingColumn() throws Exception {
        int databaseSizeBeforeUpdate = columnRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColumnMockMvc.perform(put("/api/columns")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(column)))
            .andExpect(status().isBadRequest());

        // Validate the Column in the database
        List<Column> columnList = columnRepository.findAll();
        assertThat(columnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteColumn() throws Exception {
        // Initialize the database
        columnRepository.save(column);

        int databaseSizeBeforeDelete = columnRepository.findAll().size();

        // Delete the column
        restColumnMockMvc.perform(delete("/api/columns/{id}", column.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Column> columnList = columnRepository.findAll();
        assertThat(columnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
