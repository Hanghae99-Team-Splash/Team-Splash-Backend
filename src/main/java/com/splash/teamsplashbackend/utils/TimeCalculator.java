package com.splash.teamsplashbackend.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeCalculator {
    public static String timecalculator(LocalDateTime modifiedAt) {
        long timeDifference = Duration.between(modifiedAt, LocalDateTime.now()).getSeconds();
        String calculation = "";
        if ((timeDifference / 60 / 60 / 24) > 0) {
            calculation = timeDifference / 60 / 60 / 24 + "days ago";
        }
        else if ((timeDifference / 60 / 60) > 0) {
            calculation = timeDifference / 60 / 60 + "hours ago";
        }
        else if ((timeDifference / 60) > 0) {
            calculation = timeDifference / 60 + "minutes ago";
        }
        else {
            calculation = timeDifference + "seconds ago";
        }
        return calculation;
    }

}
