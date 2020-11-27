package hb.techs.baby_stories;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class act2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);
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



}
