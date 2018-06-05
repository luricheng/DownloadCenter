package com.example.demo.model;

public class DownloadlogKey {
    public DownloadlogKey(int softwareid,String accountid){
        setAccountid(accountid);
        setSoftwareid(softwareid);
    }
    private Integer softwareid;

    private String accountid;

    public Integer getSoftwareid() {
        return softwareid;
    }

    public void setSoftwareid(Integer softwareid) {
        this.softwareid = softwareid;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }
}