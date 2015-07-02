package com.myorg.commonapp.controller.base;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huyan on 2015/7/2.
 */
public class FileUploadUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtils.class);

    /**
     * 文件上传
     * @param request
     * @param fileUploadPath 文件上传路径
     * @param useOriginFileName 是否使用原始文件名
     * @return 文件上传位置，相对路径
     */
    public List<String> uploadFile(HttpServletRequest request, String fileUploadPath, boolean useOriginFileName){

        List<String> uploadFilePathList = new ArrayList<String>();
        ServletContext servletContext = request.getSession().getServletContext();
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(servletContext);

        //判断请求form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)) {

            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            Iterator<String> fileNames=multiRequest.getFileNames();
            while(fileNames.hasNext()) {
                String fileName = fileNames.next();

                //得到文件保存的绝对路径
                String savePath = servletContext.getRealPath(fileUploadPath);
                //取得文件
                MultipartFile file=multiRequest.getFile(fileName);
                if(file!=null) {
                    //设置上传文件名
                    String name;
                    if (useOriginFileName){ //使用原始文件名称
                        name = file.getOriginalFilename();
                    } else { //使用时间戳作为文件名称
                        String fileOriginName = file.getOriginalFilename();
                        int lastIndex = fileOriginName.lastIndexOf(".");
                        String suffix = fileOriginName.substring(lastIndex, fileOriginName.length());
                        name = System.currentTimeMillis()+suffix;
                    }

                    try {
                        //上传
                        FileUtils.writeByteArrayToFile(new File(savePath, name), file.getBytes());
                        uploadFilePathList.add(fileUploadPath + File.separator + name);
                    } catch (IOException e) {
                        LOGGER.error("uploadFiles fileName["+fileName+"] is error", e);
                    }
                } //if

            } //while

        } //if

        return uploadFilePathList;
    }
}
