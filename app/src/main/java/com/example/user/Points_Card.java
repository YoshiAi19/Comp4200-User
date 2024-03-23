package com.example.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Points_Card extends Fragment {

    CardView userIDCard;
    TextView userID,forgotToShowID;

    public Points_Card() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_points__card, container, false);

        userID = view.findViewById(R.id.userID);
        forgotToShowID = view.findViewById(R.id.forgotToShowID);
        userIDCard = view.findViewById(R.id.userIDCard);


        forgotToShowID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create an instance of GetPoints:
                GetPoints getPoints = new GetPoints();

                //get the fragment maanager and transaction:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //replace the current fragment (points card) with get points fragment
                ft.replace(R.id.frame, getPoints);
                //commit:
                ft.commit();

            }
        });



        return view;
        }
}