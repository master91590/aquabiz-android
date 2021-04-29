package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc05Locator02 extends AppCompatActivity implements OnMapReadyCallback {
    String name, contact, address, latitude, longitude;
    static String products, anongID, selected_Product;
    ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc05_locator02);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Branch Locator");

        ListView listview = findViewById(R.id.listview);
        TextView txt = findViewById(R.id.txt);

        String selected_id = setting_global.getpreferences(this, R.string.sfc05_branch_selected);

        String branch = setting_global.getpreferences(this, R.string.sfc05_branch);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtPhone = findViewById(R.id.txtPhone);

        StringTokenizer tokens = new StringTokenizer(branch, "♠");
        while (tokens.hasMoreTokens()){
            String kabuuan = tokens.nextToken();
            StringTokenizer tokens2 = new StringTokenizer(kabuuan, "♣");
            String id = tokens2.nextToken();
            if(selected_id.equals(id)) {
                name = tokens2.nextToken();
                contact = tokens2.nextToken();
                address = tokens2.nextToken();
                tokens2.nextToken();
                latitude = tokens2.nextToken();
                longitude = tokens2.nextToken();
                String products = tokens2.nextToken();
                if(products.equals("wala")) {
                    listview.setVisibility(View.GONE);
                    txt.setVisibility(View.GONE);
                }
                else {
                    StringTokenizer tokens3 = new StringTokenizer(products, "♥");
                    int bilang = tokens3.countTokens();
                    for(int i = 0; i<bilang; i++){
                        String product = tokens3.nextToken();
                        sfc03Species01_Item adapter = new sfc03Species01_Item(product);
                        view_adapters.add(adapter);
                    }
                }

                txtName.setText(name);
                txtPhone.setText(contact);
                txtAddress.setText(address);
                sfc05Locator02_Adapter lAdapter = new sfc05Locator02_Adapter(this, R.layout.activity_sfc05_locator02_adapter, view_adapters);
                listview.setAdapter(lAdapter);
                break;
            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);

        listview.setOnItemClickListener((parent, view, position, id) -> {
            products = setting_global.getpreferences(this, R.string.sfc04_products02);
            setting_global.editpreferences(this, R.string.sfc03_saan, "branch");
            selected_Product = String.valueOf(view_adapters.get(position).getKabuuan());
            StringTokenizer tokensProduct = new StringTokenizer(selected_Product, "|");
            anongID = tokensProduct.nextToken();
            if(products.equals("0")){
                if(setting_global.isNetworkAvailable(this)){
                    setting_global.ShowProgressDialog(sfc05Locator02.this);
                    setting_connection productsBackgroundTask = new setting_connection(sfc05Locator02.this);
                    productsBackgroundTask.execute("Products", "aquabiz");
                }else setting_global.noInternetDisplay(this);
            }else {
                anongProdukto(this);
            }
        });
    }

    public static void anongProdukto(Context context){
        StringTokenizer tokensProducts = new StringTokenizer(products, "♠");
        int bilangProdukto = tokensProducts.countTokens();
        for (int i = 0; i<bilangProdukto; i++) {
            String kabuuangProdukto = tokensProducts.nextToken();
            StringTokenizer tokens2 = new StringTokenizer(kabuuangProdukto, "♣");
            String id_produkto = tokens2.nextToken();
            if(anongID.equals(id_produkto)){
                setting_global.editpreferences(context, R.string.sfc04_products03, kabuuangProdukto);
                setting_global.gointent(context, sfc04Products03.class);
                break;
            }else{
                if(bilangProdukto == i + 1){
                    setting_global.displayNoDataAvailable(context);
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng location = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        googleMap.addMarker(new MarkerOptions().position(location).title("Santeh Feeds Corporation - " + name).snippet(address));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,16));
    }

    public void btn_call_branch(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);
        String p = "tel:" + contact;
        i.setData(Uri.parse(p));
        startActivity(i);
    }

    public void btn_direction(View v){
        if(setting_global.isNetworkAvailable(this)){
            if(setting_global.isPackageInstalled("com.google.android.apps.maps", this))
            {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?f=d&daddr="+latitude + "," + longitude));
                intent.setComponent(new ComponentName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity"));
                startActivity(intent);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(sfc05Locator02.this);
                builder.setTitle("Google Maps not found");
                builder.setMessage("Please install Google Maps");
                builder.setCancelable(true);
                builder.setNegativeButton("Install", (dialog, which) -> setting_global.openApplicationInPlayStore(this, "com.google.android.apps.maps"));
                builder.setPositiveButton(
                        "Use Browser",
                        (dialog, id) -> {
                            Uri.Builder directionsBuilder = new Uri.Builder().scheme("https").authority("www.google.com").appendPath("maps").appendPath("dir").appendPath("").appendQueryParameter("api", "1").appendQueryParameter("destination", latitude + "," + longitude);
                            startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }else setting_global.noInternetDisplay(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc05Locator01.class);
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