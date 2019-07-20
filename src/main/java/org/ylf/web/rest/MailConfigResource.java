package org.ylf.web.rest;

import org.ylf.domain.MailConfig;
import org.ylf.repository.MailConfigRepository;
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
 * REST controller for managing {@link org.ylf.domain.MailConfig}.
 */
@RestController
@RequestMapping("/api")
public class MailConfigResource {

    private final Logger log = LoggerFactory.getLogger(MailConfigResource.class);

    private static final String ENTITY_NAME = "mailConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MailConfigRepository mailConfigRepository;

    public MailConfigResource(MailConfigRepository mailConfigRepository) {
        this.mailConfigRepository = mailConfigRepository;
    }

    /**
     * {@code POST  /mail-configs} : Create a new mailConfig.
     *
     * @param mailConfig the mailConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mailConfig, or with status {@code 400 (Bad Request)} if the mailConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mail-configs")
    public ResponseEntity<MailConfig> createMailConfig(@RequestBody MailConfig mailConfig) throws URISyntaxException {
        log.debug("REST request to save MailConfig : {}", mailConfig);
        if (mailConfig.getId() != null) {
            throw new BadRequestAlertException("A new mailConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MailConfig result = mailConfigRepository.save(mailConfig);
        return ResponseEntity.created(new URI("/api/mail-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mail-configs} : Updates an existing mailConfig.
     *
     * @param mailConfig the mailConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mailConfig,
     * or with status {@code 400 (Bad Request)} if the mailConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mailConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mail-configs")
    public ResponseEntity<MailConfig> updateMailConfig(@RequestBody MailConfig mailConfig) throws URISyntaxException {
        log.debug("REST request to update MailConfig : {}", mailConfig);
        if (mailConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MailConfig result = mailConfigRepository.save(mailConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mailConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mail-configs} : get all the mailConfigs.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mailConfigs in body.
     */
    @GetMapping("/mail-configs")
    public ResponseEntity<List<MailConfig>> getAllMailConfigs(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of MailConfigs");
        Page<MailConfig> page = mailConfigRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mail-configs/:id} : get the "id" mailConfig.
     *
     * @param id the id of the mailConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mailConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mail-configs/{id}")
    public ResponseEntity<MailConfig> getMailConfig(@PathVariable Long id) {
        log.debug("REST request to get MailConfig : {}", id);
        Optional<MailConfig> mailConfig = mailConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mailConfig);
    }

    /**
     * {@code DELETE  /mail-configs/:id} : delete the "id" mailConfig.
     *
     * @param id the id of the mailConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mail-configs/{id}")
    public ResponseEntity<Void> deleteMailConfig(@PathVariable Long id) {
        log.debug("REST request to delete MailConfig : {}", id);
        mailConfigRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
