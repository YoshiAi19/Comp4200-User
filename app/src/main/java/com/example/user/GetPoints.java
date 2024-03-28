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
    EditText et_input_transactionID;

    Button convert_btn;

    MyDBHelper myDBHelper;

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
        convert_btn =  view.findViewById(R.id.convertToPoints);

        myDBHelper = new MyDBHelper(getContext());
        PointsConverter.init(myDBHelper);

        //When the convert button is pressed an alert message will appear
        convert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO get the user id
                //get user email from main (log in)
                String userEmail = MainActivity.userEmail;
                //determine the user id:
                int id = myDBHelper.getID(userEmail);
                //Get the user's transaction id:
                String getTransactionID = et_input_transactionID.getText().toString();

                //call the method to determine the amount corresponding to the transaction id:
                double getTransactionAmount = myDBHelper.getAmountFromTransactionID(Integer.parseInt(getTransactionID));

                //check if the amount has been converted into points:
                boolean isConverted = myDBHelper.isPointsConverted(Integer.parseInt(getTransactionID));

                //check if the transaction ID exists:
                if(getTransactionAmount != 0){

                    //If the points have been converted:
                    if(isConverted){
                        //show an alert message that says that the amount has been already converted:
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        //set the message  with transaction id and points has already been converted
                        builder.setMessage("Transaction ID: " + getTransactionID + " has already been converted");
                        //set the title
                        builder.setTitle("Points Already Converted");
                        //create the dialog
                        AlertDialog alertDialog = builder.create();
                        //show the alert box
                        alertDialog.show();

                    }
                    else {
                        //convert the amount to points by calling the convertDollarToPoints form the Points Converter class:
                        int points = PointsConverter.convertDollarToPoints(Integer.parseInt(getTransactionID),id,getTransactionAmount);

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        //set the message in which it shows the user transaction id and amount and it's corresponding points
                        builder.setMessage("Transaction ID: " + getTransactionID + "\nTransaction Amount: $" + getTransactionAmount + "\nPoints Converted: " + points );
                        //set the title:
                        builder.setTitle("Points Converted");
                        //create the dialog:
                        AlertDialog alertDialog = builder.create();
                        //show the alert box:
                        alertDialog.show();
                    }
                }
                //if the transaction ID does not exists:
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    //set the message in which it shows the user transaction id and amount and it's corresponding points
                    builder.setMessage("Transaction ID: " + getTransactionID + "Does Not Exist" );
                    //set the title:
                    builder.setTitle("Error");
                    //create the dialog:
                    AlertDialog alertDialog = builder.create();
                    //show the alert box:
                    alertDialog.show();
                }
            }

        });

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    //get the user id :

}