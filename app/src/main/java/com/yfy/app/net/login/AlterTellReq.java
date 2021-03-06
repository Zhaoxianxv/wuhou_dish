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
@Root(name = TagFinal.USER_SET_MOBILE, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"no"})
public class AlterTellReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key=  Base.user.getSession_key();     ///

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "no", required = false)
    private String no;     ///

    public void setNo(String no) {
        this.no = no;
    }
}
