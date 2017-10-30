package com.example.androidthingsproject.utils;

/**
 * Created by aiachimov on 10/5/17.
 */

public class Bmp180Data {

  private int altitude;
  private long pressure;
  private int temperature;

  public Bmp180Data(int altitude, long pressure, int temperature) {
    this.altitude = altitude;
    this.pressure = pressure;
    this.temperature = temperature;
  }

  public int getAltitude() {
    return altitude;
  }

  public void setAltitude(int altitude) {
    this.altitude = altitude;
  }

  public long getPressure() {
    return pressure;
  }

  public void setPressure(long pressure) {
    this.pressure = pressure;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
