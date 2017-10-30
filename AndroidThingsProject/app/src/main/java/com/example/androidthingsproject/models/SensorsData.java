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
  private boolean isSmokeExist;

  public SensorsData() {
  }

  public SensorsData(HashMap<String, HumidityIndicators> humidityIndicators, HashMap<String, PressureIndicators> pressureIndicators, HashMap<String, TemperatureIndicators> temperatureIndicators, boolean isBathRoomLightEnable, boolean isKitchenLightEnable, boolean isMainRoomLightEnable, boolean isSmokeExist) {
    this.humidityIndicators = humidityIndicators;
    this.pressureIndicators = pressureIndicators;
    this.temperatureIndicators = temperatureIndicators;
    this.isBathRoomLightEnable = isBathRoomLightEnable;
    this.isKitchenLightEnable = isKitchenLightEnable;
    this.isMainRoomLightEnable = isMainRoomLightEnable;
    this.isSmokeExist = isSmokeExist;
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

  public void setBathRoomLightEnable(boolean bathRoomLightEnable) {
    isBathRoomLightEnable = bathRoomLightEnable;
  }

  public boolean isKitchenLightEnable() {
    return isKitchenLightEnable;
  }

  public void setKitchenLightEnable(boolean kitchenLightEnable) {
    isKitchenLightEnable = kitchenLightEnable;
  }

  public boolean isMainRoomLightEnable() {
    return isMainRoomLightEnable;
  }

  public void setMainRoomLightEnable(boolean mainRoomLightEnable) {
    isMainRoomLightEnable = mainRoomLightEnable;
  }

  public boolean getIsBathRoomLightEnable() {
    return isBathRoomLightEnable;
  }

  public boolean getIsKitchenLightEnable() {
    return isKitchenLightEnable;
  }

  public boolean getIsMainRoomLightEnable() {
    return isMainRoomLightEnable;
  }

  public boolean isSmokeExist() {
    return isSmokeExist;
  }

  public void setSmokeExist(boolean smokeExist) {
    isSmokeExist = smokeExist;
  }
}