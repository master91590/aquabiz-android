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

public class sfc03Species04_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private final Context mContext2;
    private final int mResource2;

    public sfc03Species04_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        LinearLayout llHeader = convertView2.findViewById(R.id.llHeader);
        LinearLayout llNormal = convertView2.findViewById(R.id.llNormal);
        LinearLayout llBold = convertView2.findViewById(R.id.llBold);
        LinearLayout llItalic = convertView2.findViewById(R.id.llItalic);
        LinearLayout llNote = convertView2.findViewById(R.id.llNote);
        LinearLayout llLegend = convertView2.findViewById(R.id.llLegend);
        TextView txtHeader = convertView2.findViewById(R.id.txtHeader);
        TextView txtNormalParticular = convertView2.findViewById(R.id.txtNormalParticular);
        TextView txtNormalVariables = convertView2.findViewById(R.id.txtNormalVariables);
        TextView txtBoldParticular = convertView2.findViewById(R.id.txtBoldParticular);
        TextView txtBoldParticular2 = convertView2.findViewById(R.id.txtBoldParticular2);
        TextView txtBoldVariables = convertView2.findViewById(R.id.txtBoldVariables);
        TextView txtItalicParticular = convertView2.findViewById(R.id.txtItalicParticular);
        TextView txtItalicVariables = convertView2.findViewById(R.id.txtItalicVariables);
        TextView txtNote = convertView2.findViewById(R.id.txtNote);
        TextView lblLegend = convertView2.findViewById(R.id.lblLegend);
        TextView txtLegend = convertView2.findViewById(R.id.txtLegend);
        View viewLine = convertView2.findViewById(R.id.viewLine);

        StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "|");
        String ano = tokensHiwalay.nextToken();
        String particular = tokensHiwalay.nextToken();
        String variable = tokensHiwalay.nextToken();

        switch (ano) {
            case "header":
                llHeader.setVisibility(View.VISIBLE);
                llNormal.setVisibility(View.GONE);
                llBold.setVisibility(View.GONE);
                llItalic.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
                llNote.setVisibility(View.GONE);
                llLegend.setVisibility(View.GONE);
                txtHeader.setText(particular);
                break;
            case "bold":
                llHeader.setVisibility(View.GONE);
                llNormal.setVisibility(View.GONE);
                llBold.setVisibility(View.VISIBLE);
                llItalic.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
                llNote.setVisibility(View.GONE);
                llLegend.setVisibility(View.GONE);
                StringTokenizer tokensParticular = new StringTokenizer(particular, "-");
                txtBoldParticular.setText(tokensParticular.nextToken());
                txtBoldParticular2.setText(tokensParticular.nextToken());
                if(variable.equals("wala"))txtBoldVariables.setVisibility(View.GONE);
                else {
                    txtBoldVariables.setVisibility(View.VISIBLE);
                    txtBoldVariables.setText(variable);
                }
                break;
            case "italic":
                llHeader.setVisibility(View.GONE);
                llNormal.setVisibility(View.GONE);
                llBold.setVisibility(View.GONE);
                llItalic.setVisibility(View.VISIBLE);
                viewLine.setVisibility(View.GONE);
                llNote.setVisibility(View.GONE);
                llLegend.setVisibility(View.GONE);
                txtItalicParticular.setText(particular);
                txtItalicVariables.setText(variable);
                break;
            case "line":
                llHeader.setVisibility(View.GONE);
                llNormal.setVisibility(View.GONE);
                llBold.setVisibility(View.GONE);
                llItalic.setVisibility(View.GONE);
                viewLine.setVisibility(View.VISIBLE);
                llNote.setVisibility(View.GONE);
                llLegend.setVisibility(View.GONE);
                break;
            case "note":
                llHeader.setVisibility(View.GONE);
                llNormal.setVisibility(View.GONE);
                llBold.setVisibility(View.GONE);
                llItalic.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
                llNote.setVisibility(View.VISIBLE);
                llLegend.setVisibility(View.GONE);
                txtNote.setText(particular);
                break;
            case "legend":
                llHeader.setVisibility(View.GONE);
                llNormal.setVisibility(View.GONE);
                llBold.setVisibility(View.GONE);
                llItalic.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
                llNote.setVisibility(View.GONE);
                llLegend.setVisibility(View.VISIBLE);
                lblLegend.setText(particular);
                txtLegend.setText(variable);
                break;
            default:
                llHeader.setVisibility(View.GONE);
                llNormal.setVisibility(View.VISIBLE);
                llBold.setVisibility(View.GONE);
                llItalic.setVisibility(View.GONE);
                viewLine.setVisibility(View.GONE);
                llNote.setVisibility(View.GONE);
                llLegend.setVisibility(View.GONE);
                txtNormalParticular.setText(particular);
                txtNormalVariables.setText(variable);
                break;
        }

        return convertView2;
    }


}
