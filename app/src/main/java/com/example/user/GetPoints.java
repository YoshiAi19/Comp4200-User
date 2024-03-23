package com.example.user;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class GetPoints extends Fragment {

   TextView get_points_title, get_points_info;
   EditText et_input_transactionID, et_input_totalOrderAmount;

   Button convert_btn;

    //Create an empty constructor called Get Points
    public GetPoints(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_get_points,container, false);
        get_points_title = view.findViewById(R.id.getPointsTitle);
        get_points_info = view.findViewById(R.id.getPointsInfo);
        et_input_transactionID = view.findViewById(R.id.input_transactionID);
        et_input_totalOrderAmount = view.findViewById(R.id.input_totalOrderAmount);
        convert_btn =  view.findViewById(R.id.convertToPoints);



        //When the convert button is pressed an alert message will appear
        convert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO need to validate the user id and amount with the database
                //Get the user's amount
                String getAmount = et_input_totalOrderAmount.getText().toString();
                //Get the user's transaction id:
                String getTransactionID = et_input_transactionID.getText().toString();


                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                //set the message in which it shows the user transaction id and amount and it's corresponding points
                builder.setMessage("Transaction ID: " + getTransactionID + "\nAmount " + getAmount + " has been converted to: " + PointsConverter.convertDollarToPoints(Double.parseDouble(getAmount)) + " points");
                //set the title:
                builder.setTitle("Points Converted");
                //create the dialog:
                AlertDialog alertDialog = builder.create();
                //show the alert box:
                alertDialog.show();
            }
        });

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


}