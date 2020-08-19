package com.example.baby_stories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

import static java.util.Calendar.getInstance;


public class MainActivity extends AppCompatActivity {
public MaterialCardView act0, act1,act2,act3,act4,floatingActionButton ;
    public static InterstitialAd mInterstitialAd ,mInterstitialAd2;
    public static boolean hasToldHim  = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        act1 = findViewById(R.id.short_story);
        act2 = findViewById(R.id.todays_story);
        act0 = findViewById(R.id.imageView);

        act3 = findViewById(R.id.long_story);
        act4 = findViewById(R.id.act4);
        floatingActionButton = findViewById(R.id.floating_action_button);
        // initialize admob ...


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        loadInterstitial();


          // variables



        // change fonts  ...



            // opening the activities when click on their names ...
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, gridForLongStories.class);
                startActivity(i);
            }
        });

        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, gridForShortStories.class);
                startActivity(i);
            }
        });
        act0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),favoris_offline.class));
            }
        });
        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd2.isLoaded()) {
                    mInterstitialAd2.show();
                    Intent i = new Intent(MainActivity.this, act2.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(MainActivity.this, "تأكد من اتصالك بالأنترنت لكي تتمكن من قراءة نصائح للأبوين وفوائد قراءة القصص للأطفال , ان كنت متصل بالأنترنت أنتظر قليلا ريثما نقوم بالاتصال بالسيرفر", Toast.LENGTH_LONG).show();
                }

            }
        });
            act4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, act4.class);
                    startActivity(i);
                }
            });
            // on click float button
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  startActivity(new Intent(getApplicationContext(),help.class));

            }
        });

        // .... recycle the tab ...

        }


    @Override
    protected void onResume() {
        super.onResume();
        TranslateAnimation animation = new TranslateAnimation( 1000.0f,0.0f, 0.0f, 0.0f);
        // animations ...
        TranslateAnimation animation1 = new TranslateAnimation( -1000.0f,0.0f, 0.0f, 0.0f);
        TranslateAnimation animation0 = new TranslateAnimation( 0.0f,0.0f, 1000.0f, 0.0f);
        animation0.setDuration(800);
        animation.setDuration(800);
        animation1.setDuration(800);
        findViewById(R.id.todays_story).setAnimation(animation0);
        findViewById(R.id.short_story).setAnimation(animation0);
        findViewById(R.id.long_story).setAnimation(animation);
        findViewById(R.id.imageView).setAnimation(animation);
        findViewById(R.id.floating_action_button).setAnimation(animation1);
        findViewById(R.id.act4).setAnimation(animation1);

    }

    private void loadInterstitial() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd2 = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd2.setAdUnitId(getResources().getString(R.string.interstitial2_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd2.loadAd( new AdRequest.Builder().build());
    }


}

