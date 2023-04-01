package com.example.labo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.fragment.app.Fragment;

public class ListerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lister, container, false);


        TextView textView = view.findViewById(R.id.text_view);
        ImageView clearIcon = view.findViewById(R.id.clear_icon);

        MainActivity mainActivity = (MainActivity) getActivity();
        Produit[] listeProd = mainActivity.getListeProd();
        StringBuilder sb = new StringBuilder();
        for (Produit produit : listeProd) {
            sb.append(produit.toString());
            sb.append("\n\n");
        }
        textView.setText(sb.toString());

        clearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
            }
        });

        return view;
    }
}


