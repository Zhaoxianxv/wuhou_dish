package com.yfy.app.net.login;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.RESET_PASSWORD, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {"userid","password","usertype"})
public class ResetPasswordReq {


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "userid", required = false)
    private int userid;     ///


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "password", required = false)
    private String password;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "usertype", required = false)
    private String usertype= "tea";


    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
