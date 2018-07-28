package com.simple.pojo;

import java.util.Date;

public class Data {
    private Integer id;

    private Integer uuid;

    private Integer number;

    private String personnel;

    private Date createTime;

    private Date closeTime;

    public Data(Integer id, Integer uuid, Integer number, String personnel, Date createTime, Date closeTime) {
        this.id = id;
        this.uuid = uuid;
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

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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