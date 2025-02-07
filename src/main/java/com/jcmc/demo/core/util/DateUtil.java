package com.jcmc.demo.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static final int MOUNTH = 12;
    public static final int DAYS_OF_WEEK = 7;
    public static final int DAY_MILLISECONDS = 86400000;
    public static final int MILISECONDS = 1000;
    public static final String FORMAT_DDSMMSYYYYDHHCMMCSS = "dd/MM/yyyy.hh:mm:ss";
    public static final String FORMAT_DDSMMSYYYYSHHCMMCSS = "dd/MM/yyyy hh:mm:ss";
    public static final String FORMAT_DDSMMSYYYYSHH24CMMCSS = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DDSMMSYYYY = "dd/MM/yyyy";
    public static final String FORMAT_DDMMYYYY = "ddMMyyyy";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_DDDMMDYYYY = "dd-MM-yyyy";
    public static final String FORMAT_YYYYDMMDDDHHMMSS = "yyyy-MM-dd hh:mm:ss";
    private static final String TIME_FORMAT = "hh:mm aa";
    public static final String FORMAT_YYYYMMDDHHMMSSSS = "yyyy-MM-dd-HHmmssSS";

    private DateUtil() {
        // Constructor default
    }

    public static String formatDate(Date date, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            return format.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date toDate(String date, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            return format.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static int compare(String date1, String date2, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return compare(format.parse(date1), format.parse(date2));
        } catch (ParseException ex) {
            return 0;
        }
    }

    public static int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    public static Date getDateToString(String date, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Integer diffMounths(Date dateInit, Date dateEnd) {
        Calendar dateInitCalendar = new GregorianCalendar();
        Calendar dateEndCalendar = new GregorianCalendar();
        dateInitCalendar.setTime(dateInit);
        dateEndCalendar.setTime(dateEnd);
        Integer difM = null;
        Integer difA = null;
        try {
            difA = dateEndCalendar.get(Calendar.YEAR) - dateInitCalendar.get(Calendar.YEAR);
            difM = difA * MOUNTH + dateEndCalendar.get(Calendar.MONTH) - dateInitCalendar.get(Calendar.MONTH);
        } catch (Exception ex) {
            return null;
        }
        return difM;
    }

    public static Integer diffWeeks(Date dateInit, Date dateEnd) {
        try {
            long diff = dateEnd.getTime() - dateInit.getTime();
            int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            return days / DAYS_OF_WEEK;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Integer diffQuincenas(Date dateInit, Date dateEnd) {
        try {
            Calendar dateInitCalendar = new GregorianCalendar();
            Calendar dateEndCalendar = new GregorianCalendar();
            dateInitCalendar.setTime(dateInit);
            dateEndCalendar.setTime(dateEnd);
            int difA = dateEndCalendar.get(Calendar.YEAR) - dateInitCalendar.get(Calendar.YEAR);
            int difM = difA * MOUNTH + dateEndCalendar.get(Calendar.MONTH) - dateInitCalendar.get(Calendar.MONTH);
            return difM * 2;
        } catch (Exception ex) {
            return null;
        }
    }

    public static int compareDate(Date date1, Date date2) {
        return date2.compareTo(date1);
    }

    public static Date addDays(Date date, Integer days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static boolean validateFormat(String date) {
        return validateFormat(date, null);
    }

    public static boolean validateFormat(String date, String format) {
        String pattern = FORMAT_DDSMMSYYYY;
        if (format != null) {
            pattern = format;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Integer getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Integer getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static String getTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        return dateFormat.format(date);
    }

    public static String getMonthStr(Date date) {
        Integer month = getMonth(date);

        switch (month) {
            case 1:
                return "Ene";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Abr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Ago";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dic";
            default :
                return "";
        }
    }
}


