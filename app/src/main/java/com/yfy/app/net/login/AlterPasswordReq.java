package com.yfy.app.net.login;

import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * Created by yfyandr on 2018/4/26.
 */
@Root(name = TagFinal.ALTER_PASSWORD, strict = false)
@Namespace(reference = Base.NAMESPACE)
@Order(elements = {Base.session_key,"oldpassword","newpassword"})
public class AlterPasswordReq {

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "session_key", required = false)
    private String session_key= Base.user.getSession_key();     ///


    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "oldpassword", required = false)
    private String oldPassword;

    @Namespace(reference = Base.NAMESPACE)
    @Element(name = "newpassword", required = false)
    private String newPassword;

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
