package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class sfc03Species01_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private Context mContext2;
    private int mResource2;

    public sfc03Species01_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        RelativeLayout rlSpecies1 = convertView2.findViewById(R.id.rlSpecies1);
        RelativeLayout rlSpecies2 = convertView2.findViewById(R.id.rlSpecies2);
        ImageView imgSpecies1 = convertView2.findViewById(R.id.imgSpecies1);
        ImageView imgSpecies2 = convertView2.findViewById(R.id.imgSpecies2);
        TextView txtSpecies1 = convertView2.findViewById(R.id.txtSpecies1);
        TextView txtSpecies2 = convertView2.findViewById(R.id.txtSpecies2);
        StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "♦");
        String kabuuan1 = tokensHiwalay.nextToken();
        String kabuuan2 = tokensHiwalay.nextToken();

        StringTokenizer tokens1 = new StringTokenizer(kabuuan1, "♣");
        String name1 = tokens1.nextToken();
        tokens1.nextToken();
        tokens1.nextToken();
        tokens1.nextToken();
        tokens1.nextToken();
        String img1 = tokens1.nextToken();
        imgSpecies1.setImageBitmap(setting_global.StringToBitMap(img1));
        txtSpecies1.setText(name1);

        if (!kabuuan2.equals("-")){
            rlSpecies2.setVisibility(View.VISIBLE);
            StringTokenizer tokens2 = new StringTokenizer(kabuuan2, "♣");
            String name2 = tokens2.nextToken();
            tokens2.nextToken();
            tokens2.nextToken();
            tokens2.nextToken();
            tokens2.nextToken();
            String img2 = tokens2.nextToken();
            imgSpecies2.setImageBitmap(setting_global.StringToBitMap(img2));
            txtSpecies2.setText(name2);
        }else rlSpecies2.setVisibility(View.GONE);



        rlSpecies1.setOnClickListener(v -> {
            setting_global.editpreferences(mContext2, R.string.sfc03_species02, kabuuan1);
            setting_global.gointent(mContext2, sfc03Species02.class);
        });

        rlSpecies2.setOnClickListener(v -> {
            setting_global.editpreferences(mContext2, R.string.sfc03_species02, kabuuan2);
            setting_global.gointent(mContext2, sfc03Species02.class);
        });

        return convertView2;
    }
}
