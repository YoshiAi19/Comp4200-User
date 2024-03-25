package com.example.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RewardsPage extends AppCompatActivity {

    ImageView home_btn, card_btn, account_btn;
    Button offers_btn, points_btn;
    TextView titleRewards, welcomeText, userText, userPoints;
    FrameLayout frame;
    ProgressBar progressBar;
    int currentPoints = 0;

    //TODO uncomment once nav bar functionality is complete
    //ButtonsOnManyActivities buttonsOnManyActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_page);

        EdgeToEdge.enable(this);

        //initialize views
        home_btn = findViewById(R.id.homeBtnRewards);
        card_btn = findViewById(R.id.pointsCard);
        account_btn = findViewById(R.id.userAccountLogo);
        titleRewards = findViewById(R.id.titleRewards);
        welcomeText = findViewById(R.id.welcomeText);
        userText = findViewById(R.id.userText);
        frame = findViewById(R.id.frame);
        progressBar = findViewById(R.id.progressBar);
        userPoints = findViewById(R.id.userPoints);
        offers_btn = findViewById(R.id.currentOffersBtn);
        points_btn = findViewById(R.id.getPointsBtn);

        // Initialize DB
        MyDBHelper dbHelper = new MyDBHelper(getApplicationContext());

        //TODO uncomment once nav bar functionality is complete

        /* Initialize Action Bar
        buttonsOnManyActivities = new ButtonsOnManyActivities(this);
        buttonsOnManyActivities.HomeButton(this,home_btn); //home button
        buttonsOnManyActivities.account(this,account_btn); //account button
        buttonsOnManyActivities.pointsCard(this,card_btn); //card button
        */

        // Get userNickname for welcome message
        String userNickname = dbHelper.getUserNickname(1); // TODO: Modify to select logged-in user _id
        userText.setText(userNickname);

        // Initialize progress bar with points for _idUser
        currentPoints = dbHelper.getPointsValue(1); // TODO: Modify to select logged-in _idUser
        progressBar.setProgress(currentPoints);
        String pointsString = getString(R.string.user_points);
        String formattedText = currentPoints + " " + pointsString;
        userPoints.setText(formattedText);


        // Apply window insets listener for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Set onClickListener for the get Points button
        //TODO link to get points once functionality is completed

        // Set onClickListener for Current Offers button
        offers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open HomePage
                Intent intent = new Intent(RewardsPage.this, CurrentOffers.class);
                startActivity(intent);
                finish();
            }
        });
    }
}