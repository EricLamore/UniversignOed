package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.AdminPermissions;
import com.universign.universigncs.oed.repository.AdminPermissionsRepository;
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
 * REST controller for managing {@link com.universign.universigncs.oed.domain.AdminPermissions}.
 */
@RestController
@RequestMapping("/api")
public class AdminPermissionsResource {

    private final Logger log = LoggerFactory.getLogger(AdminPermissionsResource.class);

    private static final String ENTITY_NAME = "adminPermissions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdminPermissionsRepository adminPermissionsRepository;

    public AdminPermissionsResource(AdminPermissionsRepository adminPermissionsRepository) {
        this.adminPermissionsRepository = adminPermissionsRepository;
    }

    /**
     * {@code POST  /admin-permissions} : Create a new adminPermissions.
     *
     * @param adminPermissions the adminPermissions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adminPermissions, or with status {@code 400 (Bad Request)} if the adminPermissions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin-permissions")
    public ResponseEntity<AdminPermissions> createAdminPermissions(@RequestBody AdminPermissions adminPermissions) throws URISyntaxException {
        log.debug("REST request to save AdminPermissions : {}", adminPermissions);
        if (adminPermissions.getId() != null) {
            throw new BadRequestAlertException("A new adminPermissions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdminPermissions result = adminPermissionsRepository.save(adminPermissions);
        return ResponseEntity.created(new URI("/api/admin-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /admin-permissions} : Updates an existing adminPermissions.
     *
     * @param adminPermissions the adminPermissions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adminPermissions,
     * or with status {@code 400 (Bad Request)} if the adminPermissions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adminPermissions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin-permissions")
    public ResponseEntity<AdminPermissions> updateAdminPermissions(@RequestBody AdminPermissions adminPermissions) throws URISyntaxException {
        log.debug("REST request to update AdminPermissions : {}", adminPermissions);
        if (adminPermissions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdminPermissions result = adminPermissionsRepository.save(adminPermissions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adminPermissions.getId()))
            .body(result);
    }

    /**
     * {@code GET  /admin-permissions} : get all the adminPermissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adminPermissions in body.
     */
    @GetMapping("/admin-permissions")
    public ResponseEntity<List<AdminPermissions>> getAllAdminPermissions(Pageable pageable) {
        log.debug("REST request to get a page of AdminPermissions");
        Page<AdminPermissions> page = adminPermissionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /admin-permissions/:id} : get the "id" adminPermissions.
     *
     * @param id the id of the adminPermissions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adminPermissions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin-permissions/{id}")
    public ResponseEntity<AdminPermissions> getAdminPermissions(@PathVariable String id) {
        log.debug("REST request to get AdminPermissions : {}", id);
        Optional<AdminPermissions> adminPermissions = adminPermissionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adminPermissions);
    }

    /**
     * {@code DELETE  /admin-permissions/:id} : delete the "id" adminPermissions.
     *
     * @param id the id of the adminPermissions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin-permissions/{id}")
    public ResponseEntity<Void> deleteAdminPermissions(@PathVariable String id) {
        log.debug("REST request to delete AdminPermissions : {}", id);
        adminPermissionsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
