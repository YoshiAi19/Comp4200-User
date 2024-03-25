package com.example.user;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class points_card_activity extends AppCompatActivity {
    ImageView back_btn, home_btn, card_btn,account_btn;

    TextView titleCard;
    ActionBar actionBar;
    Toolbar bottomActionBar;
    ButtonsOnManyActivities buttonsOnManyActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_points_card);

        buttonsOnManyActivities = new ButtonsOnManyActivities(this);

        //back_btn = findViewById(R.id.backButton);
        home_btn = findViewById(R.id.homeButtonOrder);
        card_btn = findViewById(R.id.pointsCard);
        account_btn = findViewById(R.id.userAccountLogo);
        titleCard = findViewById(R.id.pointsCardTitle);


        //set up the action bar
        bottomActionBar = (Toolbar) findViewById(R.id.bottomActionBar);
        setSupportActionBar(bottomActionBar);

        //call the action bar:
        actionBar = getSupportActionBar();
        //show the back button in the action bar:
        actionBar.setDisplayHomeAsUpEnabled(true);

        //When the home button is pressed it will take the user to the home page:
        //call the home button listener function from the ButtonsOnManyActivities
        buttonsOnManyActivities.HomeButton(this,home_btn);


        //When the point card is pressed it will take the user to the points card fragment
        //call the points card listener from the ButtonsOnManyActivities
        buttonsOnManyActivities.pointsCard(this,card_btn);

        //When the user account button is pressed it will take the user to the account page
        //call the account button listener from the ButtonsOnManyActivities
        buttonsOnManyActivities.account(this,account_btn);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //When the back button is pressed it will take the user back to the previous page:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}