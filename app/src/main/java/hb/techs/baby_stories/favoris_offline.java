package hb.techs.baby_stories;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;


public class favoris_offline extends AppCompatActivity {
    public  GridView gridView ;
    DatabaseHandler db ;
    public static List<storyItem> storyItemList = new ArrayList<>();
    public static FavorisAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}


