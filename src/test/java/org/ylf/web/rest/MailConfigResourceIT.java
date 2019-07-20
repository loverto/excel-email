package org.ylf.web.rest;

import org.ylf.ExcelEmailApp;
import org.ylf.domain.MailConfig;
import org.ylf.repository.MailConfigRepository;
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
 * Integration tests for the {@Link MailConfigResource} REST controller.
 */
@SpringBootTest(classes = ExcelEmailApp.class)
public class MailConfigResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_SMTP_SERVER = "AAAAAAAAAA";
    private static final String UPDATED_SMTP_SERVER = "BBBBBBBBBB";

    private static final Integer DEFAULT_SMTP_PORT = 1;
    private static final Integer UPDATED_SMTP_PORT = 2;

    private static final String DEFAULT_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_TYPE = "BBBBBBBBBB";

    @Autowired
    private MailConfigRepository mailConfigRepository;

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

    private MockMvc restMailConfigMockMvc;

    private MailConfig mailConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MailConfigResource mailConfigResource = new MailConfigResource(mailConfigRepository);
        this.restMailConfigMockMvc = MockMvcBuilders.standaloneSetup(mailConfigResource)
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
    public static MailConfig createEntity(EntityManager em) {
        MailConfig mailConfig = new MailConfig()
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .smtpServer(DEFAULT_SMTP_SERVER)
            .smtpPort(DEFAULT_SMTP_PORT)
            .typeId(DEFAULT_TYPE_ID)
            .mailType(DEFAULT_MAIL_TYPE);
        return mailConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MailConfig createUpdatedEntity(EntityManager em) {
        MailConfig mailConfig = new MailConfig()
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .smtpServer(UPDATED_SMTP_SERVER)
            .smtpPort(UPDATED_SMTP_PORT)
            .typeId(UPDATED_TYPE_ID)
            .mailType(UPDATED_MAIL_TYPE);
        return mailConfig;
    }

    @BeforeEach
    public void initTest() {
        mailConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createMailConfig() throws Exception {
        int databaseSizeBeforeCreate = mailConfigRepository.findAll().size();

        // Create the MailConfig
        restMailConfigMockMvc.perform(post("/api/mail-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailConfig)))
            .andExpect(status().isCreated());

        // Validate the MailConfig in the database
        List<MailConfig> mailConfigList = mailConfigRepository.findAll();
        assertThat(mailConfigList).hasSize(databaseSizeBeforeCreate + 1);
        MailConfig testMailConfig = mailConfigList.get(mailConfigList.size() - 1);
        assertThat(testMailConfig.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testMailConfig.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testMailConfig.getSmtpServer()).isEqualTo(DEFAULT_SMTP_SERVER);
        assertThat(testMailConfig.getSmtpPort()).isEqualTo(DEFAULT_SMTP_PORT);
        assertThat(testMailConfig.getTypeId()).isEqualTo(DEFAULT_TYPE_ID);
        assertThat(testMailConfig.getMailType()).isEqualTo(DEFAULT_MAIL_TYPE);
    }

    @Test
    @Transactional
    public void createMailConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mailConfigRepository.findAll().size();

        // Create the MailConfig with an existing ID
        mailConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMailConfigMockMvc.perform(post("/api/mail-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailConfig)))
            .andExpect(status().isBadRequest());

        // Validate the MailConfig in the database
        List<MailConfig> mailConfigList = mailConfigRepository.findAll();
        assertThat(mailConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMailConfigs() throws Exception {
        // Initialize the database
        mailConfigRepository.saveAndFlush(mailConfig);

        // Get all the mailConfigList
        restMailConfigMockMvc.perform(get("/api/mail-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mailConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].smtpServer").value(hasItem(DEFAULT_SMTP_SERVER.toString())))
            .andExpect(jsonPath("$.[*].smtpPort").value(hasItem(DEFAULT_SMTP_PORT)))
            .andExpect(jsonPath("$.[*].typeId").value(hasItem(DEFAULT_TYPE_ID.toString())))
            .andExpect(jsonPath("$.[*].mailType").value(hasItem(DEFAULT_MAIL_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getMailConfig() throws Exception {
        // Initialize the database
        mailConfigRepository.saveAndFlush(mailConfig);

        // Get the mailConfig
        restMailConfigMockMvc.perform(get("/api/mail-configs/{id}", mailConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mailConfig.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.smtpServer").value(DEFAULT_SMTP_SERVER.toString()))
            .andExpect(jsonPath("$.smtpPort").value(DEFAULT_SMTP_PORT))
            .andExpect(jsonPath("$.typeId").value(DEFAULT_TYPE_ID.toString()))
            .andExpect(jsonPath("$.mailType").value(DEFAULT_MAIL_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMailConfig() throws Exception {
        // Get the mailConfig
        restMailConfigMockMvc.perform(get("/api/mail-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMailConfig() throws Exception {
        // Initialize the database
        mailConfigRepository.saveAndFlush(mailConfig);

        int databaseSizeBeforeUpdate = mailConfigRepository.findAll().size();

        // Update the mailConfig
        MailConfig updatedMailConfig = mailConfigRepository.findById(mailConfig.getId()).get();
        // Disconnect from session so that the updates on updatedMailConfig are not directly saved in db
        em.detach(updatedMailConfig);
        updatedMailConfig
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .smtpServer(UPDATED_SMTP_SERVER)
            .smtpPort(UPDATED_SMTP_PORT)
            .typeId(UPDATED_TYPE_ID)
            .mailType(UPDATED_MAIL_TYPE);

        restMailConfigMockMvc.perform(put("/api/mail-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMailConfig)))
            .andExpect(status().isOk());

        // Validate the MailConfig in the database
        List<MailConfig> mailConfigList = mailConfigRepository.findAll();
        assertThat(mailConfigList).hasSize(databaseSizeBeforeUpdate);
        MailConfig testMailConfig = mailConfigList.get(mailConfigList.size() - 1);
        assertThat(testMailConfig.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testMailConfig.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testMailConfig.getSmtpServer()).isEqualTo(UPDATED_SMTP_SERVER);
        assertThat(testMailConfig.getSmtpPort()).isEqualTo(UPDATED_SMTP_PORT);
        assertThat(testMailConfig.getTypeId()).isEqualTo(UPDATED_TYPE_ID);
        assertThat(testMailConfig.getMailType()).isEqualTo(UPDATED_MAIL_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingMailConfig() throws Exception {
        int databaseSizeBeforeUpdate = mailConfigRepository.findAll().size();

        // Create the MailConfig

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMailConfigMockMvc.perform(put("/api/mail-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mailConfig)))
            .andExpect(status().isBadRequest());

        // Validate the MailConfig in the database
        List<MailConfig> mailConfigList = mailConfigRepository.findAll();
        assertThat(mailConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMailConfig() throws Exception {
        // Initialize the database
        mailConfigRepository.saveAndFlush(mailConfig);

        int databaseSizeBeforeDelete = mailConfigRepository.findAll().size();

        // Delete the mailConfig
        restMailConfigMockMvc.perform(delete("/api/mail-configs/{id}", mailConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MailConfig> mailConfigList = mailConfigRepository.findAll();
        assertThat(mailConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MailConfig.class);
        MailConfig mailConfig1 = new MailConfig();
        mailConfig1.setId(1L);
        MailConfig mailConfig2 = new MailConfig();
        mailConfig2.setId(mailConfig1.getId());
        assertThat(mailConfig1).isEqualTo(mailConfig2);
        mailConfig2.setId(2L);
        assertThat(mailConfig1).isNotEqualTo(mailConfig2);
        mailConfig1.setId(null);
        assertThat(mailConfig1).isNotEqualTo(mailConfig2);
    }
}
