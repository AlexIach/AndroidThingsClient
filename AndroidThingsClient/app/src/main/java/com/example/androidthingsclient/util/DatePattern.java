package com.example.androidthingsclient.util;

import com.example.androidthingsclient.R;

/**
 * Created by aiachimov on 8/9/17.
 */

public enum DatePattern {

    TIME(R.string.common_time_format, "HH:mm"),

    TIME_AM_PM(R.string.common_time_format_am_pm, "hh:mm a"),

    DATE(R.string.common_date_format_long, "dd MMM yyyy"),

    DATE_WITHOUT_YEAR(R.string.common_date_format_long, "dd MMM"),

    DATE_WITH_WEEK_DAY(R.string.common_date_format_full, "EEE dd MMM yyyy");

    final int patternStringRes;

    final String defaultPattern;

    DatePattern(int patternStringRes, String defaultPattern) {
        this.patternStringRes = patternStringRes;
        this.defaultPattern = defaultPattern;
    }
}
