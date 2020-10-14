package com.yfy.wuhoudish_stu;

import com.yfy.wuhoudish.bean.ScoreBean;

import java.util.List;

public class TermBean {

    /**
     * termname : 2019-2020上期
     * termid : 3
     */

    private String termname;
    private String termid;
    private List<ScoreBean> scorelist;
    private List<ArtBean> artscorelist;
    public List<ArtBean> getArtscorelist() {
        return artscorelist;
    }

    public void setArtscorelist(List<ArtBean> artscorelist) {
        this.artscorelist = artscorelist;
    }

    public List<ScoreBean> getScorelist() {
        return scorelist;
    }

    public void setScorelist(List<ScoreBean> scorelist) {
        this.scorelist = scorelist;
    }

    public String getTermname() {
        return termname;
    }

    public void setTermname(String termname) {
        this.termname = termname;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }
}
