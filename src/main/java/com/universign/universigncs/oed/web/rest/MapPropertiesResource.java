package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.MapProperties;
import com.universign.universigncs.oed.repository.MapPropertiesRepository;
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
 * REST controller for managing {@link com.universign.universigncs.oed.domain.MapProperties}.
 */
@RestController
@RequestMapping("/api")
public class MapPropertiesResource {

    private final Logger log = LoggerFactory.getLogger(MapPropertiesResource.class);

    private static final String ENTITY_NAME = "mapProperties";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MapPropertiesRepository mapPropertiesRepository;

    public MapPropertiesResource(MapPropertiesRepository mapPropertiesRepository) {
        this.mapPropertiesRepository = mapPropertiesRepository;
    }

    /**
     * {@code POST  /map-properties} : Create a new mapProperties.
     *
     * @param mapProperties the mapProperties to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mapProperties, or with status {@code 400 (Bad Request)} if the mapProperties has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/map-properties")
    public ResponseEntity<MapProperties> createMapProperties(@RequestBody MapProperties mapProperties) throws URISyntaxException {
        log.debug("REST request to save MapProperties : {}", mapProperties);
        if (mapProperties.getId() != null) {
            throw new BadRequestAlertException("A new mapProperties cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MapProperties result = mapPropertiesRepository.save(mapProperties);
        return ResponseEntity.created(new URI("/api/map-properties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /map-properties} : Updates an existing mapProperties.
     *
     * @param mapProperties the mapProperties to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mapProperties,
     * or with status {@code 400 (Bad Request)} if the mapProperties is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mapProperties couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/map-properties")
    public ResponseEntity<MapProperties> updateMapProperties(@RequestBody MapProperties mapProperties) throws URISyntaxException {
        log.debug("REST request to update MapProperties : {}", mapProperties);
        if (mapProperties.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MapProperties result = mapPropertiesRepository.save(mapProperties);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mapProperties.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /map-properties} : get all the mapProperties.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mapProperties in body.
     */
    @GetMapping("/map-properties")
    public ResponseEntity<List<MapProperties>> getAllMapProperties(Pageable pageable) {
        log.debug("REST request to get a page of MapProperties");
        Page<MapProperties> page = mapPropertiesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /map-properties/:id} : get the "id" mapProperties.
     *
     * @param id the id of the mapProperties to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mapProperties, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/map-properties/{id}")
    public ResponseEntity<MapProperties> getMapProperties(@PathVariable String id) {
        log.debug("REST request to get MapProperties : {}", id);
        Optional<MapProperties> mapProperties = mapPropertiesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mapProperties);
    }

    /**
     * {@code DELETE  /map-properties/:id} : delete the "id" mapProperties.
     *
     * @param id the id of the mapProperties to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/map-properties/{id}")
    public ResponseEntity<Void> deleteMapProperties(@PathVariable String id) {
        log.debug("REST request to delete MapProperties : {}", id);
        mapPropertiesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
