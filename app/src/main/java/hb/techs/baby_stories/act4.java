package hb.techs.baby_stories;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import de.cketti.mailto.EmailIntentBuilder;


public class act4 extends AppCompatActivity {
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act4);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadInterstitial();

        AdView mAdView = findViewById(R.id.adViewAct4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // fetch and link variables
        Button eval_app = findViewById(R.id.eval_app);
        Button comm_dev = findViewById(R.id.comm_dev);
        TextView bodyText = findViewById(R.id.bodyText);
        TextView rights = findViewById(R.id.rights);
        // set Onclick.. "communicate with developer"
        final Intent i = EmailIntentBuilder.from(getApplicationContext()).to("hb.tech.business@gmail.com").
                subject(" التواصل مع : HB TECH").body("السلام عليكم ,, \n" +
                "اكتب ما تود ارساله هنا من فضلك , وسوف نرد عليك في أقرب الآجال بإذن الله \n وتقبلوا منا فائق الاحترام .")
                .build();
        comm_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                assert telephonyManager != null;
                String dz = telephonyManager.getSimCountryIso();
                boolean dziriUser = "dz".equals(dz);
                if (!dziriUser) {
                    startActivity(Intent.createChooser(i, "يرجى اختيار تطبيق لترسل به الإيميل "));
                } else {

                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "0556765919", null));
                    startActivity(call);

                }
            }
        });
        //set Onclick.. evaluate the app
         eval_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }         }
        });


        // text view "About the App" ..
        String string = "   إن تطبيق  \" قصص وعبر للأطفال \"  تطبيق وليد رغبة في إفادة النشء القادم و تقوية الأخلاق والمبادئ الدينية في نفوسهم . فمهمتنا ببساطة تحفيز حس الخيال والإبداع لدى الأطفال و المساهمة في  التكوين الأمثل للجيل القادم بإذن الله .\n" +
                "\n" +
                "   لذلك نرجوا منكم مساعدتنا في نشر التطبيق و توسعة جمهوره لتعم الفائدة وذلك من خلال خطوتين بسيطتين :\n\n" +
                "1/  تقييم التطبيق بخمس نجوم محفزة لنا.\n\n" +
                "2/ الضغط على الإعلانات التي تظهر لكم قدر المستطاع لكي نستطيع تمويل التطبيق و  تكاليفه.";
        bodyText.setText(string);
// rights text ....

        String copy = getString(R.string.u00a9_2019_all_rights_reserved) + " ";
        String rightReserved = copy + "2020" + " All Rights Reserved .";
        rights.setText(rightReserved);


    }

    public void cook(View vv) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + "hb.techs.lala")));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + "hb.techs.lala")));
        }
    }

    private void loadInterstitial() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial2_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void showInter() {
        if (mInterstitialAd == null)
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
                    Toast.makeText(act4.this, "فضل جلب الإعلان", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                    Toast.makeText(act4.this, "نشكرك جدا على الضغط على الإعلان انت تساعدنا بهذه الطريقة", Toast.LENGTH_SHORT).show();
                    if (mInterstitialAd != null)
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());

                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                    if (mInterstitialAd != null)
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the interstitial ad is closed.
                    if (mInterstitialAd != null)
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        showInter();
        startActivity(new Intent(this, MainActivity.class));

    }
}
