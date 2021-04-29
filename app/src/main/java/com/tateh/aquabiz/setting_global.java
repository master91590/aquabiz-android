package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class setting_global {
    public static void gointent(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        packageContext.startActivity(intent);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(cm).getActiveNetworkInfo();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            return Objects.requireNonNull(capabilities).hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        } else return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void editpreferences(Context packageContext, int ID, String string) {
        SharedPreferences.Editor editor;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(packageContext);
        editor = preferences.edit();
        editor.putString(String.valueOf(ID),string);
        editor.apply();
    }

    public static String getpreferences(Context packageContext, int ID){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(packageContext);
        return preferences.getString(String.valueOf(ID), packageContext.getString(ID));
    }

    private static AlertDialog alertDialog;
    private static CountDownTimer dialogWaitTimer;
    public static void ShowProgressDialog(final Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        assert inflater != null;
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.activity_sfc02_home_progressdialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        int oras=150000;
        dialogWaitTimer = new CountDownTimer(oras,1000) {
            public void onTick(long millisUntilFinished) { }
            public void onFinish() {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(true);
                    builder.setTitle("Connection Timeout.");
                    builder.setMessage("Can't connect to the server. Application will restart.");
                    builder.setPositiveButton("OK", (dialog, which) -> restart(context, 1000));
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        }.start();
    }

    public static void restart(Context context, int delay) {
        if(Build.VERSION.SDK_INT < 29) {
            if (delay == 0) {
                delay = 1;
            }
            Intent restartIntent = context.getPackageManager()
                    .getLaunchIntentForPackage(context.getPackageName());
            @SuppressLint("WrongConstant") PendingIntent intent = PendingIntent.getActivity(
                    context, 0,
                    restartIntent, Intent.FLAG_ACTIVITY_CLEAR_TOP);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (manager != null)
                manager.set(AlarmManager.RTC, System.currentTimeMillis() + delay, intent);
            System.exit(2);
        }else{
            gointent(context, sfc01SplashScreen.class);
        }
    }

    public static void dismissAlertDialog() {
        if(alertDialog.isShowing()) alertDialog.dismiss();
        if(dialogWaitTimer != null) {
            dialogWaitTimer.cancel();
            dialogWaitTimer = null;
        }
    }

    public static Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,70, baos);
        byte [] b=baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static void OKdisplay(String title, String message, final Context context, final Class<?> SaanPupuntaClass) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> gointent(context, SaanPupuntaClass));
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void YesNoDisplay(String title, String message, final Context context, final Class<?> SaanPupuntaClass) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("YES", (dialog, which) -> setting_global.gointent(context, SaanPupuntaClass));
        builder.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void noInternetDisplay(Context context){
        display("No internet connection","Please connect to WIFI or turn on your mobile data and check if you have internet access!", context);
    }

    public static void display(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public static void displayNoDataAvailable( Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setMessage("To be add soon.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException cj) {
            return false;
        }
    }

    public static void openApplicationInPlayStore(Context context, String packagename) {
        try {
            startViewUri(context, "market://details?id="+packagename);
        } catch (ActivityNotFoundException cj) {
            startViewUri(context, "http://play.google.com/store/apps/details?id="+packagename);
        }
    }
    private static void startViewUri(Context context, String uri) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }
}
