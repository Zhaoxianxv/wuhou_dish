package com.yfy.app.net.car;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/27.
 */
@Root(name = TagFinal.CAR_SET_STATE, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,Base.id,"opearteid","text"})
public class CarStateReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.session_key, required = false)
    private String session_key= Base.user.getSession_key();

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.id, required = false)
    private int id;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "opearteid", required = false)
    private int opearteid;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "text", required = false)
    private String text;

    public void setId(int id) {
        this.id = id;
    }

    public void setOpearteid(int opearteid) {
        this.opearteid = opearteid;
    }

    public void setText(String text) {
        this.text = text;
    }
}
