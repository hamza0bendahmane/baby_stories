package hb.techs.baby_stories;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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



public class help extends AppCompatActivity {
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadInterstitial();

        AdView mAdView = findViewById(R.id.adViewActHelp);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // variables ...
        TextView title = findViewById(R.id.titleHelp);
        TextView body = findViewById(R.id.bodyHelp);

        // set text & typeFace title
        title.setText("فوائد قراءة القصص للأطفال , وبعض النصائح لقضاء وقت ممتع");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/kufi.ttf");
        title.setTypeface(typeface1);

        // set text & typeFace body
        body.setText("فوائد قراءة القصص للطفل قبل النوم ؟\n" +
                "إن قراءة القصص على طفلك لا تساعد فقط في اعطاء نوم هادئ لطفلك بل تعزز ابداعاته مستقبلا و في سن مبكرة فحسب دراسات بعض المعاهد اللتي تقول انه عندما يقرأ الآباء او الامهات او المربيات \n" +
                "قصص و يكون هناك تفاعل لفظي مع طفلك بما فيه القراءة فان طفلك يتعلم اكثر و يكون ناضجا و تزيد من القيم الاخلاقية مع اختيار أحسن القصص , و تكون بداية قراءة القصص على طفلك قبل النوم في سن مبكرة لتعزيز خياله الحقيقي\n" +
                " , حتى قبل الولادة (اثناء الحمل ) فتكون القراءة بصوت عال قليلا فان طفلك يسمعك و هو في الرحم , و افضل توقيت هو قبل النوم فيكون روتين يومي فهذا يهدأ طفلك ايضا و يجعله يفهم انه حان وقت النوم ( 18:30 الى 20:30) فاذا تجاوزتي هذا الوقت فقد يكون طفلك متعب.\n" +
                "\n" +
                "\n" +
                "ـ فوائد قراءة القصص على طفلك قبل النوم :\n" +
                "ان القراءة تساعد طفلك على معالجة الكلمات بسرعة في الوقت اللاحق من حياته , و هي وسيلة رائعة لتحسين التفاعل بينك و بين طفلك و هذه بعض الفوائد الاخرى .\n" +
                "ـ تطور مهارة التواصل : هي تساعد على تطوير مهارات التواصل الشفوي لطفلك و تعزز الذاكرة , و كذلك اكتساب مهارة معرفة اللغة و هي وسيلة لتعزيز المفردات و تراكيب الجمل المختلفة في سن مبكرة جدا , و مع مرور الوقت من المهم ايضا تعلم لغة الجسد و الطرق اللفظية و قراءة الكلمات .\n" +
                "ـ التطور الاجتماعي و العاطفي : القصص و طفلك يسيران جنبا الى جنب و يمكن تطور الافكار حول مختلف الالعاب و الحيوانات و الطيور و يكون قد عرف عنهم كل شيء في وقت مبكر , مع استخدام كلمات جديدة و هذا ما يجعله يعبر عن مشاعره .\n" +
                "ـ المهارات المعرفية : قبل بداية طفلك في الكلام يكون قد استوعب معلومات عن اللغة من خلال الاستماع الى القصص و كذلك يتعلم قلب الصفحات و مع استمرار نموه يفهم ان فن القراءة يكون من اليمين الى اليسار ( بالنسبة للغة العربية ) و كذلك تطوير مهارة حل المشاكل مستقبلا .\n" +
                "\n" +
                "\n" +
                "ـ تحسين الانتباه : القراءة قبل النوم هي وسيلة ممتازة لحصول طفلك على الراحة و يمكنه تحسين الاهتمام من خلال القراءة كل يوم .\n" +
                "ـ تخفف من القلق : هي وسيلة جيدة لاسترخاء الجسم و العقل قبل الذهاب الى السرير .\n" +
                "ـ تحسين الشخصية و المعرفة : مع نمو طفلك يكون قد حان الوقت للتاثير على طفلك و تعليمه دروس الحياة فهذا يحسن من شخصيته و معرفته .\n" +
                "ـ تصبح روتين : مع كبر طفلك يكون قد اعتاد على القراءة مما يجعله هو كذلك روتين يومي لنفسه و يصبح يستمتع بها .\n" +
                "\n" +
                "\n" +
                "ـ نصائح للاباء :\n" +
                "لا تجعل القراءة مقلقة لنفسك و لطفلك , بل يجب الابقاء عليها مثيرة للاهتمام لدرجة انك تنتظر ذلك الوقت و تزيد الترابط مع طفلك .\n" +
                "1 ـ استخدام العواطف المختلفة و الاصوات المعبرة فهي تعزز التنمية الاجتماعية و العاطفية لطفل\n" +
                "2 ـ عند القراءة على طفلك حاول ان تفهم  تسائلاته و الاجابة عليها لتعزيز التنمية الاجتماعية و            مهارات التفكير .\n" +
                "3 ـ السماح لطفلك تقليد الاصوات و النظر الى الصور , و تعلم الكلمات .\n" +
                "4 ـ قراءة قصص مترابطةعلى شكل سلسلة للاثارة و الفرح و التقارب .\n" +
                "5 ـ حضن طفلك عند القراءة فهذا يجعله متصل و آمن و دافئ .\n" +
                "6 ـ محاولة تقليد الشخصيات و اصوات الحيوانات أثناء القراءة .\n" +
                "7 ـ الاطفال يحبون قراءة القصص و تكرارها عليهم عدة مرات فلا تقلق من هذا الامر .\n" +
                "8 ـ توقيف تشغيل التلفاز و الراديو اثناء قراءة القصص .\n" +
                "9 ـ التفاعل مع طفلك فهذا هو المفتاح لتحقيق الاستفادة من القصص .\n");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/messi.ttf");
        body.setTypeface(typeface);

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
                    Toast.makeText(help.this, "فضل جلب الإعلان", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                    Toast.makeText(help.this, "نشكرك جدا على الضغط على الإعلان انت تساعدنا بهذه الطريقة", Toast.LENGTH_SHORT).show();
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

    private void loadInterstitial() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
