package com.example.labo_2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_popup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lister:
                ListerFragment listerFragment = new ListerFragment();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.fragment_container, listerFragment);
                transaction1.addToBackStack(null);
                transaction1.commit();
                return true;
            case R.id.categories:
                CategorieFragment categorieFragment = new CategorieFragment();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.fragment_container, categorieFragment);
                transaction2.addToBackStack(null);
                transaction2.commit();
                return true;
            case R.id.total:
                TotalFragment totalFragment = new TotalFragment();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.fragment_container, totalFragment);
                transaction3.addToBackStack(null);
                transaction3.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Produit[] getListeProd() {
        Produit[] listeProd = {
                new Produit(1, "Corvette", "Sport", 66999.99, 2),
                new Produit(2, "Porsche", "Sport", 79999.99, 2),
                new Produit(3, "Caravan", "MPV", 34999.99, 5),
                new Produit(4, "Intrepid", "Saloon", 32999.99, 4),
                new Produit(5, "Interstellar", "MPV", 29999.99, 3),
                new Produit(6, "Impala", "Saloon", 29999.99, 2),
                new Produit(7, "Ferrari", "Sport", 87999.99, 1)
        };
        return listeProd;
    }
}