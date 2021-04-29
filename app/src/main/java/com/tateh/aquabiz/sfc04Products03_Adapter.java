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

public class sfc04Products03_Adapter extends ArrayAdapter<sfc03Species01_Item> {
    private final Context mContext2;
    private final int mResource2;

    public sfc04Products03_Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<sfc03Species01_Item> objects) {
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

        LinearLayout LLname = convertView2.findViewById(R.id.LLname);
        TextView txtName = convertView2.findViewById(R.id.txtName);
        LinearLayout LLblank = convertView2.findViewById(R.id.LLblank);
        TextView txtBlank = convertView2.findViewById(R.id.txtBlank);
        LinearLayout LLcategory = convertView2.findViewById(R.id.LLcategory);
        TextView txtCategory = convertView2.findViewById(R.id.txtCategory);
        LinearLayout LLculture = convertView2.findViewById(R.id.LLculture);
        TextView txtCulture = convertView2.findViewById(R.id.txtCulture);
        LinearLayout LLpackaging = convertView2.findViewById(R.id.LLpackaging);
        TextView txtpackaging = convertView2.findViewById(R.id.txtpackaging);
        LinearLayout LLdescription = convertView2.findViewById(R.id.LLdescription);
        TextView txtdescription = convertView2.findViewById(R.id.txtdescription);
        LinearLayout LLbullet = convertView2.findViewById(R.id.LLbullet);
        TextView txtBullet = convertView2.findViewById(R.id.txtBullet);
        LinearLayout LLbutton = convertView2.findViewById(R.id.LLbutton);
        AppCompatButton btnOrder = convertView2.findViewById(R.id.btnOrder);
        AppCompatButton btnBrochure = convertView2.findViewById(R.id.btnBrochure);

        StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "|");
        String ano = tokensHiwalay.nextToken();
        String ilalagay = tokensHiwalay.nextToken();

        switch (ano) {
            case "name":
                LLname.setVisibility(View.VISIBLE);
                LLblank.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                txtName.setText(ilalagay);
                break;
            case "blank":
                LLblank.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                txtBlank.setText(ilalagay);
                break;
            case "category":
                LLcategory.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLblank.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                txtCategory.setText(ilalagay);
                break;
            case "culture":
                LLculture.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLblank.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                txtCulture.setText(ilalagay);
                break;
            case "packaging":
                LLpackaging.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLblank.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                ilalagay = ilalagay.replaceAll("\\\\n", "\n");
                txtpackaging.setText(ilalagay);
                break;
            case "bullet":
                LLbullet.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLblank.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                txtBullet.setText(ilalagay);
                break;
            case "button":
                LLbutton.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLblank.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLdescription.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                break;
            default:
                LLdescription.setVisibility(View.VISIBLE);
                LLname.setVisibility(View.GONE);
                LLblank.setVisibility(View.GONE);
                LLcategory.setVisibility(View.GONE);
                LLculture.setVisibility(View.GONE);
                LLpackaging.setVisibility(View.GONE);
                LLbullet.setVisibility(View.GONE);
                LLbutton.setVisibility(View.GONE);
                if(ilalagay.equals("-")) txtdescription.setVisibility(View.GONE);
                else {
                    txtdescription.setText(ilalagay);
                    txtdescription.setVisibility(View.VISIBLE);
                }
                break;
        }

        btnOrder.setOnClickListener(v -> {
            if(setting_global.isNetworkAvailable(mContext2)){
                setting_global.ShowProgressDialog(mContext2);
                setting_connection BackgroundTask = new setting_connection(mContext2);
                BackgroundTask.execute("LOCATION", "location");
            }else setting_global.gointent(mContext2, sfc04Products04.class);
        });

        btnBrochure.setOnClickListener(v -> {
            if(setting_global.isNetworkAvailable(mContext2)){
                PackageManager pm = mContext2.getPackageManager();
                Intent testIntent = new Intent(Intent.ACTION_VIEW);
                testIntent.setType("application/pdf");
                @SuppressLint("QueryPermissionsNeeded") List activities = pm.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
                if (activities.size() > 0) {
                    download();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext2);
                    builder.setCancelable(true);
                    builder.setTitle("PRODUCTS");
                    builder.setMessage("No PDF Viewer Installed, Do you want to continue?");
                    builder.setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss());
                    builder.setNegativeButton("Download Anyway", (dialog, which) -> {
                        download();
                        dialog.dismiss();
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }else setting_global.noInternetDisplay(mContext2);
        });

        return convertView2;
    }

    public void download(){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(sfc04Products03.url));
        request.setTitle(sfc04Products03.product + ".pdf");
        request.setDescription(sfc04Products03.product);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        assert sfc04Products03.manager != null;
        sfc04Products03.manager.enqueue(request);
        ShowProgressDialog();
    }

    public void ShowProgressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext2);
        LayoutInflater inflater = (LayoutInflater) mContext2.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        assert inflater != null;
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.activity_sfc04_products03_download, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        final AlertDialog b = dialogBuilder.create();
        b.show();
        new CountDownTimer(3000,1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                b.dismiss();
            }
        }.start();
    }
}
