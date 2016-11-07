package com.hyq.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/28.
 */
@Entity
@Table(name = "t_leaveWords")
public class LeaveWords {

    private Integer id;
    private String content;
    private String ip;      //留言者ip
    private Date createTime;        //游客留言时间
    private int status; //表示该条留言的状态，0表示未读，1表示已读
    private String tourEmail;   //游客邮箱

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Lob
    @Column(columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(length = 10,columnDefinition = "int default 0")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(length = 50)
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 50)
    public String getTourEmail() {
        return tourEmail;
    }

    public void setTourEmail(String tourEmail) {
        this.tourEmail = tourEmail;
    }
}
