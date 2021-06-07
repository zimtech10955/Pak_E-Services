package com.example.pake_services;

import android.widget.Toast;

public class Verabls {
    public static int count=0;
    public static int control=0;
    public static String Native=null;
    public static String intestial=null;
    public static String banner=null;

    public static String getBanner() {
        return banner;
    }

    public static void setBanner(String banner) {
        Verabls.banner = banner;
    }

    public Verabls() {
    }

    public static void setNative(String aNative) {
        Native = aNative;
    }

    public static void setIntestial(String intestial) {
        Verabls.intestial = intestial;
    }

    public static String getNative() {
        return Native;
    }

    public static String getIntestial() {
        return intestial;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Verabls.count = count;
    }

    public static int getControl() {
        return control;
    }

    public static void setControl(int control) {
        Verabls.control = control;
    }

    public static boolean flag() {

        if (count%control==0)
            return true;
        else
            return false;

    }

}
