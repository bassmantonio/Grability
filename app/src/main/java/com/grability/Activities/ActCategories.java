package com.grability.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.grability.BLL.ConfigurationKeys;
import com.grability.BLL.Utilities;
import com.grability.DTO.FeedDTO;

/**
 * This Activity is to represent a category list, gotten before thanks to Json Service.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class ActCategories extends AppCompatActivity {

    // Application Context
    private Context appContext;

    // ListView
    private ListView lvCategories;

    // GridView
    private GridView gvCategories;

    // TextView
    private TextView txtTitle;
    private TextView txtRights;

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
        setContentView(R.layout.layout_act_categories);
        initializeView();
    }

    /**
     * getJasonOnPreferences is a method for get Json string, after call Json Service just once.
     * @return   String: with JSON info, saved in shared preferences.
     */
    private String getJasonOnPreferences()
    {
        String result = "";

        // Save JSONString on SharedPreferences
        SharedPreferences userPreferences = getSharedPreferences(ConfigurationKeys.SHARED_PREFERENCES_STRING, appContext.MODE_PRIVATE);
        result = userPreferences.getString(ConfigurationKeys.JSON_STRING_KEY,"No hay valor");
        return result;
    }

    /**
     * initializeView is where Activity controls were initialized.
     */
    private void initializeView()
    {
        this.pauseFlag = false;
        FeedDTO.setInstance(Utilities.getFeedFromJSON(getJasonOnPreferences()));
        setTitle(FeedDTO.getInstance().getTitle());

        final String[] categoryArray = Utilities.getCategoriesFromFeed(FeedDTO.getInstance());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, categoryArray);

        this.lvCategories = (ListView)findViewById(R.id.lvCategories);
        this.txtTitle = (TextView)findViewById(R.id.txtTitle);
        this.txtRights = (TextView)findViewById(R.id.txtRights);
        this.gvCategories = (GridView)findViewById(R.id.gvCategories);

        if(this.lvCategories != null) {
            this.lvCategories.setAdapter(adapter);
            this.lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Start the next activity
                    Intent appListIntent = new Intent().setClass(
                            ActCategories.this, ActAppList.class);
                    appListIntent.putExtras(prepareBundle(parent.getItemAtPosition(position).toString()));
                    Utilities.startAnimationBeforeIntent( R.anim.category_item_animation, view, appListIntent);
                }
            });
        }
        if (gvCategories != null) {
            this.gvCategories.setAdapter(adapter);
            this.gvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Start the next activity
                    Intent appListIntent = new Intent().setClass(
                            ActCategories.this, ActAppList.class);

                    appListIntent.putExtras(prepareBundle(parent.getItemAtPosition(position).toString()));
                    Utilities.startAnimationBeforeIntent(R.anim.category_item_animation, view, appListIntent);
                }
            });
        }

        this.txtTitle.setText(R.string.categories);
        this.txtRights.setText(FeedDTO.getInstance().getRights());

        this.windowDecor = (View)this.getWindow().getDecorView();
        Utilities.startAnimationSet(R.animator.act_categories_animator,this.windowDecor);
    }


    /**
     * prepareBundle is a method used for create a next Intent's Bundle.
     * @param  input: contains category name.
     * @see String
     * @return Bundle: this is bundle package for nex Intent.
     */
    private Bundle prepareBundle(String input)
    {
        Bundle result = new Bundle();
        result.putString(ConfigurationKeys.APP_CATEGORY, input);

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
