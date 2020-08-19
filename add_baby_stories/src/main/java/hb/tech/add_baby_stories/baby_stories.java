package hb.tech.add_baby_stories;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Timestamp;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.LayoutInflater;
import android.view.View;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;
import java.util.Queue;

public class baby_stories extends AppCompatActivity {
    StoriesAdapter adapter;
    Query query0 ;
    FirestoreRecyclerOptions<File_item> options1st ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_stories);
       int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);




            query0 = FirebaseFirestore.getInstance()
                    .collection("Stories")
                    .orderBy("day", Query.Direction.ASCENDING);
            options1st =
                    new FirestoreRecyclerOptions.Builder<File_item>()
                            .setQuery(query0, File_item.class).build();


                FloatingActionButton fab = findViewById(R.id.fab);
                RecyclerView stories = findViewById(R.id.recyclerView);
                LayoutManager manager=  new LinearLayoutManager(baby_stories.this);
                stories.setHasFixedSize(true);
                stories.setLayoutManager(manager);
                adapter = new StoriesAdapter(options1st,getApplicationContext());
                stories.setAdapter(adapter);
                adapter.setOnitemClickListener(new StoriesAdapter.onItemClick() {
                @Override
                public void onClick(int position, Long l) {
                    Bundle b = new Bundle();
                    //       if (position != -1 &&  options!=null && options.getSnapshots()!=null && position< options.getSnapshots().size()) {
                    File_item item = adapter.getItem(position);
                    b.putString("title", item.getTitle());
                    b.putString("body", item.getBody());
                    b.putString("day", item.getDay());
                    Intent i = new Intent(baby_stories.this, StoryActivity.class);
                    i.putExtras(b);
                    startActivity(i);

                }


            });



    fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            sendNotification();
            }
        });
    }
    private void  sendNotification(){

    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

}
