package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.UniversignOedApp;
import com.universign.universigncs.oed.domain.Link;
import com.universign.universigncs.oed.repository.LinkRepository;
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
 * Integration tests for the {@link LinkResource} REST controller.
 */
@SpringBootTest(classes = UniversignOedApp.class)
public class LinkResourceIT {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restLinkMockMvc;

    private Link link;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LinkResource linkResource = new LinkResource(linkRepository);
        this.restLinkMockMvc = MockMvcBuilders.standaloneSetup(linkResource)
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
    public static Link createEntity() {
        Link link = new Link();
        return link;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Link createUpdatedEntity() {
        Link link = new Link();
        return link;
    }

    @BeforeEach
    public void initTest() {
        linkRepository.deleteAll();
        link = createEntity();
    }

    @Test
    public void createLink() throws Exception {
        int databaseSizeBeforeCreate = linkRepository.findAll().size();

        // Create the Link
        restLinkMockMvc.perform(post("/api/links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(link)))
            .andExpect(status().isCreated());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeCreate + 1);
        Link testLink = linkList.get(linkList.size() - 1);
    }

    @Test
    public void createLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = linkRepository.findAll().size();

        // Create the Link with an existing ID
        link.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinkMockMvc.perform(post("/api/links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(link)))
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllLinks() throws Exception {
        // Initialize the database
        linkRepository.save(link);

        // Get all the linkList
        restLinkMockMvc.perform(get("/api/links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(link.getId())));
    }
    
    @Test
    public void getLink() throws Exception {
        // Initialize the database
        linkRepository.save(link);

        // Get the link
        restLinkMockMvc.perform(get("/api/links/{id}", link.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(link.getId()));
    }

    @Test
    public void getNonExistingLink() throws Exception {
        // Get the link
        restLinkMockMvc.perform(get("/api/links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLink() throws Exception {
        // Initialize the database
        linkRepository.save(link);

        int databaseSizeBeforeUpdate = linkRepository.findAll().size();

        // Update the link
        Link updatedLink = linkRepository.findById(link.getId()).get();

        restLinkMockMvc.perform(put("/api/links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLink)))
            .andExpect(status().isOk());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
        Link testLink = linkList.get(linkList.size() - 1);
    }

    @Test
    public void updateNonExistingLink() throws Exception {
        int databaseSizeBeforeUpdate = linkRepository.findAll().size();

        // Create the Link

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinkMockMvc.perform(put("/api/links")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(link)))
            .andExpect(status().isBadRequest());

        // Validate the Link in the database
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLink() throws Exception {
        // Initialize the database
        linkRepository.save(link);

        int databaseSizeBeforeDelete = linkRepository.findAll().size();

        // Delete the link
        restLinkMockMvc.perform(delete("/api/links/{id}", link.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Link> linkList = linkRepository.findAll();
        assertThat(linkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Link.class);
        Link link1 = new Link();
        link1.setId("id1");
        Link link2 = new Link();
        link2.setId(link1.getId());
        assertThat(link1).isEqualTo(link2);
        link2.setId("id2");
        assertThat(link1).isNotEqualTo(link2);
        link1.setId(null);
        assertThat(link1).isNotEqualTo(link2);
    }
}
