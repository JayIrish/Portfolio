package com.example.labo_2;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TotalFragment extends Fragment {

    private TextView mTotalTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_total, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        Produit[] listeProd = mainActivity.getListeProd();

        mTotalTextView = rootView.findViewById(R.id.total_text_view);

        double totalPrice = 0.0;
        for (Produit produit : listeProd) {
            int qute = produit.getInventaire();
            double prix = produit.getPrix();
            totalPrice += qute*prix;
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedTotalPrice = numberFormat.format(totalPrice);

// Set the formatted total price to the TextView
        TextView totalTextView = rootView.findViewById(R.id.total_text_view);
        totalTextView.setText("$" + formattedTotalPrice);

        return rootView;
    }
}
