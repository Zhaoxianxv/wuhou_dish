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
@Root(name = TagFinal.CAR_ASK_FOR, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,Base.id,"start_time","end_time","address","type","reason","teachers","dealuseid"})
public class CarAskDoReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key= Base.user.getSession_key();

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.id, required = false)
    private int id=0;


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "start_time", required = false)
    private String start_time;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "end_time", required = false)
    private String end_time;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "address", required = false)
    private String address;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "type", required = false)
    private int type;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "teachers", required = false)
    private String teachers;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "reason", required = false)
    private String reason;


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "dealuseid", required = false)
    private int dealuseid;

    public void setId(int id) {
        this.id = id;
    }


    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(int type) {
        this.type = type;
    }


    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public void setDealuseid(int dealuseid) {
        this.dealuseid = dealuseid;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
