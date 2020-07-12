package com.example.dell.miss;

public class ZhuiyiMessage {
    private int imgId;
    private String zyname;
    private String zyyear;
    private String zyaddress;
    private String context;
    private String jianjie;

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public ZhuiyiMessage(){

    }
    public ZhuiyiMessage(int imgId,String zyname,String zyyear,String zyaddress){
        this.imgId = imgId;
        this.zyname = zyname;
        this.zyyear = zyyear;
        this.zyaddress = zyaddress;
    }
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getZyname() {
        return zyname;
    }

    public void setZyname(String zyname) {
        this.zyname = zyname;
    }

    public String getZyyear() {
        return zyyear;
    }

    public void setZyyear(String zyyear) {
        this.zyyear = zyyear;
    }

    public String getZyaddress() {
        return zyaddress;
    }

    public void setZyaddress(String zyaddress) {
        this.zyaddress = zyaddress;
    }
}
