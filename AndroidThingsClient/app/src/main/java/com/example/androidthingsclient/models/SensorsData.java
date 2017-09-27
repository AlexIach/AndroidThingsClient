package com.example.androidthingsclient.models;

import java.util.List;

/**
 * Created by aiachimov on 6/20/17.
 */

public class SensorsData {
    private List<HumidityIndicators> humidityIndicators;
    private List<PressureIndicators> pressureIndicators;
    private List<TemperatureIndicators> temperatureIndicators;
    private boolean isBathRoomLightEnable;
    private boolean isKitchenLightEnable;
    private boolean isMainRoomLightEnable;

    public SensorsData() {
    }

    public SensorsData(List<HumidityIndicators> humidityIndicators,
                       List<PressureIndicators> pressureIndicators,
                       List<TemperatureIndicators> temperatureIndicators,
                       boolean isBathRoomLightEnable, boolean isKitchenLightEnable,
                       boolean isMainRoomLightEnable) {
        this.humidityIndicators = humidityIndicators;
        this.pressureIndicators = pressureIndicators;
        this.temperatureIndicators = temperatureIndicators;
        this.isBathRoomLightEnable = isBathRoomLightEnable;
        this.isKitchenLightEnable = isKitchenLightEnable;
        this.isMainRoomLightEnable = isMainRoomLightEnable;
    }

    public List<HumidityIndicators> getHumidityIndicators() {
        return humidityIndicators;
    }

    public void setHumidityIndicators(List<HumidityIndicators> humidityIndicators) {
        this.humidityIndicators = humidityIndicators;
    }

    public List<PressureIndicators> getPressureIndicators() {
        return pressureIndicators;
    }

    public void setPressureIndicators(List<PressureIndicators> pressureIndicators) {
        this.pressureIndicators = pressureIndicators;
    }

    public List<TemperatureIndicators> getTemperatureIndicators() {
        return temperatureIndicators;
    }

    public void setTemperatureIndicators(List<TemperatureIndicators> temperatureIndicators) {
        this.temperatureIndicators = temperatureIndicators;
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