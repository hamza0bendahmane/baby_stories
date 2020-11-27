package hb.techs.baby_stories;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;

public class act3 extends AppCompatActivity {
public  String title1 ;
public int position ;
public  String story;
public DatabaseHandler db ;
public int imgId;
    boolean isFav ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element);

     LoadAsk();

        TypedArray imgTab = getResources().obtainTypedArray(R.array.long_images);
        TextView title = findViewById(R.id.tx);
        ImageView img = findViewById(R.id.im);
        TextView text = findViewById(R.id.txx);
        ImageButton make_favorite = findViewById(R.id.make_favorite);
        Resources res = getResources();

        Bundle bundle = getIntent().getExtras();
         position = bundle.getInt("position");
         title1 =getResources().getStringArray(R.array.long_title)[position];
        title.setText(title1);
        title.setTextColor(getResources().getColor(R.color.color3));
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/kufi.ttf");
        title.setTypeface(typeface1);
         story = res.getStringArray(R.array.long_story)[position];
         imgId = imgTab.getResourceId(position,0);
        img.setImageResource(imgId);

        imgTab.recycle();
        text.setText(story);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/messi.ttf");
        text.setTypeface(typeface);

        make_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inserting note in db and getting
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
