package com.hyq.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
@Entity
@Table(name = "t_type")
public class Type {

    private Integer id;

    private String typeName;

    private String typeDesc;

    private long articleNum;

    private List<Article> articleList = new ArrayList<Article>();

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 50)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(length = 400)
    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @OneToMany(targetEntity = Article.class,mappedBy="type")
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Column(length = 10,columnDefinition = "int default 0")
    public long getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(long articleNum) {
        this.articleNum = articleNum;
    }
}
