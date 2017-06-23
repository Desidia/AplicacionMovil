package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by diego on 16-06-2017.
 */

public class DrawerItemPrincipalAdapter extends ArrayAdapter {

    public DrawerItemPrincipalAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lateral_principal, null);
        }


        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);

        DrawerItemPrincipal item = (DrawerItemPrincipal) getItem(position);
        icon.setImageResource(item.getIconId());
        return convertView;
    }
}
