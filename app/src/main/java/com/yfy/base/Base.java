package com.yfy.base;


import android.graphics.Typeface;

import com.yfy.db.User;

/**
 * Created by Daniel on 2016/3/8.
 * 产量们
 */
public class Base {
    public static String DB_NAME="yfydb";


    public final static String URL = "http://whqfgk.yfyit.com/service2.svc";//10.30.192.227
    public final static String RETROFIT_URI = "http://whqfgk.yfyit.com/";
    //app更新地址
    public static final String AUTO_UPDATE_URI = "http://www.yfyit.com/apk/gxxc.txt";
    public static final String AUTHORITY = "com.yfy.wuhoudish.fileProvider";//android 7.0文件访问权限Tag（要和Provider一直）
//--------------------------------------
    public static final String NET_SOAP_ACTION = "http://tempuri.org/Service2/";
    public static final String Content_Type = "Content-Type: text/xml;charset=UTF-8";//
//    public static final String Content_Type = "Content-Type: application/soap+xml;charset=UTF-8";//application/soap+xml
    public static final String SOAP_ACTION = "SOAPAction: http://tempuri.org/Service2/";//
    public static final String POST_URI = "/Service2.svc";//
    public static final String NAMESPACE = "http://tempuri.org/";//








    public static final String TEM = "tem";//
    public static final String ARR = "arr";//
    public static final String BODY = "Body";//
    public static final String RESPONSE = "Response";//
    public static final String RESULT = "Result";//
    public static final String XMLNS = "xmlns";//
    public static final String session_key = "session_key";//
    public static final String type = "type";//
    public static final String state = "state";//
    public static final String page = "page";//
    public static final String size = "size";//
    public static final String id = "id";//

    public final static String WCF_TXT = "wcf.txt";

    public static final String name = "name";//
    public static final String tag = "tag";//
    public static final String title = "title";//
    public static final String content = "content";//
    public static final String date = "date";//
    public static final String data = "data";//




    public static final int TIME_OUT = 10000;
    public final static String APP_ID="";
    public static final int UPLOAD_LIMIT = 100 * 1024;
    public static final long TOTAL_UPLOAD_LIMIT = 4 * 1024 * 1024;




    public static float density = 0;
    public static String wcfInfo = "";
    public static User user = null;
    public static Typeface typeface = null;


    //tea
    public static String year="";
    public static String year_name="";
    public static String type_id="";
    public static String type_name="";
}
