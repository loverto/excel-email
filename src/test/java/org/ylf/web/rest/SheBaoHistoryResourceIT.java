package org.ylf.web.rest;

import org.ylf.ExcelEmailApp;
import org.ylf.domain.SheBaoHistory;
import org.ylf.repository.SheBaoHistoryRepository;
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
 * Integration tests for the {@Link SheBaoHistoryResource} REST controller.
 */
@SpringBootTest(classes = ExcelEmailApp.class)
public class SheBaoHistoryResourceIT {

    private static final Integer DEFAULT_XH = 1;
    private static final Integer UPDATED_XH = 2;

    private static final String DEFAULT_DEPT = "AAAAAAAAAA";
    private static final String UPDATED_DEPT = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SBJS_YANGLAO_SHIYE = "AAAAAAAAAA";
    private static final String UPDATED_SBJS_YANGLAO_SHIYE = "BBBBBBBBBB";

    private static final String DEFAULT_SBJS_GONG_SHANG = "AAAAAAAAAA";
    private static final String UPDATED_SBJS_GONG_SHANG = "BBBBBBBBBB";

    private static final String DEFAULT_SBJS_YI_SHANG_SHENG = "AAAAAAAAAA";
    private static final String UPDATED_SBJS_YI_SHANG_SHENG = "BBBBBBBBBB";

    private static final String DEFAULT_GRKKXX_YAOLANG = "AAAAAAAAAA";
    private static final String UPDATED_GRKKXX_YAOLANG = "BBBBBBBBBB";

    private static final String DEFAULT_GRKKXX_SHIYE = "AAAAAAAAAA";
    private static final String UPDATED_GRKKXX_SHIYE = "BBBBBBBBBB";

    private static final String DEFAULT_GRKKXX_YILIAO = "AAAAAAAAAA";
    private static final String UPDATED_GRKKXX_YILIAO = "BBBBBBBBBB";

    private static final String DEFAULT_GRKKXX_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_GRKKXX_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_DWBF_YAOLAO = "AAAAAAAAAA";
    private static final String UPDATED_DWBF_YAOLAO = "BBBBBBBBBB";

    private static final String DEFAULT_DWBF_SHIYE = "AAAAAAAAAA";
    private static final String UPDATED_DWBF_SHIYE = "BBBBBBBBBB";

    private static final String DEFAULT_DWBF_YILIAO = "AAAAAAAAAA";
    private static final String UPDATED_DWBF_YILIAO = "BBBBBBBBBB";

    private static final String DEFAULT_DWBF_GONGSHANG = "AAAAAAAAAA";
    private static final String UPDATED_DWBF_GONGSHANG = "BBBBBBBBBB";

    private static final String DEFAULT_DWBF_SHENGYU = "AAAAAAAAAA";
    private static final String UPDATED_DWBF_SHENGYU = "BBBBBBBBBB";

    private static final String DEFAULT_DWBF_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_DWBF_TOTAL = "BBBBBBBBBB";

    @Autowired
    private SheBaoHistoryRepository sheBaoHistoryRepository;

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

    private MockMvc restSheBaoHistoryMockMvc;

    private SheBaoHistory sheBaoHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SheBaoHistoryResource sheBaoHistoryResource = new SheBaoHistoryResource(sheBaoHistoryRepository);
        this.restSheBaoHistoryMockMvc = MockMvcBuilders.standaloneSetup(sheBaoHistoryResource)
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
    public static SheBaoHistory createEntity(EntityManager em) {
        SheBaoHistory sheBaoHistory = new SheBaoHistory()
            .xh(DEFAULT_XH)
            .dept(DEFAULT_DEPT)
            .name(DEFAULT_NAME)
            .sbjsYanglaoShiye(DEFAULT_SBJS_YANGLAO_SHIYE)
            .sbjsGongShang(DEFAULT_SBJS_GONG_SHANG)
            .sbjsYiShangSheng(DEFAULT_SBJS_YI_SHANG_SHENG)
            .grkkxxYaolang(DEFAULT_GRKKXX_YAOLANG)
            .grkkxxShiye(DEFAULT_GRKKXX_SHIYE)
            .grkkxxYiliao(DEFAULT_GRKKXX_YILIAO)
            .grkkxxTotal(DEFAULT_GRKKXX_TOTAL)
            .dwbfYaolao(DEFAULT_DWBF_YAOLAO)
            .dwbfShiye(DEFAULT_DWBF_SHIYE)
            .dwbfYiliao(DEFAULT_DWBF_YILIAO)
            .dwbfGongshang(DEFAULT_DWBF_GONGSHANG)
            .dwbfShengyu(DEFAULT_DWBF_SHENGYU)
            .dwbfTotal(DEFAULT_DWBF_TOTAL);
        return sheBaoHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SheBaoHistory createUpdatedEntity(EntityManager em) {
        SheBaoHistory sheBaoHistory = new SheBaoHistory()
            .xh(UPDATED_XH)
            .dept(UPDATED_DEPT)
            .name(UPDATED_NAME)
            .sbjsYanglaoShiye(UPDATED_SBJS_YANGLAO_SHIYE)
            .sbjsGongShang(UPDATED_SBJS_GONG_SHANG)
            .sbjsYiShangSheng(UPDATED_SBJS_YI_SHANG_SHENG)
            .grkkxxYaolang(UPDATED_GRKKXX_YAOLANG)
            .grkkxxShiye(UPDATED_GRKKXX_SHIYE)
            .grkkxxYiliao(UPDATED_GRKKXX_YILIAO)
            .grkkxxTotal(UPDATED_GRKKXX_TOTAL)
            .dwbfYaolao(UPDATED_DWBF_YAOLAO)
            .dwbfShiye(UPDATED_DWBF_SHIYE)
            .dwbfYiliao(UPDATED_DWBF_YILIAO)
            .dwbfGongshang(UPDATED_DWBF_GONGSHANG)
            .dwbfShengyu(UPDATED_DWBF_SHENGYU)
            .dwbfTotal(UPDATED_DWBF_TOTAL);
        return sheBaoHistory;
    }

    @BeforeEach
    public void initTest() {
        sheBaoHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createSheBaoHistory() throws Exception {
        int databaseSizeBeforeCreate = sheBaoHistoryRepository.findAll().size();

        // Create the SheBaoHistory
        restSheBaoHistoryMockMvc.perform(post("/api/she-bao-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sheBaoHistory)))
            .andExpect(status().isCreated());

        // Validate the SheBaoHistory in the database
        List<SheBaoHistory> sheBaoHistoryList = sheBaoHistoryRepository.findAll();
        assertThat(sheBaoHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        SheBaoHistory testSheBaoHistory = sheBaoHistoryList.get(sheBaoHistoryList.size() - 1);
        assertThat(testSheBaoHistory.getXh()).isEqualTo(DEFAULT_XH);
        assertThat(testSheBaoHistory.getDept()).isEqualTo(DEFAULT_DEPT);
        assertThat(testSheBaoHistory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSheBaoHistory.getSbjsYanglaoShiye()).isEqualTo(DEFAULT_SBJS_YANGLAO_SHIYE);
        assertThat(testSheBaoHistory.getSbjsGongShang()).isEqualTo(DEFAULT_SBJS_GONG_SHANG);
        assertThat(testSheBaoHistory.getSbjsYiShangSheng()).isEqualTo(DEFAULT_SBJS_YI_SHANG_SHENG);
        assertThat(testSheBaoHistory.getGrkkxxYaolang()).isEqualTo(DEFAULT_GRKKXX_YAOLANG);
        assertThat(testSheBaoHistory.getGrkkxxShiye()).isEqualTo(DEFAULT_GRKKXX_SHIYE);
        assertThat(testSheBaoHistory.getGrkkxxYiliao()).isEqualTo(DEFAULT_GRKKXX_YILIAO);
        assertThat(testSheBaoHistory.getGrkkxxTotal()).isEqualTo(DEFAULT_GRKKXX_TOTAL);
        assertThat(testSheBaoHistory.getDwbfYaolao()).isEqualTo(DEFAULT_DWBF_YAOLAO);
        assertThat(testSheBaoHistory.getDwbfShiye()).isEqualTo(DEFAULT_DWBF_SHIYE);
        assertThat(testSheBaoHistory.getDwbfYiliao()).isEqualTo(DEFAULT_DWBF_YILIAO);
        assertThat(testSheBaoHistory.getDwbfGongshang()).isEqualTo(DEFAULT_DWBF_GONGSHANG);
        assertThat(testSheBaoHistory.getDwbfShengyu()).isEqualTo(DEFAULT_DWBF_SHENGYU);
        assertThat(testSheBaoHistory.getDwbfTotal()).isEqualTo(DEFAULT_DWBF_TOTAL);
    }

    @Test
    @Transactional
    public void createSheBaoHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sheBaoHistoryRepository.findAll().size();

        // Create the SheBaoHistory with an existing ID
        sheBaoHistory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSheBaoHistoryMockMvc.perform(post("/api/she-bao-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sheBaoHistory)))
            .andExpect(status().isBadRequest());

        // Validate the SheBaoHistory in the database
        List<SheBaoHistory> sheBaoHistoryList = sheBaoHistoryRepository.findAll();
        assertThat(sheBaoHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSheBaoHistories() throws Exception {
        // Initialize the database
        sheBaoHistoryRepository.saveAndFlush(sheBaoHistory);

        // Get all the sheBaoHistoryList
        restSheBaoHistoryMockMvc.perform(get("/api/she-bao-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sheBaoHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].xh").value(hasItem(DEFAULT_XH)))
            .andExpect(jsonPath("$.[*].dept").value(hasItem(DEFAULT_DEPT.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sbjsYanglaoShiye").value(hasItem(DEFAULT_SBJS_YANGLAO_SHIYE.toString())))
            .andExpect(jsonPath("$.[*].sbjsGongShang").value(hasItem(DEFAULT_SBJS_GONG_SHANG.toString())))
            .andExpect(jsonPath("$.[*].sbjsYiShangSheng").value(hasItem(DEFAULT_SBJS_YI_SHANG_SHENG.toString())))
            .andExpect(jsonPath("$.[*].grkkxxYaolang").value(hasItem(DEFAULT_GRKKXX_YAOLANG.toString())))
            .andExpect(jsonPath("$.[*].grkkxxShiye").value(hasItem(DEFAULT_GRKKXX_SHIYE.toString())))
            .andExpect(jsonPath("$.[*].grkkxxYiliao").value(hasItem(DEFAULT_GRKKXX_YILIAO.toString())))
            .andExpect(jsonPath("$.[*].grkkxxTotal").value(hasItem(DEFAULT_GRKKXX_TOTAL.toString())))
            .andExpect(jsonPath("$.[*].dwbfYaolao").value(hasItem(DEFAULT_DWBF_YAOLAO.toString())))
            .andExpect(jsonPath("$.[*].dwbfShiye").value(hasItem(DEFAULT_DWBF_SHIYE.toString())))
            .andExpect(jsonPath("$.[*].dwbfYiliao").value(hasItem(DEFAULT_DWBF_YILIAO.toString())))
            .andExpect(jsonPath("$.[*].dwbfGongshang").value(hasItem(DEFAULT_DWBF_GONGSHANG.toString())))
            .andExpect(jsonPath("$.[*].dwbfShengyu").value(hasItem(DEFAULT_DWBF_SHENGYU.toString())))
            .andExpect(jsonPath("$.[*].dwbfTotal").value(hasItem(DEFAULT_DWBF_TOTAL.toString())));
    }
    
    @Test
    @Transactional
    public void getSheBaoHistory() throws Exception {
        // Initialize the database
        sheBaoHistoryRepository.saveAndFlush(sheBaoHistory);

        // Get the sheBaoHistory
        restSheBaoHistoryMockMvc.perform(get("/api/she-bao-histories/{id}", sheBaoHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sheBaoHistory.getId().intValue()))
            .andExpect(jsonPath("$.xh").value(DEFAULT_XH))
            .andExpect(jsonPath("$.dept").value(DEFAULT_DEPT.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sbjsYanglaoShiye").value(DEFAULT_SBJS_YANGLAO_SHIYE.toString()))
            .andExpect(jsonPath("$.sbjsGongShang").value(DEFAULT_SBJS_GONG_SHANG.toString()))
            .andExpect(jsonPath("$.sbjsYiShangSheng").value(DEFAULT_SBJS_YI_SHANG_SHENG.toString()))
            .andExpect(jsonPath("$.grkkxxYaolang").value(DEFAULT_GRKKXX_YAOLANG.toString()))
            .andExpect(jsonPath("$.grkkxxShiye").value(DEFAULT_GRKKXX_SHIYE.toString()))
            .andExpect(jsonPath("$.grkkxxYiliao").value(DEFAULT_GRKKXX_YILIAO.toString()))
            .andExpect(jsonPath("$.grkkxxTotal").value(DEFAULT_GRKKXX_TOTAL.toString()))
            .andExpect(jsonPath("$.dwbfYaolao").value(DEFAULT_DWBF_YAOLAO.toString()))
            .andExpect(jsonPath("$.dwbfShiye").value(DEFAULT_DWBF_SHIYE.toString()))
            .andExpect(jsonPath("$.dwbfYiliao").value(DEFAULT_DWBF_YILIAO.toString()))
            .andExpect(jsonPath("$.dwbfGongshang").value(DEFAULT_DWBF_GONGSHANG.toString()))
            .andExpect(jsonPath("$.dwbfShengyu").value(DEFAULT_DWBF_SHENGYU.toString()))
            .andExpect(jsonPath("$.dwbfTotal").value(DEFAULT_DWBF_TOTAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSheBaoHistory() throws Exception {
        // Get the sheBaoHistory
        restSheBaoHistoryMockMvc.perform(get("/api/she-bao-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSheBaoHistory() throws Exception {
        // Initialize the database
        sheBaoHistoryRepository.saveAndFlush(sheBaoHistory);

        int databaseSizeBeforeUpdate = sheBaoHistoryRepository.findAll().size();

        // Update the sheBaoHistory
        SheBaoHistory updatedSheBaoHistory = sheBaoHistoryRepository.findById(sheBaoHistory.getId()).get();
        // Disconnect from session so that the updates on updatedSheBaoHistory are not directly saved in db
        em.detach(updatedSheBaoHistory);
        updatedSheBaoHistory
            .xh(UPDATED_XH)
            .dept(UPDATED_DEPT)
            .name(UPDATED_NAME)
            .sbjsYanglaoShiye(UPDATED_SBJS_YANGLAO_SHIYE)
            .sbjsGongShang(UPDATED_SBJS_GONG_SHANG)
            .sbjsYiShangSheng(UPDATED_SBJS_YI_SHANG_SHENG)
            .grkkxxYaolang(UPDATED_GRKKXX_YAOLANG)
            .grkkxxShiye(UPDATED_GRKKXX_SHIYE)
            .grkkxxYiliao(UPDATED_GRKKXX_YILIAO)
            .grkkxxTotal(UPDATED_GRKKXX_TOTAL)
            .dwbfYaolao(UPDATED_DWBF_YAOLAO)
            .dwbfShiye(UPDATED_DWBF_SHIYE)
            .dwbfYiliao(UPDATED_DWBF_YILIAO)
            .dwbfGongshang(UPDATED_DWBF_GONGSHANG)
            .dwbfShengyu(UPDATED_DWBF_SHENGYU)
            .dwbfTotal(UPDATED_DWBF_TOTAL);

        restSheBaoHistoryMockMvc.perform(put("/api/she-bao-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSheBaoHistory)))
            .andExpect(status().isOk());

        // Validate the SheBaoHistory in the database
        List<SheBaoHistory> sheBaoHistoryList = sheBaoHistoryRepository.findAll();
        assertThat(sheBaoHistoryList).hasSize(databaseSizeBeforeUpdate);
        SheBaoHistory testSheBaoHistory = sheBaoHistoryList.get(sheBaoHistoryList.size() - 1);
        assertThat(testSheBaoHistory.getXh()).isEqualTo(UPDATED_XH);
        assertThat(testSheBaoHistory.getDept()).isEqualTo(UPDATED_DEPT);
        assertThat(testSheBaoHistory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSheBaoHistory.getSbjsYanglaoShiye()).isEqualTo(UPDATED_SBJS_YANGLAO_SHIYE);
        assertThat(testSheBaoHistory.getSbjsGongShang()).isEqualTo(UPDATED_SBJS_GONG_SHANG);
        assertThat(testSheBaoHistory.getSbjsYiShangSheng()).isEqualTo(UPDATED_SBJS_YI_SHANG_SHENG);
        assertThat(testSheBaoHistory.getGrkkxxYaolang()).isEqualTo(UPDATED_GRKKXX_YAOLANG);
        assertThat(testSheBaoHistory.getGrkkxxShiye()).isEqualTo(UPDATED_GRKKXX_SHIYE);
        assertThat(testSheBaoHistory.getGrkkxxYiliao()).isEqualTo(UPDATED_GRKKXX_YILIAO);
        assertThat(testSheBaoHistory.getGrkkxxTotal()).isEqualTo(UPDATED_GRKKXX_TOTAL);
        assertThat(testSheBaoHistory.getDwbfYaolao()).isEqualTo(UPDATED_DWBF_YAOLAO);
        assertThat(testSheBaoHistory.getDwbfShiye()).isEqualTo(UPDATED_DWBF_SHIYE);
        assertThat(testSheBaoHistory.getDwbfYiliao()).isEqualTo(UPDATED_DWBF_YILIAO);
        assertThat(testSheBaoHistory.getDwbfGongshang()).isEqualTo(UPDATED_DWBF_GONGSHANG);
        assertThat(testSheBaoHistory.getDwbfShengyu()).isEqualTo(UPDATED_DWBF_SHENGYU);
        assertThat(testSheBaoHistory.getDwbfTotal()).isEqualTo(UPDATED_DWBF_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSheBaoHistory() throws Exception {
        int databaseSizeBeforeUpdate = sheBaoHistoryRepository.findAll().size();

        // Create the SheBaoHistory

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSheBaoHistoryMockMvc.perform(put("/api/she-bao-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sheBaoHistory)))
            .andExpect(status().isBadRequest());

        // Validate the SheBaoHistory in the database
        List<SheBaoHistory> sheBaoHistoryList = sheBaoHistoryRepository.findAll();
        assertThat(sheBaoHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSheBaoHistory() throws Exception {
        // Initialize the database
        sheBaoHistoryRepository.saveAndFlush(sheBaoHistory);

        int databaseSizeBeforeDelete = sheBaoHistoryRepository.findAll().size();

        // Delete the sheBaoHistory
        restSheBaoHistoryMockMvc.perform(delete("/api/she-bao-histories/{id}", sheBaoHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SheBaoHistory> sheBaoHistoryList = sheBaoHistoryRepository.findAll();
        assertThat(sheBaoHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SheBaoHistory.class);
        SheBaoHistory sheBaoHistory1 = new SheBaoHistory();
        sheBaoHistory1.setId(1L);
        SheBaoHistory sheBaoHistory2 = new SheBaoHistory();
        sheBaoHistory2.setId(sheBaoHistory1.getId());
        assertThat(sheBaoHistory1).isEqualTo(sheBaoHistory2);
        sheBaoHistory2.setId(2L);
        assertThat(sheBaoHistory1).isNotEqualTo(sheBaoHistory2);
        sheBaoHistory1.setId(null);
        assertThat(sheBaoHistory1).isNotEqualTo(sheBaoHistory2);
    }
}
