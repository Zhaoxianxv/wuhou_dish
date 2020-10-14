package com.yfy.app.net;


import com.yfy.base.Base;
import com.yfy.final_tag.TagFinal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 接口请求
 */
public interface InterfaceApi {




    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_CURRENT_TERM})
    @POST(Base.POST_URI)
    Call<ResEnv> get_current_term(@Body ReqEnv Envelope);



    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GETNOTICENUM})
    @POST(Base.POST_URI)
    Call<ResEnv> getnoticenum(@Body ReqEnv Envelope);





    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_USER_ADMIN})
    @POST(Base.POST_URI)
    Call<ResEnv> get_user_right(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.READNOTICE})
    @POST(Base.POST_URI)
    Call<ResEnv> read_notice(@Body ReqEnv Envelope);

    /**
     * --------------------------duty----------------------------------------
     */

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.DUTY_TYPE})
    @POST(Base.POST_URI)
    Call<ResEnv> duty_get_type(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.DUTY_SET_PLAN})
    @POST(Base.POST_URI)
    Call<ResEnv> duty_set_plan(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.DUTY_GET_USER})
    @POST(Base.POST_URI)
    Call<ResEnv> duty_user_list(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.DUTY_GET_PLANE})
    @POST(Base.POST_URI)
    Call<ResEnv> duty_get_plane(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.USER_GET_WEEK_ALL})
    @POST(Base.POST_URI)
    Call<ResEnv> get_week_all(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.DUTY_DEL_IMAGE})
    @POST(Base.POST_URI)
    Call<ResEnv> duty_del_image(@Body ReqEnv Envelope);
    /**
     * ---------------------------affiche  school---------------------
     */
    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.SCHOOL_NEWS_BANNER})
    @POST(Base.POST_URI)
    Call<ResEnv> news_banner(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.SCHOOL_NEWS_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> school_news_list(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.SCHOOL_GET_MENU})
    @POST(Base.POST_URI)
    Call<ResEnv> school_menu(@Body ReqEnv Envelope);
    /**
     * -----------------------login---------------
     */

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.LOGIN})
    @POST(Base.POST_URI)
    Call<ResEnv> login_in(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.USER_LOGOUT})
    @POST(Base.POST_URI)
    Call<ResEnv> login_out(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ALTER_PASSWORD})
    @POST(Base.POST_URI)
    Call<ResEnv> alter_password(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.USER_GET_MOBILE})
    @POST(Base.POST_URI)
    Call<ResEnv> get_phone(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.USER_SET_MOBILE})
    @POST(Base.POST_URI)
    Call<ResEnv> alter_phone(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_RESET_PASSWORD_CODE})
    @POST(Base.POST_URI)
    Call<ResEnv> get_reset_password_code(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.RESET_PASSWORD})
    @POST(Base.POST_URI)
    Call<ResEnv> reset_password(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.USER_ADD_HEAD})
    @POST(Base.POST_URI)
    Call<ResEnv> set_head(@Body ReqEnv Envelope);

    /**
     * -------------------box-----------------------
     */

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.BOX_GET_LEADER_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> box_leaders(@Body ReqEnv Envelope);
    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.BOX_GET_COUNT_LEADER})
    @POST(Base.POST_URI)
    Call<ResEnv> box_c_leader(@Body ReqEnv Envelope);
    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.BOX_GET_COUNT_USER})
    @POST(Base.POST_URI)
    Call<ResEnv> box_c_user(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.BOX_GET_DEATIL})
    @POST(Base.POST_URI)
    Call<ResEnv> box_read_detail(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.BOX_GET_USER})
    @POST(Base.POST_URI)
    Call<ResEnv> box_user_list(@Body ReqEnv Envelope);
    /**
     * --------------------------------maintain----------------------------------
     */

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.MAINNEW_GET_DETAIL})
    @POST(Base.POST_URI)
    Call<ResEnv> main_item_detail(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.MAINNEW_GET_COUNT})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_count(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.MAINNEW_GET_OPERATE})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_get_do_type(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.MAINNEW_GET_MAIN_LIST_USER})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_user(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.MAINNEW_GET_MAIN_LIST_ADMIN})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_admin(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+ TagFinal.MAINNEW_GET_TYPE})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_get_section(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+ TagFinal.MAINNEW_GET_OFICE})
    @POST(Base.POST_URI)
    Call<ResEnv> get_office(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+ TagFinal.MAINNEW_SET_TYPE})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_set_section(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+ TagFinal.MAINNEW_DELETE_MAINTAIN})
    @POST(Base.POST_URI)
    Call<ResEnv> maintain_del_item(@Body ReqEnv Envelope);
//
    /**
     *----------------------atten-----------------------
     */

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTENNEW_GET_MAIN_LIST_USER})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_user_list(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTENNEW_GET_MAIN_LIST_ADMIN})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_admin_list(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTENNEW_ADMIN_COUNT})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_count(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTENNEW_TYPE})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_type(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTENNEW_USER_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_get_admin(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTENNEW_DELETE})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_del_item(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.ATTEN_ITEM_DETAIL})
    @POST(Base.POST_URI)
    Call<ResEnv> atten_get_item(@Body ReqEnv Envelope);

//
//
    /**
     * ---------------------notice--------------------
     */

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_RECEIVE_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_user_list(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_SEND_BOX_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_send_list(@Body ReqEnv Envelope);//已发信息

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_GET_CONTENT})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_get_detail(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_READ})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_read(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_GET_READSTATE})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_read_state(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_GET_TEA})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_get_tea(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.NOTICE_GET_STU})
    @POST(Base.POST_URI)
    Call<ResEnv> notice_get_stu(@Body ReqEnv Envelope);

//    /**
//     *----------------------order-----------------------
//     */
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.READNOTICE})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> read_notice_order(@Body ReqOrderEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.ORDER_GET_ROOM_NAME})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> get_funcRoom_name(@Body ReqOrderEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.ORDER_QUERY})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> query_funcRoom(@Body ReqOrderEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.Order_User_Detsail})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> my_funcRoom(@Body ReqOrderEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.ORDER_GET_DETAIL})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> get_funcRoom_detail(@Body ReqOrderEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.ORDER_GET_GRADE})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> get_funcRoom_type(@Body ReqOrderEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.ORDER_GET_COUNT})
//    @POST(TagFinal.POST_URI)
//    Call<ResOrderEnv> review_funcRoom_count(@Body ReqOrderEnv Envelope);
//
//
//    /**
//     * -----------------------moral-------------------
//     */
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.CONVENTION_GET_TIME})
//    @POST(TagFinal.POST_URI)
//    Call<ResMoralEnv> moral_time(@Body ReqMoralEnv Envelope);

    /**
     * -----------------------tea评测--------------------------
     */
    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.TEA_JUDGE_CLASS})
    @POST(Base.POST_URI)
    Call<ResEnv> teacher_judge_class(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.TEA_JUDGE_STATISTICS_CLASS})
    @POST(Base.POST_URI)
    Call<ResEnv> judge_tj_class(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.TEA_JUDGE_STATISTICS})
    @POST(Base.POST_URI)
    Call<ResEnv> judge_statistics(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.TEA_JUDGE_YEAR})
    @POST(Base.POST_URI)
    Call<ResEnv> judge_year(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.TEA_ADD_PARAMETER})
    @POST(Base.POST_URI)
    Call<ResEnv> judge_item(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.TEA_JUDGE_INFO})
    @POST(Base.POST_URI)
    Call<ResEnv> item_info(@Body ReqEnv Envelope);
//
//
//    /**
//     * ---------------------applied---------------------
//     */
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.APPLIED_ADMIN_LIST})
//    @POST(TagFinal.POST_URI)
//    Call<ResEnv> applied_admin_list(@Body ReqEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.APPLIED_USER_LIST})
//    @POST(TagFinal.POST_URI)
//    Call<ResEnv> applied_user_list(@Body ReqEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.APPLIED_GET_DETAIL})
//    @POST(TagFinal.POST_URI)
//    Call<ResEnv> applied_item_detail(@Body ReqEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.APPLIED_GET_PROJEDCT_STATE})
//    @POST(TagFinal.POST_URI)
//    Call<ResEnv> appliedGetList(@Body ReqEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.NOTICE_GET_TEA})
//    @POST(TagFinal.POST_URI)
//    Call<ResEnv> notice_get_tea(@Body ReqEnv Envelope);
//
//    @Headers({TagFinal.Content_Type, TagFinal.SOAP_ACTION+TagFinal.NOTICE_GET_STU})
//    @POST(TagFinal.POST_URI)
//    Call<ResEnv> notice_get_stu(@Body ReqEnv Envelope);

    /**
     * ---------------------car------------------
     */
    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET_TYPE})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get_type(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_SET_STATE})
    @POST(Base.POST_URI)
    Call<ResEnv> car_set_state(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET_USER_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get_user_list(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET_ADMIN_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get_admin_list(@Body ReqEnv Envelope);


    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_MORE_FOR_ASK_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> car_more_for_ask_list(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET_ITEM_DETAIL})
    @POST(Base.POST_URI)
    Call<ResEnv> car_item_detail(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_ASK_FOR})
    @POST(Base.POST_URI)
    Call<ResEnv> car_ask_do(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_ADMIN_COUNT})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get_admin_count(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET_DEP})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get_dep(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.CAR_GET_ALL_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> car_get_all_list(@Body ReqEnv Envelope);

    //-------------------wuhou----------------------------
    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_STU_SCORE})
    @POST(Base.POST_URI)
    Call<ResEnv> get_stu_score(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_STU_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> get_stu_list(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_SCHOOL_LIST})
    @POST(Base.POST_URI)
    Call<ResEnv> get_school_list(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION + TagFinal.GET_URL})
    @POST(Base.POST_URI)
    Call<ResEnv> get_url(@Body ReqEnv Envelope);

    @Headers({Base.Content_Type, Base.SOAP_ACTION+TagFinal.GET_MY_SCORE})
    @POST(Base.POST_URI)
    Call<ResEnv> get_my_score(@Body ReqEnv Envelope);

}

