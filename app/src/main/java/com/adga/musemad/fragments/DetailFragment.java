package com.adga.musemad.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.adga.musemad.R;

public class DetailFragment extends Fragment {

    private static final String ARG_NAME = "arg_name";
    private static final String ARG_IMAGE_RESOURCE_ID = "arg_image_resource_id";
    private static final String ARG_DESC = "arg_desc";

    private String museumName;
    private int imageResourceId;
    private String museumDesc;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String name, int imageResourceId, String desc) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putInt(ARG_IMAGE_RESOURCE_ID, imageResourceId);
        args.putString(ARG_DESC, desc); // Agregar la descripción a los argumentos

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            museumName = getArguments().getString(ARG_NAME);
            imageResourceId = getArguments().getInt(ARG_IMAGE_RESOURCE_ID);
            museumDesc = getArguments().getString(ARG_DESC);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView detailImage = view.findViewById(R.id.detailImage);
        TextView detailTitle = view.findViewById(R.id.detailTitle);
        TextView detailDesc = view.findViewById(R.id.detailDesc);

        // Obtén la descripción directamente del HomeFragment
        // Asegúrate de que museumDesc se establezca en HomeFragment cuando creas los objetos Museum
        if (getArguments() != null) {
            detailTitle.setText(museumName);
            detailImage.setImageResource(imageResourceId);
            detailDesc.setText(getArguments().getString(ARG_DESC));
        }

        return view;
    }


}
