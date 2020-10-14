package com.yfy.app.net.affiche;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.SCHOOL_NEWS_LIST, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {"no",Base.page,Base.size,"search"})
public class AfficheListReq {
    @Namespace(reference = Base.NAMESPACE)
    @Element(name ="no", required = false)
    private String no;     ///

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.page, required = false)
    private int page;     ///

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = Base.size, required = false)
    private int size=TagFinal.TEN_INT;     ///

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "search", required = false)
    private String search="";     ///

    public void setPage(int page) {
        this.page = page;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
