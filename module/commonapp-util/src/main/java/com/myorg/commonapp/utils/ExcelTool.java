package com.myorg.commonapp.utils;

import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * Created by huyan on 16/9/19.
 */
public class ExcelTool {

    private static final Logger LOGGER = Logger.getLogger(ExcelTool.class);

    public static void buildExcelByTemplate(String template, Map data, OutputStream outputStream) {
        Workbook workbook = null;
        try {
            workbook = getWorkBook(template);
        } catch (InvalidFormatException e) {
            LOGGER.warn("template format error", e);
        } catch (IOException e) {
            LOGGER.warn("template io error", e);
        }
        if (null == workbook) {
            return;
        }
        //使用 jxls 导出excel，导出的字段内容中有一个字段内容是 url 地址（http://.....），导出发现，
        // url地址被截断了：只剩下 http: 了，后面的网站没有了
        //jxls的官方论坛上找到了答案：原来 // 是jxls 内置的 metainfotoken符号
        //下面2句代码是正解，能处理此问题
        Configuration config = new Configuration();
        config.setMetaInfoToken("\\\\");
        XLSTransformer transformer = new XLSTransformer(config);
        transformer.transformWorkbook(workbook, data);
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.warn("out excel io error", e);
        }
    }

    private static Workbook getWorkBook(String template) throws IOException, InvalidFormatException {
        URL url = ExcelTool.class.getResource("/" + template);
        if (null == url) {
            LOGGER.info("not found excel template");
            return null;
        }

        File templateFile = new File(url.getPath());
        if (!templateFile.exists()) {
            LOGGER.info("jxls  template not exist:" + templateFile.getPath());
            return null;
        }
        InputStream is = new BufferedInputStream(new FileInputStream(templateFile));
        org.apache.poi.ss.usermodel.Workbook wb;
        try {
            wb = WorkbookFactory.create(is);
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return wb;
    }

}
