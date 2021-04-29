package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc03Species03 extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    YouTubePlayer youTubePlayer;
    boolean nakaraos;
    String youtube_id_pond1, youtube_id_pond2, youtube_id_cage1, youtube_id_cage2, pond, cage, saan="pond";
    Button btn_pond, btn_cage;
    int bilang, bilang_yt_pond, bilang_yt_cage;
    LinearLayout llPart;
    ListView listView;
    ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc03_species03);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_pond = findViewById(R.id.btn_pond);
        btn_cage = findViewById(R.id.btn_cage);
        listView = findViewById(R.id.listview);
        llPart = findViewById(R.id.llPart);

        btn_cage.setEnabled(true);
        btn_pond.setEnabled(false);
        btn_pond.setBackgroundResource(R.drawable.sfc03_species03_pond);
        btn_cage.setBackgroundResource(R.drawable.sfc03_species03_cage_inactive);

        String kabuuan = setting_global.getpreferences(this, R.string.sfc03_howtogrow);
        String species = setting_global.getpreferences(this, R.string.sfc03_species_name);
        setTitle("How to Grow" + species);

        StringTokenizer tokens = new StringTokenizer(kabuuan, "♥");
        bilang = tokens.countTokens();
        StringTokenizer tokens2 = new StringTokenizer(tokens.nextToken(), "$");

        String youtube_id_pond = tokens2.nextToken();
        StringTokenizer tokens_yt_pond = new StringTokenizer(youtube_id_pond, "^");
        bilang_yt_pond = tokens_yt_pond.countTokens();
        youtube_id_pond1 = tokens_yt_pond.nextToken();
        if(bilang_yt_pond > 1){
            youtube_id_pond2 = tokens_yt_pond.nextToken();
            llPart.setVisibility(View.VISIBLE);
        }

        pond = tokens2.nextToken();
        loadData(pond);

        if(bilang > 1){
            StringTokenizer tokens3 = new StringTokenizer(tokens.nextToken(), "$");
            String youtube_id_cage = tokens3.nextToken();
            StringTokenizer tokens_yt_cage = new StringTokenizer(youtube_id_cage, "^");
            bilang_yt_cage = tokens_yt_cage.countTokens();
            youtube_id_cage1 = tokens_yt_cage.nextToken();
            if(bilang_yt_pond > 1){
                youtube_id_cage2 = tokens_yt_cage.nextToken();
            }
            cage = tokens3.nextToken();
        }



        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(String.valueOf(R.string.sfc03_key), this);

    }

    private void loadData(String ano) {
        StringTokenizer tokens = new StringTokenizer(ano, "^");
        int ilan = tokens.countTokens();
        view_adapters.clear();
        for (int i = 0; i<ilan; i++){
            sfc03Species01_Item adapter = new sfc03Species01_Item(tokens.nextToken());
            view_adapters.add(adapter);
        }
        sfc03Species03_Adapter lAdapter = new sfc03Species03_Adapter(this, R.layout.activity_sfc03_species03_adapter, view_adapters);
        listView.setAdapter(lAdapter);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.youTubePlayer = youTubePlayer;
        nakaraos=b;
        if (!b) {
            youTubePlayer.cueVideo(youtube_id_pond1);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, 1).show();
        } else {
            Toast.makeText(this,"Initialization failure: Unable to play video", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        String saan = setting_global.getpreferences(this, R.string.sfc03_saan);
        if(saan.equals("faq")) setting_global.gointent(this, sfc03Species05.class);
        else setting_global.gointent(this, sfc03Species02.class);
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

    public void btn_pond(View view) {
        if(bilang == 1) btn_pond.setEnabled(false);
        else{
            if (!nakaraos) youTubePlayer.cueVideo(youtube_id_pond1);
            btn_cage.setEnabled(true);
            btn_pond.setEnabled(false);
            btn_pond.setBackgroundResource(R.drawable.sfc03_species03_pond);
            btn_cage.setBackgroundResource(R.drawable.sfc03_species03_cage_inactive);
            loadData(pond);
        }
        if(bilang_yt_pond > 1){
            llPart.setVisibility(View.VISIBLE);
        }else{
            llPart.setVisibility(View.GONE);
        }
        saan = "pond";
    }

    public void btn_cage(View view) {
        if(bilang == 1) setting_global.displayNoDataAvailable(this);
        else{
            if (!nakaraos) youTubePlayer.cueVideo(youtube_id_cage1);
            btn_cage.setEnabled(false);
            btn_pond.setEnabled(true);
            btn_pond.setBackgroundResource(R.drawable.sfc03_species03_pond_inactive);
            btn_cage.setBackgroundResource(R.drawable.sfc03_species03_cage);
            loadData(cage);
        }
        if(bilang_yt_cage > 1){
            llPart.setVisibility(View.VISIBLE);
        }else{
            llPart.setVisibility(View.GONE);
        }
        saan = "cage";
    }

    public void btn_nodataavailable(View view) {
        setting_global.displayNoDataAvailable(this);
    }

    public void btn_part1(View view) {
        if(saan.equals("pond")){
            if (!nakaraos) youTubePlayer.cueVideo(youtube_id_pond1);
        }else{
            if (!nakaraos) youTubePlayer.cueVideo(youtube_id_cage1);
        }
        enabledisable(1);
    }

    public void btn_part2(View view) {
        if(saan.equals("pond")){
            if (!nakaraos) youTubePlayer.cueVideo(youtube_id_pond2);
        }else{
            if (!nakaraos) youTubePlayer.cueVideo(youtube_id_cage2);
        }
        enabledisable(2);
    }

    private void enabledisable(int i) {
        AppCompatButton btn_part1 = findViewById(R.id.btn_part1);
        AppCompatButton btn_part2 = findViewById(R.id.btn_part2);
        if(i==1){
            btn_part1.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
            btn_part2.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btn_part1.setEnabled(false);
            btn_part2.setEnabled(true);
        }else{
            btn_part2.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
            btn_part1.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btn_part2.setEnabled(false);
            btn_part1.setEnabled(true);
        }
    }
}