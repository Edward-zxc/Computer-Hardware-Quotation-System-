package com.jdbc.beans;

public class Users {

    private Integer id;
    private String uname;
    private String upwd;
    private Integer ustatus;
    private Double uscore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Double getUscore() {
        return uscore;
    }

    public void setUscore(Double uscore) {
        this.uscore = uscore;
    }
}
