package com.example.user;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //this comment
    Button btn_login, btn_signup;
    TextView forgot_pwd;
    CheckBox rememberMe;
    EditText email,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_login = findViewById(R.id.logIn);
        btn_signup = findViewById(R.id.signup_btn);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        rememberMe = findViewById(R.id.rememberMe);
        forgot_pwd = findViewById(R.id.forgotPwd);


        //Initialize DB and insert default user/reward records
        MyDBHelper dbHelper = new MyDBHelper(getApplicationContext());
        // Insert initial user data -- ONLY INCLUDE ON FIRST LAUNCH OF APP
        /*
        dbHelper.addUser("minern@uwindsor.ca", "minern", "Password!123");
        dbHelper.addUser("azzam2@uwindsor.ca", "azzam2", "Password!123");
        dbHelper.addUser("ismai11a@uwindsor.ca", "ismai11a", "Password!123");
        dbHelper.addUser("elbaser@uwindsor.ca", "elbaser", "Password!123");
        dbHelper.addUser("banga2@uwindsor.ca", "sbanga", "Password!123");

        // Add default rewards for each user
        dbHelper.addReward(0, 1);
        dbHelper.addReward(0, 2);
        dbHelper.addReward(0, 3);
        dbHelper.addReward(0, 4);
        dbHelper.addReward(0, 5);
        */




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //When log in button is pressed:
                //1. Validate user log in information:

                //1.1 If user log in information is correct then take the user to the home page:
                Intent toHome = new Intent(MainActivity.this, HomePage.class);
                startActivity(toHome);

                //1.2 Otherwise send a toast message:
                String invalidID = "Enter a valid email and password";
                Toast.makeText(MainActivity.this, invalidID, Toast.LENGTH_LONG).show();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignUp = new Intent(MainActivity.this, SignUpPage.class);
                startActivity(toSignUp);
            }
        });


        //Forgot Password:
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When the forgot password textview is pressed it takes the user to forgot pwd page
                Intent toResetPwd = new Intent(MainActivity.this, ResetPwd.class);
                startActivity(toResetPwd);
            }
        });

    }

}