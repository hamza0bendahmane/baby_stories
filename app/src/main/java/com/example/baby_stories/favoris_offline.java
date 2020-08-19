package com.example.baby_stories;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.baby_stories.MainActivity.mInterstitialAd;

public class favoris_offline extends AppCompatActivity {
    public  GridView gridView ;
    DatabaseHandler db ;
    public static List<storyItem> storyItemList = new ArrayList<>();
    public static FavorisAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showInter();
        setContentView(R.layout.activity_favoris_offline);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adViewOff);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
         gridView = findViewById(R.id.gridView);
         loadNotes();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i =new Intent(favoris_offline.this,favorisItem.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                storyItem ss = (storyItem) gridView.getItemAtPosition(position);
                bundle.putString("title",ss.getTitle());
                bundle.putString("story",ss.getStory());
                bundle.putInt("id",ss.getId());
                bundle.putInt("imageId",ss.getImageId());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(favoris_offline.this);
                dialog.setMessage("هل تريد فعلا حذف القصة من المفضلة");
                dialog.setPositiveButton("حذف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new DatabaseHandler(favoris_offline.this);
                        storyItem ss = (storyItem) gridView.getItemAtPosition(position);
                        db.deleteStory(ss);
                        dialog.dismiss();
                        recreate();
                    }
                });
                dialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes(){
        DatabaseHandler db = new DatabaseHandler(favoris_offline.this);
        TextView empty_fav = findViewById(R.id.empty_fav);
        storyItemList = db.getAllStories();
        if(storyItemList.size() != 0){
            empty_fav.setVisibility(View.GONE);
            adapter = new FavorisAdapter(favoris_offline.this,storyItemList);
            gridView.setAdapter(adapter);

        }else {
           empty_fav.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
startActivity(new Intent(getApplicationContext(),MainActivity.class));
    finish();
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
                    Toast.makeText(favoris_offline.this, "فضل جلب الإعلان", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                    Toast.makeText(favoris_offline.this, "نشكرك جدا على الضغط على الإعلان انت تساعدنا بهذه الطريقة", Toast.LENGTH_SHORT).show();
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


