package com.example.user;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Account extends AppCompatActivity {

    ImageView back_btn, home_btn, card_btn,account_btn;
    ButtonsOnManyActivities buttonsOnManyActivities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        buttonsOnManyActivities = new ButtonsOnManyActivities(this);

        home_btn = findViewById(R.id.homeButtonOrder);
        card_btn = findViewById(R.id.pointsCard);
        account_btn = findViewById(R.id.userAccountLogo);


        //set up the action bar
        Toolbar bottomActionBar = (Toolbar) findViewById(R.id.bottomActionBar);
        setSupportActionBar(bottomActionBar);

        //call the action bar:
        ActionBar actionBar = getSupportActionBar();
        //show the back button in the action bar:
        actionBar.setDisplayHomeAsUpEnabled(true);


        //When the home button is pressed it will take the user to the home page:
        //call the home button listener function from the ButtonsOnManyActivities
        buttonsOnManyActivities.HomeButton(this,home_btn);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nicknameTitle), (v, insets) -> {
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