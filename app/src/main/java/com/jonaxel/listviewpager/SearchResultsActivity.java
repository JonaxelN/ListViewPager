package com.jonaxel.listviewpager;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class SearchResultsActivity extends ActionBarActivity {

    private LatLng UPV ;
    private LatLng positionDinamic;
    private String precio;

    GoogleMap mapa;

    private TextView txtQuery;

    String querySearch = null;

    private double lat;
    private double lng;


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Parse.initialize(this, "CofixcfvGfJogAXWkPHQg44lhIlgv1uEeHizUZBt", "wo0lZyD1DpzhcyhvM2tyMsMi5hR7klpfCKsmiD0H");


        UPV = new LatLng(39.481106, -0.340987);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // get the action bar
        ActionBar actionBar = getSupportActionBar();

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtQuery = (TextView) findViewById(R.id.txtQuery);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            querySearch = getIntent().getStringExtra(SearchManager.QUERY);

            actionBar.setTitle(querySearch);
        }

        mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.getUiSettings().setCompassEnabled(true);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            querySearch = intent.getStringExtra(SearchManager.QUERY);

            //toolbar.setTitle(querySearch);

            final ParseQuery<ParseObject> query = ParseQuery.getQuery("Mexico");
            query.whereEqualTo("Nombre", querySearch);
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    if (parseObject == null) {
                        Log.d("score", "Error: " + e.getMessage());
                    } else {
                        precio = parseObject.getString("Precio");
                        Log.d("Nombre: ", "Retrieved " + parseObject.getString("Precio") + " scores");
                        //txtQuery.setText("El precio de es de: " + parseObject.getString("Precio"));
                        lat = Double.parseDouble(parseObject.getString("Lat"));
                        lng = Double.parseDouble(parseObject.getString("Lnt"));

                        positionDinamic = new LatLng(lat, lng);
                        Log.d("LatLng now", positionDinamic.toString());

                        mapa.addMarker(new MarkerOptions()
                                .position(positionDinamic)
                                .title(querySearch)
                                .snippet("El precio es de: " + parseObject.getString("Precio")));
                        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(positionDinamic, 15));
                    }
                }
            });
        }
    }
}
