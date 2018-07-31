package com.simple.pojo;

import java.util.Date;

public class Detail {
    private Integer id;

    private Integer dataId;

    private Integer times;

    private String temperature;

    private String weight;

    private Date createTime;

    public Detail(Integer id, Integer dataId, Integer times, String temperature, String weight, Date createTime) {
        this.id = id;
        this.dataId = dataId;
        this.times = times;
        this.temperature = temperature;
        this.weight = weight;
        this.createTime = createTime;
    }

    public Detail() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}