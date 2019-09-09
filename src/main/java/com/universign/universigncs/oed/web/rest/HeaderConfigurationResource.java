package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.HeaderConfiguration;
import com.universign.universigncs.oed.repository.HeaderConfigurationRepository;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.universign.universigncs.oed.domain.HeaderConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class HeaderConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(HeaderConfigurationResource.class);

    private static final String ENTITY_NAME = "headerConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HeaderConfigurationRepository headerConfigurationRepository;

    public HeaderConfigurationResource(HeaderConfigurationRepository headerConfigurationRepository) {
        this.headerConfigurationRepository = headerConfigurationRepository;
    }

    /**
     * {@code POST  /header-configurations} : Create a new headerConfiguration.
     *
     * @param headerConfiguration the headerConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new headerConfiguration, or with status {@code 400 (Bad Request)} if the headerConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/header-configurations")
    public ResponseEntity<HeaderConfiguration> createHeaderConfiguration(@RequestBody HeaderConfiguration headerConfiguration) throws URISyntaxException {
        log.debug("REST request to save HeaderConfiguration : {}", headerConfiguration);
        if (headerConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new headerConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HeaderConfiguration result = headerConfigurationRepository.save(headerConfiguration);
        return ResponseEntity.created(new URI("/api/header-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /header-configurations} : Updates an existing headerConfiguration.
     *
     * @param headerConfiguration the headerConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated headerConfiguration,
     * or with status {@code 400 (Bad Request)} if the headerConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the headerConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/header-configurations")
    public ResponseEntity<HeaderConfiguration> updateHeaderConfiguration(@RequestBody HeaderConfiguration headerConfiguration) throws URISyntaxException {
        log.debug("REST request to update HeaderConfiguration : {}", headerConfiguration);
        if (headerConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HeaderConfiguration result = headerConfigurationRepository.save(headerConfiguration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, headerConfiguration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /header-configurations} : get all the headerConfigurations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of headerConfigurations in body.
     */
    @GetMapping("/header-configurations")
    public ResponseEntity<List<HeaderConfiguration>> getAllHeaderConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of HeaderConfigurations");
        Page<HeaderConfiguration> page = headerConfigurationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /header-configurations/:id} : get the "id" headerConfiguration.
     *
     * @param id the id of the headerConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the headerConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/header-configurations/{id}")
    public ResponseEntity<HeaderConfiguration> getHeaderConfiguration(@PathVariable String id) {
        log.debug("REST request to get HeaderConfiguration : {}", id);
        Optional<HeaderConfiguration> headerConfiguration = headerConfigurationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(headerConfiguration);
    }

    /**
     * {@code DELETE  /header-configurations/:id} : delete the "id" headerConfiguration.
     *
     * @param id the id of the headerConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/header-configurations/{id}")
    public ResponseEntity<Void> deleteHeaderConfiguration(@PathVariable String id) {
        log.debug("REST request to delete HeaderConfiguration : {}", id);
        headerConfigurationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
