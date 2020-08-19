package hb.tech.add_baby_stories;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

public class StoriesAdapter extends FirestoreRecyclerAdapter<File_item, StoriesAdapter.ViewHolder> {

    public boolean UsersPost = false;


    onItemClick mlistener;
    Context cc;
    static FirestoreRecyclerOptions<File_item> options;

    public StoriesAdapter(@NonNull FirestoreRecyclerOptions<File_item> optioans, Context cc) {
        super(optioans);
        this.cc = cc;
        options = optioans;

    }

    public void setOnitemClickListener(onItemClick listener) {
        mlistener = listener;
    }


    @NonNull
    @Override
    public StoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courses_file_item, parent, false);
        return new StoriesAdapter.ViewHolder(view, mlistener);
    }

    @Override
    protected void onBindViewHolder(@NonNull  StoriesAdapter.ViewHolder holder,  int position, @NonNull  File_item model) {
        holder.setTxtTitle(model.getTitle());
        FirebaseStorage.getInstance().getReference("images/").child("story"+model.getDay()+".png.jpg")
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
              holder.setImageFile(uri);
            }
        });
        holder.setTxtDay(model.getDay());




    }


    public interface onItemClick {
        void onClick(int position, Long itemId);


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {


        private TextView txtTitle , txtday;
        private ImageView imagePoster;

        public ViewHolder(final View itemView, final onItemClick list) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list != null) {
                        int position = getAdapterPosition();
                        Long l = getItemId();
                        if (position != RecyclerView.NO_POSITION) {
                            list.onClick(position, l);
                        }

                    }
                }

            });
            txtday = itemView.findViewById(R.id.day);
            txtTitle = itemView.findViewById(R.id.title);

            imagePoster = itemView.findViewById(R.id.imgGrid);


        }




        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }

        public void setTxtDay(String string) {
            txtday.setText(string);
        }


        public void setImageFile(Uri path) {

            Glide
                    .with(itemView.getContext())
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(600, 200) // resizes the image to these dimensions (in pixel)
                    .fitCenter() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
                    .into(imagePoster);





        }


    }


}

