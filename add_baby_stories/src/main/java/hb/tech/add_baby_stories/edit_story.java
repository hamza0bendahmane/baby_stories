package hb.tech.add_baby_stories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import javax.annotation.Nonnull;

public class edit_story extends AppCompatActivity {

    AlertDialog dia;
    static Uri Image_picked =null ;
    ImageView show_picked_image ;
    EditText articleTitle , editor_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_story);
        show_picked_image =findViewById(R.id.show_picked_image);
        articleTitle =findViewById(R.id.articleTitle);
        editor_view =findViewById(R.id.editor_view);
        Bundle b = getIntent().getExtras();
        FirebaseStorage.getInstance().getReference("images/").child("story"+b.getString("day")+".png.jpg")
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                updateUI(b.getString("title"),b.getString("body"),uri);

            }
        });

    }


    public void pick_image(View view) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 0);


    }

    public void updateUI(String title,String body , Uri ima) {
        Glide
                .with(getApplicationContext())
                .load(ima)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(600, 200) // resizes the image to these dimensions (in pixel)
                .fitCenter() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
                .into(show_picked_image);
        articleTitle.setText(title);
        editor_view.setText(body);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null && data.getData() != null) {
            Image_picked = data.getData();
            show_picked_image.setImageURI(Image_picked);

        }
    }

    private void saveUserChanges() {



        final HashMap<String, Object> map = new HashMap<>();
        if (Image_picked != null) {
            FirebaseStorage.getInstance().getReference("images/" + "story"+
                    getIntent().getExtras().getString("day")+".png.jpg")
                    .putFile(Image_picked).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@Nonnull Task<UploadTask.TaskSnapshot> task) {
                 if (task.isSuccessful())
                     Toast.makeText(edit_story.this, "Image Uploaded Successfuly Boss", Toast.LENGTH_SHORT).show();
                 else
                     Toast.makeText(edit_story.this, "image failed", Toast.LENGTH_SHORT).show();

                }
            });


        }


            // Name is valid save it ....
            map.put("title", articleTitle.getText().toString().trim());
            map.put("body", editor_view.getText().toString().trim());
            map.put("day", getIntent().getExtras().getString("day"));



        DocumentReference ref = FirebaseFirestore.getInstance().collection("Stories").document(getIntent().getExtras()
        .getString("day"));
            ref.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(edit_story.this, "SUCCESSFULLY UPDATED THIS STORY ", Toast.LENGTH_SHORT).show();
                    dia.dismiss();
                    onBackPressed();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(edit_story.this, "FAILED UPDATED THIS STORY "+e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

    }

    public void cancel(View v) {
        onBackPressed();
    }

    public void post(View view) {
   dia = new AlertDialog.Builder(edit_story.this).setMessage("" +
            "are you sure Boss with this changes ??")
            .setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    saveUserChanges();

                }
            }).show();

    }

}
