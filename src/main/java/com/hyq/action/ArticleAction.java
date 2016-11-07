package com.hyq.action;

import com.hyq.entity.Article;
import com.hyq.entity.PageBean;
import com.hyq.entity.Type;
import com.hyq.entity.User;
import com.hyq.service.ArticleService;
import com.hyq.service.TypeService;
import com.hyq.util.*;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 * 处理一切跟文章有关的请求，包涵back的方法表示处理后台的请求
 */
@Controller
public class ArticleAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware{
    @Resource
    private ArticleService articleService;
    @Resource
    private TypeService typeService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;

    private String rightPage;
    private String leftPage;
    private List<Type> typeList;
    private Article article;
    private List<Article> articleList;
    private int currentPage;
    private String pageCode;
    private int articleId;
    private Article s_article;
    private String hiddenIcon;


    public String getHiddenIcon() {
        return hiddenIcon;
    }

    public void setHiddenIcon(String hiddenIcon) {
        this.hiddenIcon = hiddenIcon;
    }

    public String getLeftPage() {
        return leftPage;
    }

    public void setLeftPage(String leftPage) {
        this.leftPage = leftPage;
    }

    public Article getS_article() {
        return s_article;
    }

    public void setS_article(Article s_article) {
        this.s_article = s_article;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public String getRightPage() {
        return rightPage;
    }

    public void setRightPage(String rightPage) {
        this.rightPage = rightPage;
    }

    //跳转到写博客的页面，如果当前有articleId，则把id对应的文章传到前端
    public String backwrite()throws Exception{
        if (articleId!=0){
            //修改文章操作，如果该文章的静态页面文件存在，则删除它
            article = articleService.getArticleById(articleId);

            String backFilePath = getFilePathByUrl(article.getBackUrl());
            String foreFilePath = getFilePathByUrl(article.getForeUrl());

            File file = new File(backFilePath);
            while (file.exists()){
                System.out.println("删除后台静态页面");
                file.getAbsoluteFile().delete();
            }
            file = new File(foreFilePath);
            while (file.exists()){
                System.out.println("删除前台静态页面");
                file.getAbsoluteFile().delete();
            }

            file = null;

            //重新设置前后台静态页面url
            article.setBackUrl("");
            article.setForeUrl("");
            articleService.save(article);
            article = articleService.getArticleById(articleId);

        }
        typeList = typeService.findTypeList();
        rightPage = "/background/article_write.jsp";
        return SUCCESS;
    }

    //如果是新的则保存，否则是更新
    public String backsave()throws Exception{
        if (article.getId()==null){
            article.setCreateTime(new Date());
        }
        articleService.save(article);
        return "list";
    }

    //处理生成静态页面操作
    public String backGenHtmlPage() throws Exception{
        article = articleService.getArticleById(articleId);
        JSONObject result = new JSONObject();
        if(StringUtil.isEmpty(article.getBackUrl())&&StringUtil.isEmpty(article.getForeUrl())){
            String backUrl = getHostAndProjectName()+genHtmlPage(article.getId(),true);
            String foreUrl = getHostAndProjectName()+genHtmlPage(article.getId(),false);
            article.setForeUrl(foreUrl);
            article.setBackUrl(backUrl);
            articleService.save(article);
            result.put("success",true);
        }else{
            result.put("errorMsg","静态页面已经存在");
        }
        ResponseUtil.write(response,result);
        return null;
    }


    //删除某篇文章
    public String backdelete()throws Exception{
        //先判断该文章对应的静态页面是否存在，如果存在则先删除静态页面文件，再删除数据库中数据
        article = articleService.getArticleById(articleId);

        String backFilePath = getFilePathByUrl(article.getBackUrl());
        String foreFilePath = getFilePathByUrl(article.getForeUrl());

        File file = new File(backFilePath);
        while (file.exists()){
            System.out.println("删除后台静态页面");
            file.getAbsoluteFile().delete();
        }
        file = new File(foreFilePath);
        while (file.exists()){
            System.out.println("删除前台静态页面");
            file.getAbsoluteFile().delete();
        }

        articleService.delete(article);
        return "list";
    }


    //后台获取指定id的某篇博客
    public String backdetail()throws Exception{
        article = articleService.getArticleById(articleId);
        rightPage = "/background/article_detail.jsp";
        return SUCCESS;
    }

    //前台获取指定id的谋篇博客
    public String foreDetail()throws Exception{
        article = articleService.getArticleById(articleId);
        articleService.changePageViews(articleId);
        leftPage = "/foreground/article_detail.jsp";
        return "fore-index";
    }

    //后台获取博客列表
    public String backlist()throws Exception{
        pre_List("/article_backlist.action");
        rightPage = "/background/article_list.jsp";
        return SUCCESS;
    }

    //访问前台首页时，获取最新发布的一篇文章
    public String index()throws Exception{
        article = articleService.getArticleById(articleService.getNewestId());
        hiddenIcon = "show";
        leftPage = "/foreground/article_detail.jsp";
        return "fore-index";
    }

    //前台获取博客列表
    public String foreList()throws Exception{
        pre_List("/article_foreList.action");
        for (Article a : articleList) {
            //System.out.println(a.getContent());
            a.setContent(HtmlParser.getHtmlParser(a.getContent()));
        }
        leftPage = "/foreground/articleList_block.jsp";
        return "fore-index";
    }

    //前台和后台获取博客列表的公共部分，分页后面跟的action不一样，所以加个参数
    public void pre_List(String action){
        if(currentPage==0){
            currentPage = 1;
        }
        String pageParam = "";
        if (s_article!=null){
            if (StringUtil.isNotEmpty(s_article.getContent())){
                pageParam+="&s_article.content="+s_article.getContent();
            }
            if (s_article.getCreateTime()!=null){
                pageParam+="&s_article.createTime="+ DateUtil.formatDate(s_article.getCreateTime(),"yyyy-MM"+"-01 00:00:00");
            }

            if(s_article.getType()!=null){
                if (s_article.getType().getId()!=null){
                    pageParam+="&s_article.type.id="+s_article.getType().getId();
                }
            }
        }
        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute("currentUser");
        if (currentUser==null){
            if (s_article==null){
                s_article = new Article();
            }
            s_article.setVisitAuth(0);
        }
        typeList = typeService.findTypeList();
        PageBean pageBean = new PageBean(currentPage, Integer.parseInt(PropertiesUtil.getValue("pageSize")));
        pageCode = PageCode.getPageCode(articleService.getArticleCount(s_article),currentPage,Integer.parseInt(PropertiesUtil.getValue("pageSize")),action,pageParam);
        articleList = articleService.findArticleList(s_article,pageBean);
    }



    // 生成某篇文章的前后台对应的静态页面，并且返回新的url中的一部分，
    // isBack为true表示生成后台静态页面，为false表示生成前台静态页面
    public String genHtmlPage(int id,boolean isBack) throws Exception{
        Article a = articleService.getArticleById(id);
        String fileName;
        if(isBack){
            //后台以当前时间和id合并命名文件
            fileName = DateUtil.getPartOfMillis()+id+".html";
        }else{
            //前台仅仅以当前时间命名文件
            fileName = DateUtil.getPartOfMillis()+".html";
        }
        Date date = a.getCreateTime();
        //获取文章发布时间的年月日生成存放静态页面的文件夹
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DATE));
        String mulu = servletContext.getRealPath("/")+ "article\\" +year+"\\"+month+"\\"+day+"\\";
        //生成文件存放目录
        File file = new File(mulu+fileName);    //组成完整文件路径
        //判断文件是否存在，如果不存在则生成文件，否则跳过
        if (!file.exists()){
            //下面先获取当前请求的路径，然后替换为指向该文章详情页的url
            String targetUrl;
            if (isBack){
                targetUrl = getHostAndProjectName()+"/article_backdetail.action?articleId="+id;
            }else{
                targetUrl = getHostAndProjectName()+"/article_foreDetail?articleId="+id;
            }
            URL url = new URL(targetUrl);
            StringBuffer sb = new StringBuffer();
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream input = urlConnection.getInputStream();	//获取输入流
            String contentType = urlConnection.getContentType();//获取网页内容类型
            int tempIndex = -1;
            String ecoding = "UTF-8";		//默认网页编码为UTF-8
            if(contentType!=null){
                tempIndex = contentType.lastIndexOf("charset=");
                if(tempIndex!=-1){
                    ecoding = contentType.substring(tempIndex+8);//获取网页真正的编码
                }
            }

            byte[] buffer = new byte[627560];
            int size = 0;   //存储从流中读取的总字节数
            boolean streamTag = true;
            boolean flag = false;   //当第一次出现input.available()为0时，线程睡眠700(防止由于网络原因数据不完整)
            while(streamTag){
                int byteNumbers = input.available();    //本次从字节流中读取的字节数
                System.out.println("本次读取字节数："+byteNumbers);
                if(byteNumbers>0){
                    input.read(buffer, size, byteNumbers);
                    size +=byteNumbers;
                    flag = false;
                }else if(byteNumbers==0){
                    //第一次读取字节数为0时，线程睡眠500，防止由于网络原因造成判别错误
                    //连续两次读取字节数均为0则表示InputStream中的字节流读取完毕
                    if(!flag){
                        Thread.sleep(700);
                        flag=true;
                        continue;
                    }else if(flag){
                        streamTag = false;
                        sb.append(new String(buffer,0,size,ecoding));
                    }
                }
            }

            if(input!=null){
                input.close();
            }

            //System.out.println(sb.toString());  //输出获取到的文本

            file = new File(mulu);
            //判断指定目录文件夹是否存在，如果不存在则生成它
            if (!file.exists()){
                file.mkdirs();
            }

            file = new File(mulu+fileName); //完整的文件名路径

            //按照指定编码把字符串写入文件中
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
            writer.write(sb.toString());
            writer.close(); //关闭流，如果不关闭的话，则程序会占用静态文件，导致无法删除

            buffer = null;  //释放内存
            sb = null;  //释放内存
            //该返回值用于拼接访问该静态页面的url
            return "/article/"+year+"/"+month+"/"+day+"/"+fileName;
        }
        return null;

    }

    //根据url获取该文章的静态页面的绝对路径
    public String getFilePathByUrl(String url){
        if (StringUtil.isNotEmpty(url)){
            //url例如：http://localhost:8080/blog/article/2016/10/24/96701144.html
            url = url.substring(url.indexOf(request.getContextPath()+"/")+request.getContextPath().length()+1);
            //现在url变为：article/2016/10/24/96701144.html
            String[] params = url.split("/");

            String filePath = servletContext.getRealPath("/");
            for(int i=0;i<params.length;i++){
                filePath+=params[i];
                if (i!=params.length-1){
                    filePath+="\\";
                }
            }
            //返回结果示例：G:\IDEA Project\Blog\out\artifacts\Blog_war_exploded\article\2016\10\24\96701144.html
            return filePath;
        }
        return "";
    }

    //返回值示例：http://localhost:8080/blog
    public String getHostAndProjectName(){
        StringBuffer sb = request.getRequestURL();
        String contextPath = request.getContextPath();
        int tag = sb.toString().indexOf(contextPath);
        return sb.toString().substring(0,tag)+request.getContextPath();
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
