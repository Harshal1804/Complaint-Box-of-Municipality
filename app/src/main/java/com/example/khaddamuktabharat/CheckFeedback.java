package com.example.khaddamuktabharat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckFeedback extends AppCompatActivity {
    private ListView listView;
    FirebaseDatabase database;
    // creating a new array list.
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FeedbackFormStore feed;
    // creating a variable for database reference.
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_feedback);
        feed=new FeedbackFormStore();
        listView = (ListView) findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
        adapter=new ArrayAdapter<String>(this,R.layout.complaintxmlfile,R.id.Feedback_info,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=1;
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    feed=ds.getValue(FeedbackFormStore.class);
                list.add("\n"+i+" Mobile no="+feed.getMob1().toString()+"\nName= "+feed.getName1().toString()+"\n Application User Interface= "+feed.getUserfrend().toString()+"\nHelpful= "+feed.getHelpful().toString()+"\nDesign= "+feed.getDesign().toString()+"\n");
                    i++;
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}