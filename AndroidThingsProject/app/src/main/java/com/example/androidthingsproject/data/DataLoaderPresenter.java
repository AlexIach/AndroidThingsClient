package com.example.androidthingsproject.data;

import android.content.Context;

import com.example.androidthingsproject.R;
import com.example.androidthingsproject.models.HumidityIndicators;
import com.example.androidthingsproject.models.PressureIndicators;
import com.example.androidthingsproject.models.TemperatureIndicators;

import java.util.Random;

/**
 * Created by aiachimov on 10/6/17.
 */

public class DataLoaderPresenter {

    private static final String QR_CODE_ID = "raspberry_one";

    private static int LOWER_LIMIT_OF_HUMIDITY = 30;
    private static int NORMAL_LIMIT_OF_HUMIDITY = 60;

    private static int LOWER_LIMIT_OF_TEMPERATURE = 15;
    private static int NORMAL_LIMIT_OF_TEMPERATURE = 25;

    private static int LOWER_LIMIT_OF_PRESSURE = 700;
    private static int NORMAL_LIMIT_OF_PRESSURE = 800;

    public Context context;

    public DataLoaderPresenter(Context context) {
        this.context = context;
    }

    public HumidityIndicators getHumidityIndicators() {
        HumidityIndicators humidityIndicators = new HumidityIndicators();
        humidityIndicators.setQrCodeId(QR_CODE_ID);
        humidityIndicators.setTime(getCurrentTimeInTMPS());
        humidityIndicators.setValue(simulateHumidity());// TODO logic to get humidity value from sensor
        humidityIndicators.setType(getTypeByValueAndSensor(humidityIndicators.getValue(), context.getString(R.string.humidity)));

        return humidityIndicators;
    }

    public TemperatureIndicators getTemperatureIndicators() {
        TemperatureIndicators temperatureIndicators = new TemperatureIndicators();
        temperatureIndicators.setQrCodeId(QR_CODE_ID);
        temperatureIndicators.setTime(getCurrentTimeInTMPS());
        temperatureIndicators.setValue(simulateTemperature());// TODO logic to get temperature value from sensor
        temperatureIndicators.setType(getTypeByValueAndSensor(temperatureIndicators.getValue(), context.getString(R.string.temperature)));

        return temperatureIndicators;
    }

    public PressureIndicators getPressureIndicators() {
        PressureIndicators pressureIndicators = new PressureIndicators();
        pressureIndicators.setQrCodeId(QR_CODE_ID);
        pressureIndicators.setTime(getCurrentTimeInTMPS());
        pressureIndicators.setValue(simulatePressure());// TODO logic to get pressure value from sensor
        pressureIndicators.setType(getTypeByValueAndSensor(pressureIndicators.getValue(), context.getString(R.string.pressure)));

        return pressureIndicators;
    }

    private String getTypeByValueAndSensor(String value, String sensor) {
        String type;

        int convertedValue = Integer.parseInt(value);

        if (sensor.equalsIgnoreCase(context.getString(R.string.humidity))) {
            if (convertedValue <= LOWER_LIMIT_OF_HUMIDITY) {
                type = context.getString(R.string.humidity_type_low);
            } else if (convertedValue > LOWER_LIMIT_OF_HUMIDITY && convertedValue <= NORMAL_LIMIT_OF_HUMIDITY) {
                type = context.getString(R.string.humidity_type_normal);
            } else {
                type = context.getString(R.string.humidity_type_high);
            }

        } else if (sensor.equalsIgnoreCase(context.getString(R.string.temperature))) {
            if (convertedValue <= LOWER_LIMIT_OF_TEMPERATURE) {
                type = context.getString(R.string.temperature_type_cold);
            } else if (convertedValue > LOWER_LIMIT_OF_TEMPERATURE && convertedValue <= NORMAL_LIMIT_OF_TEMPERATURE) {
                type = context.getString(R.string.temperature_type_normal);
            } else {
                type = context.getString(R.string.temperature_type_hot);
            }

        } else {
            if (convertedValue <= LOWER_LIMIT_OF_PRESSURE) {
                type = context.getString(R.string.pressure_type_low);
            } else if (convertedValue > LOWER_LIMIT_OF_PRESSURE && convertedValue <= NORMAL_LIMIT_OF_PRESSURE) {
                type = context.getString(R.string.pressure_type_normal);
            } else {
                type = context.getString(R.string.pressure_type_high);
            }
        }
        return type;
    }

    private String getCurrentTimeInTMPS() {
        Long timeStamp = System.currentTimeMillis() / 1000;
        return timeStamp.toString();
    }


    private String simulateTemperature() {
        Random random = new Random(System.currentTimeMillis());
        return Integer.toString(random.nextInt((25 - 23) + 1) + 23);
    }

    private String simulateHumidity() {
        Random random = new Random(System.currentTimeMillis());
        return Integer.toString(random.nextInt((55 - 50) + 1) + 50);
    }

    private String simulatePressure() {
        Random random = new Random(System.currentTimeMillis());
        return Integer.toString(random.nextInt((760 - 750) + 1) + 750);
    }
}
