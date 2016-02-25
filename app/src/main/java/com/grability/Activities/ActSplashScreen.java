package com.grability.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.grability.BLL.ConfigurationKeys;
import com.grability.BLL.ServiceRequestManager;
import com.grability.BLL.Utilities;

/**
 * This Activity is to shown a splash screen, while data is obtained by JSON Service (just once).
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ActSplashScreen extends AppCompatActivity {

    // Application Context
    private Context appContext;

    // windowView
    private View windowDecor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.windowDecor = (View)this.getWindow().getDecorView();

        if (getResources().getBoolean(R.bool.portrait_only))
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.layout_act_splash_screen);


        if (getFirstTimeAccess(false)) {
            if (Utilities.isOnline()) {
                callService();
                getFirstTimeAccess(true);
                callActivity();
            }
            else
            {
                Utilities.showToast(this, getResources().getString(R.string.no_internet_connection_for_download));
            }
        }
        else
        {
            callActivity();
        }

    }

    /**
     * Calls JSON Service in a thread, just once.
     */
    private void callService() {
        try {

            ServiceRequestManager servicio = new ServiceRequestManager(this);
            servicio.runService();

        }
        catch (Exception ex) {

        }
    }

    /**
     * Starts a new activity, after data charge.
     */
    private void callActivity()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ImageView image = (ImageView) findViewById(R.id.ivSplashScreen);
                Animation translateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_screen_animation);
                image.startAnimation(translateAnimation);

                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Start the next activity
                        Intent categoriesIntent = new Intent().setClass(
                                ActSplashScreen.this, ActCategories.class);
                        startActivity(categoriesIntent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }
        }, ConfigurationKeys.SPLASH_SCREEN_DELAY);
    }

    /**
     * Sets JSON String into SharedPreferences
     * @param JSONString: Represents the JSON Information.
     */
    public void setJasonOnPreferences(String JSONString)
    {
        // Save JSONString on SharedPreferences
        SharedPreferences userPreferences = getSharedPreferences(ConfigurationKeys.SHARED_PREFERENCES_STRING, appContext.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = userPreferences.edit();
        preferencesEditor.putString(ConfigurationKeys.JSON_STRING_KEY,JSONString);
        preferencesEditor.commit();
    }

    /**
     * Evaluates if app first time has launched
     */
    private boolean getFirstTimeAccess(boolean writtenFlag)
    {

        // SharedPreferences
        SharedPreferences userPreferences = getSharedPreferences(ConfigurationKeys.SHARED_PREFERENCES_STRING, appContext.MODE_PRIVATE);
        boolean firstTime = userPreferences.getBoolean(ConfigurationKeys.FIRST_TIME_KEY, true);

        if (firstTime && writtenFlag) {
            SharedPreferences.Editor preferencesEditor = userPreferences.edit();
            preferencesEditor.putBoolean(ConfigurationKeys.FIRST_TIME_KEY, false);
            preferencesEditor.commit();
        }
        return firstTime;
    }
}
