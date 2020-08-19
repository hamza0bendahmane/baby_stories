package hb.tech.add_baby_stories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import javax.annotation.Nonnull;

public class StoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


                TextView title = findViewById(R.id.tx);
                ImageView img = findViewById(R.id.im);
                TextView text = findViewById(R.id.txx);



            findViewById(R.id.edit_story).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inte =new Intent(StoryActivity.this,edit_story.class);
                    Bundle b = new Bundle();
                    b.putString("title", getIntent().getExtras().getString("title"));
                    b.putString("body",getIntent().getExtras().getString("body"));
                    b.putString("day",getIntent().getExtras().getString("day"));
                    inte.putExtras(b);
                    startActivity(inte);

                    finish();
                }
            });

                Bundle bundle = getIntent().getExtras();
                title.setText(bundle.getString("title"));
                Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/kufi.ttf");
                title.setTypeface(typeface1);
                FirebaseStorage.getInstance().getReference("images/").child("story"+bundle.getString("day")+".png.jpg")
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide
                        .with(getApplicationContext())
                        .load(uri)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(600, 200) // resizes the image to these dimensions (in pixel)
                        .fitCenter() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
                        .into(img);
            }
        });


                text.setText(bundle.getString("body"));
                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/messi.ttf");
                text.setTypeface(typeface);


    }



}
