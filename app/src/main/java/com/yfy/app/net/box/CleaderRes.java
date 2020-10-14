package com.yfy.app.net.box;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/27.
 */
@Root(name = TagFinal.BOX_GET_COUNT_LEADER+"Response")
public class CleaderRes {
    @Attribute(name = "xmlns", empty = Base.NAMESPACE, required = false)
    public String nameSpace;

    @Element(name = TagFinal.BOX_GET_COUNT_LEADER+"Result", required = false)
    public String cLeaderResult;
}
