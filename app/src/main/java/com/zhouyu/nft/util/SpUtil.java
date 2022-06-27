package com.zhouyu.nft.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhouyu.nft.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;


public class SpUtil {

    //存储用户信息
    @SuppressLint("ApplySharedPref")
    public static void writeData(Context context, UserInfo data){
        SharedPreferences mySharePreferences =context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor =mySharePreferences.edit();
        editor.putString("Age", data.getAge());
        editor.putString("DaoNum", data.getDaoNum());
        editor.putString("FansNum", data.getFansNum());
        editor.putString("FollowNum", data.getFollowNum());
        editor.putString("HeadImg", data.getHeadImg());
        editor.putString("Introduce", data.getIntroduce());
        editor.putString("Nick", data.getNick());
        editor.putString("RcToken", data.getRcToken());
        editor.putString("Sex", data.getSex());
        editor.putString("InviteCode", data.getInviteCode());
        editor.putString("WalletAdress", data.getWalletAdress());
        editor.putString("isRealName", data.getIsRealName());
        editor.putString("Phone", data.getPhone());
        editor.commit();
    }

    //存储用户Token
    @SuppressLint("ApplySharedPref")
    public static void writeToken(Context context, String token){
        SharedPreferences mySharePreferences =context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor =mySharePreferences.edit();
        editor.putString("Token", token);
        editor.commit();
    }

    //读取用户信息
    public static UserInfo readData(Context context){
        SharedPreferences mySharePreferences =context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        String Age =mySharePreferences.getString("Age", "");
        String DaoNum =mySharePreferences.getString("DaoNum", "");
        String FansNum =mySharePreferences.getString("FansNum", "");
        String FollowNum =mySharePreferences.getString("FollowNum", "");
        String HeadImg =mySharePreferences.getString("HeadImg", "");
        String Introduce =mySharePreferences.getString("Introduce", "");
        String Nick =mySharePreferences.getString("Nick", "");
        String RcToken =mySharePreferences.getString("RcToken", "");
        String Sex =mySharePreferences.getString("Sex", "");
        String Token =mySharePreferences.getString("Token", "");
        String InviteCode =mySharePreferences.getString("InviteCode", "");
        String WalletAdress =mySharePreferences.getString("WalletAdress", "");
        String IsRealName =mySharePreferences.getString("isRealName", "");
        String Phone =mySharePreferences.getString("Phone", "");
        UserInfo data=new UserInfo();
        data.setAge(Age);
        data.setDaoNum(DaoNum);
        data.setFansNum(FansNum);
        data.setFollowNum(FollowNum);
        data.setHeadImg(HeadImg);
        data.setIntroduce(Introduce);
        data.setNick(Nick);
        data.setRcToken(RcToken);
        data.setSex(Sex);
        data.setToken(Token);
        data.setInviteCode(InviteCode);
        data.setWalletAdress(WalletAdress);
        data.setIsRealName(IsRealName);
        data.setPhone(Phone);
        return data;
    }

    //是否登录
    public static boolean isLogin(Context context){
        SharedPreferences mySharePreferences =context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
        String userId =mySharePreferences.getString("Token", "");
        return userId.isEmpty();
    }

    //注销登录删除SP数据
    @SuppressLint("ApplySharedPref")
    public static void clearSp(Context context){
        SharedPreferences mySharePreferences =context.getSharedPreferences("UserInfo", Activity.MODE_PRIVATE);
        if(mySharePreferences!=null){
            mySharePreferences.edit().clear().commit();
        }
    }

    //存储搜索信息
    @SuppressLint("ApplySharedPref")
    public static void searchData(Context context, List<String> search){
        SharedPreferences mySharePreferences =context.getSharedPreferences("search", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor =mySharePreferences.edit();
        Gson gson=new Gson();
        String data = gson.toJson(search);
        editor.putString("data",data);
        editor.commit();
    }

    //读取搜索信息
    public static List<String> readSearchData(Context context){
        SharedPreferences mySharePreferences =context.getSharedPreferences("search", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        String data = mySharePreferences.getString("data", "");
        List<String> dataList=new ArrayList<>();
        if (!data.isEmpty())
        {
            Gson gson=new Gson();
            dataList=gson.fromJson(data, new TypeToken<List<String>>() {}.getType());
        }
        return dataList;
    }

    //清空搜索信息
    @SuppressLint("ApplySharedPref")
    public static void deleteSearchData(Context context){
        SharedPreferences mySharePreferences =context.getSharedPreferences("search", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor =mySharePreferences.edit();
        editor.clear();
        editor.commit();
    }


    //存储环境
    @SuppressLint("ApplySharedPref")
    public static void writeEnv(Context context, String env){
        SharedPreferences mySharePreferences =context.getSharedPreferences("Env", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor =mySharePreferences.edit();
        editor.putString("env", env);
        editor.commit();
    }

    //读取环境
    public static String readEnv(Context context,String mo){
        SharedPreferences mySharePreferences =context.getSharedPreferences("Env", Activity.MODE_PRIVATE|Activity.MODE_PRIVATE);
        return mySharePreferences.getString("env", mo);
    }
}
