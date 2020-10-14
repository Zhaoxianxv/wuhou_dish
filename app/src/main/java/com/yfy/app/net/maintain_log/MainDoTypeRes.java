package com.yfy.app.net.maintain_log;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.MAINNEW_GET_OPERATE+"Response")
public class MainDoTypeRes {

    @Attribute(name = "xmlns", empty = Base.NAMESPACE, required = false)
    public String nameSpace;

    @Element(name =  TagFinal.MAINNEW_GET_OPERATE+"Result", required = false)
    public String result;
}
