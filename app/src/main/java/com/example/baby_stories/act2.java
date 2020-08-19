package com.example.baby_stories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

import static com.example.baby_stories.MainActivity.mInterstitialAd;
import static java.util.Calendar.*;
import static java.util.Calendar.DAY_OF_MONTH;

public class act2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
        showInter();
        TextView date = findViewById(R.id.date);
         final TextView story = findViewById(R.id.textView2);
         final TextView title = findViewById(R.id.textView);
         final ImageView img = findViewById(R.id.imageStory);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adViewAct2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String today;
        Calendar calendar = getInstance();
        int imonth = calendar.get(MONTH) + 1;
        String month = String.valueOf(imonth);
        today = calendar.get(YEAR) + "/" + month + "/" + calendar.get(DAY_OF_MONTH);
        date.setText("اليوم :" + "\n" + today);
        int dayMonth = calendar.get(DAY_OF_MONTH);
        // get data from Firebase .....
        StorageReference images = FirebaseStorage.getInstance().
                getReference().child("images").child("story"+dayMonth+".png.jpg");
        images.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               Glide.with(getApplicationContext()).load(uri)
                       .diskCacheStrategy(DiskCacheStrategy.ALL)
                       .into(img);
            }
        });
        // get the title and body ...
        DocumentReference story_ref = FirebaseFirestore.getInstance().collection("Stories")
                .document(String.valueOf(dayMonth));
        story_ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                title.setText(documentSnapshot.get("title").toString());
               story.setText(documentSnapshot.get("body").toString());

            }
        });
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/messi.ttf");
        story.setTypeface(typeface1);
        title.setTextColor(getResources().getColor(R.color.color2));
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/kufi.ttf");
        title.setTypeface(typeface);

    }


    public void showInter(){
        if (mInterstitialAd==null)
            Toast.makeText(this, "خطأ", Toast.LENGTH_SHORT).show();
        else {
            if (mInterstitialAd.isLoading()) {
                Toast.makeText(this, "الإعلان في طريقه في لتحميل", Toast.LENGTH_LONG).show();
                recreate();
            }
            if (mInterstitialAd.isLoaded())
                mInterstitialAd.show();



            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    // Code to be executed when an ad request fails.
                    Toast.makeText(act2.this, "فضل جلب الإعلان", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                    Toast.makeText(act2.this, "نشكرك جدا على الضغط على الإعلان انت تساعدنا بهذه الطريقة", Toast.LENGTH_SHORT).show();
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());

                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the interstitial ad is closed.
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());

                }
            });
        }
    }
}
