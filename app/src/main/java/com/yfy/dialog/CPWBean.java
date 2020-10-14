package com.yfy.dialog;

public class CPWBean {
    private String name;
    private String id;
    private boolean is_select;
    private boolean is_show=false;

    public CPWBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public CPWBean() {
    }

    public boolean isIs_show() {
        return is_show;
    }

    public void setIs_show(boolean is_show) {
        this.is_show = is_show;
    }

    public boolean isIs_select() {
        return is_select;
    }

    public void setIs_select(boolean is_select) {
        this.is_select = is_select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
