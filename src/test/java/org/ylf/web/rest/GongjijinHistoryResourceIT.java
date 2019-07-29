package org.ylf.web.rest;

import org.ylf.ExcelEmailApp;
import org.ylf.domain.GongjijinHistory;
import org.ylf.repository.GongjijinHistoryRepository;
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
 * Integration tests for the {@Link GongjijinHistoryResource} REST controller.
 */
@SpringBootTest(classes = ExcelEmailApp.class)
public class GongjijinHistoryResourceIT {

    private static final String DEFAULT_XH = "AAAAAAAAAA";
    private static final String UPDATED_XH = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT = "AAAAAAAAAA";
    private static final String UPDATED_DEPT = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GJJJS = "AAAAAAAAAA";
    private static final String UPDATED_GJJJS = "BBBBBBBBBB";

    private static final String DEFAULT_GRKKMX = "AAAAAAAAAA";
    private static final String UPDATED_GRKKMX = "BBBBBBBBBB";

    private static final String DEFAULT_DWKKMX = "AAAAAAAAAA";
    private static final String UPDATED_DWKKMX = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL = "BBBBBBBBBB";

    @Autowired
    private GongjijinHistoryRepository gongjijinHistoryRepository;

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

    private MockMvc restGongjijinHistoryMockMvc;

    private GongjijinHistory gongjijinHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GongjijinHistoryResource gongjijinHistoryResource = new GongjijinHistoryResource(gongjijinHistoryRepository);
        this.restGongjijinHistoryMockMvc = MockMvcBuilders.standaloneSetup(gongjijinHistoryResource)
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
    public static GongjijinHistory createEntity(EntityManager em) {
        GongjijinHistory gongjijinHistory = new GongjijinHistory()
            .xh(DEFAULT_XH)
            .dept(DEFAULT_DEPT)
            .name(DEFAULT_NAME)
            .gjjjs(DEFAULT_GJJJS)
            .grkkmx(DEFAULT_GRKKMX)
            .dwkkmx(DEFAULT_DWKKMX)
            .total(DEFAULT_TOTAL);
        return gongjijinHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GongjijinHistory createUpdatedEntity(EntityManager em) {
        GongjijinHistory gongjijinHistory = new GongjijinHistory()
            .xh(UPDATED_XH)
            .dept(UPDATED_DEPT)
            .name(UPDATED_NAME)
            .gjjjs(UPDATED_GJJJS)
            .grkkmx(UPDATED_GRKKMX)
            .dwkkmx(UPDATED_DWKKMX)
            .total(UPDATED_TOTAL);
        return gongjijinHistory;
    }

    @BeforeEach
    public void initTest() {
        gongjijinHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createGongjijinHistory() throws Exception {
        int databaseSizeBeforeCreate = gongjijinHistoryRepository.findAll().size();

        // Create the GongjijinHistory
        restGongjijinHistoryMockMvc.perform(post("/api/gongjijin-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gongjijinHistory)))
            .andExpect(status().isCreated());

        // Validate the GongjijinHistory in the database
        List<GongjijinHistory> gongjijinHistoryList = gongjijinHistoryRepository.findAll();
        assertThat(gongjijinHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        GongjijinHistory testGongjijinHistory = gongjijinHistoryList.get(gongjijinHistoryList.size() - 1);
        assertThat(testGongjijinHistory.getXh()).isEqualTo(DEFAULT_XH);
        assertThat(testGongjijinHistory.getDept()).isEqualTo(DEFAULT_DEPT);
        assertThat(testGongjijinHistory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGongjijinHistory.getGjjjs()).isEqualTo(DEFAULT_GJJJS);
        assertThat(testGongjijinHistory.getGrkkmx()).isEqualTo(DEFAULT_GRKKMX);
        assertThat(testGongjijinHistory.getDwkkmx()).isEqualTo(DEFAULT_DWKKMX);
        assertThat(testGongjijinHistory.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createGongjijinHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gongjijinHistoryRepository.findAll().size();

        // Create the GongjijinHistory with an existing ID
        gongjijinHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGongjijinHistoryMockMvc.perform(post("/api/gongjijin-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gongjijinHistory)))
            .andExpect(status().isBadRequest());

        // Validate the GongjijinHistory in the database
        List<GongjijinHistory> gongjijinHistoryList = gongjijinHistoryRepository.findAll();
        assertThat(gongjijinHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGongjijinHistories() throws Exception {
        // Initialize the database
        gongjijinHistoryRepository.saveAndFlush(gongjijinHistory);

        // Get all the gongjijinHistoryList
        restGongjijinHistoryMockMvc.perform(get("/api/gongjijin-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gongjijinHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].xh").value(hasItem(DEFAULT_XH.toString())))
            .andExpect(jsonPath("$.[*].dept").value(hasItem(DEFAULT_DEPT.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].gjjjs").value(hasItem(DEFAULT_GJJJS.toString())))
            .andExpect(jsonPath("$.[*].grkkmx").value(hasItem(DEFAULT_GRKKMX.toString())))
            .andExpect(jsonPath("$.[*].dwkkmx").value(hasItem(DEFAULT_DWKKMX.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.toString())));
    }
    
    @Test
    @Transactional
    public void getGongjijinHistory() throws Exception {
        // Initialize the database
        gongjijinHistoryRepository.saveAndFlush(gongjijinHistory);

        // Get the gongjijinHistory
        restGongjijinHistoryMockMvc.perform(get("/api/gongjijin-histories/{id}", gongjijinHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gongjijinHistory.getId().intValue()))
            .andExpect(jsonPath("$.xh").value(DEFAULT_XH.toString()))
            .andExpect(jsonPath("$.dept").value(DEFAULT_DEPT.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.gjjjs").value(DEFAULT_GJJJS.toString()))
            .andExpect(jsonPath("$.grkkmx").value(DEFAULT_GRKKMX.toString()))
            .andExpect(jsonPath("$.dwkkmx").value(DEFAULT_DWKKMX.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGongjijinHistory() throws Exception {
        // Get the gongjijinHistory
        restGongjijinHistoryMockMvc.perform(get("/api/gongjijin-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGongjijinHistory() throws Exception {
        // Initialize the database
        gongjijinHistoryRepository.saveAndFlush(gongjijinHistory);

        int databaseSizeBeforeUpdate = gongjijinHistoryRepository.findAll().size();

        // Update the gongjijinHistory
        GongjijinHistory updatedGongjijinHistory = gongjijinHistoryRepository.findById(gongjijinHistory.getId()).get();
        // Disconnect from session so that the updates on updatedGongjijinHistory are not directly saved in db
        em.detach(updatedGongjijinHistory);
        updatedGongjijinHistory
            .xh(UPDATED_XH)
            .dept(UPDATED_DEPT)
            .name(UPDATED_NAME)
            .gjjjs(UPDATED_GJJJS)
            .grkkmx(UPDATED_GRKKMX)
            .dwkkmx(UPDATED_DWKKMX)
            .total(UPDATED_TOTAL);

        restGongjijinHistoryMockMvc.perform(put("/api/gongjijin-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGongjijinHistory)))
            .andExpect(status().isOk());

        // Validate the GongjijinHistory in the database
        List<GongjijinHistory> gongjijinHistoryList = gongjijinHistoryRepository.findAll();
        assertThat(gongjijinHistoryList).hasSize(databaseSizeBeforeUpdate);
        GongjijinHistory testGongjijinHistory = gongjijinHistoryList.get(gongjijinHistoryList.size() - 1);
        assertThat(testGongjijinHistory.getXh()).isEqualTo(UPDATED_XH);
        assertThat(testGongjijinHistory.getDept()).isEqualTo(UPDATED_DEPT);
        assertThat(testGongjijinHistory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGongjijinHistory.getGjjjs()).isEqualTo(UPDATED_GJJJS);
        assertThat(testGongjijinHistory.getGrkkmx()).isEqualTo(UPDATED_GRKKMX);
        assertThat(testGongjijinHistory.getDwkkmx()).isEqualTo(UPDATED_DWKKMX);
        assertThat(testGongjijinHistory.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingGongjijinHistory() throws Exception {
        int databaseSizeBeforeUpdate = gongjijinHistoryRepository.findAll().size();

        // Create the GongjijinHistory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGongjijinHistoryMockMvc.perform(put("/api/gongjijin-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gongjijinHistory)))
            .andExpect(status().isBadRequest());

        // Validate the GongjijinHistory in the database
        List<GongjijinHistory> gongjijinHistoryList = gongjijinHistoryRepository.findAll();
        assertThat(gongjijinHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGongjijinHistory() throws Exception {
        // Initialize the database
        gongjijinHistoryRepository.saveAndFlush(gongjijinHistory);

        int databaseSizeBeforeDelete = gongjijinHistoryRepository.findAll().size();

        // Delete the gongjijinHistory
        restGongjijinHistoryMockMvc.perform(delete("/api/gongjijin-histories/{id}", gongjijinHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GongjijinHistory> gongjijinHistoryList = gongjijinHistoryRepository.findAll();
        assertThat(gongjijinHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GongjijinHistory.class);
        GongjijinHistory gongjijinHistory1 = new GongjijinHistory();
        gongjijinHistory1.setId(1L);
        GongjijinHistory gongjijinHistory2 = new GongjijinHistory();
        gongjijinHistory2.setId(gongjijinHistory1.getId());
        assertThat(gongjijinHistory1).isEqualTo(gongjijinHistory2);
        gongjijinHistory2.setId(2L);
        assertThat(gongjijinHistory1).isNotEqualTo(gongjijinHistory2);
        gongjijinHistory1.setId(null);
        assertThat(gongjijinHistory1).isNotEqualTo(gongjijinHistory2);
    }
}
