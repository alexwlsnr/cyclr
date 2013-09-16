package com.alexwlsnr.cycletimecalc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Hours;

import java.util.ArrayList;

/**
 * Created by alex on 16/09/13.
 */
public class CycleTimeUtils {


    public static int getCycleTime(DateTime startDate, DateTime endDate)
    {
        return getCycleTime(startDate, endDate, new ArrayList<DateTime>());
    }

    public static int getCycleTime(DateTime startDate, DateTime endDate, ArrayList<DateTime> nonWorkingDays)
    {

        int workDays = 0;
        int count = 0;
        while(!startDate.plusDays(count).toLocalDate().isEqual(endDate.toLocalDate()))
        {
            Boolean isNonworkingDay = false;
            for(DateTime nonWorkingDay: nonWorkingDays)
            {
                if(startDate.plusDays(count).toLocalDate().isEqual(nonWorkingDay.toLocalDate()))
                {
                    isNonworkingDay = true;
                }


            }
            if(!isWeekend(startDate.plusDays(count++)))
            {
                workDays++;
            }
        }


        int workHours = workDays * 8;
        int hourDiff = Hours.hoursBetween(startDate.plusDays(count), endDate).getHours();
        workHours += hourDiff > 8 ? 8 : hourDiff;




        return workHours;
    }


    public static boolean isWeekend(DateTime dateTime)
    {
        return (DateTimeConstants.SATURDAY == dateTime.dayOfWeek().get() || DateTimeConstants.SUNDAY == dateTime.dayOfWeek().get());
    }
}
