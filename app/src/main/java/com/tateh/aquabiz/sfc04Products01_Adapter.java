package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc04Products01_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private Context mContext2;
    private int mResource2;

    public sfc04Products01_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
        super(context, resource, objects);
        mContext2 = context;
        mResource2 = resource;
    }

    @NonNull
    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, @Nullable View convertView2, @NonNull ViewGroup parent) {
        String kabuuan = Objects.requireNonNull(getItem(position)).getKabuuan();

        new sfc03Species01_Item(kabuuan);

        LayoutInflater inflater2 = LayoutInflater.from(mContext2);
        convertView2 = inflater2.inflate(mResource2, parent, false);

        ImageView img = convertView2.findViewById(R.id.img);
        TextView txt = convertView2.findViewById(R.id.txt);

        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        String name = tokens.nextToken();
        String icon = tokens.nextToken();
        img.setImageBitmap(setting_global.StringToBitMap(icon));
        txt.setText(name);

        return convertView2;
    }
}
