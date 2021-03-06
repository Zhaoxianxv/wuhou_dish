package com.yfy.app.net.duty;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.DUTY_GET_PLANE, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"typeid","date"})
public class DutyPlaneReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key=Base.user.getSession_key();


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "typeid", required = false)
    private int typeid;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "date", required = false)
    private String date;

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
