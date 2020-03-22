package com.ssm.activity.Utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static final Logger log=LoggerFactory.getLogger(DateUtils.class);
	  /**
     * 默认日期格式
     */
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd_HH-mm-ss";

    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    public static final String YYYYMMDDHHMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String YYYYMMddHHmmss = "yyyyMMddHHmmss";
    
    /**默认时间格式：yyyy-MM-dd HH:mm:ss */
    public static final SimpleDateFormat default_format = new SimpleDateFormat(YYYYMMDDHHMMSS);
    /**默认时间格式：yyyy-MM-dd HH:mm:ss */
    public static final SimpleDateFormat format_MM_DD = new SimpleDateFormat("MM-dd");
    /**日期时间格式：yyyy-MM-dd*/
    public static final SimpleDateFormat format_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    /**yyyyMMdd*/
    public static final SimpleDateFormat format_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    /**yyyyMM*/
    public static final SimpleDateFormat format_YYYYMM = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat format_YYYY = new SimpleDateFormat("yyyy");

    /**yyMMddHHmmss*/
    public static final SimpleDateFormat format_YYMMDDHHMMSS = new SimpleDateFormat("yyMMddHHmmss");

    public static final SimpleDateFormat format_YYYYMMDD_HHMM = new SimpleDateFormat(YYYYMMDDHHMM);

    /**yyyyMMddHHmmss*/
    public static final SimpleDateFormat format_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
    /**yyyyMMddHHmmssSSS*/
    public static final SimpleDateFormat format_YYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final SimpleDateFormat format_YYYYMMDDHHMM = new SimpleDateFormat("yyyyMMddHHmm");
    public static final SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat format_YYYY_MM_DD_HH_MM_SS_SSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final SimpleDateFormat format_HH_MM_SS = new SimpleDateFormat("HH:mm:ss");

    public static final SimpleDateFormat format_HH_MM = new SimpleDateFormat("HH:mm");
    /** <默认构造函数>
     */
    private DateUtils()
    { }
    
    /** <字符串转换成日期>
     * <如果转换格式为空，则利用默认格式进行转换操作>
     * @param str 字符串
     * @param format 日期格式
     * @return 日期
     * @see [类、类#方法、类#成员]
     */
    public static Date str2Date(String str, String format){
        try {
			return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
			log.error(e.getMessage(),e);
		}
		return null;
    }
    
    public static Date parseDate(String dateStr) {
		return parseDate(dateStr, default_format);
	}

	public static String dateToShortStr(Date date){
        return format(date,format_YYYY_MM_DD);
    }
    
    public static Date parseDate(String dateStr,SimpleDateFormat format)  {
		return parse(dateStr,format);
	}

    public static String format(Date date) {
		return format(date, format_YYYY_MM_DD_HH_MM_SS);
	}
    
    /** 
     * @Description:格式化并返回当前时间
     */
    public static String formatNow() {
		return format(new Date(), format_YYYY_MM_DD_HH_MM_SS);
	}
    
    /** 
     * @Description:格式化并返回当前时间
     */
    public static String formatNow(SimpleDateFormat format) {
		return format(new Date(), format);
	}
    
    public static String format(Date date, SimpleDateFormat format) {
    	synchronized (format) {
    		return date==null?null:format.format(date);
		}
	}
    public static String formatDateTime(Date date, String format) {
    	return format(date, new SimpleDateFormat(format));
	}
    
    
    public static String formatDateTime(Date date, Date defaultDate, String format) {
    	return date!=null?formatDateTime(date, format):formatDateTime(defaultDate, format);
	}
    
    /** <时间戳转换为字符串>
     * <功能详细描述>
     * @param time
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String timestamp2Str(Timestamp time){
        return format(time, format_YYYY_MM_DD_HH_MM_SS);
    }
    
    /** <一句话功能简述>
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Timestamp str2Timestamp(String str){
        Date date = str2Date(str, YYYYMMDDHHMMSS);
        return date!=null?new Timestamp(date.getTime()):null;
    }
    
    /**
     * string装int的时间
     *
     * @param timeStr
     * @return
     */
    public static long getIntTimeByString(String timeStr) {
        Date date = parse(timeStr,default_format);
		return date!=null?(date.getTime() / 1000) :0;
    }

	/**
	 * 获取2个时间相隔几分钟
	 */
	public static int getBetweenMinuteNumber(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}
		if (startDate.after(endDate)) {
			Date tmp = endDate;
			endDate = startDate;
			startDate = tmp;
		}
		long timeNumber = -1l;
		long TIME = 60L * 1000L;
		try {
			timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return (int) timeNumber;
	}

   /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
    public static int differentHoursByMillisecond(Date date1,Date date2){
        int hours = (int) ((date2.getTime() - date1.getTime()) / (1000*3600));
        return hours;
    }


    /**
     * 字符串
     * 开始时间到结束时间的日期遍历
     * @param beginDay
     * @param endDay
     * @return
     */
    public static List<String> loopDate(String beginDay, String endDay) {

        if (beginDay == null || beginDay.trim().equals("") || endDay == null || endDay.trim().equals("")) {
            return null;
        }
        try{
            Date startDate = parse(beginDay,format_YYYY_MM_DD);
            Date endDate = parse(endDay,format_YYYY_MM_DD);
            return loopDate(startDate, endDate);
        }
        catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
    /**
     * 开始时间到结束时间的日期遍历
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> loopDate(Date startDate, Date endDate) {
        List<String> listDate = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        endDate=calendar.getTime();
        calendar.setTime(startDate);
        while(calendar.getTime().before(endDate)){
            listDate.add(format_YYYY_MM_DD.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return listDate;
    }

    public static String longDateToStr(Date date){
        return format_YYYYMMDDHHMMSS.format(date);
    }


    public static Date getDayOfEarliest(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.MILLISECOND,0);

        return cal.getTime();
    }

    public static Date getDayOfLatest(Date date) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.MILLISECOND,59);

        return cal.getTime();
    }

    public static Date parse(String str,SimpleDateFormat format) {
        try {
            synchronized (format) {
                return format.parse(str);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static Date parse(String str) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDDHHMMSS);
        try {
            synchronized (format) {
                return format.parse(str);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static String getDayString(Date day) {
        return format(day,format_YYYYMMDD);
    }

    public static int getMinutes(Date time){
        if(time == null) {
            return 0;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(time);
        return ca.get(Calendar.HOUR_OF_DAY) * 60 + ca.get(Calendar.MINUTE);
    }

    /** 23:59:59
     * @param date
     * @return
     */
    public static Date endTime(Date date){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);

        return ca.getTime();
    }



    public static long toUtc(Date dateTime){
        return dateTime.getTime() / 1000;
    }

    public static Date toDate(long second){
        return new Date( (long)second * 1000);
    }

    public static Date toDate(Date time){
        Calendar date = Calendar.getInstance();
        date.setTime(time);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTime();
    }

    public static Date dateNow(){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTime();
    }

    public static Date dateNowEnd(){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    public static Date dateStart(Date day){
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    public static Date dateEnd(Date day){
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    public static String dateStartStr(Date day){
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        Date time = date.getTime();
        return format(time,DateUtils.format_YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateEndStr(Date day){
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 0);
        Date time = date.getTime();
        return format(time,DateUtils.format_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 几天之前
     * @param x
     * @return
     */
    public static Date dateOfWeek(int x){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_MONTH, -x);
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }
    public static Date getMondayOfProWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week -6);
        return c.getTime();
    }

    /**
     * 获得周几
     * @param dateStr
     * @return
     */
    public static int dateOfWeek(String dateStr){

        Date date = parseDate(dateStr,format_YYYY_MM_DD);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        if (day_of_week == 1) {
            day_of_week = 8;
        }

        return day_of_week -= 1;
    }

    /**
     * 添加天数
     * @param date
     * @param days
     * @return
     */
    public static Date add(Date date, int days){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, days);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);

        return ca.getTime();
    }

    public static Date addDay(Date date,int days){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, days);

        return ca.getTime();
    }

    /**
     * 添加分钟
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE, minutes);

        return ca.getTime();
    }

    public static Date addSecond(Date date, int seconds){
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.SECOND, seconds);

        return ca.getTime();
    }


    public static Date dateNext(){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_MONTH, 1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTime();
    }

    public static Date datePrevious(){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_MONTH, -1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTime();
    }

    public static Date datePreviousEnd(){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.add(Calendar.DAY_OF_MONTH, -1);
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    public static int getHours(Date datetime){
        if(datetime == null) {
            return 0;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(datetime);

        return ca.get(Calendar.HOUR_OF_DAY);
    }

    public static Date toDate(String dateString) throws ParseException {
        if(dateString == null || dateString.length() < 1) {
            throw new ParseException("数据为null", 0);
        }

        return parse(dateString,format_YYYY_MM_DD);
    }

    public static Date toDateTime(String dateString) throws ParseException{
        if(dateString == null || dateString.length() < 1) {
            throw new ParseException("数据为null", 0);
        }
        return parse(dateString,default_format);
    }

    /**
     * "Sep 29, 2012 1:00:01 AM"格式转换
     * @param dateTimeString
     * @return
     * @throws ParseException
     */
    public static Date toDateTimeByLocal(String dateTimeString) throws ParseException{
        if(dateTimeString == null || dateTimeString.length() < 1) {
            throw new ParseException("数据为null", 0);
        }

        SimpleDateFormat dateStringFormart = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
        return dateStringFormart.parse(dateTimeString);
    }

    public static Date convert(int minutes){
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.set(Calendar.HOUR_OF_DAY, minutes / 60);
        date.set(Calendar.MINUTE, minutes % 60);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTime();
    }

    public static Date convert(Date day, int minutes){
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, minutes / 60);
        date.set(Calendar.MINUTE, minutes % 60);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        return date.getTime();
    }

    public static String toDateTimeString(Date dateTime){
        if(dateTime == null) {
            return null;
        }

        return format(dateTime,default_format);
    }

    public static String toDateString(Date date){
        if(date == null) {
            return null;
        }

        SimpleDateFormat dateFormart = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormart.format(date);
    }

    public static int getSeconds(Date time){
        if(time == null) {
            return 0;
        }

        return (int)(time.getTime() / 1000);
    }

    /**
     * 获取秒
     * @param begintime
     * @param endtime
     * @return
     */
    public static int getSeconds(Date begintime, Date endtime){
        if(begintime == null || endtime == null) {
            return 0;
        }

        return (int)((endtime.getTime() - begintime.getTime()) / 1000);
    }

    public static String toDateTimeString(String format, Date datetime){
        if(datetime == null) {
            return "";
        }

        SimpleDateFormat dateFormart;
        if(format == null || format.length() < 1) {
            dateFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            dateFormart = new SimpleDateFormat(format);
        }

        return dateFormart.format(datetime);
    }

    public static String getDateTimeString(Date dateTime){
        if(dateTime == null) {
            return null;
        }

        SimpleDateFormat datetimeFormart = new SimpleDateFormat("HH:mm");
        return datetimeFormart.format(dateTime);
    }

    public static Date tomorrow(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();
    }
  /**
  * 返回一个时间移动多少分钟后的结果
  * @param date 时间
  * @param minutes 移动的分钟数(正数加负数减)
  * @return
  */
 public static String moveMinutes(Date date, int minutes) {
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(date);
     calendar.add(Calendar.MINUTE, minutes);
     return format(calendar.getTime());
 }

    /**
	 * @param date
	 * @param minutes
	 * @param multiple 倍数
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes,int multiple){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int rec=ca.get(Calendar.MINUTE)%multiple;
		ca.add(Calendar.MINUTE, minutes-rec);
		
		return ca.getTime();
	}
	
  public static String getHourMinuts(Date day){
	  String returnStr = "";
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(day);
	  int hour = cal.get(Calendar.HOUR_OF_DAY);
	  int minus = cal.get(Calendar.MINUTE);
	  if (hour < 10) {
	      returnStr += ("0" + hour);
	  } else {
	      returnStr += hour;
	  }
	  returnStr += ":";
	  if (minus < 10) {
	      returnStr += ("0" + minus);
	  } else {
	      returnStr += minus;
	  }
	  return returnStr;
	}
  /**
	 * 获取一年的开始时间
	 * @param year 年份
	 * @return
	 */
	public static String getYearStartTime(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		return format(calendar.getTime(),format_YYYY_MM_DD);
	}
	
	/**
	 * 获取一年的开始时间
	 * @param year 年份
	 * @return
	 */
	public static String getYearEndTime(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return format(calendar.getTime(),format_YYYY_MM_DD);
	}

    /**
     * 将小时分的字符串转换为分钟数
     * @param time
     * @return
     */
    public static int hourToMinute(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minuts = Integer.parseInt(times[1]);
        return hour * 60 + minuts;
    }

    /**
     * 将分钟数转换为小时分的字符串
     * @param minute
     * @return
     */
    public static String minuteToHour(int minute) {
        int hour = minute / 60;
        int minutes = minute % 60;
        String hourStr = "";
        String minutesStr = "";
        if (hour < 10) {
            hourStr = "0" + hour;
        } else {
            hourStr = "" + hour;
        }
        if (minutes < 10) {
            minutesStr = "0" + minutes;
        } else {
            minutesStr = "" + minutes;
        }
        return hourStr + ":" + minutesStr;
    }

    /**
     * 传入一个时间，获取该时间是当天的第多少分钟
     *
     * @param date
     * @return
     */
    public static int getTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minus = cal.get(Calendar.MINUTE);
        return hour * 60 + minus;
    }
    /**
     * 整形转时期格式
     * @param seconds
     * @return
     */
    public static Date parseIntToDate(long seconds) {
    	return new Date(seconds*1000);
    }

    public static String secondsFormatStr(long secondsLong) {

        int seconds = (int)(secondsLong/1000);
        int hour = seconds / 3600;
        int minuts = (seconds % 3600) / 60;
        int second = ((seconds % 3600) % 60);
        String returnstr = "";
        if (hour < 10) {
            returnstr += "0" + hour + ":";
        } else {
            returnstr += hour + ":";
        }
        if (minuts < 10) {
            returnstr += "0" + minuts + ":";
        } else {
            returnstr += minuts + ":";
        }
        if (second < 10) {
            returnstr += "0" + second;
        } else {
            returnstr += "" + second;
        }
        return returnstr;
    }
    
    public static String date2DeviceTime(Date time){
        return format(time, format_YYMMDDHHMMSS);
    }
    
    public static Date deviceTime2Date(String timeString){
        return parse(timeString, format_YYMMDDHHMMSS);
    }
    /**
     * 	获取年月日
     * @param date
     * @return
     */
    public static String getDay(Date date){
		return format(date,format_YYYY_MM_DD);
	}

    public static int getDayOfMonth(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取某一天的前一天的某个时段的开始结束时间
     * @param day
     * @param timeNumber
     * @return
     */
    public static ArrayList<String> getTimeNumberStartEndTime(Date day, int timeNumber, int dayFor){
        ArrayList<String> returnList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, dayFor);
        cal.set(Calendar.HOUR_OF_DAY, timeNumber - 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date start = cal.getTime();
        String startTime = default_format.format(start);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date end = cal.getTime();
        String endTime = default_format.format(end);
        returnList.add(startTime);
        returnList.add(endTime);
        return returnList;
    }
    public static String getNextMonth(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
        if (cal.get(Calendar.MONTH)+1 <10){
            return cal.get(Calendar.YEAR)+"0"+(cal.get(Calendar.MONTH)+1);
        }else{
            return cal.get(Calendar.YEAR)+""+(cal.get(Calendar.MONTH)+1);
        }
    }

    public static String getThisMonth(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        if (cal.get(Calendar.MONTH)+1 <10){
            return cal.get(Calendar.YEAR)+"0"+(cal.get(Calendar.MONTH)+1);
        }else{
            return cal.get(Calendar.YEAR)+""+(cal.get(Calendar.MONTH)+1);
        }
    }

    /**
     * 获取毫秒数
     * @param str 字符串类型EG：2018-01-01
     * @return
     */
    public static long getSecond(String str){
        Date day = parseDate(str,format_YYYY_MM_DD);
        return day.getTime();
    }

    /**
     * 字符串组装：获取两个时间的小时分然后字符串连接在一起
     * @param sendTime
     * @param arriveTime
     * @return
     */
    public static String getTimeIntervalStr(Date sendTime, Date arriveTime) {
        String send = format_HH_MM.format(sendTime);
        String arrive = format_HH_MM.format(arriveTime);
        return send + "-" + arrive;
    }

    /**
     * 根据时间间隔获取一天内一组起止时间，时间单位默认都是小时，24小时制
     * @param startHour 开始时间小时
     * @param endHour 结束时间小时
     * @param interval 时间间隔小时
     * @return
     */
    public static HashMap<Integer, Integer> getTimeIntervalMap(int startHour, int endHour, int interval) {
        HashMap<Integer, Integer> returnMap = new HashMap<Integer, Integer>();
        if (startHour != 0) {
            returnMap.put(0, startHour);
        }
        int tempNumber = startHour;
        while (tempNumber + interval < endHour) {
            returnMap.put(tempNumber, tempNumber + interval);
            tempNumber = tempNumber + interval;
        }
        if (tempNumber + interval == endHour) {
            returnMap.put(tempNumber, tempNumber + interval);
            tempNumber = tempNumber + interval;
        } else {
            returnMap.put(tempNumber, endHour);
            tempNumber = endHour;
        }
        if (tempNumber < 24) {
            returnMap.put(tempNumber, 24);
        }
        return returnMap;
    }

    public static String getDayStart(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return default_format.format(cal.getTime());
    }

    public static Date getDayStartTime(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static String getYesterdayStart(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return default_format.format(cal.getTime());
    }

    public static Date getYesterdayStartTime(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getYesterday(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static String getYesterdayEnd(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return default_format.format(cal.getTime());
    }

    public static Date getYesterdayEndTime(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static String getDayEnd(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return default_format.format(cal.getTime());
    }

    public static String getYearMonth(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        if (cal.get(Calendar.MONTH)+1 <10){
            return cal.get(Calendar.YEAR)+"0"+(cal.get(Calendar.MONTH)+1);
        }else{
            return cal.get(Calendar.YEAR)+""+(cal.get(Calendar.MONTH)+1);
        }
    }

    public static int getTimeNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY)+1;
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getDayType(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if (day == 1 || day == 7) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * 拿到一天内的24个时间段
     * @return
     */
    public static ArrayList<Integer> getTimeInterval(){
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        int start = 1;
        int end = 24;
        for (int x=start; x<=end; x++){
            returnList.add(x);
        }
        return returnList;
    }

    /**
     * 根据日期和时间段编号，去获取那一天的那个时间段的起止时间字符串
     * @param day 日期
     * @param interval 时间段编号--值范围1到24
     * @return SIZE一定为2，第一个是开始时间，第二个是结束时间
     */
    public static ArrayList<String> getStartEndTimeInOneInterval(Date day, Integer interval){
        ArrayList<String> returnList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, interval-1);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(day);
        calEnd.set(Calendar.HOUR_OF_DAY, interval-1);
        calEnd.set(Calendar.MINUTE, 59);
        calEnd.set(Calendar.SECOND, 59);
        returnList.add(default_format.format(cal.getTime()));
        returnList.add(default_format.format(calEnd.getTime()));
        return returnList ;
    }

    public static int getPeriod(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int x = (cal.get(Calendar.HOUR_OF_DAY) * 60 * 60 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND)) / (15 * 60);
        int y = (cal.get(Calendar.HOUR_OF_DAY) * 60 * 60 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND)) % (15 * 60);
        if (y != 0) {
            return x + 1;
        } else if (x == 0 && y == 0) {
            return 1;
        } else {
            return x;
        }
    }

    /**
     * 根据时间段编号去获取时间周期字符串
     * @param period
     * @return
     */
    public static String getPeriodStr(int period) {
        int x = (period - 1) / 4;
        int y = (period - 1) % 4;
        if (x < 0){
            x = 0;
        }
        if (y<0){
            y = 0;
        }
        String str1 = "";
        String str2 = "";
        String str3 = "";
        if (x < 10) {
            str1 = "0" + x;
        } else {
            str1 = x + "";
        }
        if (x + 1 < 10) {
            str3 = "0" + (x + 1);
        } else {
            str3 = (x + 1) + "";
        }
        if (y == 0) {
            str2 = ":00";
        } else if (y == 1) {
            str2 = ":15";
        } else if (y == 2) {
            str2 = ":30";
        } else if (y == 3) {
            str2 = ":45";
        }
        return str1 + str2 + "-" + str3 + str2;
    }

    /**
     *
     * @param moningHighBeginTime 早高峰开始时间
     * @param moningHighEndTime 早高峰结束时间
     * @param nightHighBeginTime 晚高峰开始时间
     * @param nightHighEndTime 晚高峰结束时间
     * @return
     */
    public static ArrayList<String> getPeakTime(Date day, int moningHighBeginTime, int moningHighEndTime, int nightHighBeginTime, int nightHighEndTime){
        ArrayList<String> returnList = new ArrayList<String>();
        int hour1 = moningHighBeginTime / 60;
        int minute1 = moningHighBeginTime % 60;

        int hour2 = moningHighEndTime / 60;
        int minute2 = moningHighEndTime % 60;

        int hour3 = nightHighBeginTime / 60;
        int minute3 = nightHighBeginTime % 60;

        int hour4 = nightHighEndTime / 60;
        int minute4 = nightHighEndTime % 60;
        // 早高峰开始时间
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(day);
        cal1.set(Calendar.HOUR_OF_DAY, hour1);
        cal1.set(Calendar.MINUTE, minute1);
        cal1.set(Calendar.SECOND, 0);
        returnList.add(default_format.format(cal1.getTime()));
        // 早高峰结束时间
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(day);
        cal2.set(Calendar.HOUR_OF_DAY, hour2);
        cal2.set(Calendar.MINUTE, minute2);
        cal2.set(Calendar.SECOND, 0);
        returnList.add(default_format.format(cal2.getTime()));
        // 晚高峰开始时间
        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(day);
        cal3.set(Calendar.HOUR_OF_DAY, hour3);
        cal3.set(Calendar.MINUTE, minute3);
        cal3.set(Calendar.SECOND, 0);
        returnList.add(default_format.format(cal3.getTime()));
        // 晚高峰结束时间
        Calendar cal4 = Calendar.getInstance();
        cal4.setTime(day);
        cal4.set(Calendar.HOUR_OF_DAY, hour4);
        cal4.set(Calendar.MINUTE, minute4);
        cal4.set(Calendar.SECOND, 0);
        returnList.add(default_format.format(cal4.getTime()));
        // 平峰期开始时间 ---等于早高峰结束时间
        returnList.add(default_format.format(cal2.getTime()));
        // 平峰期结束时间---等于晚高峰开始时间
        returnList.add(default_format.format(cal3.getTime()));
        // 低峰期1开始时间
        Calendar cal5 = Calendar.getInstance();
        cal5.setTime(day);
        cal5.set(Calendar.HOUR_OF_DAY, 0);
        cal5.set(Calendar.MINUTE, 0);
        cal5.set(Calendar.SECOND, 0);
        returnList.add(default_format.format(cal5.getTime()));
        // 低峰期1结束时间
        returnList.add(default_format.format(cal1.getTime()));
        // 低峰期2开始时间
        returnList.add(default_format.format(cal4.getTime()));
        // 低峰期2结束时间
        Calendar cal6 = Calendar.getInstance();
        cal6.setTime(day);
        cal6.set(Calendar.HOUR_OF_DAY, 23);
        cal6.set(Calendar.MINUTE, 59);
        cal6.set(Calendar.SECOND, 59);
        returnList.add(default_format.format(cal6.getTime()));
        return returnList;
    }

    /**
     * 获取两个时间段之间的分钟数---舍去秒
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public static double getMinutesBetween(Date dateOne, Date dateTwo) {
        Calendar calOne = Calendar.getInstance();
        calOne.setTime(dateOne);
        Calendar calTwo = Calendar.getInstance();
        calTwo.setTime(dateTwo);
        return (calTwo.get(Calendar.HOUR_OF_DAY) * 60 + calTwo.get(Calendar.MINUTE)) - (calOne.get(Calendar.HOUR_OF_DAY) * 60 + calOne.get(Calendar.MINUTE));
    }

    public static int getDaysBettwenTwoDays(String day1, String day2) {
        Date date1 = parseDate(day1);
        Date date2 = parseDate(day2);
        return Math.abs((int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24))) + 1;
    }

    /**
     * day1 在day2之前返回-1，day2 在day1之前返回1，相等返回0
     * @param day1
     * @param day2
     * @return
     */
    public static int compare(String day1, String day2) {

        Date date1 = parseDate(day1,format_YYYY_MM_DD);
        Date date2 = parseDate(day2,format_YYYY_MM_DD);
        if (date1.getTime() < date2.getTime()) {
            return -1;
        } else if (date1.getTime() > date2.getTime()) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 计算两个时间之间的秒数,取整四舍五入
     * @param time1
     * @param time2
     * @return
     */
    public static int getSecondsBettwenTime(String time1, String time2){
        BigDecimal bg;
        bg = new BigDecimal(((parseDate(time2).getTime() - parseDate(time1).getTime())/1000));
        return Integer.parseInt(bg.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    /**
     * 将时间类型的字符串转换为文件名所需格式
     * @param dateStr
     * @return
     */
    public static String dateParseFileName(String dateStr) {
        Date day = parseDate(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int days = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minus = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        String returnStr = year + "";
        if (month + 1 < 10) {
            returnStr += ("0" + (month + 1));
        } else {
            returnStr += (month + 1);
        }
        if (days < 10) {
            returnStr += ("0" + days);
        } else {
            returnStr += days;
        }
        if (hour < 10) {
            returnStr += ("0" + hour);
        } else {
            returnStr += hour;
        }
        if (minus < 10) {
            returnStr += ("0" + minus);
        } else {
            returnStr += minus;
        }
        if (seconds < 10) {
            returnStr += ("0" + seconds);
        } else {
            returnStr += seconds;
        }
        return returnStr;
    }

    public static String getYesterdayYearMonthDay(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        String year = cal.get(Calendar.YEAR) + "";
        String month = "";
        String today = "";
        if (cal.get(Calendar.MONTH) + 1 < 10) {
            month = "0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            month = (cal.get(Calendar.MONTH) + 1) + "";
        }
        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            today = "0" + cal.get(Calendar.DAY_OF_MONTH);
        } else {
            today = cal.get(Calendar.DAY_OF_MONTH) + "";
        }
        return year + month + today;
    }

    public static String getYesterdayYearMonth(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        String year = cal.get(Calendar.YEAR) + "";
        String month = "";
        if (cal.get(Calendar.MONTH) + 1 < 10) {
            month = "0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            month = (cal.get(Calendar.MONTH) + 1) + "";
        }
        return year + month;
    }

    public static String getYesterdayStr(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, -1);
        return format_YYYYMMDD.format(cal.getTime());
    }

    /**
     * 获取本月第一秒钟的时间
     *
     * @return
     */
    public static String getMonthStartTime(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        if (year != null && year.trim() != "") {
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
        }
        if (month != null && month.trim() != "") {
            calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        }
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return format_YYYY_MM_DD.format(calendar.getTime()) + " 00:00:00";
    }

    public static String getLastMonthStartTime(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return format_YYYY_MM_DD.format(calendar.getTime()) + " 00:00:00";
    }

    /**
     * 获取本月最后一秒钟的时间
     *
     * @return
     */
    public static String getMonthEndTime(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        if (year != null && year.trim() != "") {
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
        }
        if (month != null && month.trim() != "") {
            calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        }
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return format_YYYY_MM_DD.format(calendar.getTime()) + " 23:59:59";
    }

    public static String getLastMonthEndTime(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return format_YYYY_MM_DD.format(calendar.getTime()) + " 23:59:59";
    }

    public static String getYearMonthToday(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        String year = cal.get(Calendar.YEAR) + "";
        String month = "";
        String today = "";
        if (cal.get(Calendar.MONTH) + 1 < 10) {
            month = "0" + (cal.get(Calendar.MONTH) + 1);
        } else {
            month = (cal.get(Calendar.MONTH) + 1) + "";
        }
        if (cal.get(Calendar.DAY_OF_MONTH) < 10 ){
            today = "0" + cal.get(Calendar.DAY_OF_MONTH);
        }else{
            today = cal.get(Calendar.DAY_OF_MONTH) + "";
        }
        return year + month + today;
    }

    public static Date getNextNDay(String day, int x) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(day));
        cal.add(Calendar.DATE, x);
        return cal.getTime();
    }

    public static String getLastWeekDayStart(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        return default_format.format(cal.getTime());
    }

    public static String getLastWeekDayEnd(Date day){
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        return default_format.format(cal.getTime());
    }

    public static String formatHourIntervalStart(int startHour) {
        String str1 = "";
        if (startHour < 10) {
            str1 = "0" + startHour + ":00";
        } else {
            str1 = startHour + ":00";
        }
        return str1;
    }

    public static int getHour(String dateStr){
        String str = dateStr.split(" ")[1];
        int hour = Integer.parseInt(str.split(":")[0]);
        return hour;
    }

    public static int getHour(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        return cal.get(Calendar.HOUR_OF_DAY);
    }


}