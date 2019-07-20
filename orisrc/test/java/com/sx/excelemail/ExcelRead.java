package com.sx.excelemail;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sx.excelemail.po.GongjijinBean;
import com.sx.excelemail.po.MailBean;
import com.sx.excelemail.po.ShebaoBean;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ExcelRead {


    @Test
    public void shebao() {
//        try {
//            ImportParams params = new ImportParams();
//            params.setHeadRows(3);
//            //params.setTitleRows(3);
//            params.setNeedSave(true);
//            List<ShebaoBean> result = ExcelImportUtil.importExcel(
//                    (new ClassPathResource("test.xlsx")).getFile(),
//                    ShebaoBean.class, params);
//            for (int i = 0; i < result.size(); i++) {
//                System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
//            }
//            Assert.assertTrue(result.size() == 2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Test
    public void gongjijin(){
//        try {
//            ImportParams params = new ImportParams();
//            params.setHeadRows(3);
//            params.setStartSheetIndex(1);
//            params.setSheetNum(1);
//            params.setNeedSave(true);
//            List<GongjijinBean> result = ExcelImportUtil.importExcel(
//                    (new ClassPathResource("test.xlsx")).getFile(),
//                    GongjijinBean.class, params);
//            for (int i = 0; i < result.size(); i++) {
//                System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
//            }
//            Assert.assertTrue(result.size() == 2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
            Map<String, String> map = new HashMap<>();
            Class<?> clazz = obj.getClass();
            System.out.println(clazz);
            for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(obj);
                    map.put(fieldName, String.valueOf(value));
                }
            return map;
        }


    @Test
    public void zt(){
        try {

            ImportParams params = new ImportParams();
            params.setHeadRows(3);
            //params.setTitleRows(3);
            params.setNeedSave(true);
            String path = "test.xlsx";
            List<ShebaoBean> shebaoBeans = ExcelImportUtil.importExcel(
                    (new ClassPathResource(path)).getFile(),
                    ShebaoBean.class, params);
//            for (int i = 0; i < shebaoBeans.size(); i++) {
//                System.out.println(ReflectionToStringBuilder.toString(shebaoBeans.get(i)));
//            }
//            Assert.assertTrue(shebaoBeans.size() == 2);

            ArrayList<ShebaoBean> unique  = shebaoBeans.stream().collect(
                    Collectors.collectingAndThen(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(o -> o.getName()))), ArrayList::new));

            List<ShebaoBean> subtract = ListUtils.subtract(shebaoBeans, unique);

            for (ShebaoBean s: subtract){
                String name = s.getName();
                log.info("重复的人员,没有发送邮件{}",s);
            }


            log.info("共计 {} 人, 重复 {} 人",shebaoBeans.size(),shebaoBeans.size()-unique.size());

            ImportParams importParams = new ImportParams();
            importParams.setHeadRows(3);
            importParams.setStartSheetIndex(1);
            importParams.setSheetNum(1);
            importParams.setNeedSave(true);
            List<GongjijinBean> gongjijinBeans = ExcelImportUtil.importExcel(
                    (new ClassPathResource(path)).getFile(),
                    GongjijinBean.class, importParams);
//            for (int i = 0; i < gongjijinBeans.size(); i++) {
//                System.out.println(ReflectionToStringBuilder.toString(gongjijinBeans.get(i)));
//            }




            //Assert.assertTrue(gongjijinBeans.size() == 2);



            TemplateExportParams templateExportParams = new TemplateExportParams(
                    "template.xlsx");



            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("date", "2014-12-25");
//            map.put("money", 2000000.00);
//            map.put("upperMoney", "贰佰万");
//            map.put("company", "执笔潜行科技有限公司");
//            map.put("bureau", "财政局");
//            map.put("person", "JueYue");
//            map.put("phone", "1879740****");
//            List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//            for (int i = 0; i < 4; i++) {
//                Map<String, String> lm = new HashMap<String, String>();
//                lm.put("id", i + 1 + "");
//                lm.put("zijin", i * 10000 + "");
//                lm.put("bianma", "A001");
//                lm.put("mingcheng", "设计");
//                lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
//                lm.put("quancheng", "开源项目");
//                lm.put("sqje", i * 10000 + "");
//                lm.put("hdje", i * 10000 + "");
//
//                listMap.add(lm);
//            }

            for (int i =0 ;i<shebaoBeans.size();i++){
                ShebaoBean value = shebaoBeans.get(i);
                List<Map<String,String>> a = new ArrayList();


                Map<String, String> stringObjectMap = objectToMap(value);
                a.add(stringObjectMap);
                map.put("sb", a);
                GongjijinBean value1 = gongjijinBeans.get(i);
                List<Map<String, String>> b = new ArrayList();
                Map<String, String> stringObjectMap1 = objectToMap(value1);
                b.add(stringObjectMap1);
                map.put("gjj", b);

                String name = value.getName();
                templateExportParams.setSheetName(name);

                Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
                File savefile = new File("D:/excel/");
                if (!savefile.exists()) {
                    savefile.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream("D:/excel/"+name+".xlsx");
                workbook.write(fos);
                fos.close();

            }










        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    @Test
    public void sendMail(){


    }


    @Test
    public void readUser(){
//        try {
//            ImportParams params = new ImportParams();
//            params.setNeedSave(true);
//            List<MailBean> result = ExcelImportUtil.importExcel(
//                    (new ClassPathResource("user.xlsx")).getFile(),
//                    MailBean.class, params);
//            for (int i = 0; i < result.size(); i++) {
//                System.out.println(ReflectionToStringBuilder.toString(result.get(i)));
//            }
//            Assert.assertTrue(result.size() == 1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }




    @Test
    public void export(){
//        try {
//            TemplateExportParams templateExportParams = new TemplateExportParams(
//                    "template.xlsx");
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("date", "2014-12-25");
//            map.put("money", 2000000.00);
//            map.put("upperMoney", "贰佰万");
//            map.put("company", "执笔潜行科技有限公司");
//            map.put("bureau", "财政局");
//            map.put("person", "JueYue");
//            map.put("phone", "1879740****");
//            List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
//            for (int i = 0; i < 4; i++) {
//                Map<String, String> lm = new HashMap<String, String>();
//                lm.put("id", i + 1 + "");
//                lm.put("zijin", i * 10000 + "");
//                lm.put("bianma", "A001");
//                lm.put("mingcheng", "设计");
//                lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
//                lm.put("quancheng", "开源项目");
//                lm.put("sqje", i * 10000 + "");
//                lm.put("hdje", i * 10000 + "");
//
//                listMap.add(lm);
//            }
//            map.put("maplist", listMap);
//
//            Workbook workbook = ExcelExportUtil.exportExcel(templateExportParams, map);
//            File savefile = new File("D:/excel/");
//            if (!savefile.exists()) {
//                savefile.mkdirs();
//            }
//            FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书_map.xls");
//            workbook.write(fos);
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
