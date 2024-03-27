package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/*
 * This class will contain buttons that are used in multiple activities such as:
 *   - Home Button
 *   - Back Button
 *   - Points Card
 * */
public class ButtonsOnManyActivities {
    private Activity activity;
    private ImageView homeButton;
    private ImageView backButton;
    private ImageView pointCardButton;

    private  ImageView accountButton;

    //TODO: Fix Nav Bar:

    public ButtonsOnManyActivities(Activity activity) {
        this.activity = activity;
    }

    //Create a constructor for the home button:
    public void HomeButton(Activity activity, ImageView homeButton) {
        this.homeButton = homeButton;


        //set a listener for this image home button:
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an intent that will take the user back to the home page:
                Intent toHome = new Intent(activity, HomePage.class);
                //When the home page button is pressed it will go back to the first home page that
                //created earlier instead of creating a new each time and trying to find the user name for each time it
                //opens a new home page
                toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(toHome);
            }
        });
    }

    //Create a constructor for the back image button:
    public void backButton(Activity activity, ImageView backButton) {
        this.backButton = backButton;


        //set a listener for the back button
        //when pressed it will take the user to the last page they were previously on
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    //Create a constructor for the points card:
    public void pointsCard(Activity activity, ImageView pointCardButton) {
        this.pointCardButton = pointCardButton;

        //set a listener for this image home button:
        pointCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an intent that will take the user back to the home page:
                Intent toPointsCard = new Intent(activity, points_card_activity.class);
                activity.startActivity(toPointsCard);
            }
        });
    }

    //Create a constructor for the account:
    public void account (Activity activity, ImageView accountButton) {
        this.accountButton = accountButton;


        //set a listener for this image home button:
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an intent that will take the user back to the home page:
                Intent toAccountInfo = new Intent(activity, Account.class);
                activity.startActivity(toAccountInfo);
            }
        });
    }



}





