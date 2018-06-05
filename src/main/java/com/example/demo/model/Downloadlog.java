package com.example.demo.model;


import java.sql.Timestamp;
import java.util.Date;

public class Downloadlog extends DownloadlogKey {

    public Downloadlog(Integer softwareId, String accountId, Timestamp time){
        super(softwareId,accountId);
        setTime(time);
    }

    public Downloadlog(Integer softwareId,String accountId,Date time){
        super(softwareId,accountId);
        setTime(time);
    }

    public Downloadlog(String accountId,Integer softwareId,Date time){
        super(softwareId,accountId);
        setTime(time);
    }

    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}