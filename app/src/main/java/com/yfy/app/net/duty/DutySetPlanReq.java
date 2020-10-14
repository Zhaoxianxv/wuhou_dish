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
@Root(name = TagFinal.DUTY_SET_PLAN, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"userid","typeid","date","reason"})
public class DutySetPlanReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.session_key, required = false)
    private String session_key=Base.user.getSession_key();


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "userid", required = false)
    private String userid;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "typeid", required = false)
    private int typeid;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "date", required = false)
    private String date;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "reason", required = false)
    private String reason;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}