package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc05Locator01_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private Context mContext2;
    private int mResource2;

    public sfc05Locator01_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        TextView txtName = convertView2.findViewById(R.id.txtName);
        TextView txtAddress = convertView2.findViewById(R.id.txtAddress);

        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        tokens.nextToken();
        String name = tokens.nextToken();
        tokens.nextToken();
        String address = tokens.nextToken();
        txtName.setText(name);
        txtAddress.setText(address);

        return convertView2;
    }
}
