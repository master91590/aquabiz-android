package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc03Species05 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc03_species05);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String species = setting_global.getpreferences(this, R.string.sfc03_species_name);
        setTitle(species + " FAQ");

        ExpandableListView listView = findViewById(R.id.lvFAQExp);
        HashMap<String,List<String>> listHash = new HashMap<>();
        List<String> listDataQuestion = new ArrayList<>();
        String faq = setting_global.getpreferences(this, R.string.sfc03_faq);
        StringTokenizer tokensfaq = new StringTokenizer(faq, "♥");
        while (tokensfaq.hasMoreTokens()){
            StringTokenizer tokens = new StringTokenizer(tokensfaq.nextToken(), "|");
            String question = tokens.nextToken();
            String answer = tokens.nextToken();
            answer = answer.replaceAll("\\\\n", "\n");
            listDataQuestion.add(question);
            List<String> listDataAnswers = new ArrayList<>();
            listDataAnswers.add(answer);
            listHash.put(question, listDataAnswers);
        }

        setting_ExpandableListView_Adapter listAdapter = new setting_ExpandableListView_Adapter(this, listDataQuestion, listHash, "species");
        listView.setAdapter(listAdapter);

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    listView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        setTitle(species + " FAQ");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        listView.setIndicatorBoundsRelative(width - GetPixelFromDips(50), width - GetPixelFromDips(20));
    }

    public int GetPixelFromDips(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc03Species02.class);
    }

    public void btn_home(View view) {
        setting_global.gointent(this,sfc02Home.class);
    }

    public void btn_notification(View view) {
    }

    public void btn_announcement(View view) {
    }

    public void btn_setting(View view) {
    }
}