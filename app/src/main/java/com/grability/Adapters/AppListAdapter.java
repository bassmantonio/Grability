package com.grability.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.grability.Activities.*;
import com.grability.DTO.EntryDTO;
import com.squareup.picasso.Picasso;
import java.util.Vector;


/**
 * This class used for inflate a custom adapter for ListViews and Gridviews.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class AppListAdapter extends ArrayAdapter<EntryDTO> {

    /**
    * Private properties
     * Entries Vector
     * App COntext
     * */
    private Vector<EntryDTO> entriesArray;
    private Context appContext;

    /**
     * Constructor for initialize private properties
     * @param context; Sets the app context
     * @param objects; Sets the Entries Vector
     */
    public AppListAdapter(Context context, Vector<EntryDTO> objects) {
        super(context, R.layout.list_item_app, objects);
        this.entriesArray = objects;
        this.appContext = context;
    }

    /**
     * Default method for inflate and make items into custom adapter
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item_app, null);

        ImageView ivAppIcon = (ImageView) item.findViewById(R.id.ivAppIconLow);
        TextView txtAppName = (TextView) item.findViewById(R.id.txtAppName);
        TextView txtAppArtist = (TextView) item.findViewById(R.id.txtAppArtist);

        String imageURL = "";
        int imgHeight = 0;

        if (ivAppIcon != null) {
            imageURL = entriesArray.elementAt(position).getImages().elementAt(0).getURL();
            imgHeight = Integer.parseInt(entriesArray.elementAt(position).getImages().elementAt(0).getHeight());

        }else
        {
            ivAppIcon = (ImageView) item.findViewById(R.id.ivAppIconMed);
            if (ivAppIcon != null) {
                imageURL = entriesArray.elementAt(position).getImages().elementAt(1).getURL();
                imgHeight = Integer.parseInt(entriesArray.elementAt(position).getImages().elementAt(1).getHeight());

            }else
            {
                ivAppIcon = (ImageView) item.findViewById(R.id.ivAppIconHig);
                imageURL = entriesArray.elementAt(position).getImages().elementAt(2).getURL();
                imgHeight = Integer.parseInt(entriesArray.elementAt(position).getImages().elementAt(2).getHeight());
            }
        }

        Picasso.with(this.appContext)
                .load(imageURL)
                .placeholder(R.drawable.loader)
                .error(R.drawable.blank)
                .resize(imgHeight,imgHeight)
                .into(ivAppIcon);


        //ivAppIconPort.setImageResource(R.drawable.abc);
        txtAppName.setText(entriesArray.elementAt(position).getName());
        txtAppArtist.setText(entriesArray.elementAt(position).getArtist().getName());

        return (item);
    }
}
