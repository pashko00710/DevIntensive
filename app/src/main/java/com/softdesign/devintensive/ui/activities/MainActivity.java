package com.softdesign.devintensive.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.utils.ConstantManager;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name_me);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Выполнен метод onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Выполнен метод onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Выполнен метод onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Выполнен метод onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Выполнен метод onDestroy");
    }
}
