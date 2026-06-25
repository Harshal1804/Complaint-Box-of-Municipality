package com.example.khaddamuktabharat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DetailedReviewCheckedByMunicipal extends AppCompatActivity {
    String selectedItem,_showLocation,_storeNameEditText,_mobileNoEditText,_emailEditText,_pincode,_Review;
    String emailid="complaintboxofmunicipality";
    String passwordEmail ="ysgb dkkd opcn wuvx";
    DatabaseReference db,db1;
    private StorageReference storageReference;

String selectedItem1;
    String loc,name,mob,ema,pin,rev,dat,im,req;
    TextView Review,ReqId;
    ImageView imgview;
    String img;
    Button btn;
    private String mailhost = "smtp.gmail.com";

    final int defaultSelection = 1; // Change this index for the default selection

    ListView listView;
    TextView Msg,Datee,showLocation,storeNameEditText,mobileNoEditText,emailEditText,pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_review_checked_by_municipal);
        String[] items = {"Request Send", "Request Under Review", "Request Completed", "Request Rejected"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView = findViewById(R.id.listView);
        imgview = findViewById(R.id.imageView);
        Review = findViewById(R.id.Review);
        ReqId=findViewById(R.id.ReqId);
        Datee = findViewById(R.id.Dateee);
        Msg = findViewById(R.id.Msg);
        db = FirebaseDatabase.getInstance().getReference("Users1");
        storageReference = FirebaseStorage.getInstance().getReference();
        // db1 = FirebaseDatabase.getInstance().getReference("users");
        showLocation = findViewById(R.id.showLocation);
        storeNameEditText = findViewById(R.id.storeNameEditText);
        mobileNoEditText = findViewById(R.id.mobileNoEditText);
        emailEditText = findViewById(R.id.emailEditText);
        pincode = findViewById(R.id.pincode);
        //  db = FirebaseDatabase.getInstance().getReference().child("users");
        btn = findViewById(R.id.submitDataButton);
        listView.setAdapter(adapter);
        listView.setSelection(defaultSelection);
        listView.performItemClick(listView.getChildAt(defaultSelection), defaultSelection, listView.getItemIdAtPosition(defaultSelection));
        String stringHost = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", stringHost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Get the selected item
                selectedItem1 = items[position];
                Toast.makeText(DetailedReviewCheckedByMunicipal.this, "Selected item: " + selectedItem1, Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            selectedItem = intent.getStringExtra("selectedItem");
            String s1=selectedItem.replace("Request Id=","");
            String s=s1.trim();
            Toast.makeText(DetailedReviewCheckedByMunicipal.this, "SEE="+s, Toast.LENGTH_SHORT).show();
            db.child(s).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
System.out.println("******************"+s);
ReqId.setText( "Request Id = " + task.getResult().child("newId").getValue().toString());
                       showLocation.setText("  Location = " + task.getResult().child("locationgps").getValue().toString());
                        storeNameEditText.setText("  Name = " + task.getResult().child("name").getValue().toString());
                        mobileNoEditText.setText("  Mobile no = " + task.getResult().child("mobileNo").getValue().toString());
                        emailEditText.setText("  Email id = " + task.getResult().child("emailid").getValue().toString());
                        pincode.setText("  Pincode = " + task.getResult().child("pincode").getValue().toString());
                        Review.setText(task.getResult().child("review").getValue().toString());
                        Datee.setText("  Date = " + task.getResult().child("date").getValue().toString());
                        img = task.getResult().child("imageUrl").getValue().toString();
                        Msg.setText("Message= " + task.getResult().child("Message113").getValue().toString());
                        if (img != null && !img.isEmpty()) {
                            Picasso.get().load(img).into(imgview);
                        }
                        loc = task.getResult().child("locationgps").getValue().toString();
                        //storeNameEditText.setText("  Name = "+task.getResult().child("name").getValue().toString());
                        name = task.getResult().child("name").getValue().toString();
                        // mobileNoEditText.setText("  Mobile no = "+task.getResult().child("mobileNo").getValue().toString());
                        mob = task.getResult().child("mobileNo").getValue().toString();
                        // emailEditText.setText("  Email id = "+task.getResult().child("emailid").getValue().toString());
                        ema = task.getResult().child("emailid").getValue().toString();
                        // pincode.setText("  Pincode = "+task.getResult().child("pincode").getValue().toString());
                        pin = task.getResult().child("pincode").getValue().toString();
                        req = task.getResult().child("newId").getValue().toString();

                        //  Review.setText("  Review = "+task.getResult().child("review").getValue().toString());
                        //  Datee.setText("  Date = "+task.getResult().child("date").getValue().toString());
                        dat = task.getResult().child("date").getValue().toString();
                        img = task.getResult().child("imageUrl").getValue().toString();


                        // Log.d("firebase", "data is"+String.valueOf(task.getResult().getValue()));
                    }
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       updateData();
                                       String mob = mobileNoEditText.getText().toString();
                                       String em = emailEditText.getText().toString().trim();
                                       System.out.println("em=========" + em);
               /* db.child(mob).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        } else {*/
                                       //   String authpass = task.getResult().child("hospPass1").getValue().toString();
                        /*   javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator() {
                                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                                   //  char pas[]=passwordEmail.toCharArray();
                                    return new javax.mail.PasswordAuthentication(emailid,passwordEmail);
                                }
                            });
                            MimeMessage mimeMessage = new MimeMessage(session);
                            try {
                                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(em));
                                mimeMessage.setSubject("Subject: Updated Your Request");
                                mimeMessage.setText("\nYour request Updates=" + rev + "\n\nThank You");
                            } catch (MessagingException ex) {
                                throw new RuntimeException(ex);
                            }

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Transport.send(mimeMessage);
                                    } catch (MessagingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                        }
                // }
              });
            }

        //);


    //}*/
                                   }
                               });}
                // Log.d("firebase", "data is"+String.valueOf(task.getResult().getValue()));


    public void updateData() {
        System.out.println("Mob==" + req);
        db.child(req).child("address").setValue(loc);
        db.child(req).child("date").setValue(dat);
        db.child(req).child("emailid").setValue(ema);
        db.child(req).child("locationgps").setValue(loc);
        db.child(req).child("mobileNo").setValue(mob);
        db.child(req).child("name").setValue(name);
        db.child(req).child("pincode").setValue(pin);
        rev = selectedItem1;
        db.child(req).child("review").setValue(rev);
        Toast.makeText(DetailedReviewCheckedByMunicipal.this, "Information Updated successfully", Toast.LENGTH_SHORT).show();

    }}


        /* UserData userData = new UserData(pin, ema, name, mob, loc, im, dat, rev, loc);
        db.child(mob).setValue(userData).addOnCompleteListener(this, task1 ->
        {
            if (task1.isSuccessful()) {
                Toast.makeText(DetailedReviewCheckedByMunicipal.this, "Information Updated successfully", Toast.LENGTH_SHORT).show();
                // Data saved successfully
                // clearFields();
            } else {
                // Handle failure to save data
            }


        });
*/
