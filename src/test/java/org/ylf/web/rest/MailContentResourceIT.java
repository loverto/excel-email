package org.ylf.web.rest;

import org.ylf.ExcelEmailApp;
import org.ylf.domain.MailContent;
import org.ylf.repository.MailContentRepository;
import org.ylf.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static org.ylf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MailContentResource} REST controller.
 */
@SpringBootTest(classes = ExcelEmailApp.class)
public class MailContentResourceIT {

    private static final String DEFAULT_MAIL_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_CONTENT = "BBBBBBBBBB";

    @Autowired
    private MailContentRepository mailContentRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMailContentMockMvc;

    private MailContent mailContent;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MailContentResource mailContentResource = new MailContentResource(mailContentRepository);
        this.restMailContentMockMvc = MockMvcBuilders.standaloneSetup(mailContentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MailContent createEntity(EntityManager em) {
        MailContent mailContent = new MailContent()
            .mailSubject(DEFAULT_MAIL_SUBJECT)
            .mailContent(DEFAULT_MAIL_CONTENT);
        return mailContent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MailContent createUpdatedEntity(EntityManager em) {
        MailContent mailContent = new MailContent()
            .mailSubject(UPDATED_MAIL_SUBJECT)
            .mailContent(UPDATED_MAIL_CONTENT);
        return mailContent;
    }

    @BeforeEach
    public void initTest() {
        mailContent = createEntity(em);
    }

    @Test
    @Transactional
    public void createMailContent() throws Exception {
        int databaseSizeBeforeCreate = mailContentRepository.findAll().size();

        // Create the MailContent
        restMailContentMockMvc.perform(post("/api/mail-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailContent)))
            .andExpect(status().isCreated());

        // Validate the MailContent in the database
        List<MailContent> mailContentList = mailContentRepository.findAll();
        assertThat(mailContentList).hasSize(databaseSizeBeforeCreate + 1);
        MailContent testMailContent = mailContentList.get(mailContentList.size() - 1);
        assertThat(testMailContent.getMailSubject()).isEqualTo(DEFAULT_MAIL_SUBJECT);
        assertThat(testMailContent.getMailContent()).isEqualTo(DEFAULT_MAIL_CONTENT);
    }

    @Test
    @Transactional
    public void createMailContentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mailContentRepository.findAll().size();

        // Create the MailContent with an existing ID
        mailContent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMailContentMockMvc.perform(post("/api/mail-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailContent)))
            .andExpect(status().isBadRequest());

        // Validate the MailContent in the database
        List<MailContent> mailContentList = mailContentRepository.findAll();
        assertThat(mailContentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMailContents() throws Exception {
        // Initialize the database
        mailContentRepository.saveAndFlush(mailContent);

        // Get all the mailContentList
        restMailContentMockMvc.perform(get("/api/mail-contents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mailContent.getId().intValue())))
            .andExpect(jsonPath("$.[*].mailSubject").value(hasItem(DEFAULT_MAIL_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].mailContent").value(hasItem(DEFAULT_MAIL_CONTENT.toString())));
    }
    
    @Test
    @Transactional
    public void getMailContent() throws Exception {
        // Initialize the database
        mailContentRepository.saveAndFlush(mailContent);

        // Get the mailContent
        restMailContentMockMvc.perform(get("/api/mail-contents/{id}", mailContent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mailContent.getId().intValue()))
            .andExpect(jsonPath("$.mailSubject").value(DEFAULT_MAIL_SUBJECT.toString()))
            .andExpect(jsonPath("$.mailContent").value(DEFAULT_MAIL_CONTENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMailContent() throws Exception {
        // Get the mailContent
        restMailContentMockMvc.perform(get("/api/mail-contents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMailContent() throws Exception {
        // Initialize the database
        mailContentRepository.saveAndFlush(mailContent);

        int databaseSizeBeforeUpdate = mailContentRepository.findAll().size();

        // Update the mailContent
        MailContent updatedMailContent = mailContentRepository.findById(mailContent.getId()).get();
        // Disconnect from session so that the updates on updatedMailContent are not directly saved in db
        em.detach(updatedMailContent);
        updatedMailContent
            .mailSubject(UPDATED_MAIL_SUBJECT)
            .mailContent(UPDATED_MAIL_CONTENT);

        restMailContentMockMvc.perform(put("/api/mail-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMailContent)))
            .andExpect(status().isOk());

        // Validate the MailContent in the database
        List<MailContent> mailContentList = mailContentRepository.findAll();
        assertThat(mailContentList).hasSize(databaseSizeBeforeUpdate);
        MailContent testMailContent = mailContentList.get(mailContentList.size() - 1);
        assertThat(testMailContent.getMailSubject()).isEqualTo(UPDATED_MAIL_SUBJECT);
        assertThat(testMailContent.getMailContent()).isEqualTo(UPDATED_MAIL_CONTENT);
    }

    @Test
    @Transactional
    public void updateNonExistingMailContent() throws Exception {
        int databaseSizeBeforeUpdate = mailContentRepository.findAll().size();

        // Create the MailContent

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMailContentMockMvc.perform(put("/api/mail-contents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailContent)))
            .andExpect(status().isBadRequest());

        // Validate the MailContent in the database
        List<MailContent> mailContentList = mailContentRepository.findAll();
        assertThat(mailContentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMailContent() throws Exception {
        // Initialize the database
        mailContentRepository.saveAndFlush(mailContent);

        int databaseSizeBeforeDelete = mailContentRepository.findAll().size();

        // Delete the mailContent
        restMailContentMockMvc.perform(delete("/api/mail-contents/{id}", mailContent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MailContent> mailContentList = mailContentRepository.findAll();
        assertThat(mailContentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MailContent.class);
        MailContent mailContent1 = new MailContent();
        mailContent1.setId(1L);
        MailContent mailContent2 = new MailContent();
        mailContent2.setId(mailContent1.getId());
        assertThat(mailContent1).isEqualTo(mailContent2);
        mailContent2.setId(2L);
        assertThat(mailContent1).isNotEqualTo(mailContent2);
        mailContent1.setId(null);
        assertThat(mailContent1).isNotEqualTo(mailContent2);
    }
}
