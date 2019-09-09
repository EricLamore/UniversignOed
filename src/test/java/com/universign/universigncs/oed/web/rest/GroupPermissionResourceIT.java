package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.GroupPermission;
import com.universign.universigncs.oed.repository.GroupPermissionRepository;
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
 * Integration tests for the {@link GroupPermissionResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class GroupPermissionResourceIT {

    private static final Boolean DEFAULT_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION = false;
    private static final Boolean UPDATED_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION = true;

    private static final Boolean DEFAULT_ALLOW_CUSTOMIZATION = false;
    private static final Boolean UPDATED_ALLOW_CUSTOMIZATION = true;

    private static final Boolean DEFAULT_ALLOW_AFFILIATED_GROUP = false;
    private static final Boolean UPDATED_ALLOW_AFFILIATED_GROUP = true;

    private static final Boolean DEFAULT_ALLOW_AFFILIATED_CUSTOMIZATION = false;
    private static final Boolean UPDATED_ALLOW_AFFILIATED_CUSTOMIZATION = true;

    @Autowired
    private GroupPermissionRepository groupPermissionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restGroupPermissionMockMvc;

    private GroupPermission groupPermission;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupPermissionResource groupPermissionResource = new GroupPermissionResource(groupPermissionRepository);
        this.restGroupPermissionMockMvc = MockMvcBuilders.standaloneSetup(groupPermissionResource)
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
    public static GroupPermission createEntity() {
        GroupPermission groupPermission = new GroupPermission()
            .moveOperatorsToUniversignOrganization(DEFAULT_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION)
            .allowCustomization(DEFAULT_ALLOW_CUSTOMIZATION)
            .allowAffiliatedGroup(DEFAULT_ALLOW_AFFILIATED_GROUP)
            .allowAffiliatedCustomization(DEFAULT_ALLOW_AFFILIATED_CUSTOMIZATION);
        return groupPermission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GroupPermission createUpdatedEntity() {
        GroupPermission groupPermission = new GroupPermission()
            .moveOperatorsToUniversignOrganization(UPDATED_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION)
            .allowCustomization(UPDATED_ALLOW_CUSTOMIZATION)
            .allowAffiliatedGroup(UPDATED_ALLOW_AFFILIATED_GROUP)
            .allowAffiliatedCustomization(UPDATED_ALLOW_AFFILIATED_CUSTOMIZATION);
        return groupPermission;
    }

    @BeforeEach
    public void initTest() {
        groupPermissionRepository.deleteAll();
        groupPermission = createEntity();
    }

    @Test
    public void createGroupPermission() throws Exception {
        int databaseSizeBeforeCreate = groupPermissionRepository.findAll().size();

        // Create the GroupPermission
        restGroupPermissionMockMvc.perform(post("/api/group-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupPermission)))
            .andExpect(status().isCreated());

        // Validate the GroupPermission in the database
        List<GroupPermission> groupPermissionList = groupPermissionRepository.findAll();
        assertThat(groupPermissionList).hasSize(databaseSizeBeforeCreate + 1);
        GroupPermission testGroupPermission = groupPermissionList.get(groupPermissionList.size() - 1);
        assertThat(testGroupPermission.isMoveOperatorsToUniversignOrganization()).isEqualTo(DEFAULT_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION);
        assertThat(testGroupPermission.isAllowCustomization()).isEqualTo(DEFAULT_ALLOW_CUSTOMIZATION);
        assertThat(testGroupPermission.isAllowAffiliatedGroup()).isEqualTo(DEFAULT_ALLOW_AFFILIATED_GROUP);
        assertThat(testGroupPermission.isAllowAffiliatedCustomization()).isEqualTo(DEFAULT_ALLOW_AFFILIATED_CUSTOMIZATION);
    }

    @Test
    public void createGroupPermissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupPermissionRepository.findAll().size();

        // Create the GroupPermission with an existing ID
        groupPermission.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupPermissionMockMvc.perform(post("/api/group-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupPermission)))
            .andExpect(status().isBadRequest());

        // Validate the GroupPermission in the database
        List<GroupPermission> groupPermissionList = groupPermissionRepository.findAll();
        assertThat(groupPermissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllGroupPermissions() throws Exception {
        // Initialize the database
        groupPermissionRepository.save(groupPermission);

        // Get all the groupPermissionList
        restGroupPermissionMockMvc.perform(get("/api/group-permissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupPermission.getId())))
            .andExpect(jsonPath("$.[*].moveOperatorsToUniversignOrganization").value(hasItem(DEFAULT_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION.booleanValue())))
            .andExpect(jsonPath("$.[*].allowCustomization").value(hasItem(DEFAULT_ALLOW_CUSTOMIZATION.booleanValue())))
            .andExpect(jsonPath("$.[*].allowAffiliatedGroup").value(hasItem(DEFAULT_ALLOW_AFFILIATED_GROUP.booleanValue())))
            .andExpect(jsonPath("$.[*].allowAffiliatedCustomization").value(hasItem(DEFAULT_ALLOW_AFFILIATED_CUSTOMIZATION.booleanValue())));
    }
    
    @Test
    public void getGroupPermission() throws Exception {
        // Initialize the database
        groupPermissionRepository.save(groupPermission);

        // Get the groupPermission
        restGroupPermissionMockMvc.perform(get("/api/group-permissions/{id}", groupPermission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(groupPermission.getId()))
            .andExpect(jsonPath("$.moveOperatorsToUniversignOrganization").value(DEFAULT_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION.booleanValue()))
            .andExpect(jsonPath("$.allowCustomization").value(DEFAULT_ALLOW_CUSTOMIZATION.booleanValue()))
            .andExpect(jsonPath("$.allowAffiliatedGroup").value(DEFAULT_ALLOW_AFFILIATED_GROUP.booleanValue()))
            .andExpect(jsonPath("$.allowAffiliatedCustomization").value(DEFAULT_ALLOW_AFFILIATED_CUSTOMIZATION.booleanValue()));
    }

    @Test
    public void getNonExistingGroupPermission() throws Exception {
        // Get the groupPermission
        restGroupPermissionMockMvc.perform(get("/api/group-permissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroupPermission() throws Exception {
        // Initialize the database
        groupPermissionRepository.save(groupPermission);

        int databaseSizeBeforeUpdate = groupPermissionRepository.findAll().size();

        // Update the groupPermission
        GroupPermission updatedGroupPermission = groupPermissionRepository.findById(groupPermission.getId()).get();
        updatedGroupPermission
            .moveOperatorsToUniversignOrganization(UPDATED_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION)
            .allowCustomization(UPDATED_ALLOW_CUSTOMIZATION)
            .allowAffiliatedGroup(UPDATED_ALLOW_AFFILIATED_GROUP)
            .allowAffiliatedCustomization(UPDATED_ALLOW_AFFILIATED_CUSTOMIZATION);

        restGroupPermissionMockMvc.perform(put("/api/group-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroupPermission)))
            .andExpect(status().isOk());

        // Validate the GroupPermission in the database
        List<GroupPermission> groupPermissionList = groupPermissionRepository.findAll();
        assertThat(groupPermissionList).hasSize(databaseSizeBeforeUpdate);
        GroupPermission testGroupPermission = groupPermissionList.get(groupPermissionList.size() - 1);
        assertThat(testGroupPermission.isMoveOperatorsToUniversignOrganization()).isEqualTo(UPDATED_MOVE_OPERATORS_TO_UNIVERSIGN_ORGANIZATION);
        assertThat(testGroupPermission.isAllowCustomization()).isEqualTo(UPDATED_ALLOW_CUSTOMIZATION);
        assertThat(testGroupPermission.isAllowAffiliatedGroup()).isEqualTo(UPDATED_ALLOW_AFFILIATED_GROUP);
        assertThat(testGroupPermission.isAllowAffiliatedCustomization()).isEqualTo(UPDATED_ALLOW_AFFILIATED_CUSTOMIZATION);
    }

    @Test
    public void updateNonExistingGroupPermission() throws Exception {
        int databaseSizeBeforeUpdate = groupPermissionRepository.findAll().size();

        // Create the GroupPermission

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupPermissionMockMvc.perform(put("/api/group-permissions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(groupPermission)))
            .andExpect(status().isBadRequest());

        // Validate the GroupPermission in the database
        List<GroupPermission> groupPermissionList = groupPermissionRepository.findAll();
        assertThat(groupPermissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroupPermission() throws Exception {
        // Initialize the database
        groupPermissionRepository.save(groupPermission);

        int databaseSizeBeforeDelete = groupPermissionRepository.findAll().size();

        // Delete the groupPermission
        restGroupPermissionMockMvc.perform(delete("/api/group-permissions/{id}", groupPermission.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GroupPermission> groupPermissionList = groupPermissionRepository.findAll();
        assertThat(groupPermissionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupPermission.class);
        GroupPermission groupPermission1 = new GroupPermission();
        groupPermission1.setId("id1");
        GroupPermission groupPermission2 = new GroupPermission();
        groupPermission2.setId(groupPermission1.getId());
        assertThat(groupPermission1).isEqualTo(groupPermission2);
        groupPermission2.setId("id2");
        assertThat(groupPermission1).isNotEqualTo(groupPermission2);
        groupPermission1.setId(null);
        assertThat(groupPermission1).isNotEqualTo(groupPermission2);
    }
}
