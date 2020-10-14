package com.yfy.app.net.notice;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/5/22.
 */
@Root(name = TagFinal.NOTICE_RECEIVE_LIST, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,Base.page,Base.size})
public class NoticeUserListReq {

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.session_key, required = false)
    private String session_key= Base.user.getSession_key();

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.page, required = false)
    private int page;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.size, required = false)
    private int size=TagFinal.TWO_TEN;

    public void setPage(int page) {
        this.page = page;
    }
}
