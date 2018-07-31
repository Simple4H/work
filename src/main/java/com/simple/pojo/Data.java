package com.simple.pojo;

import java.util.Date;

public class Data {
    private Integer id;

    private String number;

    private String personnel;

    private Date createTime;

    private Date closeTime;

    public Data(Integer id, String number, String personnel, Date createTime, Date closeTime) {
        this.id = id;
        this.number = number;
        this.personnel = personnel;
        this.createTime = createTime;
        this.closeTime = closeTime;
    }

    public Data() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel == null ? null : personnel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}