package com.yfy.app.net.user;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/25.
 */

@Root(name =  TagFinal.GET_CURRENT_TERM+"Response")
public class TermGetCurrentRes {
    @Attribute(name = "xmlns", empty = Base.NAMESPACE, required = false)
    public String nameSpace;


    @Element(name = TagFinal.GET_CURRENT_TERM+"Result", required = false)
    public String result;

}
