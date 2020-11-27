package hb.techs.baby_stories;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FavorisAdapter  extends BaseAdapter {

        Context context;
        List<storyItem> storyItemList;

        public FavorisAdapter(Context context, List<storyItem> storyItemList) {
            this.context = context;
            this.storyItemList = storyItemList;
        }


    @Override
    public int getCount() {
        return storyItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return new storyItem(storyItemList.get(position).Id,storyItemList.get(position).title,
                storyItemList.get(position).story
        , storyItemList.get(position).imageId);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        TextView tx = convertView.findViewById(R.id.textGrid);
        ImageView imageView =convertView.findViewById(R.id.imgGrid);
        String title = position+1 + ") "+storyItemList.get(position).getTitle();
        tx.setText(title);
        int pos = storyItemList.get(position).getImageId();
        imageView.setImageResource(pos);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/kufi.ttf");
        tx.setTypeface(typeface);
        return convertView;
    }
}
