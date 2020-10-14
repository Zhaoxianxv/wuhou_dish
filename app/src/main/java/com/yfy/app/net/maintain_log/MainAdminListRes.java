package com.yfy.app.net.maintain_log;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/27.
 */
@Root(name =  TagFinal.MAINNEW_GET_MAIN_LIST_ADMIN+Base.RESPONSE)
public class MainAdminListRes {

    @Attribute(name = Base.XMLNS, empty = Base.NAMESPACE, required = false)
    private String nameSpace;


    @Element(name =  TagFinal.MAINNEW_GET_MAIN_LIST_ADMIN+Base.RESULT, required = false)
    public String result;
}
