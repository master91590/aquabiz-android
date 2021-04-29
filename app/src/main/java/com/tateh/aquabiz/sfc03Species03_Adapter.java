package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc03Species03_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private final Context mContext2;
    private final int mResource2;

    public sfc03Species03_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        TextView txtTitle1 = convertView2.findViewById(R.id.txtTitle1);
        LinearLayout llNumber = convertView2.findViewById(R.id.llNumber);
        TextView lblNumber = convertView2.findViewById(R.id.lblNumber);
        TextView txtNumber = convertView2.findViewById(R.id.txtNumber);
        LinearLayout llBullet = convertView2.findViewById(R.id.llBullet);
        TextView txtBullet = convertView2.findViewById(R.id.txtBullet);
        TextView txtTitle2 = convertView2.findViewById(R.id.txtTitle2);
        TextView txtNormal = convertView2.findViewById(R.id.txtNormal);
        TextView txtItalic = convertView2.findViewById(R.id.txtItalic);
        ImageView img = convertView2.findViewById(R.id.img);
        StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "|");
        String ano = tokensHiwalay.nextToken();
        String ilalagay = tokensHiwalay.nextToken();

        switch (ano) {
            case "title":
                txtTitle1.setVisibility(View.VISIBLE);
                llNumber.setVisibility(View.GONE);
                llBullet.setVisibility(View.GONE);
                txtTitle2.setVisibility(View.GONE);
                txtNormal.setVisibility(View.GONE);
                txtItalic.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                txtTitle1.setText(ilalagay);
                break;
            case "bullet":
                txtTitle1.setVisibility(View.GONE);
                llNumber.setVisibility(View.GONE);
                llBullet.setVisibility(View.VISIBLE);
                txtTitle2.setVisibility(View.GONE);
                txtNormal.setVisibility(View.GONE);
                txtItalic.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                txtBullet.setText(ilalagay);
                break;
            case "bold":
                txtTitle1.setVisibility(View.GONE);
                llNumber.setVisibility(View.GONE);
                llBullet.setVisibility(View.GONE);
                txtTitle2.setVisibility(View.VISIBLE);
                txtNormal.setVisibility(View.GONE);
                txtItalic.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                txtTitle2.setText(ilalagay);
                break;
            case "none":
                txtTitle1.setVisibility(View.GONE);
                llNumber.setVisibility(View.GONE);
                llBullet.setVisibility(View.GONE);
                txtTitle2.setVisibility(View.GONE);
                txtNormal.setVisibility(View.VISIBLE);
                txtItalic.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                txtNormal.setText(ilalagay);
                break;
            case "italic":
                txtTitle1.setVisibility(View.GONE);
                llNumber.setVisibility(View.GONE);
                llBullet.setVisibility(View.GONE);
                txtTitle2.setVisibility(View.GONE);
                txtNormal.setVisibility(View.GONE);
                txtItalic.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                txtItalic.setText(ilalagay);
                break;
            case "image":
                txtTitle1.setVisibility(View.GONE);
                llNumber.setVisibility(View.GONE);
                llBullet.setVisibility(View.GONE);
                txtTitle2.setVisibility(View.GONE);
                txtNormal.setVisibility(View.GONE);
                txtItalic.setVisibility(View.GONE);
                img.setVisibility(View.VISIBLE);
                img.setImageBitmap(setting_global.StringToBitMap(ilalagay));
                break;
            default:
                txtTitle1.setVisibility(View.GONE);
                llNumber.setVisibility(View.VISIBLE);
                llBullet.setVisibility(View.GONE);
                txtTitle2.setVisibility(View.GONE);
                txtNormal.setVisibility(View.GONE);
                txtItalic.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                lblNumber.setText(ano);
                txtNumber.setText(ilalagay);
                break;
        }

        return convertView2;
    }


}
