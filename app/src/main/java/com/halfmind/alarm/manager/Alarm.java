package com.halfmind.alarm.manager;

/*import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;*/
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Daniel on 08/03/2017.
 */

public class Alarm {
    public void newGraduAlarm(Calendar current, Calendar goal/*, Context context, PendingIntent alarmIntent*/){
        //AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        int daysBetween = daysBetween(current,goal);
        int minutesBetween = minutesBetweenAlarmLinear(current,goal);
        int timeBetweenEvent[] = minutesPerTime(minutesBetween,daysBetween);
        System.out.println("Dias para la meta: " + daysBetween);
        System.out.println("Minutos para la meta: "+ minutesBetween);
        for(int i=0;i<timeBetweenEvent.length; i++)
        {
            System.out.println("Minutos entre eventos dia " + i + ": "+ timeBetweenEvent[i]);
            current.add(Calendar.DAY_OF_MONTH,1);
            current.add(Calendar.MINUTE,timeBetweenEvent[i]);
            System.out.println((current.getTime()));

        }
        /*
        for(int i=0;i<daysBetween;i++){
            manager.set(AlarmManager.RTC_WAKEUP,current.getTimeInMillis(),alarmIntent);
        }*/




    }
    public int[] minutesPerTime (int minutes, int times){
        int minutesPerTime[] = new int[times];
        int minutesBetweenEvent = minutes/times;
        int minutesNotIncluded = Math.abs(minutes)%times;
        for (int i=0; i<times; i++){
            minutesPerTime[i] = minutesBetweenEvent;
            if (times-i == minutesNotIncluded) {
                if(minutes<0){
                    minutesPerTime[i] -= 1;
                }else {
                    minutesPerTime[i] += 1;
                }
            }

        }
        return  minutesPerTime;
    }
    public int minutesBetweenAlarmLinear(Calendar firstAlarm, Calendar secondAlarm){
        int firstTimeHour = firstAlarm.get(Calendar.HOUR_OF_DAY);
        int firstTimeMinutes = firstAlarm.get(Calendar.MINUTE);
        int secondTimeHour = secondAlarm.get(Calendar.HOUR_OF_DAY);
        int secondTimeMinutes = secondAlarm.get(Calendar.MINUTE);
        //Convert the times to minutes
        firstTimeMinutes = firstTimeHour * 60 + firstTimeMinutes;
        secondTimeMinutes = secondTimeHour * 60 + secondTimeMinutes;
        int minutesBetweenAlarm = secondTimeMinutes-firstTimeMinutes;
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
        Calendar calendar1= new GregorianCalendar(2017,2,25,8,30);
        Calendar calendar2 = new GregorianCalendar(2017,3,15,7,45);
        calendar1.add(Calendar.DAY_OF_MONTH,10);
        Alarm myAlarm = new Alarm();
        myAlarm.newGraduAlarm(calendar1,calendar2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        System.out.println(sdf.format(calendar1.getTime()));

    }
}
