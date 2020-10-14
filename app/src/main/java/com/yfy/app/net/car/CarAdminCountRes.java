package com.yfy.app.net.car;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/27.
 */
@Root(name = TagFinal.CAR_ADMIN_COUNT+"Response")
public class CarAdminCountRes {
    @Attribute(name = "xmlns", empty = Base.NAMESPACE, required = false)
    public String nameSpace;

    @Element(name = TagFinal.CAR_ADMIN_COUNT+"Result", required = false)
    public String result;
}
