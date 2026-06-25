package com.example.khaddamuktabharat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
// SignupActivity.java
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CitizenSignup extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_signup);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Example of setting click listener for the "Sign Up" button
        Button signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle sign up button click
                onSignUpButtonClick(v);
            }
        });
    }

    public void onSignUpButtonClick(View view) {
        // Retrieve user input
        EditText nameEditText = findViewById(R.id.editTextName);
        EditText mobileEditText = findViewById(R.id.editTextMobile);
        EditText passwordEditText = findViewById(R.id.editTextPassword);
        EditText confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        final String name = nameEditText.getText().toString().trim();
        final String mobile = mobileEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String confirmPassword = confirmPasswordEditText.getText().toString().trim();
       String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            // Validate and process the signup information as needed
        if (!name.isEmpty() && !mobile.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && name.length()>=2 && android.util.Patterns.PHONE.matcher(mobile).matches() && password.length()>8 && isValidPassword(password)) {
            if (password.equals(confirmPassword)) {
                mAuth.createUserWithEmailAndPassword(mobile + "@domain.com", password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign up success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    // Save additional user information to Firebase Realtime Database
                                    saveUserDataToDatabase(user.getUid(), name, mobile,password);
                                    // Example: Display a toast with the signup information
                                    String signupMessage = "Name: " + name + "\nMobile: " + mobile;
                                    Toast.makeText(CitizenSignup.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(CitizenSignup.this,CitizenFirstPage.class);
                                    startActivity(i);
                                }
                            } else {
                                // If sign up fails, display a message to the user.
                                Toast.makeText(CitizenSignup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill in all fields in correct format", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserDataToDatabase(String userId, String name, String mobile,String password) {
        CitizenSignupClass user = new CitizenSignupClass(name, mobile,password);
        mDatabase.child("CitizenInfo").child(mobile).setValue(user);
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
