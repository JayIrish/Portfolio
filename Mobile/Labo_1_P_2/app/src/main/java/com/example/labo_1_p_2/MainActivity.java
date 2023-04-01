package com.example.labo_1_p_2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinProv = (Spinner) findViewById(R.id.spinProv);


        spinProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choixProv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    private void choixProv() {
        final Spinner spinProv = (Spinner) findViewById(R.id.spinProv);
        final Spinner spinVilles = (Spinner) findViewById(R.id.spinVilles);
        final TextView textViewHab = (TextView) findViewById(R.id.hab);
        final ImageView flagImg = (ImageView) findViewById(R.id.imageFlag);


        String[] villesCB = getResources().getStringArray(R.array.Colombie_Britannique);
        String[] villesAL = getResources().getStringArray(R.array.Alberta);
        String[] villesSK = getResources().getStringArray(R.array.Saskatchewan);
        String[] villesMA = getResources().getStringArray(R.array.Manitoba);
        String[] villesON = getResources().getStringArray(R.array.Ontario);
        String[] villesQC = getResources().getStringArray(R.array.Québec);
        String[] villesNL = getResources().getStringArray(R.array.Terre_Neuve_et_Labrador);
        String[] villesNS = getResources().getStringArray(R.array.Nouvelle_Écosse);
        String[] villesNB = getResources().getStringArray(R.array.Nouveau_Brunswick);
        String[] villesPEI = getResources().getStringArray(R.array.Île_du_Prince_Édouard);
        String[] ProvHab = getResources().getStringArray(R.array.population);


        Drawable flagBC = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_british_columbia, null);
        Drawable flagAL = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_alberta, null);
        Drawable flagSK = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_saskatchewan, null);
        Drawable flagMA = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_manitoba, null);
        Drawable flagON = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_ontario, null);
        Drawable flagQC = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_quebec, null);
        Drawable flagNL = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_newfoundland_and_labrador, null);
        Drawable flagNS = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_new_brunswick, null);
        Drawable flagNB = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_nova_scotia, null);
        Drawable flagPEI = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.flag_of_prince_edward_island, null);

        String prov = spinProv.getSelectedItem().toString();

        ArrayAdapter<String> adapter;
        switch (prov) {
            case "Colombie-Britannique":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesCB);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[0]);
                flagImg.setImageDrawable(flagBC);
                break;
            case "Alberta":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesAL);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[1]);
                flagImg.setImageDrawable(flagAL);
                break;
            case "Saskatchewan":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesSK);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[2]);
                flagImg.setImageDrawable(flagSK);
                break;
            case "Manitoba":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesMA);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[3]);
                flagImg.setImageDrawable(flagMA);
                break;
            case "Ontario":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesON);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[4]);
                flagImg.setImageDrawable(flagON);
                break;
            case "Québec":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesQC);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[5]);
                flagImg.setImageDrawable(flagQC);
                break;
            case "Terre-Neuve-et-Labrador":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesNL);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[6]);
                flagImg.setImageDrawable(flagNL);
                break;
            case "Nouveau-Brunswick":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesNB);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[7]);
                flagImg.setImageDrawable(flagNB);
                break;
            case "Nouvelle-Écosse":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesNS);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[8]);
                flagImg.setImageDrawable(flagNS);
                break;
            case "Île-du-Prince-Édouard":
                adapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        villesPEI);
                spinVilles.setAdapter(adapter);
                textViewHab.setText(ProvHab[9]);
                flagImg.setImageDrawable(flagPEI);
                break;
        }
    }
}