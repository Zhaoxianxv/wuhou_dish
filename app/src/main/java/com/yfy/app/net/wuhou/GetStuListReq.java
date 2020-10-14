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
@Root(name = TagFinal.GET_STU_LIST, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"schoolid"})
public class GetStuListReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.session_key, required = false)
    private String session_key= Base.user.getSession_key();     ///
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "schoolid", required = false)
    private String schoolid;     ///

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }
}
