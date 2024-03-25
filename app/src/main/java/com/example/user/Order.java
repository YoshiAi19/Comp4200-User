package com.example.user;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Order extends AppCompatActivity {

    ImageView back_btn, home_btn, card_btn,account_btn;
    TextView titleOrder;
    Button get_points_btn, rewards_btn,current_offers_btn;
    FrameLayout frame;
    ActionBar actionBar;
    Toolbar bottomActionBar;
    ButtonsOnManyActivities buttonsOnManyActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        buttonsOnManyActivities = new ButtonsOnManyActivities(this);

        //back_btn = findViewById(R.id.backButton);
        home_btn = findViewById(R.id.homeButtonOrder);
        card_btn = findViewById(R.id.pointsCard);
        account_btn = findViewById(R.id.userAccountLogo);
        titleOrder = findViewById(R.id.titleOrder);
        get_points_btn = findViewById(R.id.getPointsBtn);
        rewards_btn = findViewById(R.id.rewardsBtn);
        current_offers_btn = findViewById(R.id.currentOffersBtn);
        frame = findViewById(R.id.frame);


        //set up the action bar
        bottomActionBar = (Toolbar) findViewById(R.id.bottomActionBar);
        setSupportActionBar(bottomActionBar);

        //call the action bar:
        actionBar = getSupportActionBar();
        //show the back button in the action bar:
        actionBar.setDisplayHomeAsUpEnabled(true);


        //Will display the points card to the user onto the frame
        get_points_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPointsCard(v);
            }
        });

        //Will

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //When the home button is pressed it will take the user to the home page:
        //call the home button listener function from the ButtonsOnManyActivities
        buttonsOnManyActivities.HomeButton(this,home_btn);

        //When the point card is pressed it will take the user to the points card fragment
        //call the points card listener from the ButtonsOnManyActivities
        buttonsOnManyActivities.pointsCard(this,card_btn);

        //When the user account button is pressed it will take the user to the account page
        //call the account button listener from the ButtonsOnManyActivities
        buttonsOnManyActivities.account(this,account_btn);

    }

    /*When the Get Points Button is pressed:
      - it will open the points card fragment first
   */
    public void getPointsCard (View view){
        Points_Card pointsCard = new Points_Card();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame,pointsCard);
        ft.commit();
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