package org.ylf.web.rest;

import org.ylf.domain.MailContent;
import org.ylf.repository.MailContentRepository;
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
 * REST controller for managing {@link org.ylf.domain.MailContent}.
 */
@RestController
@RequestMapping("/api")
public class MailContentResource {

    private final Logger log = LoggerFactory.getLogger(MailContentResource.class);

    private static final String ENTITY_NAME = "mailContent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MailContentRepository mailContentRepository;

    public MailContentResource(MailContentRepository mailContentRepository) {
        this.mailContentRepository = mailContentRepository;
    }

    /**
     * {@code POST  /mail-contents} : Create a new mailContent.
     *
     * @param mailContent the mailContent to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mailContent, or with status {@code 400 (Bad Request)} if the mailContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mail-contents")
    public ResponseEntity<MailContent> createMailContent(@RequestBody MailContent mailContent) throws URISyntaxException {
        log.debug("REST request to save MailContent : {}", mailContent);
        if (mailContent.getId() != null) {
            throw new BadRequestAlertException("A new mailContent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MailContent result = mailContentRepository.save(mailContent);
        return ResponseEntity.created(new URI("/api/mail-contents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mail-contents} : Updates an existing mailContent.
     *
     * @param mailContent the mailContent to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mailContent,
     * or with status {@code 400 (Bad Request)} if the mailContent is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mailContent couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mail-contents")
    public ResponseEntity<MailContent> updateMailContent(@RequestBody MailContent mailContent) throws URISyntaxException {
        log.debug("REST request to update MailContent : {}", mailContent);
        if (mailContent.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MailContent result = mailContentRepository.save(mailContent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mailContent.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mail-contents} : get all the mailContents.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mailContents in body.
     */
    @GetMapping("/mail-contents")
    public ResponseEntity<List<MailContent>> getAllMailContents(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of MailContents");
        Page<MailContent> page = mailContentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mail-contents/:id} : get the "id" mailContent.
     *
     * @param id the id of the mailContent to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mailContent, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mail-contents/{id}")
    public ResponseEntity<MailContent> getMailContent(@PathVariable Long id) {
        log.debug("REST request to get MailContent : {}", id);
        Optional<MailContent> mailContent = mailContentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mailContent);
    }

    /**
     * {@code DELETE  /mail-contents/:id} : delete the "id" mailContent.
     *
     * @param id the id of the mailContent to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mail-contents/{id}")
    public ResponseEntity<Void> deleteMailContent(@PathVariable Long id) {
        log.debug("REST request to delete MailContent : {}", id);
        mailContentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
