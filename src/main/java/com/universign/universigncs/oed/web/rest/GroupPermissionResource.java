package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.GroupPermission;
import com.universign.universigncs.oed.repository.GroupPermissionRepository;
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
 * REST controller for managing {@link com.universign.universigncs.oed.domain.GroupPermission}.
 */
@RestController
@RequestMapping("/api")
public class GroupPermissionResource {

    private final Logger log = LoggerFactory.getLogger(GroupPermissionResource.class);

    private static final String ENTITY_NAME = "groupPermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupPermissionRepository groupPermissionRepository;

    public GroupPermissionResource(GroupPermissionRepository groupPermissionRepository) {
        this.groupPermissionRepository = groupPermissionRepository;
    }

    /**
     * {@code POST  /group-permissions} : Create a new groupPermission.
     *
     * @param groupPermission the groupPermission to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupPermission, or with status {@code 400 (Bad Request)} if the groupPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-permissions")
    public ResponseEntity<GroupPermission> createGroupPermission(@RequestBody GroupPermission groupPermission) throws URISyntaxException {
        log.debug("REST request to save GroupPermission : {}", groupPermission);
        if (groupPermission.getId() != null) {
            throw new BadRequestAlertException("A new groupPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupPermission result = groupPermissionRepository.save(groupPermission);
        return ResponseEntity.created(new URI("/api/group-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-permissions} : Updates an existing groupPermission.
     *
     * @param groupPermission the groupPermission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupPermission,
     * or with status {@code 400 (Bad Request)} if the groupPermission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupPermission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-permissions")
    public ResponseEntity<GroupPermission> updateGroupPermission(@RequestBody GroupPermission groupPermission) throws URISyntaxException {
        log.debug("REST request to update GroupPermission : {}", groupPermission);
        if (groupPermission.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupPermission result = groupPermissionRepository.save(groupPermission);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupPermission.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /group-permissions} : get all the groupPermissions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupPermissions in body.
     */
    @GetMapping("/group-permissions")
    public ResponseEntity<List<GroupPermission>> getAllGroupPermissions(Pageable pageable) {
        log.debug("REST request to get a page of GroupPermissions");
        Page<GroupPermission> page = groupPermissionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-permissions/:id} : get the "id" groupPermission.
     *
     * @param id the id of the groupPermission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupPermission, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-permissions/{id}")
    public ResponseEntity<GroupPermission> getGroupPermission(@PathVariable String id) {
        log.debug("REST request to get GroupPermission : {}", id);
        Optional<GroupPermission> groupPermission = groupPermissionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(groupPermission);
    }

    /**
     * {@code DELETE  /group-permissions/:id} : delete the "id" groupPermission.
     *
     * @param id the id of the groupPermission to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-permissions/{id}")
    public ResponseEntity<Void> deleteGroupPermission(@PathVariable String id) {
        log.debug("REST request to delete GroupPermission : {}", id);
        groupPermissionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
