package com.example.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    TextView welcomeText;
    RecyclerView recyclerView;
    AdapterForHomePage adapter;
    ArrayList<DataSetHomePage> dataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        welcomeText = findViewById(R.id.welcomeText);

        // Need to update this part once the database part is setup

        //String userEmail = fetchUserEmail();
        //String nickname = getUserNickname(userEmail);

        String welcomeMessage = "Welcome ";
        //if (nickname != null) {
        //    welcomeMessage += nickname;
        //}

        //

        welcomeText.setText(welcomeMessage);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        dataSets.add(new DataSetHomePage("Rewards and Points", R.drawable.rewards_and_points));
        dataSets.add(new DataSetHomePage("Offers and Discounts", R.drawable.offers_and_discounts));
        dataSets.add(new DataSetHomePage("Order", R.drawable.order));

        adapter = new AdapterForHomePage(dataSets, this);
        recyclerView.setAdapter(adapter);

        welcomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toOrder = new Intent(HomePage.this, Order.class);
                startActivity(toOrder);
            }
        });

        // Account icon at the top that will take the user to ***account page***
        ImageView accountIcon = findViewById(R.id.userAccountIcon);
        accountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Account.class);
                startActivity(intent);
            }
        });

        // Back icon that will take the use to the ***previous page***
        ImageView backIcon = findViewById(R.id.backBtn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to previous page
                onBackPressed();
            }
        });

        // Home button that will take the user to ***home page***
        ImageView homeButton = findViewById(R.id.homeBtn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    // Need to update this part once the database part is setup
    // Created the framework
    /*
    private String fetchUserEmail() {
        String userEmail = null;

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query the database to fetch the user's email address
        Cursor cursor = db.rawQuery("SELECT email FROM User_Table LIMIT 1", null);

        // Check if the cursor has data
        if (cursor.moveToFirst()) {
            // Retrieve the email address from the cursor
            userEmail = cursor.getString(cursor.getColumnIndex("User Email"));
        }

        cursor.close();
        db.close();

        return userEmail;
    }

    private String getUserNickname(String userEmail) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String nickname = null;

        // Query the User_Table to retrieve the nickname based on the email address
        Cursor cursor = db.rawQuery("SELECT nickname FROM User_Table WHERE email = ?", new String[]{userEmail});

        // Check if the cursor has data
        if (cursor.moveToFirst()) {
            // Retrieve the nickname from the cursor
            nickname = cursor.getString(cursor.getColumnIndex("User Nickname"));
        }

        cursor.close();
        db.close();

        return nickname;
    }
     */
}