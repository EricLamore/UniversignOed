package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.HeaderConfiguration;
import com.universign.universigncs.oed.repository.HeaderConfigurationRepository;
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
 * Integration tests for the {@link HeaderConfigurationResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class HeaderConfigurationResourceIT {

    private static final String DEFAULT_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_LOGO = "BBBBBBBBBB";

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;
    private static final Integer SMALLER_WIDTH = 1 - 1;

    private static final String DEFAULT_HREF = "AAAAAAAAAA";
    private static final String UPDATED_HREF = "BBBBBBBBBB";

    @Autowired
    private HeaderConfigurationRepository headerConfigurationRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restHeaderConfigurationMockMvc;

    private HeaderConfiguration headerConfiguration;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HeaderConfigurationResource headerConfigurationResource = new HeaderConfigurationResource(headerConfigurationRepository);
        this.restHeaderConfigurationMockMvc = MockMvcBuilders.standaloneSetup(headerConfigurationResource)
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
    public static HeaderConfiguration createEntity() {
        HeaderConfiguration headerConfiguration = new HeaderConfiguration()
            .logo(DEFAULT_LOGO)
            .width(DEFAULT_WIDTH)
            .href(DEFAULT_HREF);
        return headerConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HeaderConfiguration createUpdatedEntity() {
        HeaderConfiguration headerConfiguration = new HeaderConfiguration()
            .logo(UPDATED_LOGO)
            .width(UPDATED_WIDTH)
            .href(UPDATED_HREF);
        return headerConfiguration;
    }

    @BeforeEach
    public void initTest() {
        headerConfigurationRepository.deleteAll();
        headerConfiguration = createEntity();
    }

    @Test
    public void createHeaderConfiguration() throws Exception {
        int databaseSizeBeforeCreate = headerConfigurationRepository.findAll().size();

        // Create the HeaderConfiguration
        restHeaderConfigurationMockMvc.perform(post("/api/header-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(headerConfiguration)))
            .andExpect(status().isCreated());

        // Validate the HeaderConfiguration in the database
        List<HeaderConfiguration> headerConfigurationList = headerConfigurationRepository.findAll();
        assertThat(headerConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        HeaderConfiguration testHeaderConfiguration = headerConfigurationList.get(headerConfigurationList.size() - 1);
        assertThat(testHeaderConfiguration.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testHeaderConfiguration.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testHeaderConfiguration.getHref()).isEqualTo(DEFAULT_HREF);
    }

    @Test
    public void createHeaderConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = headerConfigurationRepository.findAll().size();

        // Create the HeaderConfiguration with an existing ID
        headerConfiguration.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restHeaderConfigurationMockMvc.perform(post("/api/header-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(headerConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the HeaderConfiguration in the database
        List<HeaderConfiguration> headerConfigurationList = headerConfigurationRepository.findAll();
        assertThat(headerConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllHeaderConfigurations() throws Exception {
        // Initialize the database
        headerConfigurationRepository.save(headerConfiguration);

        // Get all the headerConfigurationList
        restHeaderConfigurationMockMvc.perform(get("/api/header-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(headerConfiguration.getId())))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(DEFAULT_LOGO.toString())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].href").value(hasItem(DEFAULT_HREF.toString())));
    }
    
    @Test
    public void getHeaderConfiguration() throws Exception {
        // Initialize the database
        headerConfigurationRepository.save(headerConfiguration);

        // Get the headerConfiguration
        restHeaderConfigurationMockMvc.perform(get("/api/header-configurations/{id}", headerConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(headerConfiguration.getId()))
            .andExpect(jsonPath("$.logo").value(DEFAULT_LOGO.toString()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.href").value(DEFAULT_HREF.toString()));
    }

    @Test
    public void getNonExistingHeaderConfiguration() throws Exception {
        // Get the headerConfiguration
        restHeaderConfigurationMockMvc.perform(get("/api/header-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateHeaderConfiguration() throws Exception {
        // Initialize the database
        headerConfigurationRepository.save(headerConfiguration);

        int databaseSizeBeforeUpdate = headerConfigurationRepository.findAll().size();

        // Update the headerConfiguration
        HeaderConfiguration updatedHeaderConfiguration = headerConfigurationRepository.findById(headerConfiguration.getId()).get();
        updatedHeaderConfiguration
            .logo(UPDATED_LOGO)
            .width(UPDATED_WIDTH)
            .href(UPDATED_HREF);

        restHeaderConfigurationMockMvc.perform(put("/api/header-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHeaderConfiguration)))
            .andExpect(status().isOk());

        // Validate the HeaderConfiguration in the database
        List<HeaderConfiguration> headerConfigurationList = headerConfigurationRepository.findAll();
        assertThat(headerConfigurationList).hasSize(databaseSizeBeforeUpdate);
        HeaderConfiguration testHeaderConfiguration = headerConfigurationList.get(headerConfigurationList.size() - 1);
        assertThat(testHeaderConfiguration.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testHeaderConfiguration.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testHeaderConfiguration.getHref()).isEqualTo(UPDATED_HREF);
    }

    @Test
    public void updateNonExistingHeaderConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = headerConfigurationRepository.findAll().size();

        // Create the HeaderConfiguration

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHeaderConfigurationMockMvc.perform(put("/api/header-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(headerConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the HeaderConfiguration in the database
        List<HeaderConfiguration> headerConfigurationList = headerConfigurationRepository.findAll();
        assertThat(headerConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteHeaderConfiguration() throws Exception {
        // Initialize the database
        headerConfigurationRepository.save(headerConfiguration);

        int databaseSizeBeforeDelete = headerConfigurationRepository.findAll().size();

        // Delete the headerConfiguration
        restHeaderConfigurationMockMvc.perform(delete("/api/header-configurations/{id}", headerConfiguration.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HeaderConfiguration> headerConfigurationList = headerConfigurationRepository.findAll();
        assertThat(headerConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HeaderConfiguration.class);
        HeaderConfiguration headerConfiguration1 = new HeaderConfiguration();
        headerConfiguration1.setId("id1");
        HeaderConfiguration headerConfiguration2 = new HeaderConfiguration();
        headerConfiguration2.setId(headerConfiguration1.getId());
        assertThat(headerConfiguration1).isEqualTo(headerConfiguration2);
        headerConfiguration2.setId("id2");
        assertThat(headerConfiguration1).isNotEqualTo(headerConfiguration2);
        headerConfiguration1.setId(null);
        assertThat(headerConfiguration1).isNotEqualTo(headerConfiguration2);
    }
}
