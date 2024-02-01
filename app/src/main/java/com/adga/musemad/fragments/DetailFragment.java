package com.example.musemad.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.musemad.R;

public class DetailFragment extends Fragment {

    private static final String ARG_NAME = "arg_name";
    private static final String ARG_IMAGE_RESOURCE_ID = "arg_image_resource_id";

    private String museumName;
    private int imageResourceId;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String name, int imageResourceId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_IMAGE_RESOURCE_ID, imageResourceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            museumName = getArguments().getString(ARG_NAME);
            imageResourceId = getArguments().getInt(ARG_IMAGE_RESOURCE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView detailImage = view.findViewById(R.id.detailImage);
        TextView detailTitle = view.findViewById(R.id.detailTitle);
        TextView detailDesc = view.findViewById(R.id.detailDesc);

        // Obtén los datos del museo del bundle
        Bundle arguments = getArguments();
        if (arguments != null) {
            String museumName = arguments.getString(ARG_NAME);
            int imageResourceId = arguments.getInt(ARG_IMAGE_RESOURCE_ID);

            // Actualiza los elementos según la información del museo
            detailTitle.setText(museumName);
            detailImage.setImageResource(imageResourceId);
            detailDesc.setText("Descripcion museo");
        }

        return view;
    }

}
