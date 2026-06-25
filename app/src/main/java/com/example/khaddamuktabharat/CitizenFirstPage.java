package com.example.khaddamuktabharat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CitizenFirstPage extends AppCompatActivity {
    Button signup, loginbtn1;
    SharedPreferences sharedpreferences12;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference db;
    public static final String MyPREFERENCES = "MyPrefs";
    String user;
    Validation validation;
    public static final String SHARED_PREFS12 = "shared_prefs12";

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public static final String SharedUser = "usernameData";
    public static final String USERNAME = "USERNAME";
    // public static final String activityname12 = "donor";
    public static final String activityname12 = "null";

    public static final String PASSWORD_KEY = "email_key";
    EditText username1, password1;
    String u, p;
    TextView linkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_first_page);
        signup = (Button) findViewById(R.id.signupbtn);
        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);

        db = FirebaseDatabase.getInstance().getReference().child("CitizenInfo");
        sharedpreferences12 = getSharedPreferences(SHARED_PREFS12, Context.MODE_PRIVATE);
        loginbtn1 = (Button) findViewById(R.id.loginbtn1);

        loginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = username1.getText().toString();
                p = password1.getText().toString();
                if(!u.isEmpty() && !p.isEmpty() && p.length()>8 && isValidPassword(p)) {
                    db.child(u).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase", "Error getting data", task.getException());
                            } else {
                                System.out.println(task.isSuccessful());
                                System.out.println(task.getResult());
                                String authpass = task.getResult().child("password").getValue().toString();
                                System.out.println("Auth=" + authpass);
                                SharedPreferences.Editor editor = sharedpreferences12.edit();
                                System.out.println("Username=" + u);
                                editor.putString(USERNAME, u);
                                editor.putString(PASSWORD_KEY, p);
                                editor.putString(activityname12, "Donor");
                                System.out.println("password=" + p);
                                System.out.println("PASSWORD_KEY=" + p);
                                // to save our data with key and value.
                                editor.apply();
                                if (p.equals(authpass)) {
                                    Toast.makeText(CitizenFirstPage.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(CitizenFirstPage.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(CitizenFirstPage.this, "Username or password does not match", Toast.LENGTH_SHORT).show();

                                }
                                // Log.d("firebase", "data is"+String.valueOf(task.getResult().getValue()));
                            }

                        }
                    });
                } else
                {
                    Toast.makeText(CitizenFirstPage.this, "Enter valid Username and password", Toast.LENGTH_SHORT).show();

                }
                    {
                    }
                }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CitizenSignup.class);
                startActivity(intent);
            }
        });
    } public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
