package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.ErrorRequest;
import com.universign.universigncs.oed.repository.ErrorRequestRepository;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.universign.universigncs.oed.domain.ErrorRequest}.
 */
@RestController
@RequestMapping("/api")
public class ErrorRequestResource {

    private final Logger log = LoggerFactory.getLogger(ErrorRequestResource.class);

    private static final String ENTITY_NAME = "errorRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ErrorRequestRepository errorRequestRepository;

    public ErrorRequestResource(ErrorRequestRepository errorRequestRepository) {
        this.errorRequestRepository = errorRequestRepository;
    }

    /**
     * {@code POST  /error-requests} : Create a new errorRequest.
     *
     * @param errorRequest the errorRequest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new errorRequest, or with status {@code 400 (Bad Request)} if the errorRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/error-requests")
    public ResponseEntity<ErrorRequest> createErrorRequest(@Valid @RequestBody ErrorRequest errorRequest) throws URISyntaxException {
        log.debug("REST request to save ErrorRequest : {}", errorRequest);
        if (errorRequest.getId() != null) {
            throw new BadRequestAlertException("A new errorRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ErrorRequest result = errorRequestRepository.save(errorRequest);
        return ResponseEntity.created(new URI("/api/error-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /error-requests} : Updates an existing errorRequest.
     *
     * @param errorRequest the errorRequest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated errorRequest,
     * or with status {@code 400 (Bad Request)} if the errorRequest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the errorRequest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/error-requests")
    public ResponseEntity<ErrorRequest> updateErrorRequest(@Valid @RequestBody ErrorRequest errorRequest) throws URISyntaxException {
        log.debug("REST request to update ErrorRequest : {}", errorRequest);
        if (errorRequest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ErrorRequest result = errorRequestRepository.save(errorRequest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, errorRequest.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /error-requests} : get all the errorRequests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of errorRequests in body.
     */
    @GetMapping("/error-requests")
    public ResponseEntity<List<ErrorRequest>> getAllErrorRequests(Pageable pageable) {
        log.debug("REST request to get a page of ErrorRequests");
        Page<ErrorRequest> page = errorRequestRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /error-requests/:id} : get the "id" errorRequest.
     *
     * @param id the id of the errorRequest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the errorRequest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/error-requests/{id}")
    public ResponseEntity<ErrorRequest> getErrorRequest(@PathVariable String id) {
        log.debug("REST request to get ErrorRequest : {}", id);
        Optional<ErrorRequest> errorRequest = errorRequestRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(errorRequest);
    }

    /**
     * {@code DELETE  /error-requests/:id} : delete the "id" errorRequest.
     *
     * @param id the id of the errorRequest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/error-requests/{id}")
    public ResponseEntity<Void> deleteErrorRequest(@PathVariable String id) {
        log.debug("REST request to delete ErrorRequest : {}", id);
        errorRequestRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
