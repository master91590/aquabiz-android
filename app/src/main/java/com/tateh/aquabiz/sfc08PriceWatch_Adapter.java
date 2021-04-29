package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc08PriceWatch_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private Context mContext2;
    private int mResource2;

    public sfc08PriceWatch_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        TextView txtSpecies = convertView2.findViewById(R.id.txtSpecies);
        LinearLayout LLlabel = convertView2.findViewById(R.id.LLlabel);
        LinearLayout LLvalue = convertView2.findViewById(R.id.LLvalue);
        TextView txtArea = convertView2.findViewById(R.id.txtArea);
        TextView txtSpecification = convertView2.findViewById(R.id.txtSpecification);
        TextView txtPrice = convertView2.findViewById(R.id.txtPrice);


        StringTokenizer tokens = new StringTokenizer(kabuuan, "|");
        String what = tokens.nextToken();
        String value1 = tokens.nextToken();
        String value2 = tokens.nextToken();
        String value3 = tokens.nextToken();
        if(what.equals("name")){
            LLlabel.setVisibility(View.VISIBLE);
            LLvalue.setVisibility(View.GONE);
            txtSpecies.setText(value1);
        }else{
            LLvalue.setVisibility(View.VISIBLE);
            LLlabel.setVisibility(View.GONE);
            txtArea.setText(value1);
            txtSpecification.setText(value2);
            txtPrice.setText(value3);
        }

        return convertView2;
    }
}
