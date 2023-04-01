package com.example.labo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CategorieFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorie, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        Produit[] listeProd = mainActivity.getListeProd();

        Set<String> categoriesSet = new HashSet<>();
        for (Produit produit : listeProd) {
            categoriesSet.add(produit.getCategorie());
        }

        String[] categoriesArray = categoriesSet.toArray(new String[categoriesSet.size()]);
        Arrays.sort(categoriesArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoriesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = view.findViewById(R.id.categorie_spinner);
        spinner.setAdapter(adapter);

        TextView textView = view.findViewById(R.id.categorie_text_view);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected category
                String selectedCategory = parent.getItemAtPosition(position).toString();

                // Filter the list
                StringBuilder sb = new StringBuilder();
                for (Produit produit : listeProd) {
                    if (produit.getCategorie().equals(selectedCategory)) {
                        sb.append(produit.toString());
                        sb.append("\n\n");

                    }
                }

                textView.setText(sb.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return view;
    }

}


