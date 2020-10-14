package com.yfy.final_tag;


import android.annotation.SuppressLint;
import android.media.ExifInterface;

import com.yfy.app.bean.ChildBean;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class StringUtils {


	/**
	 * @return float 保留两位小数
	 */
	public static String getTwoTofloat(float f){
		DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format(f);//format 返回的是字符串
		return p;
	}


	//未读(%1$d)
	public static String getTextJoint(String type,Object... args){
		return String.format(type,args);
	}



	public static String subStr(List<String> list, String tag) {
		StringBuilder sb = new StringBuilder();
		for (String name : list) {
			sb.append(name).append(tag);
		}
		String result = sb.toString();
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}


	public static String arraysToString(List<String> list,String tag) {
		StringBuilder sb = new StringBuilder();
		for (String name : list) {
			sb.append(name).append(tag);
		}
		String result = sb.toString();
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}




	public static String listToString(List<String> datas) {
		StringBuilder sb=new StringBuilder();
		for (String h:datas){
			sb.append(h).append(",");
		}
		if (sb.length()>2){
			return sb.substring(0,sb.length()-1);
		}
		return "";
	}

	public static List<String> getListToString(String content,String tag){
		List<String> list = Arrays.asList(content.split(tag));
		return list;
	}

	public static String[] StringToArrays(String content,String tag){
		List<String> list = Arrays.asList(content.split(tag));

		return arraysToList(list);
	}

	public static String[] arraysToList(List<String> list){
		String[] se=new String[list.size()];
		return list.toArray(se);
	}



	//requst替换引号"
	public static String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '\"':
					sb.append("\\\"");
					break;
//                case '\\':
//                    sb.append("\\\\");
//                    break;
				default:
					sb.append(c);
					break;

			}
		}
		return sb.toString();
	}

	//    responece 替换\
	public static String string2Jso(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
//                case '\"':
//                    sb.append("\\\"");
//                    break;
				case '\\':
					sb.append("\\\\");
					break;
				default:
					sb.append(c);
					break;

			}
		}
		return sb.toString();
	}


	public static String getParamsXml(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String s :list) {
			sb.append("<arr:string>")
					.append(s)
					.append("</arr:string>");
		}
		String resut = sb.toString();
		return resut;
	}


//    public static String string2Json(String s) {
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < s.length(); i++) {
//
//            char c = s.charAt(i);
//            switch (c) {
//                case '\"':
//                    sb.append("\\\"");
//                    break;
//                case '\\':
//                    sb.append("\\\\");
//                    break;
//                case '/':
//                    sb.append("\\/");
//                    break;
//                case '\b':
//                    sb.append("\\b");
//                    break;
//                case '\f':
//                    sb.append("\\f");
//                    break;
//                case '\n':
//                    sb.append("\\n");
//                    break;
//                case '\r':
//                    sb.append("\\r");
//                    break;
//                case '\t':
//                    sb.append("\\t");
//                    break;
//                default:
//                    sb.append(c);
//            }
//        }
//        return sb.toString();
//    }




//
//	public static String subClassIdStr(List<SchoolClass> list) {
//		StringBuilder sb = new StringBuilder();
//		for (SchoolClass schoolClass : list) {
//			sb.append(schoolClass.getClassid()).append(",");
//		}
//		String result = sb.toString();
//		if (result.length() > 0) {
//			result = result.substring(0, result.length() - 1);
//		}
//		return result;
//	}

	public static String subNameStr(List<ChildBean> list) {
		StringBuilder sb = new StringBuilder();
		for (ChildBean contactMember : list) {
			sb.append(contactMember.getUsername()).append(",");
		}

		String result = sb.toString();
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
//
	public static String subIdStr(List<ChildBean> list) {
		StringBuilder sb = new StringBuilder();
		for (ChildBean contactMember : list) {
			sb.append(contactMember.getUserid()).append(",");
		}
		String result = sb.toString();
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}




	
}
