package com.yfy.app.net.user;

import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.SEND_PHONE_CODE+"Response")
public class PhoneCodeRes {

    @Attribute(name = "xmlns", empty = "http://tempuri.org/", required = false)
    public String nameSpace;

    @Element(name = TagFinal.SEND_PHONE_CODE+"Result", required = false)
    public String result;
}
