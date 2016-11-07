package com.hyq.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
@Entity
@Table(name = "t_article")
public class Article {


    private Integer id;     //主键
    private String title;       //文章标题
    private String content;     //文章内容
    private Date createTime;        //创建时间
    private int pageViews;      //访问次数
    private String source;      //所属来源（原创或者转载）
    private String keywords;       //关键字
    private int visitAuth = -1;      //访问权限,0表示都可见，1表示只对自己可见
    private String backUrl;     //后台访问文章详情页的url
    private String foreUrl;     //前台访问文章详情页的url

    private Type type;  //文章所属类型
    private List<Comment> commentList = new ArrayList<Comment>();       //文章的评论

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Lob
    @Column(columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 11,columnDefinition = "int default 0")
    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }
    @Column(length = 20)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    @Column(length = 4,columnDefinition = "int default 0")
    public int getVisitAuth() {
        return visitAuth;
    }

    public void setVisitAuth(int visitAuth) {
        this.visitAuth = visitAuth;
    }

    @ManyToOne
    @JoinColumn(name = "typeId")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    //获取文章的时候立即取出评论，删除文章同时删除该文章的评论
    @OneToMany(targetEntity = Comment.class,mappedBy = "article",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @OrderBy("commentTime desc")
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Column(length = 200)
    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    @Column(length = 200)
    public String getForeUrl() {
        return foreUrl;
    }

    public void setForeUrl(String foreUrl) {
        this.foreUrl = foreUrl;
    }
}
