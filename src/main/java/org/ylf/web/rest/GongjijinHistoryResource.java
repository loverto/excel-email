package org.ylf.web.rest;

import org.ylf.domain.GongjijinHistory;
import org.ylf.repository.GongjijinHistoryRepository;
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
 * REST controller for managing {@link org.ylf.domain.GongjijinHistory}.
 */
@RestController
@RequestMapping("/api")
public class GongjijinHistoryResource {

    private final Logger log = LoggerFactory.getLogger(GongjijinHistoryResource.class);

    private static final String ENTITY_NAME = "gongjijinHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GongjijinHistoryRepository gongjijinHistoryRepository;

    public GongjijinHistoryResource(GongjijinHistoryRepository gongjijinHistoryRepository) {
        this.gongjijinHistoryRepository = gongjijinHistoryRepository;
    }

    /**
     * {@code POST  /gongjijin-histories} : Create a new gongjijinHistory.
     *
     * @param gongjijinHistory the gongjijinHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gongjijinHistory, or with status {@code 400 (Bad Request)} if the gongjijinHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gongjijin-histories")
    public ResponseEntity<GongjijinHistory> createGongjijinHistory(@RequestBody GongjijinHistory gongjijinHistory) throws URISyntaxException {
        log.debug("REST request to save GongjijinHistory : {}", gongjijinHistory);
        if (gongjijinHistory.getId() != null) {
            throw new BadRequestAlertException("A new gongjijinHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GongjijinHistory result = gongjijinHistoryRepository.save(gongjijinHistory);
        return ResponseEntity.created(new URI("/api/gongjijin-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gongjijin-histories} : Updates an existing gongjijinHistory.
     *
     * @param gongjijinHistory the gongjijinHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gongjijinHistory,
     * or with status {@code 400 (Bad Request)} if the gongjijinHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gongjijinHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gongjijin-histories")
    public ResponseEntity<GongjijinHistory> updateGongjijinHistory(@RequestBody GongjijinHistory gongjijinHistory) throws URISyntaxException {
        log.debug("REST request to update GongjijinHistory : {}", gongjijinHistory);
        if (gongjijinHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GongjijinHistory result = gongjijinHistoryRepository.save(gongjijinHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gongjijinHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gongjijin-histories} : get all the gongjijinHistories.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gongjijinHistories in body.
     */
    @GetMapping("/gongjijin-histories")
    public ResponseEntity<List<GongjijinHistory>> getAllGongjijinHistories(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of GongjijinHistories");
        Page<GongjijinHistory> page = gongjijinHistoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gongjijin-histories/:id} : get the "id" gongjijinHistory.
     *
     * @param id the id of the gongjijinHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gongjijinHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gongjijin-histories/{id}")
    public ResponseEntity<GongjijinHistory> getGongjijinHistory(@PathVariable Long id) {
        log.debug("REST request to get GongjijinHistory : {}", id);
        Optional<GongjijinHistory> gongjijinHistory = gongjijinHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gongjijinHistory);
    }

    /**
     * {@code DELETE  /gongjijin-histories/:id} : delete the "id" gongjijinHistory.
     *
     * @param id the id of the gongjijinHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gongjijin-histories/{id}")
    public ResponseEntity<Void> deleteGongjijinHistory(@PathVariable Long id) {
        log.debug("REST request to delete GongjijinHistory : {}", id);
        gongjijinHistoryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
