package com.inti.brandon.travelme;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.inti.brandon.travelme.Favorite.ListAdapter;
import com.inti.brandon.travelme.Favorite.SQLiteHelper;
import com.inti.brandon.travelme.RSSFeed.NewsDetails;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment{
    private static final String TAG = "FavoriteFragment";

    SQLiteHelper DatabaseHelper;

    ListView listView;

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listview, container, false);
        listView = (ListView) view.findViewById(R.id.listview);

        context = getActivity();

        populateListView();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DatabaseHelper = new SQLiteHelper(activity);
    }


    public void DeleteData(int id,String url) {
       DatabaseHelper.deleteName(url);
    }


    private void populateListView() {

        //get the data and append to a list
        Cursor data = DatabaseHelper.getData();
        final ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> imageULR = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();


        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
            imageULR.add(data.getString(2));
            title.add(data.getString(3));
        }


        //create the list adapter and set the adapter
        final android.widget.ListAdapter adapter = new ListAdapter(context,listData,imageULR,title);
        Log.d("Saved Link Cout" ,String.valueOf(adapter.getCount()));
        Log.d("List Link Cout" ,String.valueOf(listData.size()));

        listView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(context,NewsDetails.class);
                intent.putExtra("Link",listData.get(position));
                context.startActivity(intent);
            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Delete Link")
                        .setMessage("Do you want to delete this link?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                             int position = pos;

                            public void onClick(DialogInterface dialog, int which) {
                                DeleteData(pos,listData.get(position));
                                populateListView();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_save)
                        .show();

                return true;
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }

}