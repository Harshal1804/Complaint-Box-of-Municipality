package com.example.khaddamuktabharat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeeComplaintRequestByCitizen extends AppCompatActivity {

    private ListView dataListView;
    private DatabaseReference databaseReference;
    private List<UserData> userDataList;
    private CustomAdapter customAdapter;
    EditText mobileno;
    Button btn;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_complaint_request_by_citizen);
        mobileno=findViewById(R.id.Mobileno);
        btn=findViewById(R.id.btn);
        dataListView = findViewById(R.id.dataListView);
       // databaseReference = FirebaseDatabase.getInstance().getReference("Users1");
        userDataList = new ArrayList<>();
        list=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Users1");
        //customAdapter = new CustomAdapter(this, R.layout.list_item, userDataList);
        dataListView.setAdapter(customAdapter);
        adapter=new ArrayAdapter<String>(this,R.layout.feedbackinfo,R.id.Feedback_info,list);
        userData=new UserData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                fetchDataFromFirebase();
                dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Handle ....item click
                        String selectedItem = list.get(position);//(String) parent.getItemAtPosition(position);

                        // Toast.makeText(CheckRequestByMunicipal.this, selectedItem, Toast.LENGTH_SHORT).show();
                        // Start detail activity and pass data
                        Intent intent = new Intent(SeeComplaintRequestByCitizen.this, CheckDetailReviewSendByCitizen.class);
                        intent.putExtra("selectedItem", selectedItem);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    private void fetchDataFromFirebase() {
        System.out.println("Hiiiiiiiiiiiiii");
        String mob = mobileno.getText().toString();
        ref.orderByChild("mobileNo").equalTo(mob).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=1;
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    System.out.println("data======="+snapshot.getValue().toString());
                    userData=ds.getValue(UserData.class);
                    System.out.println("HIIIIIIIIII");
                    if(mob.equals(userData.getMobileNo()))
                    {
                        list.add("\nRequest Id=" + userData.getNewId().toString());//+"\nReview= "+userData.getReview().toString());//+"\nFeedback= "+feed.getFeedback1().toString()+"\nDesign View Rating= "+feed.getDesign().toString()+"\nUser friendly Rating = "+feed.getUserfrend().toString()+"\nHelpful Rating= "+feed.getHelpful().toString());
                    }i++;
                }
                dataListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }

