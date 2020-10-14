package com.yfy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class KeyValue implements Parcelable {
    private String key;//描述值
    private String type="";//用于判断value取那个字段
    private boolean required=true;//用于判断是否必填
    private String value;//值
    private String name;//值名称
    private String id;
    private int view_type;
    private List<String> listValue=new ArrayList<String>();


    public KeyValue(String type, List<String> listValue) {
        this.type = type;
        this.listValue = listValue;
    }

    public KeyValue() {

    }
    public KeyValue(String key) {
        this.key = key;
    }

    public KeyValue(String key, String value, String name) {
        this.key = key;
        this.value = value;
        this.name = name;
    }
    public KeyValue(String key, String value, String name, int view_type) {
        this.key = key;
        this.value = value;
        this.name = name;
        this.view_type = view_type;
    }
    public KeyValue(String key, String value, String name, String id) {
        this.key = key;
        this.value = value;
        this.name = name;
        this.id = id;
    }

    public KeyValue(String key, String value,int view_type) {
        this.key = key;
        this.value = value;
        this.view_type = view_type;
    }


    public int getView_type() {
        return view_type;
    }

    public void setView_type(int view_type) {
        this.view_type = view_type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getListValue() {
        return listValue;
    }

    public void setListValue(List<String> listValue) {
        this.listValue = listValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.type);
        dest.writeByte(this.required ? (byte) 1 : (byte) 0);
        dest.writeString(this.value);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeInt(this.view_type);
        dest.writeStringList(this.listValue);
    }

    protected KeyValue(Parcel in) {
        this.key = in.readString();
        this.type = in.readString();
        this.required = in.readByte() != 0;
        this.value = in.readString();
        this.name = in.readString();
        this.id = in.readString();
        this.view_type = in.readInt();
        this.listValue = in.createStringArrayList();
    }

    public static final Creator<KeyValue> CREATOR = new Creator<KeyValue>() {
        @Override
        public KeyValue createFromParcel(Parcel source) {
            return new KeyValue(source);
        }

        @Override
        public KeyValue[] newArray(int size) {
            return new KeyValue[size];
        }
    };
}
