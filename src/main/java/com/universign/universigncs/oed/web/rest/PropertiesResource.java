package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.Properties;
import com.universign.universigncs.oed.repository.PropertiesRepository;
import com.universign.universigncs.oed.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.universign.universigncs.oed.domain.Properties}.
 */
@RestController
@RequestMapping("/api")
public class PropertiesResource {

    private final Logger log = LoggerFactory.getLogger(PropertiesResource.class);

    private static final String ENTITY_NAME = "properties";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PropertiesRepository propertiesRepository;

    public PropertiesResource(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    /**
     * {@code POST  /properties} : Create a new properties.
     *
     * @param properties the properties to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new properties, or with status {@code 400 (Bad Request)} if the properties has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/properties")
    public ResponseEntity<Properties> createProperties(@RequestBody Properties properties) throws URISyntaxException {
        log.debug("REST request to save Properties : {}", properties);
        if (properties.getId() != null) {
            throw new BadRequestAlertException("A new properties cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Properties result = propertiesRepository.save(properties);
        return ResponseEntity.created(new URI("/api/properties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /properties} : Updates an existing properties.
     *
     * @param properties the properties to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated properties,
     * or with status {@code 400 (Bad Request)} if the properties is not valid,
     * or with status {@code 500 (Internal Server Error)} if the properties couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/properties")
    public ResponseEntity<Properties> updateProperties(@RequestBody Properties properties) throws URISyntaxException {
        log.debug("REST request to update Properties : {}", properties);
        if (properties.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Properties result = propertiesRepository.save(properties);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, properties.getId()))
            .body(result);
    }

    /**
     * {@code GET  /properties} : get all the properties.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of properties in body.
     */
    @GetMapping("/properties")
    public ResponseEntity<List<Properties>> getAllProperties(Pageable pageable) {
        log.debug("REST request to get a page of Properties");
        Page<Properties> page = propertiesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /properties/:id} : get the "id" properties.
     *
     * @param id the id of the properties to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the properties, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/properties/{id}")
    public ResponseEntity<Properties> getProperties(@PathVariable String id) {
        log.debug("REST request to get Properties : {}", id);
        Optional<Properties> properties = propertiesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(properties);
    }

    /**
     * {@code DELETE  /properties/:id} : delete the "id" properties.
     *
     * @param id the id of the properties to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Void> deleteProperties(@PathVariable String id) {
        log.debug("REST request to delete Properties : {}", id);
        propertiesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
