package com.example.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Order extends AppCompatActivity {

    ImageView back_btn, home_btn;
    TextView titleOrder;
    Button get_points_btn, rewards_btn,current_offers_btn;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        back_btn = findViewById(R.id.backBtnOrder);
        home_btn = findViewById(R.id.homeBtnOrder);
        titleOrder = findViewById(R.id.titleOrder);
        get_points_btn = findViewById(R.id.getPointsBtn);
        rewards_btn = findViewById(R.id.rewardsBtn);
        current_offers_btn = findViewById(R.id.currentOffersBtn);
        frame = findViewById(R.id.frame);

        get_points_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPoints(v);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /*When the Get Points Button is pressed:
      - it will open the get points activity within the frame
   */


    public void getPoints (View view){
        GetPoints getPoints = new GetPoints();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame,getPoints);
        ft.commit();
    }
}