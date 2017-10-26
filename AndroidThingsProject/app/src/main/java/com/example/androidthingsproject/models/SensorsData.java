package com.example.androidthingsproject.models;

import java.util.HashMap;

/**
 * Created by aiachimov on 6/20/17.
 */

public class SensorsData {
    private HashMap<String, HumidityIndicators> humidityIndicators;
    private HashMap<String, PressureIndicators> pressureIndicators;
    private HashMap<String, TemperatureIndicators> temperatureIndicators;
    private boolean isBathRoomLightEnable;
    private boolean isKitchenLightEnable;
    private boolean isMainRoomLightEnable;

    public SensorsData() {
    }

    public SensorsData(HashMap<String, HumidityIndicators> humidityIndicators, HashMap<String, PressureIndicators> pressureIndicators, HashMap<String, TemperatureIndicators> temperatureIndicators, boolean isBathRoomLightEnable, boolean isKitchenLightEnable, boolean isMainRoomLightEnable) {
        this.humidityIndicators = humidityIndicators;
        this.pressureIndicators = pressureIndicators;
        this.temperatureIndicators = temperatureIndicators;
        this.isBathRoomLightEnable = isBathRoomLightEnable;
        this.isKitchenLightEnable = isKitchenLightEnable;
        this.isMainRoomLightEnable = isMainRoomLightEnable;
    }

    public HashMap<String, HumidityIndicators> getHumidityIndicators() {
        return humidityIndicators;
    }

    public void setHumidityIndicators(HashMap<String, HumidityIndicators> humidityIndicators) {
        this.humidityIndicators = humidityIndicators;
    }

    public HashMap<String, PressureIndicators> getPressureIndicators() {
        return pressureIndicators;
    }

    public void setPressureIndicators(HashMap<String, PressureIndicators> pressureIndicators) {
        this.pressureIndicators = pressureIndicators;
    }

    public HashMap<String, TemperatureIndicators> getTemperatureIndicators() {
        return temperatureIndicators;
    }

    public void setTemperatureIndicators(HashMap<String, TemperatureIndicators> temperatureIndicators) {
        this.temperatureIndicators = temperatureIndicators;
    }

    public boolean isBathRoomLightEnable() {
        return isBathRoomLightEnable;
    }

    public boolean isKitchenLightEnable() {
        return isKitchenLightEnable;
    }

    public boolean isMainRoomLightEnable() {
        return isMainRoomLightEnable;
    }

    public boolean getIsBathRoomLightEnable() {
        return isBathRoomLightEnable;
    }

    public void setBathRoomLightEnable(boolean bathRoomLightEnable) {
        isBathRoomLightEnable = bathRoomLightEnable;
    }

    public boolean getIsKitchenLightEnable() {
        return isKitchenLightEnable;
    }

    public void setKitchenLightEnable(boolean kitchenLightEnable) {
        isKitchenLightEnable = kitchenLightEnable;
    }

    public boolean getIsMainRoomLightEnable() {
        return isMainRoomLightEnable;
    }

    public void setMainRoomLightEnable(boolean mainRoomLightEnable) {
        isMainRoomLightEnable = mainRoomLightEnable;
    }
}