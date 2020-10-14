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
@Root(name = TagFinal.DUTY_DEL_IMAGE, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"date",Base.id,"image"})
public class DutyDelImageReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key=Base.user.getSession_key();


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "date", required = false)
    private String date;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.id, required = false)
    private int id;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "image", required = false)
    private String image;

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
