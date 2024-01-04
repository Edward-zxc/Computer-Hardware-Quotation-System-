package com.jdbc.beans;

public class Cart {
    private Integer cid;
    private Integer goodsid;
    private Integer cnum;
    private Integer userid;
    private String gname;
    private Double gprice;
    private Double totalPrice;

    public Double getTotalPrice() {
        return gprice * cnum;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getCnum() {
        return cnum;
    }

    public void setCnum(Integer cnum) {
        this.cnum = cnum;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
