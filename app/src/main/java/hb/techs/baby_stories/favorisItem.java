package hb.techs.baby_stories;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class favorisItem extends AppCompatActivity {
    public DatabaseHandler db;
    public static int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris_item);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getInt("id");
        TextView title = findViewById(R.id.tx);
        ImageView img = findViewById(R.id.im);
        TextView text = findViewById(R.id.txx);
        Resources res = getResources();

       // position in GridView ....
        int position = bundle.getInt("position");
        // position in original table
        String title1 = bundle.getString("title");
        String story1 = bundle.getString("story");

        title.setText(title1);
        title.setTextColor(getResources().getColor(R.color.color3));
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/kufi.ttf");
        title.setTypeface(typeface1);
        int imgId = bundle.getInt("imageId");
        img.setImageResource(imgId);
        text.setText(story1);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/messi.ttf");
        text.setTypeface(typeface);
        ImageButton delete = findViewById(R.id.delete_story);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(favorisItem.this);
                dialog.setMessage("هل تريد فعلا حذف القصة من المفضلة");
                dialog.setPositiveButton("حذف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db = new DatabaseHandler(favorisItem.this);
                        storyItem ss = db.getstoryItem(ID);
                        db.deleteStory(ss);
                        dialog.dismiss();
                        startActivity(new Intent(favorisItem.this,favoris_offline.class));
                        finish();
                    }
                });
                dialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

    }





    }

