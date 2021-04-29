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

public class sfc09AquaRX02_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private Context mContext2;
    private int mResource2;

    public sfc09AquaRX02_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        TextView txtType = convertView2.findViewById(R.id.txtType);
        RelativeLayout rlDisease = convertView2.findViewById(R.id.rlDisease);
        ImageView imgDisease = convertView2.findViewById(R.id.imgDisease);
        TextView txtDiseases = convertView2.findViewById(R.id.txtDiseases);
        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        if(tokens.countTokens() == 1){
            txtType.setText(tokens.nextToken());
            txtType.setVisibility(View.VISIBLE);
            rlDisease.setVisibility(View.GONE);
        }else {
            rlDisease.setVisibility(View.VISIBLE);
            txtType.setVisibility(View.GONE);
            tokens.nextToken();
            String name = tokens.nextToken();
            tokens.nextToken();
            tokens.nextToken();
            tokens.nextToken();
            String images = tokens.nextToken();
            StringTokenizer tokens_img = new StringTokenizer(images, "♥");
            String img="";
            while(tokens_img.hasMoreTokens()){
                String img_hiwalay = tokens_img.nextToken();
                StringTokenizer tokens_img_hiwalay = new StringTokenizer(img_hiwalay, "|");
                String anong_isda = tokens_img_hiwalay.nextToken();
                img = tokens_img_hiwalay.nextToken();
                if(anong_isda.equals(sfc09AquaRX02.isda)) break;
                else if(anong_isda.equals("lahat")) break;
            }

            imgDisease.setImageBitmap(setting_global.StringToBitMap(img));
            txtDiseases.setText(name);

            rlDisease.setOnClickListener(v -> {
                setting_global.editpreferences(mContext2, R.string.sfc09_aquarx03, kabuuan);
                setting_global.gointent(mContext2, sfc09AquaRX03.class);
            });
        }

        return convertView2;
    }
}
