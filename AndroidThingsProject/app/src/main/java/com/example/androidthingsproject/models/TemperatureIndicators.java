package com.example.androidthingsproject.models;

/**
 * Created by aiachimov on 6/26/17.
 */

public class TemperatureIndicators {

    private String qrCodeId;
    private String time;
    private String type;
    private String value;

    public TemperatureIndicators() {
    }

    public TemperatureIndicators(String qrCodeId, String time, String type, String value) {
        this.qrCodeId = qrCodeId;
        this.time = time;
        this.type = type;
        this.value = value;
    }

    public String getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(String qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
