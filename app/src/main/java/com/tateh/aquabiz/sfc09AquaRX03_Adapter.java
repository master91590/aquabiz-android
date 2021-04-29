package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc09AquaRX03_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private final Context mContext2;
    private final int mResource2;

    public sfc09AquaRX03_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        LinearLayout LLTitle = convertView2.findViewById(R.id.LLTitle);
        TextView lblTitle = convertView2.findViewById(R.id.lblTitle);
        TextView txtTitle = convertView2.findViewById(R.id.txtTitle);
        TextView txtWhat = convertView2.findViewById(R.id.txtWhat);
        TextView txtBold = convertView2.findViewById(R.id.txtBold);
        TextView txtSource = convertView2.findViewById(R.id.txtSource);
        LinearLayout LLbullet = convertView2.findViewById(R.id.LLbullet);
        TextView txtBullet = convertView2.findViewById(R.id.txtBullet);

        String selected = setting_global.getpreferences(mContext2, R.string.sfc09_aquarx02_selected);
        StringTokenizer tokens = new StringTokenizer(selected, "♣");
        String isda = tokens.nextToken();

        StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "|");
        String ano = tokensHiwalay.nextToken();
        String ilalagay = tokensHiwalay.nextToken();

        switch (ano) {
            case "disease":
                LLTitle.setVisibility(View.VISIBLE);
                txtWhat.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                lblTitle.setText("Disease:");
                txtTitle.setText(ilalagay);
                break;
            case "causative":
                LLTitle.setVisibility(View.VISIBLE);
                txtWhat.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                lblTitle.setText("Causative Agents:");
                ilalagay = ilalagay.replaceAll("\\\\n", "\n");
                txtTitle.setText(ilalagay);
                break;
            case "affected":
                LLTitle.setVisibility(View.VISIBLE);
                txtWhat.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                lblTitle.setText("Species Affected:");
                txtTitle.setText(isda);
                break;
            case "symptoms":
                txtWhat.setVisibility(View.VISIBLE);
                LLTitle.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                txtWhat.setText("Common Symptoms:");
                break;
            case "prevention":
                txtWhat.setVisibility(View.VISIBLE);
                LLTitle.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                txtWhat.setText("Prevention and Control:");
                break;
            case "bold":
                txtBold.setVisibility(View.VISIBLE);
                txtWhat.setVisibility(View.GONE);
                LLTitle.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                txtBold.setText(ilalagay);
                break;
            case "bullet":
                LLbullet.setVisibility(View.VISIBLE);
                LLTitle.setVisibility(View.GONE);
                txtWhat.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                txtSource.setVisibility(View.GONE);
                txtBullet.setText(ilalagay);
                break;
            default:
                txtSource.setVisibility(View.VISIBLE);
                LLTitle.setVisibility(View.GONE);
                txtWhat.setVisibility(View.GONE);
                txtBold.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                txtSource.setText(ilalagay);
                break;
        }

        return convertView2;
    }
}
