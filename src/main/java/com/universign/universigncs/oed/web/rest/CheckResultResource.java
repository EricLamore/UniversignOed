package com.universign.universigncs.oed.web.rest;

import com.universign.universigncs.oed.domain.CheckResult;
import com.universign.universigncs.oed.repository.CheckResultRepository;
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
 * REST controller for managing {@link com.universign.universigncs.oed.domain.CheckResult}.
 */
@RestController
@RequestMapping("/api")
public class CheckResultResource {

    private final Logger log = LoggerFactory.getLogger(CheckResultResource.class);

    private static final String ENTITY_NAME = "checkResult";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckResultRepository checkResultRepository;

    public CheckResultResource(CheckResultRepository checkResultRepository) {
        this.checkResultRepository = checkResultRepository;
    }

    /**
     * {@code POST  /check-results} : Create a new checkResult.
     *
     * @param checkResult the checkResult to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkResult, or with status {@code 400 (Bad Request)} if the checkResult has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/check-results")
    public ResponseEntity<CheckResult> createCheckResult(@RequestBody CheckResult checkResult) throws URISyntaxException {
        log.debug("REST request to save CheckResult : {}", checkResult);
        if (checkResult.getId() != null) {
            throw new BadRequestAlertException("A new checkResult cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CheckResult result = checkResultRepository.save(checkResult);
        return ResponseEntity.created(new URI("/api/check-results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /check-results} : Updates an existing checkResult.
     *
     * @param checkResult the checkResult to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkResult,
     * or with status {@code 400 (Bad Request)} if the checkResult is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkResult couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/check-results")
    public ResponseEntity<CheckResult> updateCheckResult(@RequestBody CheckResult checkResult) throws URISyntaxException {
        log.debug("REST request to update CheckResult : {}", checkResult);
        if (checkResult.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CheckResult result = checkResultRepository.save(checkResult);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkResult.getId()))
            .body(result);
    }

    /**
     * {@code GET  /check-results} : get all the checkResults.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkResults in body.
     */
    @GetMapping("/check-results")
    public ResponseEntity<List<CheckResult>> getAllCheckResults(Pageable pageable) {
        log.debug("REST request to get a page of CheckResults");
        Page<CheckResult> page = checkResultRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /check-results/:id} : get the "id" checkResult.
     *
     * @param id the id of the checkResult to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkResult, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/check-results/{id}")
    public ResponseEntity<CheckResult> getCheckResult(@PathVariable String id) {
        log.debug("REST request to get CheckResult : {}", id);
        Optional<CheckResult> checkResult = checkResultRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(checkResult);
    }

    /**
     * {@code DELETE  /check-results/:id} : delete the "id" checkResult.
     *
     * @param id the id of the checkResult to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/check-results/{id}")
    public ResponseEntity<Void> deleteCheckResult(@PathVariable String id) {
        log.debug("REST request to delete CheckResult : {}", id);
        checkResultRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
