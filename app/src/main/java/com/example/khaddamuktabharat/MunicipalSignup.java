package com.example.khaddamuktabharat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MunicipalSignup extends Activity {
EditText pass,cpass,email,cname,pincode,mobile;
Button btn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private Spinner spinnerState, spinnerCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipal_signup);
pass=(EditText)findViewById(R.id.pass);
        cpass=(EditText)findViewById(R.id.cpass);
        email=(EditText)findViewById(R.id.email);
        cname=(EditText)findViewById(R.id.cname);
        pincode=(EditText)findViewById(R.id.pincode);
        btn=(Button)findViewById(R.id.btn);
        mobile=(EditText) findViewById(R.id.mobile);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        spinnerState = findViewById(R.id.spinnerState);
        // Create ArrayAdapter for State
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(
                this, R.array.states_array, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(stateAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       String PINCODE = pincode.getText().toString().trim();
                                       String PASS = pass.getText().toString().trim();
                                       String CPASS = cpass.getText().toString().trim();
                                       String EMAIL = email.getText().toString().trim();
                                       String CNAME = cname.getText().toString().trim();
                                       String MOBILE = mobile.getText().toString().trim();
                                       String SPINERSTATE = spinnerState.getSelectedItem().toString();
                                       System.out.println("Spiner State================" + SPINERSTATE);
                                       String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                                        String pinpattern="^[1-9]{1}[0-9]{5}$";
                                    if (!PINCODE.isEmpty() && CNAME.length()>=2 && android.util.Patterns.PHONE.matcher(MOBILE).matches() && PASS.length()>8 && isValidPassword(PASS) &&!CNAME.isEmpty() && !EMAIL.isEmpty() && !PASS.isEmpty() && !CPASS.isEmpty() && !MOBILE.isEmpty() && EMAIL.matches(emailPattern) &&PINCODE.matches(pinpattern)) {
                                           if (PASS.equals(CPASS)) {
                                               mAuth.createUserWithEmailAndPassword(MOBILE + "@domain.com", PASS).addOnCompleteListener(MunicipalSignup.this, task -> {
                                                   if (task.isSuccessful()) {
                                                       // Sign up success, update UI with the signed-in user's information
                                                       FirebaseUser user = mAuth.getCurrentUser();
                                                       if (user != null) {
                                                           // Save additional user information to Firebase Realtime Database
                                                           saveUserDataToDatabase(user.getUid(), SPINERSTATE,PASS,CPASS,EMAIL,CNAME,PINCODE,MOBILE);
                                                           // Example: Display a toast with the signup information
                                                           //String signupMessage = "Name: " + CNAME + "\nMobile: " + MOBILE;
                                                           Toast.makeText(MunicipalSignup.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                                                            Intent i=new Intent(MunicipalSignup.this,MunicipalFirstPage.class);
                                                            startActivity(i);
                                                       }
                                                   } else {
                                                       // If sign up fails, display a message to the user.
                                                       Toast.makeText(MunicipalSignup.this, "Authentication failed.",
                                                               Toast.LENGTH_SHORT).show();
                                                   }
                                               });


                                           } else {
                                               Toast.makeText(MunicipalSignup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                                           }
                                       } else {
                                           Toast.makeText(MunicipalSignup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                                       }
                                   }

                                   private void saveUserDataToDatabase(String userId,String state,String pass,String cpass,String email,String cname,String pincode,String mobile) {
                                       MunicipalSignupClass user = new MunicipalSignupClass(state,pass,cpass,email,cname,pincode,mobile);
                                       mDatabase.child("MunicipalInfo").child(pincode).setValue(user);
                                   }
                               });}
        public static boolean isValidPassword(final String password) {

            Pattern pattern;
            Matcher matcher;
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);

            return matcher.matches();

        }
    }



            // Set listener for State spinner





