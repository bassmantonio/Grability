package com.grability.Activities;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.grability.BLL.ConfigurationKeys;
import com.grability.BLL.Utilities;
import com.grability.DTO.EntryDTO;
import com.grability.DTO.FeedDTO;
import com.squareup.picasso.Picasso;

/**
 * This Activity is to represent an App selected summary.
 * @author: Mauricio Antonio Moreno Marulanda
 * @version: 24/02/2016
 */
public class ActAppResume extends AppCompatActivity {

    // TextView
    private TextView txtTitle;
    private TextView txtSummary;
    private TextView txtPrice;
    private TextView txtType;
    private TextView txtCategory;
    private TextView txtRights;
    private TextView txtRelease;
    private TextView txtLink;
    private TextView txtArtist;
    private TextView txtArtistLink;
    private TextView txtAppRights;

    // ImageView
    private ImageView ivAppIcon;

    // Filter Apps
    EntryDTO appSelected;

    // windowView
    private View windowDecor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_act_app_resume);

        if (getResources().getBoolean(R.bool.portrait_only))
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        initializeView();
    }

    /**
     * initializeView is where Activity controls were initialized.
     */
    private void initializeView()
    {
        if (!Utilities.isOnline())
        {
            Utilities.showToast(this,getResources().getString(R.string.no_internet_connection_for_load_images));
        }

        Bundle categoryBundle = this.getIntent().getExtras();
        String appId = categoryBundle.getString(ConfigurationKeys.APP_ID);

        this.appSelected = Utilities.getAppbyId(FeedDTO.getInstance(), appId);

        setTitle(appSelected.getTitle());

        this.txtTitle = (TextView)findViewById(R.id.txtTitle);
        this.txtSummary = (TextView)findViewById(R.id.txtSummary);
        this.txtPrice = (TextView)findViewById(R.id.txtPrice);
        this.txtType = (TextView)findViewById(R.id.txtType);
        this.txtCategory = (TextView)findViewById(R.id.txtCategory);
        this.txtRights = (TextView)findViewById(R.id.txtRights);
        this.txtRelease = (TextView)findViewById(R.id.txtRelease);
        this.txtLink = (TextView)findViewById(R.id.txtLink);
        this.txtArtist = (TextView)findViewById(R.id.txtArtist);
        this.txtArtistLink = (TextView)findViewById(R.id.txtArtistLink);
        this.txtAppRights = (TextView)findViewById(R.id.txtAppRights);
        this.ivAppIcon = (ImageView)findViewById(R.id.ivAppIconLow);

        this.txtTitle.setText(appSelected.getName());
        this.txtSummary.setText(appSelected.getSummary());
        this.txtPrice.setText(appSelected.getPrice().getCurrency() + " - " + appSelected.getPrice().getAmount());
        this.txtType.setText(appSelected.getContentType().getLabel());
        this.txtCategory.setText(appSelected.getCategory().getLabel());
        this.txtRights.setText(appSelected.getRights());
        this.txtRelease.setText(appSelected.getReleaseDate().getFormalDate());
        this.txtLink.setText(appSelected.getLink().getHRef());
        this.txtArtist.setText(appSelected.getArtist().getName());
        this.txtArtistLink.setText(appSelected.getArtist().getHRef());
        this.txtAppRights.setText(FeedDTO.getInstance().getRights());

        String imageURL = appSelected.getImages().elementAt(0).getURL();
        int imgHeight = Integer.parseInt(appSelected.getImages().elementAt(0).getHeight());

        if(this.ivAppIcon != null)
        {
            imageURL = appSelected.getImages().elementAt(0).getURL();
            imgHeight = Integer.parseInt(appSelected.getImages().elementAt(0).getHeight());
        }else
        {
            this.ivAppIcon = (ImageView)findViewById(R.id.ivAppIconMed);
            if (this.ivAppIcon != null) {
                imageURL = appSelected.getImages().elementAt(1).getURL();
                imgHeight = Integer.parseInt(appSelected.getImages().elementAt(1).getHeight());

            }else
            {
                this.ivAppIcon = (ImageView)findViewById(R.id.ivAppIconHig);
                imageURL = appSelected.getImages().elementAt(2).getURL();
                imgHeight = Integer.parseInt(appSelected.getImages().elementAt(2).getHeight());
            }
        }

        Picasso.with(this.getApplicationContext())
                .load(imageURL)
                .resize(imgHeight,imgHeight)
                .placeholder(R.drawable.loader)
                .error(R.drawable.blank)
                .into(this.ivAppIcon);

        this.windowDecor = (View)this.getWindow().getDecorView();
        Utilities.startAnimationSet(R.animator.act_app_resume_animator, this.windowDecor);
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
}
