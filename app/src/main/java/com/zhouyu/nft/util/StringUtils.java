package com.zhouyu.nft.util;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description:字符串操作工具包
 * 2019/3/4.
 * auth:lihe
 */

public class StringUtils {
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    //private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");

    private final static java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");

    public final static ThreadLocal<SimpleDateFormat> dateFormaterYS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormaterYD = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期时间类型
     * @param sdatetime
     * @return
     */
    public static Date toDatetime(String sdatetime) {
        try {
            return dateFormaterYS.get().parse(sdatetime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormaterYD.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 将日期转换成字符串形式
     * @param date
     * @return
     */
    public static String toDateString(Date date){
        return dateFormaterYD.get().format(date);
    }

    /**
     * 将日期时间转换成字符串形式
     * @param date
     * @return
     */
    public static String toDatetimeString(Date date){
        return dateFormaterYS.get().format(date);
    }

    /**
     * 以友好的方式显示时间
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        if(StringUtils.isEmpty(sdate)) {
            return "";
        }
        if(sdate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            sdate += " 00:00:00"; //完成补齐
        }
        Date time = toDatetime(sdate);
        if(time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormaterYD.get().format(cal.getTime());
        String paramDate = dateFormaterYD.get().format(time);
        if(curDate.equals(paramDate)){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
            else
                ftime = hour+"小时前";
            return ftime;
        }

        long lt = time.getTime()/86400000;
        long ct = cal.getTimeInMillis()/86400000;
        int days = (int)(ct - lt);
        if(days == 0){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
            else
                ftime = hour+"小时前";
        }
        else if(days == 1){
            ftime = "昨天";
        }
        else if(days == 2){
            ftime = "前天";
        }
		/*else if(days > 2 && days <= 10){
			ftime = days+"天前";
		}
		else if(days > 10){
			ftime = dateFormaterYD.get().format(time);
		}*/else {
            ftime = dateFormaterYD.get().format(time);
        }
        return ftime;
    }

    /**
     * 以友好的方式显示时间
     * @param idate
     * @return
     */
    public static String friendlyChatTime(String idate) {
        if(isEmpty(idate))
            return "";
        String sdate = idate.length() > 19 ? idate.substring(0, 19) : idate;
        if(sdate.length() < 19)
            return sdate;//不明确的时间

        Date time = toDatetime(sdate);
        if(time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormaterYD.get().format(cal.getTime());
        String paramDate = dateFormaterYD.get().format(time);
        if(curDate.equals(paramDate)){
            ftime = "" + pad(time.getHours()) + ":" + pad(time.getMinutes());
            return ftime;
        }

        long lt = time.getTime()/86400000;
        long ct = cal.getTimeInMillis()/86400000;
        int days = (int)(ct - lt);
        if(days <= 1){ //包括跨24时的
            ftime = "昨天 " + pad(time.getHours()) + ":" + pad(time.getMinutes());
        }
        else if(days == 2){
            ftime = "前天" + pad(time.getHours()) + ":" + pad(time.getMinutes());
        }
        else {
            ftime = sdate;
        }
        return ftime;
    }

    /**
     * 以友好的方式显示时间
     * @param sdate
     * @return
     */
    public static String friendlyShareTime(String sdate) {
        if(StringUtils.isEmpty(sdate)) {
            return "";
        }
        if(sdate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            sdate += " 00:00:00"; //完成补齐
        }
        Date time = toDatetime(sdate);
        if(time == null) {
            return "";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormaterYD.get().format(cal.getTime());
        String paramDate = dateFormaterYD.get().format(time);
        if(curDate.equals(paramDate)){
            return "今天";
        }

        long lt = time.getTime()/86400000;
        long ct = cal.getTimeInMillis()/86400000;
        int days = (int)(ct - lt);
        if(days <= 1){
            ftime = "昨天";
        }
        else if(days == 2){
            ftime = "前天";
        }else {
            ftime = dateFormaterYD.get().format(time);
        }
        return ftime;
    }

    public static String getBigSpell(String sdate, int order) {
        if(StringUtils.isEmpty(sdate) || sdate.length() != 10) {
            return "";
        }
        if(order == 1) {
            //年
            return "";
        } else if(order == 2) {
            //月
            int nb = Integer.parseInt(sdate.substring(5, 7));
            String ns="";
            switch(nb) {
                case 1:
                    ns = "一月";
                    break;
                case 2:
                    ns = "二月";
                    break;
                case 3:
                    ns = "三月";
                    break;
                case 4:
                    ns = "四月";
                    break;
                case 5:
                    ns = "五月";
                    break;
                case 6:
                    ns = "六月";
                    break;
                case 7:
                    ns = "七月";
                    break;
                case 8:
                    ns = "八月";
                    break;
                case 9:
                    ns = "九月";
                    break;
                case 10:
                    ns = "十月";
                    break;
                case 11:
                    ns = "十一月";
                    break;
                case 12:
                    ns = "十二月";
                    break;
            }
            return ns;
        } else if(order == 3) {
            //日
            return "";
        } else {
            return "";
        }
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    /**
     * 判断给定字符串时间是否为今日
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate){
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if(time != null){
            String nowDate = dateFormaterYD.get().format(today);
            String timeDate = dateFormaterYD.get().format(time);
            if(nowDate.equals(timeDate)){
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true
     * @param input
     * @return boolean
     */
    public static boolean isEmpty( String input )
    {
        if ( input == null || "".equals( input ) )
            return true;

        for ( int i = 0; i < input.length(); i++ )
        {
            char c = input.charAt( i );
            if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if(email == null || email.trim().length()==0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 匹配短信中间的6个数字（验证码等）
     *
     * @param patternContent
     * @return
     */
    public static String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static boolean isMobileNum(String mobiles) {
        //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile("^(1)\\d{10}$"); //新手机号
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 检验验证码
     * @param code
     * @return
     */
    public static boolean isCodeNum(String code){
        Pattern p = Pattern.compile("^\\d{4}$"); //4位验证码
        Matcher m = p.matcher(code);
        return m.matches();
    }

    /**
     * 判断密码是否由33-126之间的ascii字符组成
     * @param password
     * @return
     */
    public static boolean isPasswordChar(String password) {
		/*Pattern p = Pattern.compile("[a-zA-Z0-9`~!@#$%^&*_-+=(){}[]|\\:;\"'<,>.?/]{6,16}");
		Matcher m = p.matcher(password);
		return m.matches();*/
        char c;
        for(int i=0; i<password.length(); i++){
            c = password.charAt(i);
            if(c<33 || c>126){
                return false;
            }
        }
        return true;
    }
    /**
     * 字符串转整数
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try{
            return Integer.parseInt(str);
        }catch(Exception e){}
        return defValue;
    }
    /**
     * 对象转整数
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if(obj==null) return 0;
        return toInt(obj.toString(),0);
    }
    /**
     * 对象转整数
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try{
            return Long.parseLong(obj);
        }catch(Exception e){}
        return 0;
    }

    /**
     * 字符串转浮点数
     * @param str
     * @param defValue
     * @return
     */
    public static double toDouble(String str, int defValue) {
        try{
            return Double.parseDouble(str);
        }catch(Exception e){}
        return defValue;
    }

    /**
     * 字符串转布尔值
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try{
            return Boolean.parseBoolean(b);
        }catch(Exception e){}
        return false;
    }

    public static String getDoubleString(double d){
        String s = df.format(d);
        return s;
    }

    public static int[] getThumnailDimenByUrl(String thumUrl){
        int[] dimen=null;
        if(!StringUtils.isEmpty(thumUrl)){
            String thumFile = thumUrl.substring(thumUrl.lastIndexOf("/")+1);
            if(!StringUtils.isEmpty(thumFile)){
                String thumFileName =  thumFile.substring(0, thumFile.lastIndexOf("."));
                if(!StringUtils.isEmpty(thumFileName)){
                    String[] dimenStr = thumFileName.split("_");
                    if(dimenStr != null && dimenStr.length == 3){
                        try{
                            dimen = new int[]{Integer.parseInt(dimenStr[2]), Integer.parseInt(dimenStr[1])};
                        }catch(NumberFormatException nfe){
                            Log.e("SHARE", nfe.getMessage());
                            dimen = new int[]{100, 100}; //默认高宽
                        }
                    }
                }
            }
        }
        if(dimen==null){
            dimen = new int[]{100, 100}; //默认高宽
        }
        return dimen;
    }

    /***************** 将json串根据key排序 start *****************/
    public static String sorkValueResult(String json){
        String strSortJson = sortJson(json);
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        JsonParser p = new JsonParser();
        JsonElement e = p.parse(strSortJson);
        if (e.isJsonNull() || e.isJsonPrimitive()) {
            return "";
        }
        if (e.isJsonObject()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, JsonElement> en : e.getAsJsonObject().entrySet()) {
                String str = en.getValue().toString();
//                sb.append(str.replaceAll("(?<!\\)\"",""));
                sb.append(str.replace("\\\"","lihe0806aaa").replace("\"","").replace("lihe0806aaa","\""));
            }
            return sb.toString();
        }
        return "";
    }
    /**
     * 根据json key排序
     * @param json
     * @return
     */
    public static String sortJson(String json) {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        JsonParser p = new JsonParser();
        JsonElement e = p.parse(json);
        sort(e);
        return g.toJson(e);
    }
    /**
     * 定义比较规则
     * 升序 abc
     * @return
     */
    private static Comparator<String> getComparator() {
        return (s1, s2) -> s1.compareTo(s2);
    }

    /**
     * 排序
     *
     * @param e
     */
    public static void sort(JsonElement e) {
        if (e.isJsonNull() || e.isJsonPrimitive()) {
            return;
        }

        if (e.isJsonArray()) {
            JsonArray a = e.getAsJsonArray();
            Iterator<JsonElement> it = a.iterator();
            while (it.hasNext()){
                sort(it.next());
            }
//            it.forEachRemaining(i -> sort(i));
            return;
        }

        if (e.isJsonObject()) {
            Map<String, JsonElement> tm = new TreeMap<>(getComparator());
            for (Map.Entry<String, JsonElement> en : e.getAsJsonObject().entrySet()) {
                tm.put(en.getKey(), en.getValue());
            }

            String key;
            JsonElement val;
            for (Map.Entry<String, JsonElement> en : tm.entrySet()) {
                key = en.getKey();
                val = en.getValue();
                e.getAsJsonObject().remove(key);
                e.getAsJsonObject().add(key, val);
                sort(val);
            }
        }
    }
    /***************** 将json串根据key排序 end *****************/
}
