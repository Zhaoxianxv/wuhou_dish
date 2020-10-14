package com.yfy.app.net;


import com.yfy.app.net.affiche.AfficheListRes;
import com.yfy.app.net.affiche.SChoolGetMenuRes;
import com.yfy.app.net.affiche.SchoolBannerRes;
import com.yfy.app.net.atten.AdminUserRes;
import com.yfy.app.net.atten.AttenAdminListRes;
import com.yfy.app.net.atten.AttenCountRes;
import com.yfy.app.net.atten.AttenDelItemRes;
import com.yfy.app.net.atten.AttenItemDetailRes;
import com.yfy.app.net.atten.AttenTypeRes;
import com.yfy.app.net.atten.AttenUserListRes;
import com.yfy.app.net.box.BoxDetailRes;
import com.yfy.app.net.box.BoxLeaderRes;
import com.yfy.app.net.box.BoxUserListRes;
import com.yfy.app.net.box.CUserRes;
import com.yfy.app.net.box.CleaderRes;
import com.yfy.app.net.car.CarAdminCountRes;
import com.yfy.app.net.car.CarAdminDepRes;
import com.yfy.app.net.car.CarAskDoRes;
import com.yfy.app.net.car.CarGetAdminListRes;
import com.yfy.app.net.car.CarGetAllListRes;
import com.yfy.app.net.car.CarGetRes;
import com.yfy.app.net.car.CarGetTypeRes;
import com.yfy.app.net.car.CarGetUserListRes;
import com.yfy.app.net.car.CarItemDetailRes;
import com.yfy.app.net.car.CarMoreForAskRes;
import com.yfy.app.net.car.CarStateRes;
import com.yfy.app.net.duty.DutyDelImageRes;
import com.yfy.app.net.duty.DutyPlaneRes;
import com.yfy.app.net.duty.DutySetPlanRes;
import com.yfy.app.net.duty.DutyTypeRes;
import com.yfy.app.net.duty.DutyUserListRes;
import com.yfy.app.net.duty.WeekAllRes;
import com.yfy.app.net.login.AlterPasswordRes;
import com.yfy.app.net.login.AlterTellRes;
import com.yfy.app.net.login.GetTellRes;
import com.yfy.app.net.login.UserLoginRes;
import com.yfy.app.net.login.UserLogoutRes;
import com.yfy.app.net.login.ResetCodeRes;
import com.yfy.app.net.login.ResetPasswordRes;
import com.yfy.app.net.login.SetPicRes;
import com.yfy.app.net.maintain_log.MainAdminListRes;
import com.yfy.app.net.maintain_log.MainDoTypeRes;
import com.yfy.app.net.maintain_log.MainItemDetailRes;
import com.yfy.app.net.maintain_log.MaintainCountRes;
import com.yfy.app.net.maintain_log.MaintainDelItemRes;
import com.yfy.app.net.maintain_log.MaintainGetSectionRes;
import com.yfy.app.net.maintain_log.MaintainListRes;
import com.yfy.app.net.maintain_log.MaintainSetSectionRes;
import com.yfy.app.net.maintain_log.OfficeRes;
import com.yfy.app.net.notice.GetDetailRes;
import com.yfy.app.net.notice.GetStuRes;
import com.yfy.app.net.notice.GetTeaRes;
import com.yfy.app.net.notice.NoticeSendListRes;
import com.yfy.app.net.notice.NoticeStateRes;
import com.yfy.app.net.notice.NoticeUserListRes;
import com.yfy.app.net.notice.ReadRes;
import com.yfy.app.net.tea_evaluate.JudgeAddRes;
import com.yfy.app.net.tea_evaluate.JudgeChartRes;
import com.yfy.app.net.tea_evaluate.JudgeItemRes;
import com.yfy.app.net.tea_evaluate.JudgeTjRes;
import com.yfy.app.net.tea_evaluate.JudgeparaRes;
import com.yfy.app.net.tea_evaluate.YearRes;
import com.yfy.app.net.user.AdminRes;
import com.yfy.app.net.user.NticeNumRes;
import com.yfy.app.net.user.ReadNoticeRes;
import com.yfy.app.net.user.TermGetCurrentRes;
import com.yfy.app.net.wuhou.GetMyScoreRes;
import com.yfy.app.net.wuhou.GetScoreRes;
import com.yfy.app.net.wuhou.GetStuListRes;
import com.yfy.app.net.wuhou.GetSchoolRes;
import com.yfy.app.net.wuhou.GetUrlRes;
import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 用户角色返回body
 * Created by SmileXie on 16/7/15.
 */
@Root(name = Base.BODY)
public class ResBody {




    @Element(name = TagFinal.GET_CURRENT_TERM+"Response", required = false)
    public TermGetCurrentRes termGetCurrentRes;

    @Element(name = TagFinal.GETNOTICENUM+"Response", required = false)
    public NticeNumRes getnoticenumResponse;

    @Element(name = TagFinal.GET_USER_ADMIN+"Response", required = false)
    public AdminRes adminRes;

    @Element(name = TagFinal.READNOTICE+"Response", required = false)
    public ReadNoticeRes readnoticeResponse;



    //--------------------duty-----------

    @Element(name = TagFinal.DUTY_GET_USER+"Response", required = false)
    public DutyUserListRes dutyUserListRes;

    @Element(name = TagFinal.DUTY_SET_PLAN+"Response", required = false)
    public DutySetPlanRes dutySetPlanRes;

    @Element(name = TagFinal.DUTY_TYPE+"Response", required = false)
    public DutyTypeRes duty_type_sponse;


    @Element(name = TagFinal.DUTY_GET_PLANE+"Response", required = false)
    public DutyPlaneRes dutyPlaneRes;

    @Element(name = TagFinal.DUTY_DEL_IMAGE+"Response", required = false)
    public DutyDelImageRes dutyDelImageRes;

    @Element(name = TagFinal.USER_GET_WEEK_ALL+"Response", required = false)
    public WeekAllRes weekAllRes;

    /**
     * -----------------affiche  school news---------------
     */
    @Element(name = TagFinal.SCHOOL_NEWS_LIST+"Response", required = false)
    public AfficheListRes afficheListRes;


    @Element(name = TagFinal.SCHOOL_GET_MENU+Base.RESPONSE, required = false)
    public SChoolGetMenuRes sChoolGetMenuRes;

    @Element(name = TagFinal.SCHOOL_NEWS_BANNER+"Response", required = false)
    public SchoolBannerRes schoolBannerRes;
    /**
     *------------------------------login----------
     */

    @Element(name = TagFinal.LOGIN+Base.RESPONSE, required = false)
    public UserLoginRes userLoginRes;

    @Element(name = TagFinal.USER_LOGOUT+Base.RESPONSE, required = false)
    public UserLogoutRes userLogoutRes;

    @Element(name = TagFinal.ALTER_PASSWORD+Base.RESPONSE, required = false)
    public AlterPasswordRes alterPasswordRes;

    @Element(name = TagFinal.USER_SET_MOBILE+Base.RESPONSE, required = false)
    public AlterTellRes alter_tell_res;

    @Element(name = TagFinal.USER_GET_MOBILE+"Response", required = false)
    public GetTellRes callResponse;

    @Element(name = TagFinal.GET_RESET_PASSWORD_CODE+"Response", required = false)
    public ResetCodeRes get_reset_password_code_res;

    @Element(name = TagFinal.RESET_PASSWORD+"Response", required = false)
    public ResetPasswordRes resetPasswordRes;

    @Element(name = TagFinal.USER_ADD_HEAD+"Response", required = false)
    public SetPicRes set_head_res;
    /**
     * -------------------------atten---------------------
     */

    @Element(name = TagFinal.ATTENNEW_ADMIN_COUNT+"Response", required = false)
    public AttenCountRes attenCountRes;

    @Element(name = TagFinal.ATTENNEW_TYPE+"Response", required = false)
    public AttenTypeRes attendance_typeResponse;

    @Element(name = TagFinal.ATTENNEW_USER_LIST+"Response", required = false)
    public AdminUserRes attendance_approveResponse;

    @Element(name = TagFinal.ATTENNEW_GET_MAIN_LIST_ADMIN+Base.RESPONSE, required = false)
    public AttenAdminListRes atten_admin_list_body;

    @Element(name = TagFinal.ATTENNEW_GET_MAIN_LIST_USER+Base.RESPONSE, required = false)
    public AttenUserListRes atten_user_list_body;

    @Element(name = TagFinal.ATTENNEW_DELETE+Base.RESPONSE, required = false)
    public AttenDelItemRes attenDelItemRes;

    @Element(name = TagFinal.ATTEN_ITEM_DETAIL+Base.RESPONSE, required = false)
    public AttenItemDetailRes attenItemDetailRes;

    /**
     * ------------------------maintain-----------------------------
     */

    @Element(name = TagFinal.MAINNEW_GET_DETAIL+Base.RESPONSE, required = false)
    public MainItemDetailRes mainItemDetailRes;

    @Element(name = TagFinal.MAINNEW_GET_MAIN_LIST_USER+Base.RESPONSE, required = false)
    public MaintainListRes get_Maintain_userResponse;

    @Element(name = TagFinal.MAINNEW_GET_MAIN_LIST_ADMIN+Base.RESPONSE, required = false)
    public MainAdminListRes maintain_admin_body;

    @Element(name = TagFinal.MAINNEW_GET_OPERATE+Base.RESPONSE, required = false)
    public MainDoTypeRes mainDoTypeRes;

    @Element(name = TagFinal.MAINNEW_GET_COUNT+Base.RESPONSE, required = false)
    public MaintainCountRes get_maintain_review_countResponse;

    @Element(name = TagFinal.MAINNEW_GET_TYPE+Base.RESPONSE, required = false)
    public MaintainGetSectionRes maintainGetSectionRes;

    @Element(name = TagFinal.MAINNEW_GET_OFICE+Base.RESPONSE, required = false)
    public OfficeRes officeRes;

    @Element(name = TagFinal.MAINNEW_SET_TYPE+Base.RESPONSE, required = false)
    public MaintainSetSectionRes maintainSetSectionRes;

    @Element(name = TagFinal.MAINNEW_DELETE_MAINTAIN+Base.RESPONSE,required = false)
    public MaintainDelItemRes maintainDelItemRes;
    /**
     * -------------------box-----------------------
     */
    @Element(name = TagFinal.BOX_GET_LEADER_LIST+"Response", required = false)
    public BoxLeaderRes boxLeaderRes;
    @Element(name = TagFinal.BOX_GET_COUNT_LEADER+"Response", required = false)
    public CleaderRes cleaderRes;
    @Element(name = TagFinal.BOX_GET_COUNT_USER+"Response", required = false)
    public CUserRes cUserRes;

    @Element(name = TagFinal.BOX_GET_USER+"Response", required = false)
    public BoxUserListRes boxUserListRes;

    @Element(name = TagFinal.BOX_GET_DEATIL+"Response", required = false)
    public BoxDetailRes boxDetailRes;



    //----------------tea evaluate----------------------

    @Element(name = TagFinal.TEA_JUDGE_CLASS+"Response", required = false)
    public JudgeAddRes judge_Response;

    @Element(name =  TagFinal.TEA_JUDGE_STATISTICS_CLASS+"Response", required = false)
    public JudgeTjRes judge_tj_Response;

    @Element(name =  TagFinal.TEA_JUDGE_STATISTICS+"Response", required = false)
    public JudgeChartRes judge_chart_Response;

    @Element(name = TagFinal.TEA_JUDGE_YEAR+"Response", required = false)
    public YearRes year_Response;

    @Element(name =  TagFinal.TEA_ADD_PARAMETER+"Response", required = false)
    public JudgeparaRes para_Response;

    @Element(name = TagFinal.TEA_JUDGE_INFO+"Response", required = false)
    public JudgeItemRes item_Response;



    //--------------------notice-------
    @Element(name = TagFinal.NOTICE_GET_CONTENT+"Response", required = false)
    public GetDetailRes get_notice_contentRes;

    @Element(name = TagFinal.NOTICE_RECEIVE_LIST+"Response", required = false)
    public NoticeUserListRes noticeUserListRes;

    @Element(name = TagFinal.NOTICE_SEND_BOX_LIST+"Response", required = false)
    public NoticeSendListRes noticeSendListRes;

    @Element(name = TagFinal.NOTICE_READ+"Response", required = false)
    public ReadRes read_noticeRes;

    @Element(name = TagFinal.NOTICE_GET_TEA+"Response", required = false)
    public GetTeaRes get_contacts_teaRes;

    @Element(name = TagFinal.NOTICE_GET_STU+"Response", required = false)
    public GetStuRes get_contacts_stuRes;

    @Element(name = TagFinal.NOTICE_GET_READSTATE+"Response", required = false)
    public NoticeStateRes noticeStateRes;
    /**
     * -----------car-----------
     */

    @Element(name = TagFinal.CAR_GET+"Response", required = false)
    public CarGetRes carGetRes;
    @Element(name = TagFinal.CAR_GET_TYPE+Base.RESPONSE,required = false)
    public CarGetTypeRes carGetTypeRes;
    @Element(name = TagFinal.CAR_SET_STATE+Base.RESPONSE,required = false)
    public CarStateRes carStateRes;
    @Element(name = TagFinal.CAR_GET_USER_LIST+Base.RESPONSE,required = false)
    public CarGetUserListRes carGetUserListRes;
    @Element(name = TagFinal.CAR_GET_ADMIN_LIST+Base.RESPONSE,required = false)
    public CarGetAdminListRes carGetAdminListRes;
    @Element(name = TagFinal.CAR_MORE_FOR_ASK_LIST+Base.RESPONSE,required = false)
    public CarMoreForAskRes carMoreForAskRes;
    @Element(name = TagFinal.CAR_GET_ITEM_DETAIL+Base.RESPONSE,required = false)
    public CarItemDetailRes carItemDetailRes;


    @Element(name = TagFinal.CAR_ASK_FOR+Base.RESPONSE,required = false)
    public CarAskDoRes carAskDoRes;
    @Element(name = TagFinal.CAR_ADMIN_COUNT+Base.RESPONSE,required = false)
    public CarAdminCountRes carAdminCountRes;

    @Element(name = TagFinal.CAR_GET_DEP+Base.RESPONSE,required = false)
    public CarAdminDepRes carAdminDepRes;
    @Element(name = TagFinal.CAR_GET_ALL_LIST+Base.RESPONSE,required = false)
    public CarGetAllListRes carGetAllListRes;
    //--------------------------------wuhou---------------------
    @Element(name = TagFinal.GET_URL+Base.RESPONSE,required = false)
    public GetUrlRes getUrlRes;
    @Element(name = TagFinal.GET_SCHOOL_LIST+Base.RESPONSE,required = false)
    public GetSchoolRes getSchoolRes;

    @Element(name = TagFinal.GET_STU_LIST+Base.RESPONSE,required = false)
    public GetStuListRes getStuListRes;
    @Element(name = TagFinal.GET_STU_SCORE + Base.RESPONSE, required = false)
    public GetScoreRes getScoreRes;
    @Element(name = TagFinal.GET_MY_SCORE+Base.RESPONSE,required = false)
    public GetMyScoreRes getMyScoreRes;

}
