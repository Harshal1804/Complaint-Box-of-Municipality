package com.example.khaddamuktabharat;
import static com.google.firebase.appcheck.internal.util.Logger.TAG;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import android.location.LocationListener;
import android.location.LocationManager;
public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private static final int REQUEST_LOCATION = 1;
    FusedLocationProviderClient mFusedLocationClient;

    int PERMISSION_ID = 44;
    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri imageUri;
   EditText Msg;
    private EditText nameEditText, mobileNoEditText, addressEditText;
    private ImageView imageView;
    private Button uploadImageButton, submitDataButton;
    //private LocationManager locationManager;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    TextView showLocation;
    LocationManager locationManager;
    String latitude, longitude;
    // Define the pic id
    private static final int pic_id = 123;
    // Define the button and imageview type variable
    ImageButton camera_open_id;
    ImageView click_image_id;
    private TextView locationTextView;
    private LocationListener locationListener;
    //private LocationManager locationManager;
    private static final int PERMISSION_REQUEST_CODE = 1;
   // FusedLocationProviderClient mFusedLocationClient;
EditText pincode,emailid;
TextView locationText;
    String newDateStr,maplocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    String review="Request Send";
    // Initializing other items
    // from layout file
    TextView latitudeTextView, longitTextView;
   // int PERMISSION_ID = 44;
    String date="20-02-2024";
    String newId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Msg=findViewById(R.id.Msg);
       /* ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        showLocation = findViewById(R.id.showLocation);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }

*/
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference.child("Users1").orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Child exists, get the most recent ID
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String recentId = snapshot.getKey();
                        int newKey=Integer.parseInt(recentId)+1;
                        newId=""+newKey;
                        Log.d(TAG, "Most recent ID: " + recentId);
                        // Now you can use this recent ID
                    }
                } else {
                    // Child doesn't exist, create a new ID
                    newId =""+1;  //databaseReference.child("Users1").push().getKey();
                    Log.d(TAG, "New ID created: " + newId);
                    // Now you can use this new ID
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database Error: " + databaseError.getMessage());
            }
        });

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // method to get the location
        getLastLocation();
        locationText = findViewById(R.id.showLocation);
       // Button getLocationButton = findViewById(R.id.getLocationButton);
       // Msg=findViewById(R.id.Msg);
       nameEditText = findViewById(R.id.storeNameEditText);
        mobileNoEditText = findViewById(R.id.mobileNoEditText);
        pincode = findViewById(R.id.pincode);
        emailid = findViewById(R.id.emailEditText);
        imageView = findViewById(R.id.imageView);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        submitDataButton = findViewById(R.id.submitDataButton);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        newDateStr = sdf.format(new Date());
       /* SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

        newDateStr = postFormater.format(dateObj); */
        databaseReference = FirebaseDatabase.getInstance().getReference("Users1");
        storageReference = FirebaseStorage.getInstance().getReference();


        // By ID we can get each component which id is assigned in XML file get Buttons and imageview.

    }
    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            locationText.setText(location.getLatitude() +" "+location.getLongitude());
                           // longitTextView.setText(location.getLongitude() + "");
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            locationText.setText(mLastLocation.getLatitude() + " "+mLastLocation.getLongitude());

            //latitudeTextView.setText("Latitude: " + mLastLocation.getLatitude() + "");
            //longitTextView.setText("Longitude: " + mLastLocation.getLongitude() + "");
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    public void uploadImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    public void submitData(View view) {
        if (imageUri != null) {
            uploadImageToFirebase();
        } else {
            saveDataToFirebase(null);
        }
    }

    private void uploadImageToFirebase() {
        StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

        imageReference.putFile(imageUri)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        imageReference.getDownloadUrl().addOnCompleteListener(this, downloadTask -> {
                            if (downloadTask.isSuccessful()) {
                                String imageUrl = downloadTask.getResult().toString();
                                saveDataToFirebase(imageUrl);
                            } else {
                                // Handle failure to get image download URL
                            }
                        });
                    } else {
                        // Handle image upload failure
                    }
                });
    }

    private void saveDataToFirebase(String imageUrl) {
        String name = nameEditText.getText().toString().trim();
        String msg112=Msg.getText().toString();
        String mobileNo = mobileNoEditText.getText().toString().trim();
        String email = emailid.getText().toString().trim();
        String pincode1=pincode.getText().toString();
        String locationgps=locationText.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(!name.isEmpty()&& !msg112.isEmpty() && !mobileNo.isEmpty() && !email.isEmpty() && !pincode1.isEmpty() && name.length()>=2 && android.util.Patterns.PHONE.matcher(mobileNo).matches() && email.matches(emailPattern) ){
        UserData userData = new UserData(newId,pincode1, email, name, mobileNo, maplocation, imageUrl,newDateStr,review,msg112,locationgps);
        databaseReference.child(newId).setValue(userData)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Information Send successfully", Toast.LENGTH_SHORT).show();
                        // Data saved successfully
                        clearFields();
                    } else {
                        // Handle failure to save data
                    }
                });
        }
        else
        {
            Toast.makeText(this, "please enter valid Information", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        nameEditText.setText("");
        mobileNoEditText.setText("");
       pincode.setText("");
        emailid.setText("");
        Msg.setText("");
        //addressEditText.setText("");
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
    }

    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(uri));
    }

    /*private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                maplocation="Your Location: " + "   " + "Latitude: " + latitude + " " + "Longitude: " + longitude;
                showLocation.setText("Your Location: " + "   " + "Latitude: " + latitude + " " + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


    // This method will help to retrieve the image

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.citizenmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.Home_page:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.CheckYourRequest:

                Intent intent2 = new Intent(getApplicationContext(), SeeComplaintRequestByCitizen.class);
                startActivity(intent2);
                break;

            case R.id.Logout:
                Intent intent22 = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent22);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this,
                permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}

