package com.example.igorstar.testapp;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;

import android.widget.ExpandableListView;

//import com.example.testlibrary.AppList;

import com.example.testlibrary.AppList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppListActivity extends ListActivity {
    private static final String TAG = "AppListActivity";

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        PackageManager pm = getPackageManager();
        AppList appList = new AppList();
        appList.getAppList(pm);
        Log.v(TAG, appList.word);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, appList.names);
        setListAdapter(adapter);
    }

}

