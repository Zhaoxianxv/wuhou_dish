package com.yfy.app.net.wuhou;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.GET_URL, strict = false)
@Namespace(reference = Base.NAMESPACE)
public class GetUrlReq {
}
