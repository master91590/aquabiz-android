package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class setting_ExpandableListView_Adapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> listDataHeader;
    private final HashMap<String, List<String>> listHashMap;
    private final String ano;

    public setting_ExpandableListView_Adapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap, String ano) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
        this.ano = ano;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return Objects.requireNonNull(listHashMap.get(listDataHeader.get(i))).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return Objects.requireNonNull(listHashMap.get(listDataHeader.get(i))).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String)getGroup(i);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(ano.equals("sfc9")) {
                if (inflater != null) {
                    view = inflater.inflate(R.layout.layout_expandable_listgroup, null);
                }
            }else if(ano.equals("species")){
                if (inflater != null) {
                    view = inflater.inflate(R.layout.layout_expandable_listgroup, null);
                }
            }
        }
        TextView lblListHeader = null;
        if (view != null) lblListHeader = view.findViewById(R.id.lblListHeader);
        if (lblListHeader != null) lblListHeader.setText(headerTitle);
        return view;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int i, int i1, boolean b, @NonNull View view, @NonNull ViewGroup viewGroup) {
        final String childText = (String)getChild(i,i1);

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_expandable_listitem,null);
        }

        TextView txtListChild = view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);

        if(ano.equals("species")) {
            SpannableString ss = new SpannableString(txtListChild.getText());
            String myString = txtListChild.getText().toString();
            int cj1 = myString.indexOf("[");
            int cj2 = myString.indexOf("]");
            final int bilang=i;

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View textView) {

                    setting_global.editpreferences(context, R.string.sfc03_saan, "faq");
                    String paano = setting_global.getpreferences(context, R.string.sfc03_howtogrow);
                    if (bilang==0){
                        if(setting_global.getpreferences(context, R.string.sfc03_costandreturn).equals("wala")) setting_global.displayNoDataAvailable(context);
                        else {
                            setting_global.gointent(context, sfc03Species04.class);
                        }
                    }else if(bilang==1 || bilang==2){
                        if(paano.equals("wala")) setting_global.displayNoDataAvailable(context);
                        else {
                            setting_global.gointent(context, sfc03Species04.class);
                        }
                    }else if(bilang==3){
                        //setting_global.gointent(context, sfc10Toolkit02.class);
                    }else if(bilang==4){
                        if(setting_global.getpreferences(context, R.string.sfc03_products).equals("wala")) setting_global.displayNoDataAvailable(context);
                        else {
                            String isda = setting_global.getpreferences(context, R.string.sfc03_species_name);
                            String produkto = setting_global.getpreferences(context, R.string.sfc03_products);
                            setting_global.editpreferences(context, R.string.sfc04_products02_selected, isda + "♣" + produkto);
                            if (setting_global.getpreferences(context, R.string.sfc04_products01).equals("0")) {
                                if (setting_global.isNetworkAvailable(context)) {
                                    setting_global.ShowProgressDialog(context);
                                    setting_connection productsBackgroundTask = new setting_connection(context);
                                    productsBackgroundTask.execute("Products", "aquabiz");
                                } else setting_global.noInternetDisplay(context);
                            } else setting_global.gointent(context, sfc04Products02.class);
                        }
                    }else if(bilang==5){
                        if(setting_global.getpreferences(context, R.string.sfc05_branch).equals("0")){
                            if(setting_global.isNetworkAvailable(context)){
                                setting_global.ShowProgressDialog(context);
                                setting_connection productsBackgroundTask = new setting_connection(context);
                                productsBackgroundTask.execute("Branch", "aquabiz");
                            }else setting_global.noInternetDisplay(context);
                        }else setting_global.gointent(context, sfc05Locator01.class);
                    }
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(ContextCompat.getColor(context, R.color.green_500));
                    ds.setUnderlineText(false);
                }
            };
            ss.setSpan(clickableSpan, cj1, cj2 + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            txtListChild.setText(ss);
            txtListChild.setMovementMethod(LinkMovementMethod.getInstance());
            txtListChild.setHighlightColor(Color.TRANSPARENT);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
