package com.example.demo.model;

public class Software {
    public Software(String id, String name, String source, String introduce, String downloadlink, String imglink,String downloadCount){
        setId(Integer.valueOf(id));
        setName(name);
        setSource(source);
        setIntroduce(introduce);
        setDownloadlink(downloadlink);
        setImglink(imglink);
        setDownloadcount(Integer.valueOf(downloadCount));
    }
    public Software(int id, String name, String source, String introduce, String downloadlink, String imglink, int downloadcount){
        setId(id);
        setName(name);
        setSource(source);
        setIntroduce(introduce);
        setDownloadlink(downloadlink);
        setImglink(imglink);
        setDownloadcount(downloadcount);
    }
    private Integer id;

    private String name;

    private String source;

    private String introduce;

    private String downloadlink;

    private String imglink;

    private Integer downloadcount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getDownloadlink() {
        return downloadlink;
    }

    public void setDownloadlink(String downloadlink) {
        this.downloadlink = downloadlink == null ? null : downloadlink.trim();
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink == null ? null : imglink.trim();
    }

    public Integer getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Integer downloadcount) {
        this.downloadcount = downloadcount;
    }
}