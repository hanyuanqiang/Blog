package com.hyq.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/28.
 */
@Entity
@Table(name = "t_user")
public class User {

    private Integer id;     //主键
    private String userName;    //登录名
    private String password;    //密码
    private String nickName;    //昵称
    private String city;    //所在城市
    private String signature;   //个性签名
    private String avatar;      //头像url
    private Date joinDate;      //加入时间
    private int totalOnlineTime;        //累计在线时长
    private String email;       //我的邮箱
    private String introduce;       //个人介绍

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(length = 30)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(length = 200)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Column(length = 300)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Column(length = 20,columnDefinition = "int default 0")
    public int getTotalOnlineTime() {
        return totalOnlineTime;
    }

    public void setTotalOnlineTime(int totalOnlineTime) {
        this.totalOnlineTime = totalOnlineTime;
    }

    @Column(length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 1000)
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
