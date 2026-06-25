package com.example.khaddamuktabharat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class CheckYourComplaintReview extends AppCompatActivity {

    private ListView dataListView;
    private DatabaseReference databaseReference;
    private List<UserData> userDataList;
    private CustomAdapter customAdapter;
EditText mobileno;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_your_complaint_review);
        mobileno=findViewById(R.id.Mobileno);
        btn=findViewById(R.id.btn);

        dataListView = findViewById(R.id.dataListView);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userDataList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, R.layout.list_item, userDataList);
        dataListView.setAdapter(customAdapter);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fetchDataFromFirebase();
        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle ....item click
                String selectedItem = String.valueOf(userDataList.get(position).getMobileNo());
                Toast.makeText(CheckYourComplaintReview.this, selectedItem, Toast.LENGTH_SHORT).show();
                // Start detail activity and pass data
                Intent intent = new Intent(CheckYourComplaintReview.this, CheckDetailReviewSendByCitizen.class);
                intent.putExtra("selectedItem", selectedItem);
                startActivity(intent);
            }
        });
    }
});

    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDataList.clear();
                userDataList.clear();
                String mob=mobileno.getText().toString();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData userData = snapshot.getValue(UserData.class);
                    if (userData != null) {
                        if(userData.getMobileNo().equals(mob))
                        {
                        userDataList.add(userData);

                    }}
                }

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
