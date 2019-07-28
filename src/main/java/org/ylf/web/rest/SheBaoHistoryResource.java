package org.ylf.web.rest;

import org.ylf.domain.SheBaoHistory;
import org.ylf.repository.SheBaoHistoryRepository;
import org.ylf.web.rest.errors.BadRequestAlertException;

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
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.ylf.domain.SheBaoHistory}.
 */
@RestController
@RequestMapping("/api")
public class SheBaoHistoryResource {

    private final Logger log = LoggerFactory.getLogger(SheBaoHistoryResource.class);

    private static final String ENTITY_NAME = "sheBaoHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SheBaoHistoryRepository sheBaoHistoryRepository;

    public SheBaoHistoryResource(SheBaoHistoryRepository sheBaoHistoryRepository) {
        this.sheBaoHistoryRepository = sheBaoHistoryRepository;
    }

    /**
     * {@code POST  /she-bao-histories} : Create a new sheBaoHistory.
     *
     * @param sheBaoHistory the sheBaoHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sheBaoHistory, or with status {@code 400 (Bad Request)} if the sheBaoHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/she-bao-histories")
    public ResponseEntity<SheBaoHistory> createSheBaoHistory(@RequestBody SheBaoHistory sheBaoHistory) throws URISyntaxException {
        log.debug("REST request to save SheBaoHistory : {}", sheBaoHistory);
        if (sheBaoHistory.getId() != null) {
            throw new BadRequestAlertException("A new sheBaoHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SheBaoHistory result = sheBaoHistoryRepository.save(sheBaoHistory);
        return ResponseEntity.created(new URI("/api/she-bao-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /she-bao-histories} : Updates an existing sheBaoHistory.
     *
     * @param sheBaoHistory the sheBaoHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sheBaoHistory,
     * or with status {@code 400 (Bad Request)} if the sheBaoHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sheBaoHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/she-bao-histories")
    public ResponseEntity<SheBaoHistory> updateSheBaoHistory(@RequestBody SheBaoHistory sheBaoHistory) throws URISyntaxException {
        log.debug("REST request to update SheBaoHistory : {}", sheBaoHistory);
        if (sheBaoHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SheBaoHistory result = sheBaoHistoryRepository.save(sheBaoHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sheBaoHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /she-bao-histories} : get all the sheBaoHistories.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sheBaoHistories in body.
     */
    @GetMapping("/she-bao-histories")
    public ResponseEntity<List<SheBaoHistory>> getAllSheBaoHistories(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of SheBaoHistories");
        Page<SheBaoHistory> page = sheBaoHistoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /she-bao-histories/:id} : get the "id" sheBaoHistory.
     *
     * @param id the id of the sheBaoHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sheBaoHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/she-bao-histories/{id}")
    public ResponseEntity<SheBaoHistory> getSheBaoHistory(@PathVariable Long id) {
        log.debug("REST request to get SheBaoHistory : {}", id);
        Optional<SheBaoHistory> sheBaoHistory = sheBaoHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sheBaoHistory);
    }

    /**
     * {@code DELETE  /she-bao-histories/:id} : delete the "id" sheBaoHistory.
     *
     * @param id the id of the sheBaoHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/she-bao-histories/{id}")
    public ResponseEntity<Void> deleteSheBaoHistory(@PathVariable Long id) {
        log.debug("REST request to delete SheBaoHistory : {}", id);
        sheBaoHistoryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
