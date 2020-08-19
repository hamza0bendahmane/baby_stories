package com.example.baby_stories;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private Context mContext ;
    private String[] titleTab ;
    private TypedArray iimgTab ;

    public GridAdapter(Context mContext ,char c) {
        this.mContext = mContext;
        TypedArray longTab = mContext.getResources().obtainTypedArray(R.array.long_images);
        TypedArray shortTab = mContext.getResources().obtainTypedArray(R.array.short_images);

            if (c == 's') {
                this.titleTab = mContext.getResources().getStringArray(R.array.short_title);
                this.iimgTab =shortTab;


            } else {
                this.titleTab = mContext.getResources().getStringArray(R.array.long_title);
                this.iimgTab = longTab;

            }


        }





    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout,parent,false);
            TextView tx = convertView.findViewById(R.id.textGrid);
            ImageView imageView =convertView.findViewById(R.id.imgGrid);
            // fetch images table ...

String title = position+1 + ") "+titleTab[position];
                tx.setText(title);
        int Id = iimgTab.getResourceId(position,0);
            imageView.setImageResource(Id);

        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/kufi.ttf");
        tx.setTypeface(typeface);



        return convertView;

    }
    @Override
    public int getCount() {
       return iimgTab.length();
          }

    @Override
    public Object getItem(int position) {
      return new GridClass(titleTab[position],iimgTab.getResourceId(position,0));}


}
