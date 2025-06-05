package com.alibaba.cloud.ai.example.outparser.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"date", "city", "temperature", "condition"}) // 指定属性的顺序
public class WeatherEntity {
    private String date;
    private String city;
    private String temperature;
    private String condition;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public WeatherEntity() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", temperature='" + temperature + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
