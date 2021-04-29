package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc09AquaRX03 extends AppCompatActivity {
    ImageView img;
    AppCompatButton btn_page1, btn_page2, btn_page3, btn_page4, btnPrevious, btnNext;
    int page = 0, bilang=0;
    String []arrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc09_aqua_rx03);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        img = findViewById(R.id.img);

        btn_page1 = findViewById(R.id.btn_page1);
        btn_page2 = findViewById(R.id.btn_page2);
        btn_page3 = findViewById(R.id.btn_page3);
        btn_page4 = findViewById(R.id.btn_page4);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        ListView listview = findViewById(R.id.listview);

        String kabuuan = setting_global.getpreferences(this, R.string.sfc09_aquarx03);
        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        tokens.nextToken();
        tokens.nextToken();
        tokens.nextToken();
        tokens.nextToken();
        String nilalaman = tokens.nextToken();
        String images = tokens.nextToken();
        arrImage = images.split("♥");
        bilang = arrImage.length;
        if(bilang >= 2){
            btn_page1.setVisibility(View.VISIBLE);
            btn_page2.setVisibility(View.VISIBLE);
        }
        if(bilang >= 3) btn_page3.setVisibility(View.VISIBLE);
        if(bilang >= 4) btn_page4.setVisibility(View.VISIBLE);
        img.setImageBitmap(setting_global.StringToBitMap(arrImage[0]));
        StringTokenizer tokens2 = new StringTokenizer(nilalaman, "^");
        ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
        int ilan = tokens2.countTokens();
        for (int i = 0; i<ilan; i++){
            sfc03Species01_Item adapter = new sfc03Species01_Item(tokens2.nextToken());
            view_adapters.add(adapter);
        }
        sfc04Products03_Adapter lAdapter = new sfc04Products03_Adapter(this, R.layout.activity_sfc04_products03_adapter, view_adapters);
        listview.setAdapter(lAdapter);
    }

    public void btnPrevious(View view) {
        if(page <= 0) setting_global.display("First of Line", "", this);
        else {
            page--;
            EnableDisable(page);
        }
    }

    public void btnNext(View view) {
        if(page >= bilang-1) setting_global.display("End of Line", "", this);
        else {
            page++;
            EnableDisable(page);
        }
    }

    public void btn_page1(View view) {
        EnableDisable(0);
    }

    public void btn_page2(View view) {
        EnableDisable(1);
    }

    public void btn_page3(View view) {
        EnableDisable(2);
    }

    public void btn_page4(View view) {
        EnableDisable(3);
    }

    private void EnableDisable(int i) {
        btn_page1.setBackgroundResource(R.drawable.sfc09_aquarx03_unchecked);
        btn_page2.setBackgroundResource(R.drawable.sfc09_aquarx03_unchecked);
        btn_page3.setBackgroundResource(R.drawable.sfc09_aquarx03_unchecked);
        btn_page4.setBackgroundResource(R.drawable.sfc09_aquarx03_unchecked);
        btn_page1.setEnabled(true);
        btn_page2.setEnabled(true);
        btn_page3.setEnabled(true);
        btn_page4.setEnabled(true);
        img.setImageBitmap(setting_global.StringToBitMap(arrImage[0]));
        page = i;
        if(i == 1) {
            btn_page1.setBackgroundResource(R.drawable.sfc09_aquarx03_checked);
            btn_page1.setEnabled(false);
        }else if(i == 2) {
            btn_page2.setBackgroundResource(R.drawable.sfc09_aquarx03_checked);
            btn_page2.setEnabled(false);
        }else if(i == 3) {
            btn_page3.setBackgroundResource(R.drawable.sfc09_aquarx03_checked);
            btn_page3.setEnabled(false);
        }else if(i == 4) {
            btn_page4.setBackgroundResource(R.drawable.sfc09_aquarx03_checked);
            btn_page4.setEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc09AquaRX02.class);
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