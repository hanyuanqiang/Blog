package com.hyq.action;

import com.hyq.entity.User;
import com.hyq.service.LeaveWordsService;
import com.hyq.service.UserService;
import com.hyq.util.*;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/9/29.
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware,ServletContextAware,ServletResponseAware {

    @Resource()
    private UserService userService;
    @Resource
    private LeaveWordsService leaveWordsService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;

    private User user;
    private String imageCode;
    private String rightPage;
    private String leftPage;
    private File file;
    private String fileContentType;
    private String fileFileName;
    private String savePath;


    public String getLeftPage() {
        return leftPage;
    }

    public void setLeftPage(String leftPage) {
        this.leftPage = leftPage;
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

    public String getRightPage() {
        return rightPage;
    }

    public void setRightPage(String rightPage) {
        this.rightPage = rightPage;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login()throws Exception{
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("sRand");

        if (StringUtil.isEmpty(realCode)||!realCode.equals(imageCode)){
            request.setAttribute("loginError","验证码错误！");
            return "loginError";
        }else if(user==null||StringUtil.isEmpty(user.getUserName())||
                StringUtil.isEmpty(user.getPassword())){
            request.setAttribute("loginError","用户名和密码不能为空！");
            return "loginError";
        }
        User currentUser = userService.login(user);

        if (currentUser==null){
            request.setAttribute("loginError","用户名或密码错误！");
            return "loginError";
        }else{
            session.setAttribute("currentUser",currentUser);
            long currentTime = System.currentTimeMillis();
            session.setAttribute("loginTime",currentTime);
            Logger logger = Logger.getLogger(this.getClass());
            logger.info("----------// 管理员登录  //------------");
            //获取未读留言
            int noReadLeaveWordsCount = leaveWordsService.getCountWithNoRead();
            session.setAttribute("noReadLWCount",noReadLeaveWordsCount);

            //设置cookie
            Cookie cookie = new Cookie("blogLoginInfo",currentUser.getUserName()+"-"+currentUser.getPassword());
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            return "mainPage";
        }

    }

    public String backupdate()throws Exception{
        rightPage = "/background/personCenter.jsp";
        return SUCCESS;
    }

    public String backsave()throws Exception{
        HttpSession session = request.getSession();
        if (getFileFileName()!=null){

            String[] allowedType = { "image/bmp", "image/gif", "image/jpeg", "image/png" };
            System.out.println("type:   "+getFileContentType());
            boolean allowed = Arrays.asList(allowedType).contains(getFileContentType());
            if (!allowed) {
                PrintException.print(ServletActionContext.getResponse(),"上传头像请选择图片文件");
                return null;
            }

            //提取文件扩展名
            String fileNameExtension =getFileFileName().substring(getFileFileName().lastIndexOf("."),getFileFileName().length());
            System.out.println("后缀名："+fileNameExtension);
            // 生成实际存储的真实文件名
            String realName = UUID.randomUUID().toString() + fileNameExtension;
            System.out.println("新文件名:"+realName);
            savePath = servletContext.getRealPath("/files/");
            System.out.println("savePath:"+savePath);

            FileOutputStream fos = new FileOutputStream(getSavePath()+realName);
            FileInputStream fis = new FileInputStream(getFile());
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len=fis.read(buffer))>0){
                fos.write(buffer,0,len);
            }
            user.setAvatar(request.getContextPath() + "/files/" + realName);
            System.out.println("avatar:"+request.getContextPath() + "/files/" + realName);

        }
        userService.save(user);
        user = userService.getUserById(1);
        session.setAttribute("currentUser",user);
        rightPage = "/background/personCenter.jsp";
        return SUCCESS;
    }

    public String about_me()throws Exception{
        leftPage = "/foreground/about_me.jsp";
        return "about_me";
    }

    public String backexit()throws Exception{
        //request.getSession().removeAttribute("currentUser");
        return "exit";
    }

    public String findPassword()throws Exception{
        User u = userService.getUserById(1);
        JSONObject result = new JSONObject();
        result.put("success", true);
        try {
            SendMailUtil.send("1255426342@qq.com","您正在找回账号和密码","账号："+u.getUserName()+";密码："+u.getPassword());
            SendMailUtil.send("15957196210@139.com","您正在找回账号和密码","账号："+u.getUserName()+";密码："+u.getPassword());
        }catch (Exception e){
            result.put("success",false);
            //e.printStackTrace();
        }finally {
            ResponseUtil.write(ServletActionContext.getResponse(), result);
            return null;
        }
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}
