package com.example.khaddamuktabharat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class CheckRequestByMunicipal extends AppCompatActivity {

    private ListView dataListView;
    UserData userData;
    private DatabaseReference databaseReference;
    DatabaseReference ref;
    private List<UserData> userDataList;
    //private CustomAdapter customAdapter;
    EditText mobileno;
    Button btn;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request_by_municipal);
        mobileno = findViewById(R.id.Mobileno);
        btn = findViewById(R.id.btn);
        list=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference().child("Users1");
        dataListView = findViewById(R.id.dataListView);
       // databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userDataList = new ArrayList<>();
       // customAdapter = new CustomAdapter(this, R.layout.list_item, userDataList);
        adapter=new ArrayAdapter<String>(this,R.layout.feedbackinfo,R.id.Feedback_info,list);
        userData=new UserData();
    //list=null;
       // dataListView.setAdapter(customAdapter);
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
                        Intent intent = new Intent(CheckRequestByMunicipal.this, DetailedReviewCheckedByMunicipal.class);
                        intent.putExtra("selectedItem", selectedItem);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    private void fetchDataFromFirebase() {
        userDataList.clear();
        userDataList.clear();
        String mob = mobileno.getText().toString();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=1;
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    userData=ds.getValue(UserData.class);
                    if(mob.equals(userData.getPincode())&&((userData.getReview().equals("Request Send")) ||(userData.getReview().equals("Request Under Review"))))
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
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDataList.clear();
                userDataList.clear();
                String mob = mobileno.getText().toString();
                System.out.println("Pincode======" + mob);
              int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData userData = snapshot.getValue(UserData.class);
                    if (userData != null) {
                        System.out.println("pincode====" + mob);

                        if(userData.getPincode().equals(mob)) {
                            System.out.println("get pincode=" + userData.getPincode());
                            System.out.println("pincode=" + mob);
                            userDataList.add(userData);

                        }
                    } i++;
                }
                dataListView.setAdapter(adapter);
               // customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });*/
   // }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.municipalmenu, menu);
        return true;
    }


        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            switch (item.getItemId()) {
                case R.id.Home_page:

                    Intent intent = new Intent(getApplicationContext(), CommonPageToAll.class);
                    startActivity(intent);
                    break;
                case R.id.CheckRequest:

                    Intent intent2 = new Intent(getApplicationContext(), CheckRequestByMunicipal.class);
                    startActivity(intent2);
                    break;
                case R.id.CheckFeedback:

                    Intent intent20 = new Intent(getApplicationContext(), CheckFeedback.class);
                    startActivity(intent20);
                    break;
                case R.id.Logout:
                    Intent intent22 = new Intent(getApplicationContext(), CommonPageToAll.class);
                    startActivity(intent22);
                    break;
            }
            return super.onOptionsItemSelected(item);

        }
    }
