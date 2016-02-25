package com.grability.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.grability.Adapters.AppListAdapter;
import com.grability.BLL.ConfigurationKeys;
import com.grability.BLL.Utilities;
import com.grability.DTO.EntryDTO;
import com.grability.DTO.FeedDTO;

import java.util.Vector;

/**
 * This Activity is to represent an Apps list, filtered by category previously.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ActAppList extends AppCompatActivity {

    // Application Context
    private Context appContext;

    // ListView
    private ListView lvApps;

    // GridView
    private GridView gvApps;

    // TextView
    private TextView txtTitle;
    private TextView txtRights;

    // Filter Apps
    Vector<EntryDTO> appsArray;

    // windowView
    private View windowDecor;

    // PauseFlag
    private boolean pauseFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only))
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_act_app_list);

        initializeView();
    }

    /**
     * initializeView is where Activity controls were initialized.
     */
    private void initializeView()
    {
        this.pauseFlag = false;

        if (!Utilities.isOnline())
        {
            Utilities.showToast(this,getResources().getString(R.string.no_internet_connection_for_load_images));
        }

        Bundle categoryBundle = this.getIntent().getExtras();
        String sCategory = categoryBundle.getString(ConfigurationKeys.APP_CATEGORY);

        setTitle(FeedDTO.getInstance().getTitle());

        this.appsArray = Utilities.getAppsbyCategory(FeedDTO.getInstance(), sCategory);

        AppListAdapter appListAdapter = new AppListAdapter(this,this.appsArray);

        this.lvApps= (ListView)findViewById(R.id.lvApps);
        this.txtTitle = (TextView)findViewById(R.id.txtTitle);
        this.txtRights = (TextView)findViewById(R.id.txtRights);
        this.gvApps = (GridView)findViewById(R.id.gvApps);

        if(this.lvApps != null) {
            this.lvApps.setAdapter(appListAdapter);
            this.lvApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> a, View view, int position, long id) {
                    //Start the next activity
                    Intent resumeIntent = new Intent().setClass(
                            ActAppList.this, ActAppResume.class);
                    resumeIntent.putExtras(prepareBundle(appsArray.elementAt(position)));
                    Utilities.startAnimationBeforeIntent(R.anim.app_list_item_animation, view, resumeIntent);
                }
            });
        }
        if (gvApps != null) {
            this.gvApps.setAdapter(appListAdapter);
            this.gvApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Start the next activity
                    Intent resumeIntent = new Intent().setClass(
                            ActAppList.this, ActAppResume.class);
                    resumeIntent.putExtras(prepareBundle(appsArray.elementAt(position)));
                    Utilities.startAnimationBeforeIntent(R.anim.app_list_item_animation, view, resumeIntent);
                }
            });
        }

        this.txtTitle.setText(sCategory);
        this.txtRights.setText(FeedDTO.getInstance().getRights());

        this.windowDecor = (View)this.getWindow().getDecorView();
        Utilities.startAnimationSet(R.animator.act_app_list_animator, this.windowDecor);
    }


    /**
     * BackButtonOnClick is a method called for BackButton onClick event.
     * @param  view: makes reference to BackButton view.
     */
    public void BackButtonOnClick(View view)
    {
        Animation defaultAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.back_button_animation);
        view.startAnimation(defaultAnimation);
        defaultAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onBackPressed();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * prepareBundle is a method used for create a next Intent's Bundle.
     * @param  input: makes reference to input data which will be put in bundle.
     * @see EntryDTO
     * @return Bundle: this is bundle package for nex Intent.
     */
    private Bundle prepareBundle(EntryDTO input)
    {
        Bundle result = new Bundle();
        result.putString(ConfigurationKeys.APP_ID, input.getId().getId());

        return result;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(this.pauseFlag) {
            Utilities.startAnimationSet(R.animator.activities_resume_animator, this.windowDecor);
            this.pauseFlag = false;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        this.pauseFlag = true;
    }

}
