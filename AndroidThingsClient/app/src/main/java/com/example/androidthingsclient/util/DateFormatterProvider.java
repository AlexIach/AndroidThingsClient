package com.example.androidthingsclient.util;

import android.content.Context;
import android.util.Log;

import com.example.androidthingsclient.R;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by aiachimov on 8/9/17.
 */
@Singleton
public class DateFormatterProvider {

    private static final String LOG_TAG = DateFormatterProvider.class.getSimpleName();

    private Map<DatePattern, DateTimeFormatter> standardFormatters;

    private Map<String, DateTimeFormatter> userFormatters;

    private PeriodFormatter periodFormatter;

    @Inject
    public DateFormatterProvider(Context context) {
        userFormatters = new HashMap<>();
        standardFormatters = new HashMap<>();

        initPeriodFormatter(context);
        initStandardFormatters(context);
    }

    private void initPeriodFormatter(Context context) {
        String day = context.getString(R.string.mytrips_flight_details_day);
        String hour = context.getString(R.string.mytrips_flight_details_hour);
        String min = context.getString(R.string.mytrips_flight_details_minutes);
        String week = context.getString(R.string.weeks);

        periodFormatter = new PeriodFormatterBuilder()
                .appendWeeks().appendSuffix(week).appendSuffix(" ")
                .appendDays().appendSuffix(day).appendSeparator(" ")
                .appendHours().appendSuffix(hour).appendSeparator(" ")
                .appendMinutes().appendSuffix(min).toFormatter();
    }

    private void initStandardFormatters(Context context) {
        for (DatePattern pattern : EnumSet.allOf(DatePattern.class)) {
            String patternString = context.getString(pattern.patternStringRes);
            standardFormatters.put(pattern, createFormatter(patternString, pattern.defaultPattern));
        }
    }

    private DateTimeFormatter createFormatter(String pattern, String defaultFormat) {
        try {
            return DateTimeFormat.forPattern(pattern);
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Could not instantiate formatter (" + pattern + "), switching to given default ("
                    + defaultFormat + ")", e);
            return DateTimeFormat.forPattern(defaultFormat);
        }
    }

    public PeriodFormatter periodFormatter() {
        return periodFormatter;
    }

    /**
     * @return {@link DateTimeFormatter} that will print in given pattern format
     */
    public DateTimeFormatter formatterFor(DatePattern pattern) {
        return standardFormatters.get(pattern);
    }

    /**
     * @return {@link DateTimeFormatter} that will print in given pattern format
     */
    public DateTimeFormatter formatterFor(String pattern) {
        if (!userFormatters.containsKey(pattern)) {
            userFormatters.put(pattern, DateTimeFormat.forPattern(pattern));
        }

        return userFormatters.get(pattern);
    }
}
