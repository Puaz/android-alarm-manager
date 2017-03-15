package com.halfmind.alarm.manager;

import android.app.AlarmManager;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Daniel on 08/03/2017.
 */

public class Alarm {
    public void newGraduAlarm(Calendar current, Calendar goal, Context context){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        int daysBetween = daysBetween(current,goal);
        for(int i=0;i<daysBetween;i++){

        }




    }
    public int minutesBetweenAlarmLinear(Calendar firstAlarm, Calendar secondAlarm){

        int firstTimeHour = firstAlarm.get(Calendar.HOUR_OF_DAY);
        int firstTimeMinutes = firstAlarm.get(Calendar.MINUTE);
        int secondTimeHour = secondAlarm.get(Calendar.HOUR_OF_DAY);
        int secondTimeMinutes = secondAlarm.get(Calendar.MINUTE);
        //Convert the times to minutes
        firstTimeMinutes = firstTimeHour * 60 + firstTimeMinutes;
        secondTimeMinutes = secondTimeHour * 60 + secondTimeMinutes;
        int minutesBetweenAlarm = firstTimeMinutes-secondTimeMinutes;
        return minutesBetweenAlarm;
    }
    public int daysBetween(Calendar firstDate, Calendar secondDate){
        int remainigDays;
        int firstYear = firstDate.get(Calendar.YEAR);
        int secondYear = secondDate.get(Calendar.YEAR);
        if(firstYear == secondYear){
            remainigDays = secondDate.get(Calendar.DAY_OF_YEAR) - firstDate.get(Calendar.DAY_OF_YEAR);
        }else{
            //Calculate remaining days in the firstDate year
            int remainingDaysInYear = 365 - firstDate.get(Calendar.DAY_OF_YEAR);
            GregorianCalendar leapHelper = new GregorianCalendar();
            if (leapHelper.isLeapYear(firstDate.get(Calendar.YEAR)))
                remainingDaysInYear++;
            //Calculate elapsed days in the secondDate year
            int elapsedDays = secondDate.get(Calendar.DAY_OF_YEAR);
            remainigDays = remainingDaysInYear + elapsedDays;
        }
        return remainigDays;
    }

    //main for testing porpuoses
    public static void main(String[] args){
        Calendar calendar1= new GregorianCalendar(2017,2,25);
        Calendar calendar2 = new GregorianCalendar(2017,2,15);
        calendar1.add(Calendar.DAY_OF_MONTH,10);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        System.out.println(sdf.format(calendar1.getTime()));

    }
}
