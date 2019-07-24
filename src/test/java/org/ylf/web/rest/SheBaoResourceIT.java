package org.ylf.web.rest;

import org.ylf.ExcelEmailApp;
import org.ylf.domain.SheBao;
import org.ylf.repository.SheBaoRepository;
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
 * Integration tests for the {@Link SheBaoResource} REST controller.
 */
@SpringBootTest(classes = ExcelEmailApp.class)
public class SheBaoResourceIT {

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
    private SheBaoRepository sheBaoRepository;

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

    private MockMvc restSheBaoMockMvc;

    private SheBao sheBao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SheBaoResource sheBaoResource = new SheBaoResource(sheBaoRepository);
        this.restSheBaoMockMvc = MockMvcBuilders.standaloneSetup(sheBaoResource)
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
    public static SheBao createEntity(EntityManager em) {
        SheBao sheBao = new SheBao()
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
        return sheBao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SheBao createUpdatedEntity(EntityManager em) {
        SheBao sheBao = new SheBao()
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
        return sheBao;
    }

    @BeforeEach
    public void initTest() {
        sheBao = createEntity(em);
    }

    @Test
    @Transactional
    public void createSheBao() throws Exception {
        int databaseSizeBeforeCreate = sheBaoRepository.findAll().size();

        // Create the SheBao
        restSheBaoMockMvc.perform(post("/api/she-baos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sheBao)))
            .andExpect(status().isCreated());

        // Validate the SheBao in the database
        List<SheBao> sheBaoList = sheBaoRepository.findAll();
        assertThat(sheBaoList).hasSize(databaseSizeBeforeCreate + 1);
        SheBao testSheBao = sheBaoList.get(sheBaoList.size() - 1);
        assertThat(testSheBao.getXh()).isEqualTo(DEFAULT_XH);
        assertThat(testSheBao.getDept()).isEqualTo(DEFAULT_DEPT);
        assertThat(testSheBao.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSheBao.getSbjsYanglaoShiye()).isEqualTo(DEFAULT_SBJS_YANGLAO_SHIYE);
        assertThat(testSheBao.getSbjsGongShang()).isEqualTo(DEFAULT_SBJS_GONG_SHANG);
        assertThat(testSheBao.getSbjsYiShangSheng()).isEqualTo(DEFAULT_SBJS_YI_SHANG_SHENG);
        assertThat(testSheBao.getGrkkxxYaolang()).isEqualTo(DEFAULT_GRKKXX_YAOLANG);
        assertThat(testSheBao.getGrkkxxShiye()).isEqualTo(DEFAULT_GRKKXX_SHIYE);
        assertThat(testSheBao.getGrkkxxYiliao()).isEqualTo(DEFAULT_GRKKXX_YILIAO);
        assertThat(testSheBao.getGrkkxxTotal()).isEqualTo(DEFAULT_GRKKXX_TOTAL);
        assertThat(testSheBao.getDwbfYaolao()).isEqualTo(DEFAULT_DWBF_YAOLAO);
        assertThat(testSheBao.getDwbfShiye()).isEqualTo(DEFAULT_DWBF_SHIYE);
        assertThat(testSheBao.getDwbfYiliao()).isEqualTo(DEFAULT_DWBF_YILIAO);
        assertThat(testSheBao.getDwbfGongshang()).isEqualTo(DEFAULT_DWBF_GONGSHANG);
        assertThat(testSheBao.getDwbfShengyu()).isEqualTo(DEFAULT_DWBF_SHENGYU);
        assertThat(testSheBao.getDwbfTotal()).isEqualTo(DEFAULT_DWBF_TOTAL);
    }

    @Test
    @Transactional
    public void createSheBaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sheBaoRepository.findAll().size();

        // Create the SheBao with an existing ID
        sheBao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSheBaoMockMvc.perform(post("/api/she-baos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sheBao)))
            .andExpect(status().isBadRequest());

        // Validate the SheBao in the database
        List<SheBao> sheBaoList = sheBaoRepository.findAll();
        assertThat(sheBaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSheBaos() throws Exception {
        // Initialize the database
        sheBaoRepository.saveAndFlush(sheBao);

        // Get all the sheBaoList
        restSheBaoMockMvc.perform(get("/api/she-baos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sheBao.getId().intValue())))
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
    public void getSheBao() throws Exception {
        // Initialize the database
        sheBaoRepository.saveAndFlush(sheBao);

        // Get the sheBao
        restSheBaoMockMvc.perform(get("/api/she-baos/{id}", sheBao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sheBao.getId().intValue()))
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
    public void getNonExistingSheBao() throws Exception {
        // Get the sheBao
        restSheBaoMockMvc.perform(get("/api/she-baos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSheBao() throws Exception {
        // Initialize the database
        sheBaoRepository.saveAndFlush(sheBao);

        int databaseSizeBeforeUpdate = sheBaoRepository.findAll().size();

        // Update the sheBao
        SheBao updatedSheBao = sheBaoRepository.findById(sheBao.getId()).get();
        // Disconnect from session so that the updates on updatedSheBao are not directly saved in db
        em.detach(updatedSheBao);
        updatedSheBao
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

        restSheBaoMockMvc.perform(put("/api/she-baos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSheBao)))
            .andExpect(status().isOk());

        // Validate the SheBao in the database
        List<SheBao> sheBaoList = sheBaoRepository.findAll();
        assertThat(sheBaoList).hasSize(databaseSizeBeforeUpdate);
        SheBao testSheBao = sheBaoList.get(sheBaoList.size() - 1);
        assertThat(testSheBao.getXh()).isEqualTo(UPDATED_XH);
        assertThat(testSheBao.getDept()).isEqualTo(UPDATED_DEPT);
        assertThat(testSheBao.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSheBao.getSbjsYanglaoShiye()).isEqualTo(UPDATED_SBJS_YANGLAO_SHIYE);
        assertThat(testSheBao.getSbjsGongShang()).isEqualTo(UPDATED_SBJS_GONG_SHANG);
        assertThat(testSheBao.getSbjsYiShangSheng()).isEqualTo(UPDATED_SBJS_YI_SHANG_SHENG);
        assertThat(testSheBao.getGrkkxxYaolang()).isEqualTo(UPDATED_GRKKXX_YAOLANG);
        assertThat(testSheBao.getGrkkxxShiye()).isEqualTo(UPDATED_GRKKXX_SHIYE);
        assertThat(testSheBao.getGrkkxxYiliao()).isEqualTo(UPDATED_GRKKXX_YILIAO);
        assertThat(testSheBao.getGrkkxxTotal()).isEqualTo(UPDATED_GRKKXX_TOTAL);
        assertThat(testSheBao.getDwbfYaolao()).isEqualTo(UPDATED_DWBF_YAOLAO);
        assertThat(testSheBao.getDwbfShiye()).isEqualTo(UPDATED_DWBF_SHIYE);
        assertThat(testSheBao.getDwbfYiliao()).isEqualTo(UPDATED_DWBF_YILIAO);
        assertThat(testSheBao.getDwbfGongshang()).isEqualTo(UPDATED_DWBF_GONGSHANG);
        assertThat(testSheBao.getDwbfShengyu()).isEqualTo(UPDATED_DWBF_SHENGYU);
        assertThat(testSheBao.getDwbfTotal()).isEqualTo(UPDATED_DWBF_TOTAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSheBao() throws Exception {
        int databaseSizeBeforeUpdate = sheBaoRepository.findAll().size();

        // Create the SheBao

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSheBaoMockMvc.perform(put("/api/she-baos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sheBao)))
            .andExpect(status().isBadRequest());

        // Validate the SheBao in the database
        List<SheBao> sheBaoList = sheBaoRepository.findAll();
        assertThat(sheBaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSheBao() throws Exception {
        // Initialize the database
        sheBaoRepository.saveAndFlush(sheBao);

        int databaseSizeBeforeDelete = sheBaoRepository.findAll().size();

        // Delete the sheBao
        restSheBaoMockMvc.perform(delete("/api/she-baos/{id}", sheBao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SheBao> sheBaoList = sheBaoRepository.findAll();
        assertThat(sheBaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SheBao.class);
        SheBao sheBao1 = new SheBao();
        sheBao1.setId(1L);
        SheBao sheBao2 = new SheBao();
        sheBao2.setId(sheBao1.getId());
        assertThat(sheBao1).isEqualTo(sheBao2);
        sheBao2.setId(2L);
        assertThat(sheBao1).isNotEqualTo(sheBao2);
        sheBao1.setId(null);
        assertThat(sheBao1).isNotEqualTo(sheBao2);
    }
}
