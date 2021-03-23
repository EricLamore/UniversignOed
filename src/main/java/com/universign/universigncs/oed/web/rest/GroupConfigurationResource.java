package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.GroupConfiguration;
import com.universign.universigncs.oed.repository.GroupConfigurationRepository;
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
 * REST controller for managing {@link com.universign.universigncs.oed.domain.GroupConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class GroupConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(GroupConfigurationResource.class);

    private static final String ENTITY_NAME = "groupConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupConfigurationRepository groupConfigurationRepository;

    public GroupConfigurationResource(GroupConfigurationRepository groupConfigurationRepository) {
        this.groupConfigurationRepository = groupConfigurationRepository;
    }

    /**
     * {@code POST  /group-configurations} : Create a new groupConfiguration.
     *
     * @param groupConfiguration the groupConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupConfiguration, or with status {@code 400 (Bad Request)} if the groupConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-configurations")
    public ResponseEntity<GroupConfiguration> createGroupConfiguration(@RequestBody GroupConfiguration groupConfiguration) throws URISyntaxException {
        log.debug("REST request to save GroupConfiguration : {}", groupConfiguration);
        if (groupConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new groupConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupConfiguration result = groupConfigurationRepository.save(groupConfiguration);
        return ResponseEntity.created(new URI("/api/group-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /group-configurations} : Updates an existing groupConfiguration.
     *
     * @param groupConfiguration the groupConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupConfiguration,
     * or with status {@code 400 (Bad Request)} if the groupConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-configurations")
    public ResponseEntity<GroupConfiguration> updateGroupConfiguration(@RequestBody GroupConfiguration groupConfiguration) throws URISyntaxException {
        log.debug("REST request to update GroupConfiguration : {}", groupConfiguration);
        if (groupConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupConfiguration result = groupConfigurationRepository.save(groupConfiguration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupConfiguration.getId()))
            .body(result);
    }

    /**
     * {@code GET  /group-configurations} : get all the groupConfigurations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupConfigurations in body.
     */
    @GetMapping("/group-configurations")
    public ResponseEntity<List<GroupConfiguration>> getAllGroupConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of GroupConfigurations");
        Page<GroupConfiguration> page = groupConfigurationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-configurations/:id} : get the "id" groupConfiguration.
     *
     * @param id the id of the groupConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-configurations/{id}")
    public ResponseEntity<GroupConfiguration> getGroupConfiguration(@PathVariable String id) {
        log.debug("REST request to get GroupConfiguration : {}", id);
        Optional<GroupConfiguration> groupConfiguration = groupConfigurationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(groupConfiguration);
    }

    /**
     * {@code DELETE  /group-configurations/:id} : delete the "id" groupConfiguration.
     *
     * @param id the id of the groupConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-configurations/{id}")
    public ResponseEntity<Void> deleteGroupConfiguration(@PathVariable String id) {
        log.debug("REST request to delete GroupConfiguration : {}", id);
        groupConfigurationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
