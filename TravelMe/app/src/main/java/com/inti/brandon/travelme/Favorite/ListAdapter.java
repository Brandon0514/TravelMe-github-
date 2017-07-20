package com.inti.brandon.travelme.Favorite;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inti.brandon.travelme.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ListAdapter extends ArrayAdapter<String> {

    ArrayList<String>  linkData, imageURL, title;
    Context mContext;

    public ListAdapter(Context context, ArrayList<String> linkData, ArrayList<String> imageURL, ArrayList<String> title) {
        super(context, R.layout.listview);
        this.linkData = linkData;
        this.imageURL =imageURL;
        this.title = title;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return linkData.toArray().length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.Photo = (ImageView) convertView.findViewById(R.id.Photo);
            mViewHolder.String = (TextView) convertView.findViewById(R.id.String);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        Log.d("Saved Link Title", linkData.get(position));

        Picasso.with(getContext()).load(imageURL.get(position)).into(mViewHolder.Photo);
        mViewHolder.String.setText(String.valueOf(title.get(position)));

        return convertView;
    }

    static class ViewHolder {
        ImageView Photo;
        TextView String;
    }
}

