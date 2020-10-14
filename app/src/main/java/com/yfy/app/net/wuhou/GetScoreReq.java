package com.yfy.app.net.wuhou;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.GET_STU_SCORE, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements ={Base.session_key,"stuid"})
public class GetScoreReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.session_key, required = false)
    private String session_key= Base.user.getSession_key();     ///

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "stuid", required = false)
    private String stuid;    ///

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }
}
