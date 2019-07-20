package org.ylf.web.rest;

import org.ylf.domain.SheBao;
import org.ylf.repository.SheBaoRepository;
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
 * REST controller for managing {@link org.ylf.domain.SheBao}.
 */
@RestController
@RequestMapping("/api")
public class SheBaoResource {

    private final Logger log = LoggerFactory.getLogger(SheBaoResource.class);

    private static final String ENTITY_NAME = "sheBao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SheBaoRepository sheBaoRepository;

    public SheBaoResource(SheBaoRepository sheBaoRepository) {
        this.sheBaoRepository = sheBaoRepository;
    }

    /**
     * {@code POST  /she-baos} : Create a new sheBao.
     *
     * @param sheBao the sheBao to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sheBao, or with status {@code 400 (Bad Request)} if the sheBao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/she-baos")
    public ResponseEntity<SheBao> createSheBao(@RequestBody SheBao sheBao) throws URISyntaxException {
        log.debug("REST request to save SheBao : {}", sheBao);
        if (sheBao.getId() != null) {
            throw new BadRequestAlertException("A new sheBao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SheBao result = sheBaoRepository.save(sheBao);
        return ResponseEntity.created(new URI("/api/she-baos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /she-baos} : Updates an existing sheBao.
     *
     * @param sheBao the sheBao to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sheBao,
     * or with status {@code 400 (Bad Request)} if the sheBao is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sheBao couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/she-baos")
    public ResponseEntity<SheBao> updateSheBao(@RequestBody SheBao sheBao) throws URISyntaxException {
        log.debug("REST request to update SheBao : {}", sheBao);
        if (sheBao.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SheBao result = sheBaoRepository.save(sheBao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sheBao.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /she-baos} : get all the sheBaos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sheBaos in body.
     */
    @GetMapping("/she-baos")
    public ResponseEntity<List<SheBao>> getAllSheBaos(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of SheBaos");
        Page<SheBao> page = sheBaoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /she-baos/:id} : get the "id" sheBao.
     *
     * @param id the id of the sheBao to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sheBao, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/she-baos/{id}")
    public ResponseEntity<SheBao> getSheBao(@PathVariable Long id) {
        log.debug("REST request to get SheBao : {}", id);
        Optional<SheBao> sheBao = sheBaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sheBao);
    }

    /**
     * {@code DELETE  /she-baos/:id} : delete the "id" sheBao.
     *
     * @param id the id of the sheBao to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/she-baos/{id}")
    public ResponseEntity<Void> deleteSheBao(@PathVariable Long id) {
        log.debug("REST request to delete SheBao : {}", id);
        sheBaoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
