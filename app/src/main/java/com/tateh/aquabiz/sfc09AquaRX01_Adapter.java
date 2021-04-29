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

public class sfc09AquaRX01_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private Context mContext2;
    private int mResource2;

    public sfc09AquaRX01_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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
        String isda1 = tokens1.nextToken();
        String img1 = tokens1.nextToken();
        String sakit1 = tokens1.nextToken();
        imgSpecies1.setImageBitmap(setting_global.StringToBitMap(img1));
        txtSpecies1.setText(isda1);
        rlSpecies1.setOnClickListener(v -> {
            setting_global.editpreferences(mContext2, R.string.sfc09_aquarx02_selected, isda1 + "♣" + sakit1);
            setting_global.gointent(mContext2, sfc09AquaRX02.class);
        });

        if (!kabuuan2.equals("-")){
            rlSpecies2.setVisibility(View.VISIBLE);
            StringTokenizer tokens2 = new StringTokenizer(kabuuan2, "♣");
            String isda2 = tokens2.nextToken();
            String img2 = tokens2.nextToken();
            String sakit2 = tokens2.nextToken();
            imgSpecies2.setImageBitmap(setting_global.StringToBitMap(img2));
            txtSpecies2.setText(isda2);
            rlSpecies2.setOnClickListener(v -> {
                setting_global.editpreferences(mContext2, R.string.sfc09_aquarx02_selected, isda2 + "♣" + sakit2);
                setting_global.gointent(mContext2, sfc09AquaRX02.class);
            });
        }else rlSpecies2.setVisibility(View.GONE);


        return convertView2;
    }
}
