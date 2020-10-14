package com.yfy.app.net;

import com.yfy.app.net.affiche.AfficheListReq;
import com.yfy.app.net.affiche.SchoolBannerReq;
import com.yfy.app.net.affiche.SchoolGetMenuReq;
import com.yfy.app.net.atten.AdminUserReq;
import com.yfy.app.net.atten.AttenAdminListReq;
import com.yfy.app.net.atten.AttenCountReq;
import com.yfy.app.net.atten.AttenDelItemReq;
import com.yfy.app.net.atten.AttenItemDetailReq;
import com.yfy.app.net.atten.AttenTypeReq;
import com.yfy.app.net.atten.AttenUserListReq;
import com.yfy.app.net.box.BoxDetailReq;
import com.yfy.app.net.box.BoxLeaderReq;
import com.yfy.app.net.box.BoxUserListReq;
import com.yfy.app.net.box.CLeaderReq;
import com.yfy.app.net.box.CUserReq;
import com.yfy.app.net.car.CarAdminCountReq;
import com.yfy.app.net.car.CarAdminDepReq;
import com.yfy.app.net.car.CarAskDoReq;
import com.yfy.app.net.car.CarGetAdminListReq;
import com.yfy.app.net.car.CarGetAllListReq;
import com.yfy.app.net.car.CarGetReq;
import com.yfy.app.net.car.CarGetTypeReq;
import com.yfy.app.net.car.CarGetUserListReq;
import com.yfy.app.net.car.CarItemDetailReq;
import com.yfy.app.net.car.CarMoreForAskReq;
import com.yfy.app.net.car.CarStateReq;
import com.yfy.app.net.duty.DutyDelImageReq;
import com.yfy.app.net.duty.DutyPlaneReq;
import com.yfy.app.net.duty.DutySetPlanReq;
import com.yfy.app.net.duty.DutyTypeReq;
import com.yfy.app.net.duty.DutyUserListReq;
import com.yfy.app.net.duty.WeekAllReq;
import com.yfy.app.net.login.AlterPasswordReq;
import com.yfy.app.net.login.AlterTellReq;
import com.yfy.app.net.login.GetTellReq;
import com.yfy.app.net.login.UserLoginReq;
import com.yfy.app.net.login.UserLogoutReq;
import com.yfy.app.net.login.ResetCodeReq;
import com.yfy.app.net.login.ResetPasswordReq;
import com.yfy.app.net.login.SetPicReq;
import com.yfy.app.net.maintain_log.MainAdminListReq;
import com.yfy.app.net.maintain_log.MainDoTypeReq;
import com.yfy.app.net.maintain_log.MainItemDetailReq;
import com.yfy.app.net.maintain_log.MaintainCountReq;
import com.yfy.app.net.maintain_log.MaintainDelItemReq;
import com.yfy.app.net.maintain_log.MaintainGetSectionReq;
import com.yfy.app.net.maintain_log.MaintainListReq;
import com.yfy.app.net.maintain_log.MaintainSetSectionReq;
import com.yfy.app.net.maintain_log.OfficeReq;
import com.yfy.app.net.notice.GetDetailReq;
import com.yfy.app.net.notice.GetStuReq;
import com.yfy.app.net.notice.GetTeaReq;
import com.yfy.app.net.notice.NoticeSendListReq;
import com.yfy.app.net.notice.NoticeStateReq;
import com.yfy.app.net.notice.NoticeUserListReq;
import com.yfy.app.net.notice.ReadReq;
import com.yfy.app.net.tea_evaluate.JudgeAddReq;
import com.yfy.app.net.tea_evaluate.JudgeChartReq;
import com.yfy.app.net.tea_evaluate.JudgeItemReq;
import com.yfy.app.net.tea_evaluate.JudgeTjReq;
import com.yfy.app.net.tea_evaluate.JudgeparaReq;
import com.yfy.app.net.tea_evaluate.YearReq;
import com.yfy.app.net.user.AdminReq;
import com.yfy.app.net.user.NticeNumReq;
import com.yfy.app.net.user.ReadNoticeReq;
import com.yfy.app.net.user.TermGetCurrentReq;
import com.yfy.app.net.wuhou.GetMyScoreReq;
import com.yfy.app.net.wuhou.GetSchoolReq;
import com.yfy.app.net.wuhou.GetScoreReq;
import com.yfy.app.net.wuhou.GetStuListReq;
import com.yfy.app.net.wuhou.GetUrlReq;
import com.yfy.final_tag.TagFinal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 */
@Root(name = "Body", strict = false)
public class ReqBody {

    @Element(name = TagFinal.GET_USER_ADMIN, required = false)
    public AdminReq adminReq;





    @Element(name = TagFinal.GET_CURRENT_TERM, required = false)
    public TermGetCurrentReq termGetCurrentReq;

    @Element(name = TagFinal.READNOTICE, required = false)
    public ReadNoticeReq readnotice;


    @Element(name = TagFinal.GETNOTICENUM, required = false)
    public NticeNumReq getnoticenum;



    /**
    *------------------------------login----------
     */

    @Element(name = TagFinal.LOGIN, required = false)
    public UserLoginReq userLoginReq;

    @Element(name = TagFinal.USER_LOGOUT, required = false)
    public UserLogoutReq userLogoutReq;

    @Element(name = TagFinal.ALTER_PASSWORD, required = false)
    public AlterPasswordReq alterPasswordReq;

    @Element(name = TagFinal.USER_SET_MOBILE, required = false)
    public AlterTellReq alterTellReq;


    @Element(name = TagFinal.USER_GET_MOBILE, required = false)
    public GetTellReq callReq;


    @Element(name = TagFinal.GET_RESET_PASSWORD_CODE, required = false)
    public ResetCodeReq get_reset_password_code_req;

    @Element(name = TagFinal.RESET_PASSWORD, required = false)
    public ResetPasswordReq resetPasswordReq;

    @Element(name = TagFinal.USER_ADD_HEAD, required = false)
    public SetPicReq set_head_req;



    //----------------------duty-------------


    @Element(name = TagFinal.DUTY_GET_USER, required = false)
    public DutyUserListReq dutyUserListReq;

    @Element(name = TagFinal.DUTY_TYPE, required = false)
    public DutyTypeReq duty_typeReq;


    @Element(name = TagFinal.DUTY_SET_PLAN, required = false)
    public DutySetPlanReq dutySetPlanReq;

    @Element(name = TagFinal.DUTY_DEL_IMAGE, required = false)
    public DutyDelImageReq dutyDelImageReq;

    @Element(name = TagFinal.DUTY_GET_PLANE, required = false)
    public DutyPlaneReq dutyPlaneReq;

    @Element(name = TagFinal.USER_GET_WEEK_ALL, required = false)
    public WeekAllReq weekAllReq;
    /**
     * -----------------affiche  school news---------------
     */
    @Element(name = TagFinal.SCHOOL_NEWS_LIST, required = false)
    public AfficheListReq afficheListReq;

    @Element(name = TagFinal.SCHOOL_NEWS_BANNER, required = false)
    public SchoolBannerReq schoolBannerReq;

    @Element(name = TagFinal.SCHOOL_GET_MENU, required = false)
    public SchoolGetMenuReq schoolGetMenuReq;
    /**
     * -------------------box-----------------------
     */
    @Element(name = TagFinal.BOX_GET_LEADER_LIST, required = false)
    public BoxLeaderReq boxLeaderReq;
    @Element(name = TagFinal.BOX_GET_COUNT_LEADER, required = false)
    public CLeaderReq cleaderReq;
    @Element(name = TagFinal.BOX_GET_COUNT_USER, required = false)
    public CUserReq cUserReq;

    @Element(name = TagFinal.BOX_GET_USER, required = false)
    public BoxUserListReq boxUserListReq;

    @Element(name = TagFinal.BOX_GET_DEATIL, required = false)
    public BoxDetailReq boxDetailReq;

    /**
     * -----------------------atten------------------------
     */



    @Element(name = TagFinal.ATTENNEW_GET_MAIN_LIST_USER, required = false)
    public AttenUserListReq atten_user_list_body;

    @Element(name = TagFinal.ATTENNEW_GET_MAIN_LIST_ADMIN, required = false)
    public AttenAdminListReq atten_admin_list_body;

    @Element(name = TagFinal.ATTENNEW_ADMIN_COUNT, required = false)
    public AttenCountReq count_atten;

    @Element(name = TagFinal.ATTENNEW_TYPE, required = false)
    public AttenTypeReq type_atten;

    @Element(name = TagFinal.ATTENNEW_USER_LIST, required = false)
    public AdminUserReq atten_get_admin;

    @Element(name = TagFinal.ATTENNEW_DELETE,required = false)
    public AttenDelItemReq attenDelItemReq;

    @Element(name = TagFinal.ATTEN_ITEM_DETAIL,required = false)
    public AttenItemDetailReq attenItemDetailReq;

    /**
     * ------------------------maintain------------------
     */

    @Element(name = TagFinal.MAINNEW_GET_DETAIL, required = false)
    public MainItemDetailReq mainItemDetailReq;

    @Element(name = TagFinal.MAINNEW_GET_OPERATE, required = false)
    public MainDoTypeReq mainDoTypeReq;

    @Element(name = TagFinal.MAINNEW_GET_COUNT, required = false)
    public MaintainCountReq count_maintain;

    @Element(name = TagFinal.MAINNEW_GET_MAIN_LIST_USER, required = false)
    public MaintainListReq maintain_userlist;

    @Element(name = TagFinal.MAINNEW_GET_MAIN_LIST_ADMIN, required = false)
    public MainAdminListReq maintain_adminlist;

    @Element(name = TagFinal.MAINNEW_GET_TYPE, required = false)
    public MaintainGetSectionReq maintainGetSectionReq;

    @Element(name = TagFinal.MAINNEW_GET_OFICE, required = false)
    public OfficeReq officeReq;

    @Element(name = TagFinal.MAINNEW_SET_TYPE, required = false)
    public MaintainSetSectionReq maintainSetSectionReq;

    @Element(name = TagFinal.MAINNEW_DELETE_MAINTAIN, required = false)
    public MaintainDelItemReq maintainDelItemReq;



    //----------------tea evaluate----------------------


    @Element(name = TagFinal.TEA_JUDGE_CLASS, required = false)
    public JudgeAddReq judgeReq;

    @Element(name = TagFinal.TEA_JUDGE_STATISTICS_CLASS, required = false)
    public JudgeTjReq judgetjReq;

    @Element(name = TagFinal.TEA_JUDGE_STATISTICS, required = false)
    public JudgeChartReq judgechartReq;

    @Element(name = TagFinal.TEA_JUDGE_YEAR, required = false)
    public YearReq year_Req;

    @Element(name = TagFinal.TEA_ADD_PARAMETER, required = false)
    public JudgeparaReq para_Req;


    @Element(name = TagFinal.TEA_JUDGE_INFO, required = false)
    public JudgeItemReq item_Req;


    /**
     *  ----------------notice-----------
     */

    @Element(name = TagFinal.NOTICE_GET_CONTENT, required = false)
    public GetDetailReq item_detail;

    @Element(name = TagFinal.NOTICE_RECEIVE_LIST, required = false)
    public NoticeUserListReq noticeUserListReq;

    @Element(name = TagFinal.NOTICE_SEND_BOX_LIST, required = false)
    public NoticeSendListReq noticeSendListReq;

    @Element(name = TagFinal.NOTICE_READ, required = false)
    public ReadReq read_notice;

    @Element(name = TagFinal.NOTICE_GET_TEA, required = false)
    public GetTeaReq get_tea;

    @Element(name = TagFinal.NOTICE_GET_STU, required = false)
    public GetStuReq get_stu;

    @Element(name = TagFinal.NOTICE_GET_READSTATE, required = false)
    public NoticeStateReq reade_state;


    /**
     * -----------car-----------
     */
    @Element(name = TagFinal.CAR_GET, required = false)
    public CarGetReq carGetReq;

    @Element(name = TagFinal.CAR_GET_TYPE,required = false)
    public CarGetTypeReq carGetTypeReq;

    @Element(name = TagFinal.CAR_SET_STATE,required = false)
    public CarStateReq carStateReq;

    @Element(name = TagFinal.CAR_GET_USER_LIST,required = false)
    public CarGetUserListReq carGetUserListReq;

    @Element(name = TagFinal.CAR_GET_ADMIN_LIST,required = false)
    public CarGetAdminListReq carGetAdminListReq;

    @Element(name = TagFinal.CAR_MORE_FOR_ASK_LIST,required = false)
    public CarMoreForAskReq carMoreForAskReq;

    @Element(name = TagFinal.CAR_GET_ITEM_DETAIL,required = false)
    public CarItemDetailReq carItemDetailReq;


    @Element(name = TagFinal.CAR_ASK_FOR,required = false)
    public CarAskDoReq carAskDoReq;



    @Element(name = TagFinal.CAR_ADMIN_COUNT,required = false)
    public CarAdminCountReq carAdminCountReq;

    @Element(name = TagFinal.CAR_GET_DEP,required = false)
    public CarAdminDepReq carAdminDepReq;

    @Element(name = TagFinal.CAR_GET_ALL_LIST,required = false)
    public CarGetAllListReq carGetAllListReq;


    //---------------------wuhou -------------------
    @Element(name = TagFinal.GET_STU_LIST,required = false)
    public GetStuListReq getStuListReq;
    @Element(name = TagFinal.GET_SCHOOL_LIST,required = false)
    public GetSchoolReq getSchoolReq;
    @Element(name = TagFinal.GET_STU_SCORE,required = false)
    public GetScoreReq getScoreReq;
    @Element(name = TagFinal.GET_URL, required = false)
    public GetUrlReq getUrlReq;

    @Element(name = TagFinal.GET_MY_SCORE,required = false)
    public GetMyScoreReq getMyScoreReq;

}

