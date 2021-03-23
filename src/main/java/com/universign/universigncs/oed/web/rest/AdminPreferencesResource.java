package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.AdminPreferences;
import com.universign.universigncs.oed.repository.AdminPreferencesRepository;
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
 * REST controller for managing {@link com.universign.universigncs.oed.domain.AdminPreferences}.
 */
@RestController
@RequestMapping("/api")
public class AdminPreferencesResource {

    private final Logger log = LoggerFactory.getLogger(AdminPreferencesResource.class);

    private static final String ENTITY_NAME = "adminPreferences";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminPreferencesRepository adminPreferencesRepository;

    public AdminPreferencesResource(AdminPreferencesRepository adminPreferencesRepository) {
        this.adminPreferencesRepository = adminPreferencesRepository;
    }

    /**
     * {@code POST  /admin-preferences} : Create a new adminPreferences.
     *
     * @param adminPreferences the adminPreferences to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminPreferences, or with status {@code 400 (Bad Request)} if the adminPreferences has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-preferences")
    public ResponseEntity<AdminPreferences> createAdminPreferences(@RequestBody AdminPreferences adminPreferences) throws URISyntaxException {
        log.debug("REST request to save AdminPreferences : {}", adminPreferences);
        if (adminPreferences.getId() != null) {
            throw new BadRequestAlertException("A new adminPreferences cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminPreferences result = adminPreferencesRepository.save(adminPreferences);
        return ResponseEntity.created(new URI("/api/admin-preferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-preferences} : Updates an existing adminPreferences.
     *
     * @param adminPreferences the adminPreferences to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminPreferences,
     * or with status {@code 400 (Bad Request)} if the adminPreferences is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminPreferences couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-preferences")
    public ResponseEntity<AdminPreferences> updateAdminPreferences(@RequestBody AdminPreferences adminPreferences) throws URISyntaxException {
        log.debug("REST request to update AdminPreferences : {}", adminPreferences);
        if (adminPreferences.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminPreferences result = adminPreferencesRepository.save(adminPreferences);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminPreferences.getId()))
            .body(result);
    }

    /**
     * {@code GET  /admin-preferences} : get all the adminPreferences.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminPreferences in body.
     */
    @GetMapping("/admin-preferences")
    public ResponseEntity<List<AdminPreferences>> getAllAdminPreferences(Pageable pageable) {
        log.debug("REST request to get a page of AdminPreferences");
        Page<AdminPreferences> page = adminPreferencesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /admin-preferences/:id} : get the "id" adminPreferences.
     *
     * @param id the id of the adminPreferences to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminPreferences, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-preferences/{id}")
    public ResponseEntity<AdminPreferences> getAdminPreferences(@PathVariable String id) {
        log.debug("REST request to get AdminPreferences : {}", id);
        Optional<AdminPreferences> adminPreferences = adminPreferencesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adminPreferences);
    }

    /**
     * {@code DELETE  /admin-preferences/:id} : delete the "id" adminPreferences.
     *
     * @param id the id of the adminPreferences to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-preferences/{id}")
    public ResponseEntity<Void> deleteAdminPreferences(@PathVariable String id) {
        log.debug("REST request to delete AdminPreferences : {}", id);
        adminPreferencesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
