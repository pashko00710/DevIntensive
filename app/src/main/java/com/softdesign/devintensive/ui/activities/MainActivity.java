package com.softdesign.devintensive.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.devintensive.R;
import com.softdesign.devintensive.data.managers.DataManager;
import com.softdesign.devintensive.utils.CircleImage;
import com.softdesign.devintensive.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = ConstantManager.TAG_PREFIX + " Main Activity";
    private DataManager mDataManager;
    private ImageView callImage;
    private CoordinatorLayout mCoordinatorLayout;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton mFab;
    EditText mMobile, mEmail, mAccount, mGithub, mAboutMe;
    boolean mCurrent = false;

    private List<EditText> mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setTitle(R.string.app_name_me);
        setContentView(R.layout.activity_main);
        callImage = (ImageView) findViewById(R.id.main_call);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mFab = (FloatingActionButton) findViewById(R.id.my_fab);
        mMobile = (EditText) findViewById(R.id.main_phone);
        mEmail = (EditText) findViewById(R.id.main_email);
        mDataManager = DataManager.getInstance();
        mAccount = (EditText) findViewById(R.id.main_profile);
        mGithub = (EditText) findViewById(R.id.main_github);
        mAboutMe= (EditText) findViewById(R.id.main_aboutme);
        mUserInfo = new ArrayList<>();
        mUserInfo.add(mMobile);
        mUserInfo.add(mEmail);
        mUserInfo.add(mAccount);
        mUserInfo.add(mGithub);
        mUserInfo.add(mAboutMe);
        loadUserInfoValue();
        setupActionBar();
        setupDrawerLayout();
        mFab.setOnClickListener(this);
        callImage.setOnClickListener(this);
        if(savedInstanceState == null) {
        } else {
            mCurrent = savedInstanceState.getBoolean(ConstantManager.EDIT_MODE, false);
            saveUserInfoValue();
            changeEditMode(mCurrent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(ConstantManager.EDIT_MODE, mCurrent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_call:
                break;
            case R.id.my_fab:
                if(!mCurrent) {
                    changeEditMode(true);
                    mCurrent = true;
                } else {
                    changeEditMode(false);
                    mCurrent = false;
                }
                break;
            default:
                break;
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    private void setupActionBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawerLayout() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        setupDrawerHeader(navigationView, getRoundBitmap(R.mipmap.user_default_pic), "Павел", "pashko00710@gmail.com");
    }

    private void setupDrawerHeader(NavigationView navigationView, Bitmap avatar, String myName, String email) {
        View view = navigationView.getHeaderView(0);
        if (avatar != null) {
            ImageView imageView = (ImageView)view.findViewById(R.id.profile_image);
            imageView.setImageBitmap(avatar);
        }
        if (myName != null){
            TextView textView = (TextView)view.findViewById(R.id.username_drawer_header);
            textView.setText(myName);
        }
        if (email != null){
            TextView textView = (TextView)view.findViewById(R.id.email_drawer_header);
            textView.setText(email);
        }
    }

    private Bitmap getRoundBitmap(int drawableRes){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableRes);
        bitmap = CircleImage.getRoundedBitmap(bitmap);
        return bitmap;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_myaccount :
                item.setCheckable(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.drawer_commands:
                item.setCheckable(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    private void changeEditMode(boolean mode) {
        if(mode) {
            mFab.setImageResource(R.drawable.ic_done_black_24dp);
            for (View userValue : mUserInfo) {
                userValue.setEnabled(true);
                userValue.setFocusable(true);
                userValue.setFocusableInTouchMode(true);
            }
        } else {
            mFab.setImageResource(R.drawable.ic_create_black_24dp);
            for (View userValue : mUserInfo) {
                userValue.setEnabled(false);
                userValue.setFocusable(false);
                userValue.setFocusableInTouchMode(false);
            }
        }
    }
    private void loadUserInfoValue() {
        List<String> userData = mDataManager.getPreferencesManager().loadUserData();
        for (int i = 0; i < userData.size(); i++){
            mUserInfo.get(i).setText(userData.get(i));
        }
    }

    private void saveUserInfoValue() {
        List<String> userData = new ArrayList<>();
        for (EditText userField : mUserInfo){
            userData.add(userField.getText().toString());
        }
        mDataManager.getPreferencesManager().saveUserData(userData);
    }
    //    private void runWithDelayed() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hideProgress();
//            }
//        }, 4000);
//    }
}
