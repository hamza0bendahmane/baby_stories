package hb.techs.baby_stories;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    public MaterialCardView act0, act1, act2, act3, act4, helpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        act1 = findViewById(R.id.short_story);
        act2 = findViewById(R.id.todays_story);
        act0 = findViewById(R.id.imageView);

        act3 = findViewById(R.id.long_story);
        act4 = findViewById(R.id.act4);
        helpButton = findViewById(R.id.floating_action_button);
        // initialize admob ...


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
                if (isInternetAvailable()) {
                    Intent i = new Intent(MainActivity.this, act2.class);
                    startActivity(i);
                } else
                    Snackbar.make(findViewById(R.id.lineqr), "الرجاء الإتصال بالأنترنت لقراءة قصة اليوم", Snackbar.LENGTH_LONG).show();

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
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), help.class));

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


    public boolean isInternetAvailable() {
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMan.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}

