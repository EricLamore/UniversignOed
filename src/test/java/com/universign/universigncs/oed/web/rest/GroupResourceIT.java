package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Group;
import com.universign.universigncs.oed.repository.GroupRepository;
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

import com.universign.universigncs.oed.domain.enumeration.GroupStatus;
/**
 * Integration tests for the {@link GroupResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class GroupResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSIGN_ORGANIZATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSIGN_ORGANIZATION_ID = "BBBBBBBBBB";

    private static final GroupStatus DEFAULT_STATUS = GroupStatus.REGISTERING;
    private static final GroupStatus UPDATED_STATUS = GroupStatus.ACTIVE;

    private static final Boolean DEFAULT_ACTIVATE_ADVANCED = false;
    private static final Boolean UPDATED_ACTIVATE_ADVANCED = true;

    private static final String DEFAULT_UNIVERSIGN_ORGANIZATION_PROFIL = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSIGN_ORGANIZATION_PROFIL = "BBBBBBBBBB";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restGroupMockMvc;

    private Group group;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GroupResource groupResource = new GroupResource(groupRepository);
        this.restGroupMockMvc = MockMvcBuilders.standaloneSetup(groupResource)
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
    public static Group createEntity() {
        Group group = new Group()
            .name(DEFAULT_NAME)
            .universignOrganizationId(DEFAULT_UNIVERSIGN_ORGANIZATION_ID)
            .status(DEFAULT_STATUS)
            .activateAdvanced(DEFAULT_ACTIVATE_ADVANCED)
            .universignOrganizationProfil(DEFAULT_UNIVERSIGN_ORGANIZATION_PROFIL);
        return group;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Group createUpdatedEntity() {
        Group group = new Group()
            .name(UPDATED_NAME)
            .universignOrganizationId(UPDATED_UNIVERSIGN_ORGANIZATION_ID)
            .status(UPDATED_STATUS)
            .activateAdvanced(UPDATED_ACTIVATE_ADVANCED)
            .universignOrganizationProfil(UPDATED_UNIVERSIGN_ORGANIZATION_PROFIL);
        return group;
    }

    @BeforeEach
    public void initTest() {
        groupRepository.deleteAll();
        group = createEntity();
    }

    @Test
    public void createGroup() throws Exception {
        int databaseSizeBeforeCreate = groupRepository.findAll().size();

        // Create the Group
        restGroupMockMvc.perform(post("/api/groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(group)))
            .andExpect(status().isCreated());

        // Validate the Group in the database
        List<Group> groupList = groupRepository.findAll();
        assertThat(groupList).hasSize(databaseSizeBeforeCreate + 1);
        Group testGroup = groupList.get(groupList.size() - 1);
        assertThat(testGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGroup.getUniversignOrganizationId()).isEqualTo(DEFAULT_UNIVERSIGN_ORGANIZATION_ID);
        assertThat(testGroup.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testGroup.isActivateAdvanced()).isEqualTo(DEFAULT_ACTIVATE_ADVANCED);
        assertThat(testGroup.getUniversignOrganizationProfil()).isEqualTo(DEFAULT_UNIVERSIGN_ORGANIZATION_PROFIL);
    }

    @Test
    public void createGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = groupRepository.findAll().size();

        // Create the Group with an existing ID
        group.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupMockMvc.perform(post("/api/groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(group)))
            .andExpect(status().isBadRequest());

        // Validate the Group in the database
        List<Group> groupList = groupRepository.findAll();
        assertThat(groupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupRepository.findAll().size();
        // set the field null
        group.setName(null);

        // Create the Group, which fails.

        restGroupMockMvc.perform(post("/api/groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(group)))
            .andExpect(status().isBadRequest());

        List<Group> groupList = groupRepository.findAll();
        assertThat(groupList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllGroups() throws Exception {
        // Initialize the database
        groupRepository.save(group);

        // Get all the groupList
        restGroupMockMvc.perform(get("/api/groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(group.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].universignOrganizationId").value(hasItem(DEFAULT_UNIVERSIGN_ORGANIZATION_ID.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].activateAdvanced").value(hasItem(DEFAULT_ACTIVATE_ADVANCED.booleanValue())))
            .andExpect(jsonPath("$.[*].universignOrganizationProfil").value(hasItem(DEFAULT_UNIVERSIGN_ORGANIZATION_PROFIL.toString())));
    }
    
    @Test
    public void getGroup() throws Exception {
        // Initialize the database
        groupRepository.save(group);

        // Get the group
        restGroupMockMvc.perform(get("/api/groups/{id}", group.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(group.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.universignOrganizationId").value(DEFAULT_UNIVERSIGN_ORGANIZATION_ID.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.activateAdvanced").value(DEFAULT_ACTIVATE_ADVANCED.booleanValue()))
            .andExpect(jsonPath("$.universignOrganizationProfil").value(DEFAULT_UNIVERSIGN_ORGANIZATION_PROFIL.toString()));
    }

    @Test
    public void getNonExistingGroup() throws Exception {
        // Get the group
        restGroupMockMvc.perform(get("/api/groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateGroup() throws Exception {
        // Initialize the database
        groupRepository.save(group);

        int databaseSizeBeforeUpdate = groupRepository.findAll().size();

        // Update the group
        Group updatedGroup = groupRepository.findById(group.getId()).get();
        updatedGroup
            .name(UPDATED_NAME)
            .universignOrganizationId(UPDATED_UNIVERSIGN_ORGANIZATION_ID)
            .status(UPDATED_STATUS)
            .activateAdvanced(UPDATED_ACTIVATE_ADVANCED)
            .universignOrganizationProfil(UPDATED_UNIVERSIGN_ORGANIZATION_PROFIL);

        restGroupMockMvc.perform(put("/api/groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGroup)))
            .andExpect(status().isOk());

        // Validate the Group in the database
        List<Group> groupList = groupRepository.findAll();
        assertThat(groupList).hasSize(databaseSizeBeforeUpdate);
        Group testGroup = groupList.get(groupList.size() - 1);
        assertThat(testGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGroup.getUniversignOrganizationId()).isEqualTo(UPDATED_UNIVERSIGN_ORGANIZATION_ID);
        assertThat(testGroup.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testGroup.isActivateAdvanced()).isEqualTo(UPDATED_ACTIVATE_ADVANCED);
        assertThat(testGroup.getUniversignOrganizationProfil()).isEqualTo(UPDATED_UNIVERSIGN_ORGANIZATION_PROFIL);
    }

    @Test
    public void updateNonExistingGroup() throws Exception {
        int databaseSizeBeforeUpdate = groupRepository.findAll().size();

        // Create the Group

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupMockMvc.perform(put("/api/groups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(group)))
            .andExpect(status().isBadRequest());

        // Validate the Group in the database
        List<Group> groupList = groupRepository.findAll();
        assertThat(groupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteGroup() throws Exception {
        // Initialize the database
        groupRepository.save(group);

        int databaseSizeBeforeDelete = groupRepository.findAll().size();

        // Delete the group
        restGroupMockMvc.perform(delete("/api/groups/{id}", group.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Group> groupList = groupRepository.findAll();
        assertThat(groupList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Group.class);
        Group group1 = new Group();
        group1.setId("id1");
        Group group2 = new Group();
        group2.setId(group1.getId());
        assertThat(group1).isEqualTo(group2);
        group2.setId("id2");
        assertThat(group1).isNotEqualTo(group2);
        group1.setId(null);
        assertThat(group1).isNotEqualTo(group2);
    }
}
