package org.ylf.web.rest;

import org.ylf.ExcelEmailApp;
import org.ylf.domain.Gongjijin;
import org.ylf.repository.GongjijinRepository;
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
 * Integration tests for the {@Link GongjijinResource} REST controller.
 */
@SpringBootTest(classes = ExcelEmailApp.class)
public class GongjijinResourceIT {

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
    private GongjijinRepository gongjijinRepository;

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

    private MockMvc restGongjijinMockMvc;

    private Gongjijin gongjijin;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GongjijinResource gongjijinResource = new GongjijinResource(gongjijinRepository);
        this.restGongjijinMockMvc = MockMvcBuilders.standaloneSetup(gongjijinResource)
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
    public static Gongjijin createEntity(EntityManager em) {
        Gongjijin gongjijin = new Gongjijin()
            .xh(DEFAULT_XH)
            .dept(DEFAULT_DEPT)
            .name(DEFAULT_NAME)
            .gjjjs(DEFAULT_GJJJS)
            .grkkmx(DEFAULT_GRKKMX)
            .dwkkmx(DEFAULT_DWKKMX)
            .total(DEFAULT_TOTAL);
        return gongjijin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gongjijin createUpdatedEntity(EntityManager em) {
        Gongjijin gongjijin = new Gongjijin()
            .xh(UPDATED_XH)
            .dept(UPDATED_DEPT)
            .name(UPDATED_NAME)
            .gjjjs(UPDATED_GJJJS)
            .grkkmx(UPDATED_GRKKMX)
            .dwkkmx(UPDATED_DWKKMX)
            .total(UPDATED_TOTAL);
        return gongjijin;
    }

    @BeforeEach
    public void initTest() {
        gongjijin = createEntity(em);
    }

    @Test
    @Transactional
    public void createGongjijin() throws Exception {
        int databaseSizeBeforeCreate = gongjijinRepository.findAll().size();

        // Create the Gongjijin
        restGongjijinMockMvc.perform(post("/api/gongjijins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gongjijin)))
            .andExpect(status().isCreated());

        // Validate the Gongjijin in the database
        List<Gongjijin> gongjijinList = gongjijinRepository.findAll();
        assertThat(gongjijinList).hasSize(databaseSizeBeforeCreate + 1);
        Gongjijin testGongjijin = gongjijinList.get(gongjijinList.size() - 1);
        assertThat(testGongjijin.getXh()).isEqualTo(DEFAULT_XH);
        assertThat(testGongjijin.getDept()).isEqualTo(DEFAULT_DEPT);
        assertThat(testGongjijin.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGongjijin.getGjjjs()).isEqualTo(DEFAULT_GJJJS);
        assertThat(testGongjijin.getGrkkmx()).isEqualTo(DEFAULT_GRKKMX);
        assertThat(testGongjijin.getDwkkmx()).isEqualTo(DEFAULT_DWKKMX);
        assertThat(testGongjijin.getTotal()).isEqualTo(DEFAULT_TOTAL);
    }

    @Test
    @Transactional
    public void createGongjijinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gongjijinRepository.findAll().size();

        // Create the Gongjijin with an existing ID
        gongjijin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGongjijinMockMvc.perform(post("/api/gongjijins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gongjijin)))
            .andExpect(status().isBadRequest());

        // Validate the Gongjijin in the database
        List<Gongjijin> gongjijinList = gongjijinRepository.findAll();
        assertThat(gongjijinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGongjijins() throws Exception {
        // Initialize the database
        gongjijinRepository.saveAndFlush(gongjijin);

        // Get all the gongjijinList
        restGongjijinMockMvc.perform(get("/api/gongjijins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gongjijin.getId().intValue())))
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
    public void getGongjijin() throws Exception {
        // Initialize the database
        gongjijinRepository.saveAndFlush(gongjijin);

        // Get the gongjijin
        restGongjijinMockMvc.perform(get("/api/gongjijins/{id}", gongjijin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(gongjijin.getId().intValue()))
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
    public void getNonExistingGongjijin() throws Exception {
        // Get the gongjijin
        restGongjijinMockMvc.perform(get("/api/gongjijins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGongjijin() throws Exception {
        // Initialize the database
        gongjijinRepository.saveAndFlush(gongjijin);

        int databaseSizeBeforeUpdate = gongjijinRepository.findAll().size();

        // Update the gongjijin
        Gongjijin updatedGongjijin = gongjijinRepository.findById(gongjijin.getId()).get();
        // Disconnect from session so that the updates on updatedGongjijin are not directly saved in db
        em.detach(updatedGongjijin);
        updatedGongjijin
            .xh(UPDATED_XH)
            .dept(UPDATED_DEPT)
            .name(UPDATED_NAME)
            .gjjjs(UPDATED_GJJJS)
            .grkkmx(UPDATED_GRKKMX)
            .dwkkmx(UPDATED_DWKKMX)
            .total(UPDATED_TOTAL);

        restGongjijinMockMvc.perform(put("/api/gongjijins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGongjijin)))
            .andExpect(status().isOk());

        // Validate the Gongjijin in the database
        List<Gongjijin> gongjijinList = gongjijinRepository.findAll();
        assertThat(gongjijinList).hasSize(databaseSizeBeforeUpdate);
        Gongjijin testGongjijin = gongjijinList.get(gongjijinList.size() - 1);
        assertThat(testGongjijin.getXh()).isEqualTo(UPDATED_XH);
        assertThat(testGongjijin.getDept()).isEqualTo(UPDATED_DEPT);
        assertThat(testGongjijin.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGongjijin.getGjjjs()).isEqualTo(UPDATED_GJJJS);
        assertThat(testGongjijin.getGrkkmx()).isEqualTo(UPDATED_GRKKMX);
        assertThat(testGongjijin.getDwkkmx()).isEqualTo(UPDATED_DWKKMX);
        assertThat(testGongjijin.getTotal()).isEqualTo(UPDATED_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingGongjijin() throws Exception {
        int databaseSizeBeforeUpdate = gongjijinRepository.findAll().size();

        // Create the Gongjijin

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGongjijinMockMvc.perform(put("/api/gongjijins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(gongjijin)))
            .andExpect(status().isBadRequest());

        // Validate the Gongjijin in the database
        List<Gongjijin> gongjijinList = gongjijinRepository.findAll();
        assertThat(gongjijinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGongjijin() throws Exception {
        // Initialize the database
        gongjijinRepository.saveAndFlush(gongjijin);

        int databaseSizeBeforeDelete = gongjijinRepository.findAll().size();

        // Delete the gongjijin
        restGongjijinMockMvc.perform(delete("/api/gongjijins/{id}", gongjijin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Gongjijin> gongjijinList = gongjijinRepository.findAll();
        assertThat(gongjijinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gongjijin.class);
        Gongjijin gongjijin1 = new Gongjijin();
        gongjijin1.setId(1L);
        Gongjijin gongjijin2 = new Gongjijin();
        gongjijin2.setId(gongjijin1.getId());
        assertThat(gongjijin1).isEqualTo(gongjijin2);
        gongjijin2.setId(2L);
        assertThat(gongjijin1).isNotEqualTo(gongjijin2);
        gongjijin1.setId(null);
        assertThat(gongjijin1).isNotEqualTo(gongjijin2);
    }
}
