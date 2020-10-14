package com.yfy.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class User {

//            Entity note = schema.addEntity("User");
//        note.addIdProperty();
//        note.addStringProperty("session_key").notNull();
//        note.addIntProperty("class_id").notNull();
//        note.addIntProperty("fxt_id").notNull();
//        note.addStringProperty("head_pic").notNull();
//        note.addStringProperty("user_id").notNull();
//        note.addStringProperty("name").notNull();
//        note.addStringProperty("username").notNull();
//        note.addStringProperty("pass_word").notNull();
//        note.addStringProperty("user_type").notNull();
//        note.addDateProperty("date");



    @Id
    private Long id;
    @NotNull
    private String session_key;//性别
    @NotNull
    private String headpic;//性别
    @NotNull
    private String term;//性别
    @NotNull
    private String realname;//性别
    @NotNull
    private String pass_word;//性别
    @NotNull
    private String user_type;//用户类型
    @NotNull
    private String schoolname;//性别
    private String classname;//性别
    private String sex;//性别
    @NotNull
    private String schoolid;//性别
    @Generated(hash = 1572199164)
    public User(Long id, @NotNull String session_key, @NotNull String headpic,
            @NotNull String term, @NotNull String realname,
            @NotNull String pass_word, @NotNull String user_type,
            @NotNull String schoolname, String classname, String sex,
            @NotNull String schoolid) {
        this.id = id;
        this.session_key = session_key;
        this.headpic = headpic;
        this.term = term;
        this.realname = realname;
        this.pass_word = pass_word;
        this.user_type = user_type;
        this.schoolname = schoolname;
        this.classname = classname;
        this.sex = sex;
        this.schoolid = schoolid;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSession_key() {
        return this.session_key;
    }
    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
    public String getHeadpic() {
        return this.headpic;
    }
    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }
    public String getTerm() {
        return this.term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getRealname() {
        return this.realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getPass_word() {
        return this.pass_word;
    }
    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }
    public String getSchoolname() {
        return this.schoolname;
    }
    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }
    public String getSchoolid() {
        return this.schoolid;
    }
    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }
    public String getUser_type() {
        return this.user_type;
    }
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    public String getClassname() {
        return this.classname;
    }
    public void setClassname(String classname) {
        this.classname = classname;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }


}