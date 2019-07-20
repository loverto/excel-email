package org.ylf.web.rest;

import org.ylf.domain.Gongjijin;
import org.ylf.repository.GongjijinRepository;
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
 * REST controller for managing {@link org.ylf.domain.Gongjijin}.
 */
@RestController
@RequestMapping("/api")
public class GongjijinResource {

    private final Logger log = LoggerFactory.getLogger(GongjijinResource.class);

    private static final String ENTITY_NAME = "gongjijin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GongjijinRepository gongjijinRepository;

    public GongjijinResource(GongjijinRepository gongjijinRepository) {
        this.gongjijinRepository = gongjijinRepository;
    }

    /**
     * {@code POST  /gongjijins} : Create a new gongjijin.
     *
     * @param gongjijin the gongjijin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gongjijin, or with status {@code 400 (Bad Request)} if the gongjijin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gongjijins")
    public ResponseEntity<Gongjijin> createGongjijin(@RequestBody Gongjijin gongjijin) throws URISyntaxException {
        log.debug("REST request to save Gongjijin : {}", gongjijin);
        if (gongjijin.getId() != null) {
            throw new BadRequestAlertException("A new gongjijin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Gongjijin result = gongjijinRepository.save(gongjijin);
        return ResponseEntity.created(new URI("/api/gongjijins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gongjijins} : Updates an existing gongjijin.
     *
     * @param gongjijin the gongjijin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gongjijin,
     * or with status {@code 400 (Bad Request)} if the gongjijin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gongjijin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gongjijins")
    public ResponseEntity<Gongjijin> updateGongjijin(@RequestBody Gongjijin gongjijin) throws URISyntaxException {
        log.debug("REST request to update Gongjijin : {}", gongjijin);
        if (gongjijin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Gongjijin result = gongjijinRepository.save(gongjijin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gongjijin.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gongjijins} : get all the gongjijins.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gongjijins in body.
     */
    @GetMapping("/gongjijins")
    public ResponseEntity<List<Gongjijin>> getAllGongjijins(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Gongjijins");
        Page<Gongjijin> page = gongjijinRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gongjijins/:id} : get the "id" gongjijin.
     *
     * @param id the id of the gongjijin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gongjijin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gongjijins/{id}")
    public ResponseEntity<Gongjijin> getGongjijin(@PathVariable Long id) {
        log.debug("REST request to get Gongjijin : {}", id);
        Optional<Gongjijin> gongjijin = gongjijinRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gongjijin);
    }

    /**
     * {@code DELETE  /gongjijins/:id} : delete the "id" gongjijin.
     *
     * @param id the id of the gongjijin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gongjijins/{id}")
    public ResponseEntity<Void> deleteGongjijin(@PathVariable Long id) {
        log.debug("REST request to delete Gongjijin : {}", id);
        gongjijinRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
