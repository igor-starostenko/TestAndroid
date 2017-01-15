package com.example.testlibrary;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;


public class AppList {
    public String word;
    public String[] names;

    public void getAppList(PackageManager pm) {
        word = "AppList library succeeded!";
        //get a list of installed apps.
        int x = 0;

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        names = new String[packages.size()];
        for (ApplicationInfo packageInfo : packages) {
            //Log.e(TAG, "Installed package :" + packageInfo.packageName);
            if (packageInfo.packageName.contains(".")) {
                //Log.d(TAG, nameArr[nameArr.length-1]);
                String[] nameArr = packageInfo.packageName.split("\\.");
                names[x] = nameArr[nameArr.length - 1];
            } else {
                //Log.d(TAG, packageInfo.packageName);
                names[x] = packageInfo.packageName;
            }
            x++;
        }
    }
}
