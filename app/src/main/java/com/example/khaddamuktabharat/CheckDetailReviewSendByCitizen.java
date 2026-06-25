package com.example.khaddamuktabharat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CheckDetailReviewSendByCitizen extends AppCompatActivity {
       String selectedItem,_showLocation,_storeNameEditText,_mobileNoEditText,_emailEditText,_pincode,_Review;
    DatabaseReference db;
       TextView Review;
        ImageView imgview;
        Button btn;
        TextView Msg,Datee,showLocation,storeNameEditText,mobileNoEditText,emailEditText,pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_detail_review_send_by_citizen);
      imgview=findViewById(R.id.imageView);
       Review=findViewById(R.id.Review);
       Datee=findViewById(R.id.Dateee);
       Msg=findViewById(R.id.Msg);
       showLocation=findViewById(R.id.showLocation);
       storeNameEditText=findViewById(R.id.storeNameEditText);
       mobileNoEditText=findViewById(R.id.mobileNoEditText);
       emailEditText=findViewById(R.id.emailEditText);
       pincode=findViewById(R.id.pincode);
        db = FirebaseDatabase.getInstance().getReference().child("Users1");
        // Msg=findViewById(R.id.Msg);
      //  btn=findViewById(R.id.submitDataButton);
        Intent intent = getIntent();
        if (intent != null) {
            selectedItem = intent.getStringExtra("selectedItem");
           // selectedItem = intent.getStringExtra("selectedItem");
            String s1=selectedItem.replace("Request Id=","");
            String s=s1.trim();



            db.child(s).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    showLocation.setText("  Location = "+task.getResult().child("locationgps").getValue().toString());
                    storeNameEditText.setText("  Name = "+task.getResult().child("name").getValue().toString());
                    mobileNoEditText.setText("  Mobile no = "+task.getResult().child("mobileNo").getValue().toString());
                    emailEditText.setText("  Email id = "+task.getResult().child("emailid").getValue().toString());
                    pincode.setText("  Pincode = "+task.getResult().child("pincode").getValue().toString());
                    Review.setText("  Review = "+task.getResult().child("review").getValue().toString());
                    Datee.setText("  Date = "+task.getResult().child("date").getValue().toString());
                    Msg.setText("  Msg = "+task.getResult().child("Message113").getValue().toString());
                    String img=task.getResult().child("imageUrl").getValue().toString();
                    if (img != null && ! img.isEmpty()) {
                        Picasso.get().load(img).into(imgview);
                    }

                    // Log.d("firebase", "data is"+String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }
}


        }
