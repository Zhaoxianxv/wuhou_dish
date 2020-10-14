package com.yfy.app.login;

import java.util.List;

/**
 * Created by yfy1 on 2016/12/1.
 */
public class UserRes {

    /**
     * id :
     * fxid : 0
     * headPic : null
     * name :
     * username :
     */


    /**
     * userinfo : {"id":"","fxid":0,"headPic":null,"name":"","username":""}
     * session_key :
     * result : false

     */
    private String headpic;
    private String term;
    private String realname;



    private String schoolname;
    private String schoolid;
    private String session_key;
    private String result;
    private String error_code;
    private UserinfoBean userinfo;



    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    /**
     * 重名
     */
    private List<Stunlist> stunlist;

    public List<Stunlist> getStunlist() {
        return stunlist;
    }

    public void setStunlist(List<Stunlist> stunlist) {
        this.stunlist = stunlist;
    }


    /**
     * -----------Mobile-------
     */
    private String Mobile;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
