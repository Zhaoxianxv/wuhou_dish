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
@Root(name = TagFinal.CAR_GET_USER_LIST, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"key","carid","state","page","size"})
public class CarGetUserListReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key= Base.user.getSession_key();

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "key", required = false)
    private String key="";

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "carid", required = false)
    private int carid;
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "state", required = false)
    private int state;
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "page", required = false)
    private int page;
    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "size", required = false)
    private int size=TagFinal.TEN_INT;

    public void setKey(String key) {
        this.key = key;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
