package com.simple.pojo;

import java.util.Date;

public class Detail {
    private Integer id;

    private String dataNumber;

    private Integer times;

    private String temperature;

    private String weight;

    private Date createTime;

    public Detail(Integer id, String dataNumber, Integer times, String temperature, String weight, Date createTime) {
        this.id = id;
        this.dataNumber = dataNumber;
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

    public String getDataNumber() {
        return dataNumber;
    }

    public void setDataNumber(String dataNumber) {
        this.dataNumber = dataNumber == null ? null : dataNumber.trim();
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