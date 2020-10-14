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
@Root(name = TagFinal.USER_LOGOUT, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"andios","apikey"})
public class UserLogoutReq {

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key= Base.user.getSession_key();     ///

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "andios", required = false)
    private String andios="and";

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "apikey", required = false)
    private String apikey;


    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
