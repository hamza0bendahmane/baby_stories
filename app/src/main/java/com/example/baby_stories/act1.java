package com.example.baby_stories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;

public class act1 extends AppCompatActivity {
public DatabaseHandler db ;
    boolean isFav ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element);

        LoadAsk();

        TextView title = findViewById(R.id.tx);
        ImageView img = findViewById(R.id.im);
        TextView text = findViewById(R.id.txx);
        Resources res = getResources();
        TypedArray imgTab = res.obtainTypedArray(R.array.short_images);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        final String title1 =getResources().getStringArray(R.array.short_title)[position];
        title.setText(title1);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/kufi.ttf");
        title.setTypeface(typeface1);
        title.setTextColor(getResources().getColor(R.color.color1));
        final String story = res.getStringArray(R.array.short_story)[position];
        final int imgId = imgTab.getResourceId(position,0);
        imgTab.recycle();
        img.setImageResource(imgId);
        text.setText(story);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/messi.ttf");
        text.setTypeface(typeface);


        ImageButton make_favorite = findViewById(R.id.make_favorite);

        make_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inserting note in db and getting
                // newly inserted note id

                db = new DatabaseHandler(getApplicationContext());
                isFav =   db.containStory(title1);

                favoris_offline.storyItemList.addAll(db.getAllStories());
              if (!isFav){
                storyItem note  = new storyItem(title1,story,imgId);
                long id = db.addstoryItem(note);
                // get the newly inserted note from db
                storyItem n = db.getstoryItem(id);


                if (n != null) {

                    // adding new note to array list at 0 position
                    favoris_offline.storyItemList.add(0, n);
                    Snackbar.make(v,"تم حفظ القصة بنجاح",Snackbar.LENGTH_LONG).setAction
                            ("الذهاب للمفضلة", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getApplicationContext(),favoris_offline.class));
                                }
                            }).setActionTextColor(Color.MAGENTA).show();
                }}else
              {
                  Snackbar.make(v,"هذه القصة مضافة بالفعل للمفضلة",Snackbar.LENGTH_LONG).setAction
                          ("الذهاب للمفضلة", new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  startActivity(new Intent(getApplicationContext(),favoris_offline.class));
                              }
                          }).setActionTextColor(Color.MAGENTA).show();
              }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        LoadAsk();

    }


    private void LoadAsk(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


}
