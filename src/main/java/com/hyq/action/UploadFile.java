package com.hyq.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by Administrator on 2016/9/10.
 */
@Component
public class UploadFile extends ActionSupport implements ServletResponseAware,ServletContextAware,ServletRequestAware {

    /*private ObjectMapper mapper = new ObjectMapper();
    */
    private HttpServletResponse response;
    private ServletContext servletContext;
    private HttpServletRequest request;

    private String title;
    private File file;
    private String fileContentType;
    private String fileFileName;
    private String savePath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public String execute() throws Exception {
        String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png" };
        //System.out.println("type:   "+getFileContentType());
        boolean allowed = Arrays.asList(allowedType).contains(getFileContentType());
        if (!allowed) {
            response.getWriter().write("error|不支持的类型");
            return null;
        }

        //提取文件扩展名
        String fileNameExtension =getFileFileName().substring(getFileFileName().lastIndexOf("."),getFileFileName().length());
        // System.out.println("后缀名："+fileNameExtension);
        // 生成实际存储的真实文件名
        String realName = UUID.randomUUID().toString() + fileNameExtension;
        //System.out.println("新文件名:"+realName);
        savePath = servletContext.getRealPath("/files/");


        FileOutputStream fos = new FileOutputStream(getSavePath()+realName);
        FileInputStream fis = new FileInputStream(getFile());
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=fis.read(buffer))>0){
            fos.write(buffer,0,len);
        }

        fos = new FileOutputStream("G://hanyuanqiang.github.io/blog/files/"+realName);
        fis = new FileInputStream(getFile());
        buffer = new byte[1024];
        len = 0;
        while((len=fis.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        fos.close();
        fis.close();

        response.getWriter().write(request.getContextPath() + "/files/" + realName);
        //System.out.println("图片路径:"+request.getContextPath() + "/files/" + realName);
        return null;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
